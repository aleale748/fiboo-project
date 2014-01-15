package es.uca.fiboo.naveminigame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.screens.AbstractScreen;

public class AyudaNaveScreen extends AbstractScreen {

	private Image fondo, ayuda;
	private NinePatch loaderVacio;
	private NinePatch loaderFull;
	private float w, h;
	
	public AyudaNaveScreen(fibooGame game) {
		super(game);
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
	}
	
	@Override
	public void show() {
		//fibooGame.MANAGER.get("sonidos/fondo.mp3", Sound.class).stop();
		//fibooGame.MANAGER.get("sonidos/ayuda.mp3",Sound.class).loop();
		
		Texture imgAyuda = fibooGame.MANAGER.get("naveminigame/ayuda.png", Texture.class);
		Texture imgFondo = fibooGame.MANAGER.get("robotgame/fondoestrellas.png", Texture.class);
		imgAyuda.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		imgFondo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		ayuda = new Image(imgAyuda);
		fondo = new Image(imgFondo);
		ayuda.setFillParent(true);
		fondo.setFillParent(true);
		
		Texture vacioT = fibooGame.MANAGER.get("loading/vacio.png", Texture.class);
		Texture fullT = fibooGame.MANAGER.get("loading/full.png", Texture.class);
		loaderVacio = new NinePatch(new TextureRegion(vacioT, 24, 24), 8, 8, 8, 8);
		loaderFull = new NinePatch(new TextureRegion(fullT, 24, 24), 8, 8, 8, 8);
		
		stage.addActor(fondo);
		stage.addActor(ayuda);
		
		//Cargando sonidos
		fibooGame.MANAGER.loadNaveMiniGameSounds();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.draw();
	
		float progress = fibooGame.MANAGER.getProgress();
		batch.begin();
		
		loaderVacio.draw(batch, w*0.375f, h/7 - h*0.025f, w/4, h*0.05f);
		if(progress > 0.05f) {
			loaderFull.draw(batch, w*0.375f, h/7 - h*0.025f, progress*(w/4), h*0.05f);
		}
		
		batch.end();
		Gdx.app.log("InicioNaveScreen", "Cargado al: " + progress + "%");
		
		if(fibooGame.MANAGER.update()) {
			game.setScreen(new NaveMiniGameScreen(game));
			dispose();
		}
				
		stage.act(delta);
	}
	
}
