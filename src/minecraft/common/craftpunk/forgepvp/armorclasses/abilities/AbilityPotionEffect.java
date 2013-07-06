package common.craftpunk.forgepvp.armorclasses.abilities;

import common.craftpunk.api.forgepvp.armorclasses.IAbility;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;


public class AbilityPotionEffect implements IAbility
{
	public int potionID;
	public int potionLevel;
	public int durationInTicks;
	public static int baseDurationTicks = 100;
	//public PotionEffect effect;
	
	/**
	 * Creates an armor ability which will apply the potion effect with the given id with duration 5 seconds
	 * at the given level + 1 (in minecraft potion level of 0 would give you something like Speed I).
	 * @param potID
	 * @param potLevel
	 */
	public AbilityPotionEffect(int potID, int potLevel)
	{
		this(potID, potLevel, baseDurationTicks);
		//this.effect = new PotionEffect(potID, 60, potLevel);
	}
	
	/**
	 * Creates an armor ability which will apply the potion effect with the given id
	 * at the given level + 1 (in minecraft potion level of 0 would give you something like Speed I).
	 * @param potID
	 * @param potLevel
	 * @param duration
	 */
	public AbilityPotionEffect(int potID, int potLevel, int duration)
	{
		this.potionID = potID;
		this.potionLevel = potLevel;
		this.durationInTicks = duration;
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
		
		return "Armor Ability Recurring Potion On Self: " + potionName + " " + (this.potionLevel + 1);
	}
	
	@Override
	public void activate(EntityPlayer player)
	{
		player.addPotionEffect(this.getEffect());
	}
	
	
	@Override
	public void deactivate(EntityPlayer player)
	{
		//does nothing
	}
	
	@Override
	public int getCD()
	{
		return this.durationInTicks - 10;
	}
	
	@Override
	public boolean getRecurring()
	{
		return true;
	}
	
	@Override
	public int getInitialCD()
	{
		return this.getCD();
	}

}
