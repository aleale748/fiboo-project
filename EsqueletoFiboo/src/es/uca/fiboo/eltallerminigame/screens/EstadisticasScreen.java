package es.uca.fiboo.eltallerminigame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.GameOverScreen;
import es.uca.fiboo.screens.MenuMiniJuegosScreen;
import es.uca.fiboo.screens.WinScreen;

public class EstadisticasScreen extends AbstractScreen {
	protected fibooGame game;
	
	public EstadisticasScreen(fibooGame game) {
		super(game);
		
		this.game = game;
		
		
		for (int i = 0; i < 4; ++i) {
			stage.addActor(TallerScreenPrincipal.sin_puntos.get(i));
		}
		
		if(!TallerScreenPrincipal.puntos.isEmpty()) { //Si hay algun punto que lo muestre
			for (int i = 0; i < TallerScreenPrincipal.puntos.size(); ++i) {
				stage.addActor(TallerScreenPrincipal.puntos.get(i));
			}
		}
	}
	
	@Override 
	public void show() {
		
		InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(int keycode) {
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
	public void render(float delta) {				
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.begin();
		
		if(TallerScreenPrincipal.NUMERO_REPETICIONES == TallerScreenPrincipal.aciertos) {
			//TallerScreenPrincipal.font.draw(batch, "�Muy bien! Has respondido todas bien", w/10, h/2);
			dispose();
			game.setScreen(new WinScreen(game));
		}
		else if(TallerScreenPrincipal.aciertos == 0) {
			//TallerScreenPrincipal.font.draw(batch, "Ohhh.. esta vez fue dif�cil �Sigue intentandolo!", w/10, h/2);
			dispose();
			game.setScreen(new GameOverScreen(game));
		}
		else {
			//TallerScreenPrincipal.font.draw(batch, "Esta bien, pero a�n puedes mejorar �Sigue intentandolo!", w/10, h/2);
			dispose();
			game.setScreen(new GameOverScreen(game));
		}
		
		batch.end();
		
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void dispose() {
		fibooGame.MANAGER.get("sonidos/taller.ogg", Music.class).stop();
		fibooGame.MANAGER.unloadSacoMiniGameSounds();
		fibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).setLooping(true);
		fibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).play();
		super.dispose();
	}

}
