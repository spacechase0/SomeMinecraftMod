package com.spacechase0.minecraft.decorativestuff.item;

import com.spacechase0.minecraft.decorativestuff.DecorativeStuff;
import com.spacechase0.minecraft.decorativestuff.block.DishBlock;
import com.spacechase0.minecraft.decorativestuff.dish.*;
import com.spacechase0.minecraft.decorativestuff.dish.material.DishMaterial;
import com.spacechase0.minecraft.decorativestuff.tileentity.DishTileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class DishItem extends Item
{
	public DishItem( int id, DishBlock theBlock, DishMaterial theMat, String dish )
	{
		super( id );
		block = theBlock;
		mat = theMat;
		
        setHasSubtypes( true );
        setMaxDamage( 0 );
        setMaxStackSize( 16 );

		setUnlocalizedName( mat.getType() + dish );
		setCreativeTab( DecorativeStuff.decorativeTab );
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void updateIcons( IconRegister register )
    {
    }
	
	@Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
    {
		// From ItemReed
        int i1 = world.getBlockId(x, y, z);

        if (i1 == Block.snow.blockID && (world.getBlockMetadata(x, y, z) & 7) < 1)
        {
            par7 = 1;
        }
        else if (i1 != Block.vine.blockID && i1 != Block.tallGrass.blockID && i1 != Block.deadBush.blockID)
        {
            if (par7 == 0)
            {
                --y;
            }

            if (par7 == 1)
            {
                ++y;
            }

            if (par7 == 2)
            {
                --z;
            }

            if (par7 == 3)
            {
                ++z;
            }

            if (par7 == 4)
            {
                --x;
            }

            if (par7 == 5)
            {
                ++x;
            }
        }

        if (!player.canPlayerEdit(x, y, z, par7, stack))
        {
            return false;
        }
        else if (stack.stackSize == 0)
        {
            return false;
        }
        else
        {
            if (world.canPlaceEntityOnSide( block.blockID, x, y, z, false, par7, (Entity)null, stack))
            {
                Block block = Block.blocksList[this.block.blockID];
                int j1 = block.onBlockPlaced(world, x, y, z, par7, par8, par9, par10, 0);

                if (world.setBlock(x, y, z, this.block.blockID, j1, 3))
                {
                    if (world.getBlockId(x, y, z) == this.block.blockID)
                    {
                        Block.blocksList[this.block.blockID].onBlockPlacedBy(world, x, y, z, player, stack);
                        Block.blocksList[this.block.blockID].onPostBlockPlaced(world, x, y, z, j1);
                        
                        // Mine starts here
                        DishTileEntity dish = ( DishTileEntity ) world.getBlockTileEntity( x, y, z );
                		int data = stack.getItemDamage();
                		dish.setDishType( this.block.getDishType() );
                		dish.setDishData( mat.getDishData( data ) );
                        // And ends here
                    }

                    world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
                    --stack.stackSize;
                }
            }

            return true;
        }
    }
	
	@Override
    public void getSubItems( int id, CreativeTabs tabs, List list)
    {
		mat.addSubItems( id, list );
    }
	
	public final boolean canBeDecorated()
	{
		return ( mat == DishMaterial.PORCELAIN );
	}
	
	public final DishBlock block;
	public final DishMaterial mat;
}
