package es.uca.fiboo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import es.uca.fiboo.FibooGame;

public class WinScreen extends AbstractScreen {

	public WinScreen(FibooGame game) {
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
	
	private Image win;
	private Image back;


	@Override
	public void show() {
		InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
					//musicaFondo.stop();
					//fibooGame.MANAGER.get("sonidos/fondo.mp3", Sound.class).loop();
					dispose();
					game.setScreen(new MenuMiniJuegosScreen(game));
				}
				return false;
			}
		}, stage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		time = 0;
		Texture fondo = FibooGame.MANAGER.get("portada/pantallamenuentrenamiento.png", Texture.class);
		Texture imagen = FibooGame.MANAGER.get("portada/win.png", Texture.class);
		fondo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		imagen.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		back = new Image(fondo);
		back.setFillParent(true);
		stage.addActor(back);
		win = new Image(imagen);
		win.setFillParent(true);
		stage.addActor(win);
	}

}
