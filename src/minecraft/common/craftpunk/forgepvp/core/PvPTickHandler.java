package common.craftpunk.forgepvp.core;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.concurrent.ConcurrentHashMap;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

public class PvPTickHandler implements ITickHandler
{


	public static ConcurrentHashMap<String, Integer> userLastAttackTick = new ConcurrentHashMap<String, Integer>(10 ^ 5);
	public static ConcurrentHashMap<String, ConcurrentHashMap<Integer, Integer>> userToIDToCDMap = new ConcurrentHashMap<String, ConcurrentHashMap<Integer, Integer>>(10 ^ 5);
	public static int tickCount = 0;

	public int tickCounter;

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
		
		int ticksInAWeek = 7*24*3600*20;
		
		if( tickCount > ticksInAWeek)
		{
			tickCount = 0;
			for (String user: userToIDToCDMap.keySet())
			{
				ConcurrentHashMap<Integer, Integer> idToCDMapForUser = userToIDToCDMap.get(user);
				for (int itemID: idToCDMapForUser.keySet())
				{
					int adjustedCD = idToCDMapForUser.get(itemID) - ticksInAWeek;
					idToCDMapForUser.put(itemID, adjustedCD);
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
