package common.craftpunk.forgepvp.core;

import java.util.concurrent.ConcurrentHashMap;

import common.craftpunk.core.util.Messenger;



import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ASMEventHandler;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.IEventListener;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class PvPEventHandler
{

	public static int ticksBetweenAttacks = 10;
	public static ConcurrentHashMap<Integer, Integer> itemIDToCDMap = new ConcurrentHashMap<Integer, Integer>(10 ^ 5);
	
	public PvPEventHandler()
	{
		itemIDToCDMap.putIfAbsent(368, 300); //ender pearl with 15s cd
		itemIDToCDMap.putIfAbsent(322, 72000); //golden apple with 1hour cd
	}

	/**
	 * cancels the event if the player is attacking too often.
	 * @param event
	 */
	@ForgeSubscribe(priority = EventPriority.LOWEST, receiveCanceled = false)
	public void playerAttackedEntity(AttackEntityEvent event)
	{
		if (FMLCommonHandler.instance().getEffectiveSide().isClient())
			return;
		
		EntityPlayer player = event.entityPlayer;
		int currentTick = PvPTickHandler.tickCount;
		PvPTickHandler.userLastAttackTick.putIfAbsent(player.username, currentTick);

		boolean stillOnTick = (currentTick - PvPTickHandler.userLastAttackTick.get(player.username)) < 2;
		boolean canAttack = stillOnTick || (ticksBetweenAttacks <= (currentTick - PvPTickHandler.userLastAttackTick.get(player.username)));
		
		// cancels if the player is attacking too often!
		if (canAttack)
		{
			PvPTickHandler.userLastAttackTick.put(player.username, currentTick);
			//player.sendChatToPlayer("Resetting your attack ticks!");
		}
		else
		{
			int timeTilAttack = (currentTick - PvPTickHandler.userLastAttackTick.get(player.username));
			//player.sendChatToPlayer("Your attack will be off cooldown in " + timeTilAttack + " ticks (~ " + timeTilAttack/20.0 + "s).");
			//player.sendChatToPlayer("You can only attack once every " + ticksBetweenAttacks + " ticks (~ " + ticksBetweenAttacks / 20.0 + "s)!");
			//player.sendChatToPlayer("Current ticks since last attack for " + player.username + " is " + (currentTick - PvPTickHandler.userLastAttackTick.get(player.username)));
			event.setCanceled(true);
		}
		/*
		if (event.isCanceled())
		{

		}
		else
		{
			EntityPlayer player = event.entityPlayer;

			PvPTickHandler.userLastAttackTick.putIfAbsent(player.username, ticksBetweenAttacks);

			boolean canAttack = (ticksBetweenAttacks <= PvPTickHandler.userLastAttackTick.get(player.username));
			// cancels if the player is attacking too often!
			if (canAttack)
			{
				PvPTickHandler.userLastAttackTick.put(player.username, 0);
				player.sendChatToPlayer("Resetting your attack ticks!");
			}
			else
			{
				player.sendChatToPlayer("You can only attack once every " + ticksBetweenAttacks + " ticks (~ " + ticksBetweenAttacks / 20 + "s)!");
				player.sendChatToPlayer("Current ticks since last attack for " + player.username + " is " + PvPTickHandler.userLastAttackTick.get(player.username));
				event.setCanceled(true);
			}
		}
		*/
	}

	@ForgeSubscribe(priority = EventPriority.LOWEST, receiveCanceled = false)
	public void click(PlayerInteractEvent event)
	{
		if (FMLCommonHandler.instance().getEffectiveSide().isClient())
			return;

		int currentTick = PvPTickHandler.tickCount;
		EntityPlayer player = event.entityPlayer;
		String name = player.username;
		
		if (!PvPTickHandler.userToIDToCDMap.containsKey(name))
		{
			ConcurrentHashMap<Integer, Integer> blankMap = new ConcurrentHashMap<Integer, Integer>(10^5);
			for (Integer id: itemIDToCDMap.keySet())
			{
				blankMap.put(id, -7*24*3600*20); //puts an amount of ticks equal to a week in!
			}
			PvPTickHandler.userToIDToCDMap.putIfAbsent(name, blankMap);
		}

		ItemStack is = player.inventory.getCurrentItem();
		if (is != null)
		{
			if (itemIDToCDMap.containsKey(is.itemID))
			{
				boolean overCD = itemIDToCDMap.get(is.itemID) <= (currentTick - PvPTickHandler.userToIDToCDMap.get(name).get(is.itemID));
				if (overCD)
				{
					PvPTickHandler.userToIDToCDMap.get(name).put(is.itemID, currentTick);	
				}
				else
				{
					int timeTilUse = (currentTick - PvPTickHandler.userToIDToCDMap.get(name).get(is.itemID));
					Messenger.sendMessage(player, "Your attack will be off cooldown in " + timeTilUse + " ticks (~ " + timeTilUse/20.0 + "s).");
					//player.sendChatToPlayer("You can only use that item once every " + itemIDToCDMap.get(is.itemID) + " ticks (~ " + itemIDToCDMap.get(is.itemID) / 20 + "s)!");
					//player.sendChatToPlayer("Current ticks since last use for " + name + " is " + (currentTick - PvPTickHandler.userToIDToCDMap.get(name).get(is.itemID)));
					event.setCanceled(true);
				}
			}
			else
			{
				//player.sendChatToPlayer("That item has no CD!");
			}

		}
		else
		{
			//player.sendChatToPlayer("Click event activated, but item stack was null!");
		}
	}
	
	public boolean setTicksBetweenAttacks(int time)
	{
		boolean validTime = time > 2;
		if (validTime)
		{
			ticksBetweenAttacks = time;
		}
		return validTime;
	}
	
	public void addCDToItem(int id, int cd)
	{
		itemIDToCDMap.put(id, cd);
	}

}
