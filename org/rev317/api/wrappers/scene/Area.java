package org.rev317.api.wrappers.scene;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

import org.rev317.api.methods.Calculations;

/**
 * 
 * @author Matt
 * 
 */
public class Area
{

	private Polygon p = new Polygon();

	/**
	 * Initializes a PolygonArea with the tiles given
	 * 
	 * @param tiles
	 *            tiles to use in the area
	 */
	public Area(Tile... tiles)
	{
		for (int i = 0; i < tiles.length; i++) {
			p.addPoint(tiles[i].getX(), tiles[i].getY());
		}
	}

	/**
	 * Adds a tile to the area
	 * 
	 * @param t
	 *            The tile to add the area
	 */
	public void addTile(Tile t) {
		p.addPoint(t.getX(), t.getY());
	}

	/**
	 * Gets all the tiles that are points in the area
	 * 
	 * @return the tiles.
	 */
	public Tile[] getPoints() {
		Tile[] tiles = new Tile[p.npoints];
		for (int i = 0; i < tiles.length; i++)
			tiles[i] = new Tile(p.xpoints[i], p.ypoints[i]);
		return tiles;
	}

	/**
	 * Gets all the tiles that are contained within the points.
	 * 
	 * @return the tiles.
	 */
	public Tile[] getTiles() {
		int lowestX = -1;
		int lowestY = -1;
		int highestX = -1;
		int highestY = -1;
		ArrayList<Tile> t = new ArrayList<Tile>();
		for (int i : p.xpoints) {
			if (i < lowestX || lowestX == -1) {
				lowestX = i;
			}
			if (i > highestX || highestX == -1) {
				highestX = i;
			}
		}
		for (int i : p.ypoints) {
			if (i < lowestY || lowestY == -1) {
				lowestY = i;
			}
			if (i > highestY || highestY == -1) {
				highestY = i;
			}
		}
		for (int x = lowestX - 1; x < highestX + 1; x++) {
			for (int y = lowestY - 1; y < highestY + 1; y++) {
				if (this.contains(x, y)) {
					t.add(new Tile(x, y));
				}
			}
		}
		return t.toArray(new Tile[t.size()]);
	}

	/**
	 * Checks if a tile is in the area
	 * 
	 * @param tile
	 *            The tile to check
	 * @return <b>true</b> if area does contain the tile, otherwise <b>false</b>
	 */
	public boolean contains(Tile tile) {
		return this.contains(tile.getX(), tile.getY());
	}

	public boolean contains(int x, int y) {
		int i;
		int j;
		boolean result = false;
		for (i = 0, j = p.npoints - 1; i < p.npoints; j = i++) {
			if ((p.ypoints[i] > y - 1) != (p.ypoints[j] > y - 1) && (x <= (p.xpoints[j] - p.xpoints[i]) * (y - p.ypoints[i]) / (p.ypoints[j] - p.ypoints[i]) + p.xpoints[i])) {
				result = !result;
			}
		}
		return result;
	}

	/**
	 * Calculates the polygon, for the minimap
	 * 
	 * @return A Polygon on the minimap
	 */
	public Polygon getRenderPolygon() {
		Polygon p = new Polygon();
		Tile[] tiles = getPoints();
		for (Tile t : tiles) {
			Point point = Calculations.tileToMinimap(t);
			p.addPoint(point.x, point.y);
		}
		return p;
	}

}

