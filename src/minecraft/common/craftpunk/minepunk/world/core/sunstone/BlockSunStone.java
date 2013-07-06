package common.craftpunk.minepunk.world.core.sunstone;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSunStone extends Block implements ITileEntityProvider
{
	public BlockSunStone(int id)
	{
		super(id, Material.glass);
		this.setStepSound(soundGlassFootstep).setLightValue(1.0F);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World)
    {
    	return new TileEntitySunStone();
    }
    
    /**
     * makes the block 'clear' like air.
     */
    @Override
    public int getLightOpacity(World world, int x, int y, int z)
    {
    	return 0;
    }
    
    /**
     * this is just toa make life easier for the tileentity mostly and reducing rerender calls.
     */
    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
    	return 15;
    }
    
    
    public Icon getIcon(int par1, int par2)
    {
        return glowStone.getIcon(par1, par2);
    }
}
