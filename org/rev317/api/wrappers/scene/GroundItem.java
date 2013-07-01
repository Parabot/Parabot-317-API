package org.rev317.api.wrappers.scene;

import java.awt.Point;

import org.rev317.accessors.Client;
import org.rev317.api.interfaces.Locatable;
import org.rev317.api.methods.Calculations;
import org.rev317.api.wrappers.renderable.GroundItemModel;
import org.rev317.api.wrappers.renderable.Model;
import org.rev317.loader.Loader;

/**
 * 
 * @author Clisprail
 *
 */
public final class GroundItem implements Locatable {
	private org.rev317.accessors.GroundItem accessor = null;
	private int x = 0;
	private int y = 0;
	
	private GroundItemModel cachedModel = null;
	
	public GroundItem(org.rev317.accessors.GroundItem accessor, final int x, final int y) {
		this.accessor = accessor;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets this item local location x
	 * @return x
	 */
	public final int getLocalRegionX() {
		return x;
		//return accessor.getX(); //>> 7;
	}
	
	/**
	 * Gets this item local location y
	 * @return y
	 */
	public final int getLocalRegionY() {
		return y;
		//return accessor.getY(); //>> 7;
	}

	/**
	 * Gets this ground item location
	 * @return tile location
	 */
	@Override
	public final Tile getLocation() {
		final Client client = Loader.getClient();
		return new Tile(client.getBaseX() + getLocalRegionX(), client.getBaseY() + getLocalRegionY(), client.getPlane());
	}
	
	/**
	 * Calculates center point on screen of this grounditem
	 * @return point on screen
	 */
	public final Point getCenterPointOnScreen() {
		return Calculations.tileToScreen(getLocalRegionX(), getLocalRegionY(), 0.5D, 0.5D, 0);
	}
	
	/**
	 * Determines whether this grounditem is on screen
	 * @return <b>true</b> if this grounditem is on screen, otherwise false
	 */
	public final boolean isOnScreen() {
		return Calculations.isOnScreen(this.getCenterPointOnScreen());
	}
	
	/**
	 * Gets this grounditem id
	 * @return id
	 */
	public final int getId() {
		return this.accessor.getId();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final Model getModel() {
		if(cachedModel != null) {
			return cachedModel;
		}
		
		org.rev317.accessors.Model model = this.accessor.getModel();
		if(model == null) {
			return null;
		}
		return cachedModel = new GroundItemModel(this, model);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int distanceTo() {
		return (int) Calculations.distanceTo(getLocation());
	}
	

}
