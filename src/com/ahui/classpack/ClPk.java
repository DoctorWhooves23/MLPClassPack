package com.ahui.classpack;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
//import org.bukkit.scoreboard.DisplaySlot;

import com.ahui.gender.DataSave.DataSaveMain;
import com.ahui.gender.DataSave.PlayerJoinListener;
import com.ahui.classpack.ClPk;
import com.ahui.classpack.interaction.ClassAssign;
import com.ahui.classpack.interaction.DisguiseUtil;
import com.ahui.classpack.interaction.DragonFlameUse;
import com.ahui.classpack.interaction.EarthBranchBonus;
import com.ahui.classpack.interaction.Glide;
import com.ahui.classpack.interaction.PotionEffectModiy;
import com.ahui.classpack.interaction.ProjectileSpellDamage;
import com.ahui.classpack.interaction.Research;
import com.ahui.classpack.interaction.SpellUtil;
import com.ahui.classpack.interaction.TntRainCancel;
import com.ahui.classpack.modules.pvpControl.PvpControlMain;
import com.ahui.classpack.util.TabAutoCompletion;
import com.ahui.classpack.util.Upgrade;
import com.ahui.classpack.ClPk;
import com.ahui.classpack.classes.interfaces.Flier;
import com.ahui.classpack.classes.interfaces.MagicUser;
import com.ahui.classpack.ClPk;
//import com.ahui.classpack.classes.Ahuizotl;
//import com.ahui.classpack.classes.Alicorn;
//import com.ahui.classpack.classes.Breezy;
//import com.ahui.classpack.classes.Minotaur;
import com.ahui.classpack.classes.Changeling;
//import com.ahui.classpack.classes.Chimera;
//import com.ahui.classpack.classes.DiamondDog;
//import com.ahui.classpack.classes.Draconequus;
//import com.ahui.classpack.classes.Dragon;
//import com.ahui.classpack.classes.Earth;
//import com.ahui.classpack.classes.Griffon;
//import com.ahui.classpack.classes.Pegasus;
//import com.ahui.classpack.classes.Thestral;
//import com.ahui.classpack.classes.TimberWolf;
//import com.ahui.classpack.classes.Unicorn;
//import com.ahui.classpack.classes.Zebra;
import com.ahui.classpack.classes.interfaces.ClassBase;
import com.ahui.classpack.util.ClassBooks;
import com.ahui.classpack.util.ClassSeter;
import com.ahui.classpack.util.ConfigMk;



public class ClPk extends JavaPlugin implements Listener{
	private static ClPk instance;
	public static ConfigMk config;
	public static ConfigMk advCfg;
	public static ConfigMk playerClass;
	public static ConfigMk BranchSpells;
	public static ConfigMk spawns;
	public static ConfigMk guilds_packs;
	public static ConfigMk EnvData;
	public Permission female = new Permission("gpp.female");
	public Permission male = new Permission("gpp.male");
	public Permission nogender = new Permission("gpp.nogender");
	public static DataSaveMain Male;
	public static DataSaveMain Female;
	public static DataSaveMain NoGender;
	public static HashMap<String, ClassBase> onlinePlayers = new HashMap<String, ClassBase>();
	public static HashMap<String, Location> pSpawns = new HashMap<String, Location>();
	public static String[] colours = { "&a", "&b", "&c", "&d", "&e", "&f", "&1", "&2", "&3", "&4", "&5", "&6", "&7", "&8", "&9", "&0" };
	public static ArrayList<String> colour = new ArrayList<String>();
	public static String[] classNames = {"Unicorn","Pegasus","Earth","Alicorn","Changeling","Dragon","Griffon","Draconequus","TimberWolf","Zebra","Breezy","DiamondDog","Minotaur","Ahuizotl","Thestral","Chimera"};
	public static String[] bCommands = {"Class", "Gender", "WolfPack"};
	public static List<String> baseCommands = Arrays.asList(bCommands);
	public static String[] packCmds = {"Create", "Edit", "Info", "Invite", "Join", "Leave", "Remove"};
	public static List<String> wolfPackCmds = Arrays.asList(packCmds);
	public static String[] gndrCmds = {"Set", "List"};
	public static List<String> genderCmds = Arrays.asList(gndrCmds);
	public static String[] gndrs = {"Male", "Female", "None"};
	public static List<String> genders = Arrays.asList(gndrs);
	public static String[] gndrLstCmds = {"Male", "Female", "NoGender", "All"};
	public static List<String> genderListCmds = Arrays.asList(gndrLstCmds);
	public static String[] classCmds = {"Set", "Get", "Spawn"};
	public static List<String> classCommands = Arrays.asList(classCmds);
	public static String[] classSetCmds = {"Class", "Spawn", "Tag", "Sign"};
	public static List<String> classSetCommands = Arrays.asList(classSetCmds);	
	public static String[] classGetCmds = {"Classes", "Info", "List", "Spawn", "Tag", "Sign", "Stats"};
	public static List<String> classGetCommands = Arrays.asList(classGetCmds);
	public static String[] classSignCmds = {"Text", "Line"};
	public static List<String> classSignCommands = Arrays.asList(classSignCmds);
	public static String[] emptyCmds = {};
	public static List<String> emptyCommands = Arrays.asList(emptyCmds);
	public static String[] SpellStrings = { "blink", "recall", "recallII", "flameI", "flameII", "flameIII", "fireBlast",
			"fireBall", "fireAura", "healI", "healII", "healIII", "healIV", "healTargetI", "healTargetII", "healAura",
			"courage", "curePoisonI", "curePoisonII", "cureAll", "frostI", "frostII", "frostIII", "iceSpike",
			"iceBlast", "frostAura", "shadowBeamI", "shadowBeamII", "shadowBeamIII", "shadowBlast", "shadowBolt",
			"shadowAura", "lifeDrainI", "lifeDrainII", "feedI", "feedII", "throwCake", "tntRain", "creeperRain",
			"throwFish", "potionRain" };
	public static List<String> SpellList = Arrays.asList(SpellStrings);
	public static String prefix = ChatColor.LIGHT_PURPLE + "[MCP]" + ChatColor.RESET;
	public static boolean DisguiseLibsEnabled = false;
	private PluginLogger logger = null;
	Plugin protoLib = Bukkit.getServer().getPluginManager().getPlugin("ProtocolLib");
	Plugin disguiseLib = Bukkit.getServer().getPluginManager().getPlugin("LibsDisguises");
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		PluginManager pm = getServer().getPluginManager();
		pm.addPermission(female);
		pm.addPermission(male);
		pm.addPermission(nogender);
		
		String pluginFolder =this.getDataFolder().getAbsolutePath();
		
		(new File(pluginFolder)).mkdirs();
		
		ClPk.Male = new DataSaveMain(new File(pluginFolder + File.separator + "Male.txt"));
		ClPk.Female = new DataSaveMain(new File(pluginFolder + File.separator + "Female.txt"));
		ClPk.NoGender = new DataSaveMain(new File(pluginFolder + File.separator + "NoGender.txt"));
		
		ClPk.Male.load();
		ClPk.Female.load();
		ClPk.NoGender.load();
	    
		this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
		if (protoLib == null || disguiseLib == null) {
			// Are BOTH missing, or disabled?
			if (protoLib == null && disguiseLib == null) {
				this.getLogger().warning(
						"Requirements not installed, please install LibsDisguises and ProtocolLib (Changeling class disabled)");
				DisguiseLibsEnabled = false;
			} else {
				// Find the missing file and alert the user
				if (protoLib == null) {
					this.getLogger().warning(
							"Requirements not installed, please install ProtocolLib (Changeling class disabled)");
					DisguiseLibsEnabled = false;
				}
				if (disguiseLib == null) {
					this.getLogger().warning(
							"Requirements not installed, please install LibsDisguises (Changeling class disabled)");
					// Changeling setDisabled!!!!
					DisguiseLibsEnabled = false;
				}
			}
			// stop me from running until the user installs the required files.
			// disable Changeling if not installed
			// Bukkit.getPluginManager().disablePlugin(this);
			// return;
		} // else{	
		this.getLogger().info("Requirements installed, continuing...");
		config = new ConfigMk(this, "config.yml", null);
		saveDefaultConfig("config.yml", null);
		config.reloadConfig();
		advCfg = new ConfigMk(this, "advCfg.yml", null);
		saveDefaultConfig("advCfg.yml", null);
		advCfg.reloadConfig();
		playerClass = new ConfigMk(this, "playerClass.yml", null);
		saveDefaultConfig("playerClass.yml", null);
		playerClass.reloadConfig();
		BranchSpells = new ConfigMk(this, "spells.yml", null);
		saveDefaultConfig("spells.yml", null);
		BranchSpells.reloadConfig();
		spawns = new ConfigMk(this, "spawns.yml", null);
		saveDefaultConfig("spawns.yml", null);
		spawns.reloadConfig();
		guilds_packs = new ConfigMk(this, "wolfPacks.yml", null);
		saveDefaultConfig("wolfPacks.yml", null);
		guilds_packs.reloadConfig();
		EnvData = new ConfigMk(this, "EnvData.yml", null);
		saveDefaultConfig("EnvData.yml", null);
		EnvData.reloadConfig();
		reloadSpawns();
		int millis = advCfg.getConfig().getInt("classDataAutoSave");
		// Display saveDataInterval
		Bukkit.getServer().getConsoleSender()
				.sendMessage("[" + this.getName() + "] " + "autoSaveClassDataRate: " + ChatColor.RED
						+ String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes(millis),
								TimeUnit.MILLISECONDS.toSeconds(millis)
										- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))));
		if (config.getConfig().getBoolean("Module.upgrade")) {
			Upgrade upgrade = new Upgrade();
			upgrade.getFile();
		}
		// TabReg
		getCommand("cp").setTabCompleter(new TabAutoCompletion());
		getServer().getPluginManager().registerEvents(new ClassAssign(), this);
		getServer().getPluginManager().registerEvents(new SpellUtil(), this);
		getServer().getPluginManager().registerEvents(new Research(), this);
		getServer().getPluginManager().registerEvents(new Glide(), this);
		getServer().getPluginManager().registerEvents(new ProjectileSpellDamage(), this);
		getServer().getPluginManager().registerEvents(new EarthBranchBonus(), this);
		getServer().getPluginManager().registerEvents(new DisguiseUtil(), this);
		getServer().getPluginManager().registerEvents(new DragonFlameUse(), this);
		getServer().getPluginManager().registerEvents(new TntRainCancel(), this);
		getServer().getPluginManager().registerEvents(new PotionEffectModiy(), this);
		// getServer().getPluginManager().registerEvents(new PvpControlMain(),
		// this);
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		// module registry
		// TODO
		if (config.getConfig().getBoolean("Module.togglePVP")) {
			for (Player player : Bukkit.getOnlinePlayers())
			// for(String s:playerClass.getConfig().getKeys(false))
			{
				// Player playerName = Bukkit.getPlayer(UUID.fromString(s));
				Boolean bol = playerClass.getConfig().getBoolean(player.getUniqueId() + ".PVP");
				PvpControlMain.pvpList.put(player, bol);
				System.out.print("ACP_PVPTOGGLE: " + player.getName() + "::" + bol);
			}
			getServer().getPluginManager().registerEvents(new PvpControlMain(), this);
		}
		// ------
		// continue
		instance = this;
		for (Player p : Bukkit.getOnlinePlayers()) {
//			ClassAssign.addPlayer(p);
			p.sendMessage(prefix + "loading");
		}
		if (!Bukkit.getServer().getAllowFlight()) {
			this.getLogger().warning(
					"Flying is disabled on this server, problems will occur with flying classes! Please enable flying!");
		}
		
	}
	
	@Override
	public void onDisable() {		
		Male.save();
		Female.save();
		Bukkit.getScheduler().cancelTasks(this);
	}
	public static final ClPk getInstance() {
		return instance;
	}

	public List<String> getNickNameList() {
		List<String> nickList = new ArrayList<String>();
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			if (p.getPlayer() != null && p.getPlayer().getDisplayName() != null) {
				if(p.isOp()){
					nickList.add(ChatColor.RESET+">"+ChatColor.DARK_RED+p.getPlayer().getName() + ChatColor.RESET + " :: " + p.getPlayer().getDisplayName() + " :: " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§") + ChatColor.RESET);
				}else
				if(p.getPlayer().hasPermission("cp.vip")){
					nickList.add(ChatColor.RESET+">"+ChatColor.DARK_GREEN+p.getPlayer().getName() + ChatColor.RESET + " :: " + p.getPlayer().getDisplayName() + " :: " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§") + ChatColor.RESET);
				}else{
					nickList.add(ChatColor.RESET+">"+p.getPlayer().getName() + ChatColor.RESET + " :: " + p.getPlayer().getDisplayName() + " :: " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§") + ChatColor.RESET);
				}			
			}
		}
		return nickList;
	}
	public List<String> getMaleList() {
		List<String> GenderList = new ArrayList<String>();
		for (String Gender: Male.getValues()) {
			if (Gender!= null) {
				GenderList.add(ChatColor.RESET+">"+ChatColor.AQUA+Gender+ChatColor.RESET);
			}
		}
		return GenderList;
	}
	public List<String> getFemaleList() {
		List<String> GenderList = new ArrayList<String>();
		for (String Gender: Female.getValues()) {
			if (Gender!= null) {
				GenderList.add(ChatColor.RESET+">"+ChatColor.LIGHT_PURPLE+Gender+ChatColor.RESET);
			}
		}
		return GenderList;
	}
	public List<String> getNoneList() {
		List<String> GenderList = new ArrayList<String>();
		for (String Gender: NoGender.getValues()) {
			if (Gender!= null) {
				GenderList.add(ChatColor.RESET+">"+ChatColor.WHITE+Gender+ChatColor.RESET);
			}
		}
		return GenderList;
	}
	public List<String> getGenderList() {
		List<String> GenderList = new ArrayList<String>();
		for (String Gender: Male.getValues()) {
			if (Gender!= null) {
				GenderList.add(ChatColor.RESET+">"+ChatColor.AQUA+Gender+ChatColor.RESET);
			}
		}
		for (String Gender: Female.getValues()) {
			if (Gender!= null) {
				GenderList.add(ChatColor.RESET+">"+ChatColor.LIGHT_PURPLE+Gender+ChatColor.RESET);
			}
		}
		return GenderList;
	}
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("cp")) {
			Player player = (Player) sender;
			Player p = (Player) sender;
			String plyName = sender.getName();
			if (args.length == 0){
				sender.sendMessage(prefix + ChatColor.RED + "Usage: /cp <Gender|Class|WolfPack>" + ChatColor.RESET);
			}else
				if(args[0].equalsIgnoreCase("Class")){
					if (args.length == 1){
						sender.sendMessage(prefix + ChatColor.RED + " Usage: /cp Class <Get|Set|Spawn [Class]>" + ChatColor.RESET);
					}else
						if(args[1].equalsIgnoreCase("Set")){
							if (sender.hasPermission("acp.admin")){
								if (args.length == 2){
									sender.sendMessage(prefix + ChatColor.RED + " Usage: /cp Class Set <Class|Tag|Spawn|Sign>" + ChatColor.RESET);
								}else
									if(args[2].equalsIgnoreCase("Class")){
										if (args.length == 3){
											sender.sendMessage(prefix + ChatColor.RED + " Usage: /cp Class Set Class [Player] <Class>" + ChatColor.RESET);
										}else
											if (args.length == 4){
												Player pl = Bukkit.getPlayer(p.getName());
												List<String> classes = Arrays.asList(classNames);
												if (classes.contains(args[3])) {
													ClassSeter.setClass(pl, args[3]);
												}
											}else
												if(Bukkit.getPlayer(args[3]) != null&& args.length == 5){
													if (Bukkit.getPlayer(args[3]) != null) {
														Player pl = Bukkit.getPlayer(args[3]);
														List<String> classes = Arrays.asList(classNames);
														if (classes.contains(args[4])) {
															ClassSeter.setClass(pl, args[4]);
														}
													}
												}else{
													sender.sendMessage(prefix + ChatColor.RED + "Usage: /cp Class Set Class [Player] <Class>" + ChatColor.RESET);
												}
									}else
										if(args[2].equalsIgnoreCase("Tag")){
											List<String> classes = Arrays.asList(classNames);
											if (args.length == 3){
												sender.sendMessage(prefix + ChatColor.RED + " Usage: /cp Class Set Tag <ClassName> <Tag> [SubClass]" + ChatColor.RESET);
											}else
												if(classes.contains(args[3])&&args.length == 5){
													ClPk.advCfg.getConfig().set("Prefixes."+args[3]+".default", "[" +args[4]+ "&f]");
													sender.sendMessage("The "+ args[3] + " Tag Has Been Set As "+ClPk.advCfg.getConfig().getString("Prefixes."+args[3]+".default").replaceAll("&", "§"));
												}else
													if(classes.contains(args[3])&&args.length == 6){
														ClPk.advCfg.getConfig().set("Prefixes."+args[3]+"."+args[5], "[" +args[4]+ "&f]" );
													}else {
														sender.sendMessage(prefix + "/cp Class Set Tag <ClassName> <Tag> [SubClass]");
													}
											ClPk.advCfg.saveConfig();
											ClPk.advCfg.reloadConfig();
											for (Player play : Bukkit.getOnlinePlayers()) {
												if (play.getPlayer() != null && play.getPlayer().getDisplayName() != null) {
													setDisplayName(play);
												}
											}
										}else
											if(args[2].equalsIgnoreCase("Spawn")){
												if (sender.hasPermission("acp.admin") && sender instanceof Player) {
													if ((sender instanceof Player)) {
														if (args.length == 3){
															sender.sendMessage(prefix + ChatColor.RED + " Usage: /cp Class Set Spawn <className>" + ChatColor.RESET);
														}else 
															if (args.length == 4) {
																setSpawn((Player) sender, args[3]);
															} else {
																sender.sendMessage(prefix + "/cp Class Set Spawn <className>");
															}
													} else {
														sender.sendMessage(prefix + "Only a player can use this command!");
													}
													reloadSpawns();
												} else {
													sender.sendMessage(prefix + ChatColor.RED + "Missing Permissions" + ChatColor.RESET);
												}	
											}else
												if(args[2].equalsIgnoreCase("Sign")){
													if(args[3].equalsIgnoreCase("Text")){
														List<String> classes = Arrays.asList(classNames);
														if (classes.contains(args[4])&&args.length == 6) {
															ClPk.advCfg.getConfig().set("SignText."+args[4], "[" +args[5]+ "]" );
														} else
															if (classes.contains(args[4])&&args.length < 6){
																sender.sendMessage(prefix + ChatColor.RED + "Sign Text Can Not Be Empty" + ChatColor.RESET);
															}else{
																sender.sendMessage(prefix + ChatColor.RED + "Usage: /cp Class Set Sign Text <Class> <Text>" + ChatColor.RESET);
															}
													}else
														if(args[3].equalsIgnoreCase("Line")){

															if((args[4].equalsIgnoreCase("0"))||(args[4].equalsIgnoreCase("1"))||(args[4].equalsIgnoreCase("2"))||(args[4].equalsIgnoreCase("3"))){
																ClPk.advCfg.getConfig().set("SignText.SignTextLine", args[4]);
															}else{
																sender.sendMessage(prefix + ChatColor.RED + "Invalid Line Number! Must Be 0-3" + ChatColor.RESET);
															}
														}else{
															sender.sendMessage(prefix + ChatColor.RED + "Usage: /cp Class Set Sign <Text|Line>" + ChatColor.RESET);
														}
													ClPk.advCfg.saveConfig();
													ClPk.advCfg.reloadConfig();
												}else{
													sender.sendMessage(prefix + ChatColor.RED + "Usage: /cp Class Set <Class|Tag|Spawn|Sign>" + ChatColor.RESET);
												}
							}else{
								sender.sendMessage(prefix + ChatColor.RED + "No." + ChatColor.RESET);
							}
						}else
							if(args[1].equalsIgnoreCase("Get")){
								if (args.length == 2){
									sender.sendMessage(prefix + ChatColor.RED + " Usage: /cp Class Get <Classes|Spawn|Info|List|Tag|Sign|Stats>" + ChatColor.RESET);
								}else 
									if(args[2].equalsIgnoreCase("Classes")){
										sender.sendMessage(ChatColor.DARK_GRAY+"======"+ChatColor.RESET+"["+ChatColor.YELLOW+"Classes"+ChatColor.RESET+"]"+ChatColor.DARK_GRAY+"======");
										Arrays.sort(classNames);
										for(String Class : classNames){
											sender.sendMessage(ChatColor.RESET+">"+ClPk.advCfg.getConfig().getString("Prefixes."+Class+".default").replaceAll("&", "§") + ChatColor.RESET);
										}
									}else
										if(args[2].equalsIgnoreCase("Spawn")){
											if(args.length==4){
												List<String> classes = Arrays.asList(classNames);
												for (String s : pSpawns.keySet()) {
													if (s.equalsIgnoreCase(args[3])&&classes.contains(args[3])&&args.length==4) {
														sender.sendMessage(ChatColor.DARK_GRAY+"======"+ChatColor.RESET+ClPk.advCfg.getConfig().getString("Prefixes."+args[3]+".default").replaceAll("&", "§")+ChatColor.DARK_GRAY+"======");
														sender.sendMessage(ChatColor.YELLOW+"World"+ChatColor.WHITE+">"+ClPk.spawns.getConfig().getString(args[3]+".world"));
														sender.sendMessage(ChatColor.RED+"X"+ChatColor.WHITE+"> "+ChatColor.RESET+ClPk.spawns.getConfig().getDouble(args[3]+".x"));
														sender.sendMessage(ChatColor.GREEN+"Y"+ChatColor.WHITE+"> "+ChatColor.RESET+ClPk.spawns.getConfig().getDouble(args[3]+".y"));
														sender.sendMessage(ChatColor.AQUA+"Z"+ChatColor.WHITE+"> "+ChatColor.RESET+ClPk.spawns.getConfig().getDouble(args[3]+".z"));
														sender.sendMessage(ChatColor.LIGHT_PURPLE+"Pitch"+ChatColor.WHITE+"> "+ChatColor.RESET+ClPk.spawns.getConfig().getDouble(args[3]+".pitch"));
														sender.sendMessage(ChatColor.BLUE+"Yaw"+ChatColor.WHITE+"> "+ChatColor.RESET+ClPk.spawns.getConfig().getDouble(args[3]+".yaw"));
													}
												}
											}else
												if(args.length==3){
													sender.sendMessage(ChatColor.DARK_GRAY+"======"+ChatColor.RESET+ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§")+ChatColor.DARK_GRAY+"======");
													sender.sendMessage(ChatColor.YELLOW+"World"+ChatColor.WHITE+"> "+ChatColor.RESET+ClPk.spawns.getConfig().getString((ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".world"));
													sender.sendMessage(ChatColor.RED+"X"+ChatColor.WHITE+"> "+ChatColor.RESET+ClPk.spawns.getConfig().getDouble((ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".x"));
													sender.sendMessage(ChatColor.GREEN+"Y"+ChatColor.WHITE+"> "+ChatColor.RESET+ClPk.spawns.getConfig().getDouble((ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".y"));
													sender.sendMessage(ChatColor.AQUA+"Z"+ChatColor.WHITE+"> "+ChatColor.RESET+ClPk.spawns.getConfig().getDouble((ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".z"));
													sender.sendMessage(ChatColor.LIGHT_PURPLE+"Pitch"+ChatColor.WHITE+"> "+ChatColor.RESET+ClPk.spawns.getConfig().getDouble((ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".pitch"));
													sender.sendMessage(ChatColor.BLUE+"Yaw"+ChatColor.WHITE+"> "+ChatColor.RESET+ClPk.spawns.getConfig().getDouble((ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".yaw"));

												}else{
													sender.sendMessage(prefix + ChatColor.RED + "Usage: /cp Class Get Spawn [Class]" + ChatColor.RESET);
												}	
										}else
											if(args[2].equalsIgnoreCase("Info")){
												if (sender instanceof Player) {
													List<String> classes = Arrays.asList(classNames);
													if(args.length == 3){
														ClassBooks.classBooks(p,ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"));
													}else
														if (args.length == 4&&classes.contains(args[3])) {
															ClassBooks.classBooks(p, args[3]);
														}else{
															sender.sendMessage(prefix + ChatColor.RED + "Usage: /cp Class Get Info [Class]" + ChatColor.RESET);
														}
												} else {
													sender.sendMessage(prefix + "Only a player can use this command!");
												}
											}else
												if(args[2].equalsIgnoreCase("List")){
													int but = 0;
													for (OfflinePlayer pl : Bukkit.getOfflinePlayers()) {
														if (pl.getPlayer() != null && pl.getPlayer().getDisplayName() != null) {
															sender.sendMessage(getNickNameList().get(but));
															but++;
														}
													}		
												}else
													if(args[2].equalsIgnoreCase("Tag")){
														List<String> classes = Arrays.asList(classNames);
														if(args.length == 4&&classes.contains(args[3])){
															sender.sendMessage(ClPk.advCfg.getConfig().get("Prefixes."+args[3]+".default").toString());
														}else{
															sender.sendMessage(prefix + "/cp Class Get Tag <ClassName>");
														}
													}else
														if(args[2].equalsIgnoreCase("Sign")){
															if(args[3].equalsIgnoreCase("Text")){

															}else
																if(args[3].equalsIgnoreCase("Line")){

																}else{
																	sender.sendMessage(prefix + ChatColor.RED + "Usage: /cp Class Get Sign <Text|Line>" + ChatColor.RESET);
																}
														}else
															if(args[2].equalsIgnoreCase("Stats")){

															}else{
																sender.sendMessage(prefix + ChatColor.RED + "Usage: /cp Class Get <Classes|Spawn|Info|List|Tag|Sign|Stats>" + ChatColor.RESET);					
															}
							}else
								if(args[1].equalsIgnoreCase("Spawn")){
									List<String> classes = Arrays.asList(classNames);
									if(args.length == 3&&classes.contains(args[2])){
										ClPk.spawnPlayer(p, args[2]);
									}else
										if(args.length == 2){
											ClPk.spawnPlayer(p,ClPk.playerClass.getConfig().getString((p.getUniqueId().toString())+".class"));
										}

								}else{
									sender.sendMessage(prefix + ChatColor.RED + "Usage: /cp Class <Get|Set|Spawn>" + ChatColor.RESET);
								}

				}else
					if(args[0].equalsIgnoreCase("Gender")){
                        if (args.length == 1){
                        sender.sendMessage(prefix + ChatColor.RED + "Usage: /cp Gender <Set [PlayerName]|List>" + ChatColor.RESET);
                        }else
						if(args[1].equalsIgnoreCase("Set")){
							if(args.length == 3){
								if(args[2].equalsIgnoreCase("female")){	
									player.sendMessage(prefix+ChatColor.GOLD +" You were Successfully added to "+ ChatColor.LIGHT_PURPLE + "Female");
									System.out.println("[CP] "+plyName+" Has Changed her Gender to Female");
									Female.add(plyName);
									Female.save();
									if(Male.contains(plyName)){
										Male.remove(plyName);
										Male.save();
									}
									if(NoGender.contains(args[2])){
										NoGender.remove(args[2]);
										NoGender.save();
									}
									setDisplayName(p);
									return true;
								}else
									if(args[2].equalsIgnoreCase("Male")){
										player.sendMessage(prefix+ChatColor.GOLD + " You were Successfully added to "+ ChatColor.AQUA + "Male");
										System.out.println("[CP] "+plyName+" Has Changed his Gender to Male");
										Male.add(plyName);
										Male.save();
										if(Female.contains(plyName)){
											Female.remove(plyName);
											Female.save();
										}
										if(NoGender.contains(args[2])){
											NoGender.remove(args[2]);
											NoGender.save();
										}
										setDisplayName(p);
										return true;
									}else
										if(args[2].equalsIgnoreCase("None")){
											player.sendMessage(prefix+ChatColor.GOLD + " You were Successfully added to "+ ChatColor.WHITE + "No-Gender");
											System.out.println("[CP] "+plyName+" Has Changed the Gender to No-Gender");
											NoGender.add(plyName);
											NoGender.save();
											if(Male.contains(plyName)){
												Male.remove(plyName);
												Male.save();
											}
											if(Female.contains(plyName)){
												Female.remove(plyName);
												Female.save();
											}
											setDisplayName(p);
											return true;
										}
							}else
								if(args.length == 4){
									if (sender.hasPermission("acp.admin")){
										if(args[3].equalsIgnoreCase("female")){
											Player recpt = Bukkit.getPlayer(args[2]);
											player.sendMessage(prefix+ " " + recpt.getDisplayName() +ChatColor.GOLD +	" was Successfully added to "+ ChatColor.LIGHT_PURPLE + "Female");
											recpt.sendMessage(prefix+ChatColor.GOLD + " You were Successfully added to "+ ChatColor.LIGHT_PURPLE + "Female");
											System.out.println("[CP] "+plyName+" Has Changed "+recpt.getName()+"'s Gender to Female");
											Female.add(args[2]);
											Female.save();
											if(Male.contains(args[2])){
												Male.remove(args[2]);
												Male.save();
											}
											if(NoGender.contains(args[2])){
												NoGender.remove(args[2]);
												NoGender.save();
											}
											setDisplayName(recpt);
											return true;
										}else
											if(args[3].equalsIgnoreCase("Male")){
												Player recpt = Bukkit.getPlayer(args[2]);
												player.sendMessage(prefix+ " " + recpt.getDisplayName() +ChatColor.GOLD +" was Successfully added to "+ ChatColor.AQUA + "Male");

												recpt.sendMessage(prefix+ChatColor.GOLD + " You were Successfully added to "+ ChatColor.AQUA + "Male");
												System.out.println("[CP] "+plyName+" Has Changed "+recpt.getName()+"'s Gender to Male");
												Male.add(args[2]);
												Male.save();
												if(Female.contains(args[2])){
													Female.remove(args[2]);
													Female.save();
												}
												if(NoGender.contains(args[2])){
													NoGender.remove(args[2]);
													NoGender.save();
												}
												setDisplayName(recpt);
												return true;
											}else
												if(args[3].equalsIgnoreCase("None")){
													Player recpt = Bukkit.getPlayer(args[2]);
													player.sendMessage(prefix+ " " + recpt.getDisplayName() +ChatColor.GOLD +" was Successfully added to "+ ChatColor.WHITE + "No-Gender");	
													recpt.sendMessage(prefix+ChatColor.GOLD + " You were Successfully added to "+ ChatColor.WHITE + "NoGender");
													System.out.println("[CP] "+plyName+" Has Changed "+recpt.getName()+"'s Gender to No-Gender");
													NoGender.add(plyName);
													NoGender.save();
													if(Male.contains(args[2])){
														Male.remove(args[2]);
														Male.save();
													}
													if(Female.contains(args[2])){
														Female.remove(args[2]);
														Female.save();
													}
													setDisplayName(recpt);
													return true;
												}
									}else{
										sender.sendMessage(prefix + ChatColor.RED + "Insufficient Permissions" + ChatColor.RESET);
									}
								}else{
									sender.sendMessage(prefix + ChatColor.RED + "Usage: /cp Gender Set [PlayerName] <Male|Female|NoGender>" + ChatColor.RESET);
								}
                                
						}else
							if(args[1].equalsIgnoreCase("List")){
								if(args[2].equalsIgnoreCase("Male")){
									sender.sendMessage(ChatColor.DARK_GRAY+"======"+ChatColor.WHITE+"["+ChatColor.AQUA+"Male"+ChatColor.WHITE+"]"+ChatColor.DARK_GRAY+"======");
									int but = 0;
									for (String Males : Male.getValues()) {
										if (Males != null) {
											sender.sendMessage(getMaleList().get(but));
											but++;
										}
									}		
								}else
									if(args[2].equalsIgnoreCase("Female")){
										sender.sendMessage(ChatColor.DARK_GRAY+"====="+ChatColor.WHITE+"["+ChatColor.LIGHT_PURPLE+"Female"+ChatColor.WHITE+"]"+ChatColor.DARK_GRAY+"=====");
										int but = 0;
										for (String Females : Female.getValues()) {
											if (Females != null) {
												sender.sendMessage(getFemaleList().get(but));
												but++;
											}
										}
									}else
										if(args[2].equalsIgnoreCase("NoGender")){
											int but = 0;
											sender.sendMessage(ChatColor.DARK_GRAY+"======"+ChatColor.WHITE+"["+ChatColor.WHITE+"NoGender"+ChatColor.WHITE+"]"+ChatColor.DARK_GRAY+"======");
											for (String None : NoGender.getValues()) {
												if (None != null) {
													sender.sendMessage(getNoneList().get(but));
													but++;
												}
											}
										}else
											if(args[2].equalsIgnoreCase("All")){
												int but = 0;
												sender.sendMessage(ChatColor.DARK_GRAY+"======"+ChatColor.WHITE+"["+ChatColor.AQUA+"Male"+ChatColor.WHITE+"]"+ChatColor.DARK_GRAY+"======");
												for (String Males : Male.getValues()) {
													if (Males != null) {
														sender.sendMessage(getMaleList().get(but));
														but++;
													}
												}
												but=0;
												sender.sendMessage(ChatColor.DARK_GRAY+"====="+ChatColor.WHITE+"["+ChatColor.LIGHT_PURPLE+"Female"+ChatColor.WHITE+"]"+ChatColor.DARK_GRAY+"=====");
												for (String Females : Female.getValues()) {
													if (Females != null) {
														sender.sendMessage(getFemaleList().get(but));
														but++;
													}
												}
												but = 0;
												sender.sendMessage(ChatColor.DARK_GRAY+"===="+ChatColor.WHITE+"["+ChatColor.WHITE+"NoGender"+ChatColor.WHITE+"]"+ChatColor.DARK_GRAY+"====");
												for (String None : NoGender.getValues()) {
													if (None != null) {
														sender.sendMessage(getNoneList().get(but));
														but++;
													}
												}
											}else{
												sender.sendMessage(prefix + ChatColor.RED + "Usage: /cp Gender List <Male|Female|NoGender|All>" + ChatColor.RESET);
											}
							}else{
								sender.sendMessage(prefix + ChatColor.RED + "Usage: /cp Gender <Set|List>" + ChatColor.RESET);
							}
					}else
						if(args[0].equalsIgnoreCase("WolfPack")){
							sender.sendMessage("Under Construction");
						}else{
							sender.sendMessage(prefix + ChatColor.RED + "Usage: /cp <Gender|Class|WolfPack>" + ChatColor.RESET);
						}	
		}
		return false;
	}

	public void setDisplayName(Player p) {
		if(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class")!=null){
			if (Female.contains(p.getName())){
				p.setPlayerListName(ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
			}else
			if(Male.contains(p.getName())){
				p.setPlayerListName(ChatColor.AQUA + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
			}else{
				p.setPlayerListName(ChatColor.WHITE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
			}
		}else{
			if (Female.contains(p.getName())){
				p.setPlayerListName(ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET);
			}else
			if(Male.contains(p.getName())){
				p.setPlayerListName(ChatColor.AQUA + p.getName() + ChatColor.RESET);
			}else{
				p.setPlayerListName(ChatColor.WHITE + p.getName() + ChatColor.RESET);
			}
				
		}
		
	}
	public void changeSetup(Player p) {
		if (p.getPlayer().getGameMode() != GameMode.SURVIVAL || p.getPlayer().getGameMode() != GameMode.ADVENTURE) {
			if (ClPk.onlinePlayers.get(p.getName()) instanceof Flier) {
				p.getLocation().getWorld().dropItem(p.getLocation(), new ItemStack(Material.FEATHER, 1));
				p.getPlayer().setAllowFlight(true);
			}
			if (ClPk.onlinePlayers.get(p.getName()) instanceof MagicUser) {
				p.getLocation().getWorld().dropItem(p.getLocation(), new ItemStack(Material.STICK, 1));
				p.getPlayer().setAllowFlight(false);
				if (!(ClPk.onlinePlayers.get(p.getName()) instanceof Changeling)) {
					ClassBooks.SpellBook(p);
				}
			}
			if (ClPk.onlinePlayers.get(p.getName()) instanceof Changeling) {
				p.getLocation().getWorld().dropItem(p.getLocation(), new ItemStack(Material.LEATHER, 1));
				p.getPlayer().setAllowFlight(false);
			}
		}
	}
	public static void spawnPlayer(Player p, String clnm) {
		boolean message = true;
		for (String s : pSpawns.keySet()) {
			if (s.equalsIgnoreCase(clnm)) {
				p.teleport(pSpawns.get(s));
				p.sendMessage(prefix + ChatColor.AQUA + "You've teleported to spawn!");
				message = false;
			}
		}
		if (message) {
			if (config.getConfig().getBoolean("Spawn-Options.Cspawn-Messages")) {
				p.sendMessage(prefix + "That spawn is not set yet!");
			}
		}
	}
	private void setSpawn(Player p, String clnm) {
		boolean message = true;
		for (String s : classNames) {
			if (s.equalsIgnoreCase(clnm)) {
				spawns.getConfig().set(s + ".world", p.getLocation().getWorld().getName());
				spawns.getConfig().set(s + ".x", p.getLocation().getX());
				spawns.getConfig().set(s + ".y", p.getLocation().getY());
				spawns.getConfig().set(s + ".z", p.getLocation().getZ());
				spawns.getConfig().set(s + ".pitch", p.getLocation().getPitch());
				spawns.getConfig().set(s + ".yaw", p.getLocation().getYaw());
				spawns.saveConfig();
				p.sendMessage(prefix + ChatColor.AQUA + "Spawn set for the " + clnm + " class.");
				if (spawns.getConfig().contains(s)) {
					pSpawns.put(s,
							new Location(Bukkit.getWorld(spawns.getConfig().getString(s + ".world")),
									spawns.getConfig().getInt(s + ".x"), spawns.getConfig().getInt(s + ".y"),
									spawns.getConfig().getInt(s + ".z"),

									(float) spawns.getConfig().getDouble(s + ".yaw"),
									(float) spawns.getConfig().getDouble(s + ".pitch")));
				}
				message = false;
			}
		}
		if (message) {
			p.sendMessage(prefix + "That is not a class name! (" + clnm + ")");
		}
	}
	public static Player getPlayer(String s) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.getName().equalsIgnoreCase(s)) {
				return p;
			}
		}
		return null;
	}
	private void reloadSpawns() {
		pSpawns.keySet().removeAll(pSpawns.keySet());
		for (String s : classNames) {
			if (spawns.getConfig().contains(s)) {
				pSpawns.put(s,
						new Location(Bukkit.getWorld(spawns.getConfig().getString(s + ".world")),
								spawns.getConfig().getInt(s + ".x"), spawns.getConfig().getInt(s + ".y"),
								spawns.getConfig().getInt(s + ".z"),

								(float) spawns.getConfig().getDouble(s + ".yaw"),
								(float) spawns.getConfig().getDouble(s + ".pitch")));
			}
		}
	}
	public void saveDefaultConfig(String fileName, String dir) {
		if (dir != null) {
			if (!new File(getDataFolder() + dir + "/", fileName).exists()) {

				getLogger().info("Creating " + fileName + " file...");
				File outFile = new File(getDataFolder() + dir, fileName);
				InputStream in = getResource(fileName);
				try {
					if (!outFile.exists()) {
						OutputStream out = new FileOutputStream(outFile);
						byte[] buf = new byte['?'];
						int len;
						while ((len = in.read(buf)) > 0) { // int len;
							out.write(buf, 0, len);
						}
						out.close();
						in.close();
					} else {

						logger.log(Level.WARNING, "Could not save " + outFile.getName() + " to " + outFile + " because "
								+ outFile.getName() + " already exists.");
					}
				} catch (IOException e) {

					e.printStackTrace();
				}
				// saveResource(fileName, false);
			}
		} else if (!new File(getDataFolder(), fileName).exists()) {
			getLogger().info("Creating " + fileName + " file...");
			saveResource(fileName, false);
		}
	}
}
