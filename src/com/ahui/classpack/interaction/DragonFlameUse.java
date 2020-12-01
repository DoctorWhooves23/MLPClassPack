package com.ahui.classpack.interaction;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.ahui.classpack.interaction.spells.DragonFire;
import com.ahui.classpack.ClPk;
import com.ahui.classpack.classes.Dragon;

public class DragonFlameUse 
implements Listener
{
	@SuppressWarnings("deprecation")
	@EventHandler
	public void UseFire(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		if((ClPk.onlinePlayers.get(p.getName())instanceof Dragon)
				&&(p.getItemInHand().getType().equals(Material.BLAZE_ROD)))
		{
			if(e.getAction() == (Action.RIGHT_CLICK_AIR)||e.getAction() == (Action.RIGHT_CLICK_BLOCK))
			{
				DragonFire.breath(p, ((Dragon)ClPk.onlinePlayers.get(p.getName())));
			}
		}
	}
}
