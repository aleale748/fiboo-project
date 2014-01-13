package es.uca.fiboo.pianocreen.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.MenuMiniJuegosScreen;
import es.uca.fiboo.screens.PruebaComplementosScreen;

public class PianoScreen extends AbstractScreen {
	Sound doSound;
	Sound reSound;
	Sound miSound;
	Sound faSound;
	Sound solSound;
	Sound laSound;
	Sound siSound;
	Sound zdoSound;
	Sound dotSound;
	Sound retSound;
	Sound mitSound;
	Sound fatSound;
	Sound soltSound;
	Sound latSound;
	Sound sitSound;
	Sound zdotSound;
	private ImageButton logopiano, logotrompeta;
	int seleccion;
	float escala;
	private Image imagenPiano;
	private Image imgFondo;
	public PianoScreen(fibooGame game) {
		super(game);
		Gdx.app.log(fibooGame.LOG, "Constructor piano empieza;");
		seleccion= 0;
		Gdx.app.log(fibooGame.LOG, "Constructor piano ok;");
	}
	
	@Override
	public void show() {
		super.show();
		Gdx.app.log(fibooGame.LOG, "super.show() ok;");
		imgFondo = new Image(fibooGame.MANAGER.get("naveminigame/fondonave.png", Texture.class));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);
		escala= Gdx.graphics.getWidth();
		InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
					dispose();
					game.setScreen(new MenuMiniJuegosScreen(game));
				}
				return false;
			}
		}, stage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
		Gdx.app.log(fibooGame.LOG, "multiplexer ok;");
		float imgWidth = (Gdx.graphics.getWidth() * 0.2f)/2f;
		float imgHeight = imgWidth;
		TextureRegion pantallaBotonRegion = new TextureRegion(fibooGame.MANAGER.get("pianogame/piano.png", Texture.class));
		Drawable pantallaBotonDrawable = new TextureRegionDrawable(pantallaBotonRegion);
		doSound = fibooGame.MANAGER.get("pianogame/do.mp3", Sound.class);
		reSound = fibooGame.MANAGER.get("pianogame/re.mp3", Sound.class);
		miSound = fibooGame.MANAGER.get("pianogame/mi.mp3", Sound.class);
		faSound = fibooGame.MANAGER.get("pianogame/fa.mp3", Sound.class);
		solSound = fibooGame.MANAGER.get("pianogame/sol.mp3", Sound.class);
		laSound = fibooGame.MANAGER.get("pianogame/la.mp3", Sound.class);
		siSound = fibooGame.MANAGER.get("pianogame/si.mp3", Sound.class);
		zdoSound = fibooGame.MANAGER.get("pianogame/zdo.mp3", Sound.class);
		dotSound = fibooGame.MANAGER.get("pianogame/dotrom.mp3", Sound.class);
		retSound = fibooGame.MANAGER.get("pianogame/retrom.mp3", Sound.class);
		mitSound = fibooGame.MANAGER.get("pianogame/mitrom.mp3", Sound.class);
		fatSound = fibooGame.MANAGER.get("pianogame/fatrom.mp3", Sound.class);
		soltSound = fibooGame.MANAGER.get("pianogame/soltrom.mp3", Sound.class);
		latSound = fibooGame.MANAGER.get("pianogame/latrom.mp3", Sound.class);
		sitSound = fibooGame.MANAGER.get("pianogame/sitrom.mp3", Sound.class);
		zdotSound = fibooGame.MANAGER.get("pianogame/zdotrom.mp3", Sound.class);
		// Creamos botones, los posicionamos y los a??adimos al stage
		
		imagenPiano = new Image(pantallaBotonDrawable);
		imagenPiano.setFillParent(true);
		imagenPiano.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on " + imagenPiano.getClass().getSimpleName());
				float click= Gdx.input.getX();
				if(seleccion==0){
					if(click<escala/8)
						doSound.play();
					else if(click<(escala/8)*2)
						reSound.play();
					else if(click<(escala/8)*3)
						miSound.play();
					else if(click<(escala/8)*4)
						faSound.play();
					else if(click<(escala/8)*5)
						solSound.play();
					else if(click<(escala/8)*6)
						laSound.play();
					else if(click<(escala/8)*7)
						siSound.play();
					else
						zdoSound.play();
					}
				else{
					if(click<escala/8)
						dotSound.play();
					else if(click<(escala/8)*2)
						retSound.play();
					else if(click<(escala/8)*3)
						mitSound.play();
					else if(click<(escala/8)*4)
						fatSound.play();
					else if(click<(escala/8)*5)
						soltSound.play();
					else if(click<(escala/8)*6)
						latSound.play();
					else if(click<(escala/8)*7)
						sitSound.play();
					else
						zdotSound.play();
				}
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + imagenPiano.getClass().getSimpleName());
				}
		});
		stage.addActor(imagenPiano);
		TextureRegion logopianoBotonRegion = new TextureRegion(new Texture(Gdx.files.internal("pianogame/logopiano.png")));
		Drawable logopianoBotonDrawable = new TextureRegionDrawable(logopianoBotonRegion);
		TextureRegion logotrompetaBotonRegion = new TextureRegion(new Texture(Gdx.files.internal("pianogame/logotrompeta.png")));
		Drawable logotrompetaBotonDrawable = new TextureRegionDrawable(logotrompetaBotonRegion);
		logopiano = new ImageButton(logopianoBotonDrawable);
		logopiano.setSize(imgWidth, imgHeight);
		logopiano.setPosition(Gdx.graphics.getWidth()*0.03f, 
				Gdx.graphics.getHeight()*0.9f- imgWidth);
		logopiano.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on " + logopiano.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + logopiano.getClass().getSimpleName());
						seleccion= 0;
				}
		});
		stage.addActor(logopiano);
		logotrompeta = new ImageButton(logotrompetaBotonDrawable);
		logotrompeta.setSize(imgWidth, imgHeight);
		logotrompeta.setPosition(Gdx.graphics.getWidth()*0.03f +imgWidth*1.03f, 
				Gdx.graphics.getHeight()*0.9f- imgWidth);
		logotrompeta.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on " + logotrompeta.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + logotrompeta.getClass().getSimpleName());
						seleccion= 1;
				}
		});
		stage.addActor(logotrompeta);
		
		stage.act();
		stage.draw();
	}

}
