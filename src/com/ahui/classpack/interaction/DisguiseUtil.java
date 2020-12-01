package com.ahui.classpack.interaction;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.ahui.classpack.ClPk;
import com.ahui.classpack.classes.Changeling;


public class DisguiseUtil 
implements Listener
{
	@SuppressWarnings("deprecation")
	@EventHandler
	public void cycle_use_disguise(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		if(ClPk.onlinePlayers.get(p.getName())instanceof Changeling)
		{
			if(p.getItemInHand().getType().equals(Material.LEATHER))
			{
				if(e.getAction() == (Action.LEFT_CLICK_AIR)||e.getAction() == (Action.LEFT_CLICK_BLOCK))
				{
					((Changeling)ClPk.onlinePlayers.get(e.getPlayer().getName())).cycleDisguise();
				}else if(e.getAction() == (Action.RIGHT_CLICK_AIR)||e.getAction() == (Action.RIGHT_CLICK_BLOCK))
				{
					if(((Changeling)ClPk.onlinePlayers.get(e.getPlayer().getName())).getDisguised()==false)
					{
						((Changeling)ClPk.onlinePlayers.get(e.getPlayer().getName())).disguise(p, EntityType.fromName(((Changeling)ClPk.onlinePlayers.get(e.getPlayer().getName())).currentDisguise().toUpperCase()));
					}else
					{
						((Changeling)ClPk.onlinePlayers.get(e.getPlayer().getName())).undisguise(p);
					}
				}
			}
		}
	}

}