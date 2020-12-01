package com.ahui.classpack.classes;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.Vector;

import com.ahui.classpack.classes.interfaces.ClassBase;
import com.ahui.classpack.classes.interfaces.EnergyUser;
import com.ahui.classpack.classes.interfaces.Flier;
import com.ahui.classpack.interaction.Glide;
import com.ahui.classpack.ClPk;

import com.ahui.classpack.util.RandInt;

public class Dragon 
extends ClassBase
implements EnergyUser, Flier
{
	private double energy;
	private double energyCap;
	private int level;
	private double cool;
	private int fxp;
	private int saveTime;
	private int fxpTarget;
	private double maxHealth;
	private double regenNum;

	public int taskGlide;
	public int taskSave;
	public int taskCool;
	public int taskReEn;

	public Dragon(String name) 
	{
		super(name);
		this.classID = 6;
		//set in classAssign
		this.energyCap = ClPk.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".maxEnergy");
		this.energy = ClPk.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".energy");
		this.fxp = ClPk.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".fxp");
		this.fxpTarget = ClPk.advCfg.getConfig().getInt("fxpTarget");
		this.prefix = ClPk.advCfg.getConfig().getString("Prefixes.Dragon.default").replaceAll("&", "§");
		this.level = ClPk.playerClass.getConfig().getInt(Dragon.this.getPlayer().getUniqueId()+".level");;
		this.saveTime = ClPk.advCfg.getConfig().getInt("classDataAutoSave");
		this.maxHealth = ClPk.advCfg.getConfig().getInt("Classes.Dragon.maxHealth");
		this.getPlayer().setHealthScale(maxHealth);
		this.getPlayer().setFlySpeed((float) 0.1);
		this.refreshEnergy(6);
		this.showHud();
		this.saveAll();
		this.isGlide();
	}
	public String getClassNm()
	{
		return ClPk.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".class");
	}

	@Override
	@SuppressWarnings("deprecation")
	public void showHud() 
	{
		if(Dragon.this.getPlayer().isOnline()&&Dragon.this.getPlayer()!=null&&this.getClassNm().equalsIgnoreCase("Dragon")){
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard drag = manager.getNewScoreboard();
			Objective drags = drag.registerNewObjective("Dragon", Dragon.this.getPlayer().getUniqueId().toString());

			drags.setDisplaySlot(DisplaySlot.SIDEBAR);
			//if(Dragon.this.getPlayer().getDisplayName().length() <31){
			//	drags.setDisplayName(Dragon.this.getPlayer().getDisplayName());
			//}else{
			//	drags.setDisplayName(Dragon.this.getPlayer().getName());
			//}
				drags.setDisplayName("Stats");
			Score sfxp = drags.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FXP: "+ChatColor.AQUA +getFxp()));
			sfxp.setScore(-4);

			DecimalFormat numberFormat = new DecimalFormat("#.00");
			Score score2 = drags.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ENP: "+ChatColor.AQUA + numberFormat.format(this.getEnergy()).toString()));
			score2.setScore(1);
			Score flt = drags.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FLT: "+ChatColor.AQUA+ChatColor.STRIKETHROUGH +numberFormat.format(this.cool)));
			if(this.cool>0)
			{
				flt = drags.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FLT: "+ChatColor.AQUA +numberFormat.format(this.cool/60)));	
			}
			flt.setScore(0);
			Score level = drags.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ERG: "+ChatColor.AQUA +"+" + numberFormat.format(regenNum)+"/s"));
			level.setScore(-1);
			Dragon.this.getPlayer().setScoreboard(drag);
		}	

	}

	@Override
	public void setFxp(int i)
	{
		if(this.fxp<this.fxpTarget)
		{
			this.fxp=this.fxp+i;
		}
		else
		{
			this.setLevel(this.getLevel()+1);
			this.fxp=0;
		}
	}

	@Override
	public int getFxp()
	{
		return this.fxp;
	}


	@Override
	public void coolDown()
	{
		taskCool = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{	
				if(Dragon.this.getPlayer()!=null)
				{
					if(Dragon.this.cool>0.0)
					{
						Dragon.this.setCool(Dragon.this.getCool()-1);
					}
					ClPk.playerClass.getConfig().set(Dragon.this.getPlayer().getUniqueId()+".ftime", Dragon.this.getCool());
					ClPk.playerClass.saveConfig();
					ClPk.playerClass.reloadConfig();
					Dragon.this.coolDown();
				}
			}
		},20l);
	}

	@Override
	public void setCool(double i)
	{
		this.cool = i;
	}
	@Override
	public double getCool()
	{
		return this.cool;
	}

	@Override
	public double getEnergy() 
	{
		return this.energy;
	}

	@Override
	public void setEnergy(Double newEnergy) 
	{
		this.energy = newEnergy;
		if(this.energy > energyCap)
		{
			this.energy = energyCap;
		}
		this.showHud();
	}

	@Override
	public void refreshEnergy(final int classID) 
	{
		if ((getPlayer() != null)&&(getPlayer().isOnline())&&this.getClassNm().equalsIgnoreCase("Dragon")) {
			taskReEn = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
			{
				@Override
				public void run()
				{
					if(Dragon.this.getPlayer()!=null){
						
						if(Dragon.this.getPlayer()!=null&&Dragon.this.getPlayer().getGameMode()!=GameMode.SURVIVAL)
						{
							Dragon.this.setEnergy(Dragon.this.getEnergy()+20);
						}
						if(Dragon.this.getPlayer()!=null&&Dragon.this.cool>=0.0||Dragon.this.getEnergy()>=0.0)
						{
							Dragon.this.getPlayer().setAllowFlight(true);
						}
						if(Dragon.this.getPlayer()!=null&&Dragon.this.getEnergy()<=0.0)
						{
							Dragon.this.getPlayer().setAllowFlight(false);
						}
						if(Dragon.this.getPlayer()!=null&&Dragon.this.getPlayer().getFireTicks()>0&&Dragon.this.getPlayer().isOnGround())
						{
							Dragon.this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 80, 0));
							Dragon.this.setEnergy(Dragon.this.getEnergy()+(energyCap*0.050));
							regenNum = energyCap*0.050;
						}
						if(Dragon.this.getPlayer()!=null&&Dragon.this.getPlayer().isFlying())
						{
							Dragon.this.setEnergy(Dragon.this.getEnergy()-(Glide.lCost));
						}
						else 
						{
							if(Dragon.this.getPlayer()!=null&&Dragon.this.getPlayer().isOnGround())
							{
								Dragon.this.setEnergy(Dragon.this.getEnergy()+(energyCap*0.025));
								regenNum =energyCap*0.025;
							}
							if(Dragon.this.getPlayer()!=null&&!Dragon.this.getPlayer().isOnGround()&&Dragon.this.getEnergy()<20)
							{
								Dragon.this.setEnergy(Dragon.this.getEnergy()+(Glide.lCost/10));
								regenNum = energyCap*0.075;
							}
						}
						Dragon.this.refreshEnergy(classID);
						Dragon.this.showHud();
					}
				}
			},10L);
		}
	}
	@Override
	public void setLevel(int i) 
	{
		this.level = i;
		if(level <10)
		{
			this.getPlayer().playSound(this.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, RandInt.randInt(0, 5));
			this.level = this.getLevel()+1;
			this.showHud();
			ClPk.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".level", this.getLevel());
			ClPk.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".maxEnergy", this.energyCap*1.1);
			ClPk.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".fxp", this.getFxp());
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
			this.energyCap = ClPk.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId().toString()+".maxEnergy");
		}else
		{
			this.getPlayer().sendMessage(ClPk.prefix+"You have trained as much as possible.");
		}
	}

	@Override
	public int getLevel() {
		return this.level;
	}
	public void isGlide() 
	{
		taskGlide = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{
				if(Dragon.this.getPlayer()!=null){
					Player p = Dragon.this.getPlayer();
					if(ClPk.onlinePlayers.get(p.getName())instanceof Flier)
					{
						if (p.getVelocity().getY() < -.01&&p.isSneaking()){
							if(p.getLocation().getBlock().getRelative(BlockFace.DOWN, 2).getType().equals(Material.AIR)){
								if(((Flier)ClPk.onlinePlayers.get(p.getName())).getEnergy()>10){
									((Flier)ClPk.onlinePlayers.get(p.getName())).setEnergy(((Flier)ClPk.onlinePlayers.get(p.getName())).getEnergy()-1.1);
									((Flier)ClPk.onlinePlayers.get(p.getName())).showHud();
									Vector v = p.getVelocity();
									v.setY(v.getY()* -.1);
									Vector d = p.getLocation().getDirection();
									d.multiply(.1);
									p.playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_FLAP, 1, (float) (9*v.getY()+Math.random()));
									p.setVelocity(v);
									p.setVelocity(d);
									p.setFallDistance(0);
								}
								p.getWorld().playEffect(p.getLocation().getBlock().getRelative(BlockFace.DOWN,3).getLocation(), Effect.STEP_SOUND, p.getLocation().getBlock().getRelative(BlockFace.DOWN,3).getType());

							}
						}
					}
					Dragon.this.isGlide();
				}
			}
		},10);
	}
	@Override
	public void saveAll() 
	{
		taskSave = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{
				if(Dragon.this.getPlayer()!=null){
					Player p = Dragon.this.getPlayer();
					p.sendMessage(ClPk.prefix+"autosaving classData.");
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".energy", Dragon.this.getEnergy());
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".level", Dragon.this.getLevel());
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".fxp", Dragon.this.getFxp());
					ClPk.playerClass.saveConfig();
					ClPk.playerClass.reloadConfig();
					saveAll();
				}
			}
		},saveTime);

	}
	@Override
	public void stop()
	{
		Bukkit.getScheduler().cancelTask(taskCool);
		Bukkit.getScheduler().cancelTask(taskGlide);
		Bukkit.getScheduler().cancelTask(taskReEn);
		Bukkit.getScheduler().cancelTask(taskSave);
	}

	//TODO unused
	@Override
	public String getBranch() 
	{return null;}
	//TODO unused
	@Override
	public void setBranch(String args) 
	{}
}
