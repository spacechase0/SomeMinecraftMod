package com.spacechase0.minecraft.decorativestuff.block;

import com.spacechase0.minecraft.decorativestuff.DecorativeStuff;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.World;

public class SolidBlock extends Block
{
	public SolidBlock( int id, Material mat, String theName )
	{
		super( id, mat );
		
		name = theName;
		setUnlocalizedName( name );
		setCreativeTab( DecorativeStuff.decorativeTab );
	}

	@Override
    public void registerIcons( IconRegister register )
    {
		blockIcon = register.registerIcon( "decorativestuff:" + name );
    }
	
	private final String name;
}
