package org.rev317.debug;

import java.awt.Graphics;

import org.parabot.core.paint.AbstractDebugger;
import org.rev317.api.methods.SceneObjects;
import org.rev317.api.wrappers.scene.SceneObject;

public class DObjects extends AbstractDebugger {
	private boolean enabled = false;

	@Override
	public void paint(Graphics g) {
		for(final SceneObject sceneObject : SceneObjects.getSceneObjects()) {
			sceneObject.draw(g);
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
