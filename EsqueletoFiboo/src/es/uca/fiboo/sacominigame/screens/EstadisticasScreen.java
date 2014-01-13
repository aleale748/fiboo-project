package es.uca.fiboo.sacominigame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.screens.AbstractScreen;

public class EstadisticasScreen extends AbstractScreen {
	private Texture popup;
	fibooGame game;
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
		
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		float h = Gdx.graphics.getHeight();
		float w = Gdx.graphics.getWidth();
		batch.begin();
		
		if(SacoScreenPrincipal.NUMERO_REPETICIONES == SacoScreenPrincipal.aciertos) {
			SacoScreenPrincipal.font.draw(batch, "¡Muy bien! Has respondido todas bien", w/2, h/2);
			
		}
		
		else if(SacoScreenPrincipal.aciertos == 0) {
			SacoScreenPrincipal.font.draw(batch, "Ohhh.. esta vez fue difícil ¡Sigue intentandolo!", w/2, h/2);
		}
		
		else {
			SacoScreenPrincipal.font.draw(batch, "Esta bien, pero aún puedes mejorar ¡Sigue intentandolo!", w/2, h/2);
		}
		
		batch.end();
		super.render(delta);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
