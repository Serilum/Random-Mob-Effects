package com.natamus.randommobeffects.forge.events;

import com.natamus.randommobeffects.events.AddEffectEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

import java.lang.invoke.MethodHandles;

public class ForgeAddEffectEvent {
	public static void registerEventsInBus() {
		// BusGroup.DEFAULT.register(MethodHandles.lookup(), ForgeAddEffectEvent.class);

		EntityJoinLevelEvent.BUS.addListener(ForgeAddEffectEvent::onMobSpawn);
	}

	@SubscribeEvent
	public static void onMobSpawn(EntityJoinLevelEvent e) {
		AddEffectEvent.onMobSpawn(e.getLevel(), e.getEntity());
	}
}
