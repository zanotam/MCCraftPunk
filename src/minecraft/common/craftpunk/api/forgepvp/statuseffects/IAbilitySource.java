package common.craftpunk.api.forgepvp.statuseffects;

public interface IAbilitySource
{
	/**
	 * The ability imparted by the source.
	 * @return
	 */
	public String getAbility();
	
	public IStatusEffectStack getsEffectStackCaused();
	
	/**
	 * This should be the cooldown in ticks between when it can and can't be activated.
	 * A CD of 0 will allow it to be activated at any time as many times as possible.
	 * A cd of less than 0 will allow it to be activated n number of times, where cd is -(n+1)
	 * e.g. a cd of -2 would be a one-time activation.
	 * @return
	 */
	public int getUseCD();
	

	/**
	 * How many ticks should be waited before deactivating the ability.
	 * @return
	 */
	public int getDuration();
	
	
	/**
	 * How many ticks should be waited, if any, before resetting the duration 'til deactivation
	 * and using up another charge of the ability (if applicable).
	 * This should be less than getDuration and generally more than 20.
	 * @return
	 */
	//public int getRefreshRate();
	
	
	/**
	 * The Cause of the current ability. 
	 * @return
	 */
	public Object getUser();
	
	public boolean use();
}
