package org.rev317.api.wrappers.hud;

import org.rev317.loader.Loader;

/**
 * 
 * @author Clisprail, Dane
 * 
 * 
 * 
 */
public enum Tab {

	ATTACK(0), STATS(1), QUESTS(2), INVENTORY(3), EQUIPMENT(4), PRAYER(5), MAGIC(
			6), CLANCHAT(7), FRIENDS(8), IGNORE(9), LOGOUT(10), OPTIONS(11), EMOTES(
			12), MUSIC(13), UNKNOWN(-1);

	private int index;

	private Tab(int index) {
		this.index = index;
	}

	/**
	 * Gets the Tab's index.
	 * 
	 * @return the index
	 */
	public final int getIndex() {
		return this.index;
	}

	/**
	 * Opens the Tab.
	 */
	public final void open() {
		Loader.getClient().openTab(this.getIndex());
	}

	/**
	 * Returns true if the Tab is open.
	 */
	public final boolean isOpen() {
		return Loader.getClient().getOpenTab() == this.getIndex();
	}

	/**
	 * Returns the name of the Tab.
	 */
	public final String getName() {
		return Character.toUpperCase(name().charAt(0))
				+ name().toLowerCase().substring(1);
	}

	public final String toString() {
		return "Tab: [" + this.getName() + ", " + this.getIndex() + "]";
	}

	/**
	 * Gets the currently opened Tab.
	 * 
	 * @return the Tab
	 */
	public static final Tab getOpened() {
		return get(Loader.getClient().getOpenTab());
	}

	/**
	 * Opens a Tab with the provided index.
	 * 
	 * @param index
	 */
	public static final void open(int index) {
		Loader.getClient().openTab(index);
	}

	/**
	 * Opens the provided Tab.
	 * 
	 * @param Tab
	 *            the Tab.
	 */
	public static final void open(Tab Tab) {
		Tab.open();
	}

	/**
	 * Finds and opens a tab that matches the provided string.
	 * 
	 * @param Name
	 *            the tab name.
	 */
	public static final void open(String name) {
		for (Tab t : Tab.values()) {
			if (t.getName().equalsIgnoreCase(name)) {
				t.open();
				return;
			}
		}
	}

	/**
	 * Gets the name of the Tab with the matching provided index.
	 * 
	 * @param index
	 *            the Tab index.
	 * @return the name of the Tab.
	 */
	public static final String getName(int index) {
		for (Tab Tab : values()) {
			if (Tab.getIndex() == index) {
				return Tab.getName();
			}
		}
		return "Invalid Tab";
	}

	/**
	 * Gets the Tab with the provided index.
	 * 
	 * @param index
	 *            the index
	 * @return the Tab
	 */
	public static final Tab get(int index) {
		for (Tab Tab : values()) {
			if (Tab.getIndex() == index) {
				return Tab;
			}
		}
		return Tab.UNKNOWN;
	}

}
