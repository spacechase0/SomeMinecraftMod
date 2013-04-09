package com.spacechase0.minecraft.decorativestuff.block;

import com.spacechase0.minecraft.decorativestuff.DecorativeStuff;
import com.spacechase0.minecraft.decorativestuff.tileentity.KilnTileEntity;
import java.lang.reflect.Field;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class KilnBlock extends BlockFurnace
{
	public KilnBlock( int id, boolean theActive )
	{
		super( id, theActive );
		active = theActive;
		
		setUnlocalizedName( "kiln" );
		if ( !active )
		{
			setCreativeTab( DecorativeStuff.decorativeTab );
		}
	}

	@Override
    public void registerIcons( IconRegister register )
    {
		blockIcon = Block.brick.getBlockTextureFromSide( 0 );
		mainIcon = register.registerIcon( "decorativestuff:kiln" + ( active ? "Active" : "Idle" ) );
    }
	
	@Override
    public Icon getBlockTextureFromSideAndMetadata( int side, int meta )
    {
        return ( side != meta ? this.blockIcon : this.mainIcon );
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
	
	//@Override
    public static void updateKilnBlockState( boolean burning, World world, int x, int y, int z )
    {
        int meta = world.getBlockMetadata( x, y, z );
        TileEntity entity = world.getBlockTileEntity( x, y, z );
        setKeepFurnaceInventory( true );

        if (burning)
        {
            world.setBlock( x, y, z, DecorativeStuff.activeKiln.blockID );
        }
        else
        {
            world.setBlock( x, y, z, DecorativeStuff.idleKiln.blockID );
        }

        setKeepFurnaceInventory( false );
        world.setBlockMetadataWithNotify( x, y, z, meta, 2 );

        if ( entity != null )
        {
            entity.validate();
            world.setBlockTileEntity( x, y, z, entity );
        }
    }
	
	private static void setKeepFurnaceInventory( boolean keep )
	{
		try
		{
			Class c = BlockFurnace.class;
			Field field = c.getDeclaredFields()[ 2 ];
			field.setAccessible( true );
			field.setBoolean( null, keep );
		}
		catch ( Exception exception )
		{
			exception.printStackTrace();
		}
	}
	
	private final boolean active;
	private Icon mainIcon;
}
