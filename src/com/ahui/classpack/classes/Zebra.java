package com.ahui.classpack.classes;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.ahui.classpack.classes.interfaces.ClassBase;
import com.ahui.classpack.ClPk;



public class Zebra 
extends ClassBase
{
	private int level;
	private int saveTime;
	private double maxHealth;
	public int taskGlide;
	public int taskCool;
	public int taskSave;
	public int taskReEn;
	public boolean jump = false;
	//TODO No Colide with vines
	public Zebra(String name) {
		super(name);
		this.classID = 10;
		this.prefix = ClPk.advCfg.getConfig().getString("Prefixes.Zebra.default").replaceAll("&", "§");
		this.saveTime = ClPk.advCfg.getConfig().getInt("classDataAutoSave");
		this.maxHealth = ClPk.advCfg.getConfig().getInt("Classes.Zebra.maxHealth"); 
		this.getPlayer().setHealthScale(maxHealth);
		this.getPlayer().setFlySpeed((float) 0.1);
		this.saveAll();
	}
	public String getClassNm()
	{
		return ClPk.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".class");
	}
	public void saveAll()
	{
		taskSave = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ClPk.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{
				if(Zebra.this.getPlayer()!=null){
					Player p = Zebra.this.getPlayer();
					p.sendMessage(ClPk.prefix+"autosaving classData.");
					ClPk.playerClass.saveConfig();
					ClPk.playerClass.reloadConfig();
					saveAll();
				}
			}
		},saveTime);
	}
	public int getLevel() 
	{
		return this.level;
	}
	public void stop()
	{
		Bukkit.getScheduler().cancelTask(taskReEn);
		Bukkit.getScheduler().cancelTask(taskSave);
	}

}
