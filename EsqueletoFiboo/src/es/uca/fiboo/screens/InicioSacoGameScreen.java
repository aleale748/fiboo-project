package es.uca.fiboo.screens;
import com.badlogic.gdx.audio.Music;

import es.uca.fiboo.FibooGame;
import es.uca.fiboo.eltallerminigame.screens.TallerScreenPrincipal;

public class InicioSacoGameScreen extends AbstractLoadingScreen {
	
	public InicioSacoGameScreen(FibooGame game) {
		super(game);
		FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).stop();	
		FibooGame.MANAGER.get("sonidos/ayuda.ogg", Music.class).setLooping(true);
		FibooGame.MANAGER.get("sonidos/ayuda.ogg", Music.class).play();
	}
	
	@Override
	public void setGameScreen() {
        game.setScreen(new TallerScreenPrincipal(game));
	}
	
	@Override
	public void loadAssets() {
		FibooGame.MANAGER.loadSacoMiniGameSounds();	
	}
	
	@Override
	public String getImagenFondo() {
		String path = "sacominigame/pantallainiciobolsa.png";
		return path;
	}

}
