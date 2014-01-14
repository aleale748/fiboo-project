package es.uca.fiboo.sacominigame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.screens.AbstractScreen;

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
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.app.log(SacoScreenPrincipal.LOG, "El numero de aciertos es"+SacoScreenPrincipal.aciertos);
		Gdx.app.log(SacoScreenPrincipal.LOG, "El numero de repeticiones es"+SacoScreenPrincipal.repeticiones);
		
		if(SacoScreenPrincipal.repeticiones < SacoScreenPrincipal.NUMERO_REPETICIONES) {
		
			if(tiempo <= 11) {
				Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
				Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
				float h = Gdx.graphics.getHeight();
				float w = Gdx.graphics.getWidth();
		
				batch.begin();
				SacoScreenPrincipal.font.draw(batch, "Recuerda el número de objetos que hay de cada uno!", w/10, h);
				batch.draw(cofre, w/3, h/4, w/3, h/2);
				batch.draw(gema, w/2 + w/5, h/3, w/14, h/12);
				SacoScreenPrincipal.font.draw(batch, " " +objetos[2], w/2 + w/4 + gema.getWidth(), h/3);
				batch.draw(moneda, w/2 + w/5, h/5, w/14, h/12);
				SacoScreenPrincipal.font.draw(batch, " " +objetos[1], w/2 + w/4 + moneda.getWidth(), h/5);
				batch.draw(collar, w/2 + w/5, h/12, w/14, h/12);
				SacoScreenPrincipal.font.draw(batch, " " +objetos[0], w/2 + w/4 + collar.getWidth(), h/9);
				SacoScreenPrincipal.font.draw(batch, " " +(10 - (int)tiempo), w/10, h/2);
				batch.end();
			
				tiempo = tiempo + delta;
			}
		
			else {	
				SacoScreenPrincipal.repeticiones++;
				game.setScreen(new SacoScreen(game,objetos));
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

	}

}