package com.ahui.classpack.classes;

import java.text.DecimalFormat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
//import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
//import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
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


public class DiamondDog 
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
	ItemStack pick = new ItemStack(Material.DIAMOND_PICKAXE,1);
//	public static ConfigMk diamondDogPickBoost;
//	public static String[] Lore = {"Fortune Boosted Pickaxe"};
//	public static List<String> LoreList = Arrays.asList(Lore);
	public DiamondDog(String name) 
	{
		super(name);
		this.classID = 12;

		this.Branch = ClPk.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".branch");
		this.energyCap = ClPk.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".maxEnergy");
		this.energy = ClPk.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".energy");
		this.level = ClPk.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".level");
		this.prefix = ClPk.advCfg.getConfig().getString("Prefixes.DiamondDog."+Branch).replaceAll("&", "§");
		this.saveTime = ClPk.advCfg.getConfig().getInt("classDataAutoSave");
		this.maxHealth = ClPk.advCfg.getConfig().getInt("Classes.DiamondDog.maxHealth"); 
		this.getPlayer().setHealthScale(maxHealth);
		this.getPlayer().getInventory().getContents().toString();
		this.getPlayer().setFlySpeed((float) 0.1);
		refreshEnergy(3);
		showHud();
		saveAll();
	}

	public String getClassNm()
	{
		return ClPk.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".class");
	}

//	public void itemPickup(PlayerPickupItemEvent e){
//		Player p = e.getPlayer();
//		Item i = e.getItem();
//		if ((getPlayer() != null)&&(getPlayer().isOnline())&&this.getClassNm().equalsIgnoreCase("DiamondDog")){
//			if(e.getItem().getItemStack().getType().equals(ClPk.advCfg.getConfig().getList("Classes.DiamondDog.lootValues.items").contains(e.getItem().getItemStack().getType()))){
//				if ((ClPk.advCfg.getConfig().get("Classes.DiamondDog.lootValues.multipliers."+e.getItem().getItemStack().getData().toString()))!=null){
//					e.getItem().getItemStack().setAmount((e.getItem().getItemStack().getAmount())*(ClPk.advCfg.getConfig().getInt("Classes.DiamondDog.lootValues.multipliers."+e.getItem().getItemStack().getData().toString())));
//				}else{
//					e.getItem().getItemStack().setAmount((e.getItem().getItemStack().getAmount())*(ClPk.advCfg.getConfig().getInt("Classes.DiamondDog.lootValues.defaultMultiplier")));
//				}
//				
//			}
//		}
//		
//	}
	@Override
	@SuppressWarnings("deprecation")
	public void showHud()
	{
		if(DiamondDog.this.getPlayer()!=null&&this.getClassNm().equalsIgnoreCase("DiamondDog")){
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard dia = manager.getNewScoreboard();
			Objective diam = dia.registerNewObjective("DiamondDog", this.getPlayer().getUniqueId().toString());

			diam.setDisplaySlot(DisplaySlot.SIDEBAR);
			String title = "";
			if(!this.getBranch().equalsIgnoreCase("default"))
			{
				title = this.prefix.substring(0,this.prefix.indexOf("E")).replace("[", "").replace("]", "");
			}
			//if(this.getPlayer().getDisplayName().length() <31){
			//	diam.setDisplayName(title+this.getPlayer().getDisplayName()+title);
			//}else{
			//diam.setDisplayName(title+this.getPlayer().getName()+title);
			//}
			diam.setDisplayName(title+"Stats"+title);
			Double regen = (((this.energyCap/3)*0.025)*2);
			DecimalFormat numberFormat = new DecimalFormat("#.00");
			Score score2 = diam.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ENP: "+ChatColor.GREEN + numberFormat.format(this.getEnergy()).toString()));
			score2.setScore(1);
			Score level = diam.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ERG: "+ChatColor.GREEN +"+" + numberFormat.format(regen)+"/s"));
			level.setScore(-1);
			this.getPlayer().setScoreboard(dia);
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
		if ((getPlayer() != null)&&(getPlayer().isOnline())&&this.getClassNm().equalsIgnoreCase("DiamondDog")) {
			taskReEn = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
			{
				@Override
				public void run()
				{
					if(DiamondDog.this.getPlayer()!=null){
//						Player p = DiamondDog.this.getPlayer();
//						
//						ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE,1);
//						ItemMeta pickMeta = pickaxe.getItemMeta();
//						ArrayList<String> picklore = new ArrayList<String>();
//						picklore.add("Fortune Boosted Pickaxe");
//						pickMeta.setLore(picklore);
//						pickaxe.setItemMeta(pickMeta);
//						if (p.getInventory().contains(Material.DIAMOND_PICKAXE)){
//							if (p.getInventory().getItemInMainHand().getType() == Material.DIAMOND_PICKAXE){
//								ItemStack hand = p.getInventory().getItemInMainHand();
//								ItemMeta meta = hand.getItemMeta();
//								ArrayList<String> lore = new ArrayList<String>();
//								if(p.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore()==null){	
//									lore.add("Fortune Boosted Pickaxe");
//									meta.setLore(lore);
//									hand.setItemMeta(meta);
//									hand.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 5);
//									
//								} 
//								
//							}
//							
//						}
								
						
						
						if(DiamondDog.this.getPlayer()!=null&&DiamondDog.this.getPlayer().getGameMode()!=GameMode.SURVIVAL)
						{
							DiamondDog.this.setEnergy(DiamondDog.this.getEnergy()+20);
						}
						DiamondDog.this.setEnergy(DiamondDog.this.getEnergy()+((energyCap/3)*0.025));
						if(DiamondDog.this.getPlayer()!=null&&DiamondDog.this.getBranch().equalsIgnoreCase("miner"))
						{
							if(DiamondDog.this.getPlayer()!=null&&DiamondDog.this.getPlayer().getLocation().getY()<50)
							{
								DiamondDog.this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 0));
								DiamondDog.this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0));
								if(DiamondDog.this.getPlayer()!=null&&DiamondDog.this.getPlayer().getLocation().getY()<25)
								{
									DiamondDog.this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
									DiamondDog.this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 0));
									DiamondDog.this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 0));
								}
								else
								{
									DiamondDog.this.getPlayer().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
									DiamondDog.this.getPlayer().removePotionEffect(PotionEffectType.REGENERATION);
									DiamondDog.this.getPlayer().removePotionEffect(PotionEffectType.SATURATION);

								}
							}else{
								DiamondDog.this.getPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
								DiamondDog.this.getPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION);
							}
						}
						DiamondDog.this.showHud();
						DiamondDog.this.refreshEnergy(classID);
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
			this.prefix = ClPk.advCfg.getConfig().getString("Prefixes.DiamondDog."+Branch).replaceAll("&", "§");
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
				if(DiamondDog.this.getPlayer()!=null)
				{
					Player p = DiamondDog.this.getPlayer();
					p.sendMessage(ClPk.prefix+"autosaving classData.");
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".branch", DiamondDog.this.getBranch());
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".mana", DiamondDog.this.getEnergy());
					ClPk.playerClass.getConfig().set(p.getUniqueId()+".level", DiamondDog.this.getLevel());
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
