package common.craftpunk.core;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.FakePlayer;

public class EntityPlayerOwner extends FakePlayer
{
	
	public Entity ownedEntity;
	public EntityPlayer playerOwner;
	public boolean isLiving;

	public EntityPlayerOwner(World world, EntityPlayer owner, Entity owned)
	{
		super(world, owner.username + owned.entityId);
		this.ownedEntity = owned;
		this.playerOwner = owner;
		if (this.ownedEntity instanceof EntityLiving)
		{
			this.isLiving = true;
		}
	}
	
    public ChunkCoordinates getPlayerCoordinates()
    {
        return new ChunkCoordinates(ownedEntity.chunkCoordX,ownedEntity.chunkCoordY,ownedEntity.chunkCoordZ);
    }

}
