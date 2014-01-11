package es.uca.fiboo.marcianosminigame.screens;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.Marshaller.Listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.marcianosminigame.actors.*;
import es.uca.fiboo.naveminigame.actors.EmptyStarActor;
import es.uca.fiboo.naveminigame.actors.StarActor;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.MenuMiniJuegosScreen;

public class MarcianosMiniGameScreen extends AbstractScreen {

	public MarcianosMiniGameScreen(fibooGame game) {
		super(game);
	}
	private int widthPuntuacion = 42, heightPuntuacion = 40;
	private List<MarcianoActor> marcianos;
	private List<NaveActor> naves;
	private List<NaveMarcianoActor> navesMarcianos;
	private boolean resuelto, mostrarBien, movimiento;
	private NumeroActor numeroNaves, numeroMarcianos;
	private int numeroMarcianosInt;
	private BienActor bien;
	private int contadorBien;
	private List<StarActor> puntuacion;
	private List<EmptyStarActor> puntuacionVacia;
	
	@Override
	public void show() {
		
		cont = 0;
		mostrarBien = false;
		bien = new BienActor();
		contadorBien = 0;
		
		Gdx.app.log(fibooGame.LOG, "Comienzo de show.");
		
		InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(int keycode) {
				if (keycode == Keys.BACK){
						dispose();
						game.setScreen(new MenuMiniJuegosScreen(game));
				}
				return false;
			}
		}, stage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		resuelto = true;
		marcianos = new ArrayList<MarcianoActor>();
		naves = new ArrayList<NaveActor>();
		navesMarcianos = new ArrayList<NaveMarcianoActor>();
		
		puntuacionVacia = new ArrayList<EmptyStarActor>();
		for (int i = 0; i < 10; ++i) {
			puntuacionVacia.add(new EmptyStarActor());
			puntuacionVacia.get(i).setPosition(widthPuntuacion*0.2f + i * widthPuntuacion*1.1f, Gdx.graphics.getHeight() - heightPuntuacion*1.1f);
			stage.addActor(puntuacionVacia.get(i));
		}
		
		puntuacion = new ArrayList<StarActor>();
		
		Gdx.app.log(fibooGame.LOG, "Show terminado.");
		
		
	}
	
	private int numNaves, numMarcianos, cont;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		stage.act();
		
		if (movimiento) {
			if (navesMarcianos.get(navesMarcianos.size()-1).getX() > Gdx.graphics.getWidth()) {
				for (int i = 0; i < numNaves; ++i) {
					navesMarcianos.get(i).remove();
				}
				navesMarcianos.clear();
				movimiento = false;
			}
		}
		else {
		if (mostrarBien) {
			bien.setPosition(Gdx.graphics.getWidth()/2.5f, Gdx.graphics.getHeight()/2.2f);
			if (contadorBien != 30) {
				Gdx.app.log(fibooGame.LOG, "Buclesito.");
				if (contadorBien == 0) {
					stage.addActor(bien);
					Gdx.app.log(fibooGame.LOG, "Bien a�adido.");
				}
				if (contadorBien < 8) {
					bien.setWidth(bien.getWidth()*1.06f);
					bien.setHeight(bien.getHeight()*1.06f);
				}

				
				contadorBien++;
			}
			else {
				mostrarBien = false;
				Gdx.app.log(fibooGame.LOG, "Buclesito acabado." + mostrarBien);
				bien.remove();
				bien = new BienActor();
				contadorBien = 0;
				Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
			}
			
		}
		else {
			if (resuelto) {
				for (int i = 0; i < marcianos.size(); ++i) {
					marcianos.get(i).remove();
				}
				marcianos.clear();
				for (int i = 0; i < naves.size(); ++i) {
					naves.get(i).remove();
				}
				naves.clear();
			
				if (numeroNaves != null) {
					numeroNaves.remove();
					numeroMarcianos.remove();
				}
				
				Gdx.app.log(fibooGame.LOG, "Generando n�mero de naves y de marcianos.");
				numNaves = (int) (Math.random() * 5) % 5 + 1;
				numMarcianos = numNaves + (int) (Math.random() * (9 - numNaves)) % (9 - numNaves) + 1;
				numeroMarcianosInt = numMarcianos;
				numeroNaves = new NumeroActor(numNaves);
				numeroMarcianos = new NumeroActor(numeroMarcianosInt);
				numeroNaves.setPosition(Gdx.graphics.getWidth() * 0.9f, Gdx.graphics.getHeight() * 0.4f);
				numeroMarcianos.setPosition(Gdx.graphics.getWidth() * 0.225f, Gdx.graphics.getHeight() * 0.8f);
				
				stage.addActor(numeroNaves);
				stage.addActor(numeroMarcianos);
				
				Gdx.app.log(fibooGame.LOG, "N�mero de naves y de marcianos generado: " + numNaves + " naves y " + numMarcianos + " marcianos.");
				
				Gdx.app.log(fibooGame.LOG, "Generando naves.");
				if (numNaves%2 == 0) {
					for (int i = 0; i < numNaves; ++i) {
						naves.add(new NaveActor());
						if (i == 0)
							naves.get(i).setPosition(Gdx.graphics.getWidth() * 0.75f, Gdx.graphics.getHeight() * 0.4f + naves.get(i).getWidth()*1.1f*0.6f);
						else
							if (i == 1)
								naves.get(i).setPosition(Gdx.graphics.getWidth() * 0.75f, Gdx.graphics.getHeight() * 0.4f - naves.get(i).getWidth()*1.1f*0.6f);
							else
								if (i%2 == 0)
									naves.get(i).setPosition(Gdx.graphics.getWidth() * 0.75f, Gdx.graphics.getHeight() * 0.4f + naves.get(i).getWidth()*1.1f*0.6f + naves.get(i).getWidth()*1.1f*(i/2));
								else
									naves.get(i).setPosition(Gdx.graphics.getWidth() * 0.75f, Gdx.graphics.getHeight() * 0.4f - naves.get(i).getWidth()*1.1f*0.6f - naves.get(i).getWidth()*1.1f*(i/2));
						stage.addActor(naves.get(i));
					}
				}
				else
					for (int i = 0; i < numNaves; ++i) {
						naves.add(new NaveActor());
						if (i == 0) 
							naves.get(i).setPosition(Gdx.graphics.getWidth() * 0.75f, Gdx.graphics.getHeight() * 0.4f);
						else 
							if (i % 2 == 0)
								naves.get(i).setPosition(Gdx.graphics.getWidth() * 0.75f, Gdx.graphics.getHeight() * 0.4f - naves.get(i).getWidth()*1.1f*(i/2));
							else
								naves.get(i).setPosition(Gdx.graphics.getWidth() * 0.75f, Gdx.graphics.getHeight() * 0.4f + naves.get(i).getWidth()*1.1f* (float) Math.ceil((float) i/2f));
						stage.addActor(naves.get(i));
					}
				Gdx.app.log(fibooGame.LOG, "Naves generadas.");
				
				Gdx.app.log(fibooGame.LOG, "Generando marcianos.");
				for (int i = 0; i < numMarcianos; ++i) {
					marcianos.add(new MarcianoActor());
					marcianos.get(i).setPosition(Gdx.graphics.getWidth() * (float) (Math.random() * 0.45f), Gdx.graphics.getHeight() * (float) Math.random() * 0.6f);
					stage.addActor(marcianos.get(i));
				}
				Gdx.app.log(fibooGame.LOG, "Marcianos generados.");
				
				resuelto = false;
				cont = 0;
			}
		
			if (cont > 0) 
				comprobarOverlaps();
			else 
				cont++;
		}
		}
		
		stage.draw();
		
	}
	
	private MarcianoActor marciano;
	private NaveActor nave;
	
	private void comprobarOverlaps() {

		for (int i = 0; i < naves.size(); ++i) {
			nave = naves.get(i);
			if (nave.colocado() && !nave.verificado()) {
				nave.verificar();
				numeroMarcianosInt--;
				numeroMarcianos.remove();
				numeroMarcianos = new NumeroActor(numeroMarcianosInt);
				numeroMarcianos.setPosition(Gdx.graphics.getWidth() * 0.225f, Gdx.graphics.getHeight() * 0.8f);
				stage.addActor(numeroMarcianos);
			}
			for (int j = 0; j < marcianos.size(); ++j) {
				marciano = marcianos.get(j);
					if (nave.bb.overlaps(marciano.bb))
							marciano.donde(nave);
			}
		}
		
		resuelto = true;
		for (int i = 0; i < naves.size(); ++i) {
			if (!naves.get(i).colocado())
				resuelto = false;
		}
		if (resuelto) {
			puntuacion.add(new StarActor());
			puntuacion.get(puntuacion.size() - 1).setPosition(widthPuntuacion*0.2f + (puntuacion.size() - 1) * widthPuntuacion*1.1f, Gdx.graphics.getHeight() - heightPuntuacion*1.1f);
			puntuacion.get(puntuacion.size() - 1).setWidth(widthPuntuacion);
			puntuacion.get(puntuacion.size() - 1).setHeight(heightPuntuacion);
			stage.addActor(puntuacion.get(puntuacion.size() - 1));
			for (int i = 0; i < marcianos.size(); ++i) {
				marciano = marcianos.get(i);
				if (marciano.colocado()) {
					marciano.remove();
				}
			}
			for (int i = 0; i < naves.size(); ++i) {
				nave = naves.get(i);
				if (nave.colocado()) {
					nave.remove();
				}
			}
			
			if (numNaves%2 == 0) {
				for (int i = 0; i < numNaves; ++i) {
					navesMarcianos.add(new NaveMarcianoActor());
					if (i == 0)
						navesMarcianos.get(i).setPosition(Gdx.graphics.getWidth() * 0.75f, Gdx.graphics.getHeight() * 0.4f + navesMarcianos.get(i).getWidth()*1.1f*0.6f);
					else
						if (i == 1)
							navesMarcianos.get(i).setPosition(Gdx.graphics.getWidth() * 0.75f, Gdx.graphics.getHeight() * 0.4f - navesMarcianos.get(i).getWidth()*1.1f*0.6f);
						else
							if (i%2 == 0)
								navesMarcianos.get(i).setPosition(Gdx.graphics.getWidth() * 0.75f, Gdx.graphics.getHeight() * 0.4f + navesMarcianos.get(i).getWidth()*1.1f*0.6f + navesMarcianos.get(i).getWidth()*1.1f*(i/2));
							else
								navesMarcianos.get(i).setPosition(Gdx.graphics.getWidth() * 0.75f, Gdx.graphics.getHeight() * 0.4f - navesMarcianos.get(i).getWidth()*1.1f*0.6f - navesMarcianos.get(i).getWidth()*1.1f*(i/2));
					stage.addActor(navesMarcianos.get(i));
				}
			}
			else
				for (int i = 0; i < numNaves; ++i) {
					navesMarcianos.add(new NaveMarcianoActor());
					if (i == 0) 
						navesMarcianos.get(i).setPosition(Gdx.graphics.getWidth() * 0.75f, Gdx.graphics.getHeight() * 0.4f);
					else 
						if (i % 2 == 0)
							navesMarcianos.get(i).setPosition(Gdx.graphics.getWidth() * 0.75f, Gdx.graphics.getHeight() * 0.4f - navesMarcianos.get(i).getWidth()*1.1f*(i/2));
						else
							navesMarcianos.get(i).setPosition(Gdx.graphics.getWidth() * 0.75f, Gdx.graphics.getHeight() * 0.4f + navesMarcianos.get(i).getWidth()*1.1f* (float) Math.ceil((float) i/2f));
					stage.addActor(navesMarcianos.get(i));
				}

			mostrarBien = true;
			movimiento = true;
			
		}
		

	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}
	
	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
	}

}
