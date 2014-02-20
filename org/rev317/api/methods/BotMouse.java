package org.rev317.api.methods;

import org.rev317.Loader;

/**
 * 
 * BotMouse handler
 * 
 * @author Everel
 *
 */
public class BotMouse {

	/**
	 * Gets the mouse X position of the bot mouse
	 * @return X pos
	 */
	public static int getMouseX() {
		return Loader.getClient().getBotX();
	}

	/**
	 * Gets the mouse Y position of the bot mouse
	 * @return Y pos
	 */
	public static int getMouseY() {
		return Loader.getClient().getBotY();
	}

}
