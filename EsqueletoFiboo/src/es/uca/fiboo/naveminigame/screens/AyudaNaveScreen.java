package es.uca.fiboo.naveminigame.screens;

import com.badlogic.gdx.audio.Music;

import es.uca.fiboo.FibooGame;
import es.uca.fiboo.screens.AbstractLoadingScreen;

public class AyudaNaveScreen extends AbstractLoadingScreen {

	public AyudaNaveScreen(FibooGame game) {
		super(game);
		FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).stop();
		FibooGame.MANAGER.get("sonidos/ayuda.ogg", Music.class).setLooping(true);
		FibooGame.MANAGER.get("sonidos/ayuda.ogg", Music.class).play();	
	}
	
	@Override
	public void setGameScreen() {
        game.setScreen(new NaveMiniGameScreen(game));
	}
	
	@Override
	public void loadAssets() {
		FibooGame.MANAGER.loadNaveMiniGameSounds();	
	}
	
	@Override
	public String getImagenFondo() {
		String path = "naveminigame/ayuda.png";
		return path;
	}

}
