package com.spacechase0.minecraft.decorativestuff.client.renderer;

import org.lwjgl.opengl.GL11;

import com.spacechase0.minecraft.decorativestuff.DecorativeStuff;
import com.spacechase0.minecraft.decorativestuff.client.model.*;
import com.spacechase0.minecraft.decorativestuff.tileentity.PlateTileEntity;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class PlateTileEntityRenderer extends TileEntitySpecialRenderer implements TextureBinder
{
	@Override
	public void renderTileEntityAt( TileEntity entity, double x, double y, double z, float f )
	{
		if ( !( entity instanceof PlateTileEntity ) )
		{
			return;
		}
		
		PlateTileEntity plate = ( PlateTileEntity ) entity;

        GL11.glPushMatrix();
        GL11.glTranslatef( ( float ) x, ( float ) y, ( float ) z );
        GL11.glColor4f( 1.0F, 1.0F, 1.0F, 1.0F );
        
        bindTextureByName( "/mods/decorativeStuff/textures/blocks/porcelainBlock.png" );
        plateModel.renderAll( this, plate.getColor(), plate.getStencilType(), plate.getStencilColor() );
        GL11.glColor4f( 1.0F, 1.0F, 1.0F, 1.0F );
        
        ItemStack stack = plate.getStackInSlot( 0 );
        FoodModel food = ( stack != null ) ? FoodModelRegistry.getModelForId( stack.itemID ) : null;
        if ( food != null )
        {
        	float incr = 1.f / 16;
            GL11.glTranslatef( 0, incr * 2, 0 );
            if ( food.getTextureName() != null )
            {
            	bindTextureByName( food.getTextureName() );
            }
        	food.renderFood( stack );
        }
        
        GL11.glPopMatrix();
	}
	
	public void bindTexture( String tex )
	{
		bindTextureByName( tex );
	}
	
	private PlateModel plateModel = new PlateModel();
}
