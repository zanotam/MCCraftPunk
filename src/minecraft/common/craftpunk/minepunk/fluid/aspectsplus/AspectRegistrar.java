package common.craftpunk.minepunk.fluid.aspectsplus;

import java.util.LinkedHashMap;
import java.util.Map;

/*
import thaumcraft.api.EnumTag;
import thaumcraft.api.ObjectTags;
import thaumcraft.api.ThaumcraftApi;
*/


public class AspectRegistrar
{

	public static LinkedHashMap<String, Integer> aspectToIDMap;
	// public static HashMap<String,EnumTag> nameToTagMap;
	/**
	 * an anonymous class that registers fills the aspectToIDMap
	 */
	static
	{
		/*
		for (EnumTag tag : EnumTag.values())
		{
			aspectToIDMap.put(tag.name(), tag.id);
		}
		*/
		// for (EnumTag tag : EnumTag.values()) {
		// nameToTagMap.put(tag.name(), tag);
		// }
	}

	public AspectRegistrar()
	{
		/*
		for (EnumTag tag : EnumTag.values())
		{
			aspectToIDMap.put(tag.name, tag.id);
		}
		for (EnumTag tag : EnumTag.values())
		{
			aspectToIDMap.put(tag.name(), tag.id);
		}
		*/
	}

	/**
	 * Used to assign apsects to the given item/block. Here is an example of the
	 * declaration for cobblestone:
	 * <p>
	 * <i>ThaumcraftApi.registerObjectTag(Block.cobblestone.blockID, -1, (new
	 * ObjectTags()).add(EnumTag.ROCK, 1).add(EnumTag.DESTRUCTION, 1));</i>
	 * 
	 * @param id
	 * @param meta
	 *            pass -1 if all damage values of this item/block should have
	 *            the same aspects
	 * @param aspects
	 *            A ObjectTags object of the associated aspects
	 */
	public static void registerBlockAspect(int id, int meta, Map<String, Integer> aspectNamesToAmountMap)
	{
		/*
		ObjectTags aspectsTag = new ObjectTags();
		for (String aspect : aspectNamesToAmountMap.keySet())
		{

			aspectsTag.add(EnumTag.get(aspectToIDMap.get(aspect)), aspectNamesToAmountMap.get(aspect));
		}
		ThaumcraftApi.registerObjectTag(id, meta, aspectsTag);
		*/
	}
	
	public static void registerComplexBlockAspect(int id, int meta, Map<String, Integer> aspectNamesToAmountMap)
	{
		/*
		ObjectTags aspectsTag = new ObjectTags();
		for (String aspect : aspectNamesToAmountMap.keySet())
		{

			aspectsTag.add(EnumTag.get(aspectToIDMap.get(aspect)), aspectNamesToAmountMap.get(aspect));
		}
		ThaumcraftApi.registerComplexObjectTag(id, meta, aspectsTag);
		*/
	}

}
