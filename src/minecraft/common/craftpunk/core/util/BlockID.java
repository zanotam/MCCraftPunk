package common.craftpunk.core.util;

import net.minecraft.item.Item;

/**
 * represents a minecraft full block ID as in the form id:meta.
 * @author RobertSBaker
 *
 */
public class BlockID
{
	public Pair<Integer, Integer> fullID;
	
	public int baseID;
	public int metaValue;

	public BlockID(int mcBaseID, int mcMeta)
	{
		this.baseID = mcBaseID;
		this.metaValue = mcMeta;
	}

	/**
	 * Should be able to read "id", "id:meta", and will TRY to read "itemname" if such an item exists.
	 * A meta of -1 generally means that the item meta is meaningless.
	 * @param itemString
	 */
	public BlockID getIDFromString(String itemString)
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
		return new BlockID(id, meta);
	}
	
	/**
	 * @return the 'traditional' id:meta format.
	 */
	@Override
	public String toString()
	{
		return this.baseID + ":" + this.metaValue;
	}

	public Pair<Integer, Integer> getFullID()
	{
		return fullID;
	}

	public void setFullID(Pair<Integer, Integer> fullID)
	{
		this.fullID = fullID;
	}

	public int getBaseID()
	{
		return baseID;
	}

	public void setBaseID(int mcBaseID)
	{
		this.baseID = mcBaseID;
	}

	public int getMeta()
	{
		return metaValue;
	}

	public void setMeta(int meta)
	{
		this.metaValue = meta;
	}
	
	public int getMetaValue()
	{
		return metaValue;
	}

	public void setMetaValue(int meta)
	{
		this.metaValue = meta;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + baseID;
		result = prime * result + ((fullID == null) ? 0 : fullID.hashCode());
		result = prime * result + metaValue;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlockID other = (BlockID) obj;
		if (baseID != other.baseID)
			return false;
		if (fullID == null)
		{
			if (other.fullID != null)
				return false;
		}
		else if (!fullID.equals(other.fullID))
			return false;
		if (metaValue != other.metaValue)
			return false;
		return true;
	}

}
