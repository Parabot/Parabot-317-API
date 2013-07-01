package org.rev317.core;

import org.rev317.api.events.MessageEvent;
import org.rev317.script.ScriptEngine;

public class MessageDispatcher {
	
	public static final void messageListenerHook(int type, String name, String message) {
		final MessageEvent messageEvent = new MessageEvent(type, name, message);
		ScriptEngine.getInstance().dispatch(messageEvent);
	}

}
