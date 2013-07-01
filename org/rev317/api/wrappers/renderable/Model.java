package org.rev317.api.wrappers.renderable;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import org.rev317.api.methods.Calculations;

/**
 * 
 * @author Clisprail
 * @author Matt
 * 
 */
public abstract class Model {
	protected org.rev317.accessors.Model accessor = null;
	private int numFaces = 0;

	// cached
	private int[] indices1;
	private int[] indices2;
	private int[] indices3;

	public Model(org.rev317.accessors.Model accessor) {
		this.accessor = accessor;
		//this.numFaces = getTriangleCount();
		int[] indices1 = getTriangleA();
		int[] indices2 = getTriangleB();
		int[] indices3 = getTriangleC();
		this.numFaces = Math.min(indices1.length, Math.min(indices2.length, indices3.length));
	}

	/**
	 * Gets the vertex count of this model
	 * 
	 * @return vertext count
	 */
	public int getVertexCount() {
		return accessor.getVertexCount();
	}

	/**
	 * Gets the triangle count of this model
	 * 
	 * @return
	 */
	public int getTriangleCount() {
		return accessor.getTexturedTriangleCount();
	}

	/**
	 * Gets the x vertices of model
	 * 
	 * @return x vertices
	 */
	public int[] getVertexX() {
		return accessor.getVertexX();
	}

	/**
	 * Gets the y vertices of model
	 * 
	 * @return y vertices
	 */
	public int[] getVertexY() {
		return accessor.getVertexY();
	}

	/**
	 * Gets the z vertices of model
	 * 
	 * @return z vertices
	 */
	public int[] getVertexZ() {
		return accessor.getVertexZ();
	}

	/**
	 * Gets the triangles of model
	 * 
	 * @return model triangles
	 */
	public int[] getTriangleA() {
		return accessor.getTriangleA();
	}

	/**
	 * Gets the triangles of model
	 * 
	 * @return model triangles
	 */
	public int[] getTriangleB() {
		return accessor.getTriangleB();
	}

	/**
	 * Gets the triangles of model
	 * 
	 * @return model triangles
	 */
	public int[] getTriangleC() {
		return accessor.getTriangleC();
	}

	/**
	 * Gets all triangles of model
	 * 
	 * @return polygon of model
	 * 
	 *         public Polygon[] getTriangles() { int x = getX(); int y = getY();
	 *         int tileHeight = Calculations.tileHeight(x, y);* return
	 *         getTriangles(x, y, tileHeight); }
	 */

	protected final Polygon[] getTriangles(double worldX, double worldY,
			int worldZ) {
		LinkedList<Polygon> localLinkedList = new LinkedList<Polygon>();

		int[] indices1 = getTriangleA();
		int[] indices2 = getTriangleB();
		int[] indices3 = getTriangleC();

		int[] xPoints = getVertexX();
		int[] yPoints = getVertexY();
		int[] zPoints = getVertexZ();
		for (int n = 0; n < indices1.length; n++) {
			Point point1 = Calculations.worldToScreen(worldX
					+ xPoints[indices1[n]], worldY + zPoints[indices1[n]],
					worldZ + yPoints[indices1[n]]);

			Point point2 = Calculations.worldToScreen(worldX
					+ xPoints[indices2[n]], worldY + zPoints[indices2[n]],
					worldZ + yPoints[indices2[n]]);

			Point point3 = Calculations.worldToScreen(worldX
					+ xPoints[indices3[n]], worldY + zPoints[indices3[n]],
					worldZ + yPoints[indices3[n]]);

			if ((point1.x >= 0) && (point2.x >= 0) && (point3.x >= 0)) {
				localLinkedList.add(new Polygon(new int[] { point1.x, point2.x,
						point3.x }, new int[] { point1.y, point2.y, point3.y },
						3));
			}
		}
		return (Polygon[]) localLinkedList.toArray(new Polygon[localLinkedList
				.size()]);
	}
	
	/**
	 * Draws the bounds of this model
	 * @param g
	 * @return 3d bounds polygon
	 */
	public Polygon drawBoundingBox(Graphics g) {
		final int[] yPoints = getVertexY();
		Arrays.sort(yPoints);
		final int[] xPoints = getVertexX();
		Arrays.sort(xPoints);
		final int[] zPoints = getVertexZ();
		Arrays.sort(zPoints);
		
		final int highestY = yPoints[0]; // height
		final int lowestY = yPoints[yPoints.length - 1]; // height
		
		final int lowestX = xPoints[0]; // tile x
		final int highestX = xPoints[xPoints.length - 1]; // tile x
		
		final int lowestZ = zPoints[0]; // tile y
		final int highestZ = zPoints[zPoints.length - 1]; // tile y
		
		final int worldX = getX();
		final int worldY = getY();
		final int worldZ = Calculations.tileHeight(worldX, worldY);
		
		final Point lowestXZGround = Calculations.worldToScreen(worldX + lowestX, worldY + lowestZ,
				worldZ + lowestY);
		final Point lowestXZHeight = Calculations.worldToScreen(worldX + lowestX, worldY + lowestZ,
				worldZ + highestY);
		final Point otherXZGround = Calculations.worldToScreen(worldX + lowestX, worldY + highestZ,
				worldZ + lowestY);
		final Point otherXZHeight = Calculations.worldToScreen(worldX + lowestX, worldY + highestZ,
				worldZ + highestY);
		
		final Point highestXZGround = Calculations.worldToScreen(worldX + highestX, worldY + lowestZ,
				worldZ + lowestY);
		final Point highestXZHeight = Calculations.worldToScreen(worldX + highestX, worldY + lowestZ,
				worldZ + highestY);
		
		final Point backXZGround = Calculations.worldToScreen(worldX + highestX, worldY + highestZ,
				worldZ + lowestY);
		final Point backXZHeight = Calculations.worldToScreen(worldX + highestX, worldY + highestZ,
				worldZ + highestY);

		
		final Polygon left = new Polygon();
		left.addPoint(lowestXZGround.x + 4, lowestXZGround.y + 4);
		left.addPoint(lowestXZHeight.x + 4, lowestXZHeight.y + 4);
		left.addPoint(otherXZHeight.x + 4, otherXZHeight.y + 4);
		left.addPoint(otherXZGround.x + 4, otherXZGround.y + 4);
		g.drawPolygon(left);
		
		final Polygon front = new Polygon();
		front.addPoint(lowestXZGround.x + 4, lowestXZGround.y + 4);
		front.addPoint(lowestXZHeight.x + 4, lowestXZHeight.y + 4);
		front.addPoint(highestXZHeight.x + 4, highestXZHeight.y + 4);
		front.addPoint(highestXZGround.x + 4, highestXZGround.y + 4);
		g.drawPolygon(front);
		
		final Polygon back = new Polygon();
		back.addPoint(otherXZHeight.x + 4, otherXZHeight.y + 4);
		back.addPoint(otherXZGround.x + 4, otherXZGround.y + 4);
		back.addPoint(backXZGround.x + 4, backXZGround.y + 4);
		back.addPoint(backXZHeight.x + 4, backXZHeight.y + 4);
		g.drawPolygon(back);
		
		final Polygon right = new Polygon();
		right.addPoint(backXZHeight.x + 4, backXZHeight.y + 4);
		right.addPoint(backXZGround.x + 4, backXZGround.y + 4);
		right.addPoint(highestXZGround.x + 4, highestXZGround.y + 4);
		right.addPoint(highestXZHeight.x + 4, highestXZHeight.y + 4);
		g.drawPolygon(right);
		return null;
	}

	protected final int[][] getModelPoints() {
		this.indices1 = getTriangleA();
		this.indices2 = getTriangleB();
		this.indices3 = getTriangleC();

		final int[] xPoints = getVertexX();
		final int[] yPoints = getVertexY();
		final int[] zPoints = getVertexZ();

		final int worldX = getX();
		final int worldY = getY();
		final int worldZ = Calculations.tileHeight(worldX, worldY);

		final int[][] screen = new int[getVertexCount()][2];
		for (int n = 0; n < numFaces; n++) {
			final Point point1 = Calculations.worldToScreen(worldX
					+ xPoints[indices1[n]], worldY + zPoints[indices1[n]],
					worldZ + yPoints[indices1[n]]);

			final Point point2 = Calculations.worldToScreen(worldX
					+ xPoints[indices2[n]], worldY + zPoints[indices2[n]],
					worldZ + yPoints[indices2[n]]);

			final Point point3 = Calculations.worldToScreen(worldX
					+ xPoints[indices3[n]], worldY + zPoints[indices3[n]],
					worldZ + yPoints[indices3[n]]);

			if ((point1.x >= 0) && (point2.x >= 0) && (point3.x >= 0)) {
				screen[indices1[n]][0] = point1.x + 4;
				screen[indices1[n]][1] = point1.y + 4;

				screen[indices2[n]][0] = point2.x + 4;
				screen[indices2[n]][1] = point2.y + 4;

				screen[indices3[n]][0] = point3.x + 4;
				screen[indices3[n]][1] = point3.y + 4;
			}

		}
		return screen;
	}

	/**
	 * Gets the triangles of this model
	 * @return polygon array of triangles
	 */
	public Polygon[] getTriangles() {
		int[][] points = getModelPoints();
		final ArrayList<Polygon> polys = new ArrayList<Polygon>();
		for (int index = 0; index < numFaces; index++) {
			final int index1 = this.indices1[index];
			final int index2 = this.indices2[index];
			final int index3 = this.indices3[index];

			final int xPoints[] = new int[3];
			final int yPoints[] = new int[3];

			xPoints[0] = points[index1][0];
			yPoints[0] = points[index1][1];
			xPoints[1] = points[index2][0];
			yPoints[1] = points[index2][1];
			xPoints[2] = points[index3][0];
			yPoints[2] = points[index3][1];

			polys.add(new Polygon(xPoints, yPoints, 3));
		}
		return polys.toArray(new Polygon[polys.size()]);
	}

	/**
	 * Draws this model wireframe
	 * @param graphics
	 */
	public void drawWireFrame(Graphics graphics) {
		final int[][] screen = getModelPoints();

		for (int index = 0; index < numFaces; index++) {
			final int index1 = indices1[index];
			final int index2 = indices2[index];
			final int index3 = indices3[index];

			final int point1X = screen[index1][0];
			final int point1Y = screen[index1][1];
			final int point2X = screen[index2][0];
			final int point2Y = screen[index2][1];
			final int point3X = screen[index3][0];
			final int point3Y = screen[index3][1];

			graphics.drawLine(point1X, point1Y, point2X, point2Y);
			graphics.drawLine(point2X, point2Y, point3X, point3Y);
			graphics.drawLine(point3X, point3Y, point1X, point1Y);
		}
	}
	
	/**
	 * Calculates a central point of this model
	 * @return center of model
	 */
	public Point getCentralPoint() {
		if (numFaces < 1) {
			return new Point(-1, -1);
		}
		int totalXAverage = 0;
		int totalYAverage = 0;
		int totalHeightAverage = 0;
		int index = 0;
		int[] xPoints = this.getVertexX();
		int[] yPoints = this.getVertexY();
		int[] zPoints = this.getVertexZ();
		
		this.indices1 = getTriangleA();
		this.indices2 = getTriangleB();
		this.indices3 = getTriangleC();

		while (index < numFaces) {
			final int index1 = indices1[index];
			final int index2 = indices2[index];
			final int index3 = indices3[index];
			totalXAverage += (xPoints[index1] + xPoints[index2] + xPoints[index3]) / 3;
			totalYAverage += (zPoints[index1] + zPoints[index2] + zPoints[index3]) / 3;
			totalHeightAverage += (yPoints[index1] + yPoints[index2] + yPoints[index3]) / 3;
			index++;
		}
		
		final int x = getX();
		final int y = getY();
		final int height = Calculations.tileHeight(x, y);

		final Point averagePoint = Calculations.worldToScreen(getX() + totalXAverage / numFaces, y + totalYAverage / numFaces, height + totalHeightAverage / numFaces);
		if (Calculations.isOnScreen(averagePoint)) {
			return averagePoint;
		}
		return new Point(-1, -1);
	}

	/**
	 * World unit x
	 * @return world unit x
	 */
	public abstract int getX();

	/**
	 * World unit y
	 * @return world unit y
	 */
	public abstract int getY();

}
