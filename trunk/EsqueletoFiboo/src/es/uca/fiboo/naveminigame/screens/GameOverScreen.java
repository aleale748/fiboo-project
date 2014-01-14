package es.uca.fiboo.naveminigame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.MenuMiniJuegosScreen;

public class GameOverScreen extends AbstractScreen {

	public GameOverScreen(fibooGame game) {
		super(game);
	}
	
	private float time;

	@Override
	public void render(float delta) {
		time += 0.05f;
		stage.act();
		stage.draw();
		if (time > 5f) {
			if(Gdx.input.isTouched()) {
				game.setScreen(new MenuMiniJuegosScreen(game));
			}
		}
	}
	
	private Image gameover;

	@Override
	public void show() {
		time = 0;
		gameover= new Image(fibooGame.atlasNaveMiniGame.findRegion("gameover"));
		gameover.setFillParent(true);
		stage.addActor(gameover);
	}
}
