package common.craftpunk.forgepvp.statuseffects;

import java.util.EnumSet;
import java.util.concurrent.ConcurrentHashMap;

import common.craftpunk.api.forgepvp.statuseffects.IStatusEffectStack;
import common.craftpunk.api.forgepvp.statuseffects.IStatusEffectStackContainer;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;

import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class StatusEffectStackContainerHandler implements IPlayerTracker
{
	//public static ConcurrentHashMap<EntityLiving, IStatusEffectStackContainer> entityToSEffectContainer = new ConcurrentHashMap<EntityLiving, IStatusEffectStackContainer>(10 ^ 5);
	public static ConcurrentHashMap<String, PlayerStatusEffectContainer> playerNameToSEffectContainer = new ConcurrentHashMap<String, PlayerStatusEffectContainer>(10 ^ 5);

    public void onPlayerLogin(EntityPlayer player)
    {
    	if (!playerNameToSEffectContainer.containsKey(player.username))
    	{
    		PlayerStatusEffectContainer playerSEffectContainer = new PlayerStatusEffectContainer(player);
    		playerNameToSEffectContainer.put(player.username, playerSEffectContainer);
    	}
    }

    /**
     * removes the player from the list if they have no remaining status effect stacks on them.
     */
    public void onPlayerLogout(EntityPlayer player)
    {
    	if (playerNameToSEffectContainer.get(player.username).statusEffectList.isEmpty())
    	{
    		playerNameToSEffectContainer.remove(player.username);
    	}
    }

    public void onPlayerChangedDimension(EntityPlayer player)
    {
    	
    }

    /**
     * removes all status effect stacks which do not persist after death from the player
     */
    public void onPlayerRespawn(EntityPlayer player)
    {
    	/**
    	 * re
    	 */
    	PlayerStatusEffectContainer container = playerNameToSEffectContainer.get(player.username);
    	for(IStatusEffectStack stack: container.statusEffectList)
    	{
    		if (! stack.persistsAfterDeath())
    		{
    			container.statusEffectList.remove(stack);
    		}
    	}
    }
}
