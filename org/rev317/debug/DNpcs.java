package org.rev317.debug;

import java.awt.Graphics;
import org.parabot.core.paint.AbstractDebugger;
import org.rev317.api.methods.Npcs;
import org.rev317.api.wrappers.interactive.Npc;

/**
 * 
 * @author Clisprail
 *
 */
public class DNpcs extends AbstractDebugger {
	private boolean enabled = false;

	@Override
	public void paint(Graphics g) {
		final Npc[] npcs = Npcs.getNearest();
		for(int i = 0; i < npcs.length; i++) {
			final Npc npc = npcs[i];
			npc.draw(g);
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
