package common.craftpunk.forgepvp.combatlogger;

import common.craftpunk.core.util.Pair;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

public class CombatLoggerEventHandler
{
	public static int attackDurationTicks = 30*20;
	public static int attackPvPDurationTicks = 120*20;
	public static int hurtDurationTicks = 30*20;
	public static int hurtPvPDurationTicks = 300*20;
	
	@ForgeSubscribe(priority = EventPriority.LOW, receiveCanceled = false)
	public void playerAttacks(AttackEntityEvent event)
	{
		EntityPlayer player = event.entityPlayer;
		
		boolean attackedPlayer;
		if (event.target instanceof EntityPlayerMP)
		{
			attackedPlayer = true;
		}
		else
		{
			attackedPlayer = false;
		}
		
		if (attackedPlayer)
		{
			String pvpSourceString = "attackPvP";
			Pair<String, EntityPlayer> pvpSource = new Pair<String, EntityPlayer>(pvpSourceString, player);
			ForgeCombatLogger.combatLoggerTickHandler.addSource(pvpSource, attackPvPDurationTicks);
		}
		else
		{
			//nothing here
		}
		
		
		String attackSourceString = "attack";
		Pair<String, EntityPlayer> attackSource = new Pair<String,EntityPlayer>(attackSourceString, player);
		ForgeCombatLogger.combatLoggerTickHandler.addSource(attackSource, attackDurationTicks);
	}
	
	@ForgeSubscribe(priority = EventPriority.LOW, receiveCanceled = false)
	public void playerDamaged(LivingHurtEvent event)
	{
		if (event.entityLiving instanceof EntityPlayerMP)
		{
			//do registration here as ("hurt", player)
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			String hurtSourceString = "hurt";
			Pair<String, EntityPlayer> hurtSource = new Pair<String, EntityPlayer>(hurtSourceString, player);
			ForgeCombatLogger.combatLoggerTickHandler.addSource(hurtSource, hurtDurationTicks);
		}
	}

}
