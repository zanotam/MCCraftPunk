package common.craftpunk.minepunk.world.metals.substance.tool;

import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.item.Item;

public class ToolSet
{
	public Item pickaxe;
	public Item axe;
	public Item shovel;
	public Item sword;
	public Item hoe;
	public Item[] tools;
	public ConcurrentHashMap<String, Item> nameToToolMap;
}
