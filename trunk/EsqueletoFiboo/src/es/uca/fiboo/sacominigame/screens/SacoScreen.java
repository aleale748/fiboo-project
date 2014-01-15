package es.uca.fiboo.sacominigame.screens;

import java.util.List;

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
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.naveminigame.actors.EmptyStarActor;
import es.uca.fiboo.naveminigame.actors.StarActor;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.MenuMiniJuegosScreen;

public class SacoScreen extends AbstractScreen {
	//Atributos necesarios
	private int[] objetosA;
	private int[] objetosB;
	private int[] objetosC;
	private ImageButton gemaButtonMas;
	private ImageButton gemaButtonMenos;
	private ImageButton llaveButton;
	private Texture cofre;
	private Rectangle cofreR;
	private Texture maleta;
	private Rectangle maletaR;
	private Texture gema;
	private Rectangle gemaG;
	private boolean gemaTocada;
	private Sound objetosSound;
	private fibooGame game;
	
	public SacoScreen(final fibooGame game, int[] objetos) {	
		super(game);
		
		this.game = game;
		
		Gdx.input.setInputProcessor(stage);
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		//Cargamos el sonido:
		
		objetosSound = Gdx.audio.newSound(Gdx.files.internal("sacominigame/clic.wav"));
		
		objetosA = new int[3];
		objetosB = new int[3];
		objetosC = new int[3];
		
		for(int i=0; i<objetosA.length; i++) {
			objetosA[i] = objetos[i];
			objetosC[i] = 10;
		}
		
		gemaTocada = false;
		
		//Se crean los rectangulos, botones y se cargan las im�genes necesarias..
		
		cofre = new Texture(Gdx.files.internal("sacominigame/img/cofre_grande.png"));
		maleta = new Texture(Gdx.files.internal("sacominigame/img/maleta.png"));
		gema = new Texture(Gdx.files.internal("sacominigame/img/gema_grande.png")); 
		gemaG = new Rectangle();
		TextureRegion gemaRegion = new TextureRegion(new Texture("sacominigame/img/gema.png"));
		TextureRegion monedaRegion = new TextureRegion(new Texture("sacominigame/img/moneda.png"));
		TextureRegion collarRegion = new TextureRegion(new Texture("sacominigame/img/collar.png"));
		Drawable gemaDrawable = new TextureRegionDrawable(gemaRegion);
		Drawable monedaDrawable = new TextureRegionDrawable(monedaRegion);
		Drawable collarDrawable = new TextureRegionDrawable(collarRegion);
		TextureRegion llaveRegion = new TextureRegion(new Texture("sacominigame/img/llave.png"));
		Drawable llaveDrawable = new TextureRegionDrawable(llaveRegion);
		
		//Creamos y posicionamos los rectangulos:
		
		cofreR = new Rectangle();
		
		cofreR.height = h/3;
		cofreR.width = w/3;
		cofreR.x = w - w/3;
		cofreR.y = h/3;
		
		maletaR = new Rectangle();
		
		maletaR.height = h/3;
		maletaR.width = w/3;
		maletaR.x = w/15;
		maletaR.y = h/3;
		
		
		//Creamos los botones
		
		//Gema de la maleta
		gemaButtonMas = new ImageButton(gemaDrawable);
		gemaButtonMas.setHeight(h/8);
		gemaButtonMas.setWidth(w/8);
		gemaButtonMas.setPosition(maletaR.x + w/10, maletaR.y + h/12);
		
		gemaButtonMas.addListener(new InputListener() {
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(SacoScreenPrincipal.LOG, "Touching down on gemaButtonMas");
				
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(SacoScreenPrincipal.LOG, "Touching up on gemaButtonMas");
				
				if(cofreR.overlaps(gemaG)) {
					objetosSound.play();
					objetosB[2]++;
					objetosC[2]--;
					System.out.println(objetosB[2]);
					gemaTocada = false;
				}
				
				else {
					gemaTocada = false;
				}
				
			}
		});
		
		stage.addActor(gemaButtonMas);
		
		//Gema del cofre
		
		gemaButtonMenos = new ImageButton(gemaDrawable);
		gemaButtonMenos.setHeight(h/8);
		gemaButtonMenos.setWidth(w/8);
		gemaButtonMenos.setPosition(cofreR.x - w/10, cofreR.y + h/10);
		
		gemaButtonMenos.addListener(new InputListener() {
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(SacoScreenPrincipal.LOG, "Touching down on gemaButtonMenos");
				
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(SacoScreenPrincipal.LOG, "Touching up on gemaButtonMenos");
				
				if(maletaR.overlaps(gemaG) && objetosB[2] > 0) {
					objetosSound.play();
					objetosB[2]--;
					objetosC[2]++;
					System.out.println(objetosB[2]);
					gemaTocada = false;
				}
				
				else {
					if(objetosB[2] > 0) {
						objetosB[2]--;
						System.out.println(objetosB[2]);
					}
					gemaTocada = false;
				}
				
			}
		});
		
		stage.addActor(gemaButtonMenos);
		
		
		//llave
		
		llaveButton = new ImageButton(llaveDrawable);
		llaveButton.setHeight(h/8);
		llaveButton.setWidth(w/8);
		llaveButton.setPosition(w/2,h/10);
		
		llaveButton.addListener(new InputListener() {
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(SacoScreenPrincipal.LOG, "Touching down on llaveButton");
				
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(SacoScreenPrincipal.LOG, "Touching up on llaveButton");
				
				//Para las pruebas
				objetosB[0] = objetosA[0];
				objetosB[1] = objetosA[1];
				
				if(sonIguales(objetosA,objetosB)) {
					System.out.println("Bien hecho!");
					//game.setScreen(new EstadisticasScreen(game,0)); Estadisticas ahora no sirve para esto
					SacoScreenPrincipal.aciertos++; //Sumamos el acierto actual
					
					float w = Gdx.graphics.getWidth();
					float h = Gdx.graphics.getHeight();
					
					float widthPuntuacion = 42;
					float heightPuntuacion = 42;
					
					SacoScreenPrincipal.puntos.add(new StarActor());
					SacoScreenPrincipal.puntos.get(SacoScreenPrincipal.puntos.size() - 1).setPosition(widthPuntuacion*0.2f + (SacoScreenPrincipal.puntos.size() - 1) * widthPuntuacion*1.1f, Gdx.graphics.getHeight() - heightPuntuacion*1.1f);
					SacoScreenPrincipal.puntos.get(SacoScreenPrincipal.puntos.size() - 1).setWidth(widthPuntuacion);
					SacoScreenPrincipal.puntos.get(SacoScreenPrincipal.puntos.size() - 1).setHeight(heightPuntuacion);
					stage.addActor(SacoScreenPrincipal.puntos.get(SacoScreenPrincipal.puntos.size() - 1));
					
					game.setScreen(new TiempoScreen(game)); //Lanzamos el siguiente intento
				}
				
				else {
					System.out.println("Sigue intentandolo!");
					//game.setScreen(new EstadisticasScreen(game,1)); Estadisticas ahora no sirve para esto
					game.setScreen(new TiempoScreen(game)); //Lanzamos el siguiente intento
				}
				
			}
		});
		
		stage.addActor(llaveButton);
		
		for (int i = 0; i < 4; ++i) {
			stage.addActor(SacoScreenPrincipal.sin_puntos.get(i));
		}
		
		if(!SacoScreenPrincipal.puntos.isEmpty()) { //Si hay algun punto que lo muestre
			for (int i = 0; i < SacoScreenPrincipal.puntos.size(); ++i) {
				stage.addActor(SacoScreenPrincipal.puntos.get(i));
			}
		}
		
		
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		float h = Gdx.graphics.getHeight();
		float w = Gdx.graphics.getWidth();
		
		batch.begin();
		
		SacoScreenPrincipal.font.draw(batch, "A�ade las mismas cosas que ten�a el cofre del inicio!", w/10, h);
		SacoScreenPrincipal.font.draw(batch, "Tengo la soluci�n!", w/2, h/20);
		//Dibujar el cofre con todos sus botones
		batch.draw(cofre,cofreR.x, cofreR.y, cofreR.width, cofreR.height); 	
		batch.draw(maleta, maletaR.x, maletaR.y, maletaR.width, maletaR.height);
		
		SacoScreenPrincipal.font.draw(batch, " " +objetosC[2], maletaR.x + w/5, maletaR.y + h/6);
		SacoScreenPrincipal.font.draw(batch, " " +objetosB[2], gemaButtonMenos.getX() + w/8, gemaButtonMenos.getY() + h/8);
		
		
		if(gemaButtonMas.isPressed() || gemaTocada) {
			gemaTocada = true;
			gemaG.x = Gdx.input.getX();
			gemaG.y = h - Gdx.input.getY();
			batch.draw(gema,gemaG.x,gemaG.y);
		}
		
		if(gemaButtonMenos.isPressed() || gemaTocada) {
			gemaTocada = true;
			gemaG.x = Gdx.input.getX();
			gemaG.y = h - Gdx.input.getY();
			batch.draw(gema,gemaG.x,gemaG.y);
		}
		
		if(objetosC[2] + objetosB[2] < objetosA[2] || objetosC[1] + objetosB[1] < objetosA[1] || objetosC[0] + objetosB[0] < objetosA[0]) {
			System.out.println("Cuidado con tirar las cosas!");
			//game.setScreen(new EstadisticasScreen(game,1)); Estadisticas ahora no sirve para esto.
			game.setScreen(new TiempoScreen(game)); //Lanzamos el siguiente intento
		}
		
		batch.end();
		
		stage.act(delta);
		stage.draw();
		
		
	}
	
	private boolean sonIguales(int[] objetosA, int[] objetosB) {
		boolean final_juego = true;
		
		for(int z=0; z<objetosA.length; z++) {
			if(objetosA[z] != objetosB[z]) {
				final_juego = false;
			}
		}
		return final_juego;
	}

	@Override
	public void dispose() {
		cofre.dispose();
		maleta.dispose();
		gema.dispose();
		super.dispose();
	}
	
	@Override 
	public void show() {
		
		InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
					fibooGame.MANAGER.get("sonidos/fondo.mp3", Sound.class).loop();
					dispose();
					game.setScreen(new MenuMiniJuegosScreen(game));
				}
				return false;
			}
		}, stage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

}
