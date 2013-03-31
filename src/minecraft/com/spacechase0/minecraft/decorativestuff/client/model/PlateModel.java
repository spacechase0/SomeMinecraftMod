package com.spacechase0.minecraft.decorativestuff.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class PlateModel extends ModelBase
{
	public PlateModel()
	{
		bottom = new ModelRenderer( this, 0, 0 );
		bottom.addBox( 4, 0, 4, 8, 1, 8, 0.f );
		
		top = new ModelRenderer( this, 0, 0 );
		top.addBox( 1, 1, 1, 14, 1, 14, 0.f );
	}
	
    public void renderAll()
	{
    	bottom.render( 1.f / 16 );
    	top.render( 1.f / 16 );
	}
    
    private ModelRenderer bottom;
    private ModelRenderer top;
}
