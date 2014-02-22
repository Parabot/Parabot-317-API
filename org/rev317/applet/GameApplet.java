package org.rev317.applet;

import org.rev317.input.InputHandler;
import org.rev317.script.ScriptEngine;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Everel, Matt
 *
 */
public abstract class GameApplet extends Applet implements Runnable {
    private static final long serialVersionUID = 1L;
    private ScriptEngine scriptEngine;
    
    public GameApplet() {
    	this.scriptEngine = ScriptEngine.getInstance();
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