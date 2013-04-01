package com.spacechase0.minecraft.decorativestuff.client.renderer;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class PlateItemRenderer implements IItemRenderer {

	@Override
	public boolean handleRenderType( ItemStack item, ItemRenderType type )
	{
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper( ItemRenderType type, ItemStack item, ItemRendererHelper helper )
	{
		return false;
	}

	@Override
	public void renderItem( ItemRenderType type, ItemStack item, Object... data )
	{
		// TODO Auto-generated method stub
	}
}
