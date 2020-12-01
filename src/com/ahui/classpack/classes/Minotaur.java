package com.ahui.classpack.classes;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.ahui.classpack.classes.interfaces.ClassBase;
import com.ahui.classpack.classes.interfaces.EnergyUser;
import com.ahui.classpack.ClPk;


public class Minotaur 
extends ClassBase
implements EnergyUser
{

	private String Branch;
	private double energyCap;
	private double energy;
	private int level;
	private int saveTime;
	private double maxHealth;
	public int taskSave;
	public int taskReEn;

	public Minotaur(String name) 
	{
		super(name);
		this.classID = 13;

		this.Branch = ClPk.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".branch");
		this.energyCap = ClPk.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".maxEnergy");
		this.energy = ClPk.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".energy");
		this.level = ClPk.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".level");
		this.prefix = ClPk.advCfg.getConfig().getString("Prefixes.Centaur."+Branch).replaceAll("&", "§");
		this.saveTime = ClPk.advCfg.getConfig().getInt("classDataAutoSave");
		this.maxHealth = ClPk.advCfg.getConfig().getInt("Classes.Centaur.maxHealth"); 
		this.getPlayer().setHealthScale(maxHealth);
		this.getPlayer().setFlySpeed((float) 0.1);
		
		
		refreshEnergy(3);
		showHud();
		saveAll();
	}

	public String getClassNm()
	{
		return ClPk.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".class");
	}

	@Override
	@SuppressWarnings("deprecation")
	public void showHud()
	{
		if(Minotaur.this.getPlayer()!=null&&this.getClassNm().equalsIgnoreCase("Minotaur")){
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard cen = manager.getNewScoreboard();
			Objective cent = cen.registerNewObjective("Minotaur", this.getPlayer().getUniqueId().toString());

			cent.setDisplaySlot(DisplaySlot.SIDEBAR);
			String title = "";
			if(!this.getBranch().equalsIgnoreCase("default"))
			{
				title = this.prefix.substring(0,this.prefix.indexOf("E")).replace("[", "").replace("]", "");
			}
			//if(this.getPlayer().getDisplayName().length() <31){
			//	cent.setDisplayName(title+this.getPlayer().getDisplayName()+title);
			//}else{
			//cent.setDisplayName(title+this.getPlayer().getName()+title);
			//}
			cent.setDisplayName(title+"Stats"+title);
			
			Double regen = (((this.energyCap/3)*0.025)*2);
			DecimalFormat numberFormat = new DecimalFormat("#.00");
			Score score2 = cent.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ENP: "+ChatColor.GREEN + numberFormat.format(this.getEnergy()).toString()));
			score2.setScore(1);
			Score level = cent.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ERG: "+ChatColor.GREEN +"+" + numberFormat.format(regen)+"/s"));
			level.setScore(-1);
			this.getPlayer().setScoreboard(cen);
		}
	}

	@Override
	public double getEnergy() {
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
		if ((getPlayer() != null)&&(getPlayer().isOnline())&&this.getClassNm().equalsIgnoreCase("Minotaur")) {
			taskReEn = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
			{
				@Override
				public void run()
				{
					if(Minotaur.this.getPlayer()!=null){
						if(Minotaur.this.getPlayer()!=null&&Minotaur.this.getPlayer().getGameMode()!=GameMode.SURVIVAL)
						{
							Minotaur.this.setEnergy(Minotaur.this.getEnergy()+20);
						}
						Minotaur.this.setEnergy(Minotaur.this.getEnergy()+((energyCap/3)*0.025));
						if(Minotaur.this.getPlayer()!=null&&Minotaur.this.getBranch().equalsIgnoreCase("miner"))
						{
							if(Minotaur.this.getPlayer()!=null&&Minotaur.this.getPlayer().getLocation().getY()<50)
							{
								Minotaur.this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 0));
								Minotaur.this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0));
								if(Minotaur.this.getPlayer()!=null&&Minotaur.this.getPlayer().getLocation().getY()<25)
								{
									Minotaur.this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
									Minotaur.this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 0));
									Minotaur.this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 0));
								}
								else
								{
									Minotaur.this.getPlayer().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
									Minotaur.this.getPlayer().removePotionEffect(PotionEffectType.REGENERATION);
									Minotaur.this.getPlayer().removePotionEffect(PotionEffectType.SATURATION);

								}
							}else{
								Minotaur.this.getPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
								Minotaur.this.getPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION);
							}
						}
						Minotaur.this.showHud();
						Minotaur.this.refreshEnergy(classID);
					}
				}
			},10L);
		}

	}
	@Override
	public String getBranch()
	{
		return this.Branch;
	}
	@Override
	public void setBranch(String bName)
	{
		//Three branches (miner/farmer/breeder)
		if(this.getBranch()!=bName)
		{
			this.Branch = bName;
			ClPk.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".branch", this.Branch);
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
			this.prefix = ClPk.advCfg.getConfig().getString("Prefixes.Centaur."+Branch).replaceAll("&", "§");
		}
	}
	@Override
	public void setLevel(int i)
	{
		if(level <10)
		{
			this.level = this.getLevel()+1;
			this.showHud();
			ClPk.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".level", this.getLevel());
			ClPk.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".maxEnergy", +5);
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
			this.energyCap = ClPk.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId().toString()+".maxEnergy");
		}else
		{
			this.getPlayer().sendMessage(ClPk.prefix+"You cannot learn anything more from these tomes, you destroyed the material.");
		}
	}
	@Override
	public int getLevel()
	{
		return this.level;
	}
	public void saveAll()
	{
		taskSave = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{
				if(Minotaur.this.getPlayer()!=null)
				{
					Player p = Minotaur.this.getPlayer();
					p.sendMessage(ClPk.prefix+"autosaving classData.");
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".branch", Minotaur.this.getBranch());
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".mana", Minotaur.this.getEnergy());
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".level", Minotaur.this.getLevel());
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
		Bukkit.getScheduler().cancelTask(taskSave);
		Bukkit.getScheduler().cancelTask(taskReEn);
	}


}
