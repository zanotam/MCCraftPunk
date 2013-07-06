package common.craftpunk.api.forgepvp.armorclasses;

import java.util.ArrayList;
import java.util.List;


public interface IArmorClass {
	
	public ArmorSet getArmorSet();

	
	public String getName();
	
	public void setName(String name);

	public int getPriority();

	public void setPriority(int priority);

	public boolean getDoesStack();

	public void setDoesStack(boolean doesStack);

	public void setArmorSet(ArmorSet armorSet);

	public List<IAbility> getArmorAbilityList();

	public void setArmorAbilityList(List<IAbility> armorAbilityList);
	
	public boolean matchesArmorSet(ArmorSet secondSet);
	
	public static IArmorClass emptyClass = new ArmorClass("empty", ArmorSet.emptyArmorSet, new ArrayList<IAbility>(), -1, true);
}
