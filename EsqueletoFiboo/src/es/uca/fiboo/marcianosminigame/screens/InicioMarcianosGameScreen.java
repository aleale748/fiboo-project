package es.uca.fiboo.marcianosminigame.screens;

import com.badlogic.gdx.audio.Music;

import es.uca.fiboo.FibooGame;
import es.uca.fiboo.screens.AbstractLoadingScreen;

public class InicioMarcianosGameScreen extends AbstractLoadingScreen {
	
	public InicioMarcianosGameScreen(final FibooGame game) {
		super(game);
		FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).stop();	
		FibooGame.MANAGER.get("sonidos/ayuda.ogg", Music.class).setLooping(true);
		FibooGame.MANAGER.get("sonidos/ayuda.ogg", Music.class).play();
	}
	
	@Override
	public void setGameScreen() {
        game.setScreen(new MarcianosMiniGameScreen(game));
	}
	
	@Override
	public void loadAssets() {
		FibooGame.MANAGER.loadMarcianosMiniGameSounds();	
	}
	
	@Override
	public String getImagenFondo() {
		return "marcianosminigame/pantallaayudamarcianos.png";
	}

}