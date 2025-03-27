package com.natamus.randommobeffects.forge.events;

import com.natamus.randommobeffects.events.AddEffectEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeAddEffectEvent {
	@SubscribeEvent
	public static void onMobSpawn(EntityJoinLevelEvent e) {
		AddEffectEvent.onMobSpawn(e.getLevel(), e.getEntity());
	}
}
