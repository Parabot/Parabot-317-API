package org.rev317.api.wrappers.hud;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.rev317.api.methods.Interfaces;
import org.rev317.api.methods.Menu;

/**
 * 
 * @author Matt, Clisprail
 * 
 */
public class Interface {

	private org.rev317.accessors.Interface accessor;

	public Interface(org.rev317.accessors.Interface accessor) {
		this.accessor = accessor;
	}

	/**
	 * Clicks this interface
	 */
	public final void click() {
		int x = getX() + getWidth();
		int y = getY() + getHeight();
		Mouse.getInstance().click(x, y, true);
	}

	/**
	 * Interacts with this interface
	 * @param region
	 * @param action
	 */
	public final void interact(Region region, String action) {
		Point p = getPoint(region);
		p = new Point(p.x + (getWidth() / 2), p.y + (getHeight() / 2));
		Time.sleep(100);
		Menu.interact(action, p);
	}

	/**
	 * Determines if this interface is visible
	 * @return <b>true</b> if interface is true, otherwise <b>false</b>.
	 */
	public final boolean isVisible() {
		return Interfaces.getChatboxInterfaceId() == getId()
				|| Interfaces.getOpenInterfaceId() == getId()
				|| getParentId() != getId() && getParent().isVisible();
	}

	/**
	 * Gets this interface id
	 * @return interface id
	 */
	public final int getId() {
		if (accessor == null)
			return -1;
		return accessor.getId();
	}

	/**
	 * Gets this interface's parent
	 * @return parent interface
	 */
	public final Interface getParent() {
		if (accessor == null)
			return null;
		if (getId() == getParentId())
			return this;
		return Interfaces.get(getParentId());
	}

	/**
	 * Gets the string action array from this interface
	 * @return array actions
	 */
	public final String[] getActions() {
		if (accessor == null)
			return null;
		return accessor.getActions();
	}

	/**
	 * Gets the current selected action
	 * @return selected action
	 */
	public final String getSelectedAction() {
		if (accessor == null)
			return null;
		return accessor.getSelectedActionName();
	}

	/**
	 * Gets tooltip text
	 * @return tooltip text
	 */
	public final String getTooltip() {
		if (accessor == null)
			return null;
		return accessor.getTooltip();
	}

	/**
	 * Gets width
	 * @return width
	 */
	public final int getWidth() {
		if (accessor == null)
			return 0;
		return accessor.getWidth();
	}

	/**
	 * Gets height
	 * @return height
	 */
	public final int getHeight() {
		if (accessor == null)
			return 0;
		return accessor.getHeight();
	}

	/**
	 * Gets point based on region and its offsets
	 * @param region
	 * @return point
	 */
	public final Point getPoint(Region region) {
		if (accessor == null)
			return null;
		return new Point(region.getX() + getX(), region.getY() + getY());
	}

	/**
	 * Gets x location
	 * @return x
	 */
	private final int getX() {
		if (accessor == null)
			return -1;
		int[] ids = getParent().getChildrenIds();
		if (ids != null) {
			for (int i = 0; i < ids.length; i++) {
				if (ids[i] == getId()) {
					return getParent().getChildX()[i];
				}
			}
		}
		return 0;
	}

	/**
	 * Gets child X array
	 * @return child X array
	 */
	public final int[] getChildX() {
		return accessor.getChildX();
	}

	/**
	 * Gets y location
	 * @return y
	 */
	private final int getY() {
		if (accessor == null)
			return -1;
		int[] ids = getParent().getChildrenIds();
		if (ids != null) {
			for (int i = 0; i < ids.length; i++) {
				if (ids[i] == getId()) {
					return getParent().getChildY()[i];
				}
			}
		}
		return 0;
	}

	/**
	 * Gets child Y array
	 * @return child Y array
	 */
	public final int[] getChildY() {
		return accessor.getChildY();
	}

	/**
	 * Gets children id array
	 * @return children ids
	 */
	public final int[] getChildrenIds() {
		return accessor.getChildren();
	}

	/**
	 * Gets parent id
	 * @return parent id
	 */
	public final int getParentId() {
		if (accessor == null)
			return -1;
		return Interfaces.getParentId(getId());
	}

	/**
	 * Gets interface children
	 * @return interface children
	 */
	public final Interface[] getChildren() {
		if (accessor == null)
			return null;
		int[] ids = accessor.getChildren();
		if (ids == null)
			return null;
		Interface[] ret = new Interface[ids.length];
		for (int i = 0; i < ids.length; i++)
			ret[i] = Interfaces.get(ids[i]);
		return ret;
	}

	/**
	 * Gets this interface text
	 * @return text
	 */
	public final String getText() {
		if (accessor == null)
			return null;
		return accessor.getMessage();
	}

	/**
	 * Gets the bounds of this interface
	 * @param region
	 * @return interface bounds
	 */
	public final Rectangle getArea(Region region) {
		final Point p = getPoint(region);
		return new Rectangle(p.x, p.y, getWidth(), getHeight());
	}

	/**
	 * Draws this interface
	 * @param g
	 */
	public final void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawRect(getX(), getY(), getWidth(), getHeight());
	}

	/**
	 * Gets child interface with given id
	 * @param child
	 * @return child interface
	 */
	public final Interface getChild(int child) {
		return getChildren()[child];
	}

	/**
	 * Gets the items in this interface
	 * @return items
	 */
	public final int[] getItems() {
		int[] items = new int[accessor.getItems().length];
		for (int i = 0; i < items.length; i++) {
			items[i] = accessor.getItems()[i] - 1;
		}
		return items;
	}

	/**
	 * Gets the item stacksizes
	 * @return item stacksizes
	 */
	public final int[] getStackSizes() {
		return accessor.getStackSize();
	}

	/**
	 * Determines if this interface contains or is an inventory
	 * @return <b>true</b> if this interface contains or is an inventory, otherwhise <b>false</b>.
	 */
	public final boolean hasInventory() {
		return accessor.isInventory();
	}

}
