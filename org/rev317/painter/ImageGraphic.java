package org.rev317.painter;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * 
 * @author Dane
 * 
 */
public class ImageGraphic {

	BufferedImage image;
	int[] pixels;
	int width, height;

	public ImageGraphic(int width, int height, int type)
	{
		this.width = width;
		this.height = height;
		this.image = new BufferedImage(width, height, type);
		this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}

	public BufferedImage getImage() {
		return this.image;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public Graphics createGraphics() {
		return this.image.createGraphics();
	}

	public Graphics getGraphics() {
		return this.image.getGraphics();
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

}

