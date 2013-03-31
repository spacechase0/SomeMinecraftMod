package com.spacechase0.minecraft.decorativestuff.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.item.ItemStack;

public class MushroomSoupModel extends FoodModel
{
	public MushroomSoupModel()
	{
		bowlLeft = new ModelRenderer( this, 0, 0 );
		bowlLeft.addBox( 4, 1, 5, 1, 3, 7, 0.f );
		
		bowlRight = new ModelRenderer( this, 0, 0 );
		bowlRight.addBox( 11, 1, 4, 1, 3, 7, 0.f );
		
		bowlFront = new ModelRenderer( this, 0, 10 );
		bowlFront.addBox( 4, 1, 4, 7, 3, 1, 0.f );

		bowlBack = new ModelRenderer( this, 0, 10 );
		bowlBack.addBox( 5, 1, 11, 7, 3, 1, 0.f );

		bowlBottom = new ModelRenderer( this, 0, 22 );
		bowlBottom.addBox( 5, 0, 5, 6, 1, 6, 0.f );

		soup = new ModelRenderer( this, 0, 14 );
		soup.addBox( 5, 1, 5, 6, 2, 6, 0.f );
	}
	
	@Override
	public String getTextureName()
	{
		return "/mods/decorativeStuff/textures/models/mushroomSoup.png";
	}
	
	@Override
	public void renderFood( ItemStack food )
	{
		bowlLeft.render( 1.f / 16 );
		bowlRight.render( 1.f / 16 );
		bowlFront.render( 1.f / 16 );
		bowlBack.render( 1.f / 16 );
		bowlBottom.render( 1.f / 16 );
		soup.render( 1.f / 16 );
	}

	private ModelRenderer bowlLeft;
	private ModelRenderer bowlRight;
	private ModelRenderer bowlFront;
	private ModelRenderer bowlBack;
	private ModelRenderer bowlBottom;
	private ModelRenderer soup;
}
