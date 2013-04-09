package com.spacechase0.minecraft.decorativestuff.client;

import com.spacechase0.minecraft.decorativestuff.CommonProxy;
import com.spacechase0.minecraft.decorativestuff.DecorativeStuff;
import com.spacechase0.minecraft.decorativestuff.client.model.*;
import com.spacechase0.minecraft.decorativestuff.client.renderer.*;
import com.spacechase0.minecraft.decorativestuff.tileentity.*;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.texturepacks.ITexturePack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.TextureLoadEvent;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraft.util.Icon;
import org.lwjgl.opengl.GL11;

public class ClientProxy extends CommonProxy
{
	@Override
	public void init()
	{
		ClientRegistry.bindTileEntitySpecialRenderer( DishTileEntity.class, new DishTileEntityRenderer() );
		MinecraftForgeClient.registerItemRenderer( DecorativeStuff.porcelainPlate.itemID, new DishItemRenderer( new PlateModel() ) );
		MinecraftForgeClient.registerItemRenderer( DecorativeStuff.woodPlate.itemID, new DishItemRenderer( new PlateModel() ) );
		
		textures = new HashMap< String, Integer >();
	}
	
	@Override
	public void makeDishTextures()
	{
		makeDishTextures( OreDictionary.getOres( "plankWood" ) );
	}
	
	public void makeDishTextures( List< ItemStack > items )
	{
		Iterator< ItemStack > it = items.iterator();
		while ( it.hasNext() )
		{
			ItemStack stack = it.next();
			if ( stack.itemID > 4096 || Block.blocksList[ stack.itemID ] == null )
			{
				continue;
			}
			
			if ( stack.getItemDamage() == OreDictionary.WILDCARD_VALUE )
			{
				List< ItemStack > stacks = new ArrayList< ItemStack >();
				Block.blocksList[ stack.itemID ].getSubBlocks( stack.itemID, null, stacks );
				makeDishTextures( stacks );
			}
			else
			{
				String tex = "/mod/decorativeStuff/texture/models/dishPlateWood-" + stack.itemID + "_" + stack.getItemDamage() + ".png";
				Icon icon = Block.blocksList[ stack.itemID ].getBlockTextureFromSideAndMetadata( 0, stack.getItemDamage() );
				makeTextureFor( tex, icon );
			}
		}
	}
	
	/*
	@Override
	public void checkForDishTextures( TextureLoadEvent event )
	{
		String prefix = "/mod/decorativeStuff/texture/models/dish";
		if ( event.texture.startsWith( prefix ) && event.texture.endsWith( ".png" ) )
		{
			String str = event.texture.substring( prefix.length(), event.texture.length() - 4 );
			if ( str.startsWith( "plateWood" ) )
			{
				// dishTYPEWood-ID_data.png
				String nums = str.substring( str.indexOf( "-" ) + 1 );
				String idStr = nums.substring( 0, str.indexOf( "_" ) );
				String dataStr = nums.substring( str.indexOf( "_" ) + 1 );
				
				short id = Short.parseShort( idStr );
				byte data = Byte.parseByte( dataStr );
				
				makeTextureFor( event.texture, Block.blocksList[ id ].getBlockTextureFromSideAndMetadata( 0, data ) );
			}
		}
	}
	//*/
	
	@Override
	public int getTexture( String path )
	{
		Integer i = textures.get( path );
		return ( i == null ? 0 : i );
	}
	
	private void makeTextureFor( String str, Icon icon )
	{
        try
        {
        	Minecraft mc = FMLClientHandler.instance().getClient();
			RenderEngine re = mc.renderEngine;
			
			String texPath = iconToPath( re, icon );
			ITexturePack pack = mc.texturePackList.getSelectedTexturePack();
			BufferedImage other = ImageIO.read( pack.getResourceAsStream( texPath ) );
			
			// Tiling the wood icon across a 64x32 image
			BufferedImage image = new BufferedImage( 64, 32, BufferedImage.TYPE_INT_ARGB );
			// How many times the icon will fit
			for ( int ix = 0; ix < Math.max( 1, ( int ) Math.ceil( 64.f / other.getWidth() ) ); ++ix )
			{
				for ( int iy = 0; iy < Math.max( 1, ( int ) Math.ceil( 32.f / other.getHeight() ) ); ++iy )
				{
					int baseX = ix * other.getWidth();
					int baseY = iy * other.getHeight();
					// Looping through the icons pixels
					for ( int px = 0; px < other.getWidth(); ++px )
					{
						if ( baseX + px >= 64 )
						{
							break;
						}
						
						for ( int py = 0; py < other.getHeight(); ++py )
						{
							if ( baseY + py >= 32 )
							{
								break;
							}
							
							int x = baseX + px;
							int y = baseY + py;
							
							int col = other.getRGB( px, py );
							image.setRGB( x, y, col );
						}
					}
				}
			}
			ImageIO.write( image, "PNG", new File( "image.png" ) );
			
			int id = re.allocateAndSetupTexture( image );
			textures.put( str, id );
        }
        catch ( Exception exception )
        {
        	exception.printStackTrace();
        }
	}
	
	private String iconToPath( RenderEngine re, Icon icon )
	{
		final String basePath = re.textureMapBlocks.basePath;
		final String textureExt = re.textureMapBlocks.textureExt;
		
        String name = icon.getIconName();
        String path;
        if (name.indexOf(':') == -1)
        {
            path = basePath + name + textureExt;
        }
        else
        {
            String domain = name.substring(0, name.indexOf(':'));
            String file = name.substring(name.indexOf(':') + 1);
            path = "mods/" + domain +"/" + basePath + file + textureExt;
        }
        
        return "/" + path;
	}
	
	private Map< String, Integer > textures;
}
