package common.craftpunk.forgepvp.armorclasses.abilities;

import common.craftpunk.api.forgepvp.armorclasses.IAbility;
import common.craftpunk.api.forgepvp.statuseffects.IStatusEffectStack;
import common.craftpunk.forgepvp.statuseffects.PlayerStatusEffectContainer;
import common.craftpunk.forgepvp.statuseffects.StatusEffectStackContainerHandler;

import net.minecraft.entity.player.EntityPlayer;

public class AbilityStatusEffect implements IAbility
{
	public IStatusEffectStack statusEffectStack;
	
	public AbilityStatusEffect(IStatusEffectStack sEffectStack)
	{
		this.statusEffectStack = sEffectStack;
	}
	
	public String getName()
	{
		return "Armor Ability Status Effect: " + this.statusEffectStack.getAbility().getName();
	}
	
	public void activate(EntityPlayer player)
	{
		PlayerStatusEffectContainer container = StatusEffectStackContainerHandler.playerNameToSEffectContainer.get(player.username);
		container.addStatusEffectStack(this.statusEffectStack);
	}
	
	
	public void deactivate(EntityPlayer player)
	{
		//nothing here for now
	}
	
	public int getCD()
	{
		return 50;
	}
	
	public boolean getRecurring()
	{
		return true;
	}

	@Override
	public int getInitialCD()
	{
		return this.getCD();
	}

}
