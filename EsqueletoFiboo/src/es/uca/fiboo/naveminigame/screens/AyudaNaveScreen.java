package es.uca.fiboo.naveminigame.screens;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.screens.AbstractLoadingScreen;

public class AyudaNaveScreen extends AbstractLoadingScreen {

	public AyudaNaveScreen(fibooGame game) {
		super(game);
	}
	
	@Override
	public void setGameScreen() {
        game.setScreen(new NaveMiniGameScreen(game));
	}
	
	@Override
	public void loadAssets() {
		fibooGame.MANAGER.loadNaveMiniGameSounds();	
	}
	
	@Override
	public String getImagenFondo() {
		String path = "naveminigame/ayuda.png";
		return path;
	}

}
