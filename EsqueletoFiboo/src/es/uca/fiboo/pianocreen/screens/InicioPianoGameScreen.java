package es.uca.fiboo.pianocreen.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.screens.AbstractScreen;

public class InicioPianoGameScreen extends AbstractScreen {

		private Image pantallaAyuda;
		private Image imgFondo;
		private NinePatch loaderVacio;
		private NinePatch loaderFull;
		private float w, h;
		
		public InicioPianoGameScreen(fibooGame game) {
			super(game);
			Gdx.input.setInputProcessor(stage);
			w = Gdx.graphics.getWidth();
			h = Gdx.graphics.getHeight();	
		}
		
		@Override
		public void show() {
			super.show();
			Texture fondo = fibooGame.MANAGER.get("robotgame/fondoestrellas.png", Texture.class);
			Texture ayuda = fibooGame.MANAGER.get("pianogame/pantallainiciopiano.png", Texture.class);
			fondo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			ayuda.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			
			imgFondo = new Image(fondo);
			imgFondo.setFillParent(true);
			
			pantallaAyuda = new Image(ayuda);
			pantallaAyuda.setSize(Gdx.graphics.getWidth()*0.85f, Gdx.graphics.getWidth()*0.85f);
			pantallaAyuda.setX(Gdx.graphics.getWidth()/2 - pantallaAyuda.getWidth()/2); 
	        pantallaAyuda.setY(Gdx.graphics.getHeight()/2 - pantallaAyuda.getHeight()/2);
			
	        stage.addActor(imgFondo);
			stage.addActor(pantallaAyuda);
			
			Texture vacioT = fibooGame.MANAGER.get("loading/vacio.png", Texture.class);
			Texture fullT = fibooGame.MANAGER.get("loading/full.png", Texture.class);
			loaderVacio = new NinePatch(new TextureRegion(vacioT, 24, 24), 8, 8, 8, 8);
			loaderFull = new NinePatch(new TextureRegion(fullT, 24, 24), 8, 8, 8, 8);
			
			//Cargando sonidos
			fibooGame.MANAGER.loadPianoMiniGameSounds();
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
			Gdx.app.log("InicioPianoScreen", "Cargado al: " + progress + "%");
			
			if(fibooGame.MANAGER.update()) {
				game.setScreen(new AyudaPlayPianoScreen(game));
				dispose();
			}
					
			stage.act(delta);
		}

	}
