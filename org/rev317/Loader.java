package org.rev317;

import org.objectweb.asm.Opcodes;
import org.parabot.core.Context;
import org.parabot.core.Directories;
import org.parabot.core.asm.ASMClassLoader;
import org.parabot.core.asm.adapters.AddInterfaceAdapter;
import org.parabot.core.desc.ServerProviderInfo;
import org.parabot.core.ui.components.VerboseLoader;
import org.parabot.environment.api.utils.WebUtil;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.servers.ServerManifest;
import org.parabot.environment.servers.ServerProvider;
import org.parabot.environment.servers.Type;
import org.rev317.accessors.Client;
import org.rev317.script.ScriptEngine;
import org.rev317.utils.BotMenu;

import javax.swing.*;

import java.applet.Applet;
import java.io.File;
import java.net.URL;

/**
 * 
 * This is a loader for a an unobfuscated 317 server
 * 
 * @author Everel
 *
 */
@ServerManifest(author = "Everel & Paradox", name = "Server Name Here", type = Type.INJECTION, version = 0.6)
public class Loader extends ServerProvider implements Opcodes {
	private Applet applet;

	/**
	 * Inits/loads the applet so it's ready to be added to the game panel
	 */
	@Override
	public Applet fetchApplet() {
		// load the applet, use the ASMClassLoader to load our injected classes
		try {
			final Context context = Context.getInstance();
			final ASMClassLoader classLoader = context.getASMClassLoader();
			final Class<?> clientClass = classLoader.loadClass(context.getServerProviderInfo().getClientClass());
			Object instance = clientClass.newInstance();
			applet = (Applet) instance;
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
	}

	@Override
	public URL getJar() {
		ServerProviderInfo serverProvider = Context.getInstance().getServerProviderInfo();
		
		File target = new File(Directories.getCachePath(), serverProvider.getClientCRC32() + ".jar");
		if(!target.exists()) {
			WebUtil.downloadFile(serverProvider.getClient(), target, VerboseLoader.get());
		}
		
		return WebUtil.toURL(target);
	}

	@Override
	public URL getHooks() {
		return Context.getInstance().getServerProviderInfo().getHookFile();
	}

	@Override
	public void addMenuItems(JMenuBar bar) {
		new BotMenu(bar).addItems();
	}
	
	
	public static Client getClient() {
		return (Client) Context.getInstance().getClient();
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

