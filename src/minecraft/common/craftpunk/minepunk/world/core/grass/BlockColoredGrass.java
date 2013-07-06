package common.craftpunk.minepunk.world.core.grass;

import common.craftpunk.core.util.Color;

import net.minecraft.world.IBlockAccess;

public class BlockColoredGrass extends BlockPureGrass 
{
	Color color;
	
	public BlockColoredGrass(int id)
	{
		super(id);
	}
	
	
    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
    	
    	return color.getColorInt();
    }

}
