package com.natamus.randommobeffects;

import com.natamus.collective.check.RegisterMod;
import com.natamus.randommobeffects.neoforge.config.IntegrateNeoForgeConfig;
import com.natamus.randommobeffects.neoforge.events.NeoForgeAddEffectEvent;
import com.natamus.randommobeffects.util.Reference;
import com.natamus.randommobeffects.util.Util;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;

import java.io.IOException;

@Mod(Reference.MOD_ID)
public class ModNeoForge {
	
	public ModNeoForge(IEventBus modEventBus) {
		modEventBus.addListener(this::loadComplete);

		setGlobalConstants();
		ModCommon.init();

		IntegrateNeoForgeConfig.registerScreen(ModLoadingContext.get());

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadComplete(final FMLLoadCompleteEvent event) {
		try {
			if (Util.setupPotionEffects()) {
				NeoForge.EVENT_BUS.register(NeoForgeAddEffectEvent.class);
			}
		} catch (IOException ex) {
			System.out.println("[" + Reference.NAME + "] Something went wrong while setting up the list of potion effects.");
		}
	}

	private static void setGlobalConstants() {

	}
}