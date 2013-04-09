package com.spacechase0.minecraft.decorativestuff.dish.type;

import net.minecraft.item.ItemStack;

public abstract class DishType
{
	DishType( byte theId, String theType )
	{
		id = theId;
		type = theType;
		Type = type.substring( 0, 1 ).toUpperCase() + type.substring( 1, type.length() );
	}
	
	public abstract boolean isStackValid( ItemStack stack );
	
	public static DishType forId( byte id )
	{
		switch ( id )
		{
			case 0: return PLATE;
		}
		
		return null;
	}
	
	public final byte id;
	public final String type;
	public final String Type;
	
	public static final DishType PLATE = new PlateType();
}
