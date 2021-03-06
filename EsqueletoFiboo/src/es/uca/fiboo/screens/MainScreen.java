package es.uca.fiboo.screens;

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

import es.uca.fiboo.FibooGame;

public class MainScreen extends AbstractScreen {

	public MainScreen(final FibooGame game) {
		super(game);
	}
	
	@Override
	public void show() {
		super.show();
		TextureRegion fondoRegion = new TextureRegion(new Texture("portada/logoasteroid.png"));
		Drawable fondoDrawable = new TextureRegionDrawable(fondoRegion);
		// Se crea el actor fondoImage y su tama�o se fija al llamar resize()
		Image fondoImage = new Image(fondoDrawable, Scaling.fit);
		fondoImage.setFillParent(true);
		
		// Esto es necesario para el efecto fade-in. Esto la hace transparente
		fondoImage.getColor().a = 0f;
		
		// Se a�ade la acci�n de aparecer, esperar y desaparecer
		fondoImage.addAction( sequence(fadeIn(0.75f), delay(2.75f), fadeOut(0.75f),
				new Action() {
					@Override
					public boolean act(final float delta) {
						// La �ltima acci�n mover� hacia pantalla de inicio
						game.setScreen(new LoadingScreen(game));
						return true;
					}
		}));
		
		// A�adimos el actor al stage
		stage.addActor(fondoImage);
	}

	@Override
	public void hide() {
		super.hide();
		super.dispose();
	}

}
