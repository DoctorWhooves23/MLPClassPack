package com.ahui.classpack.interaction.spells;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.ahui.classpack.ClPk;
import com.ahui.classpack.classes.interfaces.MagicUser;
import com.ahui.classpack.util.ParticleEffect;
import com.ahui.classpack.util.ParticleEffect.ParticleType;

public class Recall 
{

	public static void save(Player p, MagicUser user)
	{
		if(((MagicUser)ClPk.onlinePlayers.get(p.getName())).getCool("save")==true){return;
		}else
			if (user.getMana() > 15.0)
			{
				user.setMana(user.getMana() - 7.5);
			}
		((MagicUser)ClPk.onlinePlayers.get(p.getName())).setCool("save", 20);
		ParticleEffect porterSet1 = new ParticleEffect(ParticleType.PORTAL, 2, 32, .1);
		ParticleEffect porterSet2 = new ParticleEffect(ParticleType.REDSTONE, 0, 16, .2);

		porterSet1.sendToLocation(p.getLocation());
		porterSet2.sendToLocation(p.getLocation());
		World world = p.getLocation().getWorld();
		double x = p.getLocation().getX();
		double y = p.getLocation().getY();
		double z = p.getLocation().getZ();
		ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".setRecall.world", world.getName());
		ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".setRecall.x", x);
		ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".setRecall.y", y);
		ClPk.playerClass.getConfig().set(p.getUniqueId().toString()+".setRecall.z", z);
		ClPk.playerClass.saveConfig();
		ClPk.playerClass.reloadConfig();

	}	

	public static void recallI(Player p, MagicUser user)
	{
		if(((MagicUser)ClPk.onlinePlayers.get(p.getName())).getCool("recalli")==true){return;
		}else
			if (user.getMana() > 15.0)
			{
				user.setMana(user.getMana() - 7.5);
			}
		((MagicUser)ClPk.onlinePlayers.get(p.getName())).setCool("recalli", 5);
		ParticleEffect porterCall1 = new ParticleEffect(ParticleType.PORTAL, 2, 32, .1);
		ParticleEffect porterCall2 = new ParticleEffect(ParticleType.SPELL_INSTANT, 0, 16, .2);

		if(ClPk.playerClass.getConfig().contains(p.getUniqueId().toString()+".setRecall"))
		{
			World world = Bukkit.getWorld(ClPk.playerClass.getConfig().getString(p.getUniqueId()+".setRecall.world"));
			double x = ClPk.playerClass.getConfig().getDouble(p.getUniqueId().toString()+".setRecall.x");
			double y = ClPk.playerClass.getConfig().getDouble(p.getUniqueId().toString()+".setRecall.y");
			double z = ClPk.playerClass.getConfig().getDouble(p.getUniqueId().toString()+".setRecall.z");
			Location loc = new Location(world,x,y,z);
			if(p.getLocation().getWorld()==world&&p.getLocation().distance(loc)<500){
				
				p.teleport(loc);
				porterCall1.sendToLocation(loc);
				porterCall2.sendToLocation(loc);
			}
		}
	}
	//TransDim
	public static void recallII(Player p, MagicUser user)
	{
		if(((MagicUser)ClPk.onlinePlayers.get(p.getName())).getCool("recallii")==true){return;
		}else
			if (user.getMana() > 31.0)
			{
				user.setMana(user.getMana() - 30.0);
			}
		((MagicUser)ClPk.onlinePlayers.get(p.getName())).setCool("recallii", 5);
		ParticleEffect porterCall1 = new ParticleEffect(ParticleType.PORTAL, 2, 32, .1);
		ParticleEffect porterCall2 = new ParticleEffect(ParticleType.SPELL_INSTANT, 0, 16, .2);

		if(ClPk.playerClass.getConfig().contains(p.getUniqueId().toString()+".setRecall"))
		{
			World world = Bukkit.getWorld(ClPk.playerClass.getConfig().getString(p.getUniqueId()+".setRecall.world"));
			double x = ClPk.playerClass.getConfig().getDouble(p.getUniqueId().toString()+".setRecall.x");
			double y = ClPk.playerClass.getConfig().getDouble(p.getUniqueId().toString()+".setRecall.y");
			double z = ClPk.playerClass.getConfig().getDouble(p.getUniqueId().toString()+".setRecall.z");
			Location loc = new Location(world,x,y,z);

			p.teleport(loc);
			porterCall1.sendToLocation(loc);
			porterCall2.sendToLocation(loc);
		}
	}
}
