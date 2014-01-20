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

	private transient float time;	
	
	public WinScreen(final FibooGame game) {
		super(game);
	}
	
	@Override
	public void render(final float delta) {
		time += 0.05f;
		stage.act();
		stage.draw();
		if (time > 2f || Gdx.input.isTouched()) {
			//fibooGame.MANAGER.get("sonidos/fondo.mp3", Sound.class).loop();
			dispose();
			game.setScreen(new MenuMiniJuegosScreen(game));
		}
	}

	@Override
	public void show() {
		final InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(final int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
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
		
		Image back = new Image(fondo);
		back.setFillParent(true);
		stage.addActor(back);
		
		Image win = new Image(imagen);
		win.setFillParent(true);
		stage.addActor(win);
	}

}
