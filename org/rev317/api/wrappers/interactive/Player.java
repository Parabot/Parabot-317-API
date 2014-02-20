package org.rev317.api.wrappers.interactive;

/**
 * 
 * @author Everel
 *
 */
public final class Player extends Character {
	private org.rev317.accessors.Player accessor;
	
	public Player(org.rev317.accessors.Player accessor) {
		super(accessor);
		this.accessor = accessor;
	}
	
	/**
	 * Gets player his name
	 * @return player his name
	 */
	@Override
	public final String getName() {
		return this.accessor.getName();
	}

    public final int[] getEquipment() {
        return this.accessor.getEquipment();
    }

}

