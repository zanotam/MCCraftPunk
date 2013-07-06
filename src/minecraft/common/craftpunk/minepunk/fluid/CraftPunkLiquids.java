package common.craftpunk.minepunk.fluid;

import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraftforge.common.Configuration;
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
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;



@Mod(modid = CraftPunkLiquids.ID, name = CraftPunkLiquids.fullName, version = CraftPunkLiquids.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class CraftPunkLiquids
{
	public static final String ID = "CraftPunkLiquids";
	public static final String VERSION = "1.0.0a";
	public static final String packageName = "common.craftpunk.minepunk.fluid";
	public static final String fullName = "CraftPunk Liquids Mod";

	@Instance(ID)
	public static CraftPunkLiquids instance;

	public static final String clientProxy = packageName + ".ClientProxyClass";
	public static final String commonProxy = packageName + ".CommonProxyClass";
	
	@SidedProxy(clientSide = clientProxy, serverSide = commonProxy)
	public static CommonProxyClass proxy;

	public static Logger logger;


	public static Block blockSmartTank;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) throws Exception
	{
		logger = Logger.getLogger(ID);
		logger.setParent(FMLLog.getLogger());
		
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		config.save();
		
	}

	@Init
	public void init(FMLInitializationEvent event)
	{
		blockSmartTank = new BlockSmartTank(250);
		GameRegistry.registerBlock(blockSmartTank, "Smart Tank");
		LanguageRegistry.addName(blockSmartTank, "Smart Tank");
	}

	@PostInit
	public static void postInit(FMLPostInitializationEvent event)
	{

	}


	@ServerStarting
	public static void load(FMLServerStartingEvent e)
	{
		//blockSmartTank = new BlockSmartTank(250);
	}

	@ServerStarted
	public static void serverStarted(FMLServerStartedEvent e)
	{
	}
}
