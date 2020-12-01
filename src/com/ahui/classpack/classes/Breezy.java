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

public class Breezy 
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

	public Breezy(String name) {
		super(name);
		this.classID = 11;
		this.prefix = ClPk.advCfg.getConfig().getString("Prefixes.Breezy.default").replaceAll("&", "§");
		this.fxp = ClPk.playerClass.getConfig().getInt(Breezy.this.getPlayer().getUniqueId()+".fxp");
		this.fxpTarget = ClPk.advCfg.getConfig().getInt("fxpTarget");
		this.energy = ClPk.playerClass.getConfig().getDouble(Breezy.this.getPlayer().getUniqueId()+".energy");
		this.energyCap = ClPk.playerClass.getConfig().getDouble(Breezy.this.getPlayer().getUniqueId()+".maxEnergy");
		this.level = ClPk.playerClass.getConfig().getInt(Breezy.this.getPlayer().getUniqueId()+".level");
		this.saveTime = ClPk.advCfg.getConfig().getInt("classDataAutoSave");
		this.cool = ClPk.playerClass.getConfig().getInt(this.getPlayer().getUniqueId().toString()+".ftime");
		this.maxHealth = ClPk.advCfg.getConfig().getInt("Classes.Breezy.maxHealth"); 
		this.getPlayer().setHealthScale(maxHealth);
		this.getPlayer().setFlySpeed((float) 0.1);
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
		if(Breezy.this.getPlayer()!=null&&this.getClassNm().equalsIgnoreCase("Breezy")){
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard peg = manager.getNewScoreboard();
			Objective pegs = peg.registerNewObjective("Breezy", Breezy.this.getPlayer().getUniqueId().toString());

			pegs.setDisplaySlot(DisplaySlot.SIDEBAR);
			//if(Breezy.this.getPlayer().getDisplayName().length() <31){
			//	pegs.setDisplayName(Breezy.this.getPlayer().getDisplayName());
			//}else{
			//	pegs.setDisplayName(Breezy.this.getPlayer().getName());
			//}
			pegs.setDisplayName("Stats");
			Score sfxp = pegs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FXP: "+ChatColor.AQUA +getFxp()));
			sfxp.setScore(-4);
			Double regen = ((this.energyCap*0.025)*2);
			DecimalFormat numberFormat = new DecimalFormat("#.00");
			Score score2 = pegs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ENP: "+ChatColor.AQUA + numberFormat.format(this.getEnergy()).toString()));
			score2.setScore(1);
			Score flt = pegs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FLT: "+ChatColor.AQUA+ChatColor.STRIKETHROUGH +numberFormat.format(this.cool)));
			if(this.cool>0)
			{
				flt = pegs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FLT: "+ChatColor.AQUA +numberFormat.format(this.cool/60)));	
			}
			flt.setScore(0);
			Score level = pegs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ERG: "+ChatColor.AQUA +"+" + numberFormat.format(regen)+"/s"));
			level.setScore(-1);
			Breezy.this.getPlayer().setScoreboard(peg);
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
				if(Breezy.this.getPlayer()!=null)
				{
					if(Breezy.this.cool>0.0)
					{
						Breezy.this.setCool(Breezy.this.getCool()-1);
					}
					ClPk.playerClass.getConfig().set(Breezy.this.getPlayer().getUniqueId()+".ftime", Breezy.this.getCool());
					ClPk.playerClass.saveConfig();
					ClPk.playerClass.reloadConfig();
					Breezy.this.coolDown();
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
				if(Breezy.this.getPlayer()!=null){
					Player p = Breezy.this.getPlayer();
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
					Breezy.this.isGlide();
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
		if ((getPlayer() != null)&&(getPlayer().isOnline())&&this.getClassNm().equalsIgnoreCase("Breezy")) {
			taskReEn = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
			{
				@Override
				public void run()
				{
					if(Breezy.this.getPlayer()!=null)
					{
						if(Breezy.this.getPlayer()!=null&&Breezy.this.getPlayer().getGameMode()!=GameMode.SURVIVAL)
						{
							Breezy.this.setEnergy(Breezy.this.getEnergy()+20);
						}
						if(Breezy.this.getPlayer()!=null&&Breezy.this.cool>=0.0||Breezy.this.getEnergy()>=0.0)
						{
							Breezy.this.getPlayer().setAllowFlight(true);
						}
						if(Breezy.this.getPlayer()!=null&&Breezy.this.getEnergy()<=0.0)
						{
							Breezy.this.getPlayer().setAllowFlight(false);
						}
						if(Breezy.this.getPlayer()!=null&&Breezy.this.getPlayer().isFlying())
						{
							Breezy.this.setEnergy(Breezy.this.getEnergy()-Glide.lCost);
						}
						else if(Breezy.this.getPlayer()!=null&&Breezy.this.getPlayer().isOnGround())
						{
							Breezy.this.setEnergy(Breezy.this.getEnergy()+(energyCap*0.025));
						}
						if(Breezy.this.getPlayer()!=null&&!Breezy.this.getPlayer().isOnGround()&&Breezy.this.getEnergy()<20)
						{
							Breezy.this.setEnergy(Breezy.this.getEnergy()+(Glide.lCost/10));
						}
						Breezy.this.refreshEnergy(classID);
						Breezy.this.showHud();
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
				if(Breezy.this.getPlayer()!=null){
					Player p = Breezy.this.getPlayer();
					p.sendMessage(ClPk.prefix+"autosaving classData.");
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".energy", Breezy.this.getEnergy());
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".level", Breezy.this.getLevel());
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".fxp", Breezy.this.getFxp());
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
