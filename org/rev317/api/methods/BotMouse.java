package org.rev317.api.methods;

import org.rev317.Loader;

public class BotMouse {

	public static int getMouseX() {
		return Loader.getClient().getBotX();
	}

	public static int getMouseY() {
		return Loader.getClient().getBotY();
	}

}
