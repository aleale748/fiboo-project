package es.uca.fiboo.sacominigame.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.screens.AbstractScreen;

public class SacoScreenPrincipal extends AbstractScreen {
	public static final String LOG = "SacoGame";
	protected static BitmapFont font;
	protected static int repeticiones; //Variable para contar las repeticiones del juego
	protected static int aciertos; //Variable para contar los aciertos que ha tenido
	protected static final int NUMERO_REPETICIONES = 3;
	fibooGame game;

	public SacoScreenPrincipal(fibooGame game) {
		super(game);
		this.game = game;
		font = new BitmapFont();
		repeticiones = 0; //Variable para repetir la ejecución del juego 4 veces, por ejemplo
		aciertos = 0; //Al inicio los aciertos son 0
	}
	
	public void show() {
		game.setScreen(new TiempoScreen(game));
	}
	
}
