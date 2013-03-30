package com.spacechase0.minecraft.someminecraftmod.block;

import com.spacechase0.minecraft.someminecraftmod.SomeMinecraftMod;
import com.spacechase0.minecraft.someminecraftmod.tileentity.PlateTileEntity;

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
		setCreativeTab( SomeMinecraftMod.decorativeTab );
	}

	@Override
    public void registerIcons( IconRegister register )
    {
		blockIcon = register.registerIcon( "someMinecraftMod:" + name );
    }
	
	private final String name;
}
