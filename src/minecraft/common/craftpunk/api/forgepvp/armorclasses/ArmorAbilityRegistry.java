package common.craftpunk.api.forgepvp.armorclasses;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;


public class ArmorAbilityRegistry
{
	
	public static ConcurrentHashMap<String, IAbility> nameToAbilityMap = new ConcurrentHashMap<String, IAbility>(10^4);
	
	public static boolean register(IAbility ability)
	{
		boolean registered = false;
		if (! nameToAbilityMap.containsKey(ability.getName()))
		{
			nameToAbilityMap.put(ability.getName(), ability);
		}
		return registered;
	}
	
	public static IAbility getAbilityByname(String name)
	{
		return nameToAbilityMap.get(name);
	}

}
