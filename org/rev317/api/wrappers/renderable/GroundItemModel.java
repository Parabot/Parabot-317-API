package org.rev317.api.wrappers.renderable;

import org.rev317.api.interfaces.Locatable;

/**
 * 
 * @author Clisprail
 *
 */
public final class GroundItemModel extends Model {
	private Locatable locatable = null;

	public GroundItemModel(final Locatable locatable, org.rev317.accessors.Model accessor) {
		super(accessor);
		this.locatable = locatable;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getX() {
		return (int) ((locatable.getLocalRegionX() + 0.5D) * 128.0D);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getY() {
		return (int) ((locatable.getLocalRegionY() + 0.5D) * 128.0D);
	}

}
