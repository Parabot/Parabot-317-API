package org.rev317.api.interfaces;

import org.rev317.api.wrappers.scene.Tile;

/**
 * 
 * Can be located
 * 
 * @author Everel
 *
 */
public interface Locatable {
	
	public Tile getLocation();
	
	public int getLocalRegionX();
	
	public int getLocalRegionY();
	
	public int distanceTo();

}
