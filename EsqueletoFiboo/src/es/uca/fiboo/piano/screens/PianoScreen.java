package es.uca.fiboo.piano.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import es.uca.fiboo.FibooGame;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.MenuMiniJuegosScreen;

public class PianoScreen extends AbstractScreen {

	//Piano
	private transient Sound doSound, reSound, miSound, faSound, solSound, laSound, siSound, zdoSound;
	//Trompeta
	private transient Sound dotSound, retSound, mitSound, fatSound, soltSound, latSound, sitSound, zdotSound;
	
	private transient ImageButton logopiano, logotrompeta;

	private transient int seleccion;
	private transient final float anchopiano, alturapiano;
	private transient Texture partitura;
	
	private transient final Teclas tecla;
	private transient final float avance;
	private transient float imgWidth, imgHeight;
	private transient final int notas[] = new int[]{1,1,2,1,4,3,1,1,2,1,5,4,1,1,8,6,4,4,3,2,8,8,6,4,5,4};
	private transient final int tempo[] = new int[]{3,3,4,4,4,6,3,3,4,4,4,6,3,3,4,4,3,3,4,6,2,2,4,4,4,4};
	private transient final int silen[] = new int[]{10,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1};
	private transient float time;
	private transient float timesil;
	private transient int nota;
	private transient boolean mute, isplay, ismute;
	private transient final float h, w;
	private transient int tocada;
	private transient final float posPartitura;
	private transient float posactual;
	
	public PianoScreen(final FibooGame game) {
		super(game);
		final InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(final int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
					//FibooGame.MANAGER.get("sonidos/fondo.ogg", Sound.class).loop();
					dispose();
					game.setScreen(new MenuMiniJuegosScreen(game));
				}
				return false;
			}
		}, stage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		//FibooGame.MANAGER.get("sonidos/fondo.ogg", Sound.class).stop();
		//Gdx.app.log(FibooGame.LOG, "Constructor piano empieza;");
		seleccion = 0;
		h = Gdx.graphics.getHeight();
		w = Gdx.graphics.getWidth();
		//Gdx.app.log(FibooGame.LOG, "Constructor piano ok;");
		
		alturapiano = h - h*0.68359375f;
		anchopiano = w;
		tecla = new Teclas();
		imgWidth = w * 0.18f;
		imgHeight = imgWidth;
		tocada = -1;
		mute = true;
		isplay = false;
		ismute = false;
		nota = 0;
		posPartitura = 0.545f;
		avance = w/8.66f;
		posactual = w*posPartitura - avance;
	}
	
	@Override
	public void show() {
		super.show();
		//Gdx.app.log(FibooGame.LOG, "super.show() ok;");
		//fondo = FibooGame.MANAGER.get("robotgame/fondoestrellas.png", Texture.class);
		partitura = FibooGame.MANAGER.get("pianogame/partitura.png", Texture.class);
		//fondo.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		//Gdx.app.log(FibooGame.LOG, "multiplexer ok;");
		imgWidth = (Gdx.graphics.getWidth() * 0.2f)/2f;
		imgHeight = imgWidth;
		final TextureRegion pantallaBotonRegion = new TextureRegion(FibooGame.MANAGER.get("pianogame/piano.png", Texture.class));
		final Drawable pantallaBotonDrawable = new TextureRegionDrawable(pantallaBotonRegion);
		doSound = FibooGame.MANAGER.get("pianogame/do.ogg", Sound.class);
		reSound = FibooGame.MANAGER.get("pianogame/re.ogg", Sound.class);
		miSound = FibooGame.MANAGER.get("pianogame/mi.ogg", Sound.class);
		faSound = FibooGame.MANAGER.get("pianogame/fa.ogg", Sound.class);
		solSound = FibooGame.MANAGER.get("pianogame/sol.ogg", Sound.class);
		laSound = FibooGame.MANAGER.get("pianogame/la.ogg", Sound.class);
		siSound = FibooGame.MANAGER.get("pianogame/si.ogg", Sound.class);
		zdoSound = FibooGame.MANAGER.get("pianogame/zdo.ogg", Sound.class);
		dotSound = FibooGame.MANAGER.get("pianogame/dotrom.ogg", Sound.class);
		retSound = FibooGame.MANAGER.get("pianogame/retrom.ogg", Sound.class);
		mitSound = FibooGame.MANAGER.get("pianogame/mitrom.ogg", Sound.class);
		fatSound = FibooGame.MANAGER.get("pianogame/fatrom.ogg", Sound.class);
		soltSound = FibooGame.MANAGER.get("pianogame/soltrom.ogg", Sound.class);
		latSound = FibooGame.MANAGER.get("pianogame/latrom.ogg", Sound.class);
		sitSound = FibooGame.MANAGER.get("pianogame/sitrom.ogg", Sound.class);
		zdotSound = FibooGame.MANAGER.get("pianogame/zdotrom.ogg", Sound.class);
		
		// Creamos botones, los posicionamos y los añadimos al stage
		final Image imagenPiano = new Image(pantallaBotonDrawable);
		imagenPiano.setFillParent(true);
		imagenPiano.addListener(new InputListener() {
			@Override
			public boolean touchDown(final InputEvent event, final float x, final float y, final int pointer, final int button) {
				//Gdx.app.log(FibooGame.LOG, "Touching down on " + imagenPiano.getClass().getSimpleName()+" Y: "+Gdx.input.getY()+"tres cuartos:"+alturapiano);
				final int piano = 0;
				final float click = Gdx.input.getX();
				if(Gdx.input.getY() > alturapiano) {
					if(seleccion == piano) {
						if(click < anchopiano/8){
							doSound.play();
							tocada = 0;
						}
						else if(click < (anchopiano/8)*2){
							reSound.play();
							tocada = 1;
						}
						else if(click < (anchopiano/8)*3){
							miSound.play();
							tocada = 2;
						}
						else if(click < (anchopiano/8)*4){
							faSound.play();
							tocada = 3;
						}
						else if(click < (anchopiano/8)*5){
							solSound.play();
							tocada = 4;
						}
						else if(click < (anchopiano/8)*6){
							laSound.play();
							tocada = 5;
						}
						else if(click < (anchopiano/8)*7){
							siSound.play();
							tocada = 6;
						}
						else{
							zdoSound.play();
							tocada = 7;
						}
					}
					else {
						if(click < anchopiano/8){
							dotSound.play();
							tocada = 0;
						}
						else if(click < (anchopiano/8)*2){
							retSound.play();
							tocada = 1;
						}
						else if(click < (anchopiano/8)*3){
							mitSound.play();
							tocada = 2;
						}
						else if(click < (anchopiano/8)*4){
							fatSound.play();
							tocada = 3;
						}
						else if(click < (anchopiano/8)*5){
							soltSound.play();
							tocada = 4;
						}
						else if(click < (anchopiano/8)*6){
							latSound.play();
							tocada = 5;
						}
						else if(click < (anchopiano/8)*7){
							sitSound.play();
							tocada = 6;
						}
						else{
							zdotSound.play();
							tocada = 7;
						}
					}
				}
				return true;
			}
			
			@Override
			public void touchUp(final InputEvent event, final float x, final float y, final int pointer, final int button) {
				//Gdx.app.log(FibooGame.LOG, "Touching up on " + imagenPiano.getClass().getSimpleName());
				tocada = - 1;
			}
		});
		stage.addActor(imagenPiano);
		
		final TextureRegion logopianoBotonRegion = new TextureRegion(FibooGame.MANAGER.get("pianogame/logopiano.png", Texture.class));
		final Drawable logopianoBotonDrawable = new TextureRegionDrawable(logopianoBotonRegion);
		final TextureRegion logotrompetaBotonRegion = new TextureRegion(FibooGame.MANAGER.get("pianogame/logotrompeta.png", Texture.class));
		final Drawable logotrompetaBotonDrawable = new TextureRegionDrawable(logotrompetaBotonRegion);
		logopiano = new ImageButton(logopianoBotonDrawable);
		logopiano.setSize(imgWidth, imgHeight);
		logopiano.setX(Gdx.graphics.getWidth()*0.02f); 
		logopiano.setY(Gdx.graphics.getHeight()*0.98f - imgWidth);
		logopiano.addListener(new ClickListener() {
			@Override
			public void clicked(final InputEvent event, final float x, final float y) {
				//Gdx.app.log(FibooGame.LOG, "Touching up on " + logopiano.getClass().getSimpleName());
				seleccion = 0;
			}
		});
		stage.addActor(logopiano);
		
		logotrompeta = new ImageButton(logotrompetaBotonDrawable);
		logotrompeta.setSize(imgWidth, imgHeight);
		logotrompeta.setX(Gdx.graphics.getWidth()*0.02f);
		logotrompeta.setY(Gdx.graphics.getHeight()*0.83f - imgWidth);
		logotrompeta.addListener(new ClickListener() {
			@Override
			public void clicked(final InputEvent event, final float x, final float y) {
				//Gdx.app.log(FibooGame.LOG, "Touching up on " + logotrompeta.getClass().getSimpleName());
				seleccion = 1;
			}
		});
		stage.addActor(logotrompeta);
		
		final TextureRegion playBotonRegion = new TextureRegion(FibooGame.MANAGER.get("pianogame/play.png", Texture.class));
		final Drawable playBotonDrawable = new TextureRegionDrawable(playBotonRegion);
		final TextureRegion stopBotonRegion = new TextureRegion(FibooGame.MANAGER.get("pianogame/stop.png", Texture.class));
		final Drawable stopBotonDrawable = new TextureRegionDrawable(stopBotonRegion);
		final TextureRegion pauseBotonRegion = new TextureRegion(FibooGame.MANAGER.get("pianogame/pause.png", Texture.class));
		final Drawable pauseBotonDrawable = new TextureRegionDrawable(pauseBotonRegion);
		final TextureRegion sonidoBotonRegion = new TextureRegion(FibooGame.MANAGER.get("pianogame/sonido.png", Texture.class));
		final Drawable sonidoBotonDrawable = new TextureRegionDrawable(sonidoBotonRegion);
		final TextureRegion silencioBotonRegion = new TextureRegion(FibooGame.MANAGER.get("pianogame/mute.png", Texture.class));
		final Drawable silencioBotonDrawable = new TextureRegionDrawable(silencioBotonRegion);
		
		final ImageButton play = new ImageButton(playBotonDrawable);
		play.setSize(w*0.08f, w*0.08f);
		play.setPosition(w*0.24f, h*0.7f);
		play.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, final float x, final float y) {
				//Gdx.app.log(FibooGame.LOG, "Touching up on " + play.getClass().getSimpleName());
				isplay = true;
			}
		});
		stage.addActor(play);
		
		final ImageButton stop = new ImageButton(stopBotonDrawable);
		stop.setSize(w*0.08f, w*0.08f);
		stop.setPosition(w*0.34f,  h*0.7f);
		stop.addListener(new ClickListener() {			
			@Override
			public void clicked(final InputEvent event, final float x, final float y) {
				Gdx.app.log(FibooGame.LOG, "Touching up on " + stop.getClass().getSimpleName());
				isplay = false;
				nota = 0;
				time = 0;
				timesil = 0;
				mute = true;
				posactual = w*posPartitura - avance;
			}
		});
		stage.addActor(stop);
		
		final ImageButton pause = new ImageButton(pauseBotonDrawable);
		pause.setSize(w*0.08f, w*0.08f);
		pause.setPosition(w*0.44f, h*0.7f);
		pause.addListener(new ClickListener() {
			@Override
			public void clicked(final InputEvent event, final float x, final float y) {
				//Gdx.app.log(FibooGame.LOG, "Touching up on " + pause.getClass().getSimpleName());
				isplay = false;
			}
		});
		stage.addActor(pause);
		
		final ImageButton sonido = new ImageButton(sonidoBotonDrawable);
		sonido.setSize(w*0.08f, w*0.08f);
		sonido.setPosition(w*0.54f, h*0.7f);
		sonido.addListener(new ClickListener() {
			@Override
			public void clicked(final InputEvent event, final float x, final float y) {
				//Gdx.app.log(FibooGame.LOG, "Touching up on " + sonido.getClass().getSimpleName());
				ismute = false;
			}
		});
		stage.addActor(sonido);
		
		final ImageButton silencio = new ImageButton(silencioBotonDrawable);
		silencio.setSize(w*0.08f, w*0.08f);
		silencio.setPosition(w*0.64f,  h*0.7f);
		silencio.addListener(new ClickListener() {			
			@Override
			public void clicked(final InputEvent event, final float x, final float y) {
				//Gdx.app.log(FibooGame.LOG, "Touching up on " + silencio.getClass().getSimpleName());
				ismute = true;
			}
		});
		stage.addActor(silencio);
		
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
		batch.begin();
		//batch.draw(fondo, 0, 0, w,h);
		
		batch.draw(partitura, posactual, h*0.77f, w*3f, h*0.26f);
		
		//posPartitura-= 0.0001f;
		int piano = 0;
		if(isplay) {
			if(mute) {			
				timesil += 0.1;
				if(timesil > silen[nota]) {
					mute = false;
					timesil = 0;
					
					if(!ismute){
						if(seleccion == piano){
							switch(notas[nota]) {
								case 1: doSound.play();break;
								case 2: reSound.play();break;
								case 3:	miSound.play();break;
								case 4:	faSound.play();break;
								case 5:	solSound.play();break;
								case 6:	laSound.play();break;
								case 7:	siSound.play();break;
								case 8:	zdoSound.play();break;
								default: break;
							}
						}
						else {
							switch(notas[nota]){
								case 1: dotSound.play();break;
								case 2: retSound.play();break;
								case 3:	mitSound.play();break;
								case 4:	fatSound.play();break;
								case 5:	soltSound.play();break;
								case 6:	latSound.play();break;
								case 7:	sitSound.play();break;
								case 8:	zdotSound.play();break;
								default: break;
							}
						}
					}
				}
			}
			else {
				time += 0.1;
				if(time > tempo[nota]) {
					time = 0;
					mute = true;
					nota =(nota+1)%26;
					if (nota == 0) {
						posactual = w*posPartitura - avance;
					}
					else {
						posactual -= avance;
					}
				}
			}
		}
		
		batch.end();
		stage.act();
		stage.draw();
		batch.begin();
		if(tocada >= 0){
			//timetocada+= 0.1; 
			switch(tocada){ 
				case 0: batch.draw(tecla.tecla(tocada), 0, 0, w/8,h);
					break;
				case 1: batch.draw(tecla.tecla(tocada), anchopiano/8, 0, w/8, h);
					break;
				case 2: batch.draw(tecla.tecla(tocada), (anchopiano/8)*2, 0, w/8,h);
					break;
				case 3: batch.draw(tecla.tecla(tocada), (anchopiano/8)*3, 0, w/8,h);
					break;
				case 4: batch.draw(tecla.tecla(tocada), (anchopiano/8)*4, 0, w/8,h);
					break;
				case 5: batch.draw(tecla.tecla(tocada), (anchopiano/8)*5, 0, w/8,h);
					break;
				case 6: batch.draw(tecla.tecla(tocada), (anchopiano/8)*6, 0, w/8,h);
					break;
				case 7: batch.draw(tecla.tecla(tocada), (anchopiano/8)*7, 0, w/8,h);
					break;
				default: break;
			}
		}
		/*if(timetocada>=1){
			tocada= -1;
			timetocada= 0;
		}*/
		batch.end();
		
		
		/*logotrompeta.setSize(imgWidth, imgHeight);
		logotrompeta.setPosition(Gdx.graphics.getWidth()*0.02f, 
				Gdx.graphics.getHeight()*0.83f- imgWidth);
		*/
		if (seleccion == 0) {
			logopiano.setSize(imgWidth*1.3f, imgHeight*1.3f);
			logopiano.setPosition(w*0.11f - imgWidth, h*0.93f - imgHeight);
			logotrompeta.setSize(imgWidth, imgHeight);
			logotrompeta.setPosition(w*0.24f - imgWidth, h*0.93f - imgHeight);		
		}
		else {
			logopiano.setSize(imgWidth, imgHeight);
			logopiano.setPosition(w*0.11f - imgWidth, h*0.93f - imgHeight);
			logotrompeta.setSize(imgWidth*1.3f, imgHeight*1.3f);
			logotrompeta.setPosition(w*0.24f - imgWidth, h*0.93f - imgHeight);
		}
		//stage.act();
		//stage.draw();
	}
	
	@Override
	public void dispose() {
		FibooGame.MANAGER.unloadPianoMiniGameSounds();
		FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).setLooping(true);
		FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).play();
		super.dispose();
	}

	private class Teclas {
		private static final int FRAME_COLS = 8;
		private transient final Texture teclado;
		private transient final TextureRegion[] teclas;
		
		public Teclas(){
			teclado = FibooGame.MANAGER.get("pianogame/teclas.png", Texture.class);   
			final TextureRegion[][] tmp = TextureRegion.split(teclado, teclado.getWidth() / FRAME_COLS, teclado.getHeight()); 
			teclas = new TextureRegion[FRAME_COLS];
			int index = 0;
			for (int i = 0; i < FRAME_COLS; i++) {
				teclas[index++] = tmp[0][i];
			}
		}
		
		public TextureRegion tecla(final int n) {
			return teclas[n];
		}
	}
}
