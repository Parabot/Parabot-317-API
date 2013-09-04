package org.rev317.api.wrappers.defs;

import java.util.HashMap;

import org.rev317.Loader;

/**
 * 
 * @author Everel
 *
 */
public final class ItemDef {
	private org.rev317.accessors.ItemDef accessor = null;
	private static HashMap<Integer, ItemDef> cache = new HashMap<Integer, ItemDef>();

	public ItemDef(org.rev317.accessors.ItemDef accessor) {
		this.accessor = accessor;
	}
	
	public static ItemDef get(int id) {
		if(!cache.containsKey(id)) {
			org.rev317.accessors.ItemDef def = Loader.getClient().getItem(id);
			if(def == null) {
				return null;
			}
			cache.put(id, new ItemDef(def));
		}
		return cache.get(id);
	}
	
	/**
	 * Gets this item's name
	 * @return item's name
	 */
	public final String getName() {
		return this.accessor.getName();
	}
	

}
