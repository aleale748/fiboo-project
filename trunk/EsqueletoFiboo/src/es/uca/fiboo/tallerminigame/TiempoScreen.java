package es.uca.fiboo.tallerminigame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.naveminigame.actors.EmptyStarActor;
import es.uca.fiboo.naveminigame.actors.StarActor;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.MenuMiniJuegosScreen;

import java.util.List;

public class TiempoScreen extends AbstractScreen {
	
	private int[] objetos;
	private Texture cofre;
	private float tiempo;
	private Texture gema;
	private Texture moneda;
	private Texture collar;
	fibooGame game;
	
	public TiempoScreen(fibooGame game) {
		super(game);
		
		this.game = game;
		
		objetos = new int[3];
		
		for(int i = 0; i < objetos.length ; i++) {
			objetos[i] = (int)(Math.random() * 10);
		}
		
		cofre = new Texture(Gdx.files.internal("sacominigame/img/cofre_grande.png"));
		gema = new Texture(Gdx.files.internal("sacominigame/img/gema.png"));
		moneda = new Texture(Gdx.files.internal("sacominigame/img/moneda.png"));
		collar = new Texture(Gdx.files.internal("sacominigame/img/collar.png"));
		
		tiempo = 0;
		
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
	public void render(float delta) {
		Gdx.app.log(TallerScreenPrincipal.LOG, "El numero de aciertos es"+TallerScreenPrincipal.aciertos);
		Gdx.app.log(TallerScreenPrincipal.LOG, "El numero de repeticiones es"+TallerScreenPrincipal.repeticiones);
		
		if(TallerScreenPrincipal.repeticiones < TallerScreenPrincipal.NUMERO_REPETICIONES) {
		
			if(tiempo <= 11) {
				Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
				Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
				float h = Gdx.graphics.getHeight();
				float w = Gdx.graphics.getWidth();
		
				batch.begin();
				TallerScreenPrincipal.font.draw(batch, "Recuerda el número de objetos que hay de cada uno!", w/10, h);
				batch.draw(cofre, w/3, h/4, w/3, h/2);
				batch.draw(gema, w/2 + w/5, h/3, w/14, h/12);
				TallerScreenPrincipal.font.draw(batch, " " +objetos[2], w/2 + w/4 + gema.getWidth(), h/3);
				batch.draw(moneda, w/2 + w/5, h/5, w/14, h/12);
				TallerScreenPrincipal.font.draw(batch, " " +objetos[1], w/2 + w/4 + moneda.getWidth(), h/5);
				batch.draw(collar, w/2 + w/5, h/12, w/14, h/12);
				TallerScreenPrincipal.font.draw(batch, " " +objetos[0], w/2 + w/4 + collar.getWidth(), h/9);
				TallerScreenPrincipal.font.draw(batch, " " +(10 - (int)tiempo), w/10, h/2);
				batch.end();
			
				tiempo = tiempo + delta;
			}
		
			else {	
				TallerScreenPrincipal.repeticiones++;
				game.setScreen(new TallerScreen(game,objetos));
			}
			
		}
		
		else {
			
			//Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
			//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			//float h = Gdx.graphics.getHeight();
			//float w = Gdx.graphics.getWidth();
			
			//game.batch.begin();
			//game.font.draw(game.batch, "Has tenido "+SacoGame.aciertos+" aciertos!", 0, 480);
			//game.batch.end();
			
			game.setScreen(new EstadisticasScreen(game)); //Como ya ha realizado todas las repeticiones, mostramos las estadísticas
		}
		
		stage.act(delta);
		stage.draw();
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
	public void dispose() {
		//fibooGame.MANAGER.unloadSacoMiniGameSounds();
		super.dispose();
	}

}