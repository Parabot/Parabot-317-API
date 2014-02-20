package org.rev317.debug;

import java.awt.Graphics;

import org.parabot.core.paint.AbstractDebugger;
import org.parabot.core.paint.PaintDebugger;
import org.rev317.Loader;
import org.rev317.accessors.Client;

/**
 * 
 * @author Everel
 *
 */
public class DUsername extends AbstractDebugger {
	private boolean enabled;

	@Override
	public void paint(Graphics g) {
		Client client = Loader.getClient();
		PaintDebugger.getInstance().addLine("Username: " + client.getUsername());
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
