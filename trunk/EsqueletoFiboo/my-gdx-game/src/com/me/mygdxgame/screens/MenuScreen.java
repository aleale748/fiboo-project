package com.me.mygdxgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.mygdxgame.MyGdxGame;

public class MenuScreen extends AbstractScreen {

	private ImageButton entrenarBoton, retosBoton, personalizarBoton;
	
	public MenuScreen(Game game) {
		super(game);
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void show() {
		super.show();
		
		// Cargamos imagenes de botones
		TextureRegion entrenarBotonRegion = new TextureRegion(new Texture(Gdx.files.internal("data/EntrenarBoton.png")));
		Drawable entrenarBotonDrawable = new TextureRegionDrawable(entrenarBotonRegion);
		
		TextureRegion retosBotonRegion = new TextureRegion(new Texture(Gdx.files.internal("data/RetosBoton.png")));
		Drawable retosBotonDrawable = new TextureRegionDrawable(retosBotonRegion);
		
		TextureRegion personalizarBotonRegion = new TextureRegion(new Texture(Gdx.files.internal("data/PersoBoton.png")));
		Drawable personalizarBotonDrawable = new TextureRegionDrawable(personalizarBotonRegion);
		
		// Creamos botones, los posicionamos y los a??adimos al stage
		entrenarBoton = new ImageButton(entrenarBotonDrawable);
		entrenarBoton.setPosition(50f, 50f);
		stage.addActor(entrenarBoton);
		
		retosBoton = new ImageButton(retosBotonDrawable);
		retosBoton.setPosition(150f, 150f);
		stage.addActor(retosBoton);
		
		personalizarBoton = new ImageButton(personalizarBotonDrawable);
		personalizarBoton.setPosition(250f, 250f);
		personalizarBoton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(Game.LOG, "Touching down on " + personalizarBoton.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(Game.LOG, "Touching up on " + personalizarBoton.getClass().getSimpleName());
						game.setScreen(new PruebaDragScreen(game));
				}
		});
		stage.addActor(personalizarBoton);
	}

}
