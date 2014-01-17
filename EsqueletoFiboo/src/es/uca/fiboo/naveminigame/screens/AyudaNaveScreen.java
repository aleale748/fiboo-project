package es.uca.fiboo.naveminigame.screens;

import com.badlogic.gdx.audio.Music;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.screens.AbstractLoadingScreen;

public class AyudaNaveScreen extends AbstractLoadingScreen {

	public AyudaNaveScreen(fibooGame game) {
		super(game);
		fibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).stop();
		fibooGame.MANAGER.get("sonidos/ayuda.ogg", Music.class).setLooping(true);
		fibooGame.MANAGER.get("sonidos/ayuda.ogg", Music.class).play();	
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
