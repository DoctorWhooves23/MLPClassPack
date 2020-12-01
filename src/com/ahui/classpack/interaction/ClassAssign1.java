package com.ahui.classpack.interaction;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
import org.bukkit.scoreboard.DisplaySlot;

import com.ahui.classpack.ClPk;
import com.ahui.classpack.classes.Ahuizotl;
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
import com.ahui.classpack.classes.Minotaur;
import com.ahui.classpack.classes.interfaces.ClassBase;
import com.ahui.classpack.classes.interfaces.EnergyUser;
import com.ahui.classpack.classes.interfaces.Flier;
import com.ahui.classpack.classes.interfaces.MagicUser;
import com.ahui.classpack.util.ClassBooks;

public class ClassAssign1 implements Listener {	
	@EventHandler
	public void selectClass(PlayerInteractEvent e)
	{
		String classname = "";
		if ((e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&&((e.getClickedBlock().getType() == Material.WALL_SIGN) 
						|| (e.getClickedBlock().getType() == Material.SIGN_POST)))
		{
			String sign = ((Sign)e.getClickedBlock().getState()).getLine(ClPk.advCfg.getConfig().getInt("SignText.SignTextLine"));
			Player p = e.getPlayer();
			for(String Classes : ClPk.classNames){
				if(Classes != null){
					if (sign.equals(ClPk.advCfg.getConfig().getString("SignText."+Classes))){
						if(ClPk.config.getConfig().getStringList("vip").contains(sign.replace("[", "").replace("]", ""))
								&&!p.hasPermission("acp.vip"))
						{
							e.getPlayer().sendMessage(ClPk.prefix+" You must be VIP to use this class.");
							return;

						}else if((!ClPk.config.getConfig().getStringList("vip").contains(sign.replace("[", "").replace("]", "")))
								||p.hasPermission("acp.vip"))
						{
							if (sign.equals(ClPk.advCfg.getConfig().getString("SignText.Unicorn"))) 
							{
								if (ClPk.config.getConfig().getBoolean("Class-Options.Unicorn-Enabled"))
								{
									String[] spells = {"flameI","frostI","blink","healI","shadowBeamI"};
									//CLASSID 1
									classname = "Unicorn";

									p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
									ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
									ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
									ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
									ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".mana", ClPk.advCfg.getConfig().getDouble("Classes.Unicorn.maxMana"));
									ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".maxMana", ClPk.advCfg.getConfig().getDouble("Classes.Unicorn.maxMana"));
									ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".level", 0);
									ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".branch", "default");
									ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".currentSpell", 0);
									ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".spells", spells);
									ClPk.playerClass.saveConfig();
									ClPk.playerClass.reloadConfig();
									ClPk.onlinePlayers.remove(e.getPlayer().getName());
									Unicorn newClass = new Unicorn(e.getPlayer().getName());
									newClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Unicorn.default").replaceAll("&", "§"));
									//newClass.refreshMana(1);
									ClPk.onlinePlayers.put(e.getPlayer().getName(), newClass);	
									e.getPlayer().sendMessage(ClPk.prefix+ChatColor.AQUA + "You are now a Unicorn!");
									ClPk.spawnPlayer(e.getPlayer(), "Unicorn");
									if (ClPk.Female.contains(p.getName())){
										p.setPlayerListName(ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
									}else
										if(ClPk.Male.contains(p.getName())){
											p.setPlayerListName(ChatColor.AQUA + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
										}else{
											p.setPlayerListName(ChatColor.WHITE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
										}
									setDisplayName(p);

								}
							}else
								if (sign.equals(ClPk.advCfg.getConfig().getString("SignText.Pegasus"))) 
								{
									if (ClPk.config.getConfig().getBoolean("Class-Options.Pegasus-Enabled"))
									{
										//CLASSID 2
										classname = "Pegasus";
										p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
										ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
										ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
										ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
										ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".energy", ClPk.advCfg.getConfig().getDouble("Classes.Pegasus.maxEnergy"));
										ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".maxEnergy", ClPk.advCfg.getConfig().getDouble("Classes.Pegasus.maxEnergy"));
										ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".level", 0);
										ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".fxp", 0);
										ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".ftime", 0);
										ClPk.playerClass.saveConfig();
										ClPk.playerClass.reloadConfig();
										ClPk.onlinePlayers.remove(e.getPlayer().getName());
										Pegasus newClass = new Pegasus(e.getPlayer().getName());
										newClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Pegasus.default").replaceAll("&", "§"));
										//newClass.refreshEnergy(2);

										ClPk.onlinePlayers.put(e.getPlayer().getName(), newClass);	
										e.getPlayer().sendMessage(ClPk.prefix+ChatColor.AQUA + "You are now a Pegasus!");
										ClPk.spawnPlayer(e.getPlayer(), "Pegasus");
										if (ClPk.Female.contains(p.getName())){
											p.setPlayerListName(ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
										}else
											if(ClPk.Male.contains(p.getName())){
												p.setPlayerListName(ChatColor.AQUA + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
											}else{
												p.setPlayerListName(ChatColor.WHITE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
											}

									}
								}else
									if (sign.equals(ClPk.advCfg.getConfig().getString("SignText.Thestral"))) 
									{
										if (ClPk.config.getConfig().getBoolean("Class-Options.Thestral-Enabled"))
										{
											//CLASSID 2
											classname = "Thestral";
											p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
											ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
											ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
											ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
											ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".energy", ClPk.advCfg.getConfig().getDouble("Classes.Thestral.maxEnergy"));
											ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".maxEnergy", ClPk.advCfg.getConfig().getDouble("Classes.Thestral.maxEnergy"));
											ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".level", 0);
											ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".fxp", 0);
											ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".ftime", 0);
											ClPk.playerClass.saveConfig();
											ClPk.playerClass.reloadConfig();
											ClPk.onlinePlayers.remove(e.getPlayer().getName());
											Thestral newClass = new Thestral(e.getPlayer().getName());
											newClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Thestral.default").replaceAll("&", "§"));
											//newClass.refreshEnergy(2);

											ClPk.onlinePlayers.put(e.getPlayer().getName(), newClass);	
											e.getPlayer().sendMessage(ClPk.prefix+ChatColor.AQUA + "You are now a Thestral!");
											ClPk.spawnPlayer(e.getPlayer(), "Thestral");
											if (ClPk.Female.contains(p.getName())){
												p.setPlayerListName(ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
											}else
												if(ClPk.Male.contains(p.getName())){
													p.setPlayerListName(ChatColor.AQUA + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
												}else{
													p.setPlayerListName(ChatColor.WHITE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
												}

										}
									}else
										if (sign.equals(ClPk.advCfg.getConfig().getString("SignText.Earth"))) 
										{
											if (ClPk.config.getConfig().getBoolean("Class-Options.Earth-Enabled"))
											{
												//CLASSID 3
												classname = "Earth";
												p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
												ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
												ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
												ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
												ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".energy", ClPk.advCfg.getConfig().getDouble("Classes.Earth.maxEnergy"));
												ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".maxEnergy", ClPk.advCfg.getConfig().getDouble("Classes.Earth.maxEnergy"));
												ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".level", 0);
												ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".branch", "default");
												ClPk.playerClass.saveConfig();
												ClPk.playerClass.reloadConfig();
												ClPk.onlinePlayers.remove(e.getPlayer().getName());

												Earth newClass = new Earth(e.getPlayer().getName());
												newClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Earth.default").replaceAll("&", "§"));
												//newClass.refreshEnergy(2);

												ClPk.onlinePlayers.put(e.getPlayer().getName(), newClass);	
												e.getPlayer().sendMessage(ClPk.prefix+ChatColor.AQUA + "You are now an Earth pony!");
												ClPk.spawnPlayer(e.getPlayer(), "Earth");
												if (ClPk.Female.contains(p.getName())){
													p.setPlayerListName(ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
												}else
													if(ClPk.Male.contains(p.getName())){
														p.setPlayerListName(ChatColor.AQUA + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
													}else{
														p.setPlayerListName(ChatColor.WHITE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
													}

											}
										}else
											if (sign.equals(ClPk.advCfg.getConfig().getString("SignText.Alicorn"))) 
											{
												if (ClPk.config.getConfig().getBoolean("Class-Options.Alicorn-Enabled"))
												{
													//CLASSID 4
													classname = "Alicorn";
													p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
													ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
													ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
													ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
													ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".energy", ClPk.advCfg.getConfig().getDouble("Classes.Alicorn.maxEnergy"));
													ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".mana", ClPk.advCfg.getConfig().getDouble("Classes.Alicorn.maxMana"));
													ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".maxEnergy", ClPk.advCfg.getConfig().getDouble("Classes.Alicorn.maxEnergy"));
													ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".maxMana", ClPk.advCfg.getConfig().getDouble("Classes.Alicorn.maxMana"));
													ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".flevel", 0);
													ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".mlevel", 0);
													ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".fxp", 0);
													ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".ftime", 0);
													ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".branch", "default");
													ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".currentSpell", 0);
													ClPk.playerClass.saveConfig();
													ClPk.playerClass.reloadConfig();
													ClPk.onlinePlayers.remove(e.getPlayer().getName());

													Alicorn newClass = new Alicorn(e.getPlayer().getName());
													newClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Alicorn.default").replaceAll("&", "§"));
													//newClass.refreshEnergy(2);

													ClPk.onlinePlayers.put(e.getPlayer().getName(), newClass);	
													e.getPlayer().sendMessage(ClPk.prefix+ChatColor.AQUA + "You are now an Alicorn!");
													ClPk.spawnPlayer(e.getPlayer(), "Alicorn");
													if (ClPk.Female.contains(p.getName())){
														p.setPlayerListName(ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
													}else
														if(ClPk.Male.contains(p.getName())){
															p.setPlayerListName(ChatColor.AQUA + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
														}else{
															p.setPlayerListName(ChatColor.WHITE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
														}

												}
											}else
												if (sign.equals(ClPk.advCfg.getConfig().getString("SignText.Ahuizotl"))) 
												{
													if (ClPk.config.getConfig().getBoolean("Class-Options.Ahuizotl-Enabled"))
													{
														//CLASSID 4
														classname = "Ahuizotl";
														p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
														ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
														ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
														ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
														ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".energy", ClPk.advCfg.getConfig().getDouble("Classes.Ahuizotl.maxEnergy"));
														ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".mana", ClPk.advCfg.getConfig().getDouble("Classes.Ahuizotl.maxMana"));
														ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".maxEnergy", ClPk.advCfg.getConfig().getDouble("Classes.Ahuizotl.maxEnergy"));
														ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".maxMana", ClPk.advCfg.getConfig().getDouble("Classes.Ahuizotl.maxMana"));
														ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".flevel", 0);
														ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".mlevel", 0);
														ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".fxp", 0);
														ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".ftime", 0);
														ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".branch", "default");
														ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".currentSpell", 0);				
														ClPk.playerClass.saveConfig();
														ClPk.playerClass.reloadConfig();
														ClPk.onlinePlayers.remove(e.getPlayer().getName());

														Ahuizotl newClass = new Ahuizotl(e.getPlayer().getName());
														newClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Ahuizotl.default").replaceAll("&", "§"));
														//newClass.refreshEnergy(2);

														ClPk.onlinePlayers.put(e.getPlayer().getName(), newClass);	
														e.getPlayer().sendMessage(ClPk.prefix+ChatColor.AQUA + "You are now an Ahuizotl!");
														ClPk.spawnPlayer(e.getPlayer(), "Ahuizotl");
														if (ClPk.Female.contains(p.getName())){
															p.setPlayerListName(ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
														}else
															if(ClPk.Male.contains(p.getName())){
																p.setPlayerListName(ChatColor.AQUA + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
															}else{
																p.setPlayerListName(ChatColor.WHITE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
															}

													}
												}else
													if (sign.equals(ClPk.advCfg.getConfig().getString("SignText.Changeling"))) 
													{
														if (ClPk.DisguiseLibsEnabled==true&&ClPk.config.getConfig().getBoolean("Class-Options.Changeling-Enabled"))
														{
															//CLASSID 5
															classname = "Changeling";
															p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
															ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
															ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
															ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
															ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".energy", ClPk.advCfg.getConfig().getDouble("Classes.Changeling.maxEnergy"));
															ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".mana", ClPk.advCfg.getConfig().getDouble("Classes.Changeling.maxMana"));
															ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".maxEnergy", ClPk.advCfg.getConfig().getDouble("Classes.Changeling.maxEnergy"));
															ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".maxMana", ClPk.advCfg.getConfig().getDouble("Classes.Changeling.maxMana"));
															ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".flevel", 0);
															ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".mlevel", 0);
															ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".fxp", 0);
															ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".ftime", 0);
															ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".branch", "default");
															ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".disguises",ClPk.advCfg.getConfig().getDouble("Classes.Changeling.disguises.default"));
															ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".currentSpell", 0);
															ClPk.playerClass.saveConfig();
															ClPk.playerClass.reloadConfig();
															ClPk.onlinePlayers.remove(e.getPlayer().getName());

															Changeling newClass = new Changeling(e.getPlayer().getName());
															newClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Changeling.default").replaceAll("&", "§"));
															ClPk.onlinePlayers.put(e.getPlayer().getName(), newClass);	
															e.getPlayer().sendMessage(ClPk.prefix+ChatColor.AQUA + "You are now a Changeling!");
															ClPk.spawnPlayer(e.getPlayer(), "Changeling");
															if (ClPk.Female.contains(p.getName())){
																p.setPlayerListName(ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
															}else
																if(ClPk.Male.contains(p.getName())){
																	p.setPlayerListName(ChatColor.AQUA + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																}else{
																	p.setPlayerListName(ChatColor.WHITE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																}

														}
													}else
														if (sign.equals(ClPk.advCfg.getConfig().getString("SignText.Chimera"))) 
														{
															if (ClPk.DisguiseLibsEnabled==true&&ClPk.config.getConfig().getBoolean("Class-Options.Chimera-Enabled"))
															{
																//CLASSID 5
																classname = "Chimera";
																p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
																ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
																ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
																ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
																ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".energy", ClPk.advCfg.getConfig().getDouble("Classes.Chimera.maxEnergy"));
																ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".mana", ClPk.advCfg.getConfig().getDouble("Classes.Chimera.maxMana"));
																ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".maxEnergy", ClPk.advCfg.getConfig().getDouble("Classes.Chimera.maxEnergy"));
																ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".maxMana", ClPk.advCfg.getConfig().getDouble("Classes.Chimera.maxMana"));
																ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".flevel", 0);
																ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".mlevel", 0);
																ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".fxp", 0);
																ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".ftime", 0);
																ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".branch", "default");
																ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".disguises",ClPk.advCfg.getConfig().getDouble("Classes.Chimera.disguises.default"));
																ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".currentSpell", 0);
																ClPk.playerClass.saveConfig();
																ClPk.playerClass.reloadConfig();
																ClPk.onlinePlayers.remove(e.getPlayer().getName());

																Chimera newClass = new Chimera(e.getPlayer().getName());
																newClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Chimera.default").replaceAll("&", "§"));
																ClPk.onlinePlayers.put(e.getPlayer().getName(), newClass);	
																e.getPlayer().sendMessage(ClPk.prefix+ChatColor.AQUA + "You are now a Chimera!");
																ClPk.spawnPlayer(e.getPlayer(), "Chimera");
																if (ClPk.Female.contains(p.getName())){
																	p.setPlayerListName(ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																}else
																	if(ClPk.Male.contains(p.getName())){
																		p.setPlayerListName(ChatColor.AQUA + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																	}else{
																		p.setPlayerListName(ChatColor.WHITE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																	}

															}
														}else
															if (sign.equals(ClPk.advCfg.getConfig().getString("SignText.Dragon"))) 
															{
																if (ClPk.config.getConfig().getBoolean("Class-Options.Dragon-Enabled"))
																{
																	//classID 6
																	classname = "Dragon";
																	p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);	
																	ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
																	ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
																	ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
																	ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".energy", ClPk.advCfg.getConfig().getDouble("Classes.Dragon.maxEnergy"));
																	ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".maxEnergy", ClPk.advCfg.getConfig().getDouble("Classes.Dragon.maxEnergy"));
																	ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".fxp", 0);
																	ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".ftime", 0);
																	ClPk.playerClass.saveConfig();
																	ClPk.playerClass.reloadConfig();
																	ClPk.onlinePlayers.remove(e.getPlayer().getName());

																	Dragon newClass = new Dragon(e.getPlayer().getName());
																	newClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Dragon.default").replaceAll("&", "§"));
																	ClPk.onlinePlayers.put(e.getPlayer().getName(), newClass);	
																	e.getPlayer().sendMessage(ClPk.prefix+ChatColor.AQUA + "You are now a Dragon!");
																	ClPk.spawnPlayer(e.getPlayer(), "Dragon");
																	if (ClPk.Female.contains(p.getName())){
																		p.setPlayerListName(ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																	}else
																		if(ClPk.Male.contains(p.getName())){
																			p.setPlayerListName(ChatColor.AQUA + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																		}else{
																			p.setPlayerListName(ChatColor.WHITE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																		}

																}
															}else
																if (sign.equals(ClPk.advCfg.getConfig().getString("SignText.DiamondDog"))) 
																{
																	if (ClPk.config.getConfig().getBoolean("Class-Options.DiamondDog-Enabled"))
																	{
																		//CLASSID 3
																		classname = "DiamondDog";
																		p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
																		ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
																		ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
																		ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
																		ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".energy", ClPk.advCfg.getConfig().getDouble("Classes.DiamondDog.maxEnergy"));
																		ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".maxEnergy", ClPk.advCfg.getConfig().getDouble("Classes.DiamondDog.maxEnergy"));
																		ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".level", 0);
																		ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".branch", "default");
																		ClPk.playerClass.saveConfig();
																		ClPk.playerClass.reloadConfig();
																		ClPk.onlinePlayers.remove(e.getPlayer().getName());

																		DiamondDog newClass = new DiamondDog(e.getPlayer().getName());
																		newClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.DiamondDog.default").replaceAll("&", "§"));
																		//newClass.refreshEnergy(2);

																		ClPk.onlinePlayers.put(e.getPlayer().getName(), newClass);	
																		e.getPlayer().sendMessage(ClPk.prefix+ChatColor.AQUA + "You are now a DiamondDog!");
																		ClPk.spawnPlayer(e.getPlayer(), "DiamondDog");
																		if (ClPk.Female.contains(p.getName())){
																			p.setPlayerListName(ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																		}else
																			if(ClPk.Male.contains(p.getName())){
																				p.setPlayerListName(ChatColor.AQUA + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																			}else{
																				p.setPlayerListName(ChatColor.WHITE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																			}

																	}
																}else
																	if (sign.equals(ClPk.advCfg.getConfig().getString("SignText.Centaur"))) 
																	{
																		if (ClPk.config.getConfig().getBoolean("Class-Options.Centaur-Enabled"))
																		{
																			//CLASSID 3
																			classname = "Minotaur";
																			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
																			ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
																			ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
																			ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
																			ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".energy", ClPk.advCfg.getConfig().getDouble("Classes.Centaur.maxEnergy"));
																			ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".maxEnergy", ClPk.advCfg.getConfig().getDouble("Classes.Centaur.maxEnergy"));
																			ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".level", 0);
																			ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".branch", "default");
																			ClPk.playerClass.saveConfig();
																			ClPk.playerClass.reloadConfig();
																			ClPk.onlinePlayers.remove(e.getPlayer().getName());

																			Minotaur newClass = new Minotaur(e.getPlayer().getName());
																			newClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Centaur.default").replaceAll("&", "§"));
																			//newClass.refreshEnergy(2);

																			ClPk.onlinePlayers.put(e.getPlayer().getName(), newClass);	
																			e.getPlayer().sendMessage(ClPk.prefix+ChatColor.AQUA + "You are now a Minotaur!");
																			ClPk.spawnPlayer(e.getPlayer(), "Minotaur");
																			if (ClPk.Female.contains(p.getName())){
																				p.setPlayerListName(ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																			}else
																				if(ClPk.Male.contains(p.getName())){
																					p.setPlayerListName(ChatColor.AQUA + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																				}else{
																					p.setPlayerListName(ChatColor.WHITE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																				}

																		}
																	}else
																		if (sign.equals(ClPk.advCfg.getConfig().getString("SignText.TimberWolf"))) 
																		{
																			if (ClPk.config.getConfig().getBoolean("Class-Options.TimberWolf-Enabled"))
																			{
																				//classID 6
																				classname = "TimberWolf";
																				p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);	
																				ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
																				ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
																				ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
																				ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".energy", ClPk.advCfg.getConfig().getDouble("Classes.TimberWolf.maxEnergy"));
																				ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".maxEnergy", ClPk.advCfg.getConfig().getDouble("Classes.TimberWolf.maxEnergy"));
																				ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".level", 0);
																				ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".pack", "null");
																				ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".packPref", "");
																				ClPk.playerClass.saveConfig();
																				ClPk.playerClass.reloadConfig();
																				ClPk.onlinePlayers.remove(e.getPlayer().getName());

																				TimberWolf newClass = new TimberWolf(e.getPlayer().getName());
																				newClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.TimberWolf.default").replaceAll("&", "§"));
																				ClPk.onlinePlayers.put(e.getPlayer().getName(), newClass);	
																				e.getPlayer().sendMessage(ClPk.prefix+ChatColor.AQUA + "You are now a TimberWolf!");
																				ClPk.spawnPlayer(e.getPlayer(), "TimberWolf");
																				if (ClPk.Female.contains(p.getName())){
																					p.setPlayerListName(ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																				}else
																					if(ClPk.Male.contains(p.getName())){
																						p.setPlayerListName(ChatColor.AQUA + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																					}else{
																						p.setPlayerListName(ChatColor.WHITE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																					}

																			}
																		}else
																			if (sign.equals(ClPk.advCfg.getConfig().getString("SignText.Griffon"))) 
																			{
																				if (ClPk.config.getConfig().getBoolean("Class-Options.Griffon-Enabled"))
																				{
																					//CLASSID 2
																					classname = "Griffon";
																					p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
																					ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
																					ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
																					ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
																					ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".energy", ClPk.advCfg.getConfig().getDouble("Classes.Griffon.maxEnergy"));
																					ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".maxEnergy", ClPk.advCfg.getConfig().getDouble("Classes.Griffon.maxEnergy"));
																					ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".level", 0);
																					ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".fxp", 0);
																					ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".ftime", 0);
																					ClPk.playerClass.saveConfig();
																					ClPk.playerClass.reloadConfig();
																					ClPk.onlinePlayers.remove(e.getPlayer().getName());						
																					Griffon newClass = new Griffon(e.getPlayer().getName());
																					newClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Griffon.default").replaceAll("&", "§"));
																					ClPk.onlinePlayers.put(e.getPlayer().getName(), newClass);	
																					e.getPlayer().sendMessage(ClPk.prefix+ChatColor.AQUA + "You are now a Griffon!");
																					ClPk.spawnPlayer(e.getPlayer(), "Griffon");
																					if (ClPk.Female.contains(p.getName())){
																						p.setPlayerListName(ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																					}else
																						if(ClPk.Male.contains(p.getName())){
																							p.setPlayerListName(ChatColor.AQUA + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																						}else{
																							p.setPlayerListName(ChatColor.WHITE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																						}
																				}
																			}else
																				if (sign.equals(ClPk.advCfg.getConfig().getString("SignText.Draconequus"))) 
																				{
																					if (ClPk.config.getConfig().getBoolean("Class-Options.Draconequus-Enabled"))
																					{
																						List<String> spells = ClPk.BranchSpells.getConfig().getStringList("dc.default.spells");
																						//CLASSID 9
																						classname = "Draconequus";
																						p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
																						ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
																						ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
																						ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
																						ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".energy", ClPk.advCfg.getConfig().getDouble("Classes.Draconequus.maxEnergy"));
																						ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".mana", ClPk.advCfg.getConfig().getDouble("Classes.Draconequus.maxMana"));
																						ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".maxEnergy", ClPk.advCfg.getConfig().getDouble("Classes.Draconequus.maxEnergy"));
																						ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".maxMana", ClPk.advCfg.getConfig().getDouble("Classes.Draconequus.maxMana"));
																						ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".flevel", 0);
																						ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".mlevel", 0);
																						ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".fxp", 0);
																						ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".ftime", 0);
																						ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".spells", spells);
																						ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".branch", "default");
																						ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".currentSpell", 0);
																						ClPk.playerClass.saveConfig();
																						ClPk.playerClass.reloadConfig();
																						ClPk.onlinePlayers.remove(e.getPlayer().getName());		
																						Draconequus newClass = new Draconequus(e.getPlayer().getName());
																						newClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Draconequus.default").replaceAll("&", "§"));
																						ClPk.onlinePlayers.put(e.getPlayer().getName(), newClass);	
																						e.getPlayer().sendMessage(ClPk.prefix+ChatColor.AQUA + "You are now an Draconequus!");
																						ClPk.spawnPlayer(e.getPlayer(), "Draconequus");
																						if (ClPk.Female.contains(p.getName())){
																							p.setPlayerListName(ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																						}else
																							if(ClPk.Male.contains(p.getName())){
																								p.setPlayerListName(ChatColor.AQUA + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																							}else{
																								p.setPlayerListName(ChatColor.WHITE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																							}
																					}
																				}else
																					if (sign.equals(ClPk.advCfg.getConfig().getString("SignText.Zebra"))) 
																					{
																						if (ClPk.config.getConfig().getBoolean("Class-Options.Zebra-Enabled"))
																						{
																							classname = "Zebra";
																							p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
																							ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
																							ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
																							ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
																							ClPk.playerClass.saveConfig();
																							ClPk.playerClass.reloadConfig();
																							ClPk.onlinePlayers.remove(e.getPlayer().getName());						
																							Zebra newClass = new Zebra(e.getPlayer().getName());
																							newClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Zebra.default").replaceAll("&", "§"));
																							ClPk.onlinePlayers.put(e.getPlayer().getName(), newClass);	
																							e.getPlayer().sendMessage(ClPk.prefix+ChatColor.AQUA + "You are now a Zebra!");
																							ClPk.spawnPlayer(e.getPlayer(), "Zebra");
																							if (ClPk.Female.contains(p.getName())){
																								p.setPlayerListName(ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																							}else
																								if(ClPk.Male.contains(p.getName())){
																									p.setPlayerListName(ChatColor.AQUA + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																								}else{
																									p.setPlayerListName(ChatColor.WHITE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																								}
																						}
																					}else
																						if (sign.equals(ClPk.advCfg.getConfig().getString("SignText.Breezy"))) 
																						{
																							if (ClPk.config.getConfig().getBoolean("Class-Options.Breezy-Enabled"))
																							{
																								//CLASSID 2
																								classname = "Breezy";
																								p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
																								ClPk.playerClass.getConfig().set(p.getUniqueId().toString(), null);
																								ClPk.playerClass.getConfig().set(p.getUniqueId().toString() + ".name", p.getName().toString());
																								ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
																								ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".energy", ClPk.advCfg.getConfig().getDouble("Classes.Breezy.maxEnergy"));
																								ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".maxEnergy", ClPk.advCfg.getConfig().getDouble("Classes.Breezy.maxEnergy"));
																								ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".level", 0);
																								ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".fxp", 0);
																								ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".ftime", 0);
																								ClPk.playerClass.saveConfig();
																								ClPk.playerClass.reloadConfig();
																								ClPk.onlinePlayers.remove(e.getPlayer().getName());
																								Breezy newClass = new Breezy(e.getPlayer().getName());
																								newClass.setPrefix(ClPk.advCfg.getConfig().getString("Prefixes.Breezy.default").replaceAll("&", "§"));
																								//newClass.refreshEnergy(2)
																								ClPk.onlinePlayers.put(e.getPlayer().getName(), newClass);	
																								e.getPlayer().sendMessage(ClPk.prefix+ChatColor.AQUA + "You are now a Breezy!");
																								ClPk.spawnPlayer(e.getPlayer(), "Breezy");
																								if (ClPk.Female.contains(p.getName())){
																									p.setPlayerListName(ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																								}else
																									if(ClPk.Male.contains(p.getName())){
																										p.setPlayerListName(ChatColor.AQUA + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																									}else{
																										p.setPlayerListName(ChatColor.WHITE + p.getName() + ChatColor.RESET + " " + ClPk.advCfg.getConfig().getString("Prefixes."+(ClPk.playerClass.getConfig().getString(p.getUniqueId().toString() + ".class"))+".default").replaceAll("&", "§"));	
																									}
																							}
																						}
							ClassBooks.classBooks(e.getPlayer(), classname);
							clearGuild(e.getPlayer());
							changeSetup(e.getPlayer());
						}

					}
				}

			}
		}
	}





			
	public void setDisplayName(Player p) {
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
