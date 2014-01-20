package es.uca.fiboo.naveminigame.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import es.uca.fiboo.FibooGame;
import es.uca.fiboo.naveminigame.actors.AsteroideActor;
import es.uca.fiboo.naveminigame.actors.BarraActor;
import es.uca.fiboo.naveminigame.actors.BarraVaciaActor;
import es.uca.fiboo.naveminigame.actors.BulletActor;
import es.uca.fiboo.naveminigame.actors.EmptyStarActor;
import es.uca.fiboo.naveminigame.actors.EscudoActor;
import es.uca.fiboo.naveminigame.actors.ExplosionActor;
import es.uca.fiboo.naveminigame.actors.ExplosionMalActor;
import es.uca.fiboo.naveminigame.actors.NaveActor;
import es.uca.fiboo.naveminigame.actors.PadActor;
import es.uca.fiboo.naveminigame.actors.PalitosActor;
import es.uca.fiboo.naveminigame.actors.StarActor;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.GameOverScreen;
import es.uca.fiboo.screens.MenuMiniJuegosScreen;
import es.uca.fiboo.screens.WinScreen;

public class NaveMiniGameScreen extends AbstractScreen {
	
	float widthNave = 256, heightNave = 135, widthPalitos = 1024/2f, heightPalitos = 256/2f, 
			widthBullets = 38, heightBullets = 19, widthAsteroides = 128, heightAsteroides = 128, 
			widthExplosiones = 256, heightExplosiones = 256, widthPuntuacion = 42, 
			heightPuntuacion = 40, widthBasePunt = 135, 
			heightBasePunt = 90,widthBarra = 128, heightBarra = 16, 
			widthPad = 119, heightPad = 57, velocidadAsteroide, velocidadBala, 
			escala;
	Sound disparo, explosion, golpe;
	Music musicaFondo;
	
	public NaveMiniGameScreen(FibooGame game) {
		super(game);
		//Gdx.app.log(fibooGame.LOG, "Bien construido.");
		
		musicaFondo = FibooGame.MANAGER.get("sonidos/nave.ogg",Music.class);
		explosion = FibooGame.MANAGER.get("naveminigame/older/explosion.ogg", Sound.class);
		disparo = FibooGame.MANAGER.get("naveminigame/older/shoot.ogg", Sound.class);
		golpe = FibooGame.MANAGER.get("naveminigame/older/hit.ogg", Sound.class);
	}

	private NaveActor nave;
	private PadActor padShoot;
	private BarraActor vidaNave, vidaEscudo;
	private BarraVaciaActor barraNave, barraEscudo;
	private EscudoActor escudo;
	private List<StarActor> puntuacion;
	private List<EmptyStarActor> puntuacionVacia;
	private boolean resuelto;
	private int respawnSol;
	private List<AsteroideActor> asteroides;
	private List<BulletActor> bullets;
	private List<ExplosionActor> explosiones;
	private List<ExplosionMalActor> explosionesMal;
	private PalitosActor palitos;
	Image baseestrellas;
	float h, w;
	@Override
	public void show() {
		w= Gdx.graphics.getWidth();
		h= Gdx.graphics.getHeight(); 
		musicaFondo.setLooping(true);
		musicaFondo.play();
		
		velocidadAsteroide = 100 * Gdx.graphics.getDeltaTime();
		velocidadBala = w*0.8f * Gdx.graphics.getDeltaTime();
		
		//Gdx.app.log(fibooGame.LOG, "Comienza Minijuego de destruir asteroides");
		asteroides = new ArrayList<AsteroideActor>();
		bullets = new ArrayList<BulletActor>();

		InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
					musicaFondo.stop();
					dispose();
					game.setScreen(new MenuMiniJuegosScreen(game));
				}
				return false;
			}
		}, stage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		//Gdx.app.log(fibooGame.LOG, "Cargando imagen de fondo y añadiendola al escenario");
		Image imgFondo = new Image(FibooGame.MANAGER.get("robotgame/fondoestrellas.png", Texture.class));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);
		baseestrellas = new Image(FibooGame.MANAGER.get("portada/base.png", Texture.class));
		stage.addActor(baseestrellas);
		
		//Gdx.app.log(fibooGame.LOG, "Imagen de fondo añadida");
		
		explosiones = new ArrayList<ExplosionActor>();
		explosionesMal = new ArrayList<ExplosionMalActor>();

		//Gdx.app.log(fibooGame.LOG, "Generando nave");
		nave = new NaveActor();
		nave.bb.x = nave.getX();
		nave.bb.y = nave.getY();
		stage.addActor(nave);
		
		//Gdx.app.log(fibooGame.LOG, "ñadiendo listeners en la nave");
		stage.setKeyboardFocus(nave);
		nave.addListener(new InputDesktopListener());
		padShoot = new PadActor();
			
		nave.addListener(new DragListener() {
			public void touchDragged(InputEvent event, float x, float y, int pointer) {
				float dx = x - widthNave*0.5f;
				float dy = y - heightNave*0.5f;
				nave.setPosition(nave.getX() + dx, nave.getY() + dy);
			}
				 
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
			}

		});

		//Gdx.app.log(fibooGame.LOG, "Listeners en la nave añadidos");
		

		//Gdx.app.log(fibooGame.LOG, "Añadiendo botón de disparo");
		padShoot.addListener(new InputAndroidShootListener());
		stage.addActor(padShoot);
		//Gdx.app.log(fibooGame.LOG, "Botón de disparo añadido.");
		
		//Gdx.app.log(fibooGame.LOG, "Generando vidas de la nave y escudo");
		escudo = new EscudoActor();
		stage.addActor(escudo);
		
		vidaNave = new BarraActor(nave);
		vidaEscudo = new BarraActor(escudo);
		
		barraNave = new BarraVaciaActor();
		barraEscudo = new BarraVaciaActor();
		
		stage.addActor(barraNave);
		stage.addActor(barraEscudo);
		stage.addActor(vidaNave);
		stage.addActor(vidaEscudo);
		
		//Gdx.app.log(fibooGame.LOG, "Vidas de la nave y escudo añadidas");
		
		//Gdx.app.log(fibooGame.LOG, "Añadiendo sistema de puntuacion por estrellas");
		puntuacionVacia = new ArrayList<EmptyStarActor>();
		for (int i = 0; i < 5; ++i) {
			puntuacionVacia.add(new EmptyStarActor());
			puntuacionVacia.get(i).setPosition(widthPuntuacion*0.2f + i * widthPuntuacion*1.1f, h - heightPuntuacion*1.1f);
			stage.addActor(puntuacionVacia.get(i));
		}
		
		puntuacion = new ArrayList<StarActor>();

		//Gdx.app.log(fibooGame.LOG, "Sistema de puntuación por estrellas añadido");
		
		resuelto = true;
		respawnSol = 0;

		
		timer = 2 + (float) Math.random();
		//Gdx.app.log(fibooGame.LOG, "Show realizado");
	}
	
	private float timer;
	
	private float aleatorio1, aleatorio2, random;

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		stage.act();
		
		if (resuelto) {
			Gdx.app.log("NaveMiniGame", "NEW - PalitosActor");
			palitos = new PalitosActor(MathUtils.random(0, 9));
			palitos.setWidth(widthPalitos);
			palitos.setHeight(heightPalitos);
			palitos.setPosition(w/2f - palitos.getWidth()/2f, palitos.getHeight()/10f - palitos.getHeight()/8f);
			stage.addActor(palitos);
			resuelto = false;
		}
		
		timer -= delta;
		if (timer < 0) {
			
			//Gdx.app.log(fibooGame.LOG, "Generando asteroides");
			Gdx.app.log("NaveMiniGame", "NEW - AsteroideActor");
			AsteroideActor asteroide = new AsteroideActor(MathUtils.random(0, 9), (float) (puntuacion.size()/5f)*velocidadAsteroide + velocidadAsteroide);
			aleatorio1 = (float) Math.random();
			aleatorio2 = (float) Math.random();
			
			if (Math.abs(aleatorio1 - aleatorio2) < 0.3f)
				
				if (aleatorio1 < 0.5f && aleatorio1 < aleatorio2) aleatorio2 += 0.3f;
				else 
					
					if (aleatorio1 < 0.5)
						aleatorio1 += 0.3f;
				else
					
					if (aleatorio1 < aleatorio2)
						aleatorio1 -= 0.3f;
				else
					aleatorio2 -= 0.2f;
			
			asteroide.setWidth(widthAsteroides);
			asteroide.setHeight(heightAsteroides);
			random = (float) Math.random();
			
			if (random > 0.5)
				asteroide.setPosition(w + w*0.25f, h * (aleatorio1 * 0.6f + 0.1f));
			else 
				asteroide.setPosition(w, h * (aleatorio1 * 0.6f + 0.1f));
			
			asteroide.velocidad*= escala*1.001f;
			asteroide.bb.x = asteroide.getX();
			asteroide.bb.y = asteroide.getY();
			stage.addActor(asteroide);
			asteroides.add(asteroide);
			
			if (respawnSol % 1 == 0) {
				
				Gdx.app.log("NaveMiniGame", "NEW - AsteroideActor");
				AsteroideActor asteroidesol = new AsteroideActor(palitos.getNum(), (float) (puntuacion.size()/5f)*velocidadAsteroide + velocidadAsteroide);
				asteroidesol.setWidth(widthAsteroides);
				asteroidesol.setHeight(heightAsteroides);
				
				if (random > 0.5)
					asteroidesol.setPosition(w, h * (aleatorio2 * 0.6f + 0.1f));
				else 
					asteroidesol.setPosition(w + w*0.25f, h * (aleatorio2 * 0.6f + 0.1f));
				
				asteroidesol.velocidad *= escala*1.001f;
				asteroidesol.bb.x = asteroidesol.getX();
				asteroidesol.bb.y = asteroidesol.getY();
				stage.addActor(asteroidesol);
				asteroides.add(asteroidesol);
			}
			
			timer = 6f + (float) Math.random();
			

			//Gdx.app.log(fibooGame.LOG, "Generación de asteroides terminada");
		}

		//Gdx.app.log(fibooGame.LOG, "Comprobando listas de asteroides, explosiones, balas y puntuaciones");
		comprobarListas();
		//Gdx.app.log(fibooGame.LOG, "Comprobación de listas terminada");
		

		//Gdx.app.log(fibooGame.LOG, "Comprobando colisiones");
		comprobarColisiones();
		//Gdx.app.log(fibooGame.LOG, "Comprobación de colisiones terminada");

		respawnSol += 1;
		
		padShoot.toFront();
		nave.toFront();
		
		stage.draw();
	}
	
	private void comprobarListas() {
		

		//Gdx.app.log(fibooGame.LOG, "Comprobando colisión asteroide escudo");
		
		AsteroideActor asteroide;
		for(int i = 0; i < asteroides.size(); i++) {
			if (asteroides.get(i).getRight() < widthAsteroides*0.9f) {
				asteroide = asteroides.get(i);
				explosion.play();
				if (escudo.getHealth() > 0.4f) {
					
					if (asteroide.getNumero() == palitos.getNum()) {
						//Gdx.app.log(fibooGame.LOG, "Colisión de asteroide con escudo solución producida");
						
						Gdx.app.log("NaveMiniGame", "NEW - ExplosionMalActor");
						explosionesMal.add(new ExplosionMalActor());
						explosionesMal.get(explosionesMal.size()-1).setPosition(asteroides.get(i).getX()*0.98f, asteroides.get(i).getY()*0.75f);
						explosionesMal.get(explosionesMal.size()-1).setWidth(widthExplosiones);
						explosionesMal.get(explosionesMal.size()-1).setHeight(heightExplosiones);
						stage.addActor(explosionesMal.get(explosionesMal.size()-1));
						escudo.sumHealth(-0.4f);
					}
					else {
						Gdx.app.log("NaveMiniGame", "NEW - ExplisiónActor");
						explosiones.add(new ExplosionActor());
						explosiones.get(explosiones.size()-1).setPosition(asteroides.get(i).getX()*0.98f, asteroides.get(i).getY()*0.75f);
						explosiones.get(explosiones.size()-1).setWidth(widthExplosiones);
						explosiones.get(explosiones.size()-1).setHeight(heightExplosiones);
						stage.addActor(explosiones.get(explosiones.size()-1));
					}
					
					asteroides.get(i).remove();
					asteroides.remove(i);
					golpe.play();
					
				} else  {
					//Gdx.app.log(fibooGame.LOG, "Escudo debilitado. Partida terminada.");
					
					musicaFondo.stop();
					dispose();
					game.setScreen(new GameOverScreen(game));
				}
			}
		}
		
		//Gdx.app.log(fibooGame.LOG, "Comprobación de colisión asteroides escudo terminada");
		
		//Gdx.app.log(fibooGame.LOG, "Comprobación de balas al final del escenario");
		
		for(int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).getX() > stage.getWidth()) {
				bullets.get(i).remove();
				bullets.remove(i);
			}
		}
		
		//Gdx.app.log(fibooGame.LOG, "Comprobación de balas terminada");
		
		//Gdx.app.log(fibooGame.LOG, "Recolocando estrellas");
		
		for(int i = 0; i < puntuacionVacia.size(); ++i) {
			puntuacionVacia.get(i).setPosition(widthPuntuacion*0.2f + i * widthPuntuacion*1.1f, h - heightPuntuacion*1.1f);
		}
		
		for(int i = 0; i < puntuacion.size(); ++i) {
			puntuacion.get(i).setPosition(widthPuntuacion*0.2f + i * widthPuntuacion*1.1f, h - heightPuntuacion*1.1f);
		}
		
		//Gdx.app.log(fibooGame.LOG, "Recolocación de estrellas terminada");
		
		//Gdx.app.log(fibooGame.LOG, "Limpiando explosiones producidas");
		
		for(int i = 0; i < explosiones.size(); ++i) {
			if (explosiones.get(i).explosionAnimation.isAnimationFinished(explosiones.get(i).stateTime)) {
				explosiones.get(i).remove();
				explosiones.remove(i);
			}
		}
		
		for(int i = 0; i < explosionesMal.size(); ++i) {
			if (explosionesMal.get(i).explosionAnimation.isAnimationFinished(explosionesMal.get(i).stateTime)) {
				explosionesMal.get(i).remove();
				explosionesMal.remove(i);
			}
		}
		
		//Gdx.app.log(fibooGame.LOG, "Limpieza de explosiones terminadas");
		
	}
	
	private void comprobarColisiones() {
		AsteroideActor asteroide;
		//Gdx.app.log(fibooGame.LOG, "Comprobando colisiones entre asteroides y balas o nave");
		
		for (int i = 0; i < asteroides.size(); i++) {
			asteroide = asteroides.get(i);
			
			if (asteroide.bb.overlaps(nave.bb)) {
				//Gdx.app.log(fibooGame.LOG, "Colisión nave-asteroide producida");
				
				Gdx.app.log("NaveMiniGame", "NEW - ExplisionMalActor");
				explosionesMal.add(new ExplosionMalActor());
				explosionesMal.get(explosionesMal.size()-1).setPosition(asteroides.get(i).getX()*0.98f, asteroides.get(i).getY()*0.75f);
				explosionesMal.get(explosionesMal.size()-1).setWidth(widthExplosiones);
				explosionesMal.get(explosionesMal.size()-1).setHeight(heightExplosiones);
				stage.addActor(explosionesMal.get(explosionesMal.size()-1));
				asteroides.get(i).remove();
				asteroides.remove(i);
				nave.sumHealth(-0.4f);
				golpe.play();
				
				if (nave.getHealth() <= 0) {
					//Gdx.app.log(fibooGame.LOG, "Nave debilitada. Partida terminada.");
					musicaFondo.stop();
					dispose();
					game.setScreen(new GameOverScreen(game));
				}
				
			} else {
				
				for (int j = 0; j < bullets.size(); j++) {
					
					if (asteroide.bb.overlaps(bullets.get(j).bb) && !resuelto) {
						//Gdx.app.log(fibooGame.LOG, "Colisión bala-asteroide producida");
						
						if (asteroides.get(i).getNumero() == palitos.getNum()) {	
							//Gdx.app.log(fibooGame.LOG, "Asteroide soluci���n destruido");
							
							palitos.remove();
							Gdx.app.log("NaveMiniGame", "NEW - StarActor");
							puntuacion.add(new StarActor());
							puntuacion.get(puntuacion.size() - 1).setPosition(widthPuntuacion*0.2f + (puntuacion.size() - 1) * widthPuntuacion*1.1f, h - heightPuntuacion*1.1f);
							puntuacion.get(puntuacion.size() - 1).setWidth(widthPuntuacion);
							puntuacion.get(puntuacion.size() - 1).setHeight(heightPuntuacion);
							stage.addActor(puntuacion.get(puntuacion.size() - 1));
							resuelto = true;
							
							for (int k = 0; k < asteroides.size(); k++) {
								Gdx.app.log("NaveMiniGame", "NEW - ExplosionActor");
								explosiones.add(new ExplosionActor());
								explosiones.get(explosiones.size()-1).setPosition(asteroides.get(k).getX()*0.98f, asteroides.get(k).getY()*0.75f);
								explosiones.get(explosiones.size()-1).setWidth(widthExplosiones);
								explosiones.get(explosiones.size()-1).setHeight(heightExplosiones);
								stage.addActor(explosiones.get(explosiones.size()-1));
								asteroides.get(k).remove();
							}
							
							asteroides.clear();
							
							if (puntuacion.size() == 5) {
								musicaFondo.stop();
								dispose();
								game.setScreen(new WinScreen(game));
							}
							
						} else {
							//Gdx.app.log(fibooGame.LOG, "Asteroide err���neo destruido");
							nave.sumHealth(-0.4f);
							golpe.play();
							Gdx.app.log("NaveMiniGame", "NEW - ExplosionMalActor");
							explosionesMal.add(new ExplosionMalActor());
							explosionesMal.get(explosionesMal.size()-1).setPosition(asteroides.get(i).getX()*0.98f, asteroides.get(i).getY()*0.75f);
							explosionesMal.get(explosionesMal.size()-1).setWidth(widthExplosiones);
							explosionesMal.get(explosionesMal.size()-1).setHeight(heightExplosiones);
							stage.addActor(explosionesMal.get(explosionesMal.size()-1));
							if (nave.getHealth() <= 0) {
								musicaFondo.stop();
								dispose();
								game.setScreen(new GameOverScreen(game));
							}
						}
						
						if (!asteroides.isEmpty()) {
							asteroides.get(i).remove();
							asteroides.remove(i);
						}
						
						bullets.get(j).remove();
						bullets.remove(j);
						
						explosion.play();
					}
				}
			}
		}
		//Gdx.app.log(fibooGame.LOG, "Comprobación de colisiones terminada");
	}
	
	@Override
	public void resize(int width, int height) {
		escala = ((float) (w / 4f) / widthNave);
		widthNave *= escala;
		heightNave *= escala;
		widthAsteroides *= escala;
		heightAsteroides *= escala;
		widthBullets *= escala;
		heightBullets *= escala;
		widthPuntuacion *= escala;
		heightPuntuacion *= escala;
		widthBarra *= escala;
		widthBasePunt = w*0.27f;
		heightBasePunt = h*0.32f;
		heightBarra *= escala;
		widthPalitos *= escala;
		heightPalitos *= escala;
		widthPad *= escala;
		heightPad *= escala;
		widthExplosiones *= escala;
		heightExplosiones *= escala;
		nave.setWidth(widthNave);
		nave.setHeight(heightNave);
		stage.setViewport(width, height, true);
		padShoot.setWidth(widthPad*2f);
		padShoot.setHeight(heightPad*2f);
		vidaNave.setHeight(heightBarra*2);
		vidaNave.setWidth(widthBarra*2);
		vidaEscudo.setHeight(heightBarra*2);
		vidaEscudo.setWidth(widthBarra*2);
		barraNave.setHeight(heightBarra*2);
		barraNave.setWidth(widthBarra*2);
		barraEscudo.setHeight(heightBarra*2);
		barraEscudo.setWidth(widthBarra*2);
		vidaNave.setPosition(w - widthBarra*2.2f, h*0.94f);
		vidaEscudo.setPosition(w - widthBarra*2.2f, h*0.9f);
		barraNave.setPosition(w - widthBarra*2.2f, h*0.94f);
		barraEscudo.setPosition(w - widthBarra*2.2f, h*0.9f);
		nave.setPosition(w*0.01f, h/2f - heightNave/2.5f);
		padShoot.setPosition(w * 0.75f, h * 0.01f);

		for (int i = 0; i < 5; ++i) {
			puntuacionVacia.get(i).setWidth(widthPuntuacion);
			puntuacionVacia.get(i).setHeight(heightPuntuacion);
		}
		
		baseestrellas.setPosition(w*0.12f - widthBasePunt/2f, h-heightBasePunt/2f);
		baseestrellas.setWidth(widthBasePunt);
		baseestrellas.setHeight(heightBasePunt);
	}
	
	private final class InputAndroidShootListener extends InputListener {
		@Override
		public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			Gdx.app.log("NaveMiniGame", "NEW - bullet");
			BulletActor bullet = new BulletActor(velocidadBala);
			bullet.setWidth(widthBullets);
			bullet.setHeight(heightBullets);
			bullet.setPosition(nave.getX() + widthNave - widthNave/4.5f, nave.getY() + (heightNave - heightNave/5f));
			bullet.bb.x = bullet.getX();
			bullet.bb.y = bullet.getY();
			stage.addActor(bullet);
			bullets.add(bullet);
			disparo.play();
			return true;
		}
	}

	private final class InputDesktopListener extends InputListener {
		@Override
		public boolean keyDown(InputEvent event, int keycode) {
			switch(keycode) {
			case Input.Keys.UP:
				nave.velocidad.y = 250;
				return true;
			case Input.Keys.DOWN:
				nave.velocidad.y = -250;
				return true;
			default:
				return false;
			}
		}

		@Override
		public boolean keyUp(InputEvent event, int keycode) {
			switch(keycode) {
			case Input.Keys.UP:
			case Input.Keys.DOWN:
				nave.velocidad.y = 0;
				return true;
			default:
				return false;
			}
		}

		@Override
		public boolean keyTyped(InputEvent event, char character) {
			if(character != ' ') return false;
			Gdx.app.log("NaveMiniGame", "NEW - bullet");
			BulletActor bullet = new BulletActor(velocidadBala);
			bullet.setWidth(widthBullets);
			bullet.setHeight(heightBullets);
			bullet.setPosition(nave.getX() + widthNave - widthNave/4.5f, nave.getY() + (heightNave - heightNave/5f));
			bullet.bb.x = bullet.getX();
			bullet.bb.y = bullet.getY();
			stage.addActor(bullet);
			bullets.add(bullet);
			disparo.play();
			return true;
		}
	}
	
	@Override
	public void dispose() {
		FibooGame.MANAGER.unloadNaveMiniGameSounds();
		FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).setLooping(true);
		FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).play();
		super.dispose();
	}

}
