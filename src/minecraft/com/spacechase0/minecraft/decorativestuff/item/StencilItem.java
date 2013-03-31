package com.spacechase0.minecraft.decorativestuff.item;

public class StencilItem extends SimpleItem
{
	public StencilItem( int id, String name )
	{
		super( id, name + "Stencil" );
		
		setMaxStackSize( 1 );
		setMaxDamage( 4 );
	}
}
