package com.serilum.randommobeffects.neoforge.events;

import com.serilum.randommobeffects.events.AddEffectEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.bus.api.SubscribeEvent;

public class NeoForgeAddEffectEvent {
	@SubscribeEvent
	public static void onMobSpawn(EntityJoinLevelEvent e) {
		AddEffectEvent.onMobSpawn(e.getLevel(), e.getEntity());
	}
}
