package com.ahui.classpack.util;

import java.io.File;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;


import com.ahui.classpack.ClPk;


public class Upgrade 
{
	public FileConfiguration playerClassOld;
	public void getFile()
	{
		Bukkit.getServer().getConsoleSender().sendMessage("[BkClassPack] To import playerClass config from '"+ChatColor.RED+"P3Pplus"+ChatColor.RESET+"' set '"+ChatColor.RED+"Module.upgrade = true"+ChatColor.RESET+"' inside '"+ChatColor.RED+"config.yml"+ChatColor.RESET+"'");
		if(ClPk.config.getConfig().getBoolean("Module.upgrade"))
		{
			playerClassOld = YamlConfiguration.loadConfiguration(new File("plugins/PonyPackPlus", "playerClass.yml"));
			if(playerClassOld!=null)
			{
				getPlayerData();
			}
		}
	}
	public void getPlayerData()
	{
		for(String key :playerClassOld.getKeys(false))
		{
			String playerName = ChatColor.RED+Bukkit.getOfflinePlayer(UUID.fromString(key)).getName()+ChatColor.RESET;
			Bukkit.getServer().getConsoleSender().sendMessage("[BkClassPack] Found "+ChatColor.RED+key+ChatColor.RESET+" ["+playerName+"]");
			System.out.print("[BkClassPack] Updating playerData...");
			
			String pCs = playerClassOld.getString(key+".class");
			Bukkit.getServer().getConsoleSender().sendMessage("[BkClassPack] playerClass ["+playerName+"] found: "+ChatColor.RED+pCs);
			
			
			switch(pCs.toLowerCase())
			{
			case"unicorn":
				String[] spells = {"flameI","frostI","blink","healI","shadowBeamI"};
				ClPk.playerClass.getConfig().set(key, null);
				ClPk.playerClass.getConfig().set(key+".class", "Unicorn");
				ClPk.playerClass.getConfig().set(key+".mana", 50.0);
				ClPk.playerClass.getConfig().set(key+".maxMana", ClPk.advCfg.getConfig().getDouble("Classes.Unicorn.maxMana"));
				ClPk.playerClass.getConfig().set(key+".level", 0);
				ClPk.playerClass.getConfig().set(key+".branch", "default");
				ClPk.playerClass.getConfig().set(key+".currentSpell", 0);
				ClPk.playerClass.getConfig().set(key+".spells", spells);
				commitData(key);
				break;
			case"pegasus":
				ClPk.playerClass.getConfig().set(key, null);
				ClPk.playerClass.getConfig().set(key+".class", "Pegasus");
				ClPk.playerClass.getConfig().set(key+".energy", 50.0);
				ClPk.playerClass.getConfig().set(key+".maxEnergy", ClPk.advCfg.getConfig().getDouble("Classes.Pegasus.maxEnergy"));
				ClPk.playerClass.getConfig().set(key+".level", 0);
				ClPk.playerClass.getConfig().set(key+".fxp", 0);
				ClPk.playerClass.getConfig().set(key+".ftime", 0);
				commitData(key);
				break;
			case"earth":
				ClPk.playerClass.getConfig().set(key, null);
				ClPk.playerClass.getConfig().set(key+".class", "Earth");
				ClPk.playerClass.getConfig().set(key+".energy", 50.0);
				ClPk.playerClass.getConfig().set(key+".maxEnergy", ClPk.advCfg.getConfig().getDouble("Classes.Earth.maxEnergy"));
				ClPk.playerClass.getConfig().set(key+".level", 0);
				ClPk.playerClass.getConfig().set(key+".fxp", 0);
				ClPk.playerClass.getConfig().set(key+".branch", "default");
				commitData(key);
				break;
			case "alicorn":
				ClPk.playerClass.getConfig().set(key, null);
				ClPk.playerClass.getConfig().set(key+".class", "Alicorn");
				ClPk.playerClass.getConfig().set(key+".energy", ClPk.advCfg.getConfig().getDouble("Classes.Alicorn.maxEnergy"));
				ClPk.playerClass.getConfig().set(key+".mana", ClPk.advCfg.getConfig().getDouble("Classes.Alicorn.maxMana"));
				ClPk.playerClass.getConfig().set(key+".maxEnergy", ClPk.advCfg.getConfig().getDouble("Classes.Alicorn.maxEnergy"));
				ClPk.playerClass.getConfig().set(key+".maxMana", ClPk.advCfg.getConfig().getDouble("Classes.Alicorn.maxMana"));
				ClPk.playerClass.getConfig().set(key+".flevel", 0);
				ClPk.playerClass.getConfig().set(key+".mlevel", 0);
				ClPk.playerClass.getConfig().set(key+".fxp", 0);
				ClPk.playerClass.getConfig().set(key+".ftime", 0);
				ClPk.playerClass.getConfig().set(key+".branch", "default");
				commitData(key);
				break;
			case "changeling":
				ClPk.playerClass.getConfig().set(key, null);
				ClPk.playerClass.getConfig().set(key+".class", "Changeling");
				ClPk.playerClass.getConfig().set(key+".energy", ClPk.advCfg.getConfig().getDouble("Classes.Changeling.maxEnergy"));
				ClPk.playerClass.getConfig().set(key+".mana", ClPk.advCfg.getConfig().getDouble("Classes.Changeling.maxMana"));
				ClPk.playerClass.getConfig().set(key+".maxEnergy", ClPk.advCfg.getConfig().getDouble("Classes.Changeling.maxEnergy"));
				ClPk.playerClass.getConfig().set(key+".maxMana", ClPk.advCfg.getConfig().getDouble("Classes.Changeling.maxMana"));
				ClPk.playerClass.getConfig().set(key+".flevel", 0);
				ClPk.playerClass.getConfig().set(key+".mlevel", 0);
				ClPk.playerClass.getConfig().set(key+".fxp", 0);
				ClPk.playerClass.getConfig().set(key+".ftime", 0);
				ClPk.playerClass.getConfig().set(key+".branch", "default");
				ClPk.playerClass.getConfig().set(key+".disguises",ClPk.advCfg.getConfig().getDouble("Classes.Changeling.disguises.default"));
				commitData(key);
				break;
			default:
				System.out.print("[BkClassPack] playerClass ["+pCs+"] does not exist! skipping...");
				break;
			}
		}
		Bukkit.getServer().getConsoleSender().sendMessage("[BkClassPack]"+ChatColor.RED+" Upgrade complete, disabling for future startups");
		ClPk.config.getConfig().set("Module.upgrade", false);
		ClPk.config.saveConfig();
	}

	public void commitData(String key)
	{
		String playerName = ChatColor.RED+Bukkit.getOfflinePlayer(UUID.fromString(key)).getName()+ChatColor.RESET;
		Bukkit.getServer().getConsoleSender().sendMessage("[BkClassPack] Saving playerData for ["+playerName+"]");
		ClPk.playerClass.saveConfig();
		ClPk.playerClass.reloadConfig();
		Bukkit.getServer().getConsoleSender().sendMessage("[BkClassPack] playerData for ["+playerName+"] upgraded successfully");
	}

}
