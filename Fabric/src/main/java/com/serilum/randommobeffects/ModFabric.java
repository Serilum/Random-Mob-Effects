package com.serilum.randommobeffects;

import com.natamus.collective.check.RegisterMod;
import com.natamus.collective.check.ShouldLoadCheck;
import com.serilum.randommobeffects.events.AddEffectEvent;
import com.serilum.randommobeffects.util.Reference;
import com.serilum.randommobeffects.util.Util;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

import java.io.IOException;

public class ModFabric implements ModInitializer {
	
	@Override
	public void onInitialize() {
		if (!ShouldLoadCheck.shouldLoad(Reference.MOD_ID)) {
			return;
		}

		setGlobalConstants();
		ModCommon.init();

		loadEvents();

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadEvents() {
		try {
			if (Util.setupPotionEffects()) {
				ServerEntityEvents.ENTITY_LOAD.register((Entity entity, ServerLevel world) -> {
					AddEffectEvent.onMobSpawn(world, entity);
				});
			}
		} catch (IOException ex) {
			System.out.println("[" + Reference.NAME + "] Something went wrong while setting up the list of potion effects.");
		}
	}

	private static void setGlobalConstants() {

	}
}
