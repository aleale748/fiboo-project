package es.uca.fiboo.screens;
import es.uca.fiboo.fibooGame;
import es.uca.fiboo.tallerminigame.TallerScreenPrincipal;

public class InicioSacoGameScreen extends AbstractLoadingScreen {
	
	public InicioSacoGameScreen(fibooGame game) {
		super(game);	
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
