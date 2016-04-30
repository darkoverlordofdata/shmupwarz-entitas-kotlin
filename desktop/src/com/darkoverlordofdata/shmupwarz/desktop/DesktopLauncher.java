package com.darkoverlordofdata.shmupwarz.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.darkoverlordofdata.shmupwarz.ShmupWarz;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 320;//480;
		config.height = 480;//640;
		new LwjglApplication(new ShmupWarz(), config);
	}
}
