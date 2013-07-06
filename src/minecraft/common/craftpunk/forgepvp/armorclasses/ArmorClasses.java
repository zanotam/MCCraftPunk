package common.craftpunk.forgepvp.armorclasses;

import java.util.ArrayList;
import java.util.logging.Logger;

import net.minecraftforge.common.MinecraftForge;

import common.craftpunk.api.forgepvp.armorclasses.ArmorAbilityRegistry;
import common.craftpunk.api.forgepvp.armorclasses.ArmorClass;
import common.craftpunk.api.forgepvp.armorclasses.IArmorClass;
import common.craftpunk.api.forgepvp.armorclasses.ArmorSet;
import common.craftpunk.api.forgepvp.armorclasses.IAbility;
import common.craftpunk.forgepvp.armorclasses.abilities.ArcherDamageEventHandler;
import common.craftpunk.forgepvp.armorclasses.abilities.AbilityArcherDamage;
import common.craftpunk.forgepvp.armorclasses.abilities.AbilityItemHeld;
import common.craftpunk.forgepvp.armorclasses.abilities.AbilityPotionEffect;
import common.craftpunk.forgepvp.armorclasses.proxy.CommonProxyClass;

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
import cpw.mods.fml.relauncher.Side;




@Mod(modid = ArmorClasses.ID, name = ArmorClasses.fullName, version = ArmorClasses.VERSION)
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class ArmorClasses
{
	public static final String ID = "Armor Classes";
	public static final String VERSION = "1.0.0a";
	public static final String packageName = "common.craftpunk.forgepvp.armorclasses";
	public static final String fullName = "The Armor Classes Mod";

	@Instance(ID)
	public static ArmorClasses instance;

	public static final String clientProxy = packageName + ".proxy.ClientProxyClass";
	public static final String commonProxy = packageName + ".proxy.CommonProxyClass";
	
	@SidedProxy(clientSide = clientProxy, serverSide = commonProxy)
	public static CommonProxyClass proxy;

	public static Logger logger;
	
	public static ArmorClassHandler armorClassTickHandler = new ArmorClassHandler();
	public static ArmorAbilityTickHandler armorAbilityTickHandler = new ArmorAbilityTickHandler();



	public static ArrayList<IArmorClass> armorClassesList;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) throws Exception
	{
		logger = Logger.getLogger(ID);
		logger.setParent(FMLLog.getLogger());
		
		armorClassesList = new ArrayList<IArmorClass>();
		armorClassesList.add(IArmorClass.emptyClass);
		MinecraftForge.EVENT_BUS.register(new ArcherDamageEventHandler());
		
		//just testing making an armor class
		ArrayList<IAbility> diamondAbility = new ArrayList<IAbility>();
		
		AbilityPotionEffect speedThree = new AbilityPotionEffect(1,2);
		ArmorAbilityRegistry.register(speedThree);
		
		AbilityPotionEffect jumpThree = new AbilityPotionEffect(8,2);
		ArmorAbilityRegistry.register(jumpThree);
		
		AbilityItemHeld sugarSpeedThree = new AbilityItemHeld(338 , speedThree);
		ArmorAbilityRegistry.register(sugarSpeedThree);
		
		AbilityArcherDamage doubleOverTwenty = new AbilityArcherDamage(2, 20);
		ArmorAbilityRegistry.register(doubleOverTwenty);
		
		diamondAbility.add(sugarSpeedThree);
		diamondAbility.add(jumpThree);
		diamondAbility.add(doubleOverTwenty);
		
		ArmorSet diamondArmor = new ArmorSet(310, 311, 312, 313);
		
		ArmorClass diamondClass = new ArmorClass("Diamond", diamondArmor, diamondAbility);
		
		armorClassesList.add(diamondClass);
		//just testing making an armor class
		
		
		/*
		 * Configuration config = new
		 * Configuration(event.getSuggestedConfigurationFile()); config.load();
		 * config.save();
		 */
		// factionsList = FileDataHandler.factionsListProvider(event);
		// String factionsZone = "Global";
		// factionsList = GroupsHandler.createFactionList(factionsZone);
		// System.out.println(factionsList);
	}

	@Init
	public void init(FMLInitializationEvent event)
	{
		//cpw.mods.fml.common.registry.TickRegistry.registerTickHandler(pvpDamageTracker, Side.SERVER);
	}

	@PostInit
	public static void postInit(FMLPostInitializationEvent event)
	{

	}


	@ServerStarting
	public static void load(FMLServerStartingEvent e)
	{
		//MinecraftForge.EVENT_BUS.register(new PvPEventHandler());

	}

	@ServerStarted
	public static void serverStarted(FMLServerStartedEvent e)
	{
		cpw.mods.fml.common.registry.TickRegistry.registerTickHandler(armorClassTickHandler, Side.SERVER);
		cpw.mods.fml.common.registry.TickRegistry.registerTickHandler(armorAbilityTickHandler, Side.SERVER);
		GameRegistry.registerPlayerTracker(armorClassTickHandler);
	}
}