package common.craftpunk.api.minepunk.world.metals;

import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import common.craftpunk.api.core.IColor;

//what you actually make stuff out of
public interface ISubstance extends IColor
{
	/**
	 * The name to use when naming blocks and items with the substance name in them.
	 * @return
	 */
	public String getName();
	public String getUnlocalizedName();
	
	
	
	/**
	 * Should return the 'ingot' for the item.
	 * That is, the item that would be used for say a pickaxe.
	 * @return
	 */
	public ItemStack getIngot();


	/**
	 * This should return false if getStorageBlock returns null.
	 * @return
	 */
	public boolean hasStorageBlock();
	
	/**
	 * Should return the storageblock or null if one does not exist.
	 * @return
	 */
	public ItemStack getStorageBlock();
	
	
	/**
	 * This should return false if getDust returns null.
	 * @return
	 */
	public boolean hasDust();
	
	/**
	 * This should return the dust or null if there is not any dust. 
	 * @return
	 */
	public ItemStack getDust();

	
	
	/**
	 * The amount of red used to tint items and blocks. No more than 1.0f
	 * @return
	 */
	public float getRed();
	
	/**
	 * The amount of green used to tint items and blocks. No more than 1.0f
	 * @return
	 */
	public float getGreen();
	
	/**
	 * The amount of blue used to tint items and blocks. No more than 1.0f
	 * @return
	 */
	public float getBlue();
	
	
	
	/**
	 * The default hardness for blocks made out of this substance.
	 * @return
	 */
	public float getBlockHardness();
	
	/**
	 * The default resistance to explosions for blocks made out of this substance.
	 * @return
	 */
	public float getBlockResistance();
	
	/**
	 * The default opacity for blocks made out of this substance.
	 * @return
	 */
	public int getBlockOpacity();
	
	/**
	 * The default light value for blocks made out of this substance.
	 * @return
	 */
	public float getBlockLightValue();
	
	/**
	 * The default step sound for blocks made out of this substance.
	 * @return
	 */
	public StepSound getBlockStepSound();
	
	/**
	 * The default material for blocks made out of this substance.
	 * @return
	 */
	public Material getBlockMaterial();
	
	
	
	/**
	 * The default tool and armor enchantability for tools made out of this substance.
	 * @return
	 */
	public int getEnchantability();
	
	
	
	/**
	 * The default tool harvest level for tools made out of this substance.
	 * @return
	 */
	public int getToolHarvestLevel();
	
	/**
	 * The default tool max uses for tools made out of this substance.
	 * @return
	 */
	public int getToolMaxUses();
	
	/**
	 * The default tool efficiency for tools made out of this substance.
	 * @return
	 */
	public float getToolEfficiency();
	
	/**
	 * The default tool damage for tools made out of this substance.
	 * @return
	 */
	public int getToolDamage();

	
	
	
	/**
	 * The default armor durability multiplier for armor made out of this substance.
	 * @return
	 */
	public int getArmorDurability();
	
	/**
	 * 
	 * @param index 0 for head, 1 for chest, 2 for legs, 3 for boots.
	 * @return The default reduction amount for armor in that slot.
	 */
	public int getArmorReductionAmount(int index);
}
