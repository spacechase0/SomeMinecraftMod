package com.spacechase0.minecraft.someminecraftmod;

import com.spacechase0.minecraft.someminecraftmod.item.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;

@Mod( modid = "SC0_SomeMinecraftMod", name = "SomeMinecraftMod", version = "0.1" )
@NetworkMod( clientSideRequired = true, serverSideRequired = false )
public class SomeMinecraftMod
{ 
	public SomeMinecraftMod()
	{
	}
	
	@Instance( "SC0_SomeMinecraftMod" )
	public static SomeMinecraftMod instance;

	@SidedProxy( clientSide = "com.spacechase0.minecraft.someminecraftmod.CommonProxy",
			     serverSide = "com.spacechase0.minecraft.someminecraftmod.CommonProxy" )
	public static CommonProxy proxy;
	
	@PreInit
	public void preInit( FMLPreInitializationEvent event )
	{
		config = new Configuration( event.getSuggestedConfigurationFile() );
		config.load();
	}
	
	@Init
	public void init( FMLInitializationEvent event )
	{
		decorativeTab = new DecorativeCreativeTab();
		LanguageRegistry.instance().addStringLocalization( "itemGroup.someMinecraftMod", "Some Minecraft Mod" );
		
		plateItem = new PlateItem( getItemId( "plate", 0 ) );
		GameRegistry.registerItem( plateItem, plateItem.getUnlocalizedName() );
		LanguageRegistry.addName( plateItem, "Plate" );
	}
	
	@PostInit
	public void postInit( FMLPostInitializationEvent event )
	{
		config.save();
	}
	
	private int getItemId( String name, int num )
	{
		return config.getItem( name, DEFAULT_ITEM_BASE + num ).getInt();
	}
	
	public static CreativeTabs decorativeTab;
	
	public static PlateItem plateItem;
	
	private Configuration config;
	private final int DEFAULT_ITEM_BASE = 24890;
	
}
