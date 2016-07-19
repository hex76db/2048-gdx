package it.nikozy.twozerofoureight.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import it.nikozy.twozerofoureight.TwoZeroFourEight;

import static it.nikozy.twozerofoureight.util.GameConfiguration.VIEWPORT_HEIGHT;
import static it.nikozy.twozerofoureight.util.GameConfiguration.VIEWPORT_WIDTH;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = VIEWPORT_HEIGHT;
		config.width = VIEWPORT_WIDTH;
		new LwjglApplication(new TwoZeroFourEight(), config);
	}
}
