package es.uca.fiboo.robotgame.screens;

import com.badlogic.gdx.audio.Music;

import es.uca.fiboo.FibooGame;
import es.uca.fiboo.screens.AbstractLoadingScreen;

public class InicioRobotGameScreen extends AbstractLoadingScreen {

	public InicioRobotGameScreen(final FibooGame game) {
		super(game);
		FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).stop();	
		FibooGame.MANAGER.get("sonidos/ayuda.ogg", Music.class).setLooping(true);
		FibooGame.MANAGER.get("sonidos/ayuda.ogg", Music.class).play();	
	}
	
	@Override
	public void setGameScreen() {
        game.setScreen(new RobotGameScreen(game));
	}
	
	@Override
	public void loadAssets() {
		FibooGame.MANAGER.loadRobotMiniGameTextures();	
	}
	
	@Override
	public String getImagenFondo() {
		return "robotgame/pantallainiciorobot.png";
	}

}
