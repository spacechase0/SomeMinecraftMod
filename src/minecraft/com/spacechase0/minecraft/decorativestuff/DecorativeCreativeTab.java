package com.spacechase0.minecraft.decorativestuff;

import com.spacechase0.minecraft.decorativestuff.item.StencilItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class DecorativeCreativeTab extends CreativeTabs
{
	public DecorativeCreativeTab()
	{
		super( "decorativeStuff" );
	}
	
	public ItemStack getIconItemStack()
	{
		int data = 0;
		data |= ( 15 << 0 ) & 0x00F;
		data |= ( StencilItem.ABSTRACT_STENCIL << 4 ) & 0x0F0;
		data |= ( 1 << 8 ) & 0xF00;
		return new ItemStack( DecorativeStuff.plateItem, 1, data );
	}
}
