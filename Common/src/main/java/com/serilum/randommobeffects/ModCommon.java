package com.serilum.randommobeffects;

import com.serilum.randommobeffects.config.ConfigHandler;
import com.serilum.randommobeffects.util.Util;

public class ModCommon {

	public static void init() {
		ConfigHandler.initConfig();
		load();
	}

	private static void load() {
		Util.moveBlacklistFileIntoConfig();
	}
}