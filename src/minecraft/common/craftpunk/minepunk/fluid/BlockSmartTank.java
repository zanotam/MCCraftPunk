package common.craftpunk.minepunk.fluid;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockSmartTank extends Block //implements ITileEntityProvider
{
	public BlockSmartTank(int id, Material material)
	{
		super(id, material);
		this.blockIcon = new ItemStack(54,1,0).getIconIndex();
	}
	
	public BlockSmartTank(int id)
	{
		super(id, Material.iron);
        this.setCreativeTab(CreativeTabs.tabDecorations);
		this.blockIcon = new ItemStack(54,1,0).getIconIndex();
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("wood");
	}

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World)
    {
        TileEntitySmartTank tileentitysmarttank = new TileEntitySmartTank();
        return tileentitysmarttank;
    }
    
    @Override
    public Icon getIcon(int i, int j)
    {
    	return this.blockIcon;
    }

}
