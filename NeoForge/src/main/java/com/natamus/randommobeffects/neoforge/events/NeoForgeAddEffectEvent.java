package com.natamus.randommobeffects.neoforge.events;

import com.natamus.randommobeffects.events.AddEffectEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class NeoForgeAddEffectEvent {
	@SubscribeEvent
	public static void onMobSpawn(EntityJoinLevelEvent e) {
		AddEffectEvent.onMobSpawn(e.getLevel(), e.getEntity());
	}
}
