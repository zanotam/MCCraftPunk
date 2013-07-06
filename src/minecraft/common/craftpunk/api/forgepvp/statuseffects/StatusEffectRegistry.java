package common.craftpunk.api.forgepvp.statuseffects;

import java.util.LinkedHashMap;


public class StatusEffectRegistry
{
	public static LinkedHashMap<String, IStatusEffect> nameToAbilityMap = new LinkedHashMap<String, IStatusEffect>();
	
	public static boolean register(IStatusEffect sEffect)
	{
		boolean registeredSuccesfully = nameToAbilityMap.containsKey(sEffect.getName());
		if (registeredSuccesfully)
		{
			nameToAbilityMap.put(sEffect.getName(), sEffect);
		}
		return registeredSuccesfully;
	}

	public static IStatusEffect getStatusEffectByName(String name)
	{
		return nameToAbilityMap.get(name);
	}
}
