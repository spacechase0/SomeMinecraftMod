package com.spacechase0.minecraft.decorativestuff.item;

public class MoldItem extends SimpleItem
{
	public MoldItem( int id, String name )
	{
		super( id, name + "Mold" );
		
		setMaxStackSize( 1 );
		setMaxDamage( 4 );
	}
}
