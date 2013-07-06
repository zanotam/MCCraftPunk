package common.craftpunk.forgepvp.combatlogger;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;
import java.util.Vector;

import common.craftpunk.core.util.Pair;


public class CombatLoggerPlayerTracker implements IPlayerTracker
{
	public Vector<Pair<String, EntityPlayer>> taggedPlayerList = new Vector<Pair<String, EntityPlayer>>();
	public boolean tagLogin = false;
	public int loginTagTicks = 100;
	public boolean tagDimensionChange = false;
	public int dimTagTicks = 100;
	public boolean tagRespawn = false;
	public int respawnTagTicks = 100;

	@Override
	public void onPlayerLogin(EntityPlayer player)
	{
		if (tagLogin)
		{
			
		}
	}

	@Override
	public void onPlayerLogout(EntityPlayer player)
	{
		//nothing here
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player)
	{
		if (tagDimensionChange)
		{
			
		}
		
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player)
	{
		if(tagRespawn)
		{
			
		}
		
	}
}
