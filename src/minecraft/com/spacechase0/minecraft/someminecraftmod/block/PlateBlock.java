package com.spacechase0.minecraft.someminecraftmod.block;

import java.util.Random;

import com.spacechase0.minecraft.someminecraftmod.SomeMinecraftMod;
import com.spacechase0.minecraft.someminecraftmod.tileentity.PlateTileEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class PlateBlock extends BlockContainer
{
	public PlateBlock( int id )
	{
		super( id, Material.rock );
		
		setUnlocalizedName( "plateBlock" );
	}
	
	@Override
	public TileEntity createNewTileEntity( World world )
	{
		return new PlateTileEntity();
	}

	@Override
    public void registerIcons( IconRegister register )
    {
    }
	
	@Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return SomeMinecraftMod.plateItem.itemID;
    }
	
	@Override
    public boolean isOpaqueCube()
    {
        return false;
    }
	
	@Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	@Override
    public int getRenderType()
    {
        return -1;
    }
	
	@Override
    public void setBlockBoundsBasedOnState( IBlockAccess access, int x, int y, int z )
    {
		float incr = 1.f / 16;
        this.setBlockBounds( 1 * incr, 0, 1 * incr, 14 * incr, 2 * incr, 14 * incr );
    }
	
	private Icon sideIcon;
	private Icon bottomIcon;
	private Icon topIcon;
}
