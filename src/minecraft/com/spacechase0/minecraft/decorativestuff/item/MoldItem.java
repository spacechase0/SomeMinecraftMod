package com.spacechase0.minecraft.decorativestuff.item;

public class MoldItem extends SimpleItem
{
	public MoldItem( int id, String name, int[] theOutput )
	{
		super( id, name + "Mold" );
		output = theOutput;
		
		setMaxStackSize( 1 );
		setMaxDamage( 4 );
	}
	
	public int getOutputId( byte matType )
	{
		return output[ matType ];
	}
	
	private final int output[];
}
