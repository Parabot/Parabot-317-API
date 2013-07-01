package org.rev317.painter;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.parabot.core.Context;
import org.parabot.environment.api.interfaces.Paintable;
import org.rev317.accessors.Client;

/**
 * 
 * @author Clisprail, Matt, Dane
 * 
 */
public abstract class Painter extends ImageProducer {

	public final ImageGraphic game = new ImageGraphic(771, 531,
			BufferedImage.TYPE_INT_RGB);
	public final ImageGraphic paint = new ImageGraphic(771, 531,
			BufferedImage.TYPE_INT_ARGB);
	public final BufferedImage screen = new BufferedImage(771, 531,
			BufferedImage.TYPE_INT_RGB);

	public final Object paintSync = new Object();
	private boolean cacheCheck = false;
	private final List<ImageCache> cache = new ArrayList<ImageCache>();

	private static final HashMap<Context, Painter> paintMap = new HashMap<Context, Painter>();
	
	public Painter() {
		paintMap.put(Context.resolve(), this);
	}

	public static boolean renderPaint(final Image image, final int y, final int x) {
		final Context context = Context.resolve();
		final Painter painter = paintMap.get(context);
		final Client client = (Client) context.getClient();
		if (client.isLoggedIn() && !painter.cacheCheck) {
			painter.cacheCheck = true;
		}
		if (!client.isLoggedIn() && painter.cacheCheck) {
			painter.cache.clear();
			painter.game.clear();
			painter.cacheCheck = false;
		}
		synchronized (painter.cache) {
			final ImageCache cached = new ImageCache();
			cached.img = image;
			cached.x = x;
			cached.y = y;
			if (!painter.cache.contains(cached)) {
				painter.cache.add(cached);
			}
			for (ImageCache c : painter.cache) {
				painter.game.getGraphics().drawImage(c.img, c.x, c.y, null);
			}
		}
		synchronized (painter.paintSync) {
			Graphics g = painter.paint.getGraphics();
			if (g == null) {
				g = painter.paint.createGraphics();
			}
			if (g != null) {
				painter.paint.clear();
				try {
					for(final Paintable p : context.getPaintables()) {
							p.paint(g);
					}
					context.getPaintDebugger().debug(g);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			painter.screen.getGraphics().drawImage(painter.game.getImage(), 0,
					0, null);
			painter.screen.getGraphics().drawImage(painter.paint.getImage(), 0,
					0, null);
			client.getRealGraphics().drawImage(painter.screen, 0, 0, null);
			return true;
		}
	}
	
	public static Painter getInstance() {
		return paintMap.get(Context.resolve());
	}

}

class ImageCache {
	Image img;
	int x;
	int y;

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ImageCache)) {
			return false;
		}
		ImageCache c = (ImageCache) o;
		return img.equals(c.img) && x == c.x && y == c.y;
	}
}
