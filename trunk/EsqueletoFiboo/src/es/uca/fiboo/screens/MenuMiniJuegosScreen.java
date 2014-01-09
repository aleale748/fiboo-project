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
import es.uca.fiboo.cameraminigame.screens.CameraScreen;
import es.uca.fiboo.naveminigame.screens.NaveMiniGameScreen;
import es.uca.fiboo.robotgame.screens.RobotGameScreen;
import es.uca.fiboo.marcianosminigame.screens.MarcianosMiniGameScreen;

public class MenuMiniJuegosScreen extends AbstractScreen {

	private ImageButton naveBoton, bolsaBoton, robotBoton, mapaBoton, atrasBoton;
	private Image imgFondo;
	public MenuMiniJuegosScreen(fibooGame game) {
		super(game);
		Gdx.input.setInputProcessor(stage);
		imgFondo = new Image(new Texture("portada/pantallamenuentrenamiento.png"));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);
	}
	
	@Override
	public void show() {
		super.show();
		
		// Cargamos imagenes de botones
		TextureRegion naveBotonRegion = new TextureRegion(new Texture(Gdx.files.internal("portada/naveboton.png")));
		Drawable naveBotonDrawable = new TextureRegionDrawable(naveBotonRegion);
		
		//TextureRegion retosBotonRegion = new TextureRegion(new Texture(Gdx.files.internal("data/RetosBoton.png")));
		//Drawable retosBotonDrawable = new TextureRegionDrawable(retosBotonRegion);
		
		TextureRegion bolsaBotonRegion = new TextureRegion(new Texture(Gdx.files.internal("portada/bolsaboton.png")));
		Drawable bolsaBotonDrawable = new TextureRegionDrawable(bolsaBotonRegion);
		
		TextureRegion robotBotonRegion = new TextureRegion(new Texture(Gdx.files.internal("portada/robotboton.png")));
		Drawable robotBotonDrawable = new TextureRegionDrawable(robotBotonRegion);
		
		TextureRegion mapaBotonRegion = new TextureRegion(new Texture(Gdx.files.internal("portada/mapaboton.png")));
		Drawable mapaBotonDrawable = new TextureRegionDrawable(mapaBotonRegion);
		TextureRegion atrasBotonRegion = new TextureRegion(new Texture(Gdx.files.internal("portada/atrasboton.png")));
		Drawable atrasBotonDrawable = new TextureRegionDrawable(atrasBotonRegion);
		
		// Creamos botones, los posicionamos y los a??adimos al stage
		naveBoton = new ImageButton(naveBotonDrawable);
		naveBoton.setPosition(Gdx.graphics.getWidth()/4 - naveBoton.getWidth()/2, 
				Gdx.graphics.getHeight()/4 - naveBoton.getHeight()/2);
		naveBoton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on " + naveBoton.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + naveBoton.getClass().getSimpleName());
						game.setScreen(new NaveMiniGameScreen(game));
				}
		});
		stage.addActor(naveBoton);
		
		//retosBoton = new ImageButton(retosBotonDrawable);
		//retosBoton.setPosition(150f, 150f);
		//stage.addActor(retosBoton);
		
		bolsaBoton = new ImageButton(bolsaBotonDrawable);
		bolsaBoton.setPosition(Gdx.graphics.getWidth()/(4f/3f) - bolsaBoton.getWidth()/2, 
				Gdx.graphics.getHeight()/4 - bolsaBoton.getHeight()/2);
		bolsaBoton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on " + bolsaBoton.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + bolsaBoton.getClass().getSimpleName());
						game.setScreen(new NaveMiniGameScreen(game));
				}
		});
		stage.addActor(bolsaBoton);
		
		mapaBoton = new ImageButton(mapaBotonDrawable);
		mapaBoton.setPosition(Gdx.graphics.getWidth()/4 - mapaBoton.getWidth()/2, 
				Gdx.graphics.getHeight()/(4f/3f) - mapaBoton.getHeight()/2);
		mapaBoton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on " + mapaBoton.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + mapaBoton.getClass().getSimpleName());
						game.setScreen(new MarcianosMiniGameScreen(game));
				}
		});
		stage.addActor(mapaBoton);
		
		robotBoton = new ImageButton(robotBotonDrawable);
		robotBoton.setPosition(Gdx.graphics.getWidth()/(4f/3f) - robotBoton.getWidth()/2, 
				Gdx.graphics.getHeight()/(4f/3f) - robotBoton.getHeight()/2);
		robotBoton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on " + robotBoton.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + robotBoton.getClass().getSimpleName());
						game.setScreen(new RobotGameScreen(game));
				}
		});
		stage.addActor(robotBoton);
		
		atrasBoton = new ImageButton(atrasBotonDrawable);
		atrasBoton.setPosition(Gdx.graphics.getWidth()/(4f/0.3f) - atrasBoton.getWidth()/2, 
				Gdx.graphics.getHeight()/(4f/0.5f) - atrasBoton.getHeight()/2);
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
