package com.spacechase0.minecraft.decorativestuff.dish.type;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class PlateType extends DishType
{
	public PlateType()
	{
		super( ( byte ) 0, "Plate" );
	}
	
	@Override
	public boolean isStackValid( ItemStack stack )
	{
		return ( ( stack == null ) || ( stack.getItem() instanceof ItemFood ) );
	}
}
