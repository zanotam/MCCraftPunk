package common.craftpunk.minepunk.machinery.sensors;

import java.util.logging.Logger;

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


@Mod(modid = Sensors.ID, name = Sensors.fullName, version = Sensors.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Sensors
{
	public static final String ID = "Sensors";
	public static final String VERSION = "1.0.0a";
	public static final String packageName = "common.craftpunk.minepunk.machinery.sensors";
	public static final String fullName = "CraftPunk Sensors Mod";

	@Instance(ID)
	public static Sensors instance;

	@SidedProxy(clientSide = (packageName + ".ClientProxyClass"), serverSide = (packageName + ".CommonProxyClass"))
	public static CommonProxyClass proxy;

	public static Logger logger;
	
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
	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {

	}
	
	@ServerStarting
	public static void load(FMLServerStartingEvent e)
	{
		//MinecraftForge.EVENT_BUS.register(new PvPEventHandler());

	}

	@ServerStarted
	public static void serverStarted(FMLServerStartedEvent e)
	{
		
	}


}
