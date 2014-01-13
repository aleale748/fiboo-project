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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import es.uca.fiboo.fibooGame;

public class ChooseScreen extends AbstractScreen {

	private ImageButton ninoBoton;
	private ImageButton ninaBoton;
	private Image imgFondo;
	public ChooseScreen(fibooGame game) {
		super(game);
		Gdx.input.setInputProcessor(stage);
		imgFondo = new Image(fibooGame.MANAGER.get("naveminigame/fondonave.png", Texture.class));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);
	}

	@Override
	public void show() {
		super.show();

		TextureRegion ninoBotonRegion = new TextureRegion(new Texture("data/polaroidnino2.png"));
		TextureRegion ninaBotonRegion = new TextureRegion(new Texture("data/polaroidnina2.png"));
		Drawable ninoBotonDrawable = new TextureRegionDrawable(ninoBotonRegion);
		Drawable ninaBotonDrawable = new TextureRegionDrawable(ninaBotonRegion);

		// Creamos el boton, lo posicionamo y lo a�adimo al stage
		ninoBoton = new ImageButton(ninoBotonDrawable);
		ninaBoton = new ImageButton(ninaBotonDrawable);
		
		float imgWidth = Gdx.graphics.getWidth() * 0.3f;
		float imgHeight = imgWidth;
		ninoBoton.setSize(imgWidth, imgHeight);
		ninaBoton.setSize(imgWidth, imgHeight);		
		
		ninoBoton.setX(Gdx.graphics.getWidth() / 4f - ninoBoton.getWidth() / 2f);
		ninoBoton.setY(Gdx.graphics.getHeight() / 2f - ninoBoton.getHeight() / 2f);
		ninoBoton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on ninoBoton");
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on ninoBoton");
				fibooGame.getPersonaje().getAvatar().setBase("pelonino3");
				imgFondo.addAction( sequence(delay(1.0f), fadeOut(0.75f)));
				ninaBoton.addAction( sequence(delay(1.0f), fadeOut(0.75f)));
				ninoBoton.addAction( sequence(delay(1.0f), fadeOut(0.75f),
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
		
		ninaBoton.setX(Gdx.graphics.getWidth() / (4f/3f) - ninaBoton.getWidth() / 2f);
		ninaBoton.setY(Gdx.graphics.getHeight() / 2 - ninaBoton.getHeight() / 2f);
		ninaBoton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on ninaBoton");
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on ninaBoton");
				fibooGame.getPersonaje().getAvatar().setBase("pelonina3");
				imgFondo.addAction( sequence(delay(1.0f), fadeOut(0.75f)));
				ninoBoton.addAction( sequence(delay(1.0f), fadeOut(0.75f)));
				ninaBoton.addAction( sequence(delay(1.0f), fadeOut(0.75f),
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
		stage.addActor(ninoBoton);
		stage.addActor(ninaBoton);
	}

}
