package org.rev317.applet;

import java.applet.Applet;
import java.awt.AWTEvent;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import org.rev317.input.InputHandler;
import org.rev317.painter.Painter;
import org.rev317.script.ScriptEngine;

public abstract class GameApplet extends Applet {
	private static final long serialVersionUID = 1L;
	private ScriptEngine scriptEngine = ScriptEngine.getInstance();
	private Painter painter = Painter.getInstance();
	
	@Override
	public Graphics getGraphics() {
		if(painter == null) {
			painter = Painter.getInstance();
			return new BufferedImage(771, 531,
			BufferedImage.TYPE_INT_ARGB).createGraphics();
		}
		synchronized (painter.screen) {
			return painter.game.createGraphics();
		}
	}

	public Graphics getRealGraphics() {
		return super.getGraphics();
	}
	
	@Override
	public void processEvent(AWTEvent e) {
		scriptEngine.dispatch(e);
		if(InputHandler.mouseInput) {
			if(e instanceof MouseEvent) {
				super.processEvent(e);
			}
		}
		if(InputHandler.keyInput) {
			if(e instanceof KeyEvent) {
				super.processEvent(e);
			}
		}
	}

}
