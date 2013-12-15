package com.me.mygdxgame.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.mygdxgame.MyGdxGame;

public class StartScreen extends AbstractScreen {

	private ImageButton playBoton;

	public StartScreen(MyGdxGame game) {
		super(game);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void show() {
		super.show();

		TextureRegion playBotonRegion = new TextureRegion(new Texture("data/playBoton.png"));
		Drawable playBotonDrawable = new TextureRegionDrawable(playBotonRegion);

		// Creamos el boton, lo posicionamo y lo añadimo al stage
		playBoton = new ImageButton(playBotonDrawable);
		playBoton.setPosition(225f, 225f);
		playBoton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(MyGdxGame.LOG, "Touching down on playBoton");
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(MyGdxGame.LOG, "Touching up on playBoton");
				playBoton.addAction( sequence(delay(1.75f), fadeOut(0.75f),
				new Action() {
					@Override
					public boolean act(float delta) {
						// La última acción moverá hacia pantalla de menú
						game.setScreen(new MenuScreen(game));
						return true;
					}
				}));
			}
		});
		
		stage.addActor(playBoton);
	}

}
