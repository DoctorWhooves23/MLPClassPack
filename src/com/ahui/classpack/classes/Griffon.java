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
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.Vector;

import com.ahui.classpack.classes.interfaces.ClassBase;
import com.ahui.classpack.classes.interfaces.Flier;
import com.ahui.classpack.interaction.Glide;
import com.ahui.classpack.ClPk;

import com.ahui.classpack.util.RandInt;

public class Griffon extends ClassBase
implements Flier
{
	private double energy;
	private double energyCap;
	private int level;
	private double cool;
	private int fxp;
	private int saveTime;
	private int fxpTarget;
	private double maxHealth;
	
	
	public int taskGlide;
	public int taskCool;
	public int taskSave;
	public int taskReEn;
	public Griffon(String name) 
	{
		super(name);
		this.classID = 8;
		this.prefix = ClPk.advCfg.getConfig().getString("Prefixes.Griffon.default").replaceAll("&", "§");
		this.fxp = ClPk.playerClass.getConfig().getInt(Griffon.this.getPlayer().getUniqueId()+".fxp");
		this.fxpTarget = ClPk.advCfg.getConfig().getInt("fxpTarget");
		this.energy = ClPk.playerClass.getConfig().getDouble(Griffon.this.getPlayer().getUniqueId()+".energy");
		this.energyCap = ClPk.playerClass.getConfig().getDouble(Griffon.this.getPlayer().getUniqueId()+".maxEnergy");
		this.level = ClPk.playerClass.getConfig().getInt(Griffon.this.getPlayer().getUniqueId()+".level");;
		this.saveTime = ClPk.advCfg.getConfig().getInt("classDataAutoSave");
		this.cool = ClPk.playerClass.getConfig().getInt(this.getPlayer().getUniqueId().toString()+".ftime");
		this.maxHealth = ClPk.advCfg.getConfig().getInt("Classes.Griffon.maxHealth"); 
		this.getPlayer().setHealthScale(maxHealth);
		this.getPlayer().setFlySpeed((float) 0.1);
		this.refreshEnergy(8);
		this.showHud();
		this.saveAll();
		this.coolDown();
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
		if(Griffon.this.getPlayer()!=null&&this.getClassNm().equalsIgnoreCase("Griffon")){
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard grif = manager.getNewScoreboard();
			Objective grifs = grif.registerNewObjective("Griffon", Griffon.this.getPlayer().getUniqueId().toString());

			grifs.setDisplaySlot(DisplaySlot.SIDEBAR);
			//if(Griffon.this.getPlayer().getDisplayName().length() <31){
			//	grifs.setDisplayName(Griffon.this.getPlayer().getDisplayName());
			//}else{
			//	grifs.setDisplayName(Griffon.this.getPlayer().getName());
			//}
			grifs.setDisplayName("Stats");
			Score sfxp = grifs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FXP: "+ChatColor.AQUA +getFxp()));
			sfxp.setScore(-4);
			Double regen = ((this.energyCap*0.025)*2);
			DecimalFormat numberFormat = new DecimalFormat("#.00");
			Score score2 = grifs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ENP: "+ChatColor.AQUA + numberFormat.format(this.getEnergy()).toString()));
			score2.setScore(1);
			Score flt = grifs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FLT: "+ChatColor.AQUA+ChatColor.STRIKETHROUGH +numberFormat.format(this.cool)));
			if(this.cool>0)
			{
				flt = grifs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FLT: "+ChatColor.AQUA +numberFormat.format(this.cool/60)));	
			}
			flt.setScore(0);
			Score level = grifs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ERG: "+ChatColor.AQUA +"+" + numberFormat.format(regen)+"/s"));
			level.setScore(-1);
			Griffon.this.getPlayer().setScoreboard(grif);
		}
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
	}


	@Override
	public void refreshEnergy(final int classID) 
	{
		if ((getPlayer() != null)&&(getPlayer().isOnline())&&this.getClassNm().equalsIgnoreCase("Griffon")) {
			taskReEn = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
			{
				@Override
				public void run()
				{
					if(Griffon.this.getPlayer()!=null){
						
						if(Griffon.this.getPlayer()!=null&&Griffon.this.getPlayer().getGameMode()!=GameMode.SURVIVAL)
						{
							Griffon.this.setEnergy(Griffon.this.getEnergy()+20.0);
						}
						if(Griffon.this.getPlayer()!=null&&Griffon.this.cool>=0.0||Griffon.this.getEnergy()>=0.0)
						{
							Griffon.this.getPlayer().setAllowFlight(true);
						}
						if(Griffon.this.getPlayer()!=null&&Griffon.this.getEnergy()<=0.0)
						{
							Griffon.this.getPlayer().setAllowFlight(false);
						}
						if(Griffon.this.getPlayer()!=null&&Griffon.this.getPlayer().isOnGround()&&Griffon.this.getEnergy()<20.0)
						{
							Griffon.this.setEnergy(Griffon.this.getEnergy()+0.25);
						}
						if(Griffon.this.getPlayer()!=null&&Griffon.this.getPlayer().isFlying())
						{
							Griffon.this.setEnergy(Griffon.this.getEnergy()-(Glide.lCost));
						}
						else if(Griffon.this.getPlayer()!=null&&Griffon.this.getPlayer().isOnGround())
						{
							Griffon.this.setEnergy(Griffon.this.getEnergy()+(energyCap*0.025));
						}
						Griffon.this.refreshEnergy(classID);
						Griffon.this.showHud();
					}
				}
			},10L);
		}
	}

	@Override
	public void setCool(double intCool) 
	{
		this.cool = intCool;
	}

	@Override
	public double getCool() 
	{
		return this.cool;
	}

	@Override
	public void saveAll() 
	{
		taskSave = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{
				if(Griffon.this.getPlayer()!=null){
					Player p = Griffon.this.getPlayer();
					p.sendMessage(ClPk.prefix+"autosaving classData.");
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".energy", Griffon.this.getEnergy());
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".level", Griffon.this.getLevel());
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".fxp", Griffon.this.getFxp());
					ClPk.playerClass.saveConfig();
					ClPk.playerClass.reloadConfig();
					saveAll();
				}
			}
		},saveTime);

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
	public int getLevel() 
	{
		return this.level;
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
	public void coolDown() {
		taskCool = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{	
				if(Griffon.this.getPlayer()!=null)
				{
					if(Griffon.this.getPlayer()!=null&&Griffon.this.cool>0.0)
					{
						Griffon.this.setCool(Griffon.this.getCool()-1);
					}
					ClPk.playerClass.getConfig().set(Griffon.this.getPlayer().getUniqueId()+".ftime", Griffon.this.getCool());
					ClPk.playerClass.saveConfig();
					ClPk.playerClass.reloadConfig();
					Griffon.this.coolDown();
				}
			}
		},20l);

	}
	public void isGlide()
	{
		taskGlide = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{
				if(Griffon.this.getPlayer()!=null){
					Player p = Griffon.this.getPlayer();
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
					Griffon.this.isGlide();
				}
			}
		},10);
	}
	@Override
	public void stop()
	{
		Bukkit.getScheduler().cancelTask(taskCool);
		Bukkit.getScheduler().cancelTask(taskGlide);
		Bukkit.getScheduler().cancelTask(taskReEn);
		Bukkit.getScheduler().cancelTask(taskSave);
	}

}
