package com.spacechase0.minecraft.someminecraftmod.client;

import com.spacechase0.minecraft.someminecraftmod.client.renderer.*;
import com.spacechase0.minecraft.someminecraftmod.CommonProxy;
import com.spacechase0.minecraft.someminecraftmod.tileentity.*;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void init()
	{
		ClientRegistry.bindTileEntitySpecialRenderer( PlateTileEntity.class, new PlateRenderer() );
	}
}
