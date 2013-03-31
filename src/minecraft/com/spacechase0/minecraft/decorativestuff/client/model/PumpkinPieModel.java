package com.spacechase0.minecraft.decorativestuff.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.item.ItemStack;

public class PumpkinPieModel extends FoodModel
{
	public PumpkinPieModel()
	{
		bigPiece = new ModelRenderer( this, 0, 0 );
		bigPiece.addBox( 3, 0, 3, 10, 5, 5, 0.f );
		
		smallPiece = new ModelRenderer( this, 0, 10 );
		smallPiece.addBox( 3, 0, 8, 5, 5, 5, 0.f );
	}
	
	@Override
	public String getTextureName()
	{
		return "/mods/decorativeStuff/textures/models/pumpkinPie.png";
	}
	
	@Override
	public void renderFood( ItemStack food )
	{
		bigPiece.render( 1.f / 16 );
		smallPiece.render( 1.f / 16 );
	}
	
	private ModelRenderer bigPiece;
	private ModelRenderer smallPiece;
}
