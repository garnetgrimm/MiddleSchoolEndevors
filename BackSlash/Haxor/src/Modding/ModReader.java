package Modding;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class ModReader {

	public static String path = ("mods");
	static File[] pathSearch = null;
	static boolean modsFound = false;
	static Class<?> c = null;
	public static Method[] init;
	public static Method[] update;
	public static Method[] onCommand;

	public static void init() {
		// C:/Users/Garnet Grimm/Desktop/moddinggame

		try {
			File f = new File(path);
			if (!f.exists()) {
				(f).mkdir();
				System.out.println("A new mods folder was created.");
			} else
				System.out.println("Mods folder found.");
		} catch (Exception e) {
			System.out.println("mods folder does not exist.");
		}

		try {
			pathSearch = new File(path).listFiles();
			if (pathSearch.length > 0)
				modsFound = true;
		} catch (Exception e) {
			System.out.println("Mods folder could not be accessed");
		}

		if (modsFound) {
			System.out.println("Mods found!");
			initMods();
		} else
			System.out.println("Vinilla loaded.");

	}

	public static void initMods() {

		update = new Method[pathSearch.length];
		init = new Method[pathSearch.length];
		onCommand = new Method[pathSearch.length];

		for (int i = 0; i < pathSearch.length; i++) {

			String s = pathSearch[i].getName();
			String CLASS = s.substring(0, s.length() - 6);

			try {
				File classFile = new File("mods/");
				URL url = classFile.toURI().toURL();
				URL[] urls = new URL[] { url };

				@SuppressWarnings("resource")
				ClassLoader cl = new URLClassLoader(urls);
				Class<?> cls = cl.loadClass(CLASS);

				try {
					init[i] = cls.getMethod("init", new Class<?>[0]);
					update[i] = cls.getMethod("update", new Class<?>[0]);
					onCommand[i] = cls.getMethod("onCommand", new Class[] { String.class });

					init[i].invoke(null, new Object[0]);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void updateMods() {
		for (int i = 0; i < pathSearch.length; i++) {

			try {
				update[i].invoke(null, new Object[0]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void renderMods() {

	}

	public static void checkModCommand(String command) {

		for (int i = 0; i < pathSearch.length; i++) {

			try {
				onCommand[i].invoke(null, (String) command);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}