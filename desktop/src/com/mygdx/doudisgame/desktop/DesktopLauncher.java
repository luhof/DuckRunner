package com.mygdx.doudisgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.doudisgame.Doudisgame;
import com.mygdx.doudisgame.utils.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constants.APP_WIDTH;
		config.height = Constants.APP_HEIGHT;
		config.title = "Cours, Jean-Marie, Cours !";
		new LwjglApplication(new Doudisgame(), config);
	}
}

