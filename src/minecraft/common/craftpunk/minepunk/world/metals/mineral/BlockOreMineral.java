package common.craftpunk.minepunk.world.metals.mineral;

import common.craftpunk.api.minepunk.world.metals.ICrushable;
import common.craftpunk.api.minepunk.world.metals.IMineral;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

public class BlockOreMineral extends Block implements ICrushable
{
    public IMineral mineral;

    public BlockOreMineral(int par1, Material par2Material)
    {
        super(par1, par2Material);
        // TODO Auto-generated constructor stub
    }

    @Override
    public ItemStack getPrimaryElement()
    {
        return mineral.getPrimaryElement();
    }

    @Override
    public double getPrimaryBaseChance()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ItemStack getSecondaryElement()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getSecondaryBaseChance()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ItemStack getTertiaryElement()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getTertiaryBaseChance()
    {
        // TODO Auto-generated method stub
        return 0;
    }
    

}
