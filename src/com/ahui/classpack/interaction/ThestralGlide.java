package com.ahui.classpack.interaction;


import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;


import com.ahui.classpack.ClPk;
import com.ahui.classpack.classes.interfaces.Flier;
import com.ahui.classpack.util.RandInt;

public class ThestralGlide 
implements Listener
{
	public static boolean cool;
	public static Double intCool;
	public static Double lCost = ClPk.advCfg.getConfig().getDouble("flightCost");
	public static Double fTime = ClPk.advCfg.getConfig().getDouble("flightTime");
	@SuppressWarnings("deprecation")
	@EventHandler
	public void fly(PlayerInteractEvent e)
	{
		//CreativeFlight
		if(ClPk.onlinePlayers.get(e.getPlayer().getName())instanceof Flier)
		{
			Player p = e.getPlayer();
			if(p.isSneaking()&&p.getItemInHand().getType().equals(Material.FEATHER)&&p.getItemInHand().hasItemMeta()&&p.getItemInHand().getItemMeta().hasDisplayName()&&p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("wings")&&p.getLevel()>=lCost)
			{
				p.setLevel((int) (p.getLevel()-lCost));
				if(p.getItemInHand().getAmount()>1){
					p.getItemInHand().setAmount(p.getItemInHand().getAmount()-1);
				}
				else{
					p.setItemInHand(null);
				}

				((Flier)ClPk.onlinePlayers.get(p.getPlayer().getName())).setCool(((Flier)ClPk.onlinePlayers.get(p.getPlayer().getName())).getCool()+fTime);
				p.setAllowFlight(true);
				//setCanFly(p);
			}
			//train
			if(p.isSneaking()&&p.getItemInHand().getType().equals(Material.FEATHER)&&p.getItemInHand().hasItemMeta()&&p.getItemInHand().getItemMeta().hasDisplayName()&&p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("trainer"))
			{
				if(e.getAction()==Action.RIGHT_CLICK_BLOCK)
				{
					if(((Flier)ClPk.onlinePlayers.get(e.getPlayer().getName())).getEnergy()>10)
					{
						((Flier)ClPk.onlinePlayers.get(e.getPlayer().getName())).setEnergy(((Flier)ClPk.onlinePlayers.get(e.getPlayer().getName())).getEnergy()-2);
						((Flier)ClPk.onlinePlayers.get(e.getPlayer().getName())).showHud();
						e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ENDERDRAGON_FLAP, 1,RandInt.randInt(0, 3));
						e.getPlayer().getWorld().playEffect(e.getPlayer().getLocation(), Effect.STEP_SOUND, e.getPlayer().getLocation().subtract(0,1,0).getBlock().getType());
						((Flier)ClPk.onlinePlayers.get(e.getPlayer().getName())).setFxp(1);
					}
				}
			}
			//standardFlight
			if(p.getItemInHand().getType().equals(Material.FEATHER)&&!p.getItemInHand().hasItemMeta())
			{
				if(e.getAction()==Action.RIGHT_CLICK_AIR||e.getAction()==Action.RIGHT_CLICK_BLOCK)
				{
					if(((Flier)ClPk.onlinePlayers.get(e.getPlayer().getName())).getEnergy()>2)
					{
						((Flier)ClPk.onlinePlayers.get(e.getPlayer().getName())).setEnergy(((Flier)ClPk.onlinePlayers.get(e.getPlayer().getName())).getEnergy()-.05);
						((Flier)ClPk.onlinePlayers.get(e.getPlayer().getName())).showHud();
						Vector v = e.getPlayer().getVelocity();
						Vector d = e.getPlayer().getLocation().getDirection();
						if(e.getPlayer().getLocation().getY()>115)
						{
							d.multiply(2.5);	
						}else if(e.getPlayer().getLocation().getBlockY()<50){
							d.multiply(.5);
						}else{
							d.multiply(1);
						}
						e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ENDERDRAGON_FLAP, 1, (float) (9*v.getY()+Math.random()));
						e.getPlayer().setVelocity(d);
						e.getPlayer().setFallDistance(0);
						e.getPlayer().getWorld().playEffect(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN,1).getLocation(), Effect.STEP_SOUND, e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN,2).getType());
					}
				}
			}
		}
	}
	@EventHandler
	public void doubleJump(PlayerToggleFlightEvent e)
	{
		if(ClPk.onlinePlayers.get(e.getPlayer().getName())instanceof Flier)
		{
			Player p = e.getPlayer();
			if (p.getPlayerTime() >= 12541){
				p.setFlySpeed((float) 0.1);
			}else{
				p.setFlySpeed((float) 0.04);
			}
			if(((Flier)ClPk.onlinePlayers.get(e.getPlayer().getName())).getCool()==0)
			{
				if(ClPk.advCfg.getConfig().getBoolean("overPowerFlying")==true)
				{
					return;
				}
				if(p.isFlying()||!p.getGameMode().equals(GameMode.SURVIVAL)||!p.getGameMode().equals(GameMode.ADVENTURE))
				{
					return;
				}
				e.setCancelled(true);
				p.sendMessage(ClPk.prefix+"type /bcClassInfo for class info!");
			}
		}

		if(!(ClPk.onlinePlayers.get(e.getPlayer().getName())instanceof Flier))
		{
			if(e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)||e.getPlayer().getGameMode().equals(GameMode.ADVENTURE))
			{
				e.setCancelled(true);
			}
		}

	}
}
