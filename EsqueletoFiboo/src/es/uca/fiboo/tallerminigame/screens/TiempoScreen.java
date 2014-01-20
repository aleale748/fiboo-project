package es.uca.fiboo.tallerminigame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

import es.uca.fiboo.FibooGame;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.MenuMiniJuegosScreen;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TiempoScreen extends AbstractScreen {
	private transient final float hight;
	private transient final float width;
	private transient float tiempo;
	private transient final List<Texture> tornillos;
	private transient final List<Texture> tornillosUsados;
	private transient final Texture robot_triste;
	private transient final Texture robot_alegre;
	private transient final Texture robot_normal;
	private transient final List<Texture> numeros;
	private transient final int numeroTornillos;
	private transient final int estado;
	
	public TiempoScreen(final FibooGame game, final int estado) {
		super(game);
		
		this.estado = estado;
		
		//Calculamos el ancho y alto de la pantalla para escalar
		hight = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();

		//Cargamos el fondo del screen
		robot_triste = FibooGame.MANAGER.get("sacominigame/robottriste.png", Texture.class);
		robot_triste.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		robot_alegre = FibooGame.MANAGER.get("sacominigame/robotalegre.png", Texture.class);
		robot_alegre.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		robot_normal = FibooGame.MANAGER.get("sacominigame/robottallerneutro.png", Texture.class);
		robot_normal.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		//Cargamos el vector de tornillos
		tornillos = new ArrayList<Texture>();
		tornillosUsados = new ArrayList<Texture>();
		numeros = new ArrayList<Texture>();
		
		tornillos.add(FibooGame.MANAGER.get("sacominigame/tornillo2.png", Texture.class));
		tornillos.add(FibooGame.MANAGER.get("sacominigame/tornillo2azul.png", Texture.class));
		tornillos.add(FibooGame.MANAGER.get("sacominigame/tornillo2rojo.png", Texture.class));
		tornillos.add(FibooGame.MANAGER.get("sacominigame/tornillo6.png", Texture.class));
		tornillos.add(FibooGame.MANAGER.get("sacominigame/tornillo6azul.png", Texture.class));
		tornillos.add(FibooGame.MANAGER.get("sacominigame/tornillo6rojo.png", Texture.class));
		tornillos.add(FibooGame.MANAGER.get("sacominigame/circuitogris.png", Texture.class));
		tornillos.add(FibooGame.MANAGER.get("sacominigame/circuitoazul.png", Texture.class));
		tornillos.add(FibooGame.MANAGER.get("sacominigame/circuitorojo.png", Texture.class));
		
		//Cargamos los numeros
		for(int i=0; i < 10; ++i) {
			final Texture numero = FibooGame.MANAGER.get("sacominigame/"+i+".png", Texture.class);
			numero.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			numeros.add(numero);
		}
		
		//Sacamos el numero de objetos (aleatorio) pueden salir de 1 hasta 4 objetos
		
		final Random aleatorio = new Random();
		numeroTornillos = aleatorio.nextInt(4) + 1;
		
		//Segun sea el caso cogemos aleatoriamente los tornillos que queremos que salgan:
		
		for(int i=0; i<numeroTornillos; i++) {
			tornillosUsados.add(tornillos.get(aleatorio.nextInt(tornillos.size())));
		}
		
		tiempo = 0;
		
		//Añadir las estrellitas de la puntuación al stage
		
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
		
		
		if(TallerScreenPrincipal.repeticiones < TallerScreenPrincipal.NUM_REPETICIONES) {
		
			if(tiempo <= 11) {
		
				batch.begin();
				
				if(estado == 0) {
					batch.draw(robot_triste, 0, 0, width, hight); 
				}				
				else if(estado == 1) {
					batch.draw(robot_alegre, 0, 0, width, hight); 
				}
				else {
					batch.draw(robot_normal, 0, 0, width, hight); 
				}
				//Miramos cuantos tornillos debemos colocar:
				
				switch(numeroTornillos) {
				case 1:
					batch.draw(tornillosUsados.get(0), width/1.53f, hight/3.5f, width/10, hight/10);
				break;
				case 2:
					batch.draw(tornillosUsados.get(0), width/1.53f, hight/2.8f, width/10, hight/10);
					batch.draw(tornillosUsados.get(1), width/1.53f, hight/4.5f, width/10, hight/10);
				break;
				case 3:
					batch.draw(tornillosUsados.get(0), width/1.53f, hight/2.7f, width/10, hight/10);
					batch.draw(tornillosUsados.get(1), width/1.42f, hight/4.5f, width/10, hight/10);
					batch.draw(tornillosUsados.get(2), width/1.62f, hight/4.5f, width/10, hight/10);
				break;
				case 4:
					batch.draw(tornillosUsados.get(0), width/1.42f, hight/2.7f, width/10, hight/10);
					batch.draw(tornillosUsados.get(1), width/1.62f, hight/2.7f, width/10, hight/10);
					batch.draw(tornillosUsados.get(2), width/1.42f, hight/4.5f, width/10, hight/10);
					batch.draw(tornillosUsados.get(3), width/1.62f, hight/4.5f, width/10, hight/10);
				break;
				default:
				break;
				}
				
				//Dibujamos los números de la cuenta atrás
				
				if(10 - (int)tiempo == 10) { //Si es 10 dibujamos el 1 y el 0
					batch.draw(numeros.get(1), width/4.5f, hight/2.2f, width/9, hight/7.5f);
					batch.draw(numeros.get(0), width/3.6f, hight/2.2f, width/9, hight/7.5f);
				}			
				else {
					batch.draw(numeros.get(10 - (int)tiempo), width/4.5f, hight/2.2f, width/9, hight/7.5f);
				}
				
				batch.end();
			
				tiempo = tiempo + delta;
			}
			else {	
				TallerScreenPrincipal.repeticiones++;
				dispose();
				game.setScreen(new TallerScreen(game,tornillos,tornillosUsados));
			}		
		}	
		else {
			dispose();
			game.setScreen(new EstadisticasScreen(game)); //Como ya ha realizado todas las repeticiones, mostramos las estadísticas
		}
		
		stage.act(delta);
		stage.draw();
	}
	
	@Override 
	public void show() {
		
		final InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(final int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
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