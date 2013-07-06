package common.craftpunk.api.forgepvp.combatlogger;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;

public interface ICombatLogHandler extends IPlayerTracker
{
	
	public boolean addCombatTag(String source, EntityPlayer player);
	
	public boolean removeCombatTag(String source, EntityPlayer player);

}
