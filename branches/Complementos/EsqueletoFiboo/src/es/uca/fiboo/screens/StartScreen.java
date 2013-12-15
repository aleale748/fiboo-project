package es.uca.fiboo.screens;

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
import es.uca.fiboo.fibooGame;

public class StartScreen extends AbstractScreen {

	private ImageButton playBoton;

	public StartScreen(fibooGame game) {
		super(game);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void show() {
		super.show();

		TextureRegion playBotonRegion = new TextureRegion(new Texture("data/playBoton.png"));
		Drawable playBotonDrawable = new TextureRegionDrawable(playBotonRegion);

		// Creamos el boton, lo posicionamo y lo a??adimo al stage
		playBoton = new ImageButton(playBotonDrawable);
		playBoton.setPosition(Gdx.graphics.getWidth()/2 - playBoton.getWidth()/2, 
				Gdx.graphics.getHeight()/2 - playBoton.getHeight()/2);
		playBoton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on playBoton");
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on playBoton");
				playBoton.addAction( sequence(delay(1.75f), fadeOut(0.75f),
				new Action() {
					@Override
					public boolean act(float delta) {
						// La ??ltima acci??n mover?? hacia pantalla de men??
						game.setScreen(new MenuScreen(game));
						return true;
					}
				}));
			}
		});
		
		stage.addActor(playBoton);
	}

}
