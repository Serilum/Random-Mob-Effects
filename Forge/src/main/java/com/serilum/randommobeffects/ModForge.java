package com.serilum.randommobeffects;

import com.natamus.collective.check.RegisterMod;
import com.natamus.collective.check.ShouldLoadCheck;
import com.serilum.randommobeffects.forge.config.IntegrateForgeConfig;
import com.serilum.randommobeffects.forge.events.ForgeAddEffectEvent;
import com.serilum.randommobeffects.util.Reference;
import com.serilum.randommobeffects.util.Util;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.io.IOException;

@Mod(Reference.MOD_ID)
public class ModForge {
	
	public ModForge(FMLJavaModLoadingContext modLoadingContext) {
		if (!ShouldLoadCheck.shouldLoad(Reference.MOD_ID)) {
			return;
		}

		BusGroup busGroup = modLoadingContext.getModBusGroup();
		FMLLoadCompleteEvent.getBus(busGroup).addListener(this::loadComplete);

		setGlobalConstants();
		ModCommon.init();

		IntegrateForgeConfig.registerScreen(modLoadingContext);

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadComplete(final FMLLoadCompleteEvent event) {
		try {
			if (Util.setupPotionEffects()) {
				ForgeAddEffectEvent.registerEventsInBus();
			}
		} catch (IOException ex) {
			System.out.println("[" + Reference.NAME + "] Something went wrong while setting up the list of potion effects.");
		}
	}

	private static void setGlobalConstants() {

	}
}