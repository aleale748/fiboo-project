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

public class GameplayAlienScreen extends AbstractScreen {

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

	/*private final class InputAndroidDownListener extends InputListener {
		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			nave.velocidad.y = -250;
			return true;
		}

		@Override
		public void touchUp(InputEvent event, float x, float y,
				int pointer, int button) {
			nave.velocidad.y = 0;
		}
	}

	private final class InputAndroidUpListener extends InputListener {
		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			nave.velocidad.y = 250;
			return true;
		}

		@Override
		public void touchUp(InputEvent event, float x, float y,
				int pointer, int button) {
			nave.velocidad.y = 0;
		}
	}*/

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
			bullet.setPosition(nave.getWidth() - 55, nave.getY() + (nave.getHeight() - 27));
			bullet.bb.x = bullet.getX();
			bullet.bb.y = bullet.getY();
			stage.addActor(bullet);
			bullets.add(bullet);
			fibooGame.MANAGER.get("naveminigame/older/shoot.ogg", Sound.class).play();
			
			return true;
		}
	}
	
	public GameplayAlienScreen(fibooGame game) {
		super(game);
		Gdx.app.log(fibooGame.LOG, "Bien construido.");
	}

	private NaveActor nave;
	
	private PadActor padArriba, padAbajo, padShoot;
	
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
	
	private List<AlienActor> aliens;
	
	private List<BulletActor> bullets;
	
	private List<ExplosionActor> explosiones;
	
	@Override
	public void show() {
		
		aliens = new ArrayList<AlienActor>();
		bullets = new ArrayList<BulletActor>();

		Gdx.input.setInputProcessor(stage);
		
		Gdx.app.log(fibooGame.LOG, "" + fibooGame.MANAGER.getProgress());
		Image imgFondo = new Image(fibooGame.MANAGER.get("naveminigame/fondonave.png", Texture.class));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);
		

		explosiones = new ArrayList<ExplosionActor>();

		nave = new NaveActor();
		nave.bb.x = nave.getX();
		nave.bb.y = nave.getY();
		stage.addActor(nave);
		
		nave.setPosition(10, 10);
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
			
			padShoot.addListener(new InputAndroidShootListener());
			stage.addActor(padShoot);
		//}
		
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
		
		puntuacionVacia = new ArrayList<EmptyStarActor>();
		for (int i = 0; i < 10; ++i) {
			puntuacionVacia.add(new EmptyStarActor());
			puntuacionVacia.get(i).setPosition(10 + i * 46, Gdx.graphics.getHeight() - puntuacionVacia.get(i).getHeight()/2 - 30);
			stage.addActor(puntuacionVacia.get(i));
		}
		
		puntuacion = new ArrayList<StarActor>();
		
		operador = new OperadorActor();
		operador.setPosition(Gdx.graphics.getWidth()/2 - operador.getWidth()/2, 24);
		stage.addActor(operador);
		
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
		
		if (resuelto) {
			stage.getRoot().removeActor(numeroX);
			stage.getRoot().removeActor(numeroY);
			
			numeroX = new NumeroXActor((int) (Math.random() * 5) % 10);
			numeroX.setPosition(Gdx.graphics.getWidth()/2 - operador.getWidth()/2 - 50, 10);
			stage.addActor(numeroX);
			
			numeroY = new NumeroYActor((int) (Math.random() * 5) % 10);
			numeroY.setPosition(Gdx.graphics.getWidth()/2 - operador.getWidth()/2 + 80, 10);
			stage.addActor(numeroY);
			
			resuelto = false;
		}
		
		timer -= delta;
		if (timer < 0) {
			AlienActor alien = new AlienActor((int) (Math.random() * 10) % 10, (float) puntuacion.size()/10);
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
			alien.setPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * (aleatorio1 * 0.6f + 0.1f));
			alien.bb.x = alien.getX();
			alien.bb.y = alien.getY();
			stage.addActor(alien);
			aliens.add(alien);
			if (respawnSol % 2 == 0) {
				AlienActor alienSol = new AlienActor(numeroX.a + numeroY.b, (float) puntuacion.size()/10);
				alienSol.setPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * (aleatorio2 * 0.6f + 0.1f));
				alienSol.bb.x = alienSol.getX();
				alienSol.bb.y = alienSol.getY();
				stage.addActor(alienSol);
				aliens.add(alienSol);
			}
			timer = 1.5f + (float) Math.random();
		}
		comprobarListas();
		comprobarColisiones();
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
		
		stage.draw();
		
	}
	
	private void comprobarListas() {
		AlienActor alien;
		for(int i = 0; i < aliens.size(); i++) {
			if (aliens.get(i).getRight() < 110) {
				alien = aliens.get(i);
				explosiones.add(new ExplosionActor());
				explosiones.get(explosiones.size()-1).setPosition(aliens.get(i).getX()-40, aliens.get(i).getY()-75);
				stage.addActor(explosiones.get(explosiones.size()-1));
				fibooGame.MANAGER.get("naveminigame/older/explosion.ogg", Sound.class).play();
				aliens.get(i).remove();
				aliens.remove(i);
				if (escudo.getHealth() > 0.4f) {
					if(alien.getNumero() == (numeroX.a + numeroY.b)) {
						escudo.sumHealth(-0.4f);
						fibooGame.MANAGER.get("naveminigame/older/hit.ogg", Sound.class).play();
					}
				} else  {
					game.setScreen(new GameOverScreen(game));
				}
			}
		}
		
		for(int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).getX() > stage.getWidth()) {
				bullets.get(i).remove();
				bullets.remove(i);
			}
		}
		
		for(int i = 0; i < puntuacionVacia.size(); ++i) {
			puntuacionVacia.get(i).setPosition(10 + i * 46, Gdx.graphics.getHeight() - puntuacionVacia.get(i).getHeight()/2 - 30);
		}
		
		for(int i = 0; i < puntuacion.size(); ++i) {
			puntuacion.get(i).setPosition(10 + i * 46, Gdx.graphics.getHeight() - puntuacion.get(i).getHeight()/2 - 30);
		}
		
		for(int i = 0; i < explosiones.size(); ++i) {
			if (explosiones.get(i).explosionAnimation.isAnimationFinished(explosiones.get(i).stateTime)) {
				explosiones.get(i).remove();
				explosiones.remove(i);
			}
		}
		
	}
	
	private void comprobarColisiones() {
		AlienActor alien;
		for (int i = 0; i < aliens.size(); i++) {
			alien = aliens.get(i);
			if (alien.bb.overlaps(nave.bb)) {
				explosiones.add(new ExplosionActor());
				explosiones.get(explosiones.size()-1).setPosition(aliens.get(i).getX()-40, aliens.get(i).getY()-75);
				stage.addActor(explosiones.get(explosiones.size()-1));
				aliens.get(i).remove();
				aliens.remove(i);
				nave.sumHealth(-0.4f);
				fibooGame.MANAGER.get("naveminigame/older/hit.ogg", Sound.class).play();
				if (nave.getHealth() <= 0) {
					game.setScreen(new GameOverScreen(game));
				}
			} else {
				for (int j = 0; j < bullets.size(); j++) {
					if (alien.bb.overlaps(bullets.get(j).bb)) {
						if (aliens.get(i).getNumero() == (numeroX.a + numeroY.b)) {
							nave.puntos++;
							puntuacion.add(new StarActor());
							puntuacion.get(puntuacion.size()-1).setPosition(10 + (nave.puntos - 1) * 46, Gdx.graphics.getHeight() - puntuacion.get(puntuacion.size()-1).getHeight()/2 - 30);
							stage.addActor(puntuacion.get(puntuacion.size()-1));
							resuelto = true;
							for (int k = 0; k < aliens.size(); k++) {
								explosiones.add(new ExplosionActor());
								explosiones.get(explosiones.size()-1).setPosition(aliens.get(k).getX()-40, aliens.get(k).getY()-75);
								stage.addActor(explosiones.get(explosiones.size()-1));
								aliens.get(k).remove();
							}
							aliens.clear();
							if (puntuacion.size() == 10) {
								game.setScreen(new MenuScreen(game));
							}
						} else {
							nave.sumHealth(-0.2f);
							fibooGame.MANAGER.get("naveminigame/older/hit.ogg", Sound.class).play();
							explosiones.add(new ExplosionActor());
							explosiones.get(explosiones.size()-1).setPosition(aliens.get(i).getX()-40, aliens.get(i).getY()-75);
							stage.addActor(explosiones.get(explosiones.size()-1));
							if (nave.getHealth() <= 0) {
								game.setScreen(new GameOverScreen(game));
							}
						}
						if (!aliens.isEmpty()) {
							aliens.get(i).remove();
							aliens.remove(i);
						}
						bullets.get(j).remove();
						bullets.remove(j);
						fibooGame.MANAGER.get("naveminigame/older/explosion.ogg", Sound.class).play();
					}
				}
			}
		}
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
