package es.uca.fiboo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import es.uca.fiboo.fibooGame;

public class LoadingScreen extends AbstractScreen {

	private Image bg;
	private BitmapFont font;
	private NinePatch loaderVacio;
	private NinePatch loaderFull;
	private float w, h;
	
	public LoadingScreen(fibooGame game) {
		super(game);
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
	}
	
	@Override
	public void show() {
		super.show();
		
		fibooGame.MANAGER.load("portada/portadafiboo.png", Texture.class);
		fibooGame.MANAGER.load("loading/vacio.png", Texture.class);
		fibooGame.MANAGER.load("loading/full.png", Texture.class);
		fibooGame.MANAGER.finishLoading();
		
		font = new BitmapFont();
		Texture vacioT = fibooGame.MANAGER.get("loading/vacio.png", Texture.class);
		Texture fullT = fibooGame.MANAGER.get("loading/full.png", Texture.class);
		loaderVacio = new NinePatch(new TextureRegion(vacioT, 24, 24), 8, 8, 8, 8);
		loaderFull = new NinePatch(new TextureRegion(fullT, 24, 24), 8, 8, 8, 8);
		
		Texture bgT = fibooGame.MANAGER.get("portada/portadafiboo.png", Texture.class);
		bgT.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		bg = new Image(bgT);
		bg.setFillParent(true);
		stage.addActor(bg);

		//fibooGame.MANAGER.loadMusicaFondo();
		//fibooGame.MANAGER.finishLoading();
		//fibooGame.MANAGER.loadSonidos();
		fibooGame.MANAGER.loadMarcianosMiniGameTextures();
		fibooGame.MANAGER.loadCameraMiniGameTextures();
		fibooGame.MANAGER.loadRobotMiniGameTextures();
		fibooGame.MANAGER.loadPianoMiniGameTextures();
		fibooGame.MANAGER.loadNaveMiniGameTextures();
		fibooGame.MANAGER.loadMenuTextures();
		fibooGame.MANAGER.loadSacoMiniGameTextures();
		fibooGame.MANAGER.loadPersonalizacionTextures();
		fibooGame.MANAGER.loadAscensorGameTextures();
		//fibooGame.MANAGER.get("sonidos/fondo.mp3",Sound.class).loop();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.draw();
		
		float progress = fibooGame.MANAGER.getProgress();
		batch.begin();
		
		loaderVacio.draw(batch, w/4, h/4 - h/20, w/2, h/10);
		if(progress > 0.05f) {
			loaderFull.draw(batch, w/4, h/4 - h/20, progress*(w/2), h/10);
		}
		
		font.drawMultiLine(batch, "Cargando", w/2, h/4 + h/10, 0, BitmapFont.HAlignment.CENTER);
		batch.end();
		Gdx.app.log("LoadingScreen", "Cargado al: " + progress + "%");
		
		if(fibooGame.MANAGER.update()) {
			fibooGame.atlasComplementos = fibooGame.MANAGER.get("complementos/complementos.atlas", TextureAtlas.class);
			game.setScreen(new StartScreen(game));
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
