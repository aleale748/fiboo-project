package es.uca.fiboo.eltallerminigame.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.naveminigame.actors.EmptyStarActor;
import es.uca.fiboo.naveminigame.actors.StarActor;
import es.uca.fiboo.screens.AbstractScreen;

public class TallerScreenPrincipal extends AbstractScreen {
	public static final String LOG = "TallerMiniGame";
	protected static BitmapFont font;
	protected static int repeticiones; //Variable para contar las repeticiones del juego
	protected static int aciertos; //Variable para contar los aciertos que ha tenido
	protected static int fallos; //Variable para contar los fallos que ha tenido
	protected static final int NUMERO_REPETICIONES = 4;
	protected static List<StarActor> puntos;
	protected static List<EmptyStarActor> sin_puntos;
	fibooGame game;

	public TallerScreenPrincipal(fibooGame game) {
		super(game);
		this.game = game;
		repeticiones = 0; //Variable para repetir la ejecuci�n del juego 4 veces, por ejemplo
		aciertos = 0; //Al inicio los aciertos son 0
		fallos = 0; //Al inicio los fallos son 0
		//A�adiendo puntuaciones
		float widthPuntuacion = 42;
		float heightPuntuacion = 42;
		font = new BitmapFont();
		
		sin_puntos = new ArrayList<EmptyStarActor>();
		for (int i = 0; i < 4; ++i) {
			sin_puntos.add(new EmptyStarActor());
			sin_puntos.get(i).setPosition(widthPuntuacion*0.2f + i * widthPuntuacion*1.1f, Gdx.graphics.getHeight() - heightPuntuacion*1.1f);
			stage.addActor(sin_puntos.get(i));
		}
		
		puntos = new ArrayList<StarActor>();
	}
	
	public void show() {
		game.setScreen(new TiempoScreen(game,2));
	}
	
}