package es.uca.fiboo.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import es.uca.fiboo.fibooGame;

public class LoadingScreen extends AbstractScreen {

	private Image bg, playBoton;
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
		fibooGame.MANAGER.load("portada/playportada.png", Texture.class);
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
		
		playBoton = new Image(fibooGame.MANAGER.get("portada/playportada.png", Texture.class));
		addListenerPlayBoton(playBoton);
		
		fibooGame.MANAGER.loadPersonalizacionScreen();
		fibooGame.MANAGER.loadSonidos();
		fibooGame.MANAGER.loadNaveminigameScreen();
		fibooGame.MANAGER.loadMarcianosminigameScreen();
		fibooGame.MANAGER.loadCameraminigameScreen();
		fibooGame.MANAGER.robotgameScreen();
		fibooGame.MANAGER.pianogameScreen();
	}

	private void addListenerPlayBoton(Image playBoton2) {
		playBoton.setX(Gdx.graphics.getWidth()/2 - playBoton.getWidth()/2); 
		playBoton.setY(Gdx.graphics.getHeight()/5 - playBoton.getHeight()/2);
		
		playBoton.addListener(new InputListener() {
		        @Override
		        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		                return true;
		        }
		        
		        @Override
		        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		                Gdx.app.log(fibooGame.LOG, "Touching up on playBoton");
		                bg.addAction(fadeOut(0.75f));
		                playBoton.addAction( sequence(fadeOut(0.75f),
		                new Action() {
		                        @Override
		                        public boolean act(float delta) {
		                                game.setScreen(new ChooseScreen(game));
		                                return true;
		                        }
		                }));
		        }
		});
		
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
		
		int rand = (int)(Math.random() * 10) % 3;
		if(rand == 0) {
			font.drawMultiLine(batch, "Cargando.. ", w/2, h/4 + h/10, 0, BitmapFont.HAlignment.CENTER);
		}
		else if(rand == 1) {
			font.drawMultiLine(batch, "Cargando...", w/2, h/4 + h/10, 0, BitmapFont.HAlignment.CENTER);
		}
		else {
			font.drawMultiLine(batch, "Cargando.  ", w/2, h/4 + h/10, 0, BitmapFont.HAlignment.CENTER);
		}
		batch.end();
		Gdx.app.log("LoadingScreen", "Cargado al: " + progress + "%");
		
		if(fibooGame.MANAGER.update()) {
			fibooGame.atlasComplementos = fibooGame.MANAGER.get("complementos/complementos.atlas", TextureAtlas.class);
			fibooGame.atlasNaveMiniGame = fibooGame.MANAGER.get("naveminigame/atlasNaveMiniGame.atlas", TextureAtlas.class);
			fibooGame.atlasMarcianosMiniGame = fibooGame.MANAGER.get("marcianosminigame/imagenesMarcianosMiniGame.txt", TextureAtlas.class);
			game.setScreen(new ChooseScreen(game));
			//stage.addActor(playBoton);
		}
		
		stage.act(delta);
	}

	@Override
	public void hide() {
		fibooGame.MANAGER.unload("portada/portadafiboo.png");
		fibooGame.MANAGER.unload("portada/playportada.png");
		fibooGame.MANAGER.unload("loading/vacio.png");
		fibooGame.MANAGER.unload("loading/full.png");
		font.dispose();
		super.dispose();
	}

}
