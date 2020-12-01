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
import com.ahui.classpack.classes.interfaces.Flier;
import com.ahui.classpack.interaction.Glide;
import com.ahui.classpack.ClPk;

import com.ahui.classpack.util.RandInt;

public class Thestral 
extends ClassBase
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
	protected boolean nightVision;

	
	@SuppressWarnings("deprecation")
	public Thestral(String name) {
		
		super(name);
		this.classID = 15;
		this.prefix = ClPk.advCfg.getConfig().getString("Prefixes.Thestral.default").replaceAll("&", "§");
		this.fxp = ClPk.playerClass.getConfig().getInt(Thestral.this.getPlayer().getUniqueId()+".fxp");
		this.fxpTarget = ClPk.advCfg.getConfig().getInt("fxpTarget");
		this.energy = ClPk.playerClass.getConfig().getDouble(Thestral.this.getPlayer().getUniqueId()+".energy");
		this.energyCap = ClPk.playerClass.getConfig().getDouble(Thestral.this.getPlayer().getUniqueId()+".maxEnergy");
		this.level = ClPk.playerClass.getConfig().getInt(Thestral.this.getPlayer().getUniqueId()+".level");
		this.saveTime = ClPk.advCfg.getConfig().getInt("classDataAutoSave");
		this.cool = ClPk.playerClass.getConfig().getInt(this.getPlayer().getUniqueId().toString()+".ftime");
		this.maxHealth = ClPk.advCfg.getConfig().getInt("Classes.Thestral.maxHealth"); 
		this.getPlayer().setHealthScale(maxHealth);	
		Bukkit.getPlayer(name).addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,Integer.MAX_VALUE,0,true,false));
		this.refreshEnergy(2);
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
		if(Thestral.this.getPlayer()!=null&&this.getClassNm().equalsIgnoreCase("Thestral")){
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard the = manager.getNewScoreboard();
			Objective thes = the.registerNewObjective("Thestral", Thestral.this.getPlayer().getUniqueId().toString());

			thes.setDisplaySlot(DisplaySlot.SIDEBAR);
			//if(Thestral.this.getPlayer().getDisplayName().length() <31){
			//	thes.setDisplayName(Thestral.this.getPlayer().getDisplayName());
			//}else{
			//	thes.setDisplayName(Thestral.this.getPlayer().getName());
			//}
			thes.setDisplayName("Stats");
			Score sfxp = thes.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FXP: "+ChatColor.AQUA +getFxp()));
			sfxp.setScore(-4);
			Double regen = ((this.energyCap*0.025)*2);
			DecimalFormat numberFormat = new DecimalFormat("#.00");
			Score score2 = thes.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ENP: "+ChatColor.AQUA + numberFormat.format(this.getEnergy()).toString()));
			score2.setScore(1);
			Score flt = thes.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FLT: "+ChatColor.AQUA+ChatColor.STRIKETHROUGH +numberFormat.format(this.cool)));
			if(this.cool>0)
			{
				flt = thes.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FLT: "+ChatColor.AQUA +numberFormat.format(this.cool/60)));	
			}
			flt.setScore(0);
			Score level = thes.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ERG: "+ChatColor.AQUA +"+" + numberFormat.format(regen)+"/s"));
			level.setScore(-1);
			Thestral.this.getPlayer().setScoreboard(the);
		}
	}
	@Override
	public void coolDown()
	{
		taskCool = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{	
				if(Thestral.this.getPlayer()!=null)
				{
					if(Thestral.this.cool>0.0)
					{
						Thestral.this.setCool(Thestral.this.getCool()-1);
					}
					ClPk.playerClass.getConfig().set(Thestral.this.getPlayer().getUniqueId()+".ftime", Thestral.this.getCool());
					ClPk.playerClass.saveConfig();
					ClPk.playerClass.reloadConfig();
					Thestral.this.coolDown();
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
				if(Thestral.this.getPlayer()!=null){
					Player p = Thestral.this.getPlayer();
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
					Thestral.this.isGlide();
				}
			}
		},10);
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
	}
	
	@Override
	public void refreshEnergy(final int classID) 
	{
		if ((getPlayer() != null)&&(getPlayer().isOnline())&&this.getClassNm().equalsIgnoreCase("Thestral")) {
			taskReEn = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
			{
				@Override
				public void run()
				{
					if(Thestral.this.getPlayer()!=null)
					{
						if (((Thestral.this.getPlayer()!=null&&Thestral.this.getPlayer().getWorld().getTime()>=12541)||Thestral.this.getPlayer().getGameMode().equals(GameMode.CREATIVE))&&ClPk.playerClass.getConfig().getString(Thestral.this.getPlayer().getUniqueId().toString()+".class").equalsIgnoreCase("Thestral")){
							ClPk.playerClass.getConfig().set(Thestral.this.getPlayer().getUniqueId().toString()+ ".speed", "fast");
							Thestral.this.getPlayer().setFlySpeed((float) (.1*1.33));
						}
						if (((Thestral.this.getPlayer()!=null&&Thestral.this.getPlayer().getWorld().getTime()<12541)&&Thestral.this.getPlayer().getGameMode().equals(GameMode.SURVIVAL))&&ClPk.playerClass.getConfig().getString(Thestral.this.getPlayer().getUniqueId().toString()+".class").equalsIgnoreCase("Thestral")){
							ClPk.playerClass.getConfig().set(Thestral.this.getPlayer().getUniqueId().toString()+ ".speed", "slow");
							Thestral.this.getPlayer().setFlySpeed((float) (.1*0.66));
						}
						if(Thestral.this.getPlayer()!=null&&Thestral.this.getPlayer().getGameMode()!=GameMode.SURVIVAL)
						{
							Thestral.this.setEnergy(Thestral.this.getEnergy()+20);
						}
						if(Thestral.this.getPlayer()!=null&&Thestral.this.cool>=0.0||Thestral.this.getEnergy()>=0.0)
						{
							Thestral.this.getPlayer().setAllowFlight(true);
						}
						if(Thestral.this.getPlayer()!=null&&Thestral.this.getEnergy()<=0.0)
						{
							Thestral.this.getPlayer().setAllowFlight(false);
						}
						if(Thestral.this.getPlayer()!=null&&Thestral.this.getPlayer().isFlying())
						{
							Thestral.this.setEnergy(Thestral.this.getEnergy()-Glide.lCost);
						}
						else if(Thestral.this.getPlayer()!=null&&Thestral.this.getPlayer().isOnGround())
						{
							Thestral.this.setEnergy(Thestral.this.getEnergy()+(energyCap*0.025));
						}
						if(Thestral.this.getPlayer()!=null&&!Thestral.this.getPlayer().isOnGround()&&Thestral.this.getEnergy()<20)
						{
							Thestral.this.setEnergy(Thestral.this.getEnergy()+(Glide.lCost/10));
						}
						Thestral.this.refreshEnergy(classID);
						Thestral.this.showHud();
					}
				}
			},10L);
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
	public void saveAll()
	{
		taskSave = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{
				if(Thestral.this.getPlayer()!=null){
					Player p = Thestral.this.getPlayer();
					p.sendMessage(ClPk.prefix+"autosaving classData.");
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".energy", Thestral.this.getEnergy());
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".level", Thestral.this.getLevel());
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".fxp", Thestral.this.getFxp());
					ClPk.playerClass.saveConfig();
					ClPk.playerClass.reloadConfig();
					saveAll();
				}
			}
		},saveTime);
	}
	@Override
	public void setLevel(int i) {
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
	public void stop()
	{
		Bukkit.getScheduler().cancelTask(taskCool);
		Bukkit.getScheduler().cancelTask(taskGlide);
		Bukkit.getScheduler().cancelTask(taskReEn);
		Bukkit.getScheduler().cancelTask(taskSave);
	}

}
