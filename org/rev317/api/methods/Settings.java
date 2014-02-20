package org.rev317.api.methods;

import org.rev317.Loader;
import org.rev317.accessors.Client;

public class Settings {
	
	static Client client;
	public static Settings settings;
	public Settings() {
		Settings.client = Loader.getClient();
	}
	
	public static Settings getInstance() {
		if (settings == null) {
			settings = new Settings();
		}
		return settings;
	}

	public final int getSetting(int id) {
		return client.getSettings()[id];
	}
	
	public final int[] getSettings() {
		return client.getSettings();
	}
}
