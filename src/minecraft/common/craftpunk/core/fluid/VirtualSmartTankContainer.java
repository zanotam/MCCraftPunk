package common.craftpunk.core.fluid;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;


import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;


public class VirtualSmartTankContainer implements IFluidHandler {
	
	public ArrayList<VirtualSmartTank> virtualSmartTankList;
	public ConcurrentHashMap<FluidStack,VirtualSmartTank> FluidToVirtualSmartTankMap;
	public int maxCapacity;
	public ConcurrentHashMap<ForgeDirection, VirtualSmartTank> directionToTankMap;
	
	public VirtualSmartTankContainer(int capacity)
	{
		this.maxCapacity = capacity;
		ConcurrentHashMap<String, Fluid> Fluids = (ConcurrentHashMap<String, Fluid>) FluidRegistry.getRegisteredFluids();
		for (Fluid fluid: Fluids.values())
		{
			this.getOrCreateTankForFluid(new FluidStack(fluid, 0));
		}
		directionToTankMap.put(ForgeDirection.DOWN, this.getTanks()[0]);
		directionToTankMap.put(ForgeDirection.UP, this.getTanks()[0]);
		directionToTankMap.put(ForgeDirection.NORTH, this.getTanks()[0]);
		directionToTankMap.put(ForgeDirection.EAST, this.getTanks()[0]);
		directionToTankMap.put(ForgeDirection.SOUTH, this.getTanks()[0]);
		directionToTankMap.put(ForgeDirection.WEST, this.getTanks()[0]);
	}
	
	
	/**
     * Fills Fluid into internal tanks, distribution is left to the ITankContainer.
     * @param from Orientation the Fluid is pumped in from.
     * @param resource FluidStack representing the maximum amount of Fluid filled into the ITankContainer
     * @param doFill If false filling will only be simulated.
     * @return Amount of resource that was filled into internal tanks.
     */
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
    {
    	VirtualSmartTank tank = getOrCreateTankForFluid(resource);
    	int filled = tank.fill(resource, doFill);
    	return filled;
    }
    /**
     * Fills Fluid into the specified internal tank.
     * @param tankIndex the index of the tank to fill
     * @param resource FluidStack representing the maximum amount of Fluid filled into the ITankContainer
     * @param doFill If false filling will only be simulated.
     * @return Amount of resource that was filled into internal tanks.
     */
    public int fill(int tankIndex, FluidStack resource, boolean doFill)
    {
    	VirtualSmartTank tank = getOrCreateTankForFluid(resource);
    	int filled = tank.fill(resource, doFill);
    	return filled;
    }

    /**
     * Drains Fluid out of internal tanks, distribution is left to the ITankContainer.
     * @param from Orientation the Fluid is drained to.
     * @param maxDrain Maximum amount of Fluid to drain.
     * @param doDrain If false draining will only be simulated.
     * @return FluidStack representing the Fluid and amount actually drained from the ITankContainer
     */
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
    {
    	VirtualSmartTank tank = this.directionToTankMap.get(from);
    	FluidStack drained = tank.drain(maxDrain, doDrain);
    	return drained;
    }
    /**
     * Drains Fluid out of the specified internal tank.
     * @param tankIndex the index of the tank to drain
     * @param maxDrain Maximum amount of Fluid to drain.
     * @param doDrain If false draining will only be simulated.
     * @return FluidStack representing the Fluid and amount actually drained from the ITankContainer
     */
    public FluidStack drain(int tankIndex, int maxDrain, boolean doDrain)
    {
    	VirtualSmartTank tank = this.virtualSmartTankList.get(tankIndex);
    	FluidStack drained = tank.drain(maxDrain, doDrain);
    	return drained;
    }
    
    public VirtualSmartTank getOrCreateTankForFluid(FluidStack resource)
    {
    	VirtualSmartTank tank = FluidToVirtualSmartTankMap.get(resource);
    	if (tank.equals(null))
    	{
    		tank = new VirtualSmartTank(resource, this.maxCapacity, this);
    		virtualSmartTankList.add(tank);
    		FluidToVirtualSmartTankMap.put(resource, tank);
    	}
    	return tank;
    }
    
    public boolean setTankFacing(ForgeDirection facing, FluidStack Fluid)
    {
    	VirtualSmartTank tank = getOrCreateTankForFluid(Fluid);
    	directionToTankMap.put(facing, tank);
    	return true;
    }
    
    public VirtualSmartTank[] getTanks()
    {
    	VirtualSmartTank[] virtualSmartTankArray = new VirtualSmartTank[virtualSmartTankList.size()+1];
    	return virtualSmartTankList.toArray(virtualSmartTankArray);
    }



	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
    	FluidTankInfo[] virtualFluidArray= new FluidTankInfo[virtualSmartTankList.size()+1];
    	int i = 0;
    	for (VirtualSmartTank tank: this.virtualSmartTankList)
    	{
    		virtualFluidArray[i] = tank.getInfo();
    	}
    	return virtualFluidArray;
	}
	

    public void readFromNBT(NBTTagCompound tag) {

        for (VirtualSmartTank tank: virtualSmartTankList)
        {
        	tank.readFromNBT(tag);
        }
    }


    public void writeToNBT(NBTTagCompound tag) {
        for (VirtualSmartTank tank: virtualSmartTankList)
        {
        	tank.writeToNBT(tag);
        }
    }
}
