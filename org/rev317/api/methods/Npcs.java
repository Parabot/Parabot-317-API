package org.rev317.api.methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.parabot.environment.api.utils.Filter;
import org.rev317.accessors.Client;
import org.rev317.api.wrappers.interactive.Npc;
import org.rev317.loader.Loader;


/**
 * 
 * @author Clisprail
 *
 */
public final class Npcs {
	
	private static final Comparator<Npc> NEAREST_SORTER = new Comparator<Npc>() {

		@Override
		public int compare(Npc n1, Npc n2) {
			return n1.distanceTo() - n2.distanceTo();
		}

	};
	
	private static final Filter<Npc> ALL_FILTER = new Filter<Npc>() {

		@Override
		public boolean accept(Npc n) {
			return true;
		}
		
	};
	
	/**
	 * Gets all Npcs except local Npc
	 * @param filter
	 * @return all Npcs
	 */
	public static final Npc[] getNpcs(final Filter<Npc> filter) {
		final Client client = Loader.getClient();
		final int[] NpcIndices = client.getNpcIndices();
		ArrayList<Npc> npcList = new ArrayList<Npc>();
		final org.rev317.accessors.Npc[] accNpcs = client.getNpcs(); 
		for(int i = 0; i < NpcIndices.length; i++) {
			final int NpcIndex = NpcIndices[i];
			if(accNpcs[NpcIndex] == null) {
				continue;
			}
			final Npc npc = new Npc(accNpcs[NpcIndex]);
			if(filter.accept(npc)) {
				npcList.add(npc);
			}
		}
		return npcList.toArray(new Npc[npcList.size()]);
	}
	
	/**
	 * Gets all Npcs
	 * @return all Npcs
	 */
	public static final Npc[] getNpcs() {
		return getNpcs(ALL_FILTER);
	}
	
	/**
	 * Returns array with the first index to be the nearest Npc
	 * @param filter
	 * @return nearest Npcs
	 */
	public static final Npc[] getNearest(final Filter<Npc> filter) {
		final Npc[] npcs = getNpcs(filter);
		Arrays.sort(npcs, NEAREST_SORTER);
		return npcs;
	}
	
	/**
	 * Gets nearest npcs which hold given id(s)
	 * @param ids
	 * @return array of npcs with the first index to be the nearest
	 */
	public static final Npc[] getNearest(final int... ids) {
		final Npc[] npcs = getNpcs(new Filter<Npc>() {

			@Override
			public boolean accept(Npc npc) {
				for(final int id : ids) {
					if(id == npc.getDef().getId()) {
						return true;
					}
				}
				return false;
			}
			
		});
		Arrays.sort(npcs, NEAREST_SORTER);
		return npcs;
	}
	
	/**
	 * Gets nearest npcs which hold given name(s)
	 * @param names
	 * @return array of npcs with the first index to be the nearest
	 */
	public static final Npc[] getNearest(final String... names) {
		final Npc[] npcs = getNpcs(new Filter<Npc>() {

			@Override
			public boolean accept(Npc npc) {
				for(final String name : names) {
					if(npc.getName().toLowerCase().equals(name.toLowerCase())) {
						return true;
					}
				}
				return false;
			}
			
		});
		Arrays.sort(npcs, NEAREST_SORTER);
		return npcs;
	}
	
	/**
	 * Returns array with the first index to be the nearest Npc
	 * @return nearest Npcs
	 */
	public static final Npc[] getNearest() {
		return getNearest(ALL_FILTER);
	}

}
