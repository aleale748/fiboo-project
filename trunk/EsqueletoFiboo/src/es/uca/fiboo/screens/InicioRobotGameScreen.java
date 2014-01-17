package es.uca.fiboo.screens;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.robotgame.screens.RobotGameScreen;

public class InicioRobotGameScreen extends AbstractLoadingScreen {

	public InicioRobotGameScreen(fibooGame game) {
		super(game);	
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
