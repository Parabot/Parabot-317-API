package org.rev317.api.methods;

import java.util.ArrayList;

import org.rev317.api.wrappers.hud.Interface;
import org.rev317.api.wrappers.hud.Region;
import org.rev317.api.wrappers.hud.Tab;

/**
 * 
 * @author Clisprail
 *
 */
public final class Emotes {

	/**
	 * Determines if emotes tab is open
	 * 
	 * @return <b>true</b> if emotes tab is open
	 */
	public static final boolean isOpen() {
		return Tab.EMOTES.isOpen();
	}

	/**
	 * Fetches emotes names and returns them
	 * 
	 * @return emote names
	 */
	public static final String[] getEmotes() {
		final ArrayList<String> emotes = new ArrayList<String>();
		final Interface emoteTab = Interfaces.get(148);
		if (emoteTab == null) {
			return null;
		}
		for (final Interface child : emoteTab.getChildren()) {
			if (child != null) {
				emotes.add(child.getTooltip());
			}
		}
		return emotes.toArray(new String[emotes.size()]);
	}

	/**
	 * 
	 * @param emoteName
	 *            - name of emote
	 * @return <b>true</b> if performed successfully
	 */
	public static final boolean perform(final String emoteName) {
		if (!isOpen()) {
			Tab.EMOTES.open();
		}
		final Interface emoteTab = Interfaces.get(148);
		if (emoteTab == null) {
			return false;
		}
		for (final Interface child : emoteTab.getChildren()) {
			if (child != null) {
				if (child.getTooltip().equals(emoteName)) {
					child.interact(Region.TAB, emoteName);
					return true;
				}
			}
		}
		return false;
	}

}
