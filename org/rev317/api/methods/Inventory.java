package org.rev317.api.methods;

import java.util.ArrayList;
import java.util.HashMap;

import org.parabot.environment.api.utils.Filter;
import org.rev317.loader.Loader;
import org.rev317.accessors.Client;
import org.rev317.api.wrappers.hud.Interface;
import org.rev317.api.wrappers.hud.Item;

/**
 * 
 * @author Clisprail
 *
 */
public final class Inventory {
	// it's a list because multiple tabs could be open
	private static HashMap<org.rev317.accessors.Interface, Interface> interfaceCache = new HashMap<org.rev317.accessors.Interface, Interface>();
	
	private static final Filter<Item> ALL_FILTER = new Filter<Item>() {

		@Override
		public boolean accept(Item i) {
			return true;
		}
		
	};
	
	/**
	 * Gets the inventory interface
	 * @return interface
	 */
	public static final Interface getInterface() {
		final Client client = Loader.getClient();
		if(!client.isLoggedIn()) {
			return null;
		}
		org.rev317.accessors.Interface inventory = Loader.getClient().getInterfaceCache()[3214];
		if(inventory == null) {
			return null;
		}
		if(interfaceCache.get(inventory) == null) {
			interfaceCache.put(inventory, new Interface(inventory));
		}
		return interfaceCache.get(inventory);
	}
	
	/**
	 * Gets the amount of items in inventory, excludes the stack sizes
	 * @return amount of items
	 */
	public static final int getCount() {
		return getCount(false);
	}
	
	/**
	 *  Gets the amount of items with given ids in inventory, excludes the stack sizes
	 * @param ids
	 * @return amount of items
	 */
	public static final int getCount(int... ids) {
		return getCount(false, ids);
	}
	
	/**
	 * Gets the amount of items in inventory
	 * @param includeStack - true for including stack sizes to the counting
	 * @return amount of items
	 */
	public static final int getCount(final boolean includeStack) {
		final Interface inventory = getInterface();
		if(inventory == null) {
			return -1;
		}
		int count = 0;
		final int[] items = inventory.getItems();
		final int[] stackSizes = includeStack ? inventory.getStackSizes() : null;
		for(int i = 0; i < items.length; i++) {
			if(items[i] != -1) {
				count += includeStack ? stackSizes[i] : 1;
			}
		}
		return count;
	}
	
	/**
	 * Gets the amount of items with given ids in inventory
	 * @param includeStack - true for including stack sizes to the counting
	 * @param ids
	 * @return amount of items
	 */
	public static final int getCount(final boolean includeStack, int... ids) {
		final Interface inventory = getInterface();
		if(inventory == null) {
			return -1;
		}
		int count = 0;
		final int[] items = inventory.getItems(); 
		final int[] stackSizes = includeStack ? inventory.getStackSizes() : null;
		for(int i = 0; i < items.length; i++) {
			final int itemId = items[i];
			if(itemId != -1) {
				for(final int id : ids) {
					if(id == itemId) {
						count += includeStack ? stackSizes[i] : 1;
						break;
					}
				}
			}
		}
		return count;
	}
	
	/**
	 * Gets every item in inventory
	 * @return items
	 */
	public static final Item[] getItems() {
		return getItems(ALL_FILTER);
	}
	
	/**
	 * Gets all items with given ids
	 * @param ids
	 * @return items
	 */
	public static final Item[] getItems(final int... ids) {
		return getItems(new Filter<Item>() {

			@Override
			public boolean accept(Item e) {
				for(int id : ids) {
					if(e.getId() == id) {
						return true;
					}
				}
				return false;
			}
			
		});
	}
	
	/**
	 * Gets all items accepted by filter
	 * @param filter
	 * @return items
	 */
	public static final Item[] getItems(final Filter<Item> filter) {
		final Interface inventory = getInterface();
		if(inventory == null) {
			return null;
		}
		final int[] items = inventory.getItems();
		final int[] stackSizes = inventory.getStackSizes();
		final ArrayList<Item> invItems = new ArrayList<Item>(28);
		for(int i = 0; i < items.length; i++) {
			final int itemId = items[i];
			if(itemId == -1) {
				continue;
			}
			final int stackSize = stackSizes[i];
			final Item item = new Item(itemId, stackSize, i, Item.TYPE_INVENTORY);
			if(filter.accept(item)) {
				invItems.add(item);
			}
		}
		return invItems.toArray(new Item[invItems.size()]);
	}
	
	/**
	 * Determines if inventory is full
	 * @return <b>true</b> if inventory is full, otherwise <b>false</b>
	 */
	public static final boolean isFull() {
		return Inventory.getCount() == 28;
	}
	
	/**
	 * Determines if inventory is empty
	 * @return <b>true</b> if inventory is empty, otherwise <b>false</b>
	 */
	public static final boolean isEmpty() {
		return Inventory.getCount() == 0;
	}

}
