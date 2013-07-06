package common.craftpunk.forgepvp.armorclasses;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import common.craftpunk.api.forgepvp.armorclasses.IAbility;
import common.craftpunk.api.forgepvp.armorclasses.IArmorClass;
import common.craftpunk.api.forgepvp.armorclasses.ArmorSet;
import common.craftpunk.core.util.Messenger;
import common.craftpunk.core.util.Triplet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.server.FMLServerHandler;



public class ArmorClassHandler implements ITickHandler, IPlayerTracker
{

	public static List<String> playerNames = new Vector<String>();
	public static ConcurrentHashMap<String, ArrayList<IArmorClass>> playersToArmorClass = new ConcurrentHashMap<String, ArrayList<IArmorClass>>();
	public static int tickCount = 0;


	/**
	 * Called at the "start" phase of a tick
	 * 
	 * Multiple ticks may fire simultaneously- you will only be called once with
	 * all the firing ticks
	 * 
	 * @param type
	 * @param tickData
	 */
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
		tickCount++;
		for (String playerName : playerNames)
		{
			EntityPlayerMP player = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().getPlayerForUsername(playerName);
			if (player.inventory.inventoryChanged)
			{
				player.inventory.inventoryChanged = false;
				
				ArmorSet playerArmor = getPlayersArmor(player);
				ArrayList<IArmorClass> matchingArmors = matchingArmorClassList(playerArmor);
				ArrayList<IArmorClass> activeArmorClasses = armorClassPriorityHandler(matchingArmors);

				//the following figures out whether there have been any changes in the players armor classes.
				ArrayList<IArmorClass> oldArmorClasses = playersToArmorClass.get(playerName);
				ArrayList<IArmorClass> removedArmorClasses = new ArrayList<IArmorClass>();
				ArrayList<IArmorClass> addedArmorClasses = new ArrayList<IArmorClass>();
				
				for (IArmorClass oldArmor: oldArmorClasses)
				{
					if (! activeArmorClasses.contains(oldArmor))
					{
						removedArmorClasses.add(oldArmor);
					}
				}
				for (IArmorClass newArmor: activeArmorClasses)
				{
					if (! oldArmorClasses.contains(newArmor))
					{
						addedArmorClasses.add(newArmor);
					}
				}
				
				//this is for deactivating the ability so it won't trigger anymore
				for (IArmorClass oldArmor: removedArmorClasses)
				{
					Messenger.sendMessage(player, "You have deactivated the " + oldArmor.getName() + "armor class.");
					for (IAbility ability: oldArmor.getArmorAbilityList())
					{
						ability.deactivate(player);
					}
				}
				
				//this is somewhat unnecessary but still nice.
				for (IArmorClass newArmor: addedArmorClasses)
				{
					Messenger.sendMessage(player, "You have activated the " + newArmor.getName() + " armor class.");
					for (IAbility ability: newArmor.getArmorAbilityList())
					{
						Triplet<String, IArmorClass, IAbility> id = new Triplet<String, IArmorClass, IAbility>(playerName, newArmor, ability);
						ArmorAbilityTickHandler.cdMap.putIfAbsent(id, ArmorAbilityTickHandler.tickCount);
						ability.activate(player);
					}
				}
				
				
				playersToArmorClass.put(playerName, activeArmorClasses);
			}
			else
			{
				continue;
			}
		}
	}


	/**
	 * Returns the list of ticks this tick handler is interested in receiving at
	 * the minute
	 */
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.WORLD);
	}

	/**
	 * A profiling label for this tick handler
	 */
	public String getLabel()
	{
		return "Armor Class Tracker";
	}

	/**
	 * Called at the "end" phase of a tick
	 * 
	 * Multiple ticks may fire simultaneously- you will only be called once with
	 * all the firing ticks
	 * 
	 * @param type
	 * @param tickData
	 */
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{

	}


	public void onPlayerLogin(EntityPlayer player)
	{
		ArmorSet playerArmor = getPlayersArmor(player);
		ArrayList<IArmorClass> matchingArmors = matchingArmorClassList(playerArmor);
		ArrayList<IArmorClass> playerArmorClasses = armorClassPriorityHandler(matchingArmors);

		playerNames.add(player.username);
		playersToArmorClass.put(player.username, playerArmorClasses);
	}

	public void onPlayerLogout(EntityPlayer player)
	{
		playerNames.remove(player.username);
		playersToArmorClass.remove(player.username);
	}

	public void onPlayerChangedDimension(EntityPlayer player)
	{
		//purposefully does nothing for now
	}

	public void onPlayerRespawn(EntityPlayer player)
	{
	      ArmorSet playerArmor = getPlayersArmor(player);
	      ArrayList<IArmorClass> matchingArmors = matchingArmorClassList(playerArmor);
	      ArrayList<IArmorClass> playerArmorClasses = armorClassPriorityHandler(matchingArmors);
	      playersToArmorClass.put(player.username, playerArmorClasses);
	}

	public static ArmorSet getPlayersArmor(EntityPlayer player)
	{
		ItemStack head = player.inventory.armorItemInSlot(0);
		ItemStack chest = player.inventory.armorItemInSlot(1);
		ItemStack leg = player.inventory.armorItemInSlot(2);
		ItemStack foot = player.inventory.armorItemInSlot(3);
		ArmorSet playerArmor = new ArmorSet(head, chest, leg, foot);
		return playerArmor;
	}

	public static ArrayList<IArmorClass> matchingArmorClassList(ArmorSet armorSet)
	{
		ArrayList<IArmorClass> matchingArmors = new ArrayList<IArmorClass>();
		//matchingArmors.add(ArmorClass.emptyClass);
		for (IArmorClass armorClass : ArmorClasses.armorClassesList)
		{
			System.out.println(armorClass.getName());
			if (armorSet.containsArmorSet(armorClass.getArmorSet()))
			{
				matchingArmors.add(armorClass);
			}
		}
		return matchingArmors;
	}

	public static ArrayList<IArmorClass> armorClassPriorityHandler(ArrayList<IArmorClass> armorClassList)
	{
		ArrayList<IArmorClass> filteredArmorList = new ArrayList<IArmorClass>(armorClassList);

		int maxPriority = -1;
		boolean maxShares = true;
		IArmorClass maxArmor = IArmorClass.emptyClass;
		for (IArmorClass armorClass : armorClassList)
		{
			if (armorClass.getPriority() > maxPriority)
			{
				maxPriority = armorClass.getPriority();
				maxShares = armorClass.getDoesStack();
				maxArmor = armorClass;
			}
		}
		if (maxShares)
		{
			for (IArmorClass armorClass : armorClassList)
			{
				if (armorClass.getDoesStack())
				{
					filteredArmorList.add(armorClass);
				}
				else
				{
					continue;
				}
			}
		}
		else
		{
			filteredArmorList.add(maxArmor);
		}
		return filteredArmorList;
	}
}
