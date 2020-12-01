package com.ahui.gender.DataSave;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import com.ahui.classpack.ClPk;

public class PlayerJoinListener implements Listener
{
	
		private ClPk plugin;
		
		public PlayerJoinListener(ClPk plugin)
		{
			this.plugin = plugin;
		}
		
		@EventHandler(priority = EventPriority.NORMAL , ignoreCancelled = true )
		public void onPlayerLogin(PlayerLoginEvent event)
		{
			String playerName = event.getPlayer().getName();
			Player p = event.getPlayer();
			System.out.println("DEBUG: FOUND JOIN EVENT " + playerName);
			
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
