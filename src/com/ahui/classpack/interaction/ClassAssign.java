package com.ahui.classpack.interaction;

import java.util.List;

import org.bukkit.Bukkit;
//import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
//import org.bukkit.scoreboard.DisplaySlot;

import com.ahui.classpack.ClPk;
//import com.ahui.classpack.classes.Ahuizotl;
import com.ahui.classpack.classes.Alicorn;
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
import com.ahui.classpack.classes.Breezy;
//import com.ahui.classpack.classes.Minotaur;
import com.ahui.classpack.classes.interfaces.ClassBase;
import com.ahui.classpack.classes.interfaces.EnergyUser;
import com.ahui.classpack.classes.interfaces.Flier;
import com.ahui.classpack.classes.interfaces.MagicUser;
import com.ahui.classpack.util.ClassBooks;
import com.ahui.classpack.util.ClassSeter;

public class ClassAssign implements Listener {	
	@EventHandler
	public void selectClass(PlayerInteractEvent e){
//		String classname = "";
		if ((e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&&((e.getClickedBlock().getType() == Material.WALL_SIGN) 
						|| (e.getClickedBlock().getType() == Material.SIGN_POST))){
			String sign = ((Sign)e.getClickedBlock().getState()).getLine(ClPk.advCfg.getConfig().getInt("SignText.SignTextLine"));
			Player p = e.getPlayer();
			for(String Classes : ClPk.classNames){
				if(Classes != null){
					if (sign.equals(ClPk.advCfg.getConfig().getString("SignText."+Classes))){
						//						if(ClPk.config.getConfig().getStringList("vip").contains(sign.replace("[", "").replace("]", ""))
//								&&!p.hasPermission("acp.vip"))
//						{
//							e.getPlayer().sendMessage(ClPk.prefix+" You must be VIP to use this class.");
//							return;
//
//						}else 
						if((!ClPk.config.getConfig().getStringList("vip").contains(sign.replace("[", "").replace("]", "")))
								||p.hasPermission("acp.vip")){
							
								ClassSeter.setClass(p, Classes);
							
						}
					}
				}
			}
		}
	}
		
			
				
					
						

							


	public static Player getPlayer() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			
				return p;
			
		}
		return null;
	}
	public void ClrPtnEfts(Player p) {
		p.getPlayer().removePotionEffect(PotionEffectType.ABSORPTION);
		p.getPlayer().removePotionEffect(PotionEffectType.BLINDNESS);
		p.getPlayer().removePotionEffect(PotionEffectType.CONFUSION);
		p.getPlayer().removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
		p.getPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
		p.getPlayer().removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
		p.getPlayer().removePotionEffect(PotionEffectType.GLOWING);
		p.getPlayer().removePotionEffect(PotionEffectType.HARM);
		p.getPlayer().removePotionEffect(PotionEffectType.HEAL);
		p.getPlayer().removePotionEffect(PotionEffectType.HEALTH_BOOST);
		p.getPlayer().removePotionEffect(PotionEffectType.HUNGER);
		p.getPlayer().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
		p.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
		p.getPlayer().removePotionEffect(PotionEffectType.JUMP);
		p.getPlayer().removePotionEffect(PotionEffectType.LEVITATION);
		p.getPlayer().removePotionEffect(PotionEffectType.LUCK);
		p.getPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION);
		p.getPlayer().removePotionEffect(PotionEffectType.POISON);
		p.getPlayer().removePotionEffect(PotionEffectType.REGENERATION);
		p.getPlayer().removePotionEffect(PotionEffectType.SATURATION);
		p.getPlayer().removePotionEffect(PotionEffectType.SLOW);
		p.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);
		p.getPlayer().removePotionEffect(PotionEffectType.SPEED);
		p.getPlayer().removePotionEffect(PotionEffectType.UNLUCK);
		p.getPlayer().removePotionEffect(PotionEffectType.WATER_BREATHING);
		p.getPlayer().removePotionEffect(PotionEffectType.WEAKNESS);
		p.getPlayer().removePotionEffect(PotionEffectType.WITHER);
		if (ClPk.playerClass.getConfig().getString(p.getPlayer().getUniqueId()+".class") != "Thestral"){
			p.removePotionEffect(PotionEffectType.NIGHT_VISION);
		}
		if (ClPk.playerClass.getConfig().getString(p.getPlayer().getUniqueId()+".class") != "TimberWolf"){
			p.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
			p.getPlayer().removePotionEffect(PotionEffectType.SPEED);
		}
	}
	
	public void clearGuild(Player p) {
		// Clear from timberWolf packs
		try {
			for (String gid : ClPk.guilds_packs.getConfig().getConfigurationSection("Twol").getKeys(false)) {
				if (!gid.equalsIgnoreCase("null") && gid != null) {
					String leader = ClPk.guilds_packs.getConfig().getString("Twol." + gid + ".leader");
					if (leader.equals(p.getUniqueId().toString())) {
						for (OfflinePlayer op : Bukkit.getOfflinePlayers()) {
							if (ClPk.playerClass.getConfig().contains(op.getUniqueId().toString())) {
								if (ClPk.playerClass.getConfig().getString(op.getUniqueId().toString() + ".class")
										.equalsIgnoreCase("TimberWolf")) {
									if (ClPk.playerClass.getConfig().getString(op.getUniqueId().toString() + ".pack")
											.equalsIgnoreCase(gid)) {
										ClPk.playerClass.getConfig().set(op.getUniqueId().toString() + ".pack", "null");
										ClPk.playerClass.getConfig().set(op.getUniqueId().toString() + ".packPref", "");
										ClPk.playerClass.saveConfig();
										ClPk.playerClass.reloadConfig();
									}
								}
							}
						}
						ClPk.guilds_packs.getConfig().set("Twol." + gid, null);
						ClPk.guilds_packs.saveConfig();
						ClPk.guilds_packs.reloadConfig();
					}
					if (ClPk.guilds_packs.getConfig().getStringList("Twol." + gid + ".members")
							.contains(p.getUniqueId().toString())) {
						List<String> uidList = ClPk.guilds_packs.getConfig().getStringList("Twol." + gid + ".members");
						uidList.remove(p.getUniqueId().toString());
						ClPk.guilds_packs.getConfig().set("Twol." + gid + ".members", uidList);
						ClPk.guilds_packs.saveConfig();
						ClPk.guilds_packs.reloadConfig();
					}
				}
			}
		} catch (NullPointerException e) {
			System.out.print("acp>clearGuild:" + e.getCause());
		}
	}


	/**
	 * onClassChange fix any flight issues
	 * 
	 * @param p
	 *            player to edit
	 */
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
				p.getPlayer().setAllowFlight(true);
			}
			if (ClPk.onlinePlayers.get(p.getName()) instanceof Dragon) {
				p.getLocation().getWorld().dropItem(p.getLocation(), new ItemStack(Material.BLAZE_ROD, 1));
				p.getPlayer().setAllowFlight(true);
			}
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		addPlayer(p);
		if (!e.getPlayer().hasPlayedBefore()) {
			ClPk.spawnPlayer(e.getPlayer(), "");
		}
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		// Player p = e.getPlayer();
		if (ClPk.onlinePlayers.containsKey(e.getPlayer().getName())) {
			if (ClPk.onlinePlayers.get(e.getPlayer().getName()) instanceof Flier) {
				((Flier) ClPk.onlinePlayers.get(e.getPlayer().getName())).stop();
				ClPk.onlinePlayers.remove(e.getPlayer().getName());
				return;
			}
			if (ClPk.onlinePlayers.get(e.getPlayer().getName()) instanceof EnergyUser) {
				((EnergyUser) ClPk.onlinePlayers.get(e.getPlayer().getName())).stop();
				ClPk.onlinePlayers.remove(e.getPlayer().getName());
				return;
			}
			if (ClPk.onlinePlayers.get(e.getPlayer().getName()) instanceof MagicUser) {
				((MagicUser) ClPk.onlinePlayers.get(e.getPlayer().getName())).stop();
				ClPk.onlinePlayers.remove(e.getPlayer().getName());
				return;
			}
		}
	}

	public static void addPlayer(Player p) {
		
		String classname = ClPk.playerClass.getConfig().getString(p.getPlayer().getUniqueId().toString() + ".class");
		
		if (classname == null) {
			ClassBase cb = new ClassBase(p.getName());
			ClPk.onlinePlayers.put(p.getName(), cb);
			return;
		}
		if (!ClPk.playerClass.getConfig().contains(p.getUniqueId().toString() + ".book")
				|| !ClPk.playerClass.getConfig().getBoolean(p.getUniqueId().toString() + ".book")) {
			ClassBooks.classBooks(p, ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"));
		}
		switch (classname) {
		case "Unicorn":
			Unicorn uc = new Unicorn(p.getName());
			ClPk.onlinePlayers.put(p.getName(), uc);
			uc.refreshMana(1);
			p.setAllowFlight(false);
			break;
		case "Pegasus":
			Pegasus peg = new Pegasus(p.getName());
			ClPk.onlinePlayers.put(p.getName(), peg);
			peg.refreshEnergy(2);
			peg.isGlide();
			p.setAllowFlight(true);
			break;
		case "Thestral":
			Thestral the = new Thestral(p.getName());
			ClPk.onlinePlayers.put(p.getName(), the);
			the.refreshEnergy(15);
			the.isGlide();
			p.setAllowFlight(true);
			break;
		case "Breezy":
			Breezy bre = new Breezy(p.getName());
			ClPk.onlinePlayers.put(p.getName(), bre);
			bre.refreshEnergy(11);
			bre.isGlide();
			p.setAllowFlight(true);
			break;
		case "Earth":
			Earth ec = new Earth(p.getName());
			ClPk.onlinePlayers.put(p.getName(), ec);
			ec.refreshEnergy(3);
			p.setAllowFlight(false);
			break;
		case "Zebra":
			Zebra zeb = new Zebra(p.getName());
			ClPk.onlinePlayers.put(p.getName(),zeb);
			p.setAllowFlight(false);
			break;
		case "DiamondDog":
			DiamondDog ddg = new DiamondDog(p.getName());
			ClPk.onlinePlayers.put(p.getName(), ddg);
			ddg.refreshEnergy(12);
			p.setAllowFlight(false);
			break;
		case "Alicorn":
			Alicorn ac = new Alicorn(p.getName());
			ClPk.onlinePlayers.put(p.getName(), ac);
			ac.refreshEnergy(4);
			ac.refreshMana(4);
			ac.isGlide();
			p.setAllowFlight(true);
			break;
		case "Changeling":
			if (ClPk.DisguiseLibsEnabled == true) {
				Changeling ch = new Changeling(p.getName());
				ClPk.onlinePlayers.put(p.getName(), ch);
				ch.refreshEnergy(5);
				ch.refreshMana(5);
				ch.isGlide();
				p.setAllowFlight(true);
				break;
			} else {
				p.sendMessage(ClPk.prefix + "Changeling is disabled, libs disguises not found.");
				ClassBase cb = new ClassBase(p.getName());
				ClPk.onlinePlayers.put(p.getName(), cb);
				// setTo defaultClass
			}
			break;
		case "Chimera":
			if (ClPk.DisguiseLibsEnabled == true) {
				Chimera chi = new Chimera(p.getName());
				ClPk.onlinePlayers.put(p.getName(), chi);
				chi.refreshEnergy(5);
				chi.refreshMana(5);
				chi.isGlide();
				p.setAllowFlight(true);		
				break;
			} else {
				p.sendMessage(ClPk.prefix + "Chimera is disabled, libs disguises not found.");
				ClassBase cb = new ClassBase(p.getName());
				ClPk.onlinePlayers.put(p.getName(), cb);
				// setTo defaultClass
			}
			break;
		case "Dragon":
			Dragon dg = new Dragon(p.getName());
			ClPk.onlinePlayers.put(p.getName(), dg);
			dg.refreshEnergy(6);
			dg.isGlide();
			p.setAllowFlight(true);
			break;
		case "TimberWolf":
			TimberWolf tw = new TimberWolf(p.getName());
			ClPk.onlinePlayers.put(p.getName(), tw);
			tw.refreshEnergy(7);
			p.setAllowFlight(false);
			break;
		case "Griffon":
			Griffon grif = new Griffon(p.getName());
			ClPk.onlinePlayers.put(p.getName(), grif);
			grif.refreshEnergy(8);
			grif.isGlide();
			p.setAllowFlight(true);
			break;
		case "Draconequus":
			Draconequus dc = new Draconequus(p.getName());
			ClPk.onlinePlayers.put(p.getName(), dc);
			dc.refreshEnergy(9);
			dc.refreshMana(9);
			dc.isGlide();
			p.setAllowFlight(true);
			break;
		default:
			ClassBase cb = new ClassBase(p.getName());
			ClPk.onlinePlayers.put(p.getName(), cb);
			break;
		}
	}
}
