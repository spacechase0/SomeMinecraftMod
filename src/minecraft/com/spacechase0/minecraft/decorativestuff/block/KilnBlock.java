package com.spacechase0.minecraft.decorativestuff.block;

import com.spacechase0.minecraft.decorativestuff.DecorativeStuff;
import com.spacechase0.minecraft.decorativestuff.tileentity.KilnTileEntity;
import com.spacechase0.minecraft.decorativestuff.tileentity.PlateTileEntity;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;

public class KilnBlock extends BlockFurnace
{
	public KilnBlock( int id, boolean active )
	{
		super( id, active );
		
		setUnlocalizedName( "kiln" );
		if ( !active )
		{
			setCreativeTab( DecorativeStuff.decorativeTab );
		}
	}
	
	@Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return DecorativeStuff.idleKiln.blockID;
    }
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if ( world.isRemote )
        {
            return true;
        }
        else
        {
        	KilnTileEntity kiln = ( KilnTileEntity ) world.getBlockTileEntity( x, y, z );
            player.openGui( DecorativeStuff.instance, DecorativeStuff.KILN_GUI_ID, world, x, y, z );

            return true;
        }
    }
	
	@Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
	{
	}
	
	@Override
	public TileEntity createNewTileEntity( World world )
	{
		return new KilnTileEntity();
	}
}
