package common.craftpunk.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.FakePlayer;

public class TilePlayerOwner extends FakePlayer
{
	public TileEntity ownedEntity;
	public EntityPlayer playerOwner;
	
	public TilePlayerOwner(World world, EntityPlayer owner, TileEntity owned)
	{
		super(world, owner.username + owned.blockType + owned.xCoord + owned.yCoord + owned.zCoord);
		this.ownedEntity = owned;
		this.playerOwner = owner;

	}
	
    public ChunkCoordinates getPlayerCoordinates()
    {
        return new ChunkCoordinates(ownedEntity.xCoord,ownedEntity.yCoord,ownedEntity.zCoord);
    }

}
