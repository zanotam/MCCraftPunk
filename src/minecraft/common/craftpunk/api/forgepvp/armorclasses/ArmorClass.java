package common.craftpunk.api.forgepvp.armorclasses;

import java.util.ArrayList;
import java.util.List;

public class ArmorClass implements IArmorClass
{
	public String name;
	public ArmorSet armorSet;
	public List<IAbility> armorAbilityList;
	public int priority;
	public boolean doesStack;
	
	public ArmorClass(String name, ArmorSet set, ArrayList<IAbility> abilityList)
	{
		this.name=name;
		this.armorSet = set;
		this.armorAbilityList = new ArrayList<IAbility>(abilityList);
		this.priority = 1;
		this.doesStack = true;
	}
	
	public ArmorClass(String className, ArmorSet set, ArrayList<IAbility> abilityList, int priorityLevel, boolean stacks)
	{
		this.name = className;
		this.armorSet = set;
		this.armorAbilityList = new ArrayList<IAbility>(abilityList);
		this.priority = priorityLevel;
		this.doesStack = stacks;
	}
	
	
	public ArmorSet getArmorSet()
	{
		return armorSet;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean getDoesStack() {
		return doesStack;
	}

	public void setDoesStack(boolean doesStack) {
		this.doesStack = doesStack;
	}

	public void setArmorSet(ArmorSet armorSet) 
	{
		this.armorSet = armorSet;
	}


	public List<IAbility> getArmorAbilityList() {
		return armorAbilityList;
	}


	public void setArmorAbilityList(List<IAbility> armorAbilityList) {
		this.armorAbilityList = armorAbilityList;
	}
	
	public boolean matchesArmorSet(ArmorSet secondSet)
	{
		return this.armorSet.containsArmorSet(secondSet);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((armorAbilityList == null) ? 0 : armorAbilityList.hashCode());
		result = prime * result
				+ ((armorSet == null) ? 0 : armorSet.hashCode());
		result = prime * result + (doesStack ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + priority;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArmorClass other = (ArmorClass) obj;
		if (armorAbilityList == null) {
			if (other.armorAbilityList != null)
				return false;
		} else if (!armorAbilityList.equals(other.armorAbilityList))
			return false;
		if (armorSet == null) {
			if (other.armorSet != null)
				return false;
		} else if (!armorSet.equals(other.armorSet))
			return false;
		if (doesStack != other.doesStack)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (priority != other.priority)
			return false;
		return true;
	}
}
