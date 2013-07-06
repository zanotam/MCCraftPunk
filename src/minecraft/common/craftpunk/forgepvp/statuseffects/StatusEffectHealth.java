package common.craftpunk.forgepvp.statuseffects;

import common.craftpunk.api.forgepvp.statuseffects.IStatusEffectStack;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class StatusEffectHealth extends AbstractStatusEffect
{

	@Override
	public int activate(IStatusEffectStack sEffectStack, EntityLivingBase target)
	{
		if(EntityPlayer.class.isInstance(target))
		{
			EntityPlayer player = (EntityPlayer) target;
			//player.maxHealth += sEffectStack.getLevel()*sEffectStack.getAmount();
		}
		return Integer.MAX_VALUE;
	}

	@Override
	public void deactivate(IStatusEffectStack sEffectStack, EntityLivingBase target)
	{
		if(EntityPlayer.class.isInstance(target))
		{
			EntityPlayer player = (EntityPlayer) target;
		//	player.maxHealth -= sEffectStack.getLevel()*sEffectStack.getAmount();
		}
		
	}

}
