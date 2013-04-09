package com.spacechase0.minecraft.decorativestuff;

import com.spacechase0.minecraft.decorativestuff.block.*;
import com.spacechase0.minecraft.decorativestuff.dish.material.DishMaterial;
import com.spacechase0.minecraft.decorativestuff.item.*;
import com.spacechase0.minecraft.decorativestuff.tileentity.*;
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
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.TextureLoadEvent;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.event.ForgeSubscribe;

@Mod( modid = "SC0_DecorativeStuff", name = "Decorative Stuff", version = "0.2" )
@NetworkMod( clientSideRequired = true, serverSideRequired = false )
public class DecorativeStuff
{ 
	public DecorativeStuff()
	{
	}
	
	@Instance( "SC0_DecorativeStuff" )
	public static DecorativeStuff instance;

	@SidedProxy( clientSide = "com.spacechase0.minecraft.decorativestuff.client.ClientProxy",
			     serverSide = "com.spacechase0.minecraft.decorativestuff.CommonProxy" )
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
		registerCreativeTabs();
		registerBlocks();
		registerItems();
		registerRecipes();
		registerTileEntities();
		registerGui();
		
		proxy.init();
	}
	
	@PostInit
	public void postInit( FMLPostInitializationEvent event )
	{
		config.save();
		
		proxy.makeDishTextures();
	}
	
	/*
	@ForgeSubscribe
	public void onTextureLoad( TextureLoadEvent event )
	{
		proxy.checkForDishTextures( event );
	}
	//*/
	
	private void registerCreativeTabs()
	{
		decorativeTab = new DecorativeCreativeTab();
		LanguageRegistry.instance().addStringLocalization( "itemGroup.decorativeStuff", "decorativeStuff" );
	}
	
	private void registerBlocks()
	{
		plateBlock = new PlateBlock( getBlockId( "plateBlock", 0 ) );
		GameRegistry.registerBlock( plateBlock, "plateBlock" );
		LanguageRegistry.addName( plateBlock, "Plate (Block)" );
		
		porcelainBlock = new SolidBlock( getBlockId( "porcelainBlock", 1 ), Material.glass, "porcelainBlock" );
		GameRegistry.registerBlock( porcelainBlock, "porcelainBlock" );
		LanguageRegistry.addName( porcelainBlock, "Porcelain Block" );
		
		idleKiln = new KilnBlock( getBlockId( "idleKiln", 2 ), false );
		activeKiln = new KilnBlock( getBlockId( "activeKiln", 3 ), true );
		GameRegistry.registerBlock( idleKiln, "idleKiln" );
		GameRegistry.registerBlock( activeKiln, "activeKiln" );
		LanguageRegistry.addName( idleKiln, "Kiln" );
		//LanguageRegistry.addName( activeKiln, "Kiln (Active)" );
	}
	
	private void registerItems()
	{
		porcelainPlate = new DishItem( getItemId( "porcelainPlate", 0 ), plateBlock, DishMaterial.PORCELAIN, "Plate" );
		GameRegistry.registerItem( porcelainPlate, porcelainPlate.getUnlocalizedName() );
		LanguageRegistry.addName( porcelainPlate, "Porcelain Plate" );

		woodPlate = new DishItem( getItemId( "woodPlate", 8 ), plateBlock, DishMaterial.WOOD, "Wood" );
		GameRegistry.registerItem( woodPlate, woodPlate.getUnlocalizedName() );
		LanguageRegistry.addName( woodPlate, "Wooden Plate" );
		
		rawPorcelainChunk = new SimpleItem( getItemId( "rawPorcelainChunk", 1 ), "rawPorcelainChunk" );
		GameRegistry.registerItem( rawPorcelainChunk, rawPorcelainChunk.getUnlocalizedName() );
		LanguageRegistry.addName( rawPorcelainChunk, "Raw Porcelain Chunk" );
		
		moldBase = ( SimpleItem ) ( new SimpleItem( getItemId( "moldBase", 7 ), "moldBase" ) ).setMaxStackSize( 16 );
		GameRegistry.registerItem( moldBase, moldBase.getUnlocalizedName() );
		LanguageRegistry.addName( moldBase, "Mold Base" );
		
		plateMold = new MoldItem( getItemId( "plateMold", 3 ), "plate",
				                  new int[]
				                  {
			                      	porcelainPlate.itemID,
			                      	woodPlate.itemID,
				                  } );
		GameRegistry.registerItem( plateMold, plateMold.getUnlocalizedName() );
		LanguageRegistry.addName( plateMold, "Plate Mold" );
		
		stencilBase = ( SimpleItem ) ( new SimpleItem( getItemId( "stencilBase", 6 ), "stencilBase" ) ).setMaxStackSize( 16 );
		GameRegistry.registerItem( stencilBase, stencilBase.getUnlocalizedName() );
		LanguageRegistry.addName( stencilBase, "Stencil Base" );
		
		straightStencil = new StencilItem( getItemId( "straightStencil", 3 ), "straight", StencilItem.STRAIGHT_STENCIL );
		GameRegistry.registerItem( straightStencil, straightStencil.getUnlocalizedName() );
		LanguageRegistry.addName( straightStencil, "Straight Stencil" );
		
		abstractStencil = new StencilItem( getItemId( "abstractStencil", 4 ), "abstract", StencilItem.ABSTRACT_STENCIL );
		GameRegistry.registerItem( abstractStencil, abstractStencil.getUnlocalizedName() );
		LanguageRegistry.addName( abstractStencil, "Abstract Stencil" );
		
		bubbleStencil = new StencilItem( getItemId( "bubbleStencil", 5 ), "bubble", StencilItem.BUBBLE_STENCIL );
		GameRegistry.registerItem( bubbleStencil, bubbleStencil.getUnlocalizedName() );
		LanguageRegistry.addName( bubbleStencil, "Bubble Stencil" );
		
		// 6, 7, 8
	}
	
	private void registerRecipes()
	{
		GameRegistry.addShapelessRecipe( new ItemStack( rawPorcelainChunk, 1 ),
				                         new Object[]
				                         {
											Item.field_94583_ca, Item.field_94583_ca, Item.field_94583_ca, // Nether quartz
											Block.sand, Block.sand, Block.sand,
											Item.clay, Item.clay, Item.clay,
				                         } );
		
		GameRegistry.addShapedRecipe( new ItemStack( moldBase, 2 ),
				                      new Object[]
				                      {
										"#-#",
										"- -",
										"#-#",
										'#', Item.ingotIron,
										'-', Block.planks,
				                      } );
		
		GameRegistry.addShapedRecipe( new ItemStack( plateMold, 1 ),
				                      new Object[]
				                      {
										"UUU",
										'U', moldBase,
				                      } );
		
		GameRegistry.addShapedRecipe( new ItemStack( stencilBase, 2 ),
				                      new Object[]
				                      {
										"#-#",
										"-#-",
										"#-#",
										'#', Block.planks,
										'-', Item.stick,
				                      } );
		
		GameRegistry.addShapedRecipe( new ItemStack( straightStencil, 1 ),
				                      new Object[]
				                      {
										"###",
										"# #",
										"###",
										'#', stencilBase,
				                      } );
		
		GameRegistry.addShapedRecipe( new ItemStack( abstractStencil, 1 ),
				                      new Object[]
				                      {
										"# #",
										"   ",
										"# #",
										'#', stencilBase,
				                      } );
		
		GameRegistry.addShapedRecipe( new ItemStack( bubbleStencil, 1 ),
				                      new Object[]
				                      {
										" # ",
										"# #",
										" # ",
										'#', stencilBase,
				                      } );
		
		GameRegistry.addShapedRecipe( new ItemStack( idleKiln, 1 ),
				                      new Object[]
				                      {
										"#^#",
										"#O#",
										"###",
										'#', Block.brick,
										'^', Block.hopperBlock,
										'O', Block.furnaceIdle,
				                      } );
		
		GameRegistry.addRecipe( new WoodDishRecipes() );
	}
	
	private void registerTileEntities()
	{
		GameRegistry.registerTileEntity( DishTileEntity.class, "Plate" ); // "Plate" instead of "Dish" for compatibility reasons
		GameRegistry.registerTileEntity( KilnTileEntity.class, "Kiln" );
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
	public static DishBlock plateBlock;
	public static KilnBlock idleKiln;
	public static KilnBlock activeKiln;
	
	public static DishItem porcelainPlate;
	public static SimpleItem rawPorcelainChunk;
	public static SimpleItem moldBase;
	public static MoldItem plateMold;
	public static SimpleItem stencilBase;
	public static StencilItem straightStencil;
	public static StencilItem abstractStencil;
	public static StencilItem bubbleStencil;
	public static DishItem woodPlate;
	
	private Configuration config;
	private final int DEFAULT_BLOCK_BASE = 2890;
	private final int DEFAULT_ITEM_BASE = 24890;
	
	public static final int DISH_GUI_ID = 0;
	public static final int KILN_GUI_ID = 1;
}
