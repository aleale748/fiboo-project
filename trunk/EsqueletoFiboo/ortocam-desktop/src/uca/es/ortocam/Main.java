package uca.es.ortocam;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {

	public static void main(String[] args) {
		new LwjglApplication(new OrtoCam(), "2D Camera", 480, 320, false);
	}
}