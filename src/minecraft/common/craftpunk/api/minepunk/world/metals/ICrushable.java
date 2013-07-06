package common.craftpunk.api.minepunk.world.metals;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface ICrushable 
{
    /**
     * @return The item which when should go in the primary slot upon crushing.
     */
	public ItemStack getPrimaryElement();
	
	/**
	 * Gets the 'chance' of the primary element appearing when being crushed
	 * where a chance of n.x is guarantees at least n will appear every time it is crushed
	 * and an additional probability where 'x' is in essence measured on a scale from 0 to 1.0,
	 * with 1.0 obviously being 100% chance and thus adding to the 'n' category.
	 * Note that things like the IC macerator should DOUBLE the base chance, so that 
	 * @return The chance that primary base element will appear when crushed with 1.0 being 100% for one.
	 */
	public double getPrimaryBaseChance();
	
	
    /**
     * @return The item which when should go in the secondary slot upon crushing.
     */
	public ItemStack getSecondaryElement();
	
	 /**
     * Gets the 'chance' of the second element appearing when being crushed
     * where a chance of n.x is guarantees at least n will appear every time it is crushed
     * and an additional probability where 'x' is in essence measured on a scale from 0 to 1.0,
     * with 1.0 obviously being 100% chance and thus adding to the 'n' category.
     * Note that things like the IC macerator should DOUBLE the base chance, so that 
     * @return The chance that secondary base element will appear when crushed with 1.0 being 100% for one.
     */
	public double getSecondaryBaseChance();
	
	
    /**
     * @return The item which when should go in the tertiary slot upon crushing.
     */
	public ItemStack getTertiaryElement();
	
	 /**
     * Gets the 'chance' of the tertiary element appearing when being crushed
     * where a chance of n.x is guarantees at least n will appear every time it is crushed
     * and an additional probability where 'x' is in essence measured on a scale from 0 to 1.0,
     * with 1.0 obviously being 100% chance and thus adding to the 'n' category.
     * Note that things like the IC macerator should DOUBLE the base chance, so that 
     * @return The chance that tertiary base element will appear when crushed with 1.0 being 100% for one.
     */
	public double getTertiaryBaseChance();
}
