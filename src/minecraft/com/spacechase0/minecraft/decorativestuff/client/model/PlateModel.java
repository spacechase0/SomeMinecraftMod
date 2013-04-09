package com.spacechase0.minecraft.decorativestuff.client.model;

import com.spacechase0.minecraft.decorativestuff.client.renderer.ITextureBinder;
import com.spacechase0.minecraft.decorativestuff.dish.data.DishData;

import net.minecraft.client.model.ModelRenderer;

public class PlateModel extends DishModel
{
	public PlateModel()
	{
		bottom = new ModelRenderer( this, 0, 0 );
		bottom.addBox( 4, 0, 4, 8, 1, 8, 0.f );
		
		top = new ModelRenderer( this, 0, 0 );
		top.addBox( 1, 1, 1, 14, 1, 14, 0.f );
	}
    
    protected void renderDish( ITextureBinder texBinder, DishData dish )
    {
    	top.render( 1.f / 16 );
    	bottom.render( 1.f / 16 );
    }
    
    protected void renderStencilQuads( ITextureBinder texBinder, DishData dish )
    {
    	renderStencilQuad( 1, 2.1f, 1, 14, 14 );
    }
    
    protected String getDishType()
    {
    	return "Plate";
    }
    
    private ModelRenderer bottom;
    private ModelRenderer top;
}
