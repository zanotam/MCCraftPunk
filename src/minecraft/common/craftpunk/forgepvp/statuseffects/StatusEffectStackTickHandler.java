package common.craftpunk.forgepvp.statuseffects;

import java.util.EnumSet;
import java.util.concurrent.ConcurrentHashMap;

import common.craftpunk.api.forgepvp.statuseffects.IStatusEffectStack;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class StatusEffectStackTickHandler implements ITickHandler
{

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
		for (PlayerStatusEffectContainer container: StatusEffectStackContainerHandler.playerNameToSEffectContainer.values())
		{
			for (IStatusEffectStack stack: container.statusEffectList)
			{
				stack.decrementTimeInTicks(1);
				if (stack.getActivationTime() == 0)
				{
					stack.getAbility().activate(stack, container.getEntity());
				}
				if (stack.getTime() <= 0)
				{
					stack.getAbility().deactivate(stack, container.getEntity());
				}
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
		return "PvP Attack Tracker";
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

}
