package com.spacechase0.minecraft.decorativestuff.item;

import com.spacechase0.minecraft.decorativestuff.DecorativeStuff;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class SimpleItem extends Item
{
	public SimpleItem( int id, String name )
	{
		super( id );

		tex = name;
		setUnlocalizedName( name );
		setCreativeTab( DecorativeStuff.decorativeTab );
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void updateIcons( IconRegister register )
    {
        iconIndex = register.registerIcon( "decorativeStuff:" + tex );
    }
	
	private final String tex;
}
