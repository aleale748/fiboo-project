package es.uca.fiboo.naveminigame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.MenuMiniJuegosScreen;

public class WinScreen extends AbstractScreen {

	public WinScreen(fibooGame game) {
		super(game);
	}

	private float time;
	
	@Override
	public void render(float delta) {
		time += 0.05f;
		batch.begin();
		batch.draw(win, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
		
		if (time > 5f) {
			if(Gdx.input.isTouched()) {
				game.setScreen(new MenuMiniJuegosScreen(game));
			}
		}
	}
	
	private Texture win;

	@Override
	public void show() {
		time = 0;
		win = fibooGame.MANAGER.get("naveminigame/older/win.png", Texture.class);
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
