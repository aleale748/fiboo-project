package es.uca.fiboo.tallerminigame.screens;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import es.uca.fiboo.FibooGame;
import es.uca.fiboo.naveminigame.actors.StarActor;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.MenuMiniJuegosScreen;

public class TallerScreen extends AbstractScreen {
	private transient final List<Texture> tornillosUsados;
	private transient final List<Boolean> tornillosTocados;
	private transient final List<Rectangle> posTornillos;
	private transient final List<ImageButton> tornillosBotones;
	private transient final List<Texture> tornillos;
	private transient final Texture robot_triste;
	private transient final Texture robot_alegre;
	private transient final Texture robot_normal;
	private transient final Sound robot;
	private transient int tornilloActual;
	private transient final Rectangle barrigaRobot;
	private transient final List<Texture> tornillosElegidos;
	private transient final int numTornillos;
	private transient final Sound bien;
	private transient final Sound mal;	
	private transient boolean overlaps;
	
	public TallerScreen(final FibooGame game, final List<Texture> tornillos, final List<Texture> tornillosUsados) {	
		super(game);

		this.tornillosUsados = tornillosUsados;
		this.tornillos = tornillos;
		Gdx.input.setInputProcessor(stage);
		final float width = Gdx.graphics.getWidth();
		final float hight = Gdx.graphics.getHeight();
		tornillosTocados = new ArrayList<Boolean>();
		robot = FibooGame.MANAGER.get("sacominigame/robot.ogg", Sound.class);
		posTornillos = new ArrayList<Rectangle>();
		barrigaRobot = new Rectangle();
		tornillosBotones = new ArrayList<ImageButton>();
		tornillosElegidos = new ArrayList<Texture>();
		numTornillos = tornillosUsados.size();
		tornilloActual = 0;
		overlaps = false;
		
		//Inicializamos los objetos de los ArrayList
		
		for(int i=0; i<tornillos.size(); i++) {
			posTornillos.add(i, new Rectangle());
			tornillosTocados.add(i, false);
		}

		//Se cargan las imagenes necesarias
		robot_triste = FibooGame.MANAGER.get("sacominigame/robottriste.png", Texture.class);
		robot_triste.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		robot_alegre = FibooGame.MANAGER.get("sacominigame/robotalegre.png", Texture.class);
		robot_alegre.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		robot_normal = FibooGame.MANAGER.get("sacominigame/robottallerneutro.png", Texture.class);
		robot_normal.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		bien = FibooGame.MANAGER.get("sonidos/bien.ogg", Sound.class);
		mal = FibooGame.MANAGER.get("sonidos/nono.ogg", Sound.class);
		
		//Situamos la barriga del robot	
		barrigaRobot.height = hight/3.3f;
		barrigaRobot.width = width/5;
		barrigaRobot.x = width/1.9f;
		barrigaRobot.y = hight/5.3f;
			
		//Crear botones y asignar su valor
		
		for(int i=0; i<tornillos.size(); i++) {
			tornillosBotones.add(i, new ImageButton(new TextureRegionDrawable(new TextureRegion(tornillos.get(i)))));
			tornilloActual = i;
			tornillosBotones.get(i).addListener(new InputListener() {
				@Override
				public boolean touchDown(final InputEvent event,final float posX, final float posY, final int pointer, final int button) {
					return true;
				}
				@Override
				public void touchUp(final InputEvent event, final float posX, final float posY, final int pointer,final int button) {
					if(posTornillos.get(tornilloActual).overlaps(barrigaRobot)) {
						overlaps = true;
					}				
					else {
						overlaps = false;
						tornillosTocados.set(tornilloActual, false);
					}
					
				}
				
			});
		}
		
		//Posicionamos todos los tornillos y les asignamos su tama�o
		
		for(int i=0; i<tornillos.size(); i++) {
			tornillosBotones.get(i).setHeight(hight/6f);
			tornillosBotones.get(i).setWidth(width/6f);
		}
		
		tornillosBotones.get(0).setPosition(width/11f, hight/1.46f);
		tornillosBotones.get(1).setPosition(width/4.9f, hight/1.46f);
		tornillosBotones.get(2).setPosition(width/3.2f, hight/1.46f);
		tornillosBotones.get(3).setPosition(width/11f, hight/1.9f);
		tornillosBotones.get(4).setPosition(width/4.9f, hight/1.9f);
		tornillosBotones.get(5).setPosition(width/3.2f, hight/1.9f);
		tornillosBotones.get(6).setPosition(width/11f, hight/2.6f);
		tornillosBotones.get(7).setPosition(width/4.9f, hight/2.6f);
		tornillosBotones.get(8).setPosition(width/3.2f, hight/2.6f);
		
		
		//A�adimos todos los botones al stage		
		for(int i=0; i<tornillos.size(); i++) {
			stage.addActor(tornillosBotones.get(i));
		}
			
		
		for (int i = 0; i < 4; ++i) {
			stage.addActor(TallerScreenPrincipal.SINPUNTOS.get(i));
		}
		
		if(!TallerScreenPrincipal.puntos.isEmpty()) { //Si hay algun punto que lo muestre
			for (int i = 0; i < TallerScreenPrincipal.puntos.size(); ++i) {
				stage.addActor(TallerScreenPrincipal.puntos.get(i));
			}
		}		
	}

	@Override
	public void render(final float delta) {
		
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		final float width = Gdx.graphics.getWidth();
		final float hight = Gdx.graphics.getHeight();
		
		batch.begin();
		
		if(overlaps) {
		
			robot.play();
			tornillosTocados.set(tornilloActual, false);
			if(tornillosElegidos.size() < 4) {
				tornillosElegidos.add(tornillos.get(tornilloActual));
				if(tornillosUsados.contains(tornillos.get(tornilloActual))) { //Si lo ha puesto lo borra ya
					tornillosUsados.remove(tornillos.get(tornilloActual));
				}
			}
			overlaps = false;
		}
		
		if(tornillosElegidos.size() == numTornillos && tornillosUsados.isEmpty()) { //Ha acertado
			batch.draw(robot_alegre, 0, 0, width, hight);
			bien.play();
					
			TallerScreenPrincipal.aciertos++; //Sumamos el acierto actual
			
			final float widthPuntuacion = 42;
			final float heightPuntuacion = 42;
			
			TallerScreenPrincipal.puntos.add(new StarActor());
			TallerScreenPrincipal.puntos.get(TallerScreenPrincipal.puntos.size() - 1).setPosition(widthPuntuacion*0.2f + (TallerScreenPrincipal.puntos.size() - 1) * widthPuntuacion*1.1f, Gdx.graphics.getHeight() - heightPuntuacion*1.1f);
			TallerScreenPrincipal.puntos.get(TallerScreenPrincipal.puntos.size() - 1).setWidth(widthPuntuacion);
			TallerScreenPrincipal.puntos.get(TallerScreenPrincipal.puntos.size() - 1).setHeight(heightPuntuacion);
			stage.addActor(TallerScreenPrincipal.puntos.get(TallerScreenPrincipal.puntos.size() - 1));
			
			//dispose();
			game.setScreen(new TiempoScreen(game,1)); //Lanzamos el siguiente intento
		}		
		else if(tornillosElegidos.size() == numTornillos && !tornillosUsados.isEmpty()) {
			batch.draw(robot_triste, 0, 0, width, hight);
			mal.play();
			
			TallerScreenPrincipal.fallos++; //Sumamos el fallo actual
			//dispose();
			game.setScreen(new TiempoScreen(game,0)); //Lanzamos el siguiente intento
		}		
		else {
			batch.draw(robot_normal, 0, 0, width, hight);
		
		//Comprobamos si alguno de los botones ha sido presionado
		
		for(int i=0; i<tornillos.size(); i++) {
			if(tornillosBotones.get(i).isPressed() || tornillosTocados.get(i)) {
				tornilloActual = i;
				tornillosTocados.set(i, true);
				posTornillos.get(i).x = Gdx.input.getX();
				posTornillos.get(i).y = hight - Gdx.input.getY();
				batch.draw(tornillos.get(i), posTornillos.get(i).x, posTornillos.get(i).y, width/9, hight/9);
			}
		}
			
		switch(tornillosElegidos.size()) {
			case 0:
			break;
			case 1:
				batch.draw(tornillosElegidos.get(0), width/1.42f, hight/2.7f, width/10, hight/10);
			break;
			case 2:
				batch.draw(tornillosElegidos.get(0), width/1.42f, hight/2.7f, width/10, hight/10);
				batch.draw(tornillosElegidos.get(1), width/1.62f, hight/2.7f, width/10, hight/10);
			break;
			case 3:
				batch.draw(tornillosElegidos.get(0), width/1.42f, hight/2.7f, width/10, hight/10);
				batch.draw(tornillosElegidos.get(1), width/1.62f, hight/2.7f, width/10, hight/10);
				batch.draw(tornillosElegidos.get(2), width/1.42f, hight/4.5f, width/10, hight/10);
			break;
			case 4:
				batch.draw(tornillosElegidos.get(0), width/1.42f, hight/2.7f, width/10, hight/10);
				batch.draw(tornillosElegidos.get(1), width/1.62f, hight/2.7f, width/10, hight/10);
				batch.draw(tornillosElegidos.get(2), width/1.42f, hight/4.5f, width/10, hight/10);
				batch.draw(tornillosElegidos.get(3), width/1.62f, hight/4.5f, width/10, hight/10);
			break;
			default:
			break;
		}
		}
		
		batch.end();
		
		stage.act(delta);
		stage.draw();
	}
	
	@Override 
	public void show() {
		
		final InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(final int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
					//fibooGame.MANAGER.get("sonidos/fondo.mp3", Sound.class).loop();
        			FibooGame.MANAGER.get("sonidos/taller.ogg", Music.class).stop();
					FibooGame.MANAGER.unloadSacoMiniGameSounds();
					FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).setLooping(true);
					FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).play();
					game.setScreen(new MenuMiniJuegosScreen(game));
					dispose();
				}
				return false;
			}
		}, stage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

}

