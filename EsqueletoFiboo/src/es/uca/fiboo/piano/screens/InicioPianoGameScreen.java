package es.uca.fiboo.piano.screens;

import com.badlogic.gdx.audio.Music;

import es.uca.fiboo.FibooGame;
import es.uca.fiboo.screens.AbstractLoadingScreen;

public class InicioPianoGameScreen extends AbstractLoadingScreen {
	
		public InicioPianoGameScreen(final FibooGame game) {
			super(game);	
			FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).stop();	
			FibooGame.MANAGER.get("sonidos/ayuda.ogg", Music.class).setLooping(true);
			FibooGame.MANAGER.get("sonidos/ayuda.ogg", Music.class).play();
		}
		
		@Override
		public void setGameScreen() {
	        game.setScreen(new PianoScreen(game));
		}
		
		@Override
		public void loadAssets() {
			FibooGame.MANAGER.loadPianoMiniGameSounds();
		}
		
		@Override
		public String getImagenFondo() {
			return "pianogame/pantallainiciopiano.png";
		}
	}
