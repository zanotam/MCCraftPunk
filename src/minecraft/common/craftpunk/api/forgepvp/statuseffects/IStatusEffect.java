package common.craftpunk.api.forgepvp.statuseffects;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;

public interface IStatusEffect 
{
	/**
	 * this should return the name of the Status Effect which should be unique.
	 * @return
	 */
	public String getName();

	/**
	 * This should return the max size of any specific stack.
	 * @return
	 */
	public int getStackSizeMax();

	/**
	 * This should return the max number of stacks. 
	 * This should generally be 1 or Integer.MAX_VALUE
	 * @return
	 */
	public int getNStacksMax();
	
	/**
	 * The max level that the status effect can reach. 
	 * This should generally be allowed to go as high as mathematically feasible.
	 * @return
	 */
	public int getLevelMax();

	
	/**
	 * Whether individual 'Causes' for Status Effects (saved as Strings) will be tracked separately.
	 * @return
	 */
	public boolean doesStacksPerCause();

	/**
	 * The number of different causes which can have a stack on any random single target.
	 * The causes could have that many on as many creatures as they can though.
	 * getNCausesMax*getNStacksPerCauseMax = getNStacksMax unless something has gone wrong.
	 * @return
	 */
	public int getNCausesMax();
	
	/**
	 * This is the maximum number of stacks that an individual cause can maintain.
	 * This should generally be one, as a stack per cause will generally be the best implementation.
	 * @return
	 */
	public int getNStacksPerCauseMax();
	
	/**
	 * Whether the status effect should be removed after the entity it is on dies.
	 * This can potentially be overriden by an implementation of IStatusEffectStack.
	 * @return
	 */
	public boolean ifPersistsAfterDeath();
	
	/**
	 * Activates the stack and returns with how many ticks until the next time it should be activated.
	 * 0 ticks means never.
	 */
	public int activate(IStatusEffectStack sEffectStack, EntityLivingBase target);
	
	public void deactivate(IStatusEffectStack sEffectStack, EntityLivingBase target);
}
