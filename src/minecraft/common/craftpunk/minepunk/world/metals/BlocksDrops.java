package common.craftpunk.minepunk.world.metals;

import net.minecraft.item.ItemStack;

public class BlocksDrops
{
    
    public boolean dropsSelf;
    //Don't set the following if you have dropsSelf set to true!
    public ItemStack baseDrop;
    public int baseDropMax;
    public int baseDropMin;
    
    public boolean getDropsSelf()
    {
        return this.dropsSelf;
    }
    
    public ItemStack getBaseDrop()
    {
        return this.baseDrop;
    }
    
    public int getBaseDropMax()
    {
        return this.baseDropMax;
    }
    
    public int getBaseDropMin()
    {
        return this.baseDropMin;
    }
}
