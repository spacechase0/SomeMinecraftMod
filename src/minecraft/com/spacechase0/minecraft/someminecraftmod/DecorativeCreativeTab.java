package com.spacechase0.minecraft.someminecraftmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class DecorativeCreativeTab extends CreativeTabs
{
	public DecorativeCreativeTab()
	{
		super( "someMinecraftMod" );
	}
	
	public ItemStack getIconItemStack()
	{
		return new ItemStack( SomeMinecraftMod.plateItem, 1 );
	}
}
