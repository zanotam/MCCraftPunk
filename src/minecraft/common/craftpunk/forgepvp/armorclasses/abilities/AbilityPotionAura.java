package common.craftpunk.forgepvp.armorclasses.abilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import common.craftpunk.api.forgepvp.armorclasses.IAbility;

public class AbilityPotionAura implements IAbility
{
	public int potionID;
	public int potionLevel;
	public int durationInTicks;
	public double rangeInBlocks;
	public static int baseDurationTicks = 100;

	/**
	 * Creates an armor ability which will apply the potion effect with the given id with duration 5 seconds
	 * at the given level + 1 (in minecraft potion level of 0 would give you something like Speed I).
	 * @param potID
	 * @param potLevel
	 */
	public AbilityPotionAura(int potID, int potLevel, double range)
	{
		this(potID, potLevel, baseDurationTicks, range);
		//this.effect = new PotionEffect(potID, 60, potLevel);
	}
	
	/**
	 * Creates an armor ability which will apply the potion effect with the given id
	 * at the given level + 1 (in minecraft potion level of 0 would give you something like Speed I).
	 * @param potID
	 * @param potLevel
	 * @param duration
	 */
	public AbilityPotionAura(int potID, int potLevel, int duration, double range)
	{
		this.potionID = potID;
		this.potionLevel = potLevel;
		this.durationInTicks = duration;
		this.rangeInBlocks = range;
		//this.effect = new PotionEffect(potID, 60, potLevel);
	}
	
	public PotionEffect getEffect()
	{
		return new PotionEffect(this.potionID, this.durationInTicks, this.potionLevel);
	}

	@Override
	public String getName()
	{
		String potionName = Potion.potionTypes[this.potionID].getName().substring(7);
		return "Armor Ability Aura Recurring Potion On Self: " + potionName + " " + (this.potionLevel + 1);
	}

	@Override
	public void activate(EntityPlayer player)
	{
		// TODO Auto-generated method stub
		player.addPotionEffect(this.getEffect());
	}

	@Override
	public void deactivate(EntityPlayer player)
	{
		//nothing here
	}

	@Override
	public int getCD()
	{
		return this.durationInTicks - 10;
	}

	@Override
	public int getInitialCD()
	{
		return this.getCD();
	}

	@Override
	public boolean getRecurring()
	{
		return true;
	}

}
