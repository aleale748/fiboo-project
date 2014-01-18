package es.uca.fiboo.marcianosminigame.screens;

import com.badlogic.gdx.audio.Music;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.screens.AbstractLoadingScreen;

public class InicioMarcianosGameScreen extends AbstractLoadingScreen {
	
	public InicioMarcianosGameScreen(fibooGame game) {
		super(game);
		fibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).stop();	
		fibooGame.MANAGER.get("sonidos/ayuda.ogg", Music.class).setLooping(true);
		fibooGame.MANAGER.get("sonidos/ayuda.ogg", Music.class).play();
	}
	
	@Override
	public void setGameScreen() {
        game.setScreen(new MarcianosMiniGameScreen(game));
	}
	
	@Override
	public void loadAssets() {
		fibooGame.MANAGER.loadMarcianosMiniGameSounds();	
	}
	
	@Override
	public String getImagenFondo() {
		String path = "marcianosminigame/pantallaayudamarcianos.png";
		return path;
	}

}