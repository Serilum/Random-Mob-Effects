package com.serilum.randommobeffects.util;

import com.natamus.collective.config.DuskConfig;
import com.natamus.collective.config.DuskConfigMigration;
import com.natamus.collective.data.GlobalVariables;
import com.natamus.collective.functions.ConfigFunctions;
import com.serilum.randommobeffects.config.ConfigHandler;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class Util {
	public static Holder<MobEffect> getRandomEffect(Level level) {
		Registry<MobEffect> effectRegistry = level.registryAccess().registryOrThrow(Registries.MOB_EFFECT);

		List<Holder<MobEffect>> allowedEffects = new ArrayList<>();
		for (MobEffect effect : effectRegistry) {
			if (!ConfigFunctions.isInIdList(ConfigHandler.effectBlacklist, effectRegistry.getKey(effect).toString())) {
				allowedEffects.add(effectRegistry.wrapAsHolder(effect));
			}
		}

		if (allowedEffects.isEmpty()) {
			return null;
		}
		return allowedEffects.get(GlobalVariables.random.nextInt(allowedEffects.size()));
	}

	public static void moveBlacklistFileIntoConfig() {
		String disabledEffects = DuskConfigMigration.takeDisabledIdsFromFile(Reference.MOD_ID, "blacklist.txt");
		if (disabledEffects == null) {
			return;
		}

		ConfigHandler.effectBlacklist = disabledEffects;
		DuskConfig.write(Reference.MOD_ID);
	}
}
