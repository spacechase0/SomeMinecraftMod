package com.spacechase0.minecraft.decorativestuff.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class PlateModel extends ModelBase
{
	public PlateModel()
	{
		top = new ModelRenderer( this, 0, 0 );
		top.addBox( 4, 0, 4, 8, 1, 8, 0.f );
		
		bottom = new ModelRenderer( this, 0, 0 );
		bottom.addBox( 1, 1, 1, 14, 1, 14, 0.f );
	}
	
    public void renderAll()
	{
    	top.render( 1.f / 16 );
    	bottom.render( 1.f / 16 );
	}
    
    private ModelRenderer top;
    private ModelRenderer bottom;
}
