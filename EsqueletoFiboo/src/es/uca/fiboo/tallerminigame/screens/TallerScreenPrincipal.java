package es.uca.fiboo.tallerminigame.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import es.uca.fiboo.FibooGame;
import es.uca.fiboo.naveminigame.actors.EmptyStarActor;
import es.uca.fiboo.naveminigame.actors.StarActor;
import es.uca.fiboo.screens.AbstractScreen;

public class TallerScreenPrincipal extends AbstractScreen {
	public static final String LOG = "TallerMiniGame";

	protected static int repeticiones; //Variable para contar las repeticiones del juego
	protected static int aciertos; //Variable para contar los aciertos que ha tenido
	protected static int fallos; //Variable para contar los fallos que ha tenido
	protected static final int NUM_REPETICIONES = 4;
	protected static List<StarActor> puntos;
	protected static final List<EmptyStarActor> SINPUNTOS = new ArrayList<EmptyStarActor>();

	public TallerScreenPrincipal(final FibooGame game) {
		super(game);

		FibooGame.MANAGER.get("sonidos/taller.ogg", Music.class).setLooping(true);
		FibooGame.MANAGER.get("sonidos/taller.ogg", Music.class).play();
		repeticiones = 0; //Variable para repetir la ejecuci�n del juego 4 veces, por ejemplo
		aciertos = 0; //Al inicio los aciertos son 0
		fallos = 0; //Al inicio los fallos son 0
		//A�adiendo puntuaciones
		final float widthPuntuacion = 42;
		final float heightPuntuacion = 42;
		
		for (int i = 0; i < 4; ++i) {
			SINPUNTOS.add(new EmptyStarActor());
			SINPUNTOS.get(i).setPosition(widthPuntuacion*0.2f + i * widthPuntuacion*1.1f, Gdx.graphics.getHeight() - heightPuntuacion*1.1f);
			stage.addActor(SINPUNTOS.get(i));
		}
		
		puntos = new ArrayList<StarActor>();
	}
	
	public void show() {
		game.setScreen(new TiempoScreen(game,2));
	}
	
}
