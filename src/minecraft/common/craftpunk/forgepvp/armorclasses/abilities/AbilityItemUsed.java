package common.craftpunk.forgepvp.armorclasses.abilities;

import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import common.craftpunk.api.forgepvp.armorclasses.IAbility;
import common.craftpunk.forgepvp.core.PvPEventHandler;

public class AbilityItemUsed implements IAbility
{
	public static ConcurrentHashMap<EntityPlayer, ArmorAbilityItemUseListener> playerToListenerMap = new ConcurrentHashMap<EntityPlayer, ArmorAbilityItemUseListener>(10^4);
	public int cd;
	public ItemStack activatorItem;
	public IAbility abilityOnUse;

	public AbilityItemUsed(Item item, int cd, IAbility activatedAbility)
	{
		this(new ItemStack(item), cd, activatedAbility);
	}
	
	public AbilityItemUsed(ItemStack item, int cd, IAbility activatedAbility)
	{
		this.activatorItem = item;
		this.cd = cd;
		this.abilityOnUse = activatedAbility;
	}
	
	
	@Override
	public String getName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void activate(EntityPlayer player)
	{
		// TODO Auto-generated method stub
		MinecraftForge.EVENT_BUS.register(new ArmorAbilityItemUseListener(this, player));
	}

	@Override
	public void deactivate(EntityPlayer player)
	{
		// TODO Auto-generated method stub
		playerToListenerMap.remove(player);
		MinecraftForge.EVENT_BUS.unregister(new ArmorAbilityItemUseListener(this, player));
	}

	@Override
	public int getCD()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getInitialCD()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getRecurring()
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	public void use(EntityPlayer player)
	{
		this.abilityOnUse.activate(player);
	}
	
	private class ArmorAbilityItemUseListener
	{
		public AbilityItemUsed ability;
		public EntityPlayer player;
		
		public ArmorAbilityItemUseListener(AbilityItemUsed armorAbility, EntityPlayer activatedPlayer)
		{
			this.player = activatedPlayer;
			this.ability = armorAbility;
			playerToListenerMap.put(player, this);
		}
		
		@ForgeSubscribe(receiveCanceled = false)
		public void click(PlayerInteractEvent event)
		{
			EntityPlayer eventPlayer = event.entityPlayer;
			if (eventPlayer == this.player)
			{
				ItemStack is = player.inventory.getCurrentItem();
			}
		}
	}

}
