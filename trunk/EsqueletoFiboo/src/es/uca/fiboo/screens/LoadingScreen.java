package es.uca.fiboo.screens;

import com.badlogic.gdx.Gdx;
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
		
		fibooGame.MANAGER.loadPersonalizacionScreen();
		fibooGame.MANAGER.loadSonidos();
		fibooGame.MANAGER.loadNaveminigameScreen();
		fibooGame.MANAGER.loadMarcianosminigameScreen();
		fibooGame.MANAGER.loadCameraminigameScreen();
		fibooGame.MANAGER.loadRobotGameScreen();
		fibooGame.MANAGER.loadPianoGameScreen();
		fibooGame.MANAGER.loadAscensorGameScreen();
	}

	@Override
	public void render(float delta) {
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
			fibooGame.atlasNaveMiniGame = fibooGame.MANAGER.get("naveminigame/atlasNaveMiniGame.atlas", TextureAtlas.class);
			fibooGame.atlasMarcianosMiniGame = fibooGame.MANAGER.get("marcianosminigame/imagenesMarcianosMiniGame.txt", TextureAtlas.class);
			game.setScreen(new ChooseScreen(game));
		}
		
		stage.act(delta);
	}

	@Override
	public void hide() {
		fibooGame.MANAGER.unload("portada/portadafiboo.png");
		fibooGame.MANAGER.unload("loading/vacio.png");
		fibooGame.MANAGER.unload("loading/full.png");
		font.dispose();
		super.dispose();
	}

}
