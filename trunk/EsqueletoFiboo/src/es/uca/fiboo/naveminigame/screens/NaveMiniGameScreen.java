package es.uca.fiboo.naveminigame.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.naveminigame.actors.*;
import es.uca.fiboo.screens.*;

public class NaveMiniGameScreen extends AbstractScreen {

	private final class InputAndroidShootListener extends InputListener {
		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			BulletActor bullet = new BulletActor();
			bullet.setPosition(nave.getX() + nave.getWidth() - 55, nave.getY() + (nave.getHeight() - 27));
			bullet.bb.x = bullet.getX();
			bullet.bb.y = bullet.getY();
			stage.addActor(bullet);
			bullets.add(bullet);
			fibooGame.MANAGER.get("naveminigame/older/shoot.ogg", Sound.class).play();
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
			if(character != ' ') 
				return false;
			
			BulletActor bullet = new BulletActor();
			bullet.setPosition(nave.getX() + nave.getWidth() - 55, nave.getY() + (nave.getHeight() - 27));
			bullet.bb.x = bullet.getX();
			bullet.bb.y = bullet.getY();
			stage.addActor(bullet);
			bullets.add(bullet);
			fibooGame.MANAGER.get("naveminigame/older/shoot.ogg", Sound.class).play();
			
			return true;
		}
	}
	
	public NaveMiniGameScreen(fibooGame game) {
		super(game);
		Gdx.app.log(fibooGame.LOG, "Bien construido.");
	}

	private NaveActor nave;
	
	private PadActor padShoot;
	
	private BarraActor vidaNave, vidaEscudo;
	
	private BarraVaciaActor barraNave, barraEscudo;
	
	private EscudoActor escudo;
	
	private List<StarActor> puntuacion;
	
	private List<EmptyStarActor> puntuacionVacia;
	
	private VidaTextActor vidaText;
	
	private EscudoTextActor escudoText;
	
	private boolean resuelto;
	
	private int respawnSol;
	
	private OperadorActor operador;
	
	private NumeroXActor numeroX;
	
	private NumeroYActor numeroY;
	
	private List<AsteroideActor> asteroides;
	
	private List<BulletActor> bullets;
	
	private List<ExplosionActor> explosiones;
	
	@Override
	public void show() {

		Gdx.app.log(fibooGame.LOG, "Comienza Minijuego de destruir asteroides");
		asteroides = new ArrayList<AsteroideActor>();
		bullets = new ArrayList<BulletActor>();

		Gdx.input.setInputProcessor(stage);
		
		Gdx.app.log(fibooGame.LOG, "Cargando imagen de fondo y añadiendola al escenario");
		Image imgFondo = new Image(fibooGame.MANAGER.get("naveminigame/fondonave.png", Texture.class));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);
		

		Gdx.app.log(fibooGame.LOG, "Imagen de fondo añadida");
		
		explosiones = new ArrayList<ExplosionActor>();
		

		Gdx.app.log(fibooGame.LOG, "Generando nave");
		nave = new NaveActor();
		nave.bb.x = nave.getX();
		nave.bb.y = nave.getY();
		stage.addActor(nave);
		
		nave.setPosition(10, 10);
		
		Gdx.app.log(fibooGame.LOG, "Añadiendo listeners en la nave");
		
		stage.setKeyboardFocus(nave);
		nave.addListener(new InputDesktopListener());
		padShoot = new PadActor();
		padShoot.setPosition(Gdx.graphics.getWidth() - 170, 30);
			
		nave.addListener(new DragListener() {
			public void touchDragged(InputEvent event, float x, float y, int pointer) {
				float dx = x - nave.getWidth()*0.5f;
				float dy = y - nave.getHeight()*0.5f;
				nave.setPosition(nave.getX() + dx, nave.getY() + dy);
			}
				 
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
			}

		});

		Gdx.app.log(fibooGame.LOG, "Listeners en la nave añadidos");
		

		Gdx.app.log(fibooGame.LOG, "Añadiendo botón de disparo");
		padShoot.addListener(new InputAndroidShootListener());
		stage.addActor(padShoot);
		Gdx.app.log(fibooGame.LOG, "Botón de disparo añadido.");
		

		Gdx.app.log(fibooGame.LOG, "Generando vidas de la nave y escudo");
		escudo = new EscudoActor();
		stage.addActor(escudo);
		
		vidaNave = new BarraActor(nave);
		vidaNave.setPosition(Gdx.graphics.getWidth() - vidaNave.getWidth() - 10, Gdx.graphics.getHeight() - vidaNave.getHeight() - 10);
		vidaEscudo = new BarraActor(escudo);
		vidaEscudo.setPosition(Gdx.graphics.getWidth() - vidaEscudo.getWidth() - 10, Gdx.graphics.getHeight() - vidaEscudo.getHeight() - 28);
		
		
		barraNave = new BarraVaciaActor();
		barraNave.setPosition(Gdx.graphics.getWidth() - vidaNave.getWidth() - 10, Gdx.graphics.getHeight() - vidaNave.getHeight() - 10);
		barraEscudo = new BarraVaciaActor();
		barraEscudo.setPosition(Gdx.graphics.getWidth() - vidaEscudo.getWidth() - 10, Gdx.graphics.getHeight() - vidaEscudo.getHeight() - 28);
		
		stage.addActor(barraNave);
		stage.addActor(barraEscudo);
		stage.addActor(vidaNave);
		stage.addActor(vidaEscudo);
		
		vidaText = new VidaTextActor(new BitmapFont());
		vidaText.setPosition(Gdx.graphics.getWidth() - barraNave.getWidth() - 46, Gdx.graphics.getHeight() - vidaNave.getHeight() + 5);
		escudoText = new EscudoTextActor(new BitmapFont());
		escudoText.setPosition(Gdx.graphics.getWidth() - barraNave.getWidth() - 76, Gdx.graphics.getHeight() - vidaNave.getHeight() - 13);
		stage.addActor(vidaText);
		stage.addActor(escudoText);
		

		Gdx.app.log(fibooGame.LOG, "Vidas de la nave y escudo añadidas");
		

		Gdx.app.log(fibooGame.LOG, "Añadiendo sistema de puntuacion por estrellas");
		puntuacionVacia = new ArrayList<EmptyStarActor>();
		for (int i = 0; i < 10; ++i) {
			puntuacionVacia.add(new EmptyStarActor());
			puntuacionVacia.get(i).setPosition(10 + i * 46, Gdx.graphics.getHeight() - puntuacionVacia.get(i).getHeight()/2 - 30);
			stage.addActor(puntuacionVacia.get(i));
		}
		
		puntuacion = new ArrayList<StarActor>();

		Gdx.app.log(fibooGame.LOG, "Sistema de puntuación por estrellas añadido");
		

		Gdx.app.log(fibooGame.LOG, "Añadiendo operador de la suma");
		operador = new OperadorActor();
		operador.setPosition(Gdx.graphics.getWidth()/2 - operador.getWidth()/2, 24);
		stage.addActor(operador);

		Gdx.app.log(fibooGame.LOG, "Operador de la suma añadido");
		
		resuelto = true;
		respawnSol = 0;
		
		timer = 2 + (float) Math.random();
		Gdx.app.log(fibooGame.LOG, "Show realizado");
	}
	
	private float timer;
	
	private float aleatorio1, aleatorio2;

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		stage.act();
		

		Gdx.app.log(fibooGame.LOG, "Comprobando si la suma ha sido resuelta");
		
		if (resuelto) {
			
			Gdx.app.log(fibooGame.LOG, "Generando nuevos números para la suma");
			stage.getRoot().removeActor(numeroX);
			stage.getRoot().removeActor(numeroY);
			
			float a = (float) Math.random();
			
			if (a < 0.5f) {
			
				numeroX = new NumeroXActor((int) Math.rint(Math.random() * 9));
				numeroX.setPosition(Gdx.graphics.getWidth()/2 - operador.getWidth()/2 - 50, 10);
				stage.addActor(numeroX);
				
				numeroY = new NumeroYActor((int) Math.rint(Math.random() * (9 - numeroX.a)));
				numeroY.setPosition(Gdx.graphics.getWidth()/2 - operador.getWidth()/2 + 80, 10);
				stage.addActor(numeroY);
			}
			else 
				if (a >= 0.5f) {
					numeroY = new NumeroYActor((int) Math.rint(Math.random() * 9));
					numeroY.setPosition(Gdx.graphics.getWidth()/2 - operador.getWidth()/2 + 80, 10);
					stage.addActor(numeroY);
					
					numeroX = new NumeroXActor((int) Math.rint(Math.random() * (9 - numeroY.b)));
					numeroX.setPosition(Gdx.graphics.getWidth()/2 - operador.getWidth()/2 - 50, 10);
					stage.addActor(numeroX);
				}
			
			resuelto = false;

			Gdx.app.log(fibooGame.LOG, "Generación de nuevos números para la suma terminada");
		}
		

		Gdx.app.log(fibooGame.LOG, "Comprobación de suma resuelta terminada");
		

		
		timer -= delta;
		if (timer < 0) {
			

			Gdx.app.log(fibooGame.LOG, "Generando asteroides");
			
			AsteroideActor asteroide = new AsteroideActor((int) (Math.random() * 10) % 10, (float) puntuacion.size()/10);
			aleatorio1 = (float) Math.random();
			aleatorio2 = (float) Math.random();
			if (Math.abs(aleatorio1 - aleatorio2) < 0.3f)
				if (aleatorio1 < 0.5f && aleatorio1 < aleatorio2) aleatorio2 += 0.3f;
				else if (aleatorio1 < 0.5)
					aleatorio1 += 0.3f;
				else if (aleatorio1 < aleatorio2)
					aleatorio1 -= 0.3f;
				else
					aleatorio2 -= 0.2f;
			asteroide.setPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * (aleatorio1 * 0.6f + 0.1f));
			asteroide.bb.x = asteroide.getX();
			asteroide.bb.y = asteroide.getY();
			stage.addActor(asteroide);
			asteroides.add(asteroide);
			if (respawnSol % 1 == 0) {
				AsteroideActor asteroidesol = new AsteroideActor(numeroX.a + numeroY.b, (float) puntuacion.size()/10);
				asteroidesol.setPosition(Gdx.graphics.getWidth() + (int) (400 * Math.random()), Gdx.graphics.getHeight() * (aleatorio2 * 0.6f + 0.1f));
				asteroidesol.bb.x = asteroidesol.getX();
				asteroidesol.bb.y = asteroidesol.getY();
				stage.addActor(asteroidesol);
				asteroides.add(asteroidesol);
			}
			timer = 5f + ((float) Math.random() * 2) - (puntuacion.size() * 4 / 10);
			

			Gdx.app.log(fibooGame.LOG, "Generación de asteroides terminada");
		}
		

		Gdx.app.log(fibooGame.LOG, "Comprobando listas de asteroides, explosiones, balas y puntuaciones");
		comprobarListas();
		Gdx.app.log(fibooGame.LOG, "Comprobación de listas terminada");
		

		Gdx.app.log(fibooGame.LOG, "Comprobando colisiones");
		comprobarColisiones();
		Gdx.app.log(fibooGame.LOG, "Comprobación de colisiones terminada");
		

		Gdx.app.log(fibooGame.LOG, "Reposicionamiento de los actores");
		vidaNave.setPosition(Gdx.graphics.getWidth() - vidaNave.getWidth() - 10, Gdx.graphics.getHeight() - vidaNave.getHeight() - 10);
		barraNave.setPosition(Gdx.graphics.getWidth() - vidaNave.getWidth() - 10, Gdx.graphics.getHeight() - vidaNave.getHeight() - 10);
		vidaEscudo.setPosition(Gdx.graphics.getWidth() - vidaNave.getWidth() - 10, Gdx.graphics.getHeight() - vidaNave.getHeight() - 28);
		barraEscudo.setPosition(Gdx.graphics.getWidth() - vidaEscudo.getWidth() - 10, Gdx.graphics.getHeight() - vidaEscudo.getHeight() - 28);
		vidaText.setPosition(Gdx.graphics.getWidth() - barraNave.getWidth() - 46, Gdx.graphics.getHeight() - vidaNave.getHeight() + 5);
		escudoText.setPosition(Gdx.graphics.getWidth() - barraNave.getWidth() - 76, Gdx.graphics.getHeight() - vidaNave.getHeight() - 13);
	
		numeroX.setPosition(Gdx.graphics.getWidth()/2 - operador.getWidth()/2 - 50, 10);
		numeroY.setPosition(Gdx.graphics.getWidth()/2 - operador.getWidth()/2 + 80, 10);
		operador.setPosition(Gdx.graphics.getWidth()/2 - operador.getWidth()/2, 24);
		
		respawnSol += 1;
		
		padShoot.setPosition(Gdx.graphics.getWidth() - 170, 30);
		

		Gdx.app.log(fibooGame.LOG, "Reposicionamiento terminado");
		
		stage.draw();
	}
	
	private void comprobarListas() {
		

		Gdx.app.log(fibooGame.LOG, "Comprobando colisión asteroide escudo");
		
		AsteroideActor asteroide;
		for(int i = 0; i < asteroides.size(); i++) {
			if (asteroides.get(i).getRight() < 110) {
				asteroide = asteroides.get(i);
				explosiones.add(new ExplosionActor());
				explosiones.get(explosiones.size()-1).setPosition(asteroides.get(i).getX()-40, asteroides.get(i).getY()-75);
				stage.addActor(explosiones.get(explosiones.size()-1));
				fibooGame.MANAGER.get("naveminigame/older/explosion.ogg", Sound.class).play();
				asteroides.get(i).remove();
				asteroides.remove(i);
				if (escudo.getHealth() > 0.4f) {
					if(asteroide.getNumero() == (numeroX.a + numeroY.b)) {
						Gdx.app.log(fibooGame.LOG, "Colisión de asteroide con escudo solución producida");
						escudo.sumHealth(-0.4f);
						fibooGame.MANAGER.get("naveminigame/older/hit.ogg", Sound.class).play();
					}
				} else  {
					Gdx.app.log(fibooGame.LOG, "Escudo debilitado. Partida terminada.");
					game.setScreen(new GameOverScreen(game));
				}
			}
		}
		
		Gdx.app.log(fibooGame.LOG, "Comprobación de colisión asteroides escudo terminada");
		
		Gdx.app.log(fibooGame.LOG, "Comprobación de balas al final del escenario");
		
		for(int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).getX() > stage.getWidth()) {
				bullets.get(i).remove();
				bullets.remove(i);
			}
		}
		
		Gdx.app.log(fibooGame.LOG, "Comprobación de balas terminada");
		
		Gdx.app.log(fibooGame.LOG, "Recolocando estrellas");
		
		for(int i = 0; i < puntuacionVacia.size(); ++i) {
			puntuacionVacia.get(i).setPosition(10 + i * 46, Gdx.graphics.getHeight() - puntuacionVacia.get(i).getHeight()/2 - 30);
		}
		
		for(int i = 0; i < puntuacion.size(); ++i) {
			puntuacion.get(i).setPosition(10 + i * 46, Gdx.graphics.getHeight() - puntuacion.get(i).getHeight()/2 - 30);
		}
		
		Gdx.app.log(fibooGame.LOG, "Recolocación de estrellas terminada");
		
		Gdx.app.log(fibooGame.LOG, "Limpiando explosiones producidas");
		
		for(int i = 0; i < explosiones.size(); ++i) {
			if (explosiones.get(i).explosionAnimation.isAnimationFinished(explosiones.get(i).stateTime)) {
				explosiones.get(i).remove();
				explosiones.remove(i);
			}
		}
		
		Gdx.app.log(fibooGame.LOG, "Limpieza de explosiones terminadas");
		
	}
	
	private void comprobarColisiones() {
		AsteroideActor asteroide;
		Gdx.app.log(fibooGame.LOG, "Comprobando colisiones entre asteroides y balas o nave");
		for (int i = 0; i < asteroides.size(); i++) {
			asteroide = asteroides.get(i);
			if (asteroide.bb.overlaps(nave.bb)) {
				Gdx.app.log(fibooGame.LOG, "Colisión nave-asteroide producida");
				explosiones.add(new ExplosionActor());
				explosiones.get(explosiones.size()-1).setPosition(asteroides.get(i).getX()-40, asteroides.get(i).getY()-75);
				stage.addActor(explosiones.get(explosiones.size()-1));
				asteroides.get(i).remove();
				asteroides.remove(i);
				nave.sumHealth(-0.4f);
				fibooGame.MANAGER.get("naveminigame/older/hit.ogg", Sound.class).play();
				if (nave.getHealth() <= 0) {
					Gdx.app.log(fibooGame.LOG, "Nave debilitada. Partida terminada.");
					game.setScreen(new GameOverScreen(game));
				}
			} else {
				for (int j = 0; j < bullets.size(); j++) {
					if (asteroide.bb.overlaps(bullets.get(j).bb)) {
						Gdx.app.log(fibooGame.LOG, "Colisión bala-asteroide producida");
						if (asteroides.get(i).getNumero() == (numeroX.a + numeroY.b)) {
							Gdx.app.log(fibooGame.LOG, "Asteroide solución destruido");
							puntuacion.add(new StarActor());
							puntuacion.get(puntuacion.size() - 1).setPosition(10 + (puntuacion.size() - 1) * 46, Gdx.graphics.getHeight() - puntuacion.get(puntuacion.size() - 1).getHeight() / 2 - 30);
							stage.addActor(puntuacion.get(puntuacion.size() - 1));
							resuelto = true;
							for (int k = 0; k < asteroides.size(); k++) {
								explosiones.add(new ExplosionActor());
								explosiones.get(explosiones.size()-1).setPosition(asteroides.get(k).getX()-40, asteroides.get(k).getY()-75);
								stage.addActor(explosiones.get(explosiones.size()-1));
								asteroides.get(k).remove();
							}
							asteroides.clear();
							if (puntuacion.size() == 10) {
								game.setScreen(new WinScreen(game));
							}
						} else {
							Gdx.app.log(fibooGame.LOG, "Asteroide erróneo destruido");
							nave.sumHealth(-0.2f);
							fibooGame.MANAGER.get("naveminigame/older/hit.ogg", Sound.class).play();
							explosiones.add(new ExplosionActor());
							explosiones.get(explosiones.size()-1).setPosition(asteroides.get(i).getX()-40, asteroides.get(i).getY()-75);
							stage.addActor(explosiones.get(explosiones.size()-1));
							if (nave.getHealth() <= 0) {
								game.setScreen(new GameOverScreen(game));
							}
						}
						if (!asteroides.isEmpty()) {
							asteroides.get(i).remove();
							asteroides.remove(i);
						}
						bullets.get(j).remove();
						bullets.remove(j);
						fibooGame.MANAGER.get("naveminigame/older/explosion.ogg", Sound.class).play();
					}
				}
			}
		}
		Gdx.app.log(fibooGame.LOG, "Comprobación de colisiones terminada");
	}
	
	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
	
	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
		if (Gdx.app.getType() == ApplicationType.Android && padShoot != null)
			padShoot.setPosition(stage.getWidth() - 50, 10);
	}

}
