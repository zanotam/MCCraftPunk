package common.craftpunk.minepunk.fluid;

import common.craftpunk.core.fluid.VirtualSmartTankContainer;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class TileEntitySmartTank extends TileEntity implements IFluidHandler{
	
	VirtualSmartTankContainer container;
	public static int capacity = 1000*1000;
	
	public TileEntitySmartTank()
	{
		super();
		this.container = new VirtualSmartTankContainer(capacity);
	}
	
	/**
     * Fills fluid into internal tanks, distribution is left to the ITankContainer.
     * @param from Orientation the fluid is pumped in from.
     * @param resource FluidStack representing the maximum amount of fluid filled into the ITankContainer
     * @param doFill If false filling will only be simulated.
     * @return Amount of resource that was filled into internal tanks.
     */
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
    {
    	return container.fill(from, resource, doFill);
    }
    /**
     * Fills fluid into the specified internal tank.
     * @param tankIndex the index of the tank to fill
     * @param resource FluidStack representing the maximum amount of fluid filled into the ITankContainer
     * @param doFill If false filling will only be simulated.
     * @return Amount of resource that was filled into internal tanks.
     */
    public int fill(int tankIndex, FluidStack resource, boolean doFill)
    {
    	return container.fill(tankIndex, resource, doFill);
    }

    /**
     * Drains fluid out of internal tanks, distribution is left to the ITankContainer.
     * @param from Orientation the fluid is drained to.
     * @param maxDrain Maximum amount of fluid to drain.
     * @param doDrain If false draining will only be simulated.
     * @return FluidStack representing the fluid and amount actually drained from the ITankContainer
     */
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
    {
    	return container.drain(from, maxDrain, doDrain);
    }
    /**
     * Drains fluid out of the specified internal tank.
     * @param tankIndex the index of the tank to drain
     * @param maxDrain Maximum amount of fluid to drain.
     * @param doDrain If false draining will only be simulated.
     * @return FluidStack representing the fluid and amount actually drained from the ITankContainer
     */
    public FluidStack drain(int tankIndex, int maxDrain, boolean doDrain)
    {
    	return container.drain(tankIndex, maxDrain, doDrain);
    }



	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		return this.container.drain(from, resource, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return this.container.canFill(from, fluid);
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return this.canDrain(from, fluid);
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return this.container.getTankInfo(from);
	}

    @Override
    public void readFromNBT(NBTTagCompound tag) {

        super.readFromNBT(tag);
        container.writeToNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {

        super.writeToNBT(tag);
        container.readFromNBT(tag);
    }
}
