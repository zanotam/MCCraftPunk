package common.craftpunk.forgepvp.statuseffects;

import common.craftpunk.api.forgepvp.statuseffects.IStatusEffect;
import common.craftpunk.api.forgepvp.statuseffects.IStatusEffectStack;

public class StatusEffectStack implements IStatusEffectStack
{
	public IStatusEffect ability;
	public int level;
	public int amount;
	public int timeInTicks;
	public int activationTimeInTicks;
	public Object cause;
	
	public StatusEffectStack(IStatusEffect sEffect, int stackLevel, int stackAmount, int time, int activationTime, Object stackCause)
	{
		this.ability = sEffect;
		this.level = stackLevel;
		this.amount = stackAmount;
		this.timeInTicks = time;
		this.activationTimeInTicks = activationTime;
		this.cause = stackCause;
	}
	

	@Override
	public IStatusEffect getAbility()
	{
		return this.ability;
	}

	@Override
	public int getLevel()
	{
		return this.level;
	}

	@Override
	public int getTime()
	{
		return this.timeInTicks;
	}

	@Override
	public void decrementTimeInTicks(int decrementAmount)
	{
		this.timeInTicks -= 1;
		if (this.activationTimeInTicks != Integer.MAX_VALUE)
		{
			activationTimeInTicks -= 1;
		}
	}
	
	public void setActivationTime(int replacement)
	{
		this.activationTimeInTicks = replacement;
	}

	@Override
	public int getActivationTime()
	{
		return this.activationTimeInTicks;
	}

	@Override
	public int getAmount()
	{
		return this.amount;
	}

	@Override
	public Object getCause()
	{
		return this.cause;
	}

	@Override
	public boolean persistsAfterDeath()
	{
		return this.ability.ifPersistsAfterDeath();
	}

	@Override
	public int timeChangeForStack(IStatusEffectStack oldStack)
	{
		return oldStack.getTime() + this.getTime();
	}

	@Override
	public int levelChangeForStack(IStatusEffectStack oldStack)
	{
		return Math.max(oldStack.getLevel(), this.getLevel());
	}

	@Override
	public int activationTimeForStack(IStatusEffectStack oldStack)
	{
		return Math.min(this.getActivationTime(),oldStack.getActivationTime());
	}

	@Override
	public IStatusEffectStack applyToStack(IStatusEffectStack oldStack)
	{
		int stackLevel = this.levelChangeForStack(oldStack);
		int activationTime = this.activationTimeForStack(oldStack);
		if (! (activationTime == Integer.MAX_VALUE))
		{
			activationTime = 1;
		}
		int time = this.timeChangeForStack(oldStack);
		int stackAmount = this.getAmount() + oldStack.getAmount();
		return new StatusEffectStack(this.ability, stackLevel, stackAmount,time, activationTime, this.getCause());
	}

}
