package common.craftpunk.forgepvp.combatlogger;

import java.util.logging.Logger;

import common.craftpunk.forgepvp.combatlogger.proxy.CommonProxyClass;

import net.minecraft.entity.Entity;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarted;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;


@Mod(modid = ForgeCombatLogger.ID, name = ForgeCombatLogger.fullName, version = ForgeCombatLogger.VERSION)
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class ForgeCombatLogger
{
	public static final String ID = "ForgeCombatLogger";
	public static final String VERSION = "1.0.0a";
	public static final String packageName = "common.craftpunk.forgepvp.combatlogger";
	public static final String fullName = "Forge Combat Logger Mod";

	@Instance(ID)
	public static ForgeCombatLogger instance;

	public static final String clientProxy = packageName + ".proxy.ClientProxyClass";
	public static final String commonProxy = packageName + ".proxy.CommonProxyClass";
	
	@SidedProxy(clientSide = clientProxy, serverSide = commonProxy)
	public static CommonProxyClass proxy;

	public static Logger logger;
	
	public static CombatLoggerTickHandler combatLoggerTickHandler = new CombatLoggerTickHandler();
	public static CombatLoggerPlayerTracker combatLoggerPlayerTracker = new CombatLoggerPlayerTracker();
	public static CombatLogHandler combatLogHandler = new CombatLogHandler();
	
//	public static Entity combatLogger;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		logger = Logger.getLogger(ID);
		logger.setParent(FMLLog.getLogger());

		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		//String socketInfo = config.get(Configuration.CATEGORY_GENERAL, "SocketInfo", "nothing").value;
		//Boolean sendPosition = config.get(Configuration.CATEGORY_GENERAL, "SendPosition", true).getBoolean(false);
		//Boolean sendHealth = config.get(Configuration.CATEGORY_GENERAL, "SendHealth", true).getBoolean(false);
		//Boolean sendPotionEffects = config.get(Configuration.CATEGORY_GENERAL, "SendPotionEffects", false).getBoolean(false);

		config.save();
		EntityRegistry.registerModEntity(CombatLogger.class, "combatLogger", 1, this, 80, 3, true);
	}

	@Init
	public void init(FMLInitializationEvent event) {
		// item = new Item(Configs.itemId);

		// block = new Block(blockId);
		// GameRegistry.registerBlock(block);

		// GameRegistry.registerTileEntity(TileEntity.class, "myTile");

		// GameRegistry.addRecipe(new ItemStack(itemId), new Object[] {});

		// EntityRegistry.registerModEntity(entity.class, "myEntity", 0, this, 32, 10, true)
		
		// FMLCommonHandler.instance().registerTickHandler(tracker);
		
		//cpw.mods.fml.common.registry.TickRegistry.registerTickHandler(tracker, Side.CLIENT);
		
		cpw.mods.fml.common.registry.TickRegistry.registerTickHandler(combatLoggerTickHandler, Side.SERVER);
	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {

	}
	
	@ServerStarting
	public static void load(FMLServerStartingEvent e)
	{
		MinecraftForge.EVENT_BUS.register(new CombatLoggerEventHandler());
	}

	@ServerStarted
	public static void serverStarted(FMLServerStartedEvent e)
	{
		cpw.mods.fml.common.registry.TickRegistry.registerTickHandler(combatLoggerTickHandler, Side.SERVER);
		cpw.mods.fml.common.registry.TickRegistry.registerTickHandler(combatLogHandler, Side.SERVER);

		GameRegistry.registerPlayerTracker(combatLoggerPlayerTracker);
		GameRegistry.registerPlayerTracker(combatLogHandler);
		
		
		
	}


}
