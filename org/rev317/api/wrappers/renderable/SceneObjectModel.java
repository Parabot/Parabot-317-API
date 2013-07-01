package org.rev317.api.wrappers.renderable;

import org.rev317.accessors.SceneObjectTile;
import org.rev317.api.methods.Calculations;
import org.rev317.api.wrappers.scene.SceneObject;

public final class SceneObjectModel extends Model {
	private SceneObjectTile sceneObjectTileAccessor = null;
	
	protected int[] x_base;
	protected int[] z_base;

	private int[] x_point;

	private int[] z_point;
	
	private int type = 0;
	
	public SceneObjectModel(SceneObjectTile sceneObjectTileAccessor, org.rev317.accessors.Model accessor, int type) {
		super(accessor);
		this.sceneObjectTileAccessor = sceneObjectTileAccessor;
		this.type = type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getX() {
		return this.sceneObjectTileAccessor.getX();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getY() {
		return this.sceneObjectTileAccessor.getY();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int[] getVertexX() {
		if(this.type != SceneObject.TYPE_WALLDECORATION) {
			return super.getVertexX();
		}
		update();
		return x_point;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int[] getVertexZ() {
		if(this.type != SceneObject.TYPE_WALLDECORATION) {
			return super.getVertexZ();
		}
		return z_point;
	}

	private void update() {
		x_base = super.getVertexX();
		z_base = super.getVertexZ();
		x_point = new int[x_base.length];
		z_point = new int[z_base.length];
		int theta = sceneObjectTileAccessor.getOrientation() & 0x7ff;
		int sin = Calculations.SINUS[theta];
		int cos = Calculations.COSINUS[theta];
		for (int i = 0; i < x_base.length; ++i) {
			x_point[i] = x_base[i] * cos + z_base[i] * sin >> 16;
			z_point[i] = z_base[i] * cos - x_base[i] * sin >> 16;
		}
	}

}
