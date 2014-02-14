package org.rev317;

import org.objectweb.asm.Opcodes;
import org.parabot.core.Context;
import org.parabot.core.asm.ASMClassLoader;
import org.parabot.core.asm.adapters.AddInterfaceAdapter;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.servers.ServerManifest;
import org.parabot.environment.servers.ServerProvider;
import org.parabot.environment.servers.Type;
import org.rev317.accessors.Client;
import org.rev317.core.Injector;
import org.rev317.script.ScriptEngine;
import org.rev317.utils.BotMenu;
import org.rev317.utils.Reader;

import javax.swing.*;
import java.applet.Applet;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 * This is a loader for a an unobfuscated 317 server
 * 
 * @author Everel
 *
 */
@ServerManifest(author = "Everel & Paradox", name = "Server Name Here", type = Type.INJECTION, version = 0.4)
public class Loader extends ServerProvider implements Opcodes {
	private Applet applet = null;

	/**
	 * Inits/loads the applet so it's ready to be added to the game panel
	 */
	@Override
	public Applet fetchApplet() {
		// load the applet, use the ASMClassLoader to load our injected classes
		try {
			final Context context = Context.resolve();
			//context.getClassPath().dump("dumped.jar");
			final ASMClassLoader classLoader = context.getASMClassLoader();
			final Class<?> clientClass = classLoader.loadClass(Reader.readProvider("clientClass"));
            //Used to be:
            //final Class<?> clientClass = classLoader.loadClass("client");
			Object instance = clientClass.newInstance();
			applet = (Applet) instance;
			applet.init();
			applet.start();
			return applet;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void injectHooks() {
		AddInterfaceAdapter.setAccessorPackage("org/rev317/accessors/");
		// default injection is done by bot, it basically parses the hooks file
		super.injectHooks();
		
		// custom injections here
		Injector.injectPaint();
	}

	@Override
	public URL getJar() {
		try {
			// the location of the uninjected jar, if you store the jar on your pc use File.toURI().toURL();
			return new URL(Reader.readProvider("client"));
            //Used to be:
            //return new URL("http://url.to/jar.here");
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public URL getHooks() {
		try {
			// the location of the hooks file, if you store the jar on your pc use File.toURI().toURL();
			return new URL(Reader.readProvider("hooks"));
            //Used to be:
            //return new URL("http://url.to/hooks.here");
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void addMenuItems(JMenuBar bar) {
		new BotMenu(bar).addItems();
	}
	
	
	public static Client getClient() {
		return (Client) Context.resolve().getClient();
	}
	
	@Override
	public void initScript(Script script) {
		ScriptEngine.getInstance().setScript(script);
		ScriptEngine.getInstance().init();
	}
	
	@Override
	public void unloadScript(Script script) {
		ScriptEngine.getInstance().unload();
	}

}

