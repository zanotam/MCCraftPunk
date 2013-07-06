package common.craftpunk.forgepvp.armorclasses.abilities;

import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import common.craftpunk.api.forgepvp.armorclasses.IAbility;

public class AbilityArcherDamage implements IAbility
{
	public int distanceMultipliedOver;
	public int damageMultiplier;
	public static ConcurrentHashMap<EntityPlayer, AbilityArcherDamage> playerToAbilityMap = new ConcurrentHashMap<EntityPlayer, AbilityArcherDamage>(10^4);
	
	public AbilityArcherDamage(int multiplier, int distance)
	{
		this.damageMultiplier = multiplier;
		this.distanceMultipliedOver = distance;
	}

	@Override
	public String getName()
	{
		return "Archer " + distanceMultipliedOver + " " + damageMultiplier;
	}

	@Override
	public void activate(EntityPlayer player)
	{
		playerToAbilityMap.put(player, this);
	}

	@Override
	public void deactivate(EntityPlayer player)
	{
		playerToAbilityMap.remove(player, this);
	}

	@Override
	public int getCD()
	{
		return 0;
	}

	@Override
	public int getInitialCD()
	{
		return 0;
	}

	@Override
	public boolean getRecurring()
	{
		return false;
	}
}
