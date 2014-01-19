package es.uca.fiboo.pianocreen.screens;

import com.badlogic.gdx.audio.Music;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.screens.AbstractLoadingScreen;

public class InicioPianoGameScreen extends AbstractLoadingScreen {
	
		public InicioPianoGameScreen(fibooGame game) {
			super(game);	
			fibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).stop();	
			fibooGame.MANAGER.get("sonidos/ayuda.ogg", Music.class).setLooping(true);
			fibooGame.MANAGER.get("sonidos/ayuda.ogg", Music.class).play();
		}
		
		@Override
		public void setGameScreen() {
	        game.setScreen(new PianoScreen(game));
		}
		
		@Override
		public void loadAssets() {
			fibooGame.MANAGER.loadPianoMiniGameSounds();
		}
		
		@Override
		public String getImagenFondo() {
			String path = "pianogame/pantallainiciopiano.png";
			return path;
		}
	}