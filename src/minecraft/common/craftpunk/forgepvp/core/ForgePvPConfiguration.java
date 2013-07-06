package common.craftpunk.forgepvp.core;

import java.io.File;

import common.craftpunk.core.CraftPunkConfiguration;

import net.minecraftforge.common.ConfigCategory;


public class ForgePvPConfiguration extends CraftPunkConfiguration
{
	
	public ForgePvPConfiguration(File file)
	{
		super(file);
	}
	
	public void apply()
	{
		int attackCD = this.get(CATEGORY_GENERAL, "attackCDInTicks", 5).getInt(5);
		
		ConfigCategory itemIDsWithCDs = this.getCategory("ItemIDsWithCDs");

		for (String idString: itemIDsWithCDs.keySet())
		{
			int id = 0;
			int cd = 0;
			id = Integer.parseInt(idString);
			cd = itemIDsWithCDs.get(idString).getInt(0);
		}
	}

}
