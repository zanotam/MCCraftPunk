package common.craftpunk.api.minepunk.world.metals;

import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public interface IMineral extends ICrushable
{
    public String getName();
    
    public boolean getDropsSelf();
    
    public ItemStack getBaseDrop();
    
    public int getBaseDropMax();
    
    public int getBaseDropMin();
    
    public ConcurrentHashMap<String, Icon> getIconMap();
    
    public int baseBlockLevelToMine();

}
