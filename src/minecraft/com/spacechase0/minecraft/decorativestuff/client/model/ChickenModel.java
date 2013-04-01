package com.spacechase0.minecraft.decorativestuff.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.item.ItemStack;

public class ChickenModel extends FoodModel
{
	public ChickenModel( boolean theCooked )
	{
		cooked = theCooked;
		
		main = new ModelRenderer( this, 0, 0 );
		main.addBox( 5, 0, 2, 6, 6, 8 );
		
		leg1 = new ModelRenderer( this, 0, 14 );
		leg1.addBox( 3, 2, 4, 3, 3, 7 );
		
		bone1 = new ModelRenderer( this, 0, 24 );
		bone1.addBox( 4, 3, 10, 1, 1, 4 );
		
		leg2 = new ModelRenderer( this, 0, 14 );
		leg2.addBox( 3 + 7, 2, 4, 3, 3, 7 );
		
		bone2 = new ModelRenderer( this, 0, 24 );
		bone2.addBox( 4 + 7, 3, 10, 1, 1, 4 );
	}
	
	@Override
	public String getTextureName()
	{
		return "/mods/decorativeStuff/textures/models/" + ( cooked ? "cooked" : "raw" ) + "Chicken.png";
	}
	
	@Override
	public void renderFood( ItemStack food )
	{
		main.render( 1.f / 16 );
		
		leg1.render( 1.f / 16 );
		bone1.render( 1.f / 16 );
		
		leg2.render( 1.f / 16 );
		bone2.render( 1.f / 16 );
	}
	
	private final boolean cooked;
	private ModelRenderer main;
	private ModelRenderer leg1;
	private ModelRenderer leg2;
	private ModelRenderer bone1;
	private ModelRenderer bone2;
}
