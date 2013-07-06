package common.craftpunk.minepunk.world.core;

import java.io.File;
import java.util.logging.Logger;

import common.craftpunk.minepunk.world.core.grass.BlockPureGrass;
import common.craftpunk.minepunk.world.core.grass.PureGrassRenderingHandler;
import common.craftpunk.minepunk.world.core.sunstone.BlockSunStone;
import common.craftpunk.minepunk.world.core.sunstone.TileEntitySunStone;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
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
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = CraftPunkWorld.ID, name = CraftPunkWorld.fullName, version = CraftPunkWorld.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class CraftPunkWorld
{
    public static final String ID = "CraftPunk World Core";
    public static final String VERSION = "1.0.0a";
    public static final String packageName = "common.craftpunk.minepunk.world.core";
    public static final String fullName = "CraftPunk Worldgen Mod";

    @Instance(ID)
    public static CraftPunkWorld instance;

    public static final String clientProxy = packageName + ".ClientProxyClass";
    public static final String commonProxy = packageName + ".CommonProxyClass";

    @SidedProxy(clientSide = clientProxy, serverSide = commonProxy)
    public static CommonProxyClass proxy;

    public static Logger logger;

    public static File mainConfigDir;


    public static Block blockPureGrass;

    public static Block blockSunStone;

    public TileEntity tileEntitySunstone;

    public static ISimpleBlockRenderingHandler pureGrassRenderingHandler;

    @PreInit
    public void preInit(FMLPreInitializationEvent event) throws Exception
    {
        logger = Logger.getLogger(ID);
        logger.setParent(FMLLog.getLogger());

        mainConfigDir = new File(event.getSuggestedConfigurationFile() + "CraftPunk");
        mainConfigDir.mkdir();
        // config.load();

        // config.save();



        blockPureGrass = new BlockPureGrass(251);


        blockSunStone = new BlockSunStone(252);
    }

    @Init
    public void init(FMLInitializationEvent event)
    {
        pureGrassRenderingHandler = new PureGrassRenderingHandler(RenderingRegistry.getNextAvailableRenderId());
        RenderingRegistry.registerBlockHandler(pureGrassRenderingHandler);

        GameRegistry.registerBlock(blockPureGrass, "Pure Grass");
        LanguageRegistry.addName(blockPureGrass, "Pure Grass");
        
        GameRegistry.registerBlock(blockSunStone, "Sunstone");
        LanguageRegistry.addName(blockSunStone, "Sunstone");
        
        
        GameRegistry.registerTileEntity(TileEntitySunStone.class, "Sunstone");

        
        GameRegistry.addShapelessRecipe(new ItemStack(blockPureGrass, 4),
                                        new Object[] { new ItemStack(Block.grass),
                                                       new ItemStack(Block.grass),
                                                       new ItemStack(Block.grass),
                                                       new ItemStack(Block.grass) });

        GameRegistry.addShapelessRecipe(new ItemStack(Block.grass, 4), 
                                        new Object[] { new ItemStack(blockPureGrass), 
                                                       new ItemStack(blockPureGrass), 
                                                       new ItemStack(blockPureGrass),                             
                                                       new ItemStack(blockPureGrass) });

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
