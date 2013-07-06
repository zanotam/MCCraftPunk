package common.craftpunk.core;

import java.io.File;
import java.util.logging.Logger;

import common.craftpunk.core.proxy.CommonProxyClass;

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


@Mod(modid = CraftPunk.ID, name = CraftPunk.fullName, version = CraftPunk.VERSION)
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class CraftPunk {
	
	public static final String ID = "CraftPunk";
	public static final String VERSION = "1.0.0a";
	public static final String packageName = "common.craftpunk.core";
	public static final String fullName = "CraftPunk Core Mod";
	
	@Instance(ID)
	public static CraftPunk instance;

	public static final String clientProxy = packageName + ".proxy.ClientProxyClass";
	public static final String commonProxy = packageName + ".proxy.CommonProxyClass";
	
	@SidedProxy(clientSide = clientProxy, serverSide = commonProxy)
	public static CommonProxyClass proxy;

	public static Logger logger;
	
	public static File mainConfigDir;


	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) throws Exception
	{
		logger = Logger.getLogger(ID);
		logger.setParent(FMLLog.getLogger());
		
		mainConfigDir = new File(event.getModConfigurationDirectory() + "CraftPunk");
		mainConfigDir.mkdir();
		
		//config.load();
		
		//config.save();
		
	}

	@Init
	public void init(FMLInitializationEvent event)
	{
		
	}

	@PostInit
	public static void postInit(FMLPostInitializationEvent event)
	{

	}


	@ServerStarting
	public static void load(FMLServerStartingEvent e)
	{
		
	}

	@ServerStarted
	public static void serverStarted(FMLServerStartedEvent e)
	{
		
	}
}
