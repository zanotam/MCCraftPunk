package common.craftpunk.forgepvp.statuseffects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import common.craftpunk.api.forgepvp.statuseffects.IStatusEffect;
import common.craftpunk.api.forgepvp.statuseffects.IStatusEffectStack;
import common.craftpunk.api.forgepvp.statuseffects.IStatusEffectStackContainer;
import common.craftpunk.core.util.Pair;



import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerStatusEffectContainer implements IStatusEffectStackContainer
{
	public List<IStatusEffectStack> statusEffectList;
	public ConcurrentHashMap<IStatusEffect, Integer> statusEffectToNStacksMap;
	public ConcurrentHashMap<Pair<IStatusEffect, Object>, Integer> sEffectCauseToNStacksMap;
	public EntityPlayer player;

	public PlayerStatusEffectContainer(EntityPlayer thePlayer)
	{
		this.player = thePlayer;
		this.statusEffectList = Collections.synchronizedList(new ArrayList<IStatusEffectStack>());
		this.statusEffectToNStacksMap = new ConcurrentHashMap<IStatusEffect, Integer>();
		this.sEffectCauseToNStacksMap = new ConcurrentHashMap<Pair<IStatusEffect, Object>, Integer>();
	}
	
	public EntityLivingBase getEntity()
	{
		return this.player;
	}

	public IStatusEffectStack[] getStatusEffectStackArray()
	{
		return (IStatusEffectStack[]) this.statusEffectList.toArray();
	}

	public boolean addStatusEffectStack(IStatusEffectStack sEffectStack)
	{
		boolean canAdd = false;
		canAdd = this.canSEffectBeAdded(sEffectStack);

		if (canAdd)
		{
			if (sEffectStack.getAbility().doesStacksPerCause())
			{
				//not properly implemented yet
				this.applyPerCause(sEffectStack);
			}
			else
			{
				this.apply(sEffectStack);
			}
		}
		return canAdd;
	}

	
	public boolean canSEffectBeAdded(IStatusEffectStack sEffectStack)
	{
		return true;
	}
	
	public void apply(IStatusEffectStack sEffectStack)
	{
		IStatusEffect sEffect = sEffectStack.getAbility();

		int stackSizeMax = sEffect.getStackSizeMax();
		int nStacksMax = sEffect.getNStacksMax();

		int nStacks = statusEffectToNStacksMap.get(sEffect);

		if (nStacksMax > nStacks)
		{
				statusEffectList.add(sEffectStack);
				statusEffectToNStacksMap.put(sEffect, nStacks+1);
		}
		else
		{
			ArrayList<IStatusEffectStack> matchingSEffectList = new ArrayList<IStatusEffectStack>();
			for (IStatusEffectStack stack : statusEffectList)
			{
				if (stack.getAbility().getName().equals(sEffectStack.getAbility().getName()))
				{
					matchingSEffectList.add(stack);
				}
			}
			if (stackSizeMax > 1)
			{
				boolean addedToNonMaxStack = false;
				for (IStatusEffectStack stack : matchingSEffectList)
				{
					if (addedToNonMaxStack)
					{
						break;
					}
					else
					{
						if (stack.getLevel() < sEffect.getLevelMax())
						{
							stack = sEffectStack.applyToStack(stack);
						}
					}
				}
				if (!addedToNonMaxStack)
				{
					IStatusEffectStack oldStack = matchingSEffectList.get(0);
					IStatusEffectStack changedStack = sEffectStack.applyToStack(oldStack);
					sEffect.deactivate(oldStack, this.getEntity());
					oldStack = changedStack;
				}
			}
			else
			{
				IStatusEffectStack oldStack = matchingSEffectList.get(0);
				IStatusEffectStack changedStack = sEffectStack.applyToStack(oldStack);
				sEffect.deactivate(oldStack, this.getEntity());
				oldStack = changedStack;
			}
		}
		
	}
	
	//not actually implemented yet
	protected void applyPerCause(IStatusEffectStack sEffectStack)
	{
		IStatusEffect sEffect = sEffectStack.getAbility();

		int stackSizeMax = sEffect.getStackSizeMax();
		int nStacksMax = sEffect.getNStacksMax();
		int nStacksPerCauseMax = sEffect.getNStacksPerCauseMax();
		int nCausesMax = sEffect.getNCausesMax();
	}
}
