package common.craftpunk.forgepvp.combatlogger;

import java.util.EnumSet;
import java.util.Vector;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import common.craftpunk.api.forgepvp.combatlogger.ICombatLogHandler;
import common.craftpunk.core.util.Pair;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class CombatLogHandler implements ICombatLogHandler, ITickHandler,
                             IPlayerTracker
{
    protected Set<EntityPlayer> taggedPlayerSet = Collections.synchronizedSet(new LinkedHashSet<EntityPlayer>());
    protected Vector<Pair<String, EntityPlayer>> taggedPlayerList = new Vector<Pair<String, EntityPlayer>>();
    public int tickCount = 0;
    public int timeToLeaveLoggerTicks = 60 * 20;
    protected ConcurrentHashMap<CombatLogger, Integer> loggerToExpirationMap = new ConcurrentHashMap<CombatLogger, Integer>(10 ^ 3);
    
    protected Vector<CombatLogger> loggersToSpawn = new Vector<CombatLogger>();

    @Override
    public void onPlayerLogin(EntityPlayer player)
    {
        // nothing here
        for (CombatLogger logger : loggerToExpirationMap.keySet())
        {
            if (logger.username == player.username)
            {
                WorldServer worldServer = logger.getServerForPlayer();
                worldServer.removeEntity(logger);
                loggerToExpirationMap.remove(logger);
                player.clonePlayer(logger, true);
            }
        }
    }

    @Override
    public void onPlayerLogout(EntityPlayer player)
    {
        if (taggedPlayerSet.contains(player))
        {
            ServerConfigurationManager playerConfigManager = FMLCommonHandler.instance()
                                                                             .getMinecraftServerInstance()
                                                                             .getConfigurationManager();
            EntityPlayerMP playerMP = (EntityPlayerMP) player;
            // NBTTagCompound playerMPTag =
            // playerConfigManager.readPlayerDataFromFile(playerMP);

            WorldServer worldServer = playerMP.getServerForPlayer();

            CombatLogger combatLogger = new CombatLogger(playerMP, worldServer);

            this.spawnCombatLogger(combatLogger, worldServer);
            //worldServer.spawnEntityInWorld(combatLogger);
            //worldServer.playerEntities.remove(combatLogger);

            loggerToExpirationMap.put(combatLogger, tickCount);
        }
    }

    @Override
    public void onPlayerChangedDimension(EntityPlayer player)
    {
        // nothing here
    }

    @Override
    public void onPlayerRespawn(EntityPlayer player)
    {
        // nothing here
    }

    public boolean addCombatTag(Pair<String, EntityPlayer> source)
    {
        return addCombatTag(source.getFirst(), source.getSecond());
    }

    public boolean removeCombatTag(Pair<String, EntityPlayer> source)
    {
        return removeCombatTag(source.getFirst(), source.getSecond());
    }

    /**
     * Acts basically like a set add.
     * 
     * @param source
     * @param player
     * @return
     */
    public boolean addCombatTag(String source, EntityPlayer player)
    {
        Pair<String, EntityPlayer> sourceAndPlayer = new Pair<String, EntityPlayer>(
                                                                                    source,
                                                                                    player);

        boolean shouldAdd = !taggedPlayerList.contains(sourceAndPlayer);
        boolean succeed;
        if (shouldAdd)
        {
            succeed = taggedPlayerList.add(sourceAndPlayer);
        }
        else
        {
            succeed = false;
        }

        if (succeed)
        {
            taggedPlayerSet.add(player);
        }
        else
        {
            // nothing here
        }

        return succeed;
    }

    /**
     * 
     * @param source
     * @param player
     * @return
     */
    public boolean removeCombatTag(String source, EntityPlayer player)
    {
        Pair<String, EntityPlayer> sourceAndPlayer = new Pair<String, EntityPlayer>(
                                                                                    source,
                                                                                    player);

        boolean shouldRemove = taggedPlayerList.contains(sourceAndPlayer);
        boolean succeed;

        if (shouldRemove)
        {
            succeed = taggedPlayerList.remove(sourceAndPlayer);
        }
        else
        {
            succeed = false;
        }

        if (succeed)
        {
            boolean removePlayer = true;
            for (Pair<String, EntityPlayer> pair : taggedPlayerList)
            {
                if (pair.contains(player))
                {
                    removePlayer = false;
                    break;
                }
            }
            if (removePlayer)
            {
                taggedPlayerSet.remove(player);
            }
        }
        else
        {
            // nothing here
        }

        return succeed;
    }

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {
        tickCount++;
        for (CombatLogger logger : loggerToExpirationMap.keySet())
        {
            if ((tickCount - timeToLeaveLoggerTicks) > loggerToExpirationMap.get(logger))
            {
                WorldServer worldServer = logger.getServerForPlayer();

                worldServer.removeEntity(logger);
            }
        }
        
        /*
        //this is to spawn teh actual combat logger. Not currently needed
        for (CombatLogger player: loggersToSpawn)
        if (taggedPlayerSet.contains(player))
        {
            ServerConfigurationManager playerConfigManager = FMLCommonHandler.instance()
                                                                             .getMinecraftServerInstance()
                                                                             .getConfigurationManager();
            EntityPlayerMP playerMP = (EntityPlayerMP) player;
            // NBTTagCompound playerMPTag =
            // playerConfigManager.readPlayerDataFromFile(playerMP);

            WorldServer worldServer = playerMP.getServerForPlayer();

            CombatLogger combatLogger = new CombatLogger(playerMP, worldServer);

            WorldServer worldServer = player.worldServer 
            worldServer.spawnEntityInWorld(combatLogger);
            worldServer.playerEntities.remove(combatLogger);

            loggerToExpirationMap.put(combatLogger, tickCount);
        }
        */
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
        // nothing here
    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.WORLD);
    }

    @Override
    public String getLabel()
    {
        return "Combat Logger Handler";
    }

    public boolean spawnCombatLogger(CombatLogger combatLogger, WorldServer world)
	{
        int i = MathHelper.floor_double(combatLogger.posX / 16.0D);
        int j = MathHelper.floor_double(combatLogger.posZ / 16.0D);

        if (!world.checkChunksExist(MathHelper.floor_double(combatLogger.posX), 
                                    MathHelper.floor_double(combatLogger.posY),
                                    MathHelper.floor_double(combatLogger.posZ),
                                    MathHelper.floor_double(combatLogger.posX),
                                    MathHelper.floor_double(combatLogger.posY),
                                    MathHelper.floor_double(combatLogger.posZ)))
        {
            return false;
        }
        else
        {


            if (MinecraftForge.EVENT_BUS.post(new EntityJoinWorldEvent(combatLogger, world)))
            {
                return false;
            }

            world.getChunkFromChunkCoords(i, j).addEntity(combatLogger);
            world.loadedEntityList.add(combatLogger);
            //world.obtainEntitySkin(combatLogger);
            return true;
        }
	    
	}
}
