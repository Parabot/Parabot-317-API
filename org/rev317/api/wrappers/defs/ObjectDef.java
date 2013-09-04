package org.rev317.api.wrappers.defs;

import java.util.HashMap;

import org.rev317.Loader;

/**
 * 
 * @author Everel
 *
 */
public final class ObjectDef {
	private org.rev317.accessors.ObjectDef accessor = null;
	private static HashMap<Integer, ObjectDef> cache = new HashMap<Integer, ObjectDef>();

	public ObjectDef(org.rev317.accessors.ObjectDef accessor) {
		this.accessor = accessor;
	}
	
	public static ObjectDef get(int id) {
		if(!cache.containsKey(id)) {
			org.rev317.accessors.ObjectDef def = Loader.getClient().getObjectDef(id);
			if(def == null) {
				return null;
			}
			cache.put(id, new ObjectDef(def));
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
