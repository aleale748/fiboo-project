package es.uca.fiboo.naveminigame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.MenuScreen;

public class WinScreen extends AbstractScreen {

	public WinScreen(fibooGame game) {
		super(game);
	}

	@Override
	public void render(float delta) {
		batch.begin();
		batch.draw(win, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
		
		if(Gdx.input.isTouched()) {
			game.setScreen(new MenuScreen(game));
		}
	}
	
	private Texture win;

	@Override
	public void show() {
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
