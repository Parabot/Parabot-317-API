package org.rev317.api.interfaces;

/**
 * 
 * Can be interacted with
 * 
 * @author Everel
 *
 */
public interface Interactable {
	
	/**
	 * 
	 * @param action
	 * @return <b>true</b> if it was successfully interacted with, otherwise <b>false</b>.
	 */
	public boolean interact(final String action);

}
