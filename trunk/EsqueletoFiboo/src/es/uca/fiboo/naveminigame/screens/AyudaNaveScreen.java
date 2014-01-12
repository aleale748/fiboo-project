package es.uca.fiboo.naveminigame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.MenuMiniJuegosScreen;

public class AyudaNaveScreen extends AbstractScreen {

	public AyudaNaveScreen(fibooGame game) {
		super(game);
		fibooGame.MANAGER.loadNaveminigameScreen();
	}
	
	private float time;

	@Override
	public void render(float delta) {
		time += 0.05f;
		batch.begin();
		batch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(ayuda, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
		
		if (time > 5f) {
			if(Gdx.input.isTouched()) {
				game.setScreen(new NaveMiniGameScreen(game));
			}
		}
	}
	
	private Texture fondo, ayuda;

	@Override
	public void show() {
		time = 0;
		ayuda = fibooGame.MANAGER.get("naveminigame/ayuda.png", Texture.class);
		fondo = fibooGame.MANAGER.get("naveminigame/fondonave.png", Texture.class);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
