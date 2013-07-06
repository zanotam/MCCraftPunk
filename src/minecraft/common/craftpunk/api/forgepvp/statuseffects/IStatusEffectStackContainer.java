package common.craftpunk.api.forgepvp.statuseffects;

import java.util.ArrayList;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;

public interface IStatusEffectStackContainer
{
	//public ArrayList<IStatusEffectStack> statusEffectList;
	//public EntityLiving entity;

	public EntityLivingBase getEntity();
	
	public IStatusEffectStack[] getStatusEffectStackArray();
	
	public boolean addStatusEffectStack(IStatusEffectStack sEffectStack);
	
	
}
