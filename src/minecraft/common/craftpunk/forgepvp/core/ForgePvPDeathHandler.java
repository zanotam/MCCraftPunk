package common.craftpunk.forgepvp.core;

import java.util.ArrayList;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import cpw.mods.fml.common.IPlayerTracker;

public class ForgePvPDeathHandler implements IPlayerTracker
{
    //the multiplier on how long a players death drop event will last
    public static int itemTimeMultiplier = 3;
    
    public static ArrayList<ItemStack> spawnItems;

    @ForgeSubscribe
    public void onPlayerDeath(PlayerDropsEvent event)
    {
        ArrayList<EntityItem> drops = event.drops;
        for (EntityItem item: drops)
        {
            item.lifespan *= this.itemTimeMultiplier;
        }
        
    }
    
    @Override
    public void onPlayerLogin(EntityPlayer player)
    {
        //do nothing here
    }

    @Override
    public void onPlayerLogout(EntityPlayer player)
    {
        //do nothing
    }

    @Override
    public void onPlayerChangedDimension(EntityPlayer player)
    {
        //do nothing
    }

    @Override
    public void onPlayerRespawn(EntityPlayer player)
    {
        // TODO Auto-generated method stub
        
        //give player potion or other effects
        
        //give player free spawn items
    }

}
