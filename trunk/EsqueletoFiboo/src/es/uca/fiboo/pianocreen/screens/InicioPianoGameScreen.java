package es.uca.fiboo.pianocreen.screens;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.screens.AbstractLoadingScreen;

public class InicioPianoGameScreen extends AbstractLoadingScreen {
	
		public InicioPianoGameScreen(fibooGame game) {

			super(game);	
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
