package org.rev317.api.methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.parabot.environment.api.utils.Filter;
import org.rev317.accessors.Client;
import org.rev317.api.wrappers.interactive.Player;

import org.rev317.loader.Loader;

/**
 * 
 * A class which fetches all players near you
 * 
 * @author Clisprail
 *
 */
public final class Players {
	
	private static final Comparator<Player> NEAREST_SORTER = new Comparator<Player>() {

		@Override
		public int compare(Player p1, Player p2) {
			return p1.distanceTo() - p2.distanceTo();
		}

		
		
	};
	
	private static final Filter<Player> ALL_FILTER = new Filter<Player>() {

		@Override
		public boolean accept(Player p) {
			return true;
		}
		
	};
	
	/**
	 * Gets local player
	 * @return local player
	 */
	public static final Player getLocal() {
		final Client client = Loader.getClient();
		return new Player(client.getLocalPlayer());
	}
	
	/**
	 * Gets all players except local player
	 * @param filter
	 * @return all players
	 */
	public static final Player[] getPlayers(final Filter<Player> filter) {
		final Client client = Loader.getClient();
		final int[] playerIndices = client.getPlayerIndices();
		ArrayList<Player> playerList = new ArrayList<Player>();
		final org.rev317.accessors.Player[] accPlayers = client.getPlayers(); 
		for(int i = 0; i < playerIndices.length; i++) {
			final int playerIndex = playerIndices[i];
			if(accPlayers[playerIndex] == null) {
				continue;
			}
			final Player player = new Player(accPlayers[playerIndex]);
			if(filter.accept(player)) {
				playerList.add(player);
			}
		}
		return playerList.toArray(new Player[playerList.size()]);
	}
	
	/**
	 * Gets all players except local player
	 * @return all players
	 */
	public static final Player[] getPlayers() {
		return getPlayers(ALL_FILTER);
	}
	
	/**
	 * Returns array with the first index to be the nearest player
	 * @param filter
	 * @return nearest players
	 */
	public static final Player[] getNearest(final Filter<Player> filter) {
		final Player[] players = getPlayers(filter);
		Arrays.sort(players, NEAREST_SORTER);
		return players;
	}
	
	/**
	 * Returns array with the first index to be the nearest player
	 * @return nearest players
	 */
	public static final Player[] getNearest() {
		return getNearest(ALL_FILTER);
	}
	
	
	

}
