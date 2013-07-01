package org.rev317.debug;

import java.awt.Graphics;

import org.parabot.core.paint.AbstractDebugger;
import org.rev317.api.methods.Inventory;
import org.rev317.api.wrappers.hud.Item;
import org.rev317.api.wrappers.hud.Tab;

public class DInventory extends AbstractDebugger {
	private boolean enabled = false;

	@Override
	public void paint(Graphics g) {
		if(Tab.getOpened() != Tab.INVENTORY) {
			return;
		}
		for(final Item item : Inventory.getItems()) {
			item.draw(g);
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
