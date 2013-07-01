package org.rev317.debug;

import java.awt.Graphics;

import org.parabot.core.Context;
import org.parabot.core.paint.AbstractDebugger;
import org.rev317.api.methods.Game;
import org.rev317.api.wrappers.hud.Tab;

public class DTab extends AbstractDebugger {
	private boolean enabled = false;

	@Override
	public void paint(Graphics g) {
		if(!Game.isLoggedIn()) {
			return;
		}
		Context.resolve().getPaintDebugger().addLine(Tab.getOpened().getName());
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
