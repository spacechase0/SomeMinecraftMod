package com.spacechase0.minecraft.someminecraftmod.client.model;

import net.minecraft.item.ItemStack;
import net.minecraft.client.model.ModelRenderer;

public class AppleModel extends FoodModel
{
	public AppleModel( boolean golden )
	{
		isGolden = golden;
		
		apple = new ModelRenderer( this, 0, 0 );
		apple.addBox( 6, 0, 6, 4, 4, 4, 0.f );
		
		stem = new ModelRenderer( this, 0, 0 );
		stem.addBox( 7.75f, 3.75f, 7.75f, 1, 2, 1, 0.f );
	}
	
	@Override
	public String getTextureName()
	{
		return "/mods/someMinecraftMod/textures/models/" + ( isGolden ? "golden" : "red" ) + "Apple.png";
	}
	
	@Override
	public void renderFood( ItemStack food )
	{
		apple.render( 1.f / 16 );
		stem.render( 1.f / 16 );
	}
	
	private final boolean isGolden;
	private ModelRenderer apple;
	private ModelRenderer stem;
}
