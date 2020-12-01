package com.ahui.classpack.classes;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ahui.classpack.classes.interfaces.EnergyUser;
import com.ahui.classpack.classes.interfaces.Flier;
import com.ahui.classpack.classes.interfaces.MagicUser;
import com.ahui.classpack.interaction.Glide;
import com.ahui.classpack.ClPk;

import com.ahui.classpack.util.RandInt;

public class Draconequus extends ClassBase
implements EnergyUser, MagicUser, Flier
{
	private List<String> SpecSpells;
	//private String[] debN =
	//	{"default","corrupter","saviour"};
	//private List<String> deNames = new ArrayList<String>(Arrays.asList(debN));
	private Map<String, Boolean> coolIDs = new HashMap<String, Boolean>();
	private Map<String, Integer> coolTime = new HashMap<String, Integer>();
	//private String branch;
	//private String cubCol;
	private double mana;
	private double manaCap;
	private double energy;
	private double energyCap;
	private int selSpell;
	private int mLevel;
	private int fLevel;
	private int saveTime;
	private double cool;
	private int fxp;
	private int fxpTarget;
	private double maxHealth;
	public int taskReEn;
	public int taskReMa;
	public int taskGlide;
	public int taskCool;
	public int taskCool2;
	public int taskSave;


	public Draconequus(String name) {
		super(name);
		this.classID = 9;
		this.coolIDs.clear();
		this.coolIDs.put("potionrain", false);       this.coolTime.put("potionrain", 0);
		this.coolIDs.put("tntrain", false);       this.coolTime.put("tntrain", 0);
		this.coolIDs.put("throwcake", false);    this.coolTime.put("throwcake", 0);
		this.coolIDs.put("creeperrain", false);   this.coolTime.put("creeperrain", 0);
		this.coolIDs.put("throwfish", false);   this.coolTime.put("throwfish", 0);
		this.coolIDs.put("blink", false);       this.coolTime.put("blink", 0);
		this.coolIDs.put("fireball", false);    this.coolTime.put("fireball", 0);
		this.coolIDs.put("fireblast", false);   this.coolTime.put("fireblast", 0);
		this.coolIDs.put("icespike", false);    this.coolTime.put("icespike", 0);
		this.coolIDs.put("iceblast", false);    this.coolTime.put("iceblast", 0);
		this.coolIDs.put("shadowbolt", false);  this.coolTime.put("shadowbolt", 0);
		this.coolIDs.put("shadowblast", false); this.coolTime.put("shadowblast", 0);
		this.coolIDs.put("save", false);        this.coolTime.put("save", 0);
		this.coolIDs.put("recallI", false);      this.coolTime.put("recallI", 0);
		this.coolIDs.put("recallII", false);      this.coolTime.put("recallII", 0);
		
		this.prefix = ClPk.advCfg.getConfig().getString("Prefixes.Draconequus.default").replaceAll("&", "§");
		this.manaCap = ClPk.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".maxMana");
		this.mana = ClPk.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".mana");
		this.energyCap = ClPk.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".maxEnergy");
		this.energy = ClPk.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".energy");
		this.mLevel = ClPk.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".mlevel");
		this.fLevel = ClPk.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".flevel");
		this.selSpell = ClPk.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".currentSpell");
		this.SpecSpells = ClPk.playerClass.getConfig().getStringList(this.getPlayer().getUniqueId()+".spells");
		this.saveTime = ClPk.advCfg.getConfig().getInt("classDataAutoSave");
		this.fxp = ClPk.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".fxp");
		this.fxpTarget = ClPk.advCfg.getConfig().getInt("fxpTarget");
		this.cool = ClPk.playerClass.getConfig().getInt(this.getPlayer().getUniqueId().toString()+".ftime");
		this.maxHealth = ClPk.advCfg.getConfig().getInt("Classes.Draconequus.maxHealth"); 
		this.getPlayer().setFlySpeed((float) 0.1);
		this.getPlayer().setHealthScale(maxHealth);
		this.refreshMana(9);
		this.refreshEnergy(9);
		this.showHud();
		this.saveAll();
		this.coolDown();


	}

	public String getClassNm()
	{
		return ClPk.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".class");
	}
	@Override
	@SuppressWarnings("deprecation")
	public void showHud()
	{
		if(Draconequus.this.getPlayer()!=null&&this.getClassNm().equalsIgnoreCase("Draconequus")){
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard drc = manager.getNewScoreboard();
			Objective drcs = drc.registerNewObjective("Draconequus", this.getPlayer().getUniqueId().toString());
			drcs.setDisplaySlot(DisplaySlot.SIDEBAR);
			String title = "";

			title = this.prefix.substring(0,this.prefix.indexOf("D")).replace("[", "").replace("]", "");

			//if(this.getPlayer().getDisplayName().length() <31)
			//{
			//	drcs.setDisplayName(title+this.getPlayer().getDisplayName()+title);
			//}else{
			//	drcs.setDisplayName(title+this.getPlayer().getName()+title);
			//}
				drcs.setDisplayName(title+"Stats"+title);
			Score sfxp = drcs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FXP: "+ChatColor.AQUA +getFxp()));
			sfxp.setScore(-4);
			Double mRegen = (((this.manaCap/1.5)*0.025)*2);
			Double fRegen = (((this.energyCap/1.5)*0.025)*2);
			DecimalFormat numberFormat = new DecimalFormat("#.00");
			Score score2 = drcs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "MNP: "+ChatColor.DARK_PURPLE + numberFormat.format(this.getMana()).toString()));
			score2.setScore(3);
			Score score3 = drcs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ENP: "+ChatColor.DARK_PURPLE + numberFormat.format(this.getEnergy()).toString()));
			score3.setScore(2);
			if(Draconequus.this.coolIDs.containsKey(this.currentSpell().toLowerCase())&&this.getCool(this.currentSpell().toLowerCase())==true)
			{
				Score spell = drcs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD+ "SPL: "+ChatColor.RED + this.currentSpell()));
				spell.setScore(1);
			}else
			{
				Score spell = drcs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD+ "SPL: "+ChatColor.AQUA + this.currentSpell()));
				spell.setScore(1);
			}
			Score flt = drcs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FLT: "+ChatColor.AQUA+ChatColor.STRIKETHROUGH +numberFormat.format(this.cool)));
			if(this.cool>0)
			{
				flt = drcs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FLT: "+ChatColor.AQUA +numberFormat.format(this.cool/60)));	
			}
			flt.setScore(0);
			Score manRegen = drcs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "MRG: "+ChatColor.DARK_PURPLE +"+" + numberFormat.format(mRegen)+"/s"));
			manRegen.setScore(-1);
			Score fltRegen = drcs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ERG: "+ChatColor.DARK_PURPLE +"+" + numberFormat.format(fRegen)+"/s"));
			fltRegen.setScore(-2);
			this.getPlayer().setScoreboard(drc);
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
	public void setFxp(int i) 
	{
		if(this.fxp<this.fxpTarget)
		{	
			this.fxp=this.fxp+i;
		}
		else
		{
			this.setFlierLevel();
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
				if(Draconequus.this.getPlayer()!=null)
				{
					if(Draconequus.this.getPlayer()!=null&&Draconequus.this.cool>0.0)
					{
						Draconequus.this.setCool(Draconequus.this.getCool()-1);
					}
					ClPk.playerClass.getConfig().set(Draconequus.this.getPlayer().getUniqueId()+".ftime", Draconequus.this.getCool());
					ClPk.playerClass.saveConfig();
					ClPk.playerClass.reloadConfig();
					Draconequus.this.coolDown();
				}
			}
		},20l);
	}

	@Override
	public Double getMana() 
	{
		return this.mana;
	}

	@Override
	public void setMana(Double newMana) 
	{
		this.mana = newMana;
		if(this.mana > manaCap)
		{
			this.mana = manaCap;
		}
	}

	@Override
	public void refreshMana(final int classID) 
	{
		if ((getPlayer() != null)&&(getPlayer().isOnline())&&this.getClassNm().equalsIgnoreCase("Draconequus")) {
			taskReMa = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
			{
				@Override
				public void run()
				{
					if(Draconequus.this.getPlayer()!=null){
						if(Draconequus.this.getPlayer()!=null&&Draconequus.this.getPlayer().getGameMode()!=GameMode.SURVIVAL)
						{
							Draconequus.this.setMana(Draconequus.this.getMana()+20);
						}
						Draconequus.this.setMana(Draconequus.this.getMana()+((manaCap/1.5)*0.025));
						Draconequus.this.showHud();
						Draconequus.this.refreshMana(classID);
					}
				}
			},10L);
		}
	}

	@Override
	public String currentSpell() 
	{
		return SpecSpells.get(this.selSpell);
	}

	@Override
	public void cycleSpell() 
	{
		if(!getPlayer().isSneaking())
		{
			this.selSpell+=1;
			if (this.selSpell > this.SpecSpells.size()-1)
			{
				this.selSpell = 0;
			}
		}else
		{
			this.selSpell-=1;
			if (this.selSpell < 0)
			{
				this.selSpell = this.SpecSpells.size()-1;
			}
		}

		this.showHud();
	}

	@Override
	public boolean hasSpell(String spell) 
	{
		for(String sp : SpecSpells)
		{
			if(sp.equalsIgnoreCase(spell))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public void getSpells() 
	{
		getPlayer().sendMessage(ClPk.prefix+this.SpecSpells);
	}

	public void setMagicLevel() 
	{
		if(this.getMagicLevel() <10)
		{
			this.mLevel = this.getMagicLevel()+1;
			this.showHud();
			ClPk.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".mlevel", this.getMagicLevel());
			ClPk.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".maxMana", this.manaCap+5);
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
			this.manaCap = ClPk.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId().toString()+".maxMana");
		}else
		{
			this.getPlayer().sendMessage(ClPk.prefix+"You cannot learn anything more from these tomes, you destroyed the material.");
		}
	}

	@Override
	public void saveAll()
	{
		taskSave = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{
				if(Draconequus.this.getPlayer()!=null){
					Player p = Draconequus.this.getPlayer();
					p.sendMessage(ClPk.prefix+"autosaving classData.");
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".branch", Draconequus.this.getBranch());
					if(Draconequus.this.getPlayer()!=null&&Draconequus.this.getBranch().equalsIgnoreCase("custom"))
					{
						ClPk.playerClass.getConfig().set(p.getUniqueId()+".branch","custom");
					}

					ClPk.playerClass.getConfig().set(p.getUniqueId()+".mana", Draconequus.this.getMana());
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".flevel", Draconequus.this.getFlierLevel());
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".mlevel", Draconequus.this.getMagicLevel());
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".currentSpell", Draconequus.this.selSpell);
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".spells", Draconequus.this.SpecSpells);
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".energy", Draconequus.this.getEnergy());
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".fxp", Draconequus.this.getFxp());
					ClPk.playerClass.saveConfig();
					ClPk.playerClass.reloadConfig();
					saveAll();
				}
			}
		},saveTime);
	}

	@Override
	public void addSpell(String sName) 
	{
		if(!this.hasSpell(sName))
		{
			
			for(String sp : this.SpecSpells)
			{
				if(sName.length()>sp.length()&&sName.startsWith(sp)&&sName.length()<sp.length()+3)
				{

					SpecSpells.set(SpecSpells.indexOf(sp), sName);
					ClPk.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".spells", SpecSpells);
					ClPk.playerClass.saveConfig();
					ClPk.playerClass.reloadConfig();
					return;
				}
				if(sName.length()<sp.length()&&sp.startsWith(sName))
				{
					return;
				}
			}
			this.SpecSpells.add(sName);
			ClPk.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".spells", SpecSpells);
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
		}
		{
			
			for(String sp : this.SpecSpells)
			{
				if(sName.length()>sp.length()&&sName.startsWith(sp)&&sName.length()<sp.length()+3)
				{

					SpecSpells.set(SpecSpells.indexOf(sp), sName);
					ClPk.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".spells", SpecSpells);
					ClPk.playerClass.saveConfig();
					ClPk.playerClass.reloadConfig();
					return;
				}
				if(sName.length()<sp.length()&&sp.startsWith(sName))
				{
					return;
				}
			}
			this.SpecSpells.add(sName);
			ClPk.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".spells", SpecSpells);
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
		}

	}

	@Override
	public void remSpell(String sName) 
	{
		if(this.hasSpell(sName))
		{
			
			this.SpecSpells.remove(sName);
			this.selSpell = 0;
			ClPk.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".spells", SpecSpells);
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
		}

	}

	@Override
	public void setCool(final String spellID, int coolTime) 
	{
		if(this.coolIDs.containsKey((spellID.toLowerCase())))
		{
			if(this.coolIDs.get(spellID.toLowerCase())==false)
			{
				this.coolIDs.put(spellID.toLowerCase(), true);
				this.coolTime.put(spellID.toLowerCase(), coolTime);

			}
			taskCool2 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
			{
				@Override
				public void run()
				{
					if(Draconequus.this.getPlayer()!=null)
					{
						if(Draconequus.this.getPlayer()!=null&&Draconequus.this.coolTime.get(spellID.toLowerCase())==0)
						{
							Draconequus.this.coolIDs.put(spellID.toLowerCase(), false);
							return;
						}
						Draconequus.this.coolTime.put(spellID.toLowerCase(), Draconequus.this.coolTime.get(spellID.toLowerCase())-1);
						setCool(spellID, Draconequus.this.coolTime.get(spellID.toLowerCase()));
					}
				}
			},20L);
		}

	}

	@Override
	public boolean getCool(String spellID) 
	{
		return coolIDs.get(spellID.toLowerCase());
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
		if ((getPlayer() != null)&&(getPlayer().isOnline())&&this.getClassNm().equalsIgnoreCase("Draconequus")) {
			taskReEn = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
			{
				@Override
				public void run()
				{
					if(Draconequus.this.getPlayer()!=null){
						
						if(Draconequus.this.getPlayer()!=null&&Draconequus.this.getPlayer().getGameMode()!=GameMode.SURVIVAL)
						{
							Draconequus.this.setEnergy(Draconequus.this.getEnergy()+20);
						}
						if(Draconequus.this.getPlayer()!=null&&Draconequus.this.cool>=0.0||Draconequus.this.getEnergy()>=0.0)
						{
							Draconequus.this.getPlayer().setAllowFlight(true);
						}
						if(Draconequus.this.getPlayer()!=null&&Draconequus.this.getEnergy()<=0.0)
						{
							Draconequus.this.getPlayer().setAllowFlight(false);
						}
						if(Draconequus.this.getPlayer()!=null&&Draconequus.this.getPlayer().isFlying())
						{
							Draconequus.this.setEnergy(Draconequus.this.getEnergy()-(Glide.lCost));
						}
						if(Draconequus.this.getPlayer()!=null&&Draconequus.this.getPlayer().isOnGround())
						{
							Draconequus.this.setEnergy(Draconequus.this.getEnergy()+((energyCap/1.5)*0.025));
						}
						if(Draconequus.this.getPlayer()!=null&&!Draconequus.this.getPlayer().isOnGround()&&Draconequus.this.getEnergy()<20)
						{
							Draconequus.this.setEnergy(Draconequus.this.getEnergy()+(Glide.lCost/10));
						}
						Draconequus.this.showHud();
						Draconequus.this.refreshEnergy(classID);
					}
				}
			},10L);
		}
	}

	public int getFlierLevel()
	{
		return this.fLevel;
	}
	public int getMagicLevel()
	{
		return this.mLevel;
	}
	public void setFlierLevel()
	{
		if(this.getFlierLevel() <10)
		{
			this.fLevel = this.getFlierLevel()+1;
			this.getPlayer().playSound(this.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, RandInt.randInt(0, 5));
			this.fLevel = this.getFlierLevel()+1;
			this.showHud();
			ClPk.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".flevel", this.getFlierLevel());
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
	public void isGlide()
	{
		taskGlide = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{
				if(Draconequus.this.getPlayer()!=null){
					Player p = Draconequus.this.getPlayer();
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
					Draconequus.this.isGlide();
				}
			}
		},10);
	}
	@Override
	public void stop() 
	{
		Bukkit.getScheduler().cancelTask(taskCool);
		Bukkit.getScheduler().cancelTask(taskCool2);
		Bukkit.getScheduler().cancelTask(taskGlide);
		Bukkit.getScheduler().cancelTask(taskReEn);
		Bukkit.getScheduler().cancelTask(taskReMa);
		Bukkit.getScheduler().cancelTask(taskSave);
	}

	//unused
	@Override
	public void setLevel(int i) {}
	@Override
	public int getLevel() {return 0;}
	@Override
	public String getBranch() {return null;}
	@Override
	public void setBranch(String args) {}
	@Override
	public void setLevel() {}


}
