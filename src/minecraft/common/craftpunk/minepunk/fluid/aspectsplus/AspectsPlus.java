package common.craftpunk.minepunk.fluid.aspectsplus;

import java.io.File;

import common.craftpunk.core.CraftPunk;

import net.minecraft.block.Block;
import net.minecraftforge.common.Configuration;
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

//@Mod(modid = "ApectsPlus", name = "The CraftPunk AspectsPlus Mod", version = "1.0.0", dependencies = "CraftPunkCore")
@Mod(modid = AspectsPlus.ID, name = AspectsPlus.fullName, version = AspectsPlus.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class AspectsPlus {
	
	public static final String ID = "AspectsPlus";
	public static final String VERSION = "1.0.0a";
	public static final String packageName = "common.craftpunk.minepunk.fluid.aspectsplus";
	public static final String fullName = "AspectsPlus";
	
	@Instance("AspectsPlus")
	public static AspectsPlus instance;
	
	public static final String clientProxy = packageName + ".ClientProxyClass";
	public static final String commonProxy = packageName + ".CommonProxyClass";
	
	@SidedProxy(clientSide = clientProxy, serverSide = commonProxy)
	public static CommonProxyClass proxy;


	public static Block blockSmartTank;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) throws Exception
	{
		File configDir = new File(CraftPunk.mainConfigDir, "AspectsPlus");
		if (configDir.exists())
		{
			for (File modConfig: configDir.listFiles())
			{
				AspectsPlusConfiguration config = new AspectsPlusConfiguration(configDir);
			}
		}
		else
		{
			configDir.mkdir();
		}
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		config.save();
		
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
