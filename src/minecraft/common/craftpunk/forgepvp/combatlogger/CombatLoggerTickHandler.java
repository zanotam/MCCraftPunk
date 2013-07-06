package common.craftpunk.forgepvp.combatlogger;

import java.util.EnumSet;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.entity.player.EntityPlayer;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

import common.craftpunk.core.util.Pair;

public class CombatLoggerTickHandler implements ITickHandler
{
	public static int tickCount = 0;
	public static ConcurrentHashMap<Pair<String, EntityPlayer>, Integer> sourceToDurationMap = new ConcurrentHashMap<Pair<String, EntityPlayer>, Integer>(10^4);

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
		tickCount++;
		
		for(Pair<String, EntityPlayer> source: sourceToDurationMap.keySet())
		{
			int duration = sourceToDurationMap.get(source);
			if (duration <= 0)
			{
				sourceToDurationMap.remove(source);
				ForgeCombatLogger.combatLogHandler.removeCombatTag(source);
			}
			else
			{
				sourceToDurationMap.put(source, duration - 1);
			}
		}
		
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		//nothing here
	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.WORLD);
	}

	@Override
	public String getLabel()
	{
		return "Combat Logger Source Tick Handler";
	}

	public boolean addSource(Pair<String, EntityPlayer> source, int duration)
	{
		boolean succeed;
		
		if ((sourceToDurationMap.get(source) != null) && (sourceToDurationMap.get(source) <= duration))
		{
			sourceToDurationMap.put(source, duration);
			succeed = ForgeCombatLogger.combatLogHandler.addCombatTag(source);
		}
		else if ((sourceToDurationMap.get(source) == null))
		{
		    sourceToDurationMap.put(source, duration);
            succeed = ForgeCombatLogger.combatLogHandler.addCombatTag(source);
            ForgeCombatLogger.combatLogHandler.addCombatTag(source);
		}
		else
		{
			succeed = false;
		}
		
		return succeed;
	}
	
	public boolean addSource(String sourceString, EntityPlayer player, int duration)
	{
		return addSource(new Pair<String, EntityPlayer>(sourceString, player), duration);
	}
	
	public int getSourceTime(Pair<String, EntityPlayer> source)
	{
		return sourceToDurationMap.get(source);
	}
	
	public int getSourceTime(String sourceString, EntityPlayer player)
	{
		return getSourceTime(new Pair<String, EntityPlayer>(sourceString, player));
	}
}
