package org.rev317.debug;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import org.parabot.core.Context;
import org.parabot.core.paint.AbstractDebugger;
import org.parabot.core.paint.PaintDebugger;
import org.rev317.accessors.Client;
import org.rev317.api.methods.Calculations;
import org.rev317.api.methods.Game;
import org.rev317.api.methods.Players;
import org.rev317.api.wrappers.interactive.Player;
import org.rev317.api.wrappers.scene.Tile;
import org.rev317.loader.Loader;

/**
 * 
 * @author Clisprail, Matt
 * 
 */
public class DLocations extends AbstractDebugger {
	private boolean enabled = false;

	@Override
	public void paint(Graphics g) {
		if (!Game.isLoggedIn()) {
			return;
		}
		final Client client = Loader.getClient();
		final Player p = Players.getLocal();
		final Tile t = p.getLocation();
		final Point point = Calculations.tileToMinimap(t);
		final PaintDebugger debugger = Context.resolve().getPaintDebugger();
		debugger.addLine("MapBase: (" + client.getBaseX() + ", "
				+ client.getBaseY() + ")");
		debugger.addLine("Offset: (" + t.getRegionX() + ", " + t.getRegionX()
				+ ")");
		debugger.addLine("Location: (" + t.getX() + ", " + t.getY() + ")");
		debugger.addLine("Collision Flag: 0x"
				+ String.format("%X", Game.getCollisionMap().getFlags()[t
						.getRegionX()][t.getRegionY()]));
		debugger.addLine("Moving: " + p.isWalking());

		g.setColor(Color.RED);
		g.fillRect(point.x - 2, point.y - 2, 4, 4);

		g.setColor(Color.BLUE);
		g.fillRect(point.x, point.y, 1, 1);
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
