package com.spacechase0.minecraft.decorativestuff.client.model;

import com.spacechase0.minecraft.decorativestuff.client.renderer.PlateTileEntityRenderer;
import com.spacechase0.minecraft.decorativestuff.item.StencilItem;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

public class PlateModel extends ModelBase
{
	private class Color
	{
		public Color( int theR, int theG, int theB )
		{
			r = theR;
			g = theG;
			b = theB;
		}
		
		public int r;
		public int g;
		public int b;
	}
	
	public PlateModel()
	{
		bottom = new ModelRenderer( this, 0, 0 );
		bottom.addBox( 4, 0, 4, 8, 1, 8, 0.f );
		
		top = new ModelRenderer( this, 0, 0 );
		top.addBox( 1, 1, 1, 14, 1, 14, 0.f );
	}
	
    public void renderAll( PlateTileEntityRenderer plate, int plateColor, int stencilType, int stencilColor )
	{
    	GL11.glColor3f( getColor( plateColor ).r / 255.f, getColor( plateColor ).g / 255.f, getColor( plateColor ).b / 255.f );
    	bottom.render( 1.f / 16 );
    	top.render( 1.f / 16 );
    	
    	if ( stencilType != StencilItem.NO_STENCIL )
    	{
    		Color col = getColor(stencilColor);
    		//System.out.println(getStencilTex(stencilType)+ " "+col.r+" "+col.g+" "+col.b);
	    	plate.bindTexture( getStencilTex( stencilType ) );
	    	GL11.glColor3f( getColor( stencilColor ).r / 255.f, getColor( stencilColor ).g / 255.f, getColor( stencilColor ).b / 255.f );
	    	//GL11.glEnable( GL11.GL_BLEND );
	    	
	    	float incr = 1.f / 16;
	    	
	    	Tessellator tessellator = Tessellator.instance;
	    	tessellator.startDrawingQuads();
	    	tessellator.addVertexWithUV( incr *  1, incr * 2.1, incr *  1, 0, 0 );
	    	tessellator.addVertexWithUV( incr *  1, incr * 2.1, incr * 15, 0, 1 );
	    	tessellator.addVertexWithUV( incr * 15, incr * 2.1, incr * 15, 1, 1 );
	    	tessellator.addVertexWithUV( incr * 15, incr * 2.1, incr *  1, 1, 0 );
	    	tessellator.draw();
	    	
	    	//GL11.glDisable( GL11.GL_BLEND );
    	}
	}
    
    private Color getColor( int col )
    {
    	switch ( col )
    	{
			case 0:  return new Color(  25,  25,  25 );
			case 1:  return new Color( 150,  52,  48 );
			case 2:  return new Color(  53,  70,  27 );
			case 3:  return new Color(  79,  50,  31 );
			case 4:  return new Color(  46,  56, 141 );
			case 5:  return new Color( 126,  61, 181 );
			case 6:  return new Color(  46, 110, 137 );
			case 7:  return new Color( 154, 161, 161 );
			case 8:  return new Color(  64,  64,  64 );
			case 9:  return new Color( 208, 132, 153 );
			case 10: return new Color(  65, 174,  56 );
			case 11: return new Color( 177, 166,  39 );
			case 12: return new Color( 107, 138, 201 );
			case 13: return new Color( 179,  80, 188 );
			case 14: return new Color( 216, 125,  62 );
			case 15: return new Color( 221, 221, 211 );
    	}
    	
    	// Shouldn't happen
    	return new Color( 255, 0, 255 );
    }
    
    private String getStencilTex( int type )
    {
    	return "/mods/decorativeStuff/textures/models/stencilPlate" + type + ".png";
    }
    
    private ModelRenderer bottom;
    private ModelRenderer top;
}
