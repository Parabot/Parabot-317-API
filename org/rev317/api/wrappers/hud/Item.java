package org.rev317.api.wrappers.hud;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import org.rev317.api.methods.Menu;

/**
 * 
 * @author Clisprail
 *
 */
public final class Item {
	public static final int TYPE_BANK = 1;
	public static final int TYPE_INVENTORY = 2;
	private int id = -1;
	private int stackSize = 1;
	private int slot = -1;
	private int type = 0;
	
	public Item(final int id, final int stackSize, final int slot, final int type) {
		this.id = id;
		this.stackSize = stackSize;
		this.slot = slot;
		if(type < 1 || type > 2) {
			throw new IllegalArgumentException("Invalid item type.");
		}
		this.type = type;
	}
	
	/**
	 * Gets the id of this item
	 * @return id
	 */
	public final int getId() {
		return id;
	}
	
	/**
	 * Gets the item stack size
	 * @return item stack size
	 */
	public final int getStackSize() {
		return stackSize;
	}
	
	/**
	 * The item type location
	 * @return location type
	 */
	public final int getType() {
		return type;
	}
	
	/**
	 * This item's slot
	 * @return item's slot
	 */
	public final int getSlotIndex() {
		return slot;
	}
	
	/**
	 * Gets center point of this item
	 * @return center point of this item
	 */
	public final Point getScreenLocation() {
		switch (this.type) {
		case TYPE_INVENTORY:
			int col = slot % 4;
			int row = slot / 4;
			int x = 580 + col * 42;
			int y = 228 + row * 36;
			return new Point(x, y);
		case TYPE_BANK:
			final Point first_slot_point = new Point(57, 92);
			final Point slot_distance = new Point(44, 40);
			final int length = 10;
			final int column = (slot % length);
			final int therow = (slot / length);
			final int pointX = first_slot_point.x + (column * slot_distance.x);
			final int pointY = first_slot_point.y + (therow * slot_distance.y);
			return new Point(pointX, pointY);
		}
		return new Point(-1, -1);
	}
	
	/**
	 * Draws this item
	 * 
	 * @param graphics
	 */
	public final void draw(final Graphics g) {
		final Point p = getScreenLocation();
		g.setColor(Color.black);
		g.drawString("" + this.id, p.x - 7, p.y + 5);
		g.setColor(Color.green);
		g.drawString("" + this.id, p.x - 8, p.y + 4);
	}

	/**
	 * Interacts with this item
	 * @param action
	 */
	public final void interact(String action) {
		Menu.interact(action, getScreenLocation());
	}

}
