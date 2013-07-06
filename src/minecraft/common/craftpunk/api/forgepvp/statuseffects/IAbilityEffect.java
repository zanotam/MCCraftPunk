package common.craftpunk.api.forgepvp.statuseffects;

public interface IAbilityEffect
{
	/**
	 * the name of the ability. Should be unique.
	 * @return
	 */
	public String getName();	
	
	/**
	 * Should activate the ability, run the code that actually changes stuff.
	 * 
	 * @return whether it was activated.
	 */
	public boolean activate(IStatusEffectStack sEffectStack);

	/**
	 * Even if the ability normally has a timer that will auto-deactivate it,
	 * calling this function should ALWAYS reverse whatever calling activate did.
	 * @return whether it was deactivated.
	 */
	public boolean deactivate(IStatusEffectStack sEffectStack);

}
