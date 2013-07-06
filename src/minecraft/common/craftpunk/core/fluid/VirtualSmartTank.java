package common.craftpunk.core.fluid;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.LiquidStack;

public class VirtualSmartTank implements IFluidTank {

	public FluidStack fluid;
	public int capacity;

	public VirtualSmartTankContainer owner;
	
	public VirtualSmartTank(FluidStack fluidToFill, int maxCapacity, VirtualSmartTankContainer maker)
	{
		fluid = fluidToFill;
		capacity = maxCapacity;
		owner = maker;
	}

    /**
     * @return capacity of this tank
     */
    public int getCapacity()
    {
    	return capacity;
    }
    
	@Override
	public FluidStack getFluid() {
		return fluid;
	}

	@Override
	public int getFluidAmount() {
		return this.fluid.amount;
	}

	@Override
	public FluidTankInfo getInfo() {
		return new FluidTankInfo(this);
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) 
	{
    	int amountUsed = 0;
    	if (doFill)
    	{
    		if (this.fluid.equals(resource))
    		{
    			int sum = fluid.amount + resource.amount;
    			int remainingCapacity = this.getRemainingCapacity();
    			if (sum > remainingCapacity)
    			{
    				amountUsed = remainingCapacity - fluid.amount;
    				fluid.amount += remainingCapacity;
    			}
    			else
    			{
    				amountUsed = resource.amount;
    				fluid.amount = fluid.amount + amountUsed;
    			}
    		}
    	}
    	return amountUsed;
	}
	
    /**
     * 
     * @param maxDrain
     *            Maximum amount of fluid to be removed from the container.
     * @param doFill
     *            If false, the fill will only be simulated.
     * @return Amount of fluid that was removed from the tank.
     */
    public FluidStack drain(int maxDrain, boolean doDrain)
    {
    	FluidStack drained = null;
    	if(doDrain)
    	{
    		drained = new FluidStack(fluid, maxDrain);
    		if (maxDrain > fluid.amount)
    		{
    			drained.amount = fluid.amount;
    			fluid.amount = 0;
    		}
    		else
    		{
    			fluid.amount = fluid.amount - maxDrain;
    			drained.amount = maxDrain;
    		}
    	}
    	return drained;
    }
	
    public int getRemainingCapacity()
    {
    	int liquidInAllTanks = 0;
    	for (VirtualSmartTank vSmartTank: owner.virtualSmartTankList)
    	{
    		liquidInAllTanks += vSmartTank.getFluidAmount();
    	}
    	return capacity - liquidInAllTanks;
    }
    
    public VirtualSmartTank readFromNBT(NBTTagCompound nbt) {

        if (!nbt.hasKey("Empty")) {
            FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt);

            if (fluid != null) {
                setFluid(fluid);
            }
        }
        return this;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {

        if (fluid != null) {
            fluid.writeToNBT(nbt);
        } else {
            nbt.setString("Empty", "");
        }
        return nbt;
    }
    
    public void setFluid(FluidStack fluid) {

        this.fluid = fluid;
    }
}
