package org.rev317.api.wrappers.scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import org.rev317.accessors.Client;
import org.rev317.accessors.Renderable;
import org.rev317.accessors.SceneObjectTile;
import org.rev317.api.interfaces.Interactable;
import org.rev317.api.interfaces.Locatable;
import org.rev317.api.methods.Calculations;
import org.rev317.api.methods.Game;
import org.rev317.api.methods.Menu;
import org.rev317.api.wrappers.renderable.Model;
import org.rev317.api.wrappers.renderable.SceneObjectModel;
import org.rev317.loader.Loader;

/**
 * 
 * @author Clisprail
 *
 */
public final class SceneObject implements Locatable, Interactable {
	private SceneObjectTile accessor = null;
	public static final int TYPE_WALL = 0; // object1
	public static final int TYPE_WALLDECORATION = 1; // object2
	public static final int TYPE_GROUNDDECORATION = 2; // object3
	public static final int TYPE_GROUNDITEM = 3; // object4
	public static final int TYPE_INTERACTIVE = 4; // object5
	private int type = 0;
	
	// cached
	private Model cachedModel = null;
	
	public SceneObject(SceneObjectTile accessor, int type) {
		this.accessor = accessor;
		this.type = type;
	}
	
	/**
	 * Gets this object's hash
	 * @return hash
	 */
	public final int getHash() {
		return accessor.getHash();
	}
	
	/**
	 * Gets this object's model
	 * @return model
	 */
	public final Model getModel() {
		if(cachedModel != null) {
			return cachedModel;
		}
		final Renderable renderable = accessor.getRenderable();
		if(renderable == null || !(renderable instanceof org.rev317.accessors.Model)) {
			return null;
		}
		return cachedModel = new SceneObjectModel(this.accessor, (org.rev317.accessors.Model) accessor.getRenderable(), type);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final Tile getLocation() {
		final Client client = Loader.getClient();
		return new Tile(client.getBaseX() + getLocalRegionX(), client.getBaseY() + getLocalRegionY(), client.getPlane());
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getLocalRegionX() {
		return accessor.getX() >> 7;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getLocalRegionY() {
		return accessor.getY() >> 7;
	}
	
	/**
	 * Gets this object's id
	 * @return object id
	 */
	public final int getId() {
		return getHash() >> 14 & 0x7fff;
	}
	
	/**
	 * Calculates center point on screen
	 * @return point
	 */
	public final Point getCenterPointOnScreen() {
		return Calculations.tileToScreen(getLocalRegionX(), getLocalRegionY(), 0.5D, 0.5D, 0);
	}
	
	/**
	 * Gets this object's type
	 * @return type of object
	 */
	public final int getType() {
		return type;
	}
	
	/**
	 * Determines if this object is on screen
	 * @return <b>true</b> if this object is on screen, otherwise <b>false</b>.
	 */
	public final boolean isOnScreen() {
		return isOnScreen(false);
	}
	
	/**
	 *  Determines if this object is on screen
	 * @param useModel
	 * @return <b>true</b> if this object is on screen, otherwise <b>false</b>.
	 */
	public final boolean isOnScreen(final boolean useModel) {
		if(useModel) {
			final Model m = getModel();
			if(m != null) {
				return Calculations.isOnScreen(m.getCentralPoint());
			}
		}
		return Calculations.isOnScreen(this.getCenterPointOnScreen());
	}
	
	/**
	 * Draws this object
	 * @param graphics
	 */
	public final void draw(final Graphics g) {
		draw(g, false);
	}
	
	/**
	 * Draws this object
	 * @param graphics
	 * @param drawModel
	 */
	public final void draw(final Graphics g, final boolean drawModel) {
		if(!isOnScreen()) {
			return;
		}
		final Model m = getModel();
		if(m == null) {
			final Point p = getCenterPointOnScreen();
			Color rectColor = Color.red;
			if(this.type == TYPE_WALL) {
				rectColor = Color.pink;
			}
			g.setColor(rectColor);
			g.fillRect(p.x - 2, p.y - 2, 4, 4);
			g.setColor(Color.white);
			g.drawString(Integer.toString(getId()), p.x + 5, p.y - 2);
			return;
		}
		if(drawModel) {
			m.drawWireFrame(g);
		}
		final Point p = m.getCentralPoint();
		Color rectColor = Color.red;
		if(this.type == TYPE_WALL) {
			rectColor = Color.pink;
		}
		g.setColor(rectColor);
		g.fillRect(p.x - 2, p.y - 2, 4, 4);
		g.setColor(drawModel ? Color.black : Color.white);
		g.drawString(Integer.toString(getId()), p.x + 5, p.y - 2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int distanceTo() {
		return (int) Calculations.distanceTo(getLocation());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean interact(String action) {
		final Model model = getModel();
		final Point a = (model == null) ? getCenterPointOnScreen() : model.getCentralPoint();
		Menu.interact(action, a);
		return Game.getCrosshairType() == Game.CROSSHAIR_TYPE_RED;
	}

}
