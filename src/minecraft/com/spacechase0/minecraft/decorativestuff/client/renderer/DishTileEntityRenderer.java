package com.spacechase0.minecraft.decorativestuff.client.renderer;

import com.spacechase0.minecraft.decorativestuff.DecorativeStuff;
import com.spacechase0.minecraft.decorativestuff.client.model.*;
import com.spacechase0.minecraft.decorativestuff.dish.type.DishType;
import com.spacechase0.minecraft.decorativestuff.tileentity.DishTileEntity;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class DishTileEntityRenderer extends TileEntitySpecialRenderer implements ITextureBinder
{
	@Override
	public void renderTileEntityAt( TileEntity entity, double x, double y, double z, float f )
	{
		if ( !( entity instanceof DishTileEntity ) )
		{
			return;
		}
		
		DishTileEntity dish = ( DishTileEntity ) entity;

        GL11.glPushMatrix();
        GL11.glTranslatef( ( float ) x, ( float ) y, ( float ) z );
        GL11.glColor4f( 1.0F, 1.0F, 1.0F, 1.0F );

        dish.getDishData().bindTexture( dish.getDishType().Type, this );
        models.get( dish.getDishType().id ).renderAll( this, dish.getDishData() );
        
        GL11.glColor4f( 1.0F, 1.0F, 1.0F, 1.0F );
        tileEntityRenderer.renderEngine.resetBoundTexture();
        
        ItemStack stack = dish.getStackInSlot( 0 );
        contentsRenderers.get( dish.getDishType().id ).renderContents( this, stack );
        
        GL11.glPopMatrix();
	}
	
	public void bindTexture( String tex )
	{
		bindTextureByName( tex );
	}
	
	@Override
	public void bindTexture( int id )
	{
        GL11.glBindTexture( GL11.GL_TEXTURE_2D, id );
	}
	
	private static Map< Byte, DishModel > models;
	private static Map< Byte, IDishContentsRenderer > contentsRenderers;
	
	static
	{
		models = new HashMap< Byte, DishModel >();
		models.put( DishType.PLATE.id, new PlateModel() );
		
		contentsRenderers = new HashMap< Byte, IDishContentsRenderer >();
		contentsRenderers.put( DishType.PLATE.id, new PlateContentsRenderer() );
	}
}
