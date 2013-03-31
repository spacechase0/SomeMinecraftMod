package com.spacechase0.minecraft.decorativestuff.block;

import com.spacechase0.minecraft.decorativestuff.DecorativeStuff;
import com.spacechase0.minecraft.decorativestuff.tileentity.PlateTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
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
		blockIcon = register.registerIcon( "decorativeStuff:" + name );
    }
	
	private final String name;
}
