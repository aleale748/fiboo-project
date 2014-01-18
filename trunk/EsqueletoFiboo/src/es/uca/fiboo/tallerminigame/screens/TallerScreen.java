package es.uca.fiboo.tallerminigame.screens;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import es.uca.fiboo.fibooGame;
import es.uca.fiboo.naveminigame.actors.StarActor;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.MenuMiniJuegosScreen;

public class TallerScreen extends AbstractScreen {
	private float w;
	private float h;
	private ArrayList<Texture> tornillosUsados;
	private ArrayList<Boolean> tornillosTocados;
	private ArrayList<Rectangle> posicionesTornillos;
	private ArrayList<ImageButton> tornillosBotones;
	private ArrayList<Texture> tornillos;
	private Texture robot_triste;
	private Texture robot_alegre;
	private Texture boton_taller;
	private Sound objetosSound;
	private int tornilloActual;
	private Rectangle barrigaRobot;
	private ArrayList<Texture> tornillosElegidos;
	private fibooGame game;
	
	
	public TallerScreen(final fibooGame game, final ArrayList<Texture> tornillos, final ArrayList<Texture> tornillosUsados) {	
		super(game);
		this.game = game;
		this.tornillosUsados = tornillosUsados;
		this.tornillos = tornillos;
		Gdx.input.setInputProcessor(stage);
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		tornillosTocados = new ArrayList<Boolean>();
		objetosSound = fibooGame.MANAGER.get("sacominigame/clic.wav", Sound.class);
		posicionesTornillos = new ArrayList<Rectangle>();
		barrigaRobot = new Rectangle();
		tornillosBotones = new ArrayList<ImageButton>();
		barrigaRobot = new Rectangle();
		tornillosElegidos = new ArrayList<Texture>();

		
		//Inicializamos los objetos de los ArrayList
		
		for(int i=0; i<tornillos.size(); i++) {
			posicionesTornillos.add(i, new Rectangle());
			tornillosTocados.add(i, false);
		}

		//Se cargan las imagenes necesarias
		robot_triste = fibooGame.MANAGER.get("sacominigame/robottriste.png", Texture.class);
		robot_alegre = fibooGame.MANAGER.get("sacominigame/robotalegre.png", Texture.class);
		boton_taller = fibooGame.MANAGER.get("sacominigame/tallerboton.png", Texture.class);
		
		//Situamos la barriga del robot
		
		barrigaRobot.height = h/3.3f;
		barrigaRobot.width = w/5;
		barrigaRobot.x = w/1.9f;
		barrigaRobot.y = h/5.3f;
		
		
		//Creamos los botones de todos los tornillos y les asignamos su correspondiente listener
		
		for(int i=0; i<tornillos.size(); i++) {
			tornillosBotones.add(i, new ImageButton(new TextureRegionDrawable(new TextureRegion(tornillos.get(i)))));
			tornilloActual = i;
			tornillosBotones.get(i).addListener(new InputListener() {
				
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					Gdx.app.log(TallerScreenPrincipal.LOG, "Touching down on TornilloButton"+tornilloActual);
					return true;
				}
				
				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					Gdx.app.log(TallerScreenPrincipal.LOG, "Touching up on TornilloButton");
					
					if(posicionesTornillos.get(tornilloActual).overlaps(barrigaRobot)) {
						objetosSound.play();
						Gdx.app.log(TallerScreenPrincipal.LOG, "Overlaping with barrigaRobot"+tornilloActual);
						tornillosTocados.set(tornilloActual, false);
						if(tornillosElegidos.size() < 4) {
							tornillosElegidos.add(tornillos.get(tornilloActual));
						}
					}
					
					else {
						tornillosTocados.set(tornilloActual, false);
					}
					
				}
				
			});
		}
		
		//Posicionamos todos los tornillos y les asignamos su tamaño
		
		for(int i=0; i<tornillos.size(); i++) {
			tornillosBotones.get(i).setHeight(h/9.6f);
			tornillosBotones.get(i).setWidth(w/9.6f);
		}
		
		tornillosBotones.get(0).setPosition(w/10f, h/1.5f);
		tornillosBotones.get(1).setPosition(w/5.5f, h/1.5f);
		tornillosBotones.get(2).setPosition(w/3.7f, h/1.5f);
		tornillosBotones.get(3).setPosition(w/2.8f, h/1.5f);
		tornillosBotones.get(4).setPosition(w/10f, h/1.8f);
		tornillosBotones.get(5).setPosition(w/5.5f, h/1.8f);
		tornillosBotones.get(6).setPosition(w/3.7f, h/1.8f);
		tornillosBotones.get(7).setPosition(w/2.8f, h/1.8f);
		tornillosBotones.get(8).setPosition(w/10f, h/2.3f);
		tornillosBotones.get(9).setPosition(w/5.5f, h/2.3f);
		tornillosBotones.get(10).setPosition(w/3.7f, h/2.3f);
		tornillosBotones.get(11).setPosition(w/2.8f, h/2.3f);
		tornillosBotones.get(12).setPosition(w/10f, h/3.1f);
		tornillosBotones.get(13).setPosition(w/5.5f, h/3.1f);
		tornillosBotones.get(14).setPosition(w/3.7f, h/3.1f);
		tornillosBotones.get(15).setPosition(w/2.8f, h/3.1f);
		tornillosBotones.get(16).setPosition(w/5.5f, h/4.6f);
		tornillosBotones.get(17).setPosition(w/3.7f, h/4.6f);
		
		
		//Añadimos todos los botones al stage
		
		for(int i=0; i<tornillos.size(); i++) {
			stage.addActor(tornillosBotones.get(i));
		}
		
		
		//Botón para comprobar el resultado
		
		ImageButton tallerButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(boton_taller)));
		tallerButton.setHeight(h/4);
		tallerButton.setWidth(w/4);
		tallerButton.setPosition(w/800,h/800);
		
		tallerButton.addListener(new InputListener() {
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(TallerScreenPrincipal.LOG, "Touching down on tallerButton");
				
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(TallerScreenPrincipal.LOG, "Touching up on tallerButton");
				
				if((tornillosUsados.size() == tornillosElegidos.size()) && tornillosElegidos.containsAll(tornillosUsados)) {
					TallerScreenPrincipal.aciertos++; //Sumamos el acierto actual
					
					float widthPuntuacion = 42;
					float heightPuntuacion = 42;
					
					TallerScreenPrincipal.puntos.add(new StarActor());
					TallerScreenPrincipal.puntos.get(TallerScreenPrincipal.puntos.size() - 1).setPosition(widthPuntuacion*0.2f + (TallerScreenPrincipal.puntos.size() - 1) * widthPuntuacion*1.1f, Gdx.graphics.getHeight() - heightPuntuacion*1.1f);
					TallerScreenPrincipal.puntos.get(TallerScreenPrincipal.puntos.size() - 1).setWidth(widthPuntuacion);
					TallerScreenPrincipal.puntos.get(TallerScreenPrincipal.puntos.size() - 1).setHeight(heightPuntuacion);
					stage.addActor(TallerScreenPrincipal.puntos.get(TallerScreenPrincipal.puntos.size() - 1));
					
					game.setScreen(new TiempoScreen(game)); //Lanzamos el siguiente intento
					
				}
				
				else {
					System.out.println("Sigue intentandolo!");
					TallerScreenPrincipal.fallos++; //Sumamos el fallo actual
					//game.setScreen(new EstadisticasScreen(game,1)); Estadisticas ahora no sirve para esto
					game.setScreen(new TiempoScreen(game)); //Lanzamos el siguiente intento
				}
				
			}
		});
		
		stage.addActor(tallerButton);
		
		for (int i = 0; i < 4; ++i) {
			stage.addActor(TallerScreenPrincipal.sin_puntos.get(i));
		}
		
		if(!TallerScreenPrincipal.puntos.isEmpty()) { //Si hay algun punto que lo muestre
			for (int i = 0; i < TallerScreenPrincipal.puntos.size(); ++i) {
				stage.addActor(TallerScreenPrincipal.puntos.get(i));
			}
		}
		
		
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		if(TallerScreenPrincipal.aciertos > TallerScreenPrincipal.fallos) {
			batch.draw(robot_alegre, 0, 0, w, h);
		}
		
		else {
			batch.draw(robot_triste, 0, 0, w, h);
		}
		//Dibujar el cofre con todos sus botones
		//batch.draw(cofre,cofreR.x, cofreR.y, cofreR.width, cofreR.height); 	
		//batch.draw(maleta, maletaR.x, maletaR.y, maletaR.width, maletaR.height);	
		
		//Comprobamos si alguno de los botones ha sido presionado
		
		for(int i=0; i<tornillos.size(); i++) {
			if(tornillosBotones.get(i).isPressed() || tornillosTocados.get(i)) {
				tornilloActual = i;
				tornillosTocados.set(i, true);
				posicionesTornillos.get(i).x = Gdx.input.getX();
				posicionesTornillos.get(i).y = h - Gdx.input.getY();
				batch.draw(tornillos.get(i), posicionesTornillos.get(i).x, posicionesTornillos.get(i).y, w/9, h/9);
			}
		}
			
		switch(tornillosElegidos.size()) {
			case 0:
			break;
			case 1:
				batch.draw(tornillosElegidos.get(0), w/1.42f, h/2.7f, w/10, h/10);
			break;
			case 2:
				batch.draw(tornillosElegidos.get(0), w/1.42f, h/2.7f, w/10, h/10);
				batch.draw(tornillosElegidos.get(1), w/1.62f, h/2.7f, w/10, h/10);
			break;
			case 3:
				batch.draw(tornillosElegidos.get(0), w/1.42f, h/2.7f, w/10, h/10);
				batch.draw(tornillosElegidos.get(1), w/1.62f, h/2.7f, w/10, h/10);
				batch.draw(tornillosElegidos.get(2), w/1.42f, h/4.5f, w/10, h/10);
			break;
			case 4:
				batch.draw(tornillosElegidos.get(0), w/1.42f, h/2.7f, w/10, h/10);
				batch.draw(tornillosElegidos.get(1), w/1.62f, h/2.7f, w/10, h/10);
				batch.draw(tornillosElegidos.get(2), w/1.42f, h/4.5f, w/10, h/10);
				batch.draw(tornillosElegidos.get(3), w/1.62f, h/4.5f, w/10, h/10);
			break;
		}
		
		batch.end();
		
		stage.act(delta);
		stage.draw();
	}
	

	@Override
	public void dispose() {
		fibooGame.MANAGER.unloadSacoMiniGameSounds();
		super.dispose();
	}
	
	@Override 
	public void show() {
		
		InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
					//fibooGame.MANAGER.get("sonidos/fondo.mp3", Sound.class).loop();
					game.setScreen(new MenuMiniJuegosScreen(game));
					dispose();
				}
				return false;
			}
		}, stage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

}
