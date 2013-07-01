package org.rev317.api.wrappers.interactive;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import org.rev317.accessors.Client;
import org.rev317.api.interfaces.Locatable;
import org.rev317.api.methods.Calculations;
import org.rev317.api.methods.Game;
import org.rev317.api.wrappers.renderable.CharacterModel;
import org.rev317.api.wrappers.renderable.Model;
import org.rev317.api.wrappers.scene.Tile;
import org.rev317.loader.Loader;

/**
 * 
 * @author Clisprail
 *
 */
public class Character implements Locatable {
	private org.rev317.accessors.Character accessor = null;
	
	public Character(org.rev317.accessors.Character accessor) {
		this.accessor = accessor;
	}
	
	/**
	 * Gets the world location of this character
	 * @return location
	 */
	public final Tile getLocation() {
		final Client client = Loader.getClient();
		return new Tile(client.getBaseX() + getLocalRegionX(), client.getBaseY() + getLocalRegionY(), client.getPlane());
	}
	
	/**
	 * Gets the local region X location
	 * @return x
	 */
	public final int getLocalRegionX() {
		return this.accessor.getX() >> 7;
	}
	
	/**
	 * Gets the local region Y location
	 * @return y
	 */
	public final int getLocalRegionY() {
		return this.accessor.getY() >> 7;
	}
	
	/**
	 * Calculates center point on screen of this character
	 * @return point on screen
	 */
	public final Point getCenterPointOnScreen() {
		return Calculations.tileToScreen(getLocalRegionX(), getLocalRegionY(), 0.5D, 0.5D, 100);
	}
	
	/**
	 * Calculates distance from local player to this character
	 * @return distance to character
	 */
	public final int distanceTo() {
		return (int) Calculations.distanceTo(getLocation());
	}
	
	/**
	 * Gets the animation id
	 * @return animation id
	 */
	public final int getAnimation() {
		return this.accessor.getAnimation();
	}
	
	/**
	 * Gets current health
	 * @return health
	 */
	public final int getHealth() {
		return this.accessor.getHealth();
	}
	
	/**
	 * Gets maximum health of this character
	 * @return max health
	 */
	public final int getMaxHealth() {
		return this.accessor.getMaxHealth();
	}
	
	/**
	 * Fetches loop cycle status
	 * @return loop cycle status
	 */
	public final int getLoopCycleStatus() {
		return this.accessor.getLoopCycleStatus();
	}
	
	/**
	 * Returns the id of the character which is interacted by this character
	 * @return id
	 */
	public final int getInteractingCharacter() {
		return this.accessor.getInteractingId();
	}
	
	/**
	 * Gets the turn angle
	 * @return turn angle
	 */
	public final int getTurnDirection() {
		return this.accessor.getTurnDirection();
	}

	/**
	 * Gets height of this character
	 * @return height
	 */
	public final int getHeight() {
		return this.accessor.getHeight();
	}
	
	/**
	 * Region X queue array
	 * @return queue x
	 */
	public final int[] getQueueX() {
		return this.accessor.getQueueX();
	}
	
	/**
	 * Region Y queue array
	 * @return queue Y
	 */
	public final int[] getQueueY() {
		return this.accessor.getQueueY();
	}
	
	/**
	 * Determines whether the player is walking
	 * @return <b>true</b> if player is walking
	 */
	public final boolean isWalking() {
		return this.accessor.isWalking() != 0;
	}
	
	/**
	 * Gets text above this characters head
	 * @return text above head
	 */
	public final String getDisplayedText() {
		return this.accessor.getDisplayedText();
	}
	
	/**
	 * Gets this character accessor
	 * @return accessor of this character
	 */
	public org.rev317.accessors.Character getAccessor() {
		return this.accessor;
	}
	
	/**
	 * Determines whether this character is on screen
	 * @return <b>true</b> if this character is on screen, otherwise false
	 */
	public boolean isOnScreen() {
		return Calculations.isOnScreen(this.getCenterPointOnScreen());
	}
	
	public Model getModel() {
		org.rev317.accessors.Model model = this.accessor.getModel();
		if(model == null) {
			return null;
		}
		return new CharacterModel(this.accessor.getModel(), this.accessor);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return null;
	}
	
	/**
	 * Determines if entity is in combat
	 * 
	 * @return <b>true</b> if entity is in combat
	 */
	public boolean isInCombat() {
		return accessor.getLoopCycleStatus() > Game.getLoopCycle();
	}
	
	/**
	 * Draws this character
	 * @param graphics
	 */
	public void draw(final Graphics g) {
		draw(g, false);
	}
	
	/**
	 * Draws this character
	 * @param graphics
	 * @param drawModel
	 */
	public void draw(final Graphics g, final boolean drawModel) {
		if(!isOnScreen()) {
			return;
		}
		if(drawModel) {
			final Model m = getModel();
			if(m != null) {
				m.drawWireFrame(g);
			}
		}
		final Point p = getCenterPointOnScreen();
		g.setColor(Color.red);
		g.fillRect(p.x - 2, p.y - 2, 4, 4);
		g.setColor(Color.white);
		g.drawString(getName(), p.x + 5, p.y - 2);
	}
}
