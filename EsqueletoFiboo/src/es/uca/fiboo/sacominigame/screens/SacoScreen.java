package es.uca.fiboo.sacominigame.screens;

import com.badlogic.gdx.Gdx;
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
import es.uca.fiboo.screens.AbstractScreen;

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
	fibooGame game;
	
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
		
		//Se crean los rectangulos, botones y se cargan las imágenes necesarias..
		
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
		
		cofreR.height = h/5;
		cofreR.width = w/5;
		cofreR.x = w/3;
		cofreR.y = h/4;
		
		maletaR = new Rectangle();
		
		maletaR.height = h/5;
		maletaR.width = w/5;
		maletaR.x = w/4;
		maletaR.y = h/2;
		
		
		//Creamos los botones
		
		//Gema de la maleta
		
		gemaButtonMas = new ImageButton(gemaDrawable);
		gemaButtonMas.setPosition(maletaR.x + maletaR.getWidth(), maletaR.y + maletaR.height);
		
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
		gemaButtonMenos.setPosition(cofreR.x + cofreR.getWidth(), cofreR.y + cofreR.getHeight());
		
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
		llaveButton.setPosition(w/2 - 64,h - 470);
		
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
		
		
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		float h = Gdx.graphics.getHeight();
		float w = Gdx.graphics.getWidth();
		
		SacoScreenPrincipal.batch.begin();
		
		SacoScreenPrincipal.font.draw(SacoScreenPrincipal.batch, "Añade las mismas cosas que tenía el cofre del inicio!", 0, w/2);
		SacoScreenPrincipal.font.draw(SacoScreenPrincipal.batch, "Tengo la solución!", w/2, h/20);
		//Dibujar el cofreA con todos sus botones
		SacoScreenPrincipal.batch.draw(cofre,cofreR.x, cofreR.y, cofreR.width, cofreR.height); 	
		SacoScreenPrincipal.batch.draw(maleta, maletaR.x, maletaR.y, maletaR.width, maletaR.height);
		SacoScreenPrincipal.font.draw(SacoScreenPrincipal.batch, " " +objetosC[2], w/2 - 225 , h/2 - 30);
		
		//Arrastrar figuras
		SacoScreenPrincipal.font.draw(SacoScreenPrincipal.batch, " " +objetosB[2], gemaButtonMenos.getX()+ gemaButtonMenos.getWidth() + 5, gemaButtonMenos.getY() + gemaButtonMenos.getHeight() - 10);
		if(gemaButtonMas.isPressed() || gemaTocada) {
			gemaTocada = true;
			gemaG.x = Gdx.input.getX();
			gemaG.y = h - Gdx.input.getY();
			SacoScreenPrincipal.batch.draw(gema,gemaG.x,gemaG.y);
		}
		
		if(gemaButtonMenos.isPressed() || gemaTocada) {
			gemaTocada = true;
			gemaG.x = Gdx.input.getX();
			gemaG.y = h - Gdx.input.getY();
			SacoScreenPrincipal.batch.draw(gema,gemaG.x,gemaG.y);
		}
		
		if(objetosC[2] + objetosB[2] < objetosA[2] || objetosC[1] + objetosB[1] < objetosA[1] || objetosC[0] + objetosB[0] < objetosA[0]) {
			System.out.println("Cuidado con tirar las cosas!");
			//game.setScreen(new EstadisticasScreen(game,1)); Estadisticas ahora no sirve para esto.
			game.setScreen(new TiempoScreen(game)); //Lanzamos el siguiente intento
		}
		
		SacoScreenPrincipal.batch.end();
		
		super.render(delta);
		
		
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
		super.dispose();
		cofre.dispose();
		maleta.dispose();
		gema.dispose();
	}

}
