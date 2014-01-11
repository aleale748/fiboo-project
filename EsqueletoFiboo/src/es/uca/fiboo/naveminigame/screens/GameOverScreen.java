package es.uca.fiboo.naveminigame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.MenuMiniJuegosScreen;

public class GameOverScreen extends AbstractScreen {

	public GameOverScreen(fibooGame game) {
		super(game);
	}

	@Override
	public void render(float delta) {
		batch.begin();
		batch.draw(gameover, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
		
		if(Gdx.input.isTouched()) {
			game.setScreen(new MenuMiniJuegosScreen(game));
		}
	}
	
	private Texture gameover;

	@Override
	public void show() {
		gameover = fibooGame.MANAGER.get("naveminigame/older/gameover.png", Texture.class);
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
