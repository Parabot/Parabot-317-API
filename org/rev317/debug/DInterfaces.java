package org.rev317.debug;

import org.parabot.core.paint.AbstractDebugger;
import org.parabot.core.paint.PaintDebugger;
import org.rev317.api.methods.Interfaces;

import java.awt.*;

public class DInterfaces extends AbstractDebugger {
    private boolean enabled = false;

    @Override
    public void paint(Graphics g) {
        if (Interfaces.getOpenInterface() == null){
            PaintDebugger.getInstance().addLine("Open interface ID: -1");
        }else{
            PaintDebugger.getInstance().addLine("Open interface ID: " + Interfaces.getOpenInterfaceId());
        }

        if (Interfaces.getChatboxInterface() == null){
            PaintDebugger.getInstance().addLine("Open chatbox interface ID: -1");
        }else{
            PaintDebugger.getInstance().addLine("Open chatbox interface ID: " + Interfaces.getChatboxInterfaceId());
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
