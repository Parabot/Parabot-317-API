package org.rev317.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import org.parabot.core.Context;
import org.parabot.core.paint.PaintDebugger;
import org.rev317.debug.DAnimation;
import org.rev317.debug.DBank;
import org.rev317.debug.DGroundItems;
import org.rev317.debug.DInventory;
import org.rev317.debug.DLocations;
import org.rev317.debug.DMenu;
import org.rev317.debug.DMouse;
import org.rev317.debug.DNpcs;
import org.rev317.debug.DObjects;
import org.rev317.debug.DPlayers;
import org.rev317.debug.DTab;
import org.rev317.debug.DUsername;
import org.rev317.input.InputHandler;

/**
 * 
 * @author Clisprail
 *
 */
public final class BotMenu {
	private JMenuBar bar = null;
	private final PaintDebugger paintDebugger = Context.resolve().getPaintDebugger();
	
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
		paintDebugger.addDebugger("GameObjects", new DObjects());
		
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
		
		bar.add(debug);
		bar.add(input);
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
