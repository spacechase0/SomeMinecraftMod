package com.spacechase0.minecraft.decorativestuff.block;

import com.spacechase0.minecraft.decorativestuff.DecorativeStuff;
import com.spacechase0.minecraft.decorativestuff.dish.material.DishMaterial;
import com.spacechase0.minecraft.decorativestuff.dish.type.DishType;
import com.spacechase0.minecraft.decorativestuff.item.DishItem;
import com.spacechase0.minecraft.decorativestuff.tileentity.DishTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class PlateBlock extends DishBlock
{
	public PlateBlock( int id )
	{
		super( id );
	}
	
	@Override
	public TileEntity createNewTileEntity( World world )
	{
		return new DishTileEntity();
	}
	
	@Override
    public void setBlockBoundsBasedOnState( IBlockAccess access, int x, int y, int z )
    {
		float incr = 1.f / 16;
        this.setBlockBounds( 1 * incr, 0, 1 * incr, 15 * incr, 2 * incr, 15 * incr );
    }
    
	@Override
    public void onRightClick( World world, DishTileEntity dish, EntityPlayer player, int x, int y, int z )
	{
    	if ( player.getCurrentEquippedItem() == null  || dish.getStackInSlot( 0 ) == null )
    	{
    		player.openGui( DecorativeStuff.instance, DecorativeStuff.DISH_GUI_ID, world, x, y, z );
    	}
    	else if ( player.getFoodStats().getFoodLevel() < 20 )
    	{
    		ItemStack stack = dish.decrStackSize( 0,  1 );
    		if ( stack != null )
    		{
        		ItemFood food = ( ItemFood ) stack.getItem();
        		food.onEaten( stack, world, player );
    		}
    	}
	}
	
    public DishItem getCorrespondingItem( DishTileEntity dish )
    {
    	if ( dish.getPorcelainData() != null )
    	{
    		return DecorativeStuff.porcelainPlate;
    	}
    	else if ( dish.getWoodData() != null )
    	{
    		return DecorativeStuff.woodPlate;
    	}
    	
    	return null;
    }
	
    public DishType getDishType()
    {
    	return DishType.PLATE;
    }
}
