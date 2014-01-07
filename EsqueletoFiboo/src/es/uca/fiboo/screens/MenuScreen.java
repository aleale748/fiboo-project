package es.uca.fiboo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.naveminigame.screens.NaveMiniGameScreen;

public class MenuScreen extends AbstractScreen {

	private ImageButton entrenarBoton, retosBoton, personalizarBoton;
	private Image imgFondo;
	public MenuScreen(fibooGame game) {
		super(game);
		Gdx.input.setInputProcessor(stage);
		imgFondo = new Image(new Texture("portada/pantallamenuprincipal.png"));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);
	}
	
	@Override
	public void show() {
		super.show();
		
		// Cargamos imagenes de botones
		TextureRegion entrenarBotonRegion = new TextureRegion(new Texture(Gdx.files.internal("portada/botonentrenamiento.png")));
		Drawable entrenarBotonDrawable = new TextureRegionDrawable(entrenarBotonRegion);
		
		//TextureRegion retosBotonRegion = new TextureRegion(new Texture(Gdx.files.internal("data/RetosBoton.png")));
		//Drawable retosBotonDrawable = new TextureRegionDrawable(retosBotonRegion);
		
		TextureRegion personalizarBotonRegion = new TextureRegion(new Texture(Gdx.files.internal("portada/botonpersonalizar.png")));
		Drawable personalizarBotonDrawable = new TextureRegionDrawable(personalizarBotonRegion);
		
		// Creamos botones, los posicionamos y los a??adimos al stage
		entrenarBoton = new ImageButton(entrenarBotonDrawable);
		entrenarBoton.setPosition(Gdx.graphics.getWidth()/4 - entrenarBoton.getWidth()/2, 
				Gdx.graphics.getHeight()/2 - entrenarBoton.getHeight()/2);
		entrenarBoton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on " + entrenarBoton.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + entrenarBoton.getClass().getSimpleName());
						game.setScreen(new MenuMiniJuegosScreen(game));
				}
		});
		stage.addActor(entrenarBoton);
		
		//retosBoton = new ImageButton(retosBotonDrawable);
		//retosBoton.setPosition(150f, 150f);
		//stage.addActor(retosBoton);
		
		personalizarBoton = new ImageButton(personalizarBotonDrawable);
		personalizarBoton.setPosition(Gdx.graphics.getWidth()/(4f/3f) - personalizarBoton.getWidth()/2, 
				Gdx.graphics.getHeight()/2 - personalizarBoton.getHeight()/2);
		personalizarBoton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on " + personalizarBoton.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + personalizarBoton.getClass().getSimpleName());
						game.setScreen(new PruebaComplementosScreen(game));
				}
		});
		stage.addActor(personalizarBoton);
	}

}
