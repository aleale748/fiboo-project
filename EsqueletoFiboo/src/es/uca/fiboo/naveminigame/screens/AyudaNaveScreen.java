package es.uca.fiboo.naveminigame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.screens.AbstractScreen;

public class AyudaNaveScreen extends AbstractScreen {

	public AyudaNaveScreen(fibooGame game) {
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
				game.setScreen(new NaveMiniGameScreen(game));
			}
		}
	}
	
	private Image fondo, ayuda;

	@Override
	public void show() {
		time = 0;
		ayuda = new Image(fibooGame.atlasNaveMiniGame.findRegion("ayuda"));
		fondo = new Image(fibooGame.atlasNaveMiniGame.findRegion("fondonave"));
		ayuda.setFillParent(true);
		fondo.setFillParent(true);
		stage.addActor(fondo);
		stage.addActor(ayuda);
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
