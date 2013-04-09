package com.spacechase0.minecraft.decorativestuff.client.model;

import com.spacechase0.minecraft.decorativestuff.client.renderer.ITextureBinder;
import com.spacechase0.minecraft.decorativestuff.dish.data.DishData;
import com.spacechase0.minecraft.decorativestuff.dish.data.PorcelainData;
import com.spacechase0.minecraft.decorativestuff.dish.data.WoodData;
import com.spacechase0.minecraft.decorativestuff.item.StencilItem;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

public abstract class DishModel extends ModelBase
{
	protected static class Color
	{
		public Color( int theR, int theG, int theB )
		{
			r = theR;
			g = theG;
			b = theB;
		}

	    public static Color get( int col )
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
	    	
	    	return new Color( 255, 0, 255 );
	    }
		
		public int r;
		public int g;
		public int b;
	}
	
	public DishModel()
	{
		tessellator = Tessellator.instance;
	}
	
    public final void renderAll( ITextureBinder texBinder, DishData dish )
	{
    	if ( dish instanceof PorcelainData )
    	{
    		PorcelainData data = ( PorcelainData ) dish;
    		Color col = Color.get( data.color );
    		GL11.glColor3f( col.r / 255.f, col.g / 255.f, col.b / 255.f );
    	}
    	
    	renderDish( texBinder, dish );

    	if ( dish instanceof PorcelainData )
    	{
    		PorcelainData data = ( PorcelainData ) dish;
	    	if ( data.stencilType != StencilItem.NO_STENCIL )
	    	{
	    		texBinder.bindTexture( getStencilTex( data.stencilType ) );
	    		
	    		Color col = Color.get( data.stencilColor );
		    	GL11.glColor3f( col.r / 255.f, col.g / 255.f, col.b / 255.f );
		    	
		    	tessellator.startDrawingQuads();
		    	renderStencilQuads( texBinder, data );
		    	tessellator.draw();
	    	}
    	}
	}
    
    protected abstract void renderDish( ITextureBinder texBinder, DishData dish );
    protected abstract void renderStencilQuads( ITextureBinder texBinder, DishData dish );
    protected abstract String getDishType();
    
    protected final void renderStencilQuad( float x, float y, float z, float w, float h )
    {
    	float incr = 1.f / 16;
    	
    	tessellator.addVertexWithUV( incr *   x,       incr * y, incr *   z,       0, 0 );
    	tessellator.addVertexWithUV( incr *   x,       incr * y, incr * ( z + h ), 0, 1 );
    	tessellator.addVertexWithUV( incr * ( x + w ), incr * y, incr * ( z + h ), 1, 1 );
    	tessellator.addVertexWithUV( incr * ( x + w ), incr * y, incr *   z,       1, 0 );
    	
    	tessellator.addVertexWithUV( incr *   x,       incr * y, incr *   z,       0, 0 );
    	tessellator.addVertexWithUV( incr * ( x + w ), incr * y, incr *   z,       1, 0 );
    	tessellator.addVertexWithUV( incr * ( x + w ), incr * y, incr * ( z + h ), 1, 1 );
    	tessellator.addVertexWithUV( incr *   x,       incr * y, incr * ( z + h ), 0, 1 );
    }
    
    private String getStencilTex( int type )
    {
    	return "/mods/decorativestuff/textures/models/stencil" + getDishType() + type + ".png";
    }
    
    private Tessellator tessellator;
}
