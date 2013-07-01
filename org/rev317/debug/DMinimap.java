package org.rev317.debug;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import org.parabot.core.paint.AbstractDebugger;
import org.rev317.api.methods.Game;
import org.rev317.api.methods.Players;
import org.rev317.api.methods.Walking;
import org.rev317.api.wrappers.scene.Tile;

/**
 * 
 * @author Clisprail
 * For bot developing testing only...
 */
public class DMinimap extends AbstractDebugger {
	private boolean enabled = false;

	@Override
	public void paint(Graphics g) {
		if(!Game.isLoggedIn()) {
			return;
		}
		final Tile curLoc = Players.getLocal().getLocation();
		final Tile tile = new Tile(curLoc.getX() + 1, curLoc.getY() + 0);
		final Point point = tile.toMinimap();
		g.setColor(Color.red);
		g.fillRect(point.x - 2, point.y - 2, 4, 4);
		System.out.println(Walking.isDestinationSet());
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
