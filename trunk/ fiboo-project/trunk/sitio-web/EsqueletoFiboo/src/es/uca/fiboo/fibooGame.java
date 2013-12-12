package es.uca.fiboo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.FPSLogger;
import es.uca.fiboo.screens.MainScreen;

public class fibooGame extends Game {

		public static final String VERSION = "0.0.0.01 Pre-Alpha";
		public static final String LOG = "fibooGame";
		public static final boolean DEV_MODE = false;
		
		private AssetManager manager;

		// Clase de ayuda de libgdx que logea los FPS cada segundo
		private FPSLogger fpsLogger;

		public MainScreen getMainScreen() {
			return new MainScreen(this);
		}
		
		@Override
		public void create() {
			Gdx.app.log(fibooGame.LOG, "Creating game");
			fpsLogger = new FPSLogger();
		}
		
		@Override
		public void setScreen(Screen screen) {
			super.setScreen(screen);
			
			Gdx.app.log(fibooGame.LOG, "'Setting screen' to " + screen.getClass().getSimpleName());
		}

		@Override
		public void dispose() {
			super.dispose();
			
			Gdx.app.log(fibooGame.LOG, "'Disposing' game");
		}

		@Override
		public void render() {
			super.render();
			
			if( DEV_MODE ) fpsLogger.log();
		}

		@Override
		public void resize(int width, int height) {
			super.resize(width, height);
			Gdx.app.log(fibooGame.LOG, "'Resizing' to: " + width + " x " + height);

			// show the splash screen when the game is resized for the first time
			if (getScreen() == null) {
				setScreen(getMainScreen());
			}
		}

		@Override
		public void pause() {
			super.pause();
			
			Gdx.app.log(fibooGame.LOG, "'Pausing' game");
		}

		@Override
		public void resume() {
			super.resume();
			
			Gdx.app.log(fibooGame.LOG, "'Resuming' game");
		}
		
		public AssetManager getManager() {
			return manager;
		}
}