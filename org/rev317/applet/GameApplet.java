package org.rev317.applet;

import org.rev317.input.InputHandler;
import org.rev317.painter.Painter;
import org.rev317.script.ScriptEngine;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author Everel, Matt
 *
 */
public abstract class GameApplet extends Applet implements Runnable{
    private static final long serialVersionUID = 1L;
    private ScriptEngine scriptEngine = ScriptEngine.getInstance();
    private Painter painter = Painter.getInstance();

    private boolean clientReady = false;

    @Override
    public Graphics getGraphics() {
        if(!clientReady)
            return super.getGraphics();
        if(painter == null) {
            painter = Painter.getInstance();
            return new BufferedImage(771, 531,
                    BufferedImage.TYPE_INT_ARGB).createGraphics();
        }
        synchronized (painter.screen) {
            return painter.game.createGraphics();
        }
    }

    @Override
    public void run(){
        clientReady = true;
        _run();
    }

    //Thanks to matt for the cache blackscreen fix

    public abstract void _run(); // implement this shit in the hooks (you'll also need to remove the "Runnable" interface from the client's RSApplet)

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