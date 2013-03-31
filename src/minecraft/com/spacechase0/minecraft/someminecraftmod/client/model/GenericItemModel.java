package com.spacechase0.minecraft.someminecraftmod.client.model;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class GenericItemModel extends FoodModel {

	@Override
	public String getTextureName()
	{
		return "/gui/items.png";
	}

	@Override
	public void renderFood( ItemStack food )
	{
		// Modified from RenderItem.renderDroppedItem
		Tessellator tessellator = Tessellator.instance;
		Icon icon = food.getItem().getIcon( food, 0 );
        float f4 = icon.getMinU();
        float f5 = icon.getMaxU();
        float f6 = icon.getMinV();
        float f7 = icon.getMaxV();
        float f8 = 1.0F;
        float f9 = 0.5F;
        float f10 = 0.25F;
        float f11 = 0.021875F;
        float f12 = 0.0625F /* Mine */ * 2.25f;
        glPushMatrix();
        {
        	// Wish I understood this...
            //GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        	glRotatef( 90.f, 1.f, 0.f, 0.f ); // MINE
        	glTranslatef( ( 1.f / 16 ) * 0.5f, ( 1.f / 16 ) * 0.75f, 1.f / 16 * 0.5f ); // MINE
        	/*
            GL11.glTranslatef(-f9, -f10, -((f12 + f11) * (float)1 / 2.0F));
            GL11.glTranslatef(0.0F, 0.0F, f12 + f11);
            GL11.glTranslatef(0f, 0f, f12 + f11);
            */
            GL11.glColor4f(1.f, 1.f, 1.f, 1.0F);
        	ItemRenderer.renderItemIn2D(tessellator, f5, f6, f4, f7, icon.getSheetWidth(), icon.getSheetHeight(), f12);
        }
        glPopMatrix();
	}
}
