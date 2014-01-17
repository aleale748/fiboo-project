package es.uca.fiboo.screens;

import com.badlogic.gdx.audio.Music;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.robotgame.screens.RobotGameScreen;

public class InicioRobotGameScreen extends AbstractLoadingScreen {

	public InicioRobotGameScreen(fibooGame game) {
		super(game);
		fibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).stop();	
		fibooGame.MANAGER.get("sonidos/ayuda.ogg", Music.class).setLooping(true);
		fibooGame.MANAGER.get("sonidos/ayuda.ogg", Music.class).play();	
	}
	
	@Override
	public void setGameScreen() {
        game.setScreen(new RobotGameScreen(game));
	}
	
	@Override
	public void loadAssets() {
		fibooGame.MANAGER.loadRobotMiniGameTextures();	
	}
	
	@Override
	public String getImagenFondo() {
		String path = "robotgame/pantallainiciorobot.png";
		return path;
	}

}
