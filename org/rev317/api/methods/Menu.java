package org.rev317.api.methods;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.regex.Pattern;

import org.parabot.core.Context;
import org.parabot.core.parsers.HookParser;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.rev317.accessors.Client;
import org.rev317.loader.Loader;


/**
 * 
 * @author Clisprail
 *
 */
public final class Menu {
	private static int[] menuScreenOffsets = new int[8];
	private static final Pattern TAG_PATTERN;
	
	static {
		// parse menu screen offsets
		HookParser parser = Context.resolve().getHookParser();
		final String[] offsets = parser.getConstants().get("menuScreenOffsets").split(",");
		for(int i = 0; i < offsets.length; i++) {
			menuScreenOffsets[i] = Integer.parseInt(offsets[i]);
		}
		
		// init the tag pattern
		TAG_PATTERN = Pattern.compile("@{1}[a-z|A-Z|0-9]{3}@{1}");
	}
	
	/**
	 * Gets all menu actions
	 * @return current menu action
	 */
	public static final String[] getActions() {
		final String[] actions = Loader.getClient().getMenuActions();
		final int menuActionRow = getMenuActionRow();
		final String[] menuActions = new String[menuActionRow];
		for (int i = menuActionRow - 1; i >= 0; i--) {
			menuActions[(menuActionRow - i - 1)] = TAG_PATTERN.matcher(actions[i]).replaceAll("");
		}
		return menuActions;
	}
	
	/**
	 * Gets menu action row
	 * @return menu action row
	 */
	public static final int getMenuActionRow() {
		return Loader.getClient().getMenuActionRow();
	}
	
	/**
	 * Determines if menu is open
	 * @return <b>true</b> if menu is open, otherwise <b>false</b>.
	 */
	public static final boolean isOpen() {
		return Loader.getClient().isMenuOpen();
	}
	
	/**
	 * Gets the menu screen point from given action index
	 * @param index
	 * @return point
	 */
	public static final Point getPointFromIndex(int i) {
		final Rectangle bounds = getBounds();
		return new Point(bounds.x + (bounds.width / 2), bounds.y + 25 + (15 * i));
	}
	
	/**
	 * Gets the action index which contains given string action
	 * @param action
	 * @return index if found, otherwise -1
	 */
	public static final int getActionIndex(String action) {
		final String[] actions = getActions();
		action = action.toLowerCase();
		for(int i = 0; i < actions.length; i++) {
			if(actions[i].toLowerCase().contains(action)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Gets the menu bounds
	 * @return menu bounds
	 */
	public static final Rectangle getBounds() {
		final Client client = Loader.getClient();
		int x = client.getMenuOffsetX();
		int y = client.getMenuOffsetY();
		final int width = client.getMenuWidth();
		final int height = client.getMenuHeight();
		final int menuArea = client.getMenuScreenArea();
		switch(menuArea) {
		case 0:
			x += menuScreenOffsets[0];
			y += menuScreenOffsets[1];
			break;
		case 1:
			x += menuScreenOffsets[2];
			y += menuScreenOffsets[3];
			break;
		case 2:
			x += menuScreenOffsets[4];
			y += menuScreenOffsets[5];
			break;
		case 3:
			x += menuScreenOffsets[6];
			y += menuScreenOffsets[7];
		}
		return new Rectangle(x, y, width, height);
	}

	/**
	 * Interacts with the menu
	 * @param action
	 * @param point
	 * @return <b>true</b> if successfully interacted, otherwise <b>false</b>.
	 */
	public static final boolean interact(String action, final Point point) {
		final Mouse mouse = Mouse.getInstance();
		mouse.moveMouse(point.x, point.y);
		Time.sleep(100);
		final int i = getActionIndex(action);
		if(i < 0) {
			return false;
		}
		if(i == 0) {
			// no need to interact with the menu
			mouse.click(point, true);
			return true;
		}
		if(!isOpen()) {
			mouse.click(point, false);
			Time.sleep(100);
			if(!isOpen()) {
				return false;
			}
			return interact(action, point);
		}
		final Point menuItemPoint = getPointFromIndex(i);
		mouse.click(menuItemPoint, true);
		return true;
	}
	
	

}
