package com.ahui.classpack.interaction;


import java.util.HashMap;


import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

import com.ahui.classpack.util.RelativeBlocks;

public class PotionEffectModiy implements Listener
{
	private HashMap<String, BlockFace> faces = new HashMap<String, BlockFace>();
	@EventHandler
	public void speedMining(BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		BlockFace face = getBlockFaceByPlayerName(p.getName()); 
		Block block = e.getBlock();

		if(p.hasPotionEffect(PotionEffectType.FAST_DIGGING))
		{
			if(p.isSneaking())
			{return;}
			for(Block b: RelativeBlocks.getSurroundingBlocks(face, block))
			{
				if(b.getType()!=Material.AIR)
				{
					b.breakNaturally(p.getInventory().getItemInMainHand());	
				}
			}
		}
		return;
	}
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void saveBlockFace(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		BlockFace bf = event.getBlockFace();

		if (player != null && bf != null) {
			String name = player.getName();
			faces.put(name, bf);
		}
	}
	public BlockFace getBlockFaceByPlayerName(String name) {
		return faces.get(name);
	}
}
