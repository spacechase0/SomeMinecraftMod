package com.spacechase0.minecraft.someminecraftmod;

import com.spacechase0.minecraft.someminecraftmod.block.*;
import com.spacechase0.minecraft.someminecraftmod.item.*;
import com.spacechase0.minecraft.someminecraftmod.tileentity.*;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.material.Material;
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

	@SidedProxy( clientSide = "com.spacechase0.minecraft.someminecraftmod.client.ClientProxy",
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
		// I don't remember what Grump said the recipe was
		// Waiting until he gets home from work :P
		
		registerCreativeTabs();
		registerBlocks();
		registerItems();
		registerTileEntities();
		registerGui();
		
		proxy.init();
	}
	
	@PostInit
	public void postInit( FMLPostInitializationEvent event )
	{
		config.save();
	}
	
	private void registerCreativeTabs()
	{
		decorativeTab = new DecorativeCreativeTab();
		LanguageRegistry.instance().addStringLocalization( "itemGroup.someMinecraftMod", "Some Minecraft Mod" );
	}
	
	private void registerBlocks()
	{
		plateBlock = new PlateBlock( getBlockId( "plateBlock", 0 ) );
		porcelainBlock = new SolidBlock( getBlockId( "porcelainBlock", 1 ), Material.glass, "porcelainBlock" );
	}
	
	private void registerItems()
	{
		plateItem = new PlateItem( getItemId( "porcelainPlateItem", 0 ), plateBlock, "porcelain" );
		GameRegistry.registerItem( plateItem, plateItem.getUnlocalizedName() );
		LanguageRegistry.addName( plateItem, "Porcelain Plate" );
	}
	
	private void registerTileEntities()
	{
		GameRegistry.registerTileEntity( PlateTileEntity.class, "Plate" );
	}
	
	private void registerGui()
	{
		NetworkRegistry.instance().registerGuiHandler( this, new GuiHandler() );
	}
	
	private int getBlockId( String name, int num )
	{
		return config.getBlock( name, DEFAULT_BLOCK_BASE + num ).getInt();
	}
	
	private int getItemId( String name, int num )
	{
		return config.getItem( name, DEFAULT_ITEM_BASE + num ).getInt();
	}
	
	public static CreativeTabs decorativeTab;
	
	public static SolidBlock porcelainBlock;
	public static PlateBlock plateBlock;
	
	public static PlateItem plateItem;
	
	private Configuration config;
	private final int DEFAULT_BLOCK_BASE = 2890;
	private final int DEFAULT_ITEM_BASE = 24890;
	
	public static final int PLATE_GUI_ID = 0;
}
