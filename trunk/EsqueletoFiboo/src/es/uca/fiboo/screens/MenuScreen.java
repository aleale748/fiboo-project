package es.uca.fiboo.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import es.uca.fiboo.FibooGame;
import es.uca.fiboo.personalizar.screens.PersonalizacionScreen;

public class MenuScreen extends AbstractScreen {

	private Image imgFondo;
	private boolean cambiandoPantalla;
	private float w, h, escalaAvatar;
	
	public MenuScreen(FibooGame game) {
		super(game);
		imgFondo = new Image(FibooGame.MANAGER.get("portada/pantallamenuentrenamiento.png", Texture.class));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);
		cambiandoPantalla = false;
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
		
		float imgWidth = h * 0.5f;
		float imgHeight = imgWidth;
		
		escalaAvatar = h * 0.95f;
		
		// Cargamos imagenes de botones
		Texture entrenar = FibooGame.MANAGER.get("portada/botonentrenamiento.png", Texture.class);
		Texture personalizar = FibooGame.MANAGER.get("portada/botonpersonalizar.png", Texture.class);
		Texture info = FibooGame.MANAGER.get("portada/info.png", Texture.class);

		info.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		entrenar.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		personalizar.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		// Creamos botones, los posicionamos y los aï¿½adimos al stage
		Image infoBoton = new Image(info);
		infoBoton.setSize(imgWidth*0.2f, imgHeight*0.2f);
		infoBoton.setX(w*0.05f - infoBoton.getWidth()/2);
		infoBoton.setY(h*0.08f - infoBoton.getHeight()/2);
		infoBoton.addListener(new MyClickListener(1)); 
		
		Image entrenarBoton = new Image(entrenar);
		entrenarBoton.setSize(imgWidth, imgHeight);
		entrenarBoton.setX(w/(4f/3f) - entrenarBoton.getWidth()/2);
		entrenarBoton.setY(h*0.5f);
		entrenarBoton.addListener(new MyClickListener(2)); 
		
		Image personalizarBoton = new Image(personalizar);
		personalizarBoton.setSize(imgWidth, imgHeight);
		personalizarBoton.setX(w/(4f/3f) - personalizarBoton.getWidth()/2);
		personalizarBoton.setY(h*0.05f);
		personalizarBoton.addListener(new MyClickListener(3));
		stage.addActor(infoBoton);
		stage.addActor(entrenarBoton);
		stage.addActor(personalizarBoton);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		stage.draw();
		
		if(!cambiandoPantalla) {
			batch.begin();
			FibooGame.getPersonaje().drawAvatar(batch, escalaAvatar);
			batch.end();
		}
		
		stage.act(delta);
	}
	
	private class MyClickListener extends ClickListener {
		
		private int screen;

		public MyClickListener(int screen) {
			this.screen = screen;
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y) {
			cambiandoPantalla = true;
			stage.addAction(sequence(delay(0.5f), fadeOut(0.75f),
				new Action() {
					@Override
					public boolean act(float delta) {
						FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).stop();
						setNextScreen();
						return true;
					}
			}));
		}
		
		private void setNextScreen() {
			switch(screen) {
			case 1:
				Gdx.app.log(FibooGame.LOG, "Clickeando en botón Creditos");
				game.setScreen(new CreditosScreen(game));
				break;
			case 2:
				Gdx.app.log(FibooGame.LOG, "Clickeando en botón Minijuegos");
				game.setScreen(new MenuMiniJuegosScreen(game));
				break;
			case 3:
				Gdx.app.log(FibooGame.LOG, "Clickeando en botón Personalización");
				game.setScreen(new PersonalizacionScreen(game));
				break;	
			}
		}
	}

}
