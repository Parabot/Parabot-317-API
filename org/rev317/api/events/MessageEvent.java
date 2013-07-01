package org.rev317.api.events;

/**
 * 
 * @author Clisprail, Matt
 * 
 */
public final class MessageEvent {
	private int type;
	private String name = null;
	private String message = null;

	public static final int TYPE_GENERIC = 0;
	public static final int TYPE_PLAYER = 2;
	public static final int TYPE_TRADE = 4;
	public static final int TYPE_DUEL = 8;

	public MessageEvent(final int type, String name, String msg) {
		this.type = type;
		this.name = name;
		this.message = msg;
	}

	public final String getMessage() {
		return message;
	}

	public final int getType() {
		return type;
	}

	public final String getSender() {
		return name;
	}

}