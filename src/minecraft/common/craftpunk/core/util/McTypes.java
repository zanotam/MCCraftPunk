package common.craftpunk.core.util;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;

public enum McTypes
{
	ENTITY,TILEENTITY,BLOCK,ITEM,UNKNOWN;
	
	public McTypes getType(Object obj)
	{
		if (obj instanceof Block)
		{
			return McTypes.BLOCK;
		}
		else if (obj instanceof Entity)
		{
			return McTypes.ENTITY;
		}
		else if (obj instanceof Item)
		{
			return McTypes.ITEM;
		}
		else if (obj instanceof TileEntity)
		{
			return McTypes.TILEENTITY;
		}
		else
		{
			return McTypes.UNKNOWN;
		}
	}

}
