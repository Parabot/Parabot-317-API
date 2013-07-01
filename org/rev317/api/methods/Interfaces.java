package org.rev317.api.methods;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.rev317.accessors.Client;
import org.rev317.api.wrappers.hud.Interface;
import org.rev317.loader.Loader;

/**
 * 
 * @author Clisprail
 * @author Dane
 * @author Matt
 *
 */
public final class Interfaces {
	
	public static HashMap<Integer, Integer> parents = new HashMap<Integer, Integer>();

	/**
	 * Gets the current chatbox interface.
	 * 
	 * @return chatbox interface
	 */
	public static final Interface getChatboxInterface() {
		int index = Loader.getClient().getChatboxInterfaceId();
		return index == -1 ? null : getInterface(index);
	}

	/**
	 * Gets the current main interface.
	 * 
	 * @return open interface
	 */
	public static final Interface getOpenInterface() {
		int index = Loader.getClient().getOpenInterfaceId();
		return index == -1 ? null : getInterface(index);
	}

	/**
	 * Gets the opened interface index.
	 * 
	 * @return open interface id
	 */
	public static final int getOpenInterfaceId() {
		return Loader.getClient().getOpenInterfaceId();
	}

	/**
	 * Gets the chatbox interface index.
	 * 
	 * @return open chatbox interface id
	 */
	public static final int getChatboxInterfaceId() {
		return Loader.getClient().getChatboxInterfaceId();
	}



	/**
	 * Gets interfaces that have children.
	 * 
	 * @return parent interfaces
	 */
	public static final Interface[] getParentInterfaces() {
		final Client client = Loader.getClient();
		Map<Integer, Interface> topInterfaces = new HashMap<Integer, Interface>();
		if (client.getInterfaceCache() != null)
			for (org.rev317.accessors.Interface iface : client.getInterfaceCache()) {
				if (iface == null)
					continue;
				if (topInterfaces.get(iface.getId()) == null && iface.getParent() == iface.getId()) {
					topInterfaces.put(iface.getId(), new Interface(iface));
				}
			}
		topInterfaces = new TreeMap<Integer, Interface>(topInterfaces);
		return topInterfaces.values().toArray(new Interface[topInterfaces.size()]);
	}

	/**
	 * gets the provided interface id's parent.
	 * 
	 * @param id
	 * @return parent interface
	 */
	public static final Interface getParentInterface(int id) {
		for (Interface iface : getParentInterfaces()) {
			if (iface == null)
				continue;
			if (iface.getId() == id)
				return iface;
		}
		return null;
	}

	/**
	 * Gets the child interface of the provided parent interface id.
	 * 
	 * @param parent
	 * @param child
	 * @return interface
	 */
	public static final Interface getInterface(int parent, int child) {
		return getParentInterface(parent).getChild(child);
	}

	/**
	 * Gets the interface of the provided interface id.
	 * 
	 * @param id
	 * @return interface
	 */
	public static final Interface getInterface(int id) {
		org.rev317.accessors.Interface accessor = Loader.getClient().getInterfaceCache()[id];
		if (accessor == null)
			return null;
		Interface iface = new Interface(accessor);
		if (iface.getId() == iface.getParentId())
			return iface;
		return getInterface(iface.getParentId(), id);
	}

	/**
	 * Gets interface from index
	 * @param index
	 * @return interface
	 */
	public static final Interface get(int index) {
		if (Loader.getClient().getInterfaceCache() == null || Loader.getClient().getInterfaceCache()[index] == null) {
			return null;
		}
		return new Interface(Loader.getClient().getInterfaceCache()[index]);
	}

	/**
	 * Fetches parentId from childId
	 * @param childId
	 * @return parent id
	 */
	public static final int getParentId(int childId) {
		if (parents.isEmpty())
			for (Interface face : getParentInterfaces()) {
				if (face != null) {
					if (face.getChildren() != null) {
						for (Interface child : face.getChildren()) {
							if (child != null) {
								parents.put(child.getId(), face.getId());
							}
						}

					}
				}
			}
		if (parents.get(childId) == null) {
			return childId;
		}
		return parents.get(childId);

	}

}
