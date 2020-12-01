package com.ahui.classpack.util;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;

import com.ahui.classpack.ClPk;
import com.ahui.classpack.classes.Ahuizotl;
import com.ahui.classpack.classes.Alicorn;
import com.ahui.classpack.classes.Breezy;
import com.ahui.classpack.classes.Minotaur;
import com.ahui.classpack.classes.Changeling;
import com.ahui.classpack.classes.Chimera;
import com.ahui.classpack.classes.DiamondDog;
import com.ahui.classpack.classes.Draconequus;
import com.ahui.classpack.classes.Dragon;
import com.ahui.classpack.classes.Earth;
import com.ahui.classpack.classes.Griffon;
import com.ahui.classpack.classes.Pegasus;
import com.ahui.classpack.classes.Thestral;
import com.ahui.classpack.classes.TimberWolf;
import com.ahui.classpack.classes.Unicorn;
import com.ahui.classpack.classes.Zebra;
import com.ahui.classpack.classes.interfaces.Flier;
import com.ahui.classpack.classes.interfaces.MagicUser;

public class ClassSeter {
	public static void setClass(Player p, String c) {
		String classname = "";
		switch (c) {
		case "Unicorn":
			String[] spells = { "flameI", "frostI", "blink", "healI", "shadowBeamI" };
			// CLASSID 1
			classname = "Unicorn";
			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".class", classname);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".mana",
					ClPk.advCfg.getConfig().getDouble("Classes.Unicorn.maxMana"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".maxMana",
					ClPk.advCfg.getConfig().getDouble("Classes.Unicorn.maxMana"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".level", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".branch", "default");
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".currentSpell", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".spells", spells);
			setDisplayName(p);			
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
			ClPk.onlinePlayers.remove(p.getName());
			// addExtensions(p);
			Unicorn uniClass = new Unicorn(p.getName());
			uniClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Unicorn.default").replaceAll("&", "§"));
			// newClass.refreshMana(1);
			ClPk.onlinePlayers.put(p.getName(), uniClass);
			p.sendMessage(ClPk.prefix + ChatColor.AQUA + "You are now a Unicorn!");
			ClPk.spawnPlayer(p, "Unicorn");

			break;
		case "Pegasus":
			classname = "Pegasus";
			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".class", classname);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".energy",ClPk.advCfg.getConfig().getDouble("Classes.Pegasus.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".maxEnergy",ClPk.advCfg.getConfig().getDouble("Classes.Pegasus.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".level", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".fxp", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".ftime", 0);
			setDisplayName(p);				
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
			ClPk.onlinePlayers.remove(p.getName());
			// addExtensions(p);
			Pegasus pegClass = new Pegasus(p.getName());
			pegClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Pegasus.default").replaceAll("&", "§"));
			// newClass.refreshEnergy(2);
			ClPk.onlinePlayers.put(p.getName(), pegClass);
			p.sendMessage(ClPk.prefix + ChatColor.AQUA + "You are now a Pegasus!");
			ClPk.spawnPlayer(p, "Pegasus");
			break;
		case "Thestral":
			classname = "Thestral";
			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".class", classname);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".energy",ClPk.advCfg.getConfig().getDouble("Classes.Thestral.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".maxEnergy",ClPk.advCfg.getConfig().getDouble("Classes.Thestral.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".level", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".fxp", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".ftime", 0);
			setDisplayName(p);					
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
			ClPk.onlinePlayers.remove(p.getName());
			// addExtensions(p);
			Thestral theClass = new Thestral(p.getName());
			theClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Thestral.default").replaceAll("&", "§"));
			// newClass.refreshEnergy(2);
			ClPk.onlinePlayers.put(p.getName(), theClass);
			p.sendMessage(ClPk.prefix + ChatColor.AQUA + "You are now a Thestral!");
			ClPk.spawnPlayer(p, "Thestral");
			break;
		case "Earth":
			classname = "Earth";
			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".class", classname);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".energy",
					ClPk.advCfg.getConfig().getDouble("Classes.Earth.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".maxEnergy",
					ClPk.advCfg.getConfig().getDouble("Classes.Earth.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".level", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".branch", "default");
			setDisplayName(p);					
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
			ClPk.onlinePlayers.remove(p.getName());
			// addExtensions(p);
			Earth earClass = new Earth(p.getName());
			earClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Earth.default").replaceAll("&", "§"));
			// newClass.refreshEnergy(2);

			ClPk.onlinePlayers.put(p.getName(), earClass);
			p.sendMessage(ClPk.prefix + ChatColor.AQUA + "You are now an Earth pony!");
			ClPk.spawnPlayer(p, "Earth");
			break;
		case "Alicorn":
			classname = "Alicorn";
			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".class", classname);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".energy",
					ClPk.advCfg.getConfig().getDouble("Classes.Alicorn.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".mana",
					ClPk.advCfg.getConfig().getDouble("Classes.Alicorn.maxMana"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".maxEnergy",
					ClPk.advCfg.getConfig().getDouble("Classes.Alicorn.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".maxMana",
					ClPk.advCfg.getConfig().getDouble("Classes.Alicorn.maxMana"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".flevel", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".mlevel", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".fxp", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".ftime", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".branch", "default");
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".currentSpell", 0);
			setDisplayName(p);					
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
			ClPk.onlinePlayers.remove(p.getName());
			// addExtensions(p);
			Alicorn aliClass = new Alicorn(p.getName());
			aliClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Alicorn.default").replaceAll("&", "§"));
			// newClass.refreshEnergy(2);

			ClPk.onlinePlayers.put(p.getName(), aliClass);
			p.sendMessage(ClPk.prefix + ChatColor.AQUA + "You are now an Alicorn!");
			ClPk.spawnPlayer(p, "Alicorn");
			break;
		case "Changeling":
			classname = "Changeling";
			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".class", classname);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".energy",
					ClPk.advCfg.getConfig().getDouble("Classes.Changeling.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".mana",
					ClPk.advCfg.getConfig().getDouble("Classes.Changeling.maxMana"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".maxEnergy",
					ClPk.advCfg.getConfig().getDouble("Classes.Changeling.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".maxMana",
					ClPk.advCfg.getConfig().getDouble("Classes.Changeling.maxMana"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".flevel", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".mlevel", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".fxp", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".ftime", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".branch", "default");
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".disguises",
					ClPk.advCfg.getConfig().getDouble("Classes.Changeling.disguises.default"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".currentSpell", 0);
			setDisplayName(p);				
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
			ClPk.onlinePlayers.remove(p.getName());
			// addExtensions(p);
			Changeling chaClass = new Changeling(p.getName());
			chaClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Changeling.default").replaceAll("&", "§"));
			ClPk.onlinePlayers.put(p.getName(), chaClass);
			p.sendMessage(ClPk.prefix + ChatColor.AQUA + "You are now a Changeling!");
			ClPk.spawnPlayer(p, "Changeling");
			break;
		case "Dragon":
			classname = "Dragon";
			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".class", classname);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".energy",
					ClPk.advCfg.getConfig().getDouble("Classes.Dragon.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".maxEnergy",
					ClPk.advCfg.getConfig().getDouble("Classes.Dragon.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".fxp", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".ftime", 0);
			setDisplayName(p);							
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
			ClPk.onlinePlayers.remove(p.getName());
			// addExtensions(p);
			Dragon draClass = new Dragon(p.getName());
			draClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Dragon.default").replaceAll("&", "§"));
			ClPk.onlinePlayers.put(p.getName(), draClass);
			p.sendMessage(ClPk.prefix + ChatColor.AQUA + "You are now a Dragon!");
			ClPk.spawnPlayer(p, "Dragon");
			break;
		case "TimberWolf":
			classname = "TimberWolf";
			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".class", classname);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".energy",
					ClPk.advCfg.getConfig().getDouble("Classes.TimberWolf.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".maxEnergy",
					ClPk.advCfg.getConfig().getDouble("Classes.TimberWolf.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".level", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".pack", "null");
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".packPref", "");
			setDisplayName(p);					
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
			// ClPk.onlinePlayers.remove(p.getName());
			// addExtensions(p);
			TimberWolf timClass = new TimberWolf(p.getName());
			timClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.TimberWolf.default").replaceAll("&", "§"));
			ClPk.onlinePlayers.put(p.getName(), timClass);
			p.sendMessage(ClPk.prefix + ChatColor.AQUA + "You are now a TimberWolf!");
			ClPk.spawnPlayer(p, "TimberWolf");
			break;
		case "Griffon":
			classname = "Griffon";
			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".class", classname);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".energy",
					ClPk.advCfg.getConfig().getDouble("Classes.Griffon.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".maxEnergy",
					ClPk.advCfg.getConfig().getDouble("Classes.Griffon.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".level", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".fxp", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".ftime", 0);
			setDisplayName(p);				
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
			ClPk.onlinePlayers.remove(p.getName());
			// addExtensions(p);
			Griffon griClass = new Griffon(p.getName());
			griClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Griffon.default").replaceAll("&", "§"));

			ClPk.onlinePlayers.put(p.getName(), griClass);
			p.sendMessage(ClPk.prefix + ChatColor.AQUA + "You are now a Griffon!");
			ClPk.spawnPlayer(p, "Griffon");
			break;
		case "Zebra":
			classname = "Zebra";
			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".class", classname);
			setDisplayName(p);					
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
			ClPk.onlinePlayers.remove(p.getName());
			// addExtensions(p);
			Zebra zebClass = new Zebra(p.getName());
			zebClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Zebra.default").replaceAll("&", "§"));
			// newClass.refreshEnergy(2);

			ClPk.onlinePlayers.put(p.getName(), zebClass);
			p.sendMessage(ClPk.prefix + ChatColor.AQUA + "You are now a Zebra!");
			ClPk.spawnPlayer(p, "Zebra");

			break;	
		case "Breezy":
			classname = "Breezy";
			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".class", classname);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".energy",
					ClPk.advCfg.getConfig().getDouble("Classes.Breezy.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".maxEnergy",
					ClPk.advCfg.getConfig().getDouble("Classes.Breezy.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".level", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".fxp", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".ftime", 0);
			setDisplayName(p);					
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
			ClPk.onlinePlayers.remove(p.getName());
			// addExtensions(p);
			Breezy breClass = new Breezy(p.getName());
			breClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Breezy.default").replaceAll("&", "§"));
			// newClass.refreshEnergy(2);

			ClPk.onlinePlayers.put(p.getName(), breClass);
			p.sendMessage(ClPk.prefix + ChatColor.AQUA + "You are now a Breezy!");
			ClPk.spawnPlayer(p, "Breezy");

			break;
		case "DiamondDog":
			classname = "DiamondDog";
			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".class", classname);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".energy",
					ClPk.advCfg.getConfig().getDouble("Classes.DiamondDog.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".maxEnergy",
					ClPk.advCfg.getConfig().getDouble("Classes.DiamondDog.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".level", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".branch", "default");
			setDisplayName(p);				
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
			ClPk.onlinePlayers.remove(p.getName());
			// addExtensions(p);
			DiamondDog ddgClass = new DiamondDog(p.getName());
			ddgClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.DiamondDog.default").replaceAll("&", "§"));
			// newClass.refreshEnergy(2);

			ClPk.onlinePlayers.put(p.getName(), ddgClass);
			p.sendMessage(ClPk.prefix + ChatColor.AQUA + "You are now A Diamond Dog!");
			ClPk.spawnPlayer(p, "DiamondDog");
			break;
		case "Minotaur":
			classname = "Minotaur";
			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".class", classname);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".energy",
					ClPk.advCfg.getConfig().getDouble("Classes.Centaur.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".maxEnergy",
					ClPk.advCfg.getConfig().getDouble("Classes.Centaur.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".level", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".branch", "default");
			setDisplayName(p);					
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
			ClPk.onlinePlayers.remove(p.getName());
			// addExtensions(p);
			Minotaur cenClass = new Minotaur(p.getName());
			cenClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Centaur.default").replaceAll("&", "§"));
			// newClass.refreshEnergy(2);

			ClPk.onlinePlayers.put(p.getName(), cenClass);
			p.sendMessage(ClPk.prefix + ChatColor.AQUA + "You are now a Minotaur!");
			ClPk.spawnPlayer(p, "Minotaur");
			break;
		case "Ahuizotl":
			classname = "Ahuizotl";
			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".class", classname);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".energy",
					ClPk.advCfg.getConfig().getDouble("Classes.Ahuizotl.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".mana",
					ClPk.advCfg.getConfig().getDouble("Classes.Ahuizotl.maxMana"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".maxEnergy",
					ClPk.advCfg.getConfig().getDouble("Classes.Ahuizotl.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".maxMana",
					ClPk.advCfg.getConfig().getDouble("Classes.Ahuizotl.maxMana"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".flevel", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".mlevel", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".fxp", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".ftime", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".branch", "default");
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".currentSpell", 0);
			setDisplayName(p);					
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
			ClPk.onlinePlayers.remove(p.getName());
			// addExtensions(p);
			Ahuizotl ahuClass = new Ahuizotl(p.getName());
			ahuClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Ahuizotl.default").replaceAll("&", "§"));
			// newClass.refreshEnergy(2);

			ClPk.onlinePlayers.put(p.getName(), ahuClass);
			p.sendMessage(ClPk.prefix + ChatColor.AQUA + "You are now an Ahuizotl!");
			ClPk.spawnPlayer(p, "Ahuizotl");
			break;
		
		case "Chimera":
			classname = "Chimera";
			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".class", classname);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".energy",
					ClPk.advCfg.getConfig().getDouble("Classes.Chimera.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".mana",
					ClPk.advCfg.getConfig().getDouble("Classes.Chimera.maxMana"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".maxEnergy",
					ClPk.advCfg.getConfig().getDouble("Classes.Chimera.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".maxMana",
					ClPk.advCfg.getConfig().getDouble("Classes.Chimera.maxMana"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".flevel", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".mlevel", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".fxp", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".ftime", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".branch", "default");
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".disguises",
					ClPk.advCfg.getConfig().getDouble("Classes.Chimera.disguises.default"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".currentSpell", 0);
			setDisplayName(p);					
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
			ClPk.onlinePlayers.remove(p.getName());
			// addExtensions(p);
			Chimera chiClass = new Chimera(p.getName());
			chiClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Chimera.default").replaceAll("&", "§"));
			ClPk.onlinePlayers.put(p.getName(), chiClass);
			p.sendMessage(ClPk.prefix + ChatColor.AQUA + "You are now a Chimera!");
			ClPk.spawnPlayer(p, "Chimera");
			break;
		case "Draconequus":
			List<String> dcspells = ClPk.BranchSpells.getConfig().getStringList("dc.default.spells");
			// CLASSID 9
			classname = "Draconequus";
			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".class", classname);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".energy",
					ClPk.advCfg.getConfig().getDouble("Classes.Draconequus.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".mana",
					ClPk.advCfg.getConfig().getDouble("Classes.Draconequus.maxMana"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".maxEnergy",
					ClPk.advCfg.getConfig().getDouble("Classes.Draconequus.maxEnergy"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".maxMana",
					ClPk.advCfg.getConfig().getDouble("Classes.Draconequus.maxMana"));
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".flevel", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".mlevel", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".fxp", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".ftime", 0);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".spells", dcspells);
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".branch", "default");
			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".currentSpell", 0);	
			setDisplayName(p);	
			ClPk.playerClass.saveConfig();
			ClPk.playerClass.reloadConfig();
			ClPk.onlinePlayers.remove(p.getName());
			// addExtensions(p);
			Draconequus drcClass = new Draconequus(p.getName());
			drcClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Draconequus.default").replaceAll("&", "§"));
			// newClass.refreshEnergy(2);

			ClPk.onlinePlayers.put(p.getName(), drcClass);
			p.sendMessage(ClPk.prefix + ChatColor.AQUA + "You are now a Draconequus!");
			ClPk.spawnPlayer(p.getPlayer(), "Draconequus");
		}
		ClassBooks.classBooks(p, classname);
		changeSetup(p);

	}

	public static void changeSetup(Player p) {
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

	public static void setDisplayName(Player p) {
		if(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class")!=null){
			if (ClPk.Female.contains(p.getName())){
				p.setPlayerListName(ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
			}else
			if(ClPk.Male.contains(p.getName())){
				p.setPlayerListName(ChatColor.AQUA + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
			}else{
				p.setPlayerListName(ChatColor.WHITE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
			}
		}else{
			if (ClPk.Female.contains(p.getName())){
				p.setPlayerListName(ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET);
			}else
			if(ClPk.Male.contains(p.getName())){
				p.setPlayerListName(ChatColor.AQUA + p.getName() + ChatColor.RESET);
			}else{
				p.setPlayerListName(ChatColor.WHITE + p.getName() + ChatColor.RESET);
			}
				
		}
		
	}
}
