package common.craftpunk.forgepvp.armorclasses.abilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ArcherDamageEventHandler
{
	@ForgeSubscribe(priority = EventPriority.LOWEST, receiveCanceled = false)
	public void archerAttack(LivingHurtEvent event)
	{
		if (event.source.isProjectile() && event.source.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.source.getEntity();
			double distanceInFlight = player.getDistanceToEntity(event.source.getSourceOfDamage());
			if ( AbilityArcherDamage.playerToAbilityMap.containsKey(player) )
			{
				AbilityArcherDamage ability = AbilityArcherDamage.playerToAbilityMap.get(player);
				double multiplier = 1.0 + ability.damageMultiplier*(distanceInFlight / ability.distanceMultipliedOver);
				float damage = (float) (event.ammount * multiplier);
				String message = "You hit " +  " from " + distanceInFlight + " blocks for " + (damage - event.ammount) + " BONUS damage.";
				player.sendChatToPlayer(new ChatMessageComponent().func_111079_a(message));
				event.ammount = Math.round(damage);

			}
			String message = "You hit " +  " from " + distanceInFlight + " blocks for " + event.ammount + " damage.";
			player.sendChatToPlayer(new ChatMessageComponent().func_111079_a(message));
		}
	}

}
