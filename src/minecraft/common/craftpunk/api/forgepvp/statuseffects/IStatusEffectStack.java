package common.craftpunk.api.forgepvp.statuseffects;

public interface IStatusEffectStack
{	
	/**
	 * return the status effect the stack represents.
	 * @return
	 */
	public IStatusEffect getAbility();
	
	/**
	 * the level of the abilities in the stack.
	 * if a stack has multiple effects in it, they must all be the same level.
	 * @return
	 */
	public int getLevel();
	
	/**
	 * the time left until the effect expires.
	 * @return
	 */
	public int getTime();
	
	/**
	 * used by the StatusEffectTickHandler to activate recurring effects and remove ones that
	 * expired time wise.
	 * @param decrementAmount
	 */
	public void decrementTimeInTicks(int decrementAmount);
	
	/**
	 * This should return INT_MAX if it won't activate again.
	 * This should decrement when time decrements.
	 * @return
	 */
	public int getActivationTime();
	
	/**
	 * needed for recurring effects.
	 * @return
	 */
	public void setActivationTime(int replacement);
	
	/**
	 * Some effects can stack. Think like a stack of 3 poison abilities that all tick together.
	 * @return
	 */
	public int getAmount();
	
	/**
	 * The source of the status effect
	 * @return
	 */
	public Object getCause();
	
	/**
	 * Whether the stack shouldn't be removed upon the owner's death.
	 * @return
	 */
	public boolean persistsAfterDeath();

	/*
	 * You can call these functions to just test the waters if you have some rules for the stack like no ovewriting level 
	 * or no stacking time of equal levels or something like that.
	 */
	
	/**
	 * @param oldStack
	 * How much the time 'til expiration would change if applied to oldstack
	 * @return
	 */
	public int timeChangeForStack(IStatusEffectStack oldStack);
	
	/**
	 * What the level would be if applied to oldStack.
	 * @param oldStack
	 * @return
	 */
	public int levelChangeForStack(IStatusEffectStack oldStack);
	
	/**
	 * What the new activation time would be if you combined the stacks.
	 * This is mostly for effects which can have their recurring activation times change as obviously
	 * an old effect will have to be deactivated and a new effect activated normally.
	 * @param oldStack
	 * @return
	 */
	public int activationTimeForStack(IStatusEffectStack oldStack);
	
	public IStatusEffectStack applyToStack(IStatusEffectStack oldStack);
}
