package common.craftpunk.forgepvp.statuseffects;

import common.craftpunk.api.forgepvp.statuseffects.IStatusEffect;

public abstract class AbstractStatusEffect implements IStatusEffect
{
	public String name;

	public int nStackSizeMax = 1;
	
	public int nStacksMax = 1;

	public int levelMax = Integer.MAX_VALUE;
	
	public boolean doesStacksPerCause = false;
	public int nCausesStackingMax = Integer.MAX_VALUE;
	public int nStacksPerCauseMax = 1;
	
	public boolean persists = false;
	
	/**
	 * this should return the name of the Status Effect which should be unique.
	 * @return
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * This should return the max size of any specific stack.
	 * @return
	 */
	public int getStackSizeMax()
	{
		return this.nStackSizeMax;
	}

	/**
	 * This should return the max number of stacks. 
	 * This should generally be 1 or Integer.MAX_VALUE
	 * @return
	 */
	public int getNStacksMax()
	{
		return this.nStacksMax;
	}
	
	/**
	 * The max level that the status effect can reach. 
	 * This should generally be allowed to go as high as mathematically feasible.
	 * @return
	 */
	public int getLevelMax()
	{
		return this.levelMax;
	}

	
	/**
	 * Whether individual 'Causes' for Status Effects (saved as Strings) will be tracked separately.
	 * @return
	 */
	public boolean doesStacksPerCause()
	{
		return this.doesStacksPerCause;
	}

	/**
	 * The number of different causes which can have a stack on any random single target.
	 * The causes could have that many on as many creatures as they can though.
	 * @return
	 */
	public int getNCausesMax()
	{
		return this.nCausesStackingMax;
	}
	
	

	/**
	 * This is the maximum number of stacks that an individual cause can maintain.
	 * This should generally be one, as a stack per cause will generally be the best implementation.
	 * @return
	 */
	public int getNStacksPerCauseMax()
	{
		return this.nStacksPerCauseMax;
	}
	
	/**
	 * Whether the status effect should be removed after the entity it is on dies.
	 * This can potentially be overriden by an implementation of IStatusEffectStack.
	 * @return
	 */
	public boolean ifPersistsAfterDeath()
	{
		return this.persists;
	}

}
