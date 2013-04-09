package com.spacechase0.minecraft.decorativestuff.client.renderer;

import com.spacechase0.minecraft.decorativestuff.client.model.DishModel;
import com.spacechase0.minecraft.decorativestuff.dish.data.DishData;
import com.spacechase0.minecraft.decorativestuff.item.DishItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class DishItemRenderer implements IItemRenderer, ITextureBinder
{
	public DishItemRenderer( DishModel theModel )
	{
		dish = theModel;
	}
	
	@Override
	public boolean handleRenderType( ItemStack item, ItemRenderType type )
	{
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper( ItemRenderType type, ItemStack item, ItemRendererHelper helper )
	{
		return ( helper == IItemRenderer.ItemRendererHelper.ENTITY_ROTATION || helper == IItemRenderer.ItemRendererHelper.ENTITY_BOBBING );
	}

	@Override
	public void renderItem( ItemRenderType type, ItemStack stack, Object... data )
	{
		Item itemBase = stack.getItem();
		if ( !( itemBase instanceof DishItem ) )
		{
			return;
		}
		
		if ( data[ 0 ] instanceof RenderBlocks )
		{
			RenderBlocks blocks = ( RenderBlocks ) data[ 0 ];
			re = blocks.minecraftRB.renderEngine;
		}
		else if ( data.length > 1 && data[ 1 ] instanceof RenderEngine )
		{
			re = ( RenderEngine ) data[ 1 ];
		}
		
		if ( type == IItemRenderer.ItemRenderType.ENTITY )
		{
			GL11.glTranslatef( -0.5f, 0.f, -0.5f );
		}
		else if ( type == IItemRenderer.ItemRenderType.EQUIPPED )
		{
			GL11.glRotatef( -45.f, 1.f, 0.f, 0.f );
			GL11.glRotatef( 45.f / 2, 0.f, 0.f, 1.f );
		}
		else if ( type == IItemRenderer.ItemRenderType.INVENTORY )
		{
			GL11.glScalef( 12.f, 12.f, 12.f );
			GL11.glRotatef( 135.f + ( 45.f / 4 ), 1.f, 0.f, 0.f );
			GL11.glRotatef( 45.f, 0.f, 1.f, 0.f );
			//GL11.glRotatef( 135.f, 0.f, 0.f, 1.f );
			GL11.glTranslatef( 0.f, -1.25f, 0.f );
		}
		
		DishItem item = ( DishItem ) itemBase;
		int itemData = stack.getItemDamage();
		DishData dishData = item.mat.getDishData( itemData );

		dishData.bindTexture( item.block.getDishType().Type, this );
		dish.renderAll( this, dishData );
		re.resetBoundTexture();
	}
	
	@Override
	public void bindTexture( String tex )
	{
		re.bindTexture( tex );
	}
	
	@Override
	public void bindTexture( int id )
	{
        GL11.glBindTexture( GL11.GL_TEXTURE_2D, id );
	}
	
	private RenderEngine re;
	protected DishModel dish;
}
