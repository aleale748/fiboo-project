package es.uca.fiboo.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import es.uca.fiboo.FibooGame;

public class LoadingScreen extends AbstractScreen {

	private transient Image playBoton;
	private transient BitmapFont font;
	private transient NinePatch loaderVacio;
	private transient NinePatch loaderFull;
	private transient final float width, height;
	
	public LoadingScreen(final FibooGame game) {
		super(game);
		Gdx.input.setInputProcessor(stage);
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
	}
	
	@Override
	public void show() {
		super.show();
		
		FibooGame.MANAGER.load("portada/portadafiboo.png", Texture.class);
		FibooGame.MANAGER.load("portada/playportada.png", Texture.class);
		FibooGame.MANAGER.load("loading/vacio.png", Texture.class);
		FibooGame.MANAGER.load("loading/full.png", Texture.class);
		FibooGame.MANAGER.finishLoading();
		
		//Barra de carga y texto cargando...
		font = new BitmapFont();
		Texture vacioT = FibooGame.MANAGER.get("loading/vacio.png", Texture.class);
		Texture fullT = FibooGame.MANAGER.get("loading/full.png", Texture.class);
		loaderVacio = new NinePatch(new TextureRegion(vacioT, 24, 24), 8, 8, 8, 8);
		loaderFull = new NinePatch(new TextureRegion(fullT, 24, 24), 8, 8, 8, 8);
		
		//Fondo de pantalla
		Texture bgT = FibooGame.MANAGER.get("portada/portadafiboo.png", Texture.class);
		bgT.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image bg = new Image(bgT);
		bg.setFillParent(true);
		
		//Bot�n iniciar juego
		Texture boton = FibooGame.MANAGER.get("portada/playportada.png", Texture.class); 
        boton.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        playBoton = new Image(boton);
        playBoton.setVisible(false);
        float botonWidth = width * 0.3f;
		float botonHeight = botonWidth;
		
		playBoton.setSize(botonWidth, botonHeight);
        playBoton.setX(width/2 - playBoton.getWidth()/2);
        playBoton.setY(height/4 - playBoton.getHeight()/2);
        
        playBoton.addListener(new ClickListener() {
            @Override
			public void clicked(final InputEvent event, final float x, final float y) {
				stage.addAction(sequence(delay(0.5f), fadeOut(0.75f),
                    new Action() {
                        @Override
                        public boolean act(final float delta) {
                        	if(isFisrtGame()) {
                        		//Gdx.app.log(getName(), "cargando choose screen");
                        		game.setScreen(new ChooseScreen(game));
                        	}
                        	else {
                        		//Gdx.app.log(getName(), "cargando menu screen");
                        		game.setScreen(new MenuScreen(game));
                        	}
                            return true;
                        }
                    }));
			}
        });

		stage.addActor(bg);
		stage.addActor(playBoton);
		
		// Carga de Assets
		FibooGame.MANAGER.loadPianoMiniGameTextures();
		FibooGame.MANAGER.loadNaveMiniGameTextures();
		FibooGame.MANAGER.loadMenuTextures();
		FibooGame.MANAGER.loadRobotMiniGameSounds();
		FibooGame.MANAGER.loadSacoMiniGameTextures();
		FibooGame.MANAGER.loadMarcianosMiniGameSounds();
		FibooGame.MANAGER.loadPersonalizacionTextures();
	}

	@Override
	public void render(final float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1f);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		stage.draw();
		
		if(FibooGame.MANAGER.update()) {
			if(!playBoton.isVisible()) {
				FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).setLooping(true);
				FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).play();
				playBoton.setVisible(true);
			}
		}
		else {
			float progress = FibooGame.MANAGER.getProgress();
			batch.begin();
			
			loaderVacio.draw(batch, width/4, height/4 - height/20, width/2, height/10);
			if(progress > 0.05f) {
				loaderFull.draw(batch, width/4, height/4 - height/20, progress*(width/2), height/10);
			}
			
			font.drawMultiLine(batch, "Cargando", width/2, height/4 + height/10, 0, BitmapFont.HAlignment.CENTER);
			batch.end();
			//Gdx.app.log(getName(), "Cargado al: " + progress + "%");
		}

		stage.act(delta);
	}

	@Override
	public void hide() {
		super.hide();
		font.dispose();
		super.dispose();
	}
	

	private boolean isFisrtGame() {
		FileHandle savedData = Gdx.files.local("savedData.json");
		if(savedData.exists()) {
			//Gdx.app.log(getName(), "Save data Existe");
			return false;
		}
		return true;
	}

}
