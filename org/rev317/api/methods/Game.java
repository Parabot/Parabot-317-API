package org.rev317.api.methods;

import java.awt.Point;

import org.rev317.accessors.CollisionMap;
import org.rev317.loader.Loader;

/**
 * 
 * @author Clisprail
 *
 */
public final class Game {
	public static final int CROSSHAIR_TYPE_NORMAL = 0;
	public static final int CROSSHAIR_TYPE_YELLOW = 1;
	public static final int CROSSHAIR_TYPE_RED = 2;
	
	
	/**
	 * Determines if point is in the 'renderable' screen
	 * @param p
	 * @return <b>true</b> if point is on screen, otherwise <b>false</b>
	 */
	public static final boolean isOnScreen(final Point p) {
		return Calculations.isOnScreen(p);
	}
	
	/**
	 * Determines if client is logged in
	 * @return <b>true</b> if client/player is logged in, otherwise <b>false</b>
	 */
	public static final boolean isLoggedIn() {
		return Loader.getClient().isLoggedIn();
	}
	
	/**
	 * Game's scene base X
	 * @return base X
	 */
	public static final int getBaseX() {
		return Loader.getClient().getBaseX();
	}
	
	/**
	 * Game's scene base Y
	 * @return base Y
	 */
	public static final int getBaseY() {
		return Loader.getClient().getBaseY();
	}
	
	/**
	 * Gets the click crosshair type
	 * @return crosshair type
	 */
	public static final int getCrosshairType() {
		return Loader.getClient().getCrosshairType();
	}
	
	/**
	 * Gets the current plane
	 * @return plane
	 */
	public static final int getPlane() {
		return Loader.getClient().getPlane();
	}
	
	/**
	 * Gets collision map
	 * @param plane
	 * @return collision map
	 */
	public static final CollisionMap getCollisionMap(final int plane) {
		return Loader.getClient().getCollisionMap()[plane];
	}
	
	/**
	 * Gets collision map on current plane
	 * @return collision map
	 */
	public static final CollisionMap getCollisionMap() {
		return getCollisionMap(Game.getPlane());
	}

	/**
	 * Get's client loop cycle
	 * @return loop cylce
	 */
	public static final int getLoopCycle() {
		return Loader.getClient().getLoopCycle();
	}

}
