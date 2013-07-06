package common.craftpunk.forgepvp.armorclasses.abilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import common.craftpunk.api.forgepvp.armorclasses.IAbility;

public class AbilityItemHeld implements IAbility
{
	public int cd;
	public ItemStack activatorItem;
	public IAbility abilityOnHold;
	public static int standardCD = 100;
	public boolean activated;
	public String name;
	
	public AbilityItemHeld(int itemID, IAbility activatedAbility)
	{
		this(new ItemStack(itemID, 1, 0), standardCD, activatedAbility);
	}
	
	public AbilityItemHeld(Item item, IAbility activatedAbility)
	{
		this(new ItemStack(item), standardCD, activatedAbility);
	}

	public AbilityItemHeld(Item item, int cd, IAbility activatedAbility)
	{
		this(new ItemStack(item), cd, activatedAbility);
	}
	
	public AbilityItemHeld(ItemStack item, int cd, IAbility activatedAbility)
	{
		this.activatorItem = item;
		this.cd = cd;
		this.abilityOnHold = activatedAbility;
		this.activated = false;
	}
	
	
	@Override
	public String getName()
	{
		// TODO Auto-generated method stub
		return "Item Held Effect";
	}

	@Override
	public void activate(EntityPlayer player)
	{
		ItemStack is = player.inventory.getCurrentItem();
		if ((is != null) && (is.itemID  == activatorItem.itemID) )
		{
			if (this.activated)
			{
				if (abilityOnHold.getRecurring())
				{
					abilityOnHold.activate(player);
				}
			}	
			else
			{
				abilityOnHold.activate(player);
			}
		}
		this.activated = true;
	}

	@Override
	public void deactivate(EntityPlayer player)
	{
		abilityOnHold.deactivate(player);
	}

	@Override
	public int getCD()
	{
		return this.cd;
	}

	@Override
	public int getInitialCD()
	{
		return this.cd;
	}

	@Override
	public boolean getRecurring()
	{
		return true;
	}
}
