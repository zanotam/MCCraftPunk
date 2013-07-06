package common.craftpunk.minepunk.fluid.aspectsplus;

import java.io.File;
import java.util.LinkedHashMap;

import common.craftpunk.core.CraftPunkConfiguration;

import net.minecraft.item.Item;

public class AspectsPlusConfiguration extends CraftPunkConfiguration
{
	
	public static final String nValuesString = "Number of Values";
	
	public AspectsPlusConfiguration(File file)
	{
		super(file);
	}
	
	
	public boolean processConfig()
	{
		boolean error = false;
		for(String category: this.getCategoryNames())
		{
			if (this.hasKey(category, nValuesString))
			{
				int nValues = this.get(category, nValuesString, 0).getInt();
				if (nValues != -1)
				{
					for (int i = 1; i <= nValues; i++)
					{
						String propertyName = "Value" + i;
						String valueString = this.get(category,propertyName, "").getString();
						//change error to true if it errors out?
					}
				}
			}
		}
		
		return error;
	}
	
	public boolean parseAspectProperty(String propertyString)
	{
		//change error to true if there's a problem
		boolean error = false;
		String delimiter = ";";
		int itemEndIndex = propertyString.indexOf(delimiter);
		String itemString = propertyString.substring(0, itemEndIndex);
		Integer[] itemIDInfo = parseItemNameProperty(itemString);
		int id = itemIDInfo[0];
		int meta = itemIDInfo[1];
		String aspectString = propertyString.substring(itemEndIndex+1);
		LinkedHashMap<String, Integer> aspectNamesToAmountMap = parseItemAspects(aspectString);
		AspectRegistrar.registerBlockAspect(id, meta, aspectNamesToAmountMap);
		return error;
	}
	
	//parses the itemname string in to the id and meta
	public Integer[] parseItemNameProperty(String itemString)
	{
		String delimiter = ";";
		int meta = -1;
		int id = -1;
		//firs it checks if the string is all numbers
		try{
			id = Integer.parseInt(itemString);
		}catch (NumberFormatException ex)
		{
			//then it tries to parse like it is id:meta format
			try{
				Integer.parseInt(itemString.substring(0,1));
				int idEndIndex = itemString.indexOf(delimiter);
				id = Integer.parseInt(itemString.substring(0,idEndIndex));
				meta = Integer.parseInt(itemString.substring(idEndIndex+1));
			} //then it just assumes it's a proper name
			catch(NumberFormatException ex2)
			{
				for(Item item: Item.itemsList)
				{
					if (item.getUnlocalizedName().equals(itemString))
					{
						id = item.itemID;
						break;
					}
				}
			}
		}
		Integer[] itemAndMeta =  new Integer[2];
		itemAndMeta[0] = id;
		itemAndMeta[1] = meta;
		return itemAndMeta;
	}
	
	//parses the list of aspects in to something usable by the function that gives items aspects
	public LinkedHashMap<String, Integer> parseItemAspects(String aspectString)
	{
		LinkedHashMap<String,Integer> aspectNameToAmountMap = new LinkedHashMap<String,Integer>();
		//look for delimiters, ge ttheir locations
		//use those to decide which bits of substring to extract
		//aspectString.
		
		
		
		return aspectNameToAmountMap;
	}

}
