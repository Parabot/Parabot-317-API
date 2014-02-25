package org.rev317.utils;

import javafx.embed.swing.JFXPanel;
import org.parabot.core.Context;
import org.parabot.core.paint.PaintDebugger;
import org.rev317.debug.*;
import org.rev317.input.InputHandler;
import org.rev317.randoms.ui.LoginUI;
import org.rev317.randoms.ui.RandomUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @author Everel
 *
 */
public final class BotMenu {
	private JMenuBar bar;
	private final PaintDebugger paintDebugger;
	
	private final ActionListener menuDebugItemListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() instanceof JMenuItem) {
				final JMenuItem item = (JMenuItem) e.getSource();
				toggle(item.getText());
			}
			
		}
		
	};
	
	public BotMenu(final JMenuBar bar) {
		paintDebugger = Context.getInstance().getPaintDebugger();
		this.bar = bar;
	}
	
	public final void addItems() {
		JMenu debug = new JMenu("Debug");
	
		debug.add(newDebugItem("Username"));
		paintDebugger.addDebugger("Username", new DUsername());
		
		debug.add(newDebugItem("Players"));
		paintDebugger.addDebugger("Players", new DPlayers());
		
		debug.add(newDebugItem("Npcs"));
		paintDebugger.addDebugger("Npcs", new DNpcs());
		
		debug.add(newDebugItem("SceneObjects"));
		paintDebugger.addDebugger("SceneObjects", new DObjects());
		
		debug.add(newDebugItem("GroundItems"));
		paintDebugger.addDebugger("GroundItems", new DGroundItems());
		
		debug.add(newDebugItem("Menu"));
		paintDebugger.addDebugger("Menu", new DMenu());
		
		debug.add(newDebugItem("Mouse"));
		paintDebugger.addDebugger("Mouse", new DMouse());
		
		debug.add(newDebugItem("Inventory"));
		paintDebugger.addDebugger("Inventory", new DInventory());
		
		debug.add(newDebugItem("Tab"));
		paintDebugger.addDebugger("Tab", new DTab());
		
		debug.add(newDebugItem("Bank"));
		paintDebugger.addDebugger("Bank", new DBank());
		
		debug.add(newDebugItem("Locations"));
		paintDebugger.addDebugger("Locations", new DLocations());
		
		debug.add(newDebugItem("Animation"));
		paintDebugger.addDebugger("Animation", new DAnimation());

        debug.add(newDebugItem("Interfaces"));
        paintDebugger.addDebugger("Interfaces", new DInterfaces());

        debug.add(newDebugItem("Equipment"));
        paintDebugger.addDebugger("Equipment", new DEquipment());
		
		
		JMenu input = new JMenu("Input");
		
		final JMenuItem mouse = new JCheckBoxMenuItem("Mouse");
		mouse.setSelected(true);
		mouse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				InputHandler.mouseInput = mouse.isSelected();
			}
			
		});
		input.add(mouse);
		
		final JMenuItem keyboard = new JCheckBoxMenuItem("Keyboard");
		keyboard.setSelected(true);
		keyboard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				InputHandler.keyInput = keyboard.isSelected();
			}
			
		});
		input.add(keyboard);
		
		/*debug.add(newDebugItem("Minimap"));
		paintDebugger.addDebugger("Minimap", new DMinimap());*/

        JMenu other = new JMenu("Other");
        final JMenuItem randoms = new JMenuItem("Randoms");
        final JMenuItem autoLogin = new JMenuItem("Auto login");

        randoms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new JFrame();
                        JFXPanel jfxp = new JFXPanel();
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.getContentPane().add(jfxp);
                        frame.setSize(600, 400);
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(false);

                        RandomUI.main();
                    }
                });
            }
        });

        autoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frameLogin = new JFrame();
                        JFXPanel jfxpLogin = new JFXPanel();
                        frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frameLogin.getContentPane().add(jfxpLogin);
                        frameLogin.setSize(600, 400);
                        frameLogin.setLocationRelativeTo(null);
                        frameLogin.setVisible(false);

                        LoginUI.main();
                    }
                });
            }
        });
        other.add(randoms);
        other.add(autoLogin);

        bar.add(debug);
        bar.add(input);
        bar.add(other);
	}
	
	public final JMenuItem newDebugItem(final String name) {
		final JMenuItem item = new JCheckBoxMenuItem(name);
		item.addActionListener(menuDebugItemListener);
		return item;
	}
	
	public final void toggle(final String name) {
		paintDebugger.toggle(name);
	}

}
