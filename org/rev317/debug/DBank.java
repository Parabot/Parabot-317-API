package org.rev317.debug;

import java.awt.Graphics;

import org.parabot.core.paint.AbstractDebugger;
import org.rev317.api.methods.Bank;
import org.rev317.api.wrappers.hud.Item;

public class DBank extends AbstractDebugger {
	private boolean enabled = false;

	@Override
	public void paint(Graphics g) {
		if(Bank.isOpen()) {
			for(final Item item : Bank.getBankItems()) {
				item.draw(g);
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
