package org.rev317.api.wrappers.renderable;

import org.rev317.api.methods.Calculations;

/**
 * 
 * @author Clisprail
 * 
 */
public final class CharacterModel extends Model {
	private org.rev317.accessors.Character charAccessor = null;

	protected int[] x_base;
	protected int[] z_base;

	private int[] x_point;

	private int[] z_point;

	public CharacterModel(org.rev317.accessors.Model accessor,
			org.rev317.accessors.Character charAccessor) {
		super(accessor);
		this.charAccessor = charAccessor;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getX() {
		return this.charAccessor.getX();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getY() {
		return this.charAccessor.getY();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int[] getVertexX() {
		update();
		return x_point;
	}

	private final void update() {
		x_base = super.getVertexX();
		z_base = super.getVertexZ();
		x_point = new int[x_base.length];
		z_point = new int[z_base.length];
		final int theta = charAccessor.getTurnDirection() & 0x7ff;
		final int sin = Calculations.SINUS[theta];
		final int cos = Calculations.COSINUS[theta];
		for (int i = 0; i < x_base.length; ++i) {
			x_point[i] = x_base[i] * cos + z_base[i] * sin >> 16;
			z_point[i] = z_base[i] * cos - x_base[i] * sin >> 16;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int[] getVertexZ() {
		// getVertexX is called first, which updates the points, no need to call the update method in here
		return z_point;
	}

}
