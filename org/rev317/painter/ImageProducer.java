package org.rev317.painter;

import java.awt.Image;
import java.awt.image.ImageObserver;

/**
 * 
 * @author Clisprail
 *
 */
public abstract class ImageProducer implements ImageObserver {
	
	public abstract Image getImage();

}
