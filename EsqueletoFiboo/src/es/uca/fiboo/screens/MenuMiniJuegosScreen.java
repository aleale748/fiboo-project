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
import es.uca.fiboo.cameraminigame.screens.CameraScreen;
import es.uca.fiboo.marcianosminigame.screens.InicioMarcianosGameScreen;
import es.uca.fiboo.naveminigame.screens.AyudaNaveScreen;
import es.uca.fiboo.pianocreen.screens.InicioPianoGameScreen;

public class MenuMiniJuegosScreen extends AbstractScreen {

	private ImageButton naveBoton, bolsaBoton, robotBoton, mapaBoton, marcianoBoton, atrasBoton, pianoBoton;
	private Image imgFondo;
	float w,h;
	public MenuMiniJuegosScreen(fibooGame game) {
		super(game);
		imgFondo = new Image(fibooGame.MANAGER.get("portada/pantallamenuentrenamiento.png", Texture.class));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);
		w= Gdx.graphics.getWidth();
		h= Gdx.graphics.getHeight();
	}

	@Override
	public void show() {
		super.show();
		InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
						game.setScreen(new MenuScreen(game));
				}
				return false;
			}
		}, stage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
		float imgWidth = w * 0.3f;
		float imgHeight = imgWidth;
	
		// Cargamos imagenes de botones
		TextureRegion naveBotonRegion = new TextureRegion(fibooGame.MANAGER.get("portada/naveboton.png", Texture.class));
		Drawable naveBotonDrawable = new TextureRegionDrawable(naveBotonRegion);
		
		//TextureRegion retosBotonRegion = new TextureRegion(new Texture(Gdx.files.internal("data/RetosBoton.png")));
		//Drawable retosBotonDrawable = new TextureRegionDrawable(retosBotonRegion);
		
		TextureRegion bolsaBotonRegion = new TextureRegion(fibooGame.MANAGER.get("portada/bolsaboton.png", Texture.class));
		Drawable bolsaBotonDrawable = new TextureRegionDrawable(bolsaBotonRegion);
		
		TextureRegion robotBotonRegion = new TextureRegion(fibooGame.MANAGER.get("portada/robotboton.png", Texture.class));
		Drawable robotBotonDrawable = new TextureRegionDrawable(robotBotonRegion);
		
		TextureRegion mapaBotonRegion = new TextureRegion(fibooGame.MANAGER.get("portada/mapaboton.png", Texture.class));
		Drawable mapaBotonDrawable = new TextureRegionDrawable(mapaBotonRegion);
		
		TextureRegion pianoBotonRegion = new TextureRegion(fibooGame.MANAGER.get("portada/pianoboton.png", Texture.class));
		Drawable pianoBotonDrawable = new TextureRegionDrawable(pianoBotonRegion);
		
		TextureRegion marcianoBotonRegion = new TextureRegion(fibooGame.MANAGER.get("portada/marcianoboton.png", Texture.class));
		Drawable marcianoBotonDrawable = new TextureRegionDrawable(marcianoBotonRegion);
		
		TextureRegion atrasBotonRegion = new TextureRegion(new Texture(Gdx.files.internal("portada/atrasboton.png")));
		Drawable atrasBotonDrawable = new TextureRegionDrawable(atrasBotonRegion);
		
		// Creamos botones, los posicionamos y los a??adimos al stage
		naveBoton = new ImageButton(naveBotonDrawable);
		naveBoton.setSize(imgWidth, imgHeight);
		naveBoton.setPosition(w*0.2f - naveBoton.getWidth()/2, 
				h*0.35f - naveBoton.getHeight()/2);
		naveBoton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on " + naveBoton.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + naveBoton.getClass().getSimpleName());
					game.setScreen(new AyudaNaveScreen(game));
				}
		});
		stage.addActor(naveBoton);
		
		//retosBoton = new ImageButton(retosBotonDrawable);
		//retosBoton.setPosition(150f, 150f);
		//stage.addActor(retosBoton);
		
		bolsaBoton = new ImageButton(bolsaBotonDrawable);
		bolsaBoton.setSize(imgWidth, imgHeight);
		bolsaBoton.setPosition(w*0.5f - bolsaBoton.getWidth()/2, 
				h*0.35f - bolsaBoton.getHeight()/2);
		bolsaBoton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on " + bolsaBoton.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + bolsaBoton.getClass().getSimpleName());
				game.setScreen(new InicioSacoGameScreen(game));
			}
		});
		stage.addActor(bolsaBoton);
		
		mapaBoton = new ImageButton(mapaBotonDrawable);
		mapaBoton.setSize(imgWidth, imgHeight);
		mapaBoton.setPosition(w*0.8f - mapaBoton.getWidth()/2, 
				h*0.65f - mapaBoton.getHeight()/2);
		mapaBoton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on " + mapaBoton.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + mapaBoton.getClass().getSimpleName());
						game.setScreen(new CameraScreen(game));
				}
		});
		stage.addActor(mapaBoton);
		
		robotBoton = new ImageButton(robotBotonDrawable);
		robotBoton.setSize(imgWidth, imgHeight);
		robotBoton.setPosition(w*0.2f - robotBoton.getWidth()/2, 
				h*0.65f - robotBoton.getHeight()/2);
		robotBoton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on " + robotBoton.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + robotBoton.getClass().getSimpleName());
						game.setScreen(new InicioRobotGameScreen(game));
				}
		});
		stage.addActor(robotBoton);
		
		pianoBoton = new ImageButton(pianoBotonDrawable);
		pianoBoton.setSize(imgWidth, imgHeight);
		pianoBoton.setPosition(w*0.5f - pianoBoton.getWidth()/2, 
				h*0.65f - pianoBoton.getHeight()/2);
		pianoBoton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on " + pianoBoton.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + pianoBoton.getClass().getSimpleName());
						game.setScreen(new InicioPianoGameScreen(game));
				}
		});
		stage.addActor(pianoBoton);
		
		marcianoBoton = new ImageButton(marcianoBotonDrawable);
		marcianoBoton.setSize(imgWidth, imgHeight);
		marcianoBoton.setPosition(w*0.8f - marcianoBoton.getWidth()/2, 
				h*0.35f - marcianoBoton.getHeight()/2);
		marcianoBoton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on " + marcianoBoton.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + marcianoBoton.getClass().getSimpleName());
						game.setScreen(new InicioMarcianosGameScreen(game));
				}
		});
		stage.addActor(marcianoBoton);
		
		atrasBoton = new ImageButton(atrasBotonDrawable);
		atrasBoton.setSize(imgWidth/2, imgHeight/2);
		atrasBoton.setPosition(w/(4f/0.3f) - atrasBoton.getWidth()/2f, 
				h*0.1f - atrasBoton.getHeight()/2);
		atrasBoton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on " + atrasBoton.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + atrasBoton.getClass().getSimpleName());
						game.setScreen(new MenuScreen(game));

				}
		});
		stage.addActor(atrasBoton);
	}

}
