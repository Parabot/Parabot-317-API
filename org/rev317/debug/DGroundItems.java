package org.rev317.debug;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import org.parabot.core.paint.AbstractDebugger;
import org.rev317.api.methods.GroundItems;
import org.rev317.api.wrappers.renderable.Model;
import org.rev317.api.wrappers.scene.GroundItem;
/**
 * 
 * @author Clisprail
 *
 */
public class DGroundItems extends AbstractDebugger {
	private boolean enabled = false;

	@Override
	public void paint(Graphics g) {
		GroundItem[] groundItems = GroundItems.getGroundItems();
		for(final GroundItem groundItem : groundItems) {
			if(groundItem.isOnScreen()) {
				final Point p = groundItem.getCenterPointOnScreen();
				g.setColor(Color.white);
				final Model m = groundItem.getModel();
				if(m != null) {
					m.drawWireFrame(g);
				}
				g.setColor(Color.red);
				g.drawString(groundItem.getId() + "", p.x, p.y );
			}
		}
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void toggle() {
		enabled = !enabled;
		
	}

}
