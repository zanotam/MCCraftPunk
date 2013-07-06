package common.craftpunk.minepunk.world.metals.mineral;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import common.craftpunk.api.core.IColor;
import common.craftpunk.api.minepunk.world.metals.ICrushable;
import common.craftpunk.api.minepunk.world.metals.IMineral;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class Mineral implements ICrushable, IMineral
{
	public String name;
	public List<Block> ores;

	public boolean dropsSelf;
	//Don't set the following if you have dropsSelf set to true!
	public ItemStack baseDrop;
	public int baseDropMax;
	public int baseDropMin;
	
	public ItemStack primaryCrushableItem;
	public double primaryCrushableBaseChance;
	public ItemStack secondaryCrushableItem;
	public double secondaryCrushableBaseChance;
	public ItemStack tertiaryCrushableItem;
	public double tertiaryCrushableBaseChance;
	
	public ConcurrentHashMap<String, Icon> iconMap;
	public Icon vanillaMetalOreOverlay;
	public Icon vanillaLapisOreOverlay;
	public Icon vanillaEmeraldOreOverlay;
	public Icon vanillaNetherQuartzOreOverlay;
	public Icon customOreOverlay;
	
	public int baseBlockLevelToMine;
	
	public int baseVeinCount;
	public int baseOreCount;
	public int baseMinHeight;
	public int baseMaxHeight;
	public int baseVeinChance;
	public int baseVeinDensity;
	
	public Mineral(String name)
	{
	    super();
	    this.name = name;
	    this.ores = new ArrayList<Block>();
	}
	
	public Mineral()
	{
	    
	}
	
	public String getName()
	{
	    return this.name;
	}
	
	public boolean getDropsSelf()
	{
	    return dropsSelf;
	}
	
	public ItemStack getBaseDrop()
	{
	    return baseDrop;
	}
	
	public int getBaseDropMax()
	{
	    return baseDropMax;
	}
	
	public int getBaseDropMin()
	{
	    return baseDropMin;
	}
	
	public ConcurrentHashMap<String, Icon> getIconMap()
	{
	    return iconMap;
	}
	
	public int baseBlockLevelToMine()
	{
	    return baseBlockLevelToMine;
	}
	
	
	
	
	
	
    @Override
    public ItemStack getPrimaryElement()
    {
        return primaryCrushableItem;
    }
    
    
    @Override
    public double getPrimaryBaseChance()
    {
        return primaryCrushableBaseChance;
    }
    
    @Override
    public ItemStack getSecondaryElement()
    {
        return secondaryCrushableItem;
    }
    
    @Override
    public double getSecondaryBaseChance()
    {
        return secondaryCrushableBaseChance;
    }
    
    @Override
    public ItemStack getTertiaryElement()
    {
        return tertiaryCrushableItem;
    }
    
    @Override
    public double getTertiaryBaseChance()
    {
        return tertiaryCrushableBaseChance;
    }
}
