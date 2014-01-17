package es.uca.fiboo.screens;
import com.badlogic.gdx.audio.Music;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.tallerminigame.TallerScreenPrincipal;

public class InicioSacoGameScreen extends AbstractLoadingScreen {
	
	public InicioSacoGameScreen(fibooGame game) {
		super(game);
		fibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).stop();	
		fibooGame.MANAGER.get("sonidos/ayuda.ogg", Music.class).setLooping(true);
		fibooGame.MANAGER.get("sonidos/ayuda.ogg", Music.class).play();
	}
	
	@Override
	public void setGameScreen() {
        game.setScreen(new TallerScreenPrincipal(game));
	}
	
	@Override
	public void loadAssets() {
		fibooGame.MANAGER.loadSacoMiniGameSounds();	
	}
	
	@Override
	public String getImagenFondo() {
		String path = "sacominigame/pantallainiciobolsa.png";
		return path;
	}

}
