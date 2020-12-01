package com.ahui.classpack.classes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Biome;
import org.bukkit.entity.Entity;
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
import com.ahui.classpack.util.RandInt;

public class TimberWolf 
extends ClassBase
implements EnergyUser
{
	private String Branch;
	private double energyCap;
	private double energy;
	private int level;
	private int saveTime;
	private double maxHealth;
	private Map<Biome, String> biomeMap;
	private double getRegen;
	private double cool;
	private int invisTime;
	private boolean alwaysRun;
	private String bPrefix;
	public int taskReEn;
	public int taskSave;
	public int taskNear;
	public int taskEnvVar;
	public int taskCool;
	@SuppressWarnings("unchecked")
	public TimberWolf(String name) {
		super(name);
		this.classID = 7;

		this.Branch = ClPk.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".pack");
		this.bPrefix = ClPk.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".packPref");
		this.energyCap = ClPk.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".maxEnergy");
		this.energy = ClPk.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".energy");
		this.level = ClPk.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".level");
		this.prefix = ClPk.advCfg.getConfig().getString("Prefixes.TimberWolf.default").replaceAll("&", "§");
		this.saveTime = ClPk.advCfg.getConfig().getInt("classDataAutoSave");
		this.maxHealth = ClPk.advCfg.getConfig().getInt("Classes.TimberWolf.maxHealth"); 
		this.getPlayer().setHealthScale(maxHealth);
		this.getPlayer().setFlySpeed((float) 0.1);
		if(ClPk.advCfg.getConfig().getInt("Classes.TimberWolf.alwaysRun")==1)
		{
			this.alwaysRun = true;
		}
		this.invisTime = ClPk.advCfg.getConfig().getInt("Classes.TimberWolf.invisTime");
		if(ClPk.EnvData.getConfig().getBoolean("enabled")==true)
		{
			biomeMap = (Map<Biome, String>) (ClPk.EnvData.getConfig().getMapList("biomes"));
			environmentVars();
		}	
		refreshEnergy(7);
		showHud();
		saveAll(); 
		this.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
		this.getPlayer().removePotionEffect(PotionEffectType.SPEED);
	}
	public String getClassNm()
	{
		return ClPk.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".class");
	}
	@Override
	@SuppressWarnings("deprecation")
	public void showHud()
	{
		if(TimberWolf.this.getPlayer()!=null&&this.getClassNm().equalsIgnoreCase("TimberWolf")){
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard tw = manager.getNewScoreboard();
			Objective tws = tw.registerNewObjective("TimberWolf", this.getPlayer().getUniqueId().toString());

			tws.setDisplaySlot(DisplaySlot.SIDEBAR);
			String title = "";
			if(!this.getBranch().equalsIgnoreCase("null"))
			{
				title = this.bPrefix.replaceAll("&", "§");;
			}
			/*if(this.getPlayer().getDisplayName().length() <31){
				tws.setDisplayName(title+this.getPlayer().getDisplayName()+title);
			}else{*/
			//tws.setDisplayName(title+this.getPlayer().getName()+title);
			//}
				tws.setDisplayName(title+"Stats"+title);
			Double regen = this.getRegen;
			DecimalFormat numberFormat = new DecimalFormat("#.00");
			Score score2 = tws.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ENP: "+ChatColor.GREEN + numberFormat.format(this.getEnergy()).toString()));
			score2.setScore(1);
			Score level = tws.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ERG: "+ChatColor.GREEN +"+" + numberFormat.format(regen)+"/s"));
			level.setScore(-1);
			this.getPlayer().setScoreboard(tw);
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
		if ((getPlayer() != null)&&(getPlayer().isOnline())&&this.getClassNm().equalsIgnoreCase("TimberWolf")) {
			taskReEn = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
			{
				@Override
				public void run()
				{
					//System.out.print("RUNNING");
					if(TimberWolf.this.getPlayer()!=null){
						
						if(TimberWolf.this.getPlayer()!=null&&!TimberWolf.this.getPlayer().hasPotionEffect(PotionEffectType.SPEED)&&TimberWolf.this.alwaysRun==true)
						{
							TimberWolf.this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED,Integer.MAX_VALUE,0));
						}

						if(TimberWolf.this.getPlayer()!=null&&TimberWolf.this.getPlayer().getGameMode()!=GameMode.SURVIVAL)
						{
							TimberWolf.this.setEnergy(TimberWolf.this.getEnergy()+20);
						}
						if(TimberWolf.this.getPlayer()!=null&&TimberWolf.this.getPlayer().getLocation().getBlock().getType().equals(Material.STATIONARY_WATER))
						{
							TimberWolf.this.setEnergy(TimberWolf.this.getEnergy()+((energyCap/3)*0.075));
							getRegen = ((energyCap/3)*0.075)*2;

						}else{
							TimberWolf.this.setEnergy(TimberWolf.this.getEnergy()+((energyCap/3)*0.025));
							getRegen = ((energyCap/3)*0.025)*2;
						}
						if(TimberWolf.this.getPlayer()!=null&&TimberWolf.this.getPlayer().isSneaking())
						{
							if(TimberWolf.this.getEnergy()>=energyCap/2
									&&TimberWolf.this.getCool()==0)
							{

								TimberWolf.this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,Integer.MAX_VALUE,4));
								TimberWolf.this.setEnergy(TimberWolf.this.getEnergy() - (energyCap/4));
								TimberWolf.this.setCool(TimberWolf.this.invisTime);
								TimberWolf.this.coolDown();
							}
						}

						TimberWolf.this.showHud();
						TimberWolf.this.refreshEnergy(classID);
					}
				}
			},10L);
		}
	}
	public void coolDown()
	{
		taskCool = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{	
				if(TimberWolf.this.getPlayer()!=null)
				{
					if(TimberWolf.this.getPlayer()!=null&&TimberWolf.this.cool>0.0)
					{
						TimberWolf.this.setCool(TimberWolf.this.getCool()-1);
						TimberWolf.this.coolDown();
					}
					else if(TimberWolf.this.getPlayer()!=null&&TimberWolf.this.getCool()==0.0)
					{
						TimberWolf.this.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
					}
				}
			}
		},20l);
	}
	public void setCool(double i)
	{
		this.cool = i;
	}
	public double getCool()
	{
		return this.cool;
	}
	//pack
	@Override
	public String getBranch() 
	{
		return this.Branch;
	}
	//pack
	public void createBranch(String bName, String colour)
	{
		if(ClPk.playerClass.getConfig().contains("Twol."+bName))
		{
			this.getPlayer().sendMessage(ClPk.prefix+ChatColor.RED+"This pack already exists");
		}
		else
		{
			List<String> memList = new ArrayList<String>();
			List<String> invList = new ArrayList<String>();
			memList.add(this.getPlayer().getUniqueId().toString());
			invList.clear();
			ClPk.guilds_packs.getConfig().set("Twol."+bName+".leader", this.getPlayer().getUniqueId().toString());
			ClPk.guilds_packs.getConfig().set("Twol."+bName+".prefix", colour+bName.substring(0,1)+"&r");
			ClPk.guilds_packs.getConfig().set("Twol."+bName+".invitation",invList);
			ClPk.guilds_packs.getConfig().set("Twol."+bName+".members", memList);
			this.Branch = bName;
			ClPk.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".pack", this.Branch);
			ClPk.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".packPref", colour+bName.substring(0,1)+"&r");
			this.bPrefix = ClPk.guilds_packs.getConfig().getString("Twol."+this.Branch+".prefix").replaceAll("&", "§");
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
			ClPk.guilds_packs.saveConfig();
			ClPk.guilds_packs.reloadConfig();
		}
	}
	public void editBranch(String bName, String colour)
	{
		ClPk.guilds_packs.getConfig().set("Twol."+bName+".prefix", colour+bName.substring(0,1)+"&r");
		this.Branch = bName;
		ClPk.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".pack", this.Branch);
		ClPk.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".packPref", colour+bName.substring(0,1)+"&r");
		this.bPrefix = ClPk.guilds_packs.getConfig().getString("Twol."+this.Branch+".prefix").replaceAll("&", "§");
		ClPk.playerClass.saveConfig();
		ClPk.playerClass.reloadConfig();
		ClPk.guilds_packs.saveConfig();
		ClPk.guilds_packs.reloadConfig();
	}
	public void delBranchLeave(String bName)
	{
		this.Branch="null";
		ClPk.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".pack", "null");
		ClPk.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".packPref", "");
		ClPk.playerClass.saveConfig();
		ClPk.playerClass.reloadConfig();
	}
	public void leaveBranch(String bName)
	{
		List<String> memList = ClPk.guilds_packs.getConfig().getStringList("Twol."+bName+".members");
		String leader = ClPk.guilds_packs.getConfig().getString("Twol."+bName+".leader");

		if(leader.equals(this.getPlayer().getUniqueId().toString()))
		{
			this.getPlayer().sendMessage(ClPk.prefix+ChatColor.RED+"You cannot leave your own pack.");
			return;
		}
		if(memList.contains(this.getPlayer().getUniqueId().toString()))
		{
			this.Branch="null";
			ClPk.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".pack", "null");
			ClPk.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".packPref", "");
			memList.remove(this.getPlayer().getUniqueId().toString());
			ClPk.guilds_packs.getConfig().set("Twol."+bName+".members",memList);
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
			ClPk.guilds_packs.saveConfig();
			ClPk.guilds_packs.reloadConfig();
			this.getPlayer().sendMessage("You have left the pack "+bName+".");
		}
		else{
			this.getPlayer().sendMessage("You cannot leave this pack because you are not a member.");
		}
	}
	public void stop(String name)
	{
		if(name.equals(this.getPlayer().getName()))
		{

		}
	}
	//pack
	@Override
	public void setBranch(String bName) 
	{
		if(this.getBranch()!=null&&!this.getBranch().equalsIgnoreCase("null"))
		{
			if(this.getBranch().equalsIgnoreCase(bName))
			{
				this.getPlayer().sendMessage(ClPk.prefix+ChatColor.RED+"You are already a member of this pack...");
				return;
			}
			this.getPlayer().sendMessage(ClPk.prefix+ChatColor.RED+"You are already a member of the pack "+getBranch()+"! Leave the pack before joining another.");
			return;
		}
		else
		{
			String pPref = ClPk.guilds_packs.getConfig().getString("Twol."+bName+".prefix");
			List<String> memList = ClPk.guilds_packs.getConfig().getStringList("Twol."+bName+".members");
			List<String> invList = ClPk.guilds_packs.getConfig().getStringList("Twol."+bName+".invitation");
			invList.remove(this.getPlayer().getUniqueId().toString());
			memList.add(this.getPlayer().getUniqueId().toString());
			this.Branch = bName;
			ClPk.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".pack", this.Branch);
			ClPk.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".packPref", pPref);
			ClPk.guilds_packs.getConfig().set("Twol."+bName+".members", memList);
			ClPk.guilds_packs.getConfig().set("Twol."+bName+".invitation", invList);
			ClPk.guilds_packs.saveConfig();
			ClPk.guilds_packs.reloadConfig();
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
			this.prefix = ClPk.guilds_packs.getConfig().getString("Twol."+this.Branch+".prefix").replaceAll("&", "§");
			this.getPlayer().sendMessage(ClPk.prefix+ChatColor.RED+"You are now a member of the pack "+bName+".");
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
	public PotionEffect envDecode(String val)
	{
		switch(val.toLowerCase())
		{
		case "regen":
			return new PotionEffect(PotionEffectType.REGENERATION,60,1);
		case "speed":
			return new PotionEffect(PotionEffectType.SPEED,60,1);
		case "camoflage":
			return new PotionEffect(PotionEffectType.INVISIBILITY,60,0);
		case "noregen":
			return new PotionEffect(PotionEffectType.HUNGER,60,0);
		case "slow":
			return new PotionEffect(PotionEffectType.SLOW,60,0);
		case "slowmine":
			return new PotionEffect(PotionEffectType.SLOW_DIGGING,60,1);
		default:
			return null;
		}
	}
	public void nearPackMates()
	{
		taskNear = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{
				if(TimberWolf.this.getPlayer()!=null){
					Player player = TimberWolf.this.getPlayer();
					for(Entity e : player.getNearbyEntities(20, 20, 20))
					{
						if(e instanceof Player)
						{
							Player p = (Player)e;

							if(ClPk.onlinePlayers.get(p.getName())instanceof TimberWolf)
							{
								TimberWolf.this.packBuffs(player);
							}
						}
					}
				}
			}
		},100);
	}
	public void packBuffs(Player p)
	{
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
		p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));
		p.playSound(p.getLocation(), Sound.ENTITY_WOLF_HOWL, RandInt.randFloat(0, 5), RandInt.randFloat(0, 5));
	}

	public void environmentVars()
	{
		if ((getPlayer() != null)&&(getPlayer().isOnline())&&this.getClassNm().equalsIgnoreCase("TimberWolf")) {
			taskEnvVar = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
			{
				@Override
				public void run()
				{
					if(TimberWolf.this.getPlayer()!=null){
						if(TimberWolf.this.getPlayer()!=null&&TimberWolf.this.biomeMap.containsKey(TimberWolf.this.getPlayer().getLocation().getBlock().getBiome()))
						{
							if(TimberWolf.this.getPlayer()!=null&&envDecode(biomeMap.get(TimberWolf.this.getPlayer().getLocation().getBlock().getBiome()))!=null)
							{
								TimberWolf.this.getPlayer().addPotionEffect(envDecode(biomeMap.get(TimberWolf.this.getPlayer().getLocation().getBlock().getBiome())));
							}
						}
						environmentVars();
					}
				}
			},10);
		}
	}


	public void saveAll()
	{
		taskSave = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{

				if(TimberWolf.this.getPlayer()!=null)
				{
					Player p = TimberWolf.this.getPlayer();
					p.sendMessage(ClPk.prefix+"autosaving classData.");
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".pack", TimberWolf.this.getBranch());
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".mana", TimberWolf.this.getEnergy());
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".level", TimberWolf.this.getLevel());
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
		Bukkit.getScheduler().cancelTask(taskEnvVar);
		Bukkit.getScheduler().cancelTask(taskNear);
		Bukkit.getScheduler().cancelTask(taskReEn);
		Bukkit.getScheduler().cancelTask(taskSave);
	}

}