package common.craftpunk.minepunk.world.core.sunstone;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.chunk.Chunk;



public class TileEntitySunStone extends TileEntity 
{
	public int count = 20;
	

	
	public TileEntitySunStone()
	{
		super();
		this.count = 20;
	}
	

	
    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
    	this.count++;
    	if ((this.count) >= 20)
    	{
    		this.count = 0;
    		Chunk chunk = this.worldObj.getChunkFromBlockCoords(this.xCoord, this.zCoord);
    		//chunk.addSunstone(this.xCoord & 15, this.zCoord & 15);
    	}
    	//System.out.println("updated");
    	super.updateEntity();
    	/*
    	int radius = 10;
        //if (this.worldObj != null) // && this.worldObj.isRemote)
        //{
        	//this.worldObj.setLightValue(EnumSkyBlock.Sky, this.xCoord, this.yCoord, this.zCoord, 15);
        //if (!this.worldObj.isRemote)
    		for (int dx = -radius; dx <= radius; dx++)
        	{
        		for(int dy = -radius; dy <= radius; dy++)
        		{
        			for (int dz = -radius; dz <= radius; dz++)
        			{

        				this.worldObj.setLightValue(EnumSkyBlock.Sky, this.xCoord + dx, this.yCoord + dy, this.zCoord + dz, 15);
        			}
        		}
        	}
    		
       // }
        * */
    }
    
    @Override
    /**
     * invalidates a tile entity
     */
    public void invalidate()
    {
        
		Chunk chunk = this.worldObj.getChunkFromBlockCoords(this.xCoord, this.zCoord);
		//chunk.removeSunstone(this.xCoord & 15, this.zCoord & 15);
		int ySkip = this.yCoord;
		for (int y = 1; y < ySkip; y++)
		{
			this.worldObj.updateAllLightTypes(this.xCoord, y, this.zCoord);
		}
		for (int y = ySkip + 1; y < 256; y++)
		{
		    this.worldObj.updateAllLightTypes(this.xCoord, y, this.zCoord);
		}
		super.invalidate();
		
    }

}
