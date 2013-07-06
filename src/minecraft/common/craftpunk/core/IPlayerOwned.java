package common.craftpunk.core;

import net.minecraft.entity.player.EntityPlayer;

/**
 * this is designed to go with the TilePlayerOwner and EntityPlayerOwner classes
 * which allow you to attach a player to an entity or tile for easy tracking. 
 * They also create a fake player at the location of the entity or tile, in addition to
 * keeping track of the 'real' player owner.
 * @author Robert
 *
 */
public interface IPlayerOwned
{
	public EntityPlayer getOwner();
	
	
	public EntityPlayer getOwned();
}
