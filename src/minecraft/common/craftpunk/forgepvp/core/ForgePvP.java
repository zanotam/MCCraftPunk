package common.craftpunk.forgepvp.core;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Logger;

import common.craftpunk.core.CraftPunk;
import common.craftpunk.forgepvp.core.proxy.CommonProxyClass;


import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarted;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.relauncher.Side;



@Mod(modid = ForgePvP.ID, name = ForgePvP.fullName, version = ForgePvP.VERSION) 
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class ForgePvP
{

	public static final String ID = "ForgePvP";
	public static final String VERSION = "1.0.0a";
	public static final String packageName = "common.craftpunk.forgepvp.core";
	public static final String fullName = "Forge PvP";
	
	
	@Instance(ID)
	public static ForgePvP instance;

	public static final String clientProxy = packageName + ".proxy.ClientProxyClass";
	public static final String commonProxy = packageName + ".proxy.CommonProxyClass";
	
	@SidedProxy(clientSide = clientProxy, serverSide = commonProxy)
	public static CommonProxyClass proxy;

	public static Logger logger;

	public static PvPTickHandler pvpDamageTracker = new PvPTickHandler();

	@PreInit
	public void preInit(FMLPreInitializationEvent event) throws Exception
	{
		logger = Logger.getLogger(ID);
		logger.setParent(FMLLog.getLogger());
		
		File dir = CraftPunk.mainConfigDir;
		File file = new File(dir, "ForgePvP");
		ForgePvPConfiguration config = new ForgePvPConfiguration(file);
		config.load();
		config.apply();
		config.save();
	}

	@Init
	public void init(FMLInitializationEvent event)
	{
		cpw.mods.fml.common.registry.TickRegistry.registerTickHandler(pvpDamageTracker, Side.SERVER);
	}

	@PostInit
	public static void postInit(FMLPostInitializationEvent event)
	{

	}


	@ServerStarting
	public static void load(FMLServerStartingEvent e)
	{
		MinecraftForge.EVENT_BUS.register(new PvPEventHandler());

	}

	@ServerStarted
	public static void serverStarted(FMLServerStartedEvent e)
	{
	}
}