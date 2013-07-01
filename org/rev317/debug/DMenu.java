package org.rev317.debug;

import java.awt.Graphics;
import java.awt.Rectangle;

import org.parabot.core.Context;
import org.parabot.core.paint.AbstractDebugger;
import org.parabot.core.paint.PaintDebugger;
import org.rev317.api.methods.Menu;

/**
 * 
 * @author Clisprail
 *
 */
public class DMenu extends AbstractDebugger {
	private boolean enabled = false;

	@Override
	public void paint(Graphics g) {
		final Context context = Context.resolve();
		final PaintDebugger debugger = context.getPaintDebugger();
		for(final String menuAction : Menu.getActions()) {
			debugger.addLine(menuAction);
		}
		if(Menu.isOpen()) {
			final Rectangle rect = Menu.getBounds();
			g.drawRect(rect.x, rect.y, rect.width, rect.height);
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
