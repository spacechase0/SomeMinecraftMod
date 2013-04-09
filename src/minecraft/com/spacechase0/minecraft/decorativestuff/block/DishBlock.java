package com.spacechase0.minecraft.decorativestuff.block;

import com.spacechase0.minecraft.decorativestuff.DecorativeStuff;
import com.spacechase0.minecraft.decorativestuff.dish.type.DishType;
import com.spacechase0.minecraft.decorativestuff.item.DishItem;
import com.spacechase0.minecraft.decorativestuff.tileentity.DishTileEntity;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class DishBlock extends BlockContainer
{
	public DishBlock( int id )
	{
		super( id, Material.rock );
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
    		DishTileEntity dish = ( DishTileEntity ) world.getBlockTileEntity( x, y, z );
    		onRightClick( world, dish, player, x, y, z );
            return true;
        }
    }
	
	@Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6)
    {
        DishTileEntity dish = ( DishTileEntity ) world.getBlockTileEntity(x, y, z);
        
        if (dish != null)
        {
        	ItemStack stack = dish.getStackInSlot( 0 );
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
                
            	dish.decrStackSize( 0, stack.stackSize );
        	}
        }
        
        // TO DO: Check creative mode
        {
    		Random rand = new Random();
    		
            float fx = rand.nextFloat() * 0.8F + 0.1F;
            float fy = rand.nextFloat() * 0.8F + 0.1F;
            float fz = rand.nextFloat() * 0.8F + 0.1F;
            
			ItemStack stack = new ItemStack( getCorrespondingItem( dish ).itemID, 1, dish.getDishData().getAsData() );
            
    		EntityItem entity = new EntityItem( world, (double)((float)x + fx), (double)((float)y + fy), (double)((float)z + fz), stack);
    		
            float f3 = 0.05F;
            entity.motionX = (double)((float)rand.nextGaussian() * f3);
            entity.motionY = (double)((float)rand.nextGaussian() * f3 + 0.2F);
            entity.motionZ = (double)((float)rand.nextGaussian() * f3);
            
            world.spawnEntityInWorld(entity);
        }

        super.breakBlock( world, x, y, z, par5, par6 );
    }

	@Override
    public void registerIcons( IconRegister register )
    {
    }

	@Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return 0;
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

	public abstract TileEntity createNewTileEntity( World world );
    public abstract void setBlockBoundsBasedOnState( IBlockAccess access, int x, int y, int z );
    public abstract void onRightClick( World world, DishTileEntity dish, EntityPlayer player, int x, int y, int z );
    public abstract DishItem getCorrespondingItem( DishTileEntity dish );
    public abstract DishType getDishType();
}
