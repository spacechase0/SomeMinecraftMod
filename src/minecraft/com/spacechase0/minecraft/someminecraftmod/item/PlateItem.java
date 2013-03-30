package com.spacechase0.minecraft.someminecraftmod.item;

import com.spacechase0.minecraft.someminecraftmod.SomeMinecraftMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.*;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemReed;
import net.minecraft.util.Icon;

public class PlateItem extends ItemReed // I have no idea. Plus it's SUGARCANE!
{
	public PlateItem( int id, Block block, String theType )
	{
		super( id, block );
		
		type = theType;
		setUnlocalizedName( type + "PlateItem" );
		setCreativeTab( SomeMinecraftMod.decorativeTab );
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void updateIcons( IconRegister register )
    {
        iconIndex = register.registerIcon( "someMinecraftMod:" + type + "plate" );
    }
	
	@Override
    public Icon getIconFromDamage( int dmg )
    {
		return iconIndex;
    }
	
	public final String type;
}
