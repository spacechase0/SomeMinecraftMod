package com.spacechase0.minecraft.decorativestuff.block;

import java.util.Random;

import com.spacechase0.minecraft.decorativestuff.DecorativeStuff;
import com.spacechase0.minecraft.decorativestuff.item.StencilItem;
import com.spacechase0.minecraft.decorativestuff.tileentity.PlateTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
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
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            PlateTileEntity plate = ( PlateTileEntity ) world.getBlockTileEntity( x, y, z );
            player.openGui( DecorativeStuff.instance, DecorativeStuff.PLATE_GUI_ID, world, x, y, z );

            return true;
        }
    }
	
	@Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6)
    {
        PlateTileEntity plate = ( PlateTileEntity ) world.getBlockTileEntity(x, y, z);
        
        if (plate != null)
        {
        	ItemStack stack = plate.getStackInSlot( 0 );
        	if ( stack != null )
        	{
        		Random rand = new Random();
        		
                float fx = rand.nextFloat() * 0.8F + 0.1F;
                float fy = rand.nextFloat() * 0.8F + 0.1F;
                float fz = rand.nextFloat() * 0.8F + 0.1F;
                
        		EntityItem entity = new EntityItem( world, (double)((float)x + fx), (double)((float)y + fy), (double)((float)z + fz), stack);
        		
                float f3 = 0.05F;
                entity.motionX = (double)((float)rand.nextGaussian() * f3);
                entity.motionY = (double)((float)rand.nextGaussian() * f3 + 0.2F);
                entity.motionZ = (double)((float)rand.nextGaussian() * f3);
                
                world.spawnEntityInWorld(entity);
                
            	plate.decrStackSize( 0, stack.stackSize );
        	}
        }

        super.breakBlock( world, x, y, z, par5, par6 );
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
        return DecorativeStuff.plateItem.itemID;
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
        this.setBlockBounds( 1 * incr, 0, 1 * incr, 15 * incr, 2 * incr, 15 * incr );
    }
}
