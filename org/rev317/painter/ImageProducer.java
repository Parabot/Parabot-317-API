package org.rev317.painter;

import java.awt.Image;
import java.awt.image.ImageObserver;

/**
 * 
 * @author Everel
 *
 */
public abstract class ImageProducer implements ImageObserver {
	
	/**
	 * Gets this imageproducer's image
	 * @return image of this imageproducer
	 */
	public abstract Image getImage();

}
