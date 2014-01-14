package es.uca.fiboo.marcianosminigame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

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
		stage.act();
		stage.draw();
		if (time > 5f) {
			if(Gdx.input.isTouched()) {
				game.setScreen(new MenuMiniJuegosScreen(game));
			}
		}
	}
	
	private Image win;

	@Override
	public void show() {
		time = 0;
		win= new Image(fibooGame.atlasNaveMiniGame.findRegion("win"));
		win.setFillParent(true);
		stage.addActor(win);
	}

}
