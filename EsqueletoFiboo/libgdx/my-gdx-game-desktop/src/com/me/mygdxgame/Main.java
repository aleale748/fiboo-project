package com.me.mygdxgame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "my-gdx-game v" + MyGdxGame.VERSION;
		cfg.useGL20 = false;
		cfg.width = 512;
		cfg.height = 512;
		
		new LwjglApplication(new MyGdxGame(), cfg);
	}
}
