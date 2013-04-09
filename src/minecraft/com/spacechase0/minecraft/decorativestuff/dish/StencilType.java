package com.spacechase0.minecraft.decorativestuff.dish;

public enum StencilType
{
	None( 0 ),
	Straight( 1, "straight" ),
	Abstract( 2, "abstract" ),
	Bubbles( 3, "bubble" );
	
	public final int id;
	public final String type;
	public final String Type;
	
	StencilType( int theId )
	{
		this( theId, null );
	}
	
	StencilType( int theId, String theType )
	{
		id = theId;
		type = theType;
		Type = type.substring( 0, 1 ).toUpperCase() + type.substring( 1, type.length() );
	}
	
	public static StencilType forId( int id )
	{
		switch ( id )
		{
			case 0: return None;
			case 1: return Straight;
			case 2: return Abstract;
			case 3: return Bubbles;
		}
		
		return null;
	}
}
