package com.natamus.randommobeffects.events;

import com.natamus.randommobeffects.config.ConfigHandler;
import com.natamus.randommobeffects.util.Reference;
import com.natamus.randommobeffects.util.Util;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;

import java.util.Set;

public class AddEffectEvent {
	public static void onMobSpawn(Level world, Entity entity) {
		if (world.isClientSide) {
			return;
		}
		
		if (!(entity instanceof LivingEntity)) {
			return;
		}
		
		if (!entity.getType().getCategory().equals(MobCategory.MONSTER)) {
			return;
		}
		
		if (ConfigHandler.disableCreepers) {
			if (entity instanceof Creeper) {
				return;
			}
		}
		
		String effecttag = Reference.MOD_ID + ".effectadded";
		Set<String> tags = entity.getTags();
		if (tags.contains(effecttag)) {
			return;
		}
		
		LivingEntity le = (LivingEntity)entity;
		MobEffect randomeffect = Util.getRandomEffect();
		
		MobEffectInstance effectinstance;
		if (ConfigHandler.hideEffectParticles) {
			effectinstance = new MobEffectInstance(randomeffect, Integer.MAX_VALUE, ConfigHandler.potionEffectLevel-1, true, false);
		}
		else {
			effectinstance = new MobEffectInstance(randomeffect, Integer.MAX_VALUE, ConfigHandler.potionEffectLevel-1);
		}
		
		le.addEffect(effectinstance);
		
		entity.addTag(effecttag);
	}
}
