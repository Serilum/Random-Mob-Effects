package com.natamus.randommobeffects.util;

import com.natamus.collective.data.GlobalVariables;
import com.natamus.collective.functions.DataFunctions;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {
	private static final List<String> defaultblacklist = new ArrayList<String>(Arrays.asList("minecraft:instant_health", "minecraft:instant_damage", "minecraft:invisibility", "minecraft:wither", "minecraft:glowing", "minecraft:levitation", "minecraft:bad_omen", "minecraft:hero_of_the_village"));
	private static final List<Holder<MobEffect>> potioneffects = new ArrayList<Holder<MobEffect>>();
	
	private static final String dirpath = DataFunctions.getConfigDirectory() + File.separator + "randommobeffects";
	private static final File dir = new File(dirpath);
	private static final File file = new File(dirpath + File.separator + "blacklist.txt");
	
	public static boolean setupPotionEffects() throws IOException {
		List<String> blacklist = new ArrayList<String>();
		
		PrintWriter writer = null;
		if (!dir.isDirectory() || !file.isFile()) {
			dir.mkdirs();
			writer = new PrintWriter(dirpath + File.separator + "blacklist.txt", StandardCharsets.UTF_8);
		}
		else {
			String blcontent = new String(Files.readAllBytes(Paths.get(dirpath + File.separator + "blacklist.txt", new String[0])));
			for (String effectrl : blcontent.split("," )) {
				String name = effectrl.replace("\n", "").trim();
				if (name.startsWith("!")) {
					blacklist.add(name.replace("!", ""));
				}
			}
		}
		
		for (MobEffect effect : BuiltInRegistries.MOB_EFFECT) {
			ResourceLocation rl = BuiltInRegistries.MOB_EFFECT.getKey(effect);
			if (rl == null) {
				continue;
			}
			
			String name = rl.toString();
			
			if (writer != null) {
				String towrite = name + ",";
				if (defaultblacklist.contains(name)) {
					blacklist.add(name);
					towrite = "!" + towrite;
				}
				
				writer.println(towrite);
			}
			
			if (!blacklist.contains(name)) {
				potioneffects.add(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(effect));
			}
		}
		
		if (writer != null) {
			writer.close();
		}

		return potioneffects.size() > 0;
	}
	
	public static Holder<MobEffect> getRandomEffect() {
		int i = GlobalVariables.random.nextInt(potioneffects.size());
		return potioneffects.get(i);
	}
}
