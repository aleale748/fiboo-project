package es.uca.fiboo.marcianosminigame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
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
		if (time > 2f) {
			if(Gdx.input.isTouched()) {
				//fibooGame.MANAGER.get("sonidos/fondo.mp3", Sound.class).loop();
				dispose();
				game.setScreen(new MenuMiniJuegosScreen(game));
			}
		}
	}
	
	private Image gameover;

	@Override
	public void show() {
		InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
					//musicaFondo.stop();
					//fibooGame.MANAGER.get("sonidos/fondo.mp3", Sound.class).loop();
					game.setScreen(new MenuMiniJuegosScreen(game));
					dispose();
				}
				return false;
			}
		}, stage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		time = 0;
		Texture textura = fibooGame.MANAGER.get("marcianosminigame/gameover.png", Texture.class);
		textura.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		gameover = new Image(textura);
		gameover.setFillParent(true);
		stage.addActor(gameover);
	}

}
