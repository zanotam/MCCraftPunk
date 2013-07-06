package common.craftpunk.forgepvp.armorclasses;

import java.util.EnumSet;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;
import common.craftpunk.api.forgepvp.armorclasses.IArmorClass;
import common.craftpunk.api.forgepvp.armorclasses.IAbility;
import common.craftpunk.core.util.Messenger;
import common.craftpunk.core.util.Triplet;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.server.FMLServerHandler;

public class ArmorAbilityTickHandler implements ITickHandler {

	public static int tickCount = 0;
	public static ConcurrentHashMap<Triplet<String, IArmorClass, IAbility>, Integer> cdMap = new ConcurrentHashMap<Triplet<String, IArmorClass, IAbility>, Integer>();
	
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

		if (tickCount > 7 * 24 * 3600 * 20) 
		{
			tickCount = 0;
		}
		
		for (String name: ArmorClassHandler.playersToArmorClass.keySet())
		{
			for (IArmorClass armorClass: ArmorClassHandler.playersToArmorClass.get(name))
			{
				for (IAbility ability: armorClass.getArmorAbilityList())
				{
					
					Triplet<String, IArmorClass, IAbility> id = new Triplet<String, IArmorClass, IAbility>(name, armorClass, ability);
					if (cdMap.containsKey(id))
					{
						if (ability.getRecurring())
						{
							if( (tickCount - cdMap.get(id)) > ability.getCD())
							{
								EntityPlayer player = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().getPlayerForUsername(name);
								
								ability.activate(player);
								
								//player.sendChatToPlayer("Activating " + ability.getName());
								
								cdMap.put(id, tickCount + ability.getCD());
							}
							else
							{
								continue; //cd isn't down yet!
							}
						}
						else
						{
							continue; //it's not a recurring ability, no need to auto-activate it!
						}
					}
					else
					{
						cdMap.put(id, tickCount);
						EntityPlayer player = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().getPlayerForUsername(name);
						Messenger.sendMessage(player, "Activating " + ability.getName());
						ability.activate(FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().getPlayerForUsername(name));
					}
					
				}
			}
		}
	}

	/**
	 * Returns the list of ticks this tick handler is interested in receiving at
	 * the minute
	 */
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.WORLD);
	}

	/**
	 * A profiling label for this tick handler
	 */
	public String getLabel() {
		return "Armor Ability Tick Handler";
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
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {

	}
}
