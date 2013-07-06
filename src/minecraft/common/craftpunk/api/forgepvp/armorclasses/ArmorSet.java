package common.craftpunk.api.forgepvp.armorclasses;

import java.util.ArrayList;
import java.util.Collection;

import net.minecraft.item.ItemStack;

public class ArmorSet {
	
	public ArrayList<ItemStack> headSlot;
	public ArrayList<ItemStack> chestSlot;
	public ArrayList<ItemStack> legSlot;
	public ArrayList<ItemStack> footSlot;

	public ArmorSet(ItemStack head, ItemStack chest, ItemStack leg, ItemStack foot)
	{
		ArrayList<ItemStack> headSlotList = new ArrayList<ItemStack>();
		ArrayList<ItemStack> chestSlotList = new ArrayList<ItemStack>();
		ArrayList<ItemStack> legSlotList = new ArrayList<ItemStack>();
		ArrayList<ItemStack> footSlotList = new ArrayList<ItemStack>();
		
		headSlotList.add(head);
		chestSlotList.add(chest);
		legSlotList.add(leg);
		footSlotList.add(foot);
			
		this.headSlot = headSlotList;
		this.chestSlot = chestSlotList;
		this.legSlot = legSlotList;
		this.footSlot = footSlotList;
	}
	
	public ArmorSet(int headID, int chestID, int legID, int footID)
	{
		ArrayList<ItemStack> headSlotList = new ArrayList<ItemStack>();
		ArrayList<ItemStack> chestSlotList = new ArrayList<ItemStack>();
		ArrayList<ItemStack> legSlotList = new ArrayList<ItemStack>();
		ArrayList<ItemStack> footSlotList = new ArrayList<ItemStack>();
		
		int stackSize = 1;
		int damageValue = 1;
		
		ItemStack head = new ItemStack(headID, stackSize, damageValue);
		ItemStack chest = new ItemStack(chestID, stackSize, damageValue);
		ItemStack leg = new ItemStack(legID, stackSize, damageValue);
		ItemStack foot = new ItemStack(footID, stackSize, damageValue);
	
		headSlotList.add(head);
		chestSlotList.add(chest);
		legSlotList.add(leg);
		footSlotList.add(foot);
			
		this.headSlot = new ArrayList<ItemStack>(headSlotList);
		this.chestSlot = new ArrayList<ItemStack>(chestSlotList);
		this.legSlot = new ArrayList<ItemStack>(legSlotList);
		this.footSlot = new ArrayList<ItemStack>(footSlotList);
	}
	
	@Deprecated
	public ArmorSet(int headID, int headDV, int chestID, int chestDV, int legID, int legDV, int footID, int footDV)
	{
		ArrayList<ItemStack> headSlotList = new ArrayList<ItemStack>();
		ArrayList<ItemStack> chestSlotList = new ArrayList<ItemStack>();
		ArrayList<ItemStack> legSlotList = new ArrayList<ItemStack>();
		ArrayList<ItemStack> footSlotList = new ArrayList<ItemStack>();

		
		int stackSize = 1;
		
		ItemStack head = new ItemStack(headID, stackSize, headDV);
		ItemStack chest = new ItemStack(chestID, stackSize, chestDV);
		ItemStack leg = new ItemStack(legID, stackSize, legDV);
		ItemStack foot = new ItemStack(footID, stackSize, footDV);
		
		headSlotList.add(head);
		chestSlotList.add(chest);
		legSlotList.add(leg);
		footSlotList.add(foot);
			
		this.headSlot = headSlotList;
		this.chestSlot = chestSlotList;
		this.legSlot = legSlotList;
		this.footSlot = footSlotList;

	}
	
	public ArmorSet(ArrayList<ItemStack> headPieces, ArrayList<ItemStack> chestPieces, ArrayList<ItemStack> legPieces, ArrayList<ItemStack> footPieces)
	{
		this.headSlot = new ArrayList<ItemStack>(headPieces);
		this.chestSlot = new ArrayList<ItemStack>(chestPieces);
		this.legSlot = new ArrayList<ItemStack>(legPieces);
		this.footSlot = new ArrayList<ItemStack>(footPieces);
	}
	
	public boolean contains(ItemStack armor)
	{
		boolean headMatch = containsHead(armor);
		boolean chestMatch = containsChest(armor);
		boolean legMatch = containsLeg(armor);
		boolean footMatch = containsFoot(armor);
		return headMatch || chestMatch || legMatch || footMatch;
	}
	
	public boolean containsHead(ItemStack armor)
	{
		return armorSlotContainsItem(this.headSlot, armor);
		/*
		boolean headMatch = false;
		for (ItemStack helm: headSlot)
		{
			if (helm.itemID == armor.itemID)
			{
				headMatch = true;
			}
		}
		return headMatch;
		*/
	}
	
	public boolean containsChest(ItemStack armor)
	{
		return armorSlotContainsItem(this.chestSlot, armor);
		/*
		boolean chestMatch = false;
		for (ItemStack chest: chestSlot)
		{
			if (chest.itemID == armor.itemID)
			{
				chestMatch = true;
			}
		}
		return chestMatch;
		*/
	}
	
	public boolean containsLeg(ItemStack armor)
	{
		return armorSlotContainsItem(this.legSlot, armor);
		/*
		boolean legMatch = false;
		for (ItemStack leg: legSlot)
		{
			if (leg.itemID == armor.itemID)
			{
				legMatch = true;
			}
		}
		return legMatch;
		*/
	}
	
	public boolean containsFoot(ItemStack armor)
	{
		return armorSlotContainsItem(this.footSlot, armor);
		/*
		boolean footMatch = false;
		for (ItemStack boot: footSlot)
		{
			if(boot != null)
			{
				if (boot.itemID == armor.itemID)
				{
					footMatch = true;
				}
			}
			else
			{
				
			}
		}
		return footMatch;
		*/
	}
		
	public boolean armorSlotContainsItem(Collection<ItemStack> collection, ItemStack itemStack)
	{
		boolean shouldMatch = !(itemStack == null); //null items are assumed to always be in the armor set.
		if (shouldMatch)
		{
			for (ItemStack item: collection)
			{
				if (item != null)
				{
					if (item.itemID == itemStack.itemID)
					{
						return true;
					}
				}
			}
		}
		else
		{
			return true;
		}
		return false;
	}

	public ArrayList<ItemStack> getHeadSlot() {
		return headSlot;
	}

	public void setHeadSlot(ArrayList<ItemStack> headSlot) {
		this.headSlot = headSlot;
	}

	public ArrayList<ItemStack> getChestSlot() {
		return chestSlot;
	}

	public void setChestSlot(ArrayList<ItemStack> chestSlot) {
		this.chestSlot = chestSlot;
	}

	public ArrayList<ItemStack> getLegSlot() {
		return legSlot;
	}

	public void setLegSlot(ArrayList<ItemStack> legSlot) {
		this.legSlot = legSlot;
	}

	public ArrayList<ItemStack> getFootSlot() {
		return footSlot;
	}

	public void setFootSlot(ArrayList<ItemStack> footSlot) {
		this.footSlot = footSlot;
	}
	
	public static ArmorSet emptyArmorSet = generateEmptyArmor();
			
	protected static ArmorSet generateEmptyArmor()
	{
		ArrayList<ItemStack> armorSlots = new ArrayList<ItemStack>();
		//armorSlots.add(null);
		ArmorSet empty = new ArmorSet(armorSlots,armorSlots,armorSlots,armorSlots);
		return empty;
	}
	
	/**
	 * checks if the second armor set's 4 slots are all strict subsets of the armor set
	 * with the assumption that a null slot is always a subset.
	 * @param secondSet
	 * @return
	 */
	public boolean containsArmorSet(ArmorSet secondSet)
	{
		boolean containsHeads = false;
		boolean containsChests = false;
		boolean containsLegs = false;
		boolean containsFeet = false;
	
		if (secondSet.headSlot.size() == 0)
		{
			containsHeads = true;
		}
		else
		{
			for(ItemStack item: secondSet.headSlot)
			{
				containsHeads = containsHeads || this.contains(item);
			}
		}
		
		if (secondSet.chestSlot.size() == 0)
		{
			containsChests = true;
		}
		else
		{
			for(ItemStack item: secondSet.chestSlot)
			{
				containsChests = containsChests || this.contains(item);
			}
		}
		
		if (secondSet.legSlot.size() == 0)
		{
			containsLegs = true;
		}
		else
		{
			for(ItemStack item: secondSet.legSlot)
			{
				containsLegs = containsLegs || this.contains(item);
			}
		}
	
		if (secondSet.footSlot.size() == 0)
		{
			containsFeet = true;
		}
		else
		{
			for(ItemStack item: secondSet.footSlot)
			{
				containsFeet = containsFeet || this.contains(item);
			}
		}
		
		boolean contains = containsHeads && containsChests && containsLegs && containsFeet;
		
		return contains;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((chestSlot == null) ? 0 : chestSlot.hashCode());
		result = prime * result
				+ ((footSlot == null) ? 0 : footSlot.hashCode());
		result = prime * result
				+ ((headSlot == null) ? 0 : headSlot.hashCode());
		result = prime * result + ((legSlot == null) ? 0 : legSlot.hashCode());
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
		ArmorSet other = (ArmorSet) obj;
		if (chestSlot == null) {
			if (other.chestSlot != null)
				return false;
		} else if (!chestSlot.equals(other.chestSlot))
			return false;
		if (footSlot == null) {
			if (other.footSlot != null)
				return false;
		} else if (!footSlot.equals(other.footSlot))
			return false;
		if (headSlot == null) {
			if (other.headSlot != null)
				return false;
		} else if (!headSlot.equals(other.headSlot))
			return false;
		if (legSlot == null) {
			if (other.legSlot != null)
				return false;
		} else if (!legSlot.equals(other.legSlot))
			return false;
		return true;
	}
}
