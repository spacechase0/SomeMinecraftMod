package com.spacechase0.minecraft.decorativestuff.item;

public class StencilItem extends SimpleItem
{
	public StencilItem( int id, String name, int theStencil )
	{
		super( id, name + "Stencil" );
		stencil = theStencil;
		
		setMaxStackSize( 1 );
		setMaxDamage( 4 );
	}
	
	public int getStencilType()
	{
		return stencil;
	}
	
	private final int stencil;
	
	public static final int NO_STENCIL = 0;
	public static final int STRAIGHT_STENCIL = 1;
	public static final int ABSTRACT_STENCIL = 2;
	public static final int BUBBLE_STENCIL = 3;
	public static final int STENCIL_COUNT = 3 + 1;
}
