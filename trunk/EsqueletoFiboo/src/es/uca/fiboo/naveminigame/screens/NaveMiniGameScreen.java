package es.uca.fiboo.naveminigame.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.naveminigame.actors.*;
import es.uca.fiboo.screens.*;

public class NaveMiniGameScreen extends AbstractScreen {
	
	float widthBullets = 38, heightBullets = 19, widthAsteroides = 128, heightAsteroides = 128, widthExplosiones = 256, heightExplosiones = 256, widthPuntuacion = 42, heightPuntuacion = 40, escala;

	private final class InputAndroidShootListener extends InputListener {
		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			BulletActor bullet = new BulletActor();
			bullet.setWidth(widthBullets);
			bullet.setHeight(heightBullets);
			bullet.setPosition(nave.getX() + nave.getWidth() - nave.getWidth()/4.5f, nave.getY() + (nave.getHeight() - nave.getHeight()/5f));
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
			bullet.setWidth(widthBullets);
			bullet.setHeight(heightBullets);
			bullet.setPosition(nave.getX() + nave.getWidth() - nave.getWidth()/4.5f, nave.getY() + (nave.getHeight() - nave.getHeight()/5f));
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
		
		Gdx.app.log(fibooGame.LOG, "Cargando imagen de fondo y a�adiendola al escenario");
		Image imgFondo = new Image(fibooGame.MANAGER.get("naveminigame/fondonave.png", Texture.class));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);
		

		Gdx.app.log(fibooGame.LOG, "Imagen de fondo a�adida");
		
		explosiones = new ArrayList<ExplosionActor>();
		

		Gdx.app.log(fibooGame.LOG, "Generando nave");
		nave = new NaveActor();
		nave.bb.x = nave.getX();
		nave.bb.y = nave.getY();
		stage.addActor(nave);
		
		nave.setPosition(10, 10);
		
		Gdx.app.log(fibooGame.LOG, "A�adiendo listeners en la nave");
		
		stage.setKeyboardFocus(nave);
		nave.addListener(new InputDesktopListener());
		padShoot = new PadActor();
		padShoot.setPosition(Gdx.graphics.getWidth() - 170, 30);
		padShoot.setWidth(padShoot.getWidth()*2f);
		padShoot.setHeight(padShoot.getHeight()*2f);
			
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

		Gdx.app.log(fibooGame.LOG, "Listeners en la nave a�adidos");
		

		Gdx.app.log(fibooGame.LOG, "A�adiendo bot�n de disparo");
		padShoot.addListener(new InputAndroidShootListener());
		stage.addActor(padShoot);
		Gdx.app.log(fibooGame.LOG, "Bot�n de disparo a�adido.");
		

		Gdx.app.log(fibooGame.LOG, "Generando vidas de la nave y escudo");
		escudo = new EscudoActor();
		stage.addActor(escudo);
		
		vidaNave = new BarraActor(nave);
		vidaNave.setPosition(Gdx.graphics.getWidth() - vidaNave.getWidth()*1.07f, Gdx.graphics.getHeight() - vidaNave.getHeight()*1.63f);
		vidaNave.setHeight(vidaNave.getHeight()*2);
		vidaNave.setWidth(vidaNave.getWidth()*2);
		vidaEscudo = new BarraActor(escudo);
		vidaEscudo.setPosition(Gdx.graphics.getWidth() - vidaEscudo.getWidth()*1.07f, Gdx.graphics.getHeight() - vidaEscudo.getHeight()*2.75f);
		vidaEscudo.setHeight(vidaEscudo.getHeight()*2);
		vidaEscudo.setWidth(vidaEscudo.getWidth()*2);
		
		barraNave = new BarraVaciaActor();
		barraNave.setPosition(Gdx.graphics.getWidth() - vidaNave.getWidth()*1.07f, Gdx.graphics.getHeight() - vidaNave.getHeight()*1.63f);
		barraNave.setHeight(barraNave.getHeight()*2);
		barraNave.setWidth(barraNave.getWidth()*2);
		barraEscudo = new BarraVaciaActor();
		barraEscudo.setPosition(Gdx.graphics.getWidth() - vidaEscudo.getWidth()*1.07f, Gdx.graphics.getHeight() - vidaEscudo.getHeight()*2.75f);
		barraEscudo.setHeight(barraEscudo.getHeight()*2);
		barraEscudo.setWidth(barraEscudo.getWidth()*2);
		
		stage.addActor(barraNave);
		stage.addActor(barraEscudo);
		stage.addActor(vidaNave);
		stage.addActor(vidaEscudo);
		
		Gdx.app.log(fibooGame.LOG, "Vidas de la nave y escudo a�adidas");
		

		Gdx.app.log(fibooGame.LOG, "A�adiendo sistema de puntuacion por estrellas");
		puntuacionVacia = new ArrayList<EmptyStarActor>();
		for (int i = 0; i < 10; ++i) {
			puntuacionVacia.add(new EmptyStarActor());
			puntuacionVacia.get(i).setPosition(widthPuntuacion*0.2f + i * widthPuntuacion*1.1f, Gdx.graphics.getHeight() - heightPuntuacion*1.1f);
			stage.addActor(puntuacionVacia.get(i));
		}
		
		puntuacion = new ArrayList<StarActor>();

		Gdx.app.log(fibooGame.LOG, "Sistema de puntuaci�n por estrellas a�adido");
		

		Gdx.app.log(fibooGame.LOG, "A�adiendo operador de la suma");
		operador = new OperadorActor();
		operador.setPosition(Gdx.graphics.getWidth()/2 - operador.getWidth()/2, 24);
		stage.addActor(operador);

		Gdx.app.log(fibooGame.LOG, "Operador de la suma a�adido");
		
		resuelto = true;
		respawnSol = 0;
		
		timer = 2 + (float) Math.random();
		Gdx.app.log(fibooGame.LOG, "Show realizado");
	}
	
	private float timer;
	
	private float aleatorio1, aleatorio2, widthPrevio = 60, heightPrevio = 100, random;

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		stage.act();
		

		Gdx.app.log(fibooGame.LOG, "Comprobando si la suma ha sido resuelta");
		
		if (resuelto) {
			
			Gdx.app.log(fibooGame.LOG, "Generando nuevos n�meros para la suma");
			if (numeroX != null) {
				widthPrevio = numeroX.getWidth();
				heightPrevio = numeroX.getHeight();
			}
			Gdx.app.log(fibooGame.LOG, "Generando nuevos n�meros para la suma");
			stage.getRoot().removeActor(numeroX);
			stage.getRoot().removeActor(numeroY);
			
			float a = (float) Math.random();
			
			if (a < 0.5f) {
			
				numeroX = new NumeroXActor((int) Math.rint(Math.random() * 9));
				numeroX.setPosition(Gdx.graphics.getWidth()/2 - operador.getWidth()/2 - operador.getWidth()*0.9f, operador.getWidth()*0.3f);
				numeroX.setWidth(widthPrevio);
				numeroX.setHeight(heightPrevio);
				stage.addActor(numeroX);
				
				numeroY = new NumeroYActor((int) Math.rint(Math.random() * (9 - numeroX.a)));
				numeroY.setPosition(Gdx.graphics.getWidth()/2 - operador.getWidth()/2 + operador.getWidth()*1.3f, operador.getWidth()*0.3f);
				numeroY.setWidth(widthPrevio);
				numeroY.setHeight(heightPrevio);
				stage.addActor(numeroY);
			}
			else 
				if (a >= 0.5f) {
					numeroY = new NumeroYActor((int) Math.rint(Math.random() * 9));
					numeroY.setPosition(Gdx.graphics.getWidth()/2 - operador.getWidth()/2 + operador.getWidth()*1.3f, operador.getWidth()*0.3f);
					numeroY.setWidth(widthPrevio);
					numeroY.setHeight(heightPrevio);
					stage.addActor(numeroY);
					
					numeroX = new NumeroXActor((int) Math.rint(Math.random() * (9 - numeroY.b)));
					numeroX.setPosition(Gdx.graphics.getWidth()/2 - operador.getWidth()/2 - operador.getWidth()*0.9f, operador.getWidth()*0.3f);
					numeroX.setWidth(widthPrevio);
					numeroX.setHeight(heightPrevio);
					stage.addActor(numeroX);
				}
			
			resuelto = false;

			Gdx.app.log(fibooGame.LOG, "Generaci�n de nuevos n�meros para la suma terminada");
		}
		

		Gdx.app.log(fibooGame.LOG, "Comprobaci�n de suma resuelta terminada");
		

		
		timer -= delta*0.5;
		if (timer < 0) {
			

			Gdx.app.log(fibooGame.LOG, "Generando asteroides");
			
			AsteroideActor asteroide = new AsteroideActor((int) (Math.random() * 10) % 10, (float) (puntuacion.size()/10f)*(Gdx.graphics.getWidth()*0.1f) + (Gdx.graphics.getWidth()*0.1f));
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
			asteroide.setWidth(widthAsteroides);
			asteroide.setHeight(heightAsteroides);
			random = (float) Math.random();
			if (random > 0.5)
				asteroide.setPosition(Gdx.graphics.getWidth() + Gdx.graphics.getWidth()*0.5f, Gdx.graphics.getHeight() * (aleatorio1 * 0.6f + 0.1f));
			else 
				asteroide.setPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * (aleatorio1 * 0.6f + 0.1f));
			asteroide.velocidad*= escala*1.001f;
			asteroide.bb.x = asteroide.getX();
			asteroide.bb.y = asteroide.getY();
			stage.addActor(asteroide);
			asteroides.add(asteroide);
			if (respawnSol % 1 == 0) {
				AsteroideActor asteroidesol = new AsteroideActor(numeroX.a + numeroY.b, (float) (puntuacion.size()/10f)*(Gdx.graphics.getWidth()*0.1f) + (Gdx.graphics.getWidth()*0.1f));
				if (random > 0.5)
					asteroidesol.setPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * (aleatorio2 * 0.6f + 0.1f));
				else 
					asteroidesol.setPosition(Gdx.graphics.getWidth() + Gdx.graphics.getWidth()*0.5f, Gdx.graphics.getHeight() * (aleatorio2 * 0.6f + 0.1f));
				asteroidesol.velocidad *= escala*1.001f;
				asteroidesol.bb.x = asteroidesol.getX();
				asteroidesol.bb.y = asteroidesol.getY();
				stage.addActor(asteroidesol);
				asteroides.add(asteroidesol);
			}
			timer = 5f + ((float) Math.random() * 2) - (puntuacion.size() * 4 / 10);
			

			Gdx.app.log(fibooGame.LOG, "Generaci�n de asteroides terminada");
		}
		

		Gdx.app.log(fibooGame.LOG, "Comprobando listas de asteroides, explosiones, balas y puntuaciones");
		comprobarListas();
		Gdx.app.log(fibooGame.LOG, "Comprobaci�n de listas terminada");
		

		Gdx.app.log(fibooGame.LOG, "Comprobando colisiones");
		comprobarColisiones();
		Gdx.app.log(fibooGame.LOG, "Comprobaci�n de colisiones terminada");
		

		Gdx.app.log(fibooGame.LOG, "Reposicionamiento de los actores");
		vidaNave.setPosition(Gdx.graphics.getWidth() - vidaNave.getWidth()*1.07f, Gdx.graphics.getHeight() - vidaNave.getHeight()*1.63f);
		barraNave.setPosition(Gdx.graphics.getWidth() - vidaEscudo.getWidth()*1.07f, Gdx.graphics.getHeight() - vidaEscudo.getHeight()*1.63f);
		vidaEscudo.setPosition(Gdx.graphics.getWidth() - vidaNave.getWidth()*1.07f, Gdx.graphics.getHeight() - vidaNave.getHeight()*2.75f);
		barraEscudo.setPosition(Gdx.graphics.getWidth() - vidaEscudo.getWidth()*1.07f, Gdx.graphics.getHeight() - vidaEscudo.getHeight()*2.75f);
	
		numeroX.setPosition(Gdx.graphics.getWidth()/2 - operador.getWidth()/2 - operador.getWidth()*0.9f, operador.getWidth()*0.3f);
		numeroY.setPosition(Gdx.graphics.getWidth()/2 - operador.getWidth()/2 + operador.getWidth()*1.3f, operador.getWidth()*0.3f);
		operador.setPosition(Gdx.graphics.getWidth()/2 - operador.getWidth()/2, operador.getWidth()*0.5f);
		
		respawnSol += 1;
		
		padShoot.setPosition(Gdx.graphics.getWidth() - padShoot.getWidth()*1.05f, padShoot.getHeight()*0.15f);
		

		Gdx.app.log(fibooGame.LOG, "Reposicionamiento terminado");
		
		
		Gdx.app.log(fibooGame.LOG, "Redimensionamiento de los actores");
		escala = ((float) (((Gdx.graphics.getWidth() / 4f) * 100f) / nave.getWidth()) / 100f);
		if (((float) Gdx.graphics.getWidth() - nave.getWidth()) < 500 && ((float) Gdx.graphics.getWidth() - nave.getWidth()) > 0) {
			nave.setWidth(nave.getWidth() * escala);
			nave.setHeight(nave.getHeight() * escala);
			vidaNave.setWidth(vidaNave.getWidth() * escala);
			vidaNave.setHeight(vidaNave.getHeight() * escala);
			vidaEscudo.setWidth(vidaEscudo.getWidth() * escala);
			vidaEscudo.setHeight(vidaEscudo.getHeight() * escala);
			barraNave.setWidth(barraNave.getWidth() * escala);
			barraNave.setHeight(barraNave.getHeight() * escala);
			barraEscudo.setWidth(barraEscudo.getWidth() * escala);
			barraEscudo.setHeight(barraEscudo.getHeight() * escala);
			numeroX.setWidth(numeroX.getWidth() * escala);
			numeroX.setHeight(numeroX.getHeight() * escala);
			numeroY.setWidth(numeroY.getWidth() * escala);
			numeroY.setHeight(numeroY.getHeight() * escala);
			operador.setWidth(operador.getWidth() * escala);
			operador.setHeight(operador.getHeight() * escala);
			padShoot.setWidth(padShoot.getWidth() * escala);
			padShoot.setHeight(padShoot.getHeight() * escala);
			widthBullets = widthBullets * escala;
			heightBullets = heightBullets * escala;
			for (int i = 0; i < bullets.size(); ++i) {
				bullets.get(i).setWidth(widthBullets);
				bullets.get(i).setHeight(heightBullets);
			}
			widthAsteroides = widthAsteroides * escala;
			heightAsteroides = heightAsteroides * escala;
			for (int i = 0; i < asteroides.size(); ++i) {
				asteroides.get(i).setWidth(widthAsteroides);
				asteroides.get(i).setHeight(heightAsteroides);
				asteroides.get(i).setX(asteroides.get(i).getX() * escala);
				asteroides.get(i).setY(asteroides.get(i).getY() * escala);
			}
			widthExplosiones = widthExplosiones * escala;
			heightExplosiones = heightExplosiones * escala;
			widthPuntuacion = widthPuntuacion * escala;
			heightPuntuacion = heightPuntuacion * escala;
			for (int i = 0; i < puntuacion.size(); ++i) {
				puntuacion.get(i).setWidth(widthPuntuacion);
				puntuacion.get(i).setHeight(heightPuntuacion);
			}
			for (int i = 0; i < puntuacionVacia.size(); ++i) {
				puntuacionVacia.get(i).setWidth(widthPuntuacion);
				puntuacionVacia.get(i).setHeight(heightPuntuacion);
			}
		}
		else if (((float) Gdx.graphics.getWidth() - nave.getWidth()) >= 500 && ((float) Gdx.graphics.getWidth() - nave.getWidth()) > 0) {
			nave.setWidth(nave.getWidth() * escala);
			nave.setHeight(nave.getHeight() * escala);
			vidaNave.setWidth(vidaNave.getWidth() * escala);
			vidaNave.setHeight(vidaNave.getHeight() * escala);
			vidaEscudo.setWidth(vidaEscudo.getWidth() * escala);
			vidaEscudo.setHeight(vidaEscudo.getHeight() * escala);
			barraNave.setWidth(barraNave.getWidth() * escala);
			barraNave.setHeight(barraNave.getHeight() * escala);
			barraEscudo.setWidth(barraEscudo.getWidth() * escala);
			barraEscudo.setHeight(barraEscudo.getHeight() * escala);
			numeroX.setWidth(numeroX.getWidth() * escala);
			numeroX.setHeight(numeroX.getHeight() * escala);
			numeroY.setWidth(numeroY.getWidth() * escala);
			numeroY.setHeight(numeroY.getHeight() * escala);
			operador.setWidth(operador.getWidth() * escala);
			operador.setHeight(operador.getHeight() * escala);
			padShoot.setWidth(padShoot.getWidth() * escala);
			padShoot.setHeight(padShoot.getHeight() * escala);
			widthBullets = widthBullets * escala;
			heightBullets = heightBullets * escala;
			for (int i = 0; i < bullets.size(); ++i) {
				bullets.get(i).setWidth(widthBullets);
				bullets.get(i).setHeight(heightBullets);
			}
			widthAsteroides = widthAsteroides * escala;
			heightAsteroides = heightAsteroides * escala;
			for (int i = 0; i < asteroides.size(); ++i) {
				asteroides.get(i).setWidth(widthAsteroides);
				asteroides.get(i).setHeight(heightAsteroides);
				asteroides.get(i).setX(asteroides.get(i).getX() * escala);
				asteroides.get(i).setY(asteroides.get(i).getY() * escala);
			}
			widthExplosiones = widthExplosiones * escala;
			heightExplosiones = heightExplosiones * escala;
			widthPuntuacion = widthPuntuacion * escala;
			heightPuntuacion = heightPuntuacion * escala;
			for (int i = 0; i < puntuacion.size(); ++i) {
				puntuacion.get(i).setWidth(widthPuntuacion);
				puntuacion.get(i).setHeight(heightPuntuacion);
			}
			for (int i = 0; i < puntuacionVacia.size(); ++i) {
				puntuacionVacia.get(i).setWidth(widthPuntuacion);
				puntuacionVacia.get(i).setHeight(heightPuntuacion);
			}
		}
		
		Gdx.app.log(fibooGame.LOG, "Redimensionamiento de los actores terminado");
		
		stage.draw();
	}
	
	private void comprobarListas() {
		

		Gdx.app.log(fibooGame.LOG, "Comprobando colisi�n asteroide escudo");
		
		AsteroideActor asteroide;
		for(int i = 0; i < asteroides.size(); i++) {
			if (asteroides.get(i).getRight() < widthAsteroides*0.9f) {
				asteroide = asteroides.get(i);
				explosiones.add(new ExplosionActor());
				explosiones.get(explosiones.size()-1).setPosition(asteroides.get(i).getX()*0.98f, asteroides.get(i).getY()*0.75f);
				explosiones.get(explosiones.size()-1).setWidth(widthExplosiones);
				explosiones.get(explosiones.size()-1).setHeight(heightExplosiones);
				stage.addActor(explosiones.get(explosiones.size()-1));
				fibooGame.MANAGER.get("naveminigame/older/explosion.ogg", Sound.class).play();
				asteroides.get(i).remove();
				asteroides.remove(i);
				if (escudo.getHealth() > 0.4f) {
					if(asteroide.getNumero() == (numeroX.a + numeroY.b)) {
						Gdx.app.log(fibooGame.LOG, "Colisi�n de asteroide con escudo soluci�n producida");
						escudo.sumHealth(-0.4f);
						fibooGame.MANAGER.get("naveminigame/older/hit.ogg", Sound.class).play();
					}
				} else  {
					Gdx.app.log(fibooGame.LOG, "Escudo debilitado. Partida terminada.");
					game.setScreen(new GameOverScreen(game));
				}
			}
		}
		
		Gdx.app.log(fibooGame.LOG, "Comprobaci�n de colisi�n asteroides escudo terminada");
		
		Gdx.app.log(fibooGame.LOG, "Comprobaci�n de balas al final del escenario");
		
		for(int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).getX() > stage.getWidth()) {
				bullets.get(i).remove();
				bullets.remove(i);
			}
		}
		
		Gdx.app.log(fibooGame.LOG, "Comprobaci�n de balas terminada");
		
		Gdx.app.log(fibooGame.LOG, "Recolocando estrellas");
		
		for(int i = 0; i < puntuacionVacia.size(); ++i) {
			puntuacionVacia.get(i).setPosition(widthPuntuacion*0.2f + i * widthPuntuacion*1.1f, Gdx.graphics.getHeight() - heightPuntuacion*1.1f);
		}
		
		for(int i = 0; i < puntuacion.size(); ++i) {
			puntuacion.get(i).setPosition(widthPuntuacion*0.2f + i * widthPuntuacion*1.1f, Gdx.graphics.getHeight() - heightPuntuacion*1.1f);
		}
		
		Gdx.app.log(fibooGame.LOG, "Recolocaci�n de estrellas terminada");
		
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
				Gdx.app.log(fibooGame.LOG, "Colisi�n nave-asteroide producida");
				explosiones.add(new ExplosionActor());
				explosiones.get(explosiones.size()-1).setPosition(asteroides.get(i).getX()*0.98f, asteroides.get(i).getY()*0.75f);
				explosiones.get(explosiones.size()-1).setWidth(widthExplosiones);
				explosiones.get(explosiones.size()-1).setHeight(heightExplosiones);
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
						Gdx.app.log(fibooGame.LOG, "Colisi�n bala-asteroide producida");
						if (asteroides.get(i).getNumero() == (numeroX.a + numeroY.b)) {
							Gdx.app.log(fibooGame.LOG, "Asteroide soluci�n destruido");
							puntuacion.add(new StarActor());
							puntuacion.get(puntuacion.size() - 1).setPosition(widthPuntuacion*0.2f + (puntuacion.size() - 1) * widthPuntuacion*1.1f, Gdx.graphics.getHeight() - heightPuntuacion*1.1f);
							puntuacion.get(puntuacion.size() - 1).setWidth(widthPuntuacion);
							puntuacion.get(puntuacion.size() - 1).setHeight(heightPuntuacion);
							stage.addActor(puntuacion.get(puntuacion.size() - 1));
							resuelto = true;
							for (int k = 0; k < asteroides.size(); k++) {
								explosiones.add(new ExplosionActor());
								explosiones.get(explosiones.size()-1).setPosition(asteroides.get(k).getX()*0.98f, asteroides.get(k).getY()*0.75f);
								explosiones.get(explosiones.size()-1).setWidth(widthExplosiones);
								explosiones.get(explosiones.size()-1).setHeight(heightExplosiones);
								stage.addActor(explosiones.get(explosiones.size()-1));
								asteroides.get(k).remove();
							}
							asteroides.clear();
							if (puntuacion.size() == 10) {
								game.setScreen(new WinScreen(game));
							}
						} else {
							Gdx.app.log(fibooGame.LOG, "Asteroide err�neo destruido");
							nave.sumHealth(-0.2f);
							fibooGame.MANAGER.get("naveminigame/older/hit.ogg", Sound.class).play();
							explosiones.add(new ExplosionActor());
							explosiones.get(explosiones.size()-1).setPosition(asteroides.get(i).getX()*0.98f, asteroides.get(i).getY()*0.75f);
							explosiones.get(explosiones.size()-1).setWidth(widthExplosiones);
							explosiones.get(explosiones.size()-1).setHeight(heightExplosiones);
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
		Gdx.app.log(fibooGame.LOG, "Comprobaci�n de colisiones terminada");
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
