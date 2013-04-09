package com.spacechase0.minecraft.decorativestuff.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.item.ItemStack;

public class BreadModel extends FoodModel
{
	public BreadModel()
	{
		loaf = new ModelRenderer( this, 0, 0 );
		loaf.addBox( 3, 0, 5, 10, 4, 6, 0.f );
	}
	
	@Override
	public String getTextureName()
	{
		return "/mods/decorativestuff/textures/models/bread.png";
	}
	
	@Override
	public void renderFood( ItemStack food )
	{
		loaf.render( 1.f / 16 );
	}
	
	private ModelRenderer loaf;
}
