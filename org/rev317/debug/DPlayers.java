package org.rev317.debug;

import java.awt.Graphics;

import org.parabot.core.paint.AbstractDebugger;
import org.rev317.api.methods.Players;
import org.rev317.api.wrappers.interactive.Player;

/**
 * 
 * @author Everel
 *
 */
public class DPlayers extends AbstractDebugger {
	private boolean enabled;

	@Override
	public void paint(Graphics g) {
		final Player[] players = Players.getNearest();
		for(int i = 0; i < players.length; i++) {
			final Player player = players[i];
			player.draw(g);
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
