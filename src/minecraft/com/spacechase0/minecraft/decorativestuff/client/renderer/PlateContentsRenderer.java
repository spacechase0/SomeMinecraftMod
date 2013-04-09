package com.spacechase0.minecraft.decorativestuff.client.renderer;

import com.spacechase0.minecraft.decorativestuff.client.model.FoodModel;
import com.spacechase0.minecraft.decorativestuff.client.model.FoodModelRegistry;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class PlateContentsRenderer implements IDishContentsRenderer
{
	@Override
	public void renderContents( ITextureBinder binder, ItemStack stack )
	{
		FoodModel food = ( stack != null ) ? FoodModelRegistry.getModelForId( stack.itemID ) : null;
	    if ( food != null )
	    {
	    	float incr = 1.f / 16;
	        GL11.glTranslatef( 0, incr * 2, 0 );
	        
	        if ( food.getTextureName() != null )
	        {
	        	binder.bindTexture( food.getTextureName() );
	        }
	    	food.renderFood( stack );
	    }
	}
}
