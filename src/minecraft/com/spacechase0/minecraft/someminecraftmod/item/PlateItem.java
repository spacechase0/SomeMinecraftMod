package com.spacechase0.minecraft.someminecraftmod.item;

import com.spacechase0.minecraft.someminecraftmod.SomeMinecraftMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class PlateItem extends Item/*Block*/
{
	public PlateItem( int id )
	{
		super( id );
		
		setUnlocalizedName( "plate" );
		setCreativeTab( SomeMinecraftMod.decorativeTab );
	}

    @SideOnly(Side.CLIENT)
    public void updateIcons( IconRegister register )
    {
        iconIndex = register.registerIcon( "someMinecraftMod:plate" );
    }
    
    
}
