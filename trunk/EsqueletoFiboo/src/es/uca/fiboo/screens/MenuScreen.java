package es.uca.fiboo.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.personalizar.screens.PersonalizacionScreen;

public class MenuScreen extends AbstractScreen {

	private Image entrenarBoton, personalizarBoton;
	private Image imgFondo;
	private float w, h;
	
	public MenuScreen(fibooGame game) {
		super(game);
		imgFondo = new Image(fibooGame.MANAGER.get("portada/pantallamenuprincipal.png", Texture.class));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
	}
	
	@Override
	public void show() {
		super.show();

		InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
					//musicaFondo.stop();
					//fibooGame.MANAGER.get("sonidos/fondo.mp3", Sound.class).loop();
					dispose();
					Gdx.app.exit();
				}
				return false;
			}
		}, stage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		float imgWidth = w * 0.25f;
		float imgHeight = imgWidth;
		
		// Cargamos imagenes de botones
		Texture entrenar = fibooGame.MANAGER.get("portada/botonentrenamiento.png", Texture.class);
		Texture personalizar = fibooGame.MANAGER.get("portada/botonpersonalizar.png", Texture.class);
		entrenar.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		personalizar.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		// Creamos botones, los posicionamos y los añadimos al stage
		entrenarBoton = new Image(entrenar);
		entrenarBoton.setSize(imgWidth, imgHeight);
		entrenarBoton.setX(w/4 - entrenarBoton.getWidth()/2);
		entrenarBoton.setY(h/2 - entrenarBoton.getHeight()/2);
		entrenarBoton.addListener(new MyClickListener(1)); 
		
		personalizarBoton = new Image(personalizar);
		personalizarBoton.setSize(imgWidth, imgHeight);
		personalizarBoton.setX(w/(4f/3f) - personalizarBoton.getWidth()/2);
		personalizarBoton.setY(h/2 - personalizarBoton.getHeight()/2);
		personalizarBoton.addListener(new MyClickListener(2));
		
		stage.addActor(entrenarBoton);
		stage.addActor(personalizarBoton);
	}
	
	private class MyClickListener extends ClickListener {
		
		private int screen;

		public MyClickListener(int screen) {
			this.screen = screen;
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y) {
			switch(screen) {
				case 1:
					Gdx.app.log(fibooGame.LOG, "Clickeando en botón Minijuegos");
					stage.addAction(sequence(delay(0.5f), fadeOut(0.75f),
							new Action() {
								@Override
								public boolean act(float delta) {
									game.setScreen(new MenuMiniJuegosScreen(game));
									return true;
								}
							}));
					break;
				case 2:
					Gdx.app.log(fibooGame.LOG, "Clickeando en botón Personalización");
					stage.addAction(sequence(delay(0.5f), fadeOut(0.75f),
							new Action() {
								@Override
								public boolean act(float delta) {
									game.setScreen(new PersonalizacionScreen(game));
									return true;
								}
							}));
					break;					
			}
		}
	}

}
