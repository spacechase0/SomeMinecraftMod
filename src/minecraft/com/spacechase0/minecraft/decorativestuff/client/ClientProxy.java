package com.spacechase0.minecraft.decorativestuff.client;

import com.spacechase0.minecraft.decorativestuff.CommonProxy;
import com.spacechase0.minecraft.decorativestuff.client.renderer.*;
import com.spacechase0.minecraft.decorativestuff.tileentity.*;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void init()
	{
		ClientRegistry.bindTileEntitySpecialRenderer( PlateTileEntity.class, new PlateTileEntityRenderer() );
	}
}
