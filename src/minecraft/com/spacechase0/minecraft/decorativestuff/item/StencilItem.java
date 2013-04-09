package com.spacechase0.minecraft.decorativestuff.item;

public class StencilItem extends SimpleItem
{
	public StencilItem( int id, String name, byte theStencil )
	{
		super( id, name + "Stencil" );
		stencil = theStencil;
		
		setMaxStackSize( 1 );
		setMaxDamage( 4 );
	}
	
	public byte getStencilType()
	{
		return stencil;
	}
	
	private final byte stencil;
	
	public static final byte NO_STENCIL = 0;
	public static final byte STRAIGHT_STENCIL = 1;
	public static final byte ABSTRACT_STENCIL = 2;
	public static final byte BUBBLE_STENCIL = 3;
	public static final byte STENCIL_COUNT = 3 + 1;
}
