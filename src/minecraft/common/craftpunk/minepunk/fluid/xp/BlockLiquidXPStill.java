package common.craftpunk.minepunk.fluid.xp;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;
import net.minecraftforge.liquids.ILiquid;
import net.minecraft.util.Icon;

public class BlockLiquidXPStill extends BlockStationary implements ILiquid
{

	protected BlockLiquidXPStill(int par1)
	{
		super(par1, Material.water);
	}

	@Override
	public int stillLiquidId()
	{
		return this.blockID;
	}

	@Override
	public boolean isMetaSensitive()
	{
		return false;
	}

	@Override
	public int stillLiquidMeta()
	{
		return 0;
	}

	// once again the following is basically stolen from buildcraft oil
	@Override
	public int getRenderType()
	{
		// TODO figure out what the fuck goes here.
		return 0;
		//return BuildCraftCore.oilModel;
	}

	@Override
	public boolean isBlockReplaceable(World world, int i, int j, int k)
	{
		return true;
	}

	/*
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		// TODO set icon properly
		this.theIcon = new Icon[] { iconRegister.registerIcon("buildcraft:oil"), iconRegister.registerIcon("buildcraft:oil_flow") };
	}
	*/
}
