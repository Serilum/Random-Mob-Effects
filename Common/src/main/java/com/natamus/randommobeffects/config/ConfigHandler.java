package com.natamus.randommobeffects.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.randommobeffects.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry(min = 1, max = 50) public static int potionEffectLevel = 1;
	@Entry public static boolean hideEffectParticles = false;
	@Entry public static boolean disableCreepers = true;

	public static void initConfig() {
		configMetaData.put("potionEffectLevel", Arrays.asList(
			"The default level of the effects the mod applies, by default 1."
		));
		configMetaData.put("hideEffectParticles", Arrays.asList(
			"When enabled, hides the particles from the mobs with an effect."
		));
		configMetaData.put("disableCreepers", Arrays.asList(
			"Creepers can create infinite lingering potion effects which is probably not what you want. When enabled, the mod doesn't give creepers a random effect."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}