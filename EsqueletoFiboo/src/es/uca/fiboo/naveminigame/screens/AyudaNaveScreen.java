package es.uca.fiboo.naveminigame.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.screens.AbstractScreen;

public class AyudaNaveScreen extends AbstractScreen {

	private Image playBoton;
	private NinePatch loaderVacio;
	private NinePatch loaderFull;
	private float w, h;
	
	public AyudaNaveScreen(fibooGame game) {
		super(game);
		//fibooGame.MANAGER.get("sonidos/fondo.mp3", Sound.class).stop();
		//fibooGame.MANAGER.get("sonidos/ayuda.mp3",Sound.class).loop();
		Gdx.input.setInputProcessor(stage);
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		
		Texture vacioT = fibooGame.MANAGER.get("loading/vacio.png", Texture.class);
		Texture fullT = fibooGame.MANAGER.get("loading/full.png", Texture.class);
		loaderVacio = new NinePatch(new TextureRegion(vacioT, 24, 24), 8, 8, 8, 8);
		loaderFull = new NinePatch(new TextureRegion(fullT, 24, 24), 8, 8, 8, 8);
		
		Texture boton = fibooGame.MANAGER.get("portada/playportada.png", Texture.class); 
        boton.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        playBoton = new Image(boton);
	}
	
	@Override
	public void show() {
		super.show();
		Texture fondo = fibooGame.MANAGER.get("robotgame/fondoestrellas.png", Texture.class);
		Texture ayuda = fibooGame.MANAGER.get("naveminigame/ayuda.png", Texture.class);
		fondo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		ayuda.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		Image imgFondo = new Image(fondo);
		imgFondo.setFillParent(true);
		
		Image pantallaAyuda = new Image(ayuda);
		pantallaAyuda.setSize(Gdx.graphics.getWidth()*0.85f, Gdx.graphics.getWidth()*0.85f);
		pantallaAyuda.setX(Gdx.graphics.getWidth()/2 - pantallaAyuda.getWidth()/2); 
        pantallaAyuda.setY(Gdx.graphics.getHeight()/2 - pantallaAyuda.getHeight()/2);
		
        float botonWidth = w * 0.3f;
		float botonHeight = botonWidth;
		
		playBoton.setSize(botonWidth, botonHeight);
        playBoton.setX(w/2 - playBoton.getWidth()/2);
        playBoton.setY(h/6 - playBoton.getHeight()/2);
        playBoton.setVisible(false);
        
        playBoton.addListener(new ClickListener() {
            @Override
			public void clicked(InputEvent event, float x, float y) {
				stage.addAction(sequence(delay(0.5f), fadeOut(0.75f),
                    new Action() {
                        @Override
                        public boolean act(float delta) {
                        	dispose();
                        	setGameScreen();
                            return true;
                        }
                    }));
			}
        });
        
        stage.addActor(imgFondo);
		stage.addActor(pantallaAyuda);
		stage.addActor(playBoton);
		
		//Cargando sonidos
		fibooGame.MANAGER.loadNaveMiniGameSounds();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1f);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		stage.draw();
	
		if(fibooGame.MANAGER.update()) {
			if(!playBoton.isVisible()) {
				playBoton.setVisible(true);
			}
		}
		else {
			float progress = fibooGame.MANAGER.getProgress();
			
			batch.begin();
			loaderVacio.draw(batch, w*0.375f, h/8 - h*0.025f, w/4, h*0.05f);
			if(progress > 0.05f) {
				loaderFull.draw(batch, w*0.375f, h/8 - h*0.025f, progress*(w/4), h*0.05f);
			}
			batch.end();
			Gdx.app.log(getName(), "Cargado al: " + progress + "%");
		}
						
		stage.act(delta);
	}
	

	private void setGameScreen() {
        game.setScreen(new NaveMiniGameScreen(game));
	}

}
