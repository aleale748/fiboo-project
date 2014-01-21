package es.uca.fiboo.tallerminigame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import es.uca.fiboo.FibooGame;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.GameOverScreen;
import es.uca.fiboo.screens.MenuMiniJuegosScreen;
import es.uca.fiboo.screens.WinScreen;

public class EstadisticasScreen extends AbstractScreen {
	
	public EstadisticasScreen(final FibooGame game) {
		super(game);
		
		for (int i = 0; i < 4; ++i) {
			stage.addActor(TallerScreenPrincipal.SINPUNTOS.get(i));
		}
		
		if(!TallerScreenPrincipal.puntos.isEmpty()) { //Si hay algun punto que lo muestre
			for (int i = 0; i < TallerScreenPrincipal.puntos.size(); ++i) {
				stage.addActor(TallerScreenPrincipal.puntos.get(i));
			}
		}
	}
	
	@Override 
	public void show() {
		
		final InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(final int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
					game.setScreen(new MenuMiniJuegosScreen(game));
					dispose();
				}
				return false;
			}
		}, stage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void render(final float delta) {				
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.begin();
		
		if(TallerScreenPrincipal.NUM_REPETICIONES == TallerScreenPrincipal.aciertos) {
			dispose();
			game.setScreen(new WinScreen(game));
		}
		else {
			dispose();
			game.setScreen(new GameOverScreen(game));
		}
		
		batch.end();
		
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void dispose() {
		FibooGame.MANAGER.get("sonidos/taller.ogg", Music.class).stop();
		FibooGame.MANAGER.unloadSacoMiniGameSounds();
		FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).setLooping(true);
		FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).play();
		super.dispose();
	}

}

