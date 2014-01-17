package es.uca.fiboo.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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

import es.uca.fiboo.fibooGame;

public class LoadingScreen extends AbstractScreen {

	private Image playBoton;
	private BitmapFont font;
	private NinePatch loaderVacio;
	private NinePatch loaderFull;
	private float w, h;
	
	public LoadingScreen(fibooGame game) {
		super(game);
		Gdx.input.setInputProcessor(stage);
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
	}
	
	@Override
	public void show() {
		super.show();
		
		fibooGame.MANAGER.load("portada/portadafiboo.png", Texture.class);
		fibooGame.MANAGER.load("portada/playportada.png", Texture.class);
		fibooGame.MANAGER.load("loading/vacio.png", Texture.class);
		fibooGame.MANAGER.load("loading/full.png", Texture.class);
		fibooGame.MANAGER.finishLoading();
		
		//Barra de carga y texto cargando...
		font = new BitmapFont();
		Texture vacioT = fibooGame.MANAGER.get("loading/vacio.png", Texture.class);
		Texture fullT = fibooGame.MANAGER.get("loading/full.png", Texture.class);
		loaderVacio = new NinePatch(new TextureRegion(vacioT, 24, 24), 8, 8, 8, 8);
		loaderFull = new NinePatch(new TextureRegion(fullT, 24, 24), 8, 8, 8, 8);
		
		//Fondo de pantalla
		Texture bgT = fibooGame.MANAGER.get("portada/portadafiboo.png", Texture.class);
		bgT.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image bg = new Image(bgT);
		bg.setFillParent(true);
		
		//Botón iniciar juego
		Texture boton = fibooGame.MANAGER.get("portada/playportada.png", Texture.class); 
        boton.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        playBoton = new Image(boton);
        playBoton.setVisible(false);
        float botonWidth = w * 0.3f;
		float botonHeight = botonWidth;
		
		playBoton.setSize(botonWidth, botonHeight);
        playBoton.setX(w/2 - playBoton.getWidth()/2);
        playBoton.setY(h/4 - playBoton.getHeight()/2);
        
        playBoton.addListener(new ClickListener() {
            @Override
			public void clicked(InputEvent event, float x, float y) {
				stage.addAction(sequence(delay(0.5f), fadeOut(0.75f),
                    new Action() {
                        @Override
                        public boolean act(float delta) {
                            game.setScreen(new ChooseScreen(game));
                            return true;
                        }
                    }));
			}
        });

		stage.addActor(bg);
		stage.addActor(playBoton);
		
		// Carga de Assets
		//fibooGame.MANAGER.loadMusicaFondo();
		//fibooGame.MANAGER.finishLoading();
		//fibooGame.MANAGER.loadSonidos();
		fibooGame.MANAGER.loadCameraMiniGameTextures();
		//fibooGame.MANAGER.loadRobotMiniGameTextures();
		fibooGame.MANAGER.loadPianoMiniGameTextures();
		fibooGame.MANAGER.loadNaveMiniGameTextures();
		fibooGame.MANAGER.loadMenuTextures();
		fibooGame.MANAGER.loadRobotMiniGameSounds();
		fibooGame.MANAGER.loadSacoMiniGameTextures();
		fibooGame.MANAGER.loadMarcianosMiniGameSounds();
		fibooGame.MANAGER.loadPersonalizacionTextures();
		fibooGame.MANAGER.loadAscensorGameTextures();
		//fibooGame.MANAGER.get("sonidos/fondo.mp3",Sound.class).loop();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1f);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		stage.draw();
		
		if(fibooGame.MANAGER.update()) {
			fibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).setLooping(true);
			fibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).play();
			if(!playBoton.isVisible())
				playBoton.setVisible(true);
		}
		else {
			float progress = fibooGame.MANAGER.getProgress();
			batch.begin();
			
			loaderVacio.draw(batch, w/4, h/4 - h/20, w/2, h/10);
			if(progress > 0.05f) {
				loaderFull.draw(batch, w/4, h/4 - h/20, progress*(w/2), h/10);
			}
			
			font.drawMultiLine(batch, "Cargando", w/2, h/4 + h/10, 0, BitmapFont.HAlignment.CENTER);
			batch.end();
			Gdx.app.log(getName(), "Cargado al: " + progress + "%");
		}

		stage.act(delta);
	}

	@Override
	public void hide() {
		super.hide();
		font.dispose();
		super.dispose();
	}

}
