package common.craftpunk.minepunk.world.metals;

import java.util.ArrayList;

import common.craftpunk.api.minepunk.world.metals.ISubstance;
import common.craftpunk.core.util.Color;
import common.craftpunk.minepunk.world.metals.mineral.Mineral;

import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Metal extends Mineral implements ISubstance
{
    
    public Color primaryColor;
    
    public ItemStack ingot;
    public boolean hasDust;
    public ItemStack dust;
    public boolean hasStorageBlock;
    public ItemStack storageBlock;
    
    
    
    public int enchantability;
    
    public int toolMiningLevel;
	public int toolDurability;
	public int toolMiningSpeed;
	public int toolAttackDamage;


	
	public int armorDurability;
	public int helmetBaseArmorValue;
	public int chestBaseArmorValue;
	public int legsBaseArmorValue;
	public int bootsBaseArmorValue;
	
	public float baseBlockHardness;
	public float baseBlockResistance;
	public int baseBlockOpacity;
	public float baseBlockLightValue;
	public StepSound baseBlockStepSound;
	public Material baseBlockMaterial;
	
	/*
	public Item pick;
	public Item axe;
	public Item shovel;
	public Item sword;
	public Item hoe;
	public Item[] tools;
	
	public Item helmet;
	public Item chest;
	public Item legs;
	public Item boots;
	public Item[] armor;
	
	public Block[] blocks;
	*/
	
	public Metal(String metalName)
	{
		super(metalName);
	}


    @Override
    public float getRed()
    {
        return this.primaryColor.getRed();
    }
    
    @Override
    public float getBlue()
    {
        return this.primaryColor.getBlue();
    }
    
    @Override
    public float getGreen()
    {
        return this.primaryColor.getGreen();
    }


    @Override
    public String getUnlocalizedName()
    {
        return this.name;
    }


    @Override
    public ItemStack getIngot()
    {
        return this.ingot;
    }


    @Override
    public boolean hasStorageBlock()
    {
        return this.hasStorageBlock;
    }


    @Override
    public ItemStack getStorageBlock()
    {
        return this.storageBlock;
    }


    @Override
    public boolean hasDust()
    {
       return this.hasDust;
    }


    @Override
    public ItemStack getDust()
    {
        return this.dust;
    }

    @Override
    public float getBlockHardness()
    {
        return this.baseBlockHardness;
    }


    @Override
    public float getBlockResistance()
    {
        return this.baseBlockResistance;
    }


    @Override
    public int getBlockOpacity()
    {
        return this.baseBlockOpacity;
    }


    @Override
    public float getBlockLightValue()
    {
        return this.baseBlockLightValue;
    }


    @Override
    public StepSound getBlockStepSound()
    {
        return this.baseBlockStepSound;
    }


    @Override
    public Material getBlockMaterial()
    {
        return this.baseBlockMaterial;
    }


    @Override
    public int getEnchantability()
    {
        return this.enchantability;
    }


    @Override
    public int getToolHarvestLevel()
    {
       return this.toolMiningLevel;
    }


    @Override
    public int getToolMaxUses()
    {
        return this.toolDurability;
    }


    @Override
    public float getToolEfficiency()
    {
        return this.toolMiningSpeed;
    }


    @Override
    public int getToolDamage()
    {
        return this.toolAttackDamage;
    }


    @Override
    public int getArmorDurability()
    {
        return this.armorDurability;
    }


    @Override
    public int getArmorReductionAmount(int index)
    {
        int reduction = 0;
        switch (index)
        {
            case 0: reduction = this.helmetBaseArmorValue;
            case 1: reduction = this.chestBaseArmorValue;
            case 2: reduction = this.legsBaseArmorValue;
            case 3: reduction = this.bootsBaseArmorValue;
        }
        return reduction;
    }
}
