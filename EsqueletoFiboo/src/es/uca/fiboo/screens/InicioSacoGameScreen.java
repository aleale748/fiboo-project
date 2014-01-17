package es.uca.fiboo.screens;
import es.uca.fiboo.fibooGame;
import es.uca.fiboo.sacominigame.screens.SacoScreenPrincipal;

public class InicioSacoGameScreen extends AbstractLoadingScreen {
	
	public InicioSacoGameScreen(fibooGame game) {
		super(game);	
	}
	
	@Override
	public void setGameScreen() {
        game.setScreen(new SacoScreenPrincipal(game));
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
