package com.spacechase0.minecraft.someminecraftmod.client.renderer;

import org.lwjgl.opengl.GL11;

import com.spacechase0.minecraft.someminecraftmod.SomeMinecraftMod;
import com.spacechase0.minecraft.someminecraftmod.client.model.*;
import com.spacechase0.minecraft.someminecraftmod.tileentity.PlateTileEntity;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class PlateRenderer extends TileEntitySpecialRenderer
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
        
        bindTextureByName( "/mods/someMinecraftMod/textures/blocks/porcelainBlock.png" );
        plateModel.renderAll();
        
        GL11.glPopMatrix();
	}
	
	private PlateModel plateModel = new PlateModel();
}
