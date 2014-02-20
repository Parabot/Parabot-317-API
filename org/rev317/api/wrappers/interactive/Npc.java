package org.rev317.api.wrappers.interactive;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import org.rev317.api.wrappers.defs.NpcDef;
import org.rev317.api.wrappers.renderable.Model;

/**
 * 
 * @author Everel
 *
 */
public final class Npc extends Character {
	private org.rev317.accessors.Npc accessor;

	public Npc(org.rev317.accessors.Npc accessor) {
		super(accessor);
		this.accessor = accessor;
	}
	
	/**
	 * Gets the definition of this npc
	 * @return npc definitions
	 */
	public final NpcDef getDef() {
		return new NpcDef(this.accessor.getDef());
	}
	
	/**
	 * Gets this npc name
	 * @return npc name
	 */
	@Override
	public final String getName() {
		return getDef() == null ? null : getDef().getName();
	}
	
	/**
	 * Draws this npc
	 */
	@Override
	public final void draw(Graphics g, final boolean drawModel) {
		if(!super.isOnScreen()) {
			return;
		}
		final NpcDef def = getDef();
		if(def == null) {
			return;
		}
		if(drawModel) {
			final Model m = super.getModel();
			if(m != null) {
				m.drawWireFrame(g);
			}
		}
		final Point p = getCenterPointOnScreen();
		g.setColor(Color.red);
		g.fillRect(p.x - 2, p.y - 2, 4, 4);
		g.setColor(Color.yellow);
		final StringBuilder builder = new StringBuilder();
		builder.append(def.getName()).append(" [").append(def.getId()).append("]");
		g.drawString(builder.toString(), p.x + 5, p.y - 2);
	}

}
