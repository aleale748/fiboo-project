package es.uca.fiboo.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import es.uca.fiboo.FibooGame;

/**
 * @author Sergio
 *
 */

public abstract class AbstractLoadingScreen extends AbstractScreen {

	private transient final Image playBoton;
	private transient final NinePatch loaderVacio;
	private transient final NinePatch loaderFull;
	private transient final float width, height;
	
	public AbstractLoadingScreen(final FibooGame game) {
		super(game);
		
		Gdx.input.setInputProcessor(stage);
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		Texture vacioT = FibooGame.MANAGER.get("loading/vacio.png", Texture.class);
		Texture fullT = FibooGame.MANAGER.get("loading/full.png", Texture.class);
		loaderVacio = new NinePatch(new TextureRegion(vacioT, 24, 24), 8, 8, 8, 8);
		loaderFull = new NinePatch(new TextureRegion(fullT, 24, 24), 8, 8, 8, 8);
		
		Texture boton = FibooGame.MANAGER.get("portada/playportada.png", Texture.class); 
        boton.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        playBoton = new Image(boton);
	}
	
	@Override
	public void show() {
		super.show();
		Texture fondo = FibooGame.MANAGER.get("robotgame/fondoestrellas.png", Texture.class);
		Texture ayuda = FibooGame.MANAGER.get(getImagenFondo(), Texture.class);
		fondo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		ayuda.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		Image imgFondo = new Image(fondo);
		imgFondo.setFillParent(true);
		
		Image pantallaAyuda = new Image(ayuda);
		pantallaAyuda.setSize(Gdx.graphics.getWidth()*0.85f, Gdx.graphics.getWidth()*0.85f);
		pantallaAyuda.setX(Gdx.graphics.getWidth()/2 - pantallaAyuda.getWidth()/2); 
        pantallaAyuda.setY(Gdx.graphics.getHeight()/2 - pantallaAyuda.getHeight()/2);
		
        float botonWidth = width * 0.3f;
		float botonHeight = botonWidth;

		playBoton.setSize(botonWidth, botonHeight);
        playBoton.setX(width/1.1f - playBoton.getWidth()/2);
        playBoton.setY(height/6 - playBoton.getHeight()/2);
        playBoton.setVisible(false);
        
        playBoton.addListener(new ClickListener() {
            @Override
			public void clicked(final InputEvent event, final float x, final float y) {
				stage.addAction(sequence(delay(0.5f), fadeOut(0.75f),
                    new Action() {
                        @Override
                        public boolean act(final float delta) {
                        	dispose();
                			FibooGame.MANAGER.get("sonidos/ayuda.ogg", Music.class).stop();
                        	setGameScreen();
                            return true;
                        }
                    }));
			}
        });
        
        stage.addActor(imgFondo);
		stage.addActor(pantallaAyuda);
		stage.addActor(playBoton);
		
		//Cargando Assets
		loadAssets();
	}

	@Override
	public void render(final float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1f);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		stage.draw();
	
		if(FibooGame.MANAGER.update()) {
			if(!playBoton.isVisible()) {
				playBoton.setVisible(true);
			}
		}
		else {
			float progress = FibooGame.MANAGER.getProgress();
			
			batch.begin();
			loaderVacio.draw(batch, width*0.375f, height/8 - height*0.025f, width/4, height*0.05f);
			if(progress > 0.05f) {
				loaderFull.draw(batch, width*0.375f, height/8 - height*0.025f, progress*(width/4), height*0.05f);
			}
			batch.end();
			//Gdx.app.log(getName(), "Cargado al: " + progress + "%");
		}
						
		stage.act(delta);
	}
	
	/*
	 * Screen a la que redirigir� la pantalla
	 */
	public abstract void setGameScreen();
	
	/*
	 * Assets que se deben cargar para la pantalla siguiente
	 */
	public abstract void loadAssets();

	/*
	 * Imagen de ayuda que se muestra mientras carga
	 */
	public abstract String getImagenFondo();
}
