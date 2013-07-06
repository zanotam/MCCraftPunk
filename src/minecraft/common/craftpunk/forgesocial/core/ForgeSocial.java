package common.craftpunk.forgesocial.core;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import common.craftpunk.forgesocial.core.proxy.CommonProxyClass;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarted;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
//import craftpunk.forgesocial.core.claims.*;
//import craftpunk.forgesocial.core.groups.ClaimMakingGroup;
//import craftpunk.forgesocial.core.groups.SocialGroup;

@Mod(modid = ForgeSocial.ID, name = ForgeSocial.fullName, version = ForgeSocial.VERSION)
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class ForgeSocial
{
    public static final String ID = "ForgeSocial";
    public static final String VERSION = "1.0.0a";
    public static final String packageName = "common.craftpunk.forgesocial.core";
    public static final String fullName = "The Forge Social Mod";

	@Instance(ID)
	public static ForgeSocial instance;
	
	public static final String clientProxy = packageName + ".proxy.ClientProxyClass";
	public static final String commonProxy = packageName + ".proxy.CommonProxyClass";
	    
	@SidedProxy(clientSide = clientProxy, serverSide = commonProxy)
	public static CommonProxyClass proxy;

	
	//public static ArrayList<String> claimingGroupPrefixList;
	
	//public static ArrayList<ClaimMakingGroup> claimingGroupList;
	//public static ArrayList<Claim> claims;
	//public static ArrayList<String> socialGroupList;


	@PreInit
	public void preInit(FMLPreInitializationEvent event) throws Exception
	{

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


	public static void load(FMLServerStartingEvent e)
	{
	}

	@ServerStarted
	public static void serverStarted(FMLServerStartedEvent e)
	{
		//Create claim making groups minus handler?
		//Create claims lists for each handler
		//Attach claims list to handler
	}
}
