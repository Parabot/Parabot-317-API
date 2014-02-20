package org.rev317.api.wrappers.scene;

import java.awt.Point;

import org.parabot.environment.input.Mouse;
import org.rev317.api.methods.Calculations;
import org.rev317.Loader;

/**
 * 
 * Class which holds a world location
 * 
 * @author Everel
 * 
 */
public final class Tile {
	private int x;
	private int y;
	private int z;

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Tile(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Gets x
	 * 
	 * @return x
	 */
	public final int getX() {
		return x;
	}

	/**
	 * Gets y
	 * 
	 * @return y
	 */
	public final int getY() {
		return y;
	}

	/**
	 * Gets z/plane
	 * 
	 * @return plane
	 */
	public final int getPlane() {
		return z;
	}

	/**
	 * Calculates local region x
	 * 
	 * @return local region x
	 */
	public final int getRegionX() {
		return x - Loader.getClient().getBaseX();
	}

	/**
	 * Calculates local region y
	 * 
	 * @return local region y
	 */
	public final int getRegionY() {
		return y - Loader.getClient().getBaseY();
	}

	/**
	 * Calculates distance to this tile
	 * 
	 * @return distance
	 */
	public final int distanceTo() {
		return (int) Calculations.distanceTo(this);
	}

	/**
	 * Clicks this tile on minimap
	 */
	public final void clickMM() {
		final Point point = toMinimap();
		Mouse.getInstance().click(point);

	}

	/**
	 * Calculates location on screen
	 * 
	 * @return location on screen
	 */
	public final Point toScreen() {
		return Calculations.tileToScreen(this);
	}

	/**
	 * Calculates location on minimap
	 * 
	 * @return minimap point
	 */
	public final Point toMinimap() {
		return Calculations.tileToMinimap(this);
	}

	/**
	 * Determines whether this tile is on screen
	 * 
	 * @return whether this tile is on screen
	 */
	public final boolean isOnScreen() {
		return Calculations.isOnScreen(Calculations.tileToScreen(this));
	}

	/**
	 * Determines if this tile is on minimap
	 * 
	 * @return whether this tile is on minimap
	 */
	public final boolean isOnMinimap() {
		return distanceTo() < 16;
	}

	@Override
	public String toString() {
		return "Tile: [" + getX() + ", " + getY() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		if (obj == this) {
			return true;
		}

		final Tile t = (Tile) obj;
		return t.getX() == this.getX() && t.getY() == this.getY()
				&& t.getPlane() == this.getPlane();
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + this.x;
		hash = 31 * hash + this.y;
		hash = 31 * hash + this.z;
		return hash;
	}

}
