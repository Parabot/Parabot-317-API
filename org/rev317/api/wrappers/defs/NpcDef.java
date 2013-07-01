package org.rev317.api.wrappers.defs;

/**
 * 
 * @author Clisprail
 *
 */
public final class NpcDef {
	private org.rev317.accessors.NpcDef accessor = null;
	
	public NpcDef(final org.rev317.accessors.NpcDef accessor) {
		this.accessor = accessor;
	}
	
	/**
	 * Gets the Npc name
	 * @return Npc name
	 */
	public final String getName() {
		return this.accessor.getName();
	}
	
	/**
	 * Gets the Npc id
	 * @return Npc id
	 */
	public final int getId() {
		return this.accessor.getId();
	}
	
	/**
	 * Gets this Npc level
	 * @return Npc level
	 */
	public final int getLevel() {
		return this.accessor.getLevel();
	}
	
	/**
	 * Gets the Npc interactable actions
	 * @return Npc interactable actions
	 */
	public final String[] getActions() {
		return this.accessor.getActions();
	}
	
	

}
