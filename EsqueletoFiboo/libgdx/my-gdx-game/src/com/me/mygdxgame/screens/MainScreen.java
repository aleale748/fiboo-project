package com.me.mygdxgame.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.me.mygdxgame.MyGdxGame;

public class MainScreen extends AbstractScreen {

	private Image fondoImage;
	
	public MainScreen(MyGdxGame game) {
		super(game);
	}

	@Override
	public void show() {
		super.show();
		
		TextureRegion fondoRegion = new TextureRegion(new Texture("data/fondo.png"));
		Drawable fondoDrawable = new TextureRegionDrawable(fondoRegion);
		
		// Se crea el actor fondoImage y su tamaño se fija al llamar resize()
		fondoImage = new Image(fondoDrawable, Scaling.stretch);
		fondoImage.setFillParent(true);
		
		// Esto es necesario para el efecto fade-in. Esto la hace transparente
		fondoImage.getColor().a = 0f;
		
		// Se añade la acción de aparecer, esperar y desaparecer
		fondoImage.addAction( sequence(fadeIn(0.75f), delay(1.75f), fadeOut(0.75f),
				new Action() {
					@Override
					public boolean act(float delta) {
						// La última acción moverá hacia pantalla de inicio
						game.setScreen(new StartScreen(game));
						return true;
					}
		}));
		
		// Añadimos el actor al stage
		stage.addActor(fondoImage);
	}

}
