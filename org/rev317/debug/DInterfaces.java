package org.rev317.debug;

import org.parabot.core.paint.AbstractDebugger;
import org.parabot.core.paint.PaintDebugger;
import org.rev317.api.methods.Interfaces;

import java.awt.*;

public class DInterfaces extends AbstractDebugger {
    private boolean enabled = false;

    @Override
    public void paint(Graphics g) {
        if (Interfaces.getOpenInterface() == null) {
            PaintDebugger.getInstance().addLine("Open interface ID: -1");
        } else {
            PaintDebugger.getInstance().addLine("Open interface ID: " + Interfaces.getOpenInterfaceId());
            Interfaces.getOpenInterface().draw(g);
            if (Interfaces.getOpenInterface().getChildren() != null) {
                String children = "";
                int amount = 0;
                for (int i : Interfaces.getOpenInterface().getChildrenIds()) {
                    if (Interfaces.get(i) != null) {
                        Interfaces.get(i).draw(g);
                        if (amount == 0) {
                            children += String.valueOf(i);
                            amount++;
                        } else {
                            children += ", " + String.valueOf(i);
                        }
                    }
                }
                PaintDebugger.getInstance().addLine("Open interface got children ID's: " + children);
            }
        }

        if (Interfaces.getChatboxInterface() == null) {
            PaintDebugger.getInstance().addLine("Open chatbox interface ID: -1");
        } else {
            PaintDebugger.getInstance().addLine("Open chatbox interface ID: " + Interfaces.getChatboxInterfaceId());
            if (Interfaces.getChatboxInterface().getChildren() != null) {
                String children = "";
                int amount = 0;
                for (int i : Interfaces.getChatboxInterface().getChildrenIds()) {
                    if (Interfaces.get(i) != null) {
                        Interfaces.get(i).draw(g);
                        if (amount == 0) {
                            children += String.valueOf(i);
                            amount++;
                        } else {
                            children += ", " + String.valueOf(i);
                        }
                    }
                }
                PaintDebugger.getInstance().addLine("Open chatbox interface got children ID's: " + children);
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
