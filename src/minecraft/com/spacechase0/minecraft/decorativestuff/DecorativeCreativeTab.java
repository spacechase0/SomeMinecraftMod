package com.spacechase0.minecraft.decorativestuff;

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
		return new ItemStack( DecorativeStuff.plateItem, 1 );
	}
}
