package org.rev317.debug;

import java.awt.Graphics;
import java.awt.Point;

import org.parabot.core.paint.AbstractDebugger;
import org.parabot.core.paint.PaintDebugger;
import org.parabot.environment.input.Mouse;

public class DMouse extends AbstractDebugger {
	private boolean enabled = false;

	@Override
	public void paint(Graphics g) {
		final StringBuilder stringBuilder = new StringBuilder();
		final Mouse mouse = Mouse.getInstance();
		final Point point = mouse.getPoint();
		stringBuilder.append("Mouse Location: [").append(point.x).append(", ").append(point.y).append("]");
		PaintDebugger.getInstance().addLine(stringBuilder.toString());
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
