package com.spacechase0.minecraft.decorativestuff.client.renderer;

import net.minecraft.item.ItemStack;

public interface IDishContentsRenderer
{
	public void renderContents( ITextureBinder binder, ItemStack stack );
}
