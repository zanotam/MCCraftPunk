package common.craftpunk.forgepvp.combatlogger;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class CombatLogger extends EntityPlayer
{
	public EntityPlayerMP copiedPlayer;
	public WorldServer worldServer;
	
	public CombatLogger(EntityPlayerMP realPlayer, WorldServer worldServer)
	{
		//super(realPlayer.mcServer, realPlayer.worldObj, realPlayer.username, realPlayer.theItemInWorldManager);
		super(realPlayer.worldObj, realPlayer.username);
	    this.clonePlayer(realPlayer, true);
		this.playerLocation = realPlayer.playerLocation;
		this.copiedPlayer = realPlayer;
		this.worldServer = worldServer;
	}
	
	@Override 
	public void onDeath(DamageSource damageSource)
	{
		super.onDeath(damageSource);
		this.worldServer.getSaveHandler().getSaveHandler().writePlayerData(this);
	}

	/*
	@Override
	public void sendChatToPlayer(String s)
	{
		//nothing here
	}
	*/

	@Override
	public boolean canCommandSenderUseCommand(int i, String s)
	{
		return false;
	}

	@Override
	public ChunkCoordinates getPlayerCoordinates()
	{
		return this.playerLocation;
	}

	public WorldServer getServerForPlayer()
	{
	    return this.worldServer;
	}

	@Override
	public void sendChatToPlayer(ChatMessageComponent chatmessagecomponent) {
		//nothing here
	}
}
