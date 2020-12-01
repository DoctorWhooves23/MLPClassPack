package com.ahui.classpack.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.ahui.classpack.ClPk;


public class TabAutoCompletion implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		// String name = sender.getName();
		// SPlayer p = Bukkit.getPlayer(name);
		// World world = p.getWorld();
		// PluginManager pm = Bukkit.getServer().getPluginManager();	
		Arrays.sort(ClPk.classNames);
		if (cmd.getName().equalsIgnoreCase("cp")) {
			if (args.length >= 0 && args.length < 2) {
				if (!args[0].isEmpty()) {
					List<String> commands = new ArrayList<String>();
					for (String com : ClPk.baseCommands) {
						if (com.toLowerCase().startsWith(args[0].toLowerCase()) || com.startsWith(args[0])) {
							commands.add(com);
						}
					}
					if (!commands.isEmpty()) {
						return commands;
					} else {
						return ClPk.baseCommands;
	 				}
				} else {
					return ClPk.baseCommands;
				}
			}
			if (args[0].equalsIgnoreCase("Class")){
                if (args.length >= 1 && args.length < 3) {
                    if (!args[1].isEmpty()) {
                        List<String> commands = new ArrayList<String>();
                        for (String com : ClPk.classCommands) {
                            if (com.toLowerCase().startsWith(args[1].toLowerCase()) || com.startsWith(args[1])) {
                                commands.add(com);
                            }
                        }
                        if (!commands.isEmpty()) {
                            return commands;
                        } else {
                            return ClPk.classCommands;
                        }
                    } else {
                        return ClPk.classCommands;
                    }
                }
				if (args[1].equalsIgnoreCase("Set")){
                    if (args.length >= 2 && args.length < 4) {
                        if (!args[2].isEmpty()) {
                            List<String> commands = new ArrayList<String>();
                            for (String com : ClPk.classSetCommands) {
                                if (com.toLowerCase().startsWith(args[2].toLowerCase()) || com.startsWith(args[2])) {
                                    commands.add(com);
                                }
                            }
                            if (!commands.isEmpty()) {
                                return commands;
                            } else {
                                return ClPk.classSetCommands;
                            }
                        } else {
                            return ClPk.classSetCommands;
                        }
                    }
					if (args[2].equalsIgnoreCase("Spawn")){
						if (args.length == 4) {
							List<String> classes = Arrays.asList(ClPk.classNames);
							if (!args[3].isEmpty()) {
								List<String> cList = new ArrayList<String>();
								for (String clas : classes) {
									if (clas.toLowerCase().startsWith(args[3].toLowerCase()) || clas.startsWith(args[3])) {
										cList.add(clas);
									}
								}
								if (!cList.isEmpty()) {
									return cList;
								} else {
									return classes;
								}
							}
							return classes;
						}
					}
					if (args[2].equalsIgnoreCase("Tag")){
						if (args.length == 4) {
							List<String> classes = Arrays.asList(ClPk.classNames);
							if (!args[3].isEmpty()) {
								List<String> cList = new ArrayList<String>();
								for (String clas : classes) {
									if (clas.toLowerCase().startsWith(args[3].toLowerCase()) || clas.startsWith(args[3])) {
										cList.add(clas);
									}
								}
								if (!cList.isEmpty()) {
									return cList;
								} else {
									return classes;
								}
							}
							return classes;
						}
					}
					if (args[2].equalsIgnoreCase("Sign")){
						if (args[3].equalsIgnoreCase("Text")){
							if (args.length == 5) {
								List<String> classes = Arrays.asList(ClPk.classNames);
								if (!args[4].isEmpty()) {
									List<String> cList = new ArrayList<String>();
									for (String clas : classes) {
										if (clas.toLowerCase().startsWith(args[4].toLowerCase()) || clas.startsWith(args[4])) {
											cList.add(clas);
										}
									}
									if (!cList.isEmpty()) {
										return cList;
									} else {
										return classes;
									}
								}
								return classes;
							}
						}
						if (!args[3].isEmpty()) {
							List<String> commands = new ArrayList<String>();
							for (String com : ClPk.classSignCommands) {
								if (com.toLowerCase().startsWith(args[3].toLowerCase()) || com.startsWith(args[3])) {
									commands.add(com);
								}
							}
							if (!commands.isEmpty()) {
								return commands;
							} else {
								return ClPk.classSignCommands;
							}
						} else {
							return ClPk.classSignCommands;
						}
					}
					if (args[2].equalsIgnoreCase("Class")){
						if (args.length == 4) {
							List<String> classes = Arrays.asList(ClPk.classNames);
							if (!args[3].isEmpty()) {
								List<String> cList = new ArrayList<String>();
								for (String clas : classes) {
									if (clas.toLowerCase().startsWith(args[3].toLowerCase()) || clas.startsWith(args[3])) {
										cList.add(clas);
									}
								}
								if (!cList.isEmpty()) {
									return cList;
								} else {
									return classes;
								}
							}
							return classes;
						}
						if (args.length == 5) {
							List<String> classes = Arrays.asList(ClPk.classNames);
							if (!args[4].isEmpty()) {
								List<String> cList = new ArrayList<String>();
								for (String clas : classes) {
									if (clas.toLowerCase().startsWith(args[4].toLowerCase()) || clas.startsWith(args[4])) {
										cList.add(clas);
									}
								}
								if (!cList.isEmpty()) {
									return cList;
								} else {
									return classes;
								}
							}
							return classes;
						}
					}
				}
				if (args[1].equalsIgnoreCase("Get")){
					 if (args.length >= 2 && args.length < 4) {
                        if (!args[2].isEmpty()) {
                            List<String> commands = new ArrayList<String>();
                            for (String com : ClPk.classGetCommands) {
                                if (com.toLowerCase().startsWith(args[2].toLowerCase()) || com.startsWith(args[2])) {
                                    commands.add(com);
                                }
                            }
                            if (!commands.isEmpty()) {
                                return commands;
                            } else {
                                return ClPk.classGetCommands;
                            }
                        } else {
                            return ClPk.classGetCommands;
                        }
                    }
					if (args[2].equalsIgnoreCase("Info")){
						if (args.length == 4) {
							List<String> classes = Arrays.asList(ClPk.classNames);
							if (!args[3].isEmpty()) {
								List<String> cList = new ArrayList<String>();
								for (String clas : classes) {
									if (clas.toLowerCase().startsWith(args[3].toLowerCase()) || clas.startsWith(args[3])) {
										cList.add(clas);
									}
								}
								if (!cList.isEmpty()) {
									return cList;
								} else {
									return classes;
								}
							}
							return classes;
						}
					}
					if (args[2].equalsIgnoreCase("Spawn")){
						if (args.length == 4) {
							List<String> classes = Arrays.asList(ClPk.classNames);
							if (!args[3].isEmpty()) {
								List<String> cList = new ArrayList<String>();
								for (String clas : classes) {
									if (clas.toLowerCase().startsWith(args[3].toLowerCase()) || clas.startsWith(args[3])) {
										cList.add(clas);
									}
								}
								if (!cList.isEmpty()) {
									return cList;
								} else {
									return classes;
								}
							}
							return classes;
						}
					}
					if (args[2].equalsIgnoreCase("Tag")){
						if (args.length == 4) {
							List<String> classes = Arrays.asList(ClPk.classNames);
							if (!args[3].isEmpty()) {
								List<String> cList = new ArrayList<String>();
								for (String clas : classes) {
									if (clas.toLowerCase().startsWith(args[3].toLowerCase()) || clas.startsWith(args[3])) {
										cList.add(clas);
									}
								}
								if (!cList.isEmpty()) {
									return cList;
								} else {
									return classes;
								}
							}
							return classes;
						}
					}
					if (args[2].equalsIgnoreCase("Sign")){
						if (args[3].equalsIgnoreCase("Text")){
							if (args.length == 5) {
								List<String> classes = Arrays.asList(ClPk.classNames);
								if (!args[4].isEmpty()) {
									List<String> cList = new ArrayList<String>();
									for (String clas : classes) {
										if (clas.toLowerCase().startsWith(args[4].toLowerCase()) || clas.startsWith(args[4])) {
											cList.add(clas);
										}
									}
									if (!cList.isEmpty()) {
										return cList;
									} else {
										return classes;
									}
								}
								return classes;
							}
						}
						if (!args[3].isEmpty()) {
							List<String> commands = new ArrayList<String>();
							for (String com : ClPk.classSignCommands) {
								if (com.toLowerCase().startsWith(args[3].toLowerCase()) || com.startsWith(args[3])) {
									commands.add(com);
								}
							}
							if (!commands.isEmpty()) {
								return commands;
							} else {
								return ClPk.classSignCommands;
							}
						} else {
							return ClPk.classSignCommands;
						}
					}
					if (args[2].equalsIgnoreCase("Stats")){
						
					}

                }
				if (args[1].equalsIgnoreCase("Spawn")){
					if (args.length == 3) {
						List<String> classes = Arrays.asList(ClPk.classNames);
						if (!args[2].isEmpty()) {
							List<String> cList = new ArrayList<String>();
							for (String clas : classes) {
								if (clas.toLowerCase().startsWith(args[2].toLowerCase()) || clas.startsWith(args[2])) {
									cList.add(clas);
								}
							}
							if (!cList.isEmpty()) {
								return cList;
							} else {
								return classes;
							}
						}
						return classes;
					}
				}
            }
			if (args[0].equalsIgnoreCase("wolfPack")){
				if (args[1].equalsIgnoreCase("removePack") || args[1].equalsIgnoreCase("getPack") || args[1].equalsIgnoreCase("joinPack") || args[1].equalsIgnoreCase("leavePack")) {
					if (args.length == 3) {
						List<String> packNames = new ArrayList<String>();
						for (String gName : ClPk.guilds_packs.getConfig().getConfigurationSection("Twol").getKeys(false)) {
							if (!packNames.contains(gName)) {
								packNames.add(gName);
							}
							if (!args[2].isEmpty()) {
								List<String> packList = new ArrayList<String>();
								for (String pack : ClPk.guilds_packs.getConfig().getConfigurationSection("Twol")
										.getKeys(false)) {
									if (pack.toLowerCase().startsWith(args[2].toLowerCase()) || pack.startsWith(args[2])) {
										packList.add(pack);
									}
								}
								if (!packList.isEmpty()) {
									return packList;
								} else {
									return packNames;
								}
							}

						}
						return packNames;
					}
				}
				if (args[1].equalsIgnoreCase("createPack")) {
					if (args.length == 3) {
						List<String> returnVal = new ArrayList<String>();
						returnVal.add("<desiredName>");
						return returnVal;
					}
					if (args.length == 4) {
						return ClPk.colour;
					}
				}
				if (args[1].equalsIgnoreCase("editPack")) {
					List<String> packNames = new ArrayList<String>();
					if (args.length == 3) {
						for (String gName : ClPk.guilds_packs.getConfig().getConfigurationSection("Twol").getKeys(false)) {
							if (!packNames.contains(gName)) {
								packNames.add(gName);
							}
							if (!args[2].isEmpty()) {
								List<String> packList = new ArrayList<String>();
								for (String pack : ClPk.guilds_packs.getConfig().getConfigurationSection("Twol")
										.getKeys(false)) {
									if (pack.toLowerCase().startsWith(args[2].toLowerCase()) || pack.startsWith(args[2])) {
										packList.add(pack);
									}
								}
								if (!packList.isEmpty()) {
									return packList;
								} else {
									return packNames;
								}
							}

						}
						return packNames;
					}
					if (args.length == 4) {
						return ClPk.colour;
					}
				}
				if (!args[2].isEmpty()) {
					List<String> packCmds = new ArrayList<String>();
					for (String paccom : ClPk.wolfPackCmds) {
						if (paccom.toLowerCase().startsWith(args[0].toLowerCase()) || paccom.startsWith(args[0])) {
							packCmds.add(paccom);
						}
					}
					if (!packCmds.isEmpty()) {
						return packCmds;
					} else {
						return ClPk.wolfPackCmds;
	 				}
				} else {
					return ClPk.wolfPackCmds;
				}
			}
			if (args[0].equalsIgnoreCase("Gender")){
				if (args.length >= 1 && args.length < 3) {
					if (!args[1].isEmpty()) {
						List<String> commands = new ArrayList<String>();
						for (String com : ClPk.genderCmds) {
							if (com.toLowerCase().startsWith(args[1].toLowerCase()) || com.startsWith(args[1])) {
                                commands.add(com);
                            }
						}
						if (!commands.isEmpty()) {
							return commands;
						} else {
							return ClPk.genderCmds;
						}
					} else {
						return ClPk.genderCmds;
					}
				}
                if (args[1].equalsIgnoreCase("set")) {
                    
                }
                if (args[1].equalsIgnoreCase("List")) {
                    
                }

            }
            
			if (args[0].equalsIgnoreCase("runParticles")) {
				if (args.length == 2) {
					List<String> bol = new ArrayList<String>();
					bol.add("True");
					bol.add("False");
					return bol;
				}
			}
			if (args[0].equalsIgnoreCase("setClass")) {
				if (args.length == 3) {
					List<String> classes = Arrays.asList(ClPk.classNames);
					if (!args[2].isEmpty()) {
						List<String> cList = new ArrayList<String>();
						for (String clas : classes) {
							if (clas.toLowerCase().startsWith(args[2].toLowerCase()) || clas.startsWith(args[2])) {
								cList.add(clas);
							}
						}
						if (!cList.isEmpty()) {
							return cList;
						} else {
							return classes;
						}
					}
					return classes;
				}
			}
			
			if (args[0].equalsIgnoreCase("SetSpawn")) {
				if (args.length == 2) {
					List<String> classes = Arrays.asList(ClPk.classNames);
					if (!args[1].isEmpty()) {
						List<String> cList = new ArrayList<String>();
						for (String clas : classes) {
							if (clas.toLowerCase().startsWith(args[1].toLowerCase()) || clas.startsWith(args[1])) {
								cList.add(clas);
							}
						}
						if (!cList.isEmpty()) {
							return cList;
						} else {
							return classes;
						}
					}

					return classes;
				}
			}

		}

		return ClPk.emptyCommands;

	}

}
