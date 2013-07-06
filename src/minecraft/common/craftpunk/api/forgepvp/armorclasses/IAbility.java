package common.craftpunk.api.forgepvp.armorclasses;

import net.minecraft.entity.player.EntityPlayer;



public interface IAbility {
	
	public String getName();
	
	public void activate(EntityPlayer player);
	
	public void deactivate(EntityPlayer player);
	
	public int getCD();
	
	public int getInitialCD();
	
	public boolean getRecurring();
}
