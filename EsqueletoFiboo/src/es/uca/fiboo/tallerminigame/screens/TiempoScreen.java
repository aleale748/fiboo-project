package es.uca.fiboo.tallerminigame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import es.uca.fiboo.FibooGame;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.MenuMiniJuegosScreen;
import java.util.ArrayList;
import java.util.Random;

public class TiempoScreen extends AbstractScreen {
	private float h;
	private float w;
	private float tiempo;
	private ArrayList<Texture> tornillos;
	private ArrayList<Texture> tornillosUsados;
	private Texture robot_triste;
	private Texture robot_alegre;
	private Texture robot_normal;
	private ArrayList<Texture> numeros;
	private int numeroTornillos;
	private int estado;
	FibooGame game;
	
	public TiempoScreen(FibooGame game, int estado) {
		super(game);
		
		this.game = game;
		
		this.estado = estado;
		
		//Calculamos el ancho y alto de la pantalla para escalar
		h = Gdx.graphics.getHeight();
		w = Gdx.graphics.getWidth();

		
		//Cargamos el fondo del screen
		robot_triste = FibooGame.MANAGER.get("sacominigame/robottriste.png", Texture.class);
		robot_alegre = FibooGame.MANAGER.get("sacominigame/robotalegre.png", Texture.class);
		robot_normal = FibooGame.MANAGER.get("sacominigame/robottallerneutro.png", Texture.class);
		
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
		
		numeros.add(FibooGame.MANAGER.get("sacominigame/0.png", Texture.class));
		numeros.add(FibooGame.MANAGER.get("sacominigame/1.png", Texture.class));
		numeros.add(FibooGame.MANAGER.get("sacominigame/2.png", Texture.class));
		numeros.add(FibooGame.MANAGER.get("sacominigame/3.png", Texture.class));
		numeros.add(FibooGame.MANAGER.get("sacominigame/4.png", Texture.class));
		numeros.add(FibooGame.MANAGER.get("sacominigame/5.png", Texture.class));
		numeros.add(FibooGame.MANAGER.get("sacominigame/6.png", Texture.class));
		numeros.add(FibooGame.MANAGER.get("sacominigame/7.png", Texture.class));
		numeros.add(FibooGame.MANAGER.get("sacominigame/8.png", Texture.class));
		numeros.add(FibooGame.MANAGER.get("sacominigame/9.png", Texture.class));
		
		//Sacamos el numero de objetos (aleatorio) pueden salir de 1 hasta 4 objetos
		
		Random aleatorio = new Random();
		numeroTornillos = aleatorio.nextInt(4) + 1;
		
		//Segun sea el caso cogemos aleatoriamente los tornillos que queremos que salgan:
		
		for(int i=0; i<numeroTornillos; i++) {
			tornillosUsados.add(tornillos.get(aleatorio.nextInt(tornillos.size())));
		}
		
		tiempo = 0;
		
		//Añadir las estrellitas de la puntuación al stage
		
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
		Gdx.app.log(TallerScreenPrincipal.LOG, "El numero de aciertos es"+TallerScreenPrincipal.aciertos);
		Gdx.app.log(TallerScreenPrincipal.LOG, "El numero de repeticiones es"+TallerScreenPrincipal.repeticiones);
		
		if(TallerScreenPrincipal.repeticiones < TallerScreenPrincipal.NUMERO_REPETICIONES) {
		
			if(tiempo <= 11) {
				Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
				Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
				batch.begin();
				
				if(estado == 0) {
					batch.draw(robot_triste, 0, 0, w, h); 
				}
				
				else if(estado == 1) {
					batch.draw(robot_alegre, 0, 0, w, h); 
				}
				
				else {
					batch.draw(robot_normal, 0, 0, w, h); 
				}
				//Miramos cuantos tornillos debemos colocar:
				
				switch(numeroTornillos) {
				case 1:
					batch.draw(tornillosUsados.get(0), w/1.53f, h/3.5f, w/10, h/10);
				break;
				case 2:
					batch.draw(tornillosUsados.get(0), w/1.53f, h/2.8f, w/10, h/10);
					batch.draw(tornillosUsados.get(1), w/1.53f, h/4.5f, w/10, h/10);
				break;
				case 3:
					batch.draw(tornillosUsados.get(0), w/1.53f, h/2.7f, w/10, h/10);
					batch.draw(tornillosUsados.get(1), w/1.42f, h/4.5f, w/10, h/10);
					batch.draw(tornillosUsados.get(2), w/1.62f, h/4.5f, w/10, h/10);
				break;
				case 4:
					batch.draw(tornillosUsados.get(0), w/1.42f, h/2.7f, w/10, h/10);
					batch.draw(tornillosUsados.get(1), w/1.62f, h/2.7f, w/10, h/10);
					batch.draw(tornillosUsados.get(2), w/1.42f, h/4.5f, w/10, h/10);
					batch.draw(tornillosUsados.get(3), w/1.62f, h/4.5f, w/10, h/10);
				break;
				}
				
				//Dibujamos los números de la cuenta atrás
				
				if((10 - (int)tiempo) == 10) { //Si es 10 dibujamos el 1 y el 0
					batch.draw(numeros.get(1), w/4.5f, h/2.2f, w/9, h/7.5f);
					batch.draw(numeros.get(0), w/3.6f, h/2.2f, w/9, h/7.5f);
				}
				
				else {
					batch.draw(numeros.get((10 - (int)tiempo)), w/4.5f, h/2.2f, w/9, h/7.5f);
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
		
		InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(int keycode) {
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
	
	@Override
	public void dispose() {
		super.dispose();
	}

}