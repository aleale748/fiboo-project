package es.uca.fiboo.sacominigame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.MenuMiniJuegosScreen;

public class EstadisticasScreen extends AbstractScreen {
	private Texture popup;
	protected fibooGame game;
	private ImageButton flechaButton;
	
	public EstadisticasScreen(fibooGame game) {
		super(game);
		
		this.game = game;
		
		popup = new Texture(Gdx.files.internal("sacominigame/img/popup.png"));
		
		//Botón (flecha) para volver atrás que hay que añadir
		
		//TextureRegion flechaRegion = new TextureRegion(new Texture("img/flecha.png"));
		//Drawable flechaDrawable = new TextureRegionDrawable(flechaRegion);
		//flechaButton = new ImageButton(flechaDrawable);
		//flechaButton.setPosition(x (ponerla bien), y (ponerla bien));
		//stage.addActor(flechaButton);
		
		for (int i = 0; i < 4; ++i) {
			stage.addActor(SacoScreenPrincipal.sin_puntos.get(i));
		}
		
		if(!SacoScreenPrincipal.puntos.isEmpty()) { //Si hay algun punto que lo muestre
			for (int i = 0; i < SacoScreenPrincipal.puntos.size(); ++i) {
				stage.addActor(SacoScreenPrincipal.puntos.get(i));
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

		float h = Gdx.graphics.getHeight();
		float w = Gdx.graphics.getWidth();
		batch.begin();
		
		if(SacoScreenPrincipal.NUMERO_REPETICIONES == SacoScreenPrincipal.aciertos) {
			SacoScreenPrincipal.font.draw(batch, "¡Muy bien! Has respondido todas bien", w/10, h/2);
		}
		else if(SacoScreenPrincipal.aciertos == 0) {
			SacoScreenPrincipal.font.draw(batch, "Ohhh.. esta vez fue difícil ¡Sigue intentandolo!", w/10, h/2);
		}
		else {
			SacoScreenPrincipal.font.draw(batch, "Esta bien, pero aún puedes mejorar ¡Sigue intentandolo!", w/10, h/2);
		}
		
		batch.end();
		
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void dispose() {
		fibooGame.MANAGER.unloadSacoMiniGameSounds();
		super.dispose();
	}

}
