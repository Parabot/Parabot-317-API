package org.rev317.api.wrappers.hud;

public class Region {

	public static final Region GAME = new Region(4, 4);
	public static final Region TAB = new Region(553, 205);
	public static final Region CHATBOX = new Region(17, 357);

	private int x = 0;
	private int y = 0;

	public Region(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Get's region X
	 * @return x
	 */
	public final int getX() {
		return x;
	}

	/**
	 * Get's region Y
	 * @return y
	 */
	public final int getY() {
		return y;
	}

}
