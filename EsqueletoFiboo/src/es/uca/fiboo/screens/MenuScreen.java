package es.uca.fiboo.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.personalizar.screens.PersonalizacionScreen;

public class MenuScreen extends AbstractScreen {

	private Image entrenarBoton, personalizarBoton, infoBoton;
	private Image imgFondo;
	private float w, h, escalaAvatar;
	
	public MenuScreen(fibooGame game) {
		super(game);
		imgFondo = new Image(fibooGame.MANAGER.get("portada/pantallamenuentrenamiento.png", Texture.class));
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
					Gdx.app.exit();
				}
				return false;
			}
		}, stage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		float imgWidth = w * 0.35f;
		float imgHeight = imgWidth;
		
		escalaAvatar = h * 0.7f;
		
		// Cargamos imagenes de botones
		Texture entrenar = fibooGame.MANAGER.get("portada/botonentrenamiento.png", Texture.class);
		Texture personalizar = fibooGame.MANAGER.get("portada/botonpersonalizar.png", Texture.class);
		Texture info = fibooGame.MANAGER.get("portada/info.png", Texture.class);

		info.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		entrenar.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		personalizar.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		// Creamos botones, los posicionamos y los a�adimos al stage
		infoBoton = new Image(info);
		infoBoton.setSize(imgWidth*0.2f, imgHeight*0.2f);
		infoBoton.setX(w*0.05f - infoBoton.getWidth()/2);
		infoBoton.setY(h*0.08f - infoBoton.getHeight()/2);
		infoBoton.addListener(new MyClickListener(1)); 
		
		entrenarBoton = new Image(entrenar);
		entrenarBoton.setSize(imgWidth, imgHeight);
		entrenarBoton.setX(w/(4f/3f) - entrenarBoton.getWidth()/2);
		entrenarBoton.setY(h*0.72f - entrenarBoton.getHeight()/2);
		entrenarBoton.addListener(new MyClickListener(2)); 
		
		personalizarBoton = new Image(personalizar);
		personalizarBoton.setSize(imgWidth, imgHeight);
		personalizarBoton.setX(w/(4f/3f) - personalizarBoton.getWidth()/2);
		personalizarBoton.setY(h*0.28f - personalizarBoton.getHeight()/2);
		personalizarBoton.addListener(new MyClickListener(3));
		stage.addActor(infoBoton);
		stage.addActor(entrenarBoton);
		stage.addActor(personalizarBoton);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
		batch.begin();
		fibooGame.getPersonaje().drawAvatar(batch, escalaAvatar);
		batch.end();
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
					Gdx.app.log(fibooGame.LOG, "Clickeando en bot�n Minijuegos");
					stage.addAction(sequence(delay(0.5f), fadeOut(0.75f),
							new Action() {
								@Override
								public boolean act(float delta) {
									dispose();
									game.setScreen(new CreditosScreen(game));
									return true;
								}
							}));
					break;
				case 2:
					Gdx.app.log(fibooGame.LOG, "Clickeando en bot�n Minijuegos");
					stage.addAction(sequence(delay(0.5f), fadeOut(0.75f),
							new Action() {
								@Override
								public boolean act(float delta) {
									dispose();
									game.setScreen(new MenuMiniJuegosScreen(game));
									return true;
								}
							}));
					break;
				case 3:
					Gdx.app.log(fibooGame.LOG, "Clickeando en bot�n Personalizaci�n");
					stage.addAction(sequence(delay(0.5f), fadeOut(0.75f),
							new Action() {
								@Override
								public boolean act(float delta) {
									dispose();
									game.setScreen(new PersonalizacionScreen(game));
									return true;
								}
							}));
					break;	
				
			}
		}
	}

}
