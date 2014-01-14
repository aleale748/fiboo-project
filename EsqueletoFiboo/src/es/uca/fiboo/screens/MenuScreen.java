package es.uca.fiboo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.personalizar.screens.PersonalizacionScreen;

public class MenuScreen extends AbstractScreen {

	private ImageButton entrenarBoton, personalizarBoton;
	private Image imgFondo;
	public MenuScreen(fibooGame game) {
		super(game);
		imgFondo = new Image(new Texture("portada/pantallamenuprincipal.png"));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);
	}
	
	@Override
	public void show() {
		super.show();
		
		InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
						Gdx.app.exit();
				}
				return false;
			}
		}, stage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
		float imgWidth = Gdx.graphics.getWidth() * 0.3f;
		float imgHeight = imgWidth;
		
		// Cargamos imagenes de botones
		TextureRegion entrenarBotonRegion = new TextureRegion(new Texture(Gdx.files.internal("portada/botonentrenamiento.png")));
		Drawable entrenarBotonDrawable = new TextureRegionDrawable(entrenarBotonRegion);
		
		//TextureRegion retosBotonRegion = new TextureRegion(new Texture(Gdx.files.internal("data/RetosBoton.png")));
		//Drawable retosBotonDrawable = new TextureRegionDrawable(retosBotonRegion);
		
		TextureRegion personalizarBotonRegion = new TextureRegion(new Texture(Gdx.files.internal("portada/botonpersonalizar.png")));
		Drawable personalizarBotonDrawable = new TextureRegionDrawable(personalizarBotonRegion);
		
		// Creamos botones, los posicionamos y los a??adimos al stage
		entrenarBoton = new ImageButton(entrenarBotonDrawable);
		entrenarBoton.setSize(imgWidth, imgHeight);
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
		personalizarBoton.setSize(imgWidth, imgHeight);
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
						game.setScreen(new PersonalizacionScreen(game));
				}
		});
		stage.addActor(personalizarBoton);
	}

}
