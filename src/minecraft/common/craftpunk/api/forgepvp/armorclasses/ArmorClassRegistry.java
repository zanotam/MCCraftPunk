package common.craftpunk.api.forgepvp.armorclasses;

import java.util.concurrent.ConcurrentHashMap;

public class ArmorClassRegistry
{
	public static ConcurrentHashMap<String, IArmorClass> nameToArmorClassMap = new ConcurrentHashMap<String, IArmorClass>(10^3);
	
	public static boolean register(IArmorClass armorClass)
	{
		boolean registered = false;
		if (! nameToArmorClassMap.containsKey(armorClass.getName()))
		{
			nameToArmorClassMap.put(armorClass.getName(), armorClass);
		}
		return registered;
	}
	
	public static IArmorClass getAbilityByname(String name)
	{
		return nameToArmorClassMap.get(name);
	}

}
