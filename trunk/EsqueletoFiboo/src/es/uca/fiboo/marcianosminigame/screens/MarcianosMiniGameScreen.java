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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
	private float escala, widthPuntuacion = 42, heightPuntuacion = 40, tamNaves = 128, tamMarcianos = 128, 
			widthPregunta = 1024, heightPregunta = 128, widthBien = 512, heightBien = 128, widthNumero = 60, heightNumero = 100,
			widthBoton = 202, heightBoton = 205;
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
	private int numeroMalInt, numeroSolInt;
	private MarcianoActor marciano;
	private NaveActor nave;
	private NumeroActor numeroMal, numeroSol;
	private int numNaves, numMarcianos, cont, respuesta;
	private PreguntaActor pregunta;
	private BotonActor boton1, boton2;
	
	@Override
	public void show() {

		escala = ((float) ((Gdx.graphics.getHeight() / 6f) / tamNaves));
		tamMarcianos *= escala;
		Image imgFondo = new Image(fibooGame.MANAGER.get("naveminigame/fondonave.png", Texture.class));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);
		
		cont = 0;
		mostrarBien = false;
		bien = new BienActor();
		contadorBien = 0;
		respuesta = 1;
		
		Gdx.app.log(fibooGame.LOG, "Comienzo de show.");
		
		InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
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
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		stage.act();
		if (respuesta == 3) {
			dispose();
			game.setScreen(new GameOverScreen(game));
		}
		else
			if (respuesta == 2) {
				respuesta = 1;
				puntuacion.add(new StarActor());
				puntuacion.get(puntuacion.size() - 1).setPosition(widthPuntuacion*0.2f + (puntuacion.size() - 1) * widthPuntuacion*1.1f, Gdx.graphics.getHeight() - heightPuntuacion*1.1f);
				puntuacion.get(puntuacion.size() - 1).setWidth(widthPuntuacion);
				puntuacion.get(puntuacion.size() - 1).setHeight(heightPuntuacion);
				stage.addActor(puntuacion.get(puntuacion.size() - 1));
				
				if (puntuacion.size() == 10) {
					dispose();
					game.setScreen(new WinScreen(game));
				}
				numeroMal.remove();
				numeroSol.remove();
				pregunta.remove();
				boton1.remove();
				boton2.remove();
			} 
			else
				if (respuesta == 1) {
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
							bien.setWidth(widthBien*1.06f);
							bien.setHeight(heightBien*1.06f);
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
						
						Gdx.app.log(fibooGame.LOG, "Generando n�mero de naves y de marcianos.");
						numNaves = (int) (Math.random() * 5) % 5 + 1;
						numMarcianos = numNaves + (int) (Math.random() * (9 - numNaves)) % (9 - numNaves) + 1;
						numeroMarcianosInt = numMarcianos;
						numeroNaves = new NumeroActor(numNaves);
						numeroMarcianos = new NumeroActor(numeroMarcianosInt);
						numeroNaves.setPosition(Gdx.graphics.getWidth() * 0.9f, Gdx.graphics.getHeight() * 0.4f);
						numeroMarcianos.setPosition(Gdx.graphics.getWidth() * 0.225f, Gdx.graphics.getHeight() * 0.8f);
						numeroNaves.setWidth(widthNumero);
						numeroNaves.setHeight(heightNumero);
						
						stage.addActor(numeroNaves);
						//stage.addActor(numeroMarcianos);
						
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
							if (i == 0) {
								marcianos.get(i).setPosition(Gdx.graphics.getWidth() * 0.01f,Gdx.graphics.getHeight() * (float) Math.random() * 0.6f);
							}
							else
								if (i%2 == 0f)
									marcianos.get(i).setPosition(((float) Math.random() * 0.1f + 
											0.7f) * (Gdx.graphics.getWidth() * 0.01f + 
													(float) tamMarcianos * i), 
													Gdx.graphics.getHeight() * (float) Math.random() * 0.6f);
								else
									if (marcianos.get(i-1).getY() < Gdx.graphics.getHeight() * 0.3f)
										marcianos.get(i).setPosition(((float) Math.random() * 0.2f + 
												0.7f) * (Gdx.graphics.getWidth() * 0.01f + 
														(float) tamMarcianos * (i-1)), ((float) Math.random() * 0.2f + 
																0.7f) * (
												marcianos.get(i-1).getY() + tamMarcianos));
									else
										marcianos.get(i).setPosition(((float) Math.random() * 0.2f + 
												0.7f) * (Gdx.graphics.getWidth() * 0.01f + 
														(float) tamMarcianos * (i-1)), ((float) Math.random() * 0.2f + 
																0.7f) * (
												marcianos.get(i-1).getY() - tamMarcianos));
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
				}
				redimensionar();
				stage.draw();
	}
	
	private void comprobarOverlaps() {
		Gdx.app.log(fibooGame.LOG, "Comprobando choque nave-marciano");
		for (int i = 0; i < naves.size(); ++i) {
			nave = naves.get(i);
			if (nave.colocado() && !nave.verificado()) {
				nave.verificar();
				numeroMarcianosInt--;
				numeroMarcianos.remove();
				numeroMarcianos = new NumeroActor(numeroMarcianosInt);
				numeroMarcianos.setPosition(Gdx.graphics.getWidth() * 0.225f, Gdx.graphics.getHeight() * 0.8f);
				//stage.addActor(numeroMarcianos);
			}
			for (int j = 0; j < marcianos.size(); ++j) {
				marciano = marcianos.get(j);
					if (nave.bb.overlaps(marciano.bb))
							marciano.donde(nave);
			}
		}

		Gdx.app.log(fibooGame.LOG, "Comprobacion choque nave-marciano terminada.");
		

		Gdx.app.log(fibooGame.LOG, "Comprobando si estan todas las naves cubiertas");
		resuelto = true;
		for (int i = 0; i < naves.size(); ++i) {
			if (!naves.get(i).colocado())
				resuelto = false;
		}

		Gdx.app.log(fibooGame.LOG, "Comprobacion terminada");
		if (resuelto) {

			Gdx.app.log(fibooGame.LOG, "Todas las naves cubiertas");
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
			Gdx.app.log(fibooGame.LOG, "Naves-marcianos de la solucion eliminados");
			if (numNaves%2 == 0) {
				for (int i = 0; i < numNaves; ++i) {
					navesMarcianos.add(new NaveMarcianoActor());
					if (i == 0)
						navesMarcianos.get(i).setPosition(Gdx.graphics.getWidth() * 0.75f, Gdx.graphics.getHeight() * 0.4f + tamNaves*1.1f*0.6f);
					else
						if (i == 1)
							navesMarcianos.get(i).setPosition(Gdx.graphics.getWidth() * 0.75f, Gdx.graphics.getHeight() * 0.4f - tamNaves*1.1f*0.6f);
						else
							if (i%2 == 0)
								navesMarcianos.get(i).setPosition(Gdx.graphics.getWidth() * 0.75f, Gdx.graphics.getHeight() * 0.4f + tamNaves*1.1f*0.6f + tamNaves*1.1f*(i/2));
							else
								navesMarcianos.get(i).setPosition(Gdx.graphics.getWidth() * 0.75f, Gdx.graphics.getHeight() * 0.4f - tamNaves*1.1f*0.6f - tamNaves*1.1f*(i/2));
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
							navesMarcianos.get(i).setPosition(Gdx.graphics.getWidth() * 0.75f, Gdx.graphics.getHeight() * 0.4f - tamNaves*1.1f*(i/2));
						else
							navesMarcianos.get(i).setPosition(Gdx.graphics.getWidth() * 0.75f, Gdx.graphics.getHeight() * 0.4f + tamNaves*1.1f* (float) Math.ceil((float) i/2f));
					stage.addActor(navesMarcianos.get(i));
				}
			Gdx.app.log(fibooGame.LOG, "Naves-marcianos solucion generados");
			numeroNaves.remove();
			mostrarBien = true;
			movimiento = true;
			
			pregunta = new PreguntaActor();
			pregunta.setPosition(Gdx.graphics.getWidth()/2f - widthPregunta/2f, Gdx.graphics.getHeight() * 2.5f / 4f);
			pregunta.setWidth(widthPregunta);
			pregunta.setHeight(heightPregunta);
			stage.addActor(pregunta);
			
			boton1 = new BotonActor();
			boton2 = new BotonActor();
			stage.addActor(boton1);
			stage.addActor(boton2);
			
			numeroMalInt = numeroMarcianosInt;
			if (Math.random() > 0.5f)
				numeroMalInt = (int) (Math.random() * (numeroMarcianosInt - 1));
			else
				numeroMalInt = numeroMarcianosInt + (int) (Math.random() * (numMarcianos - numeroMarcianosInt - 1)) + 1 ;
			numeroSolInt = numeroMarcianosInt;
			numeroMal = new NumeroActor(numeroMalInt);
			numeroSol = new NumeroActor(numeroSolInt);
			numeroMal.setWidth(widthNumero);
			numeroMal.setHeight(heightNumero);
			numeroSol.setWidth(widthNumero);
			numeroSol.setHeight(heightNumero);
			boton1.setWidth(widthBoton);
			boton1.setHeight(heightBoton);
			boton2.setWidth(widthBoton);
			boton2.setHeight(heightBoton);
			if (Math.random() > 0.5f) {
				numeroMal.setPosition(Gdx.graphics.getWidth()/2  - widthBoton*0.92f, Gdx.graphics.getHeight()/2.2f);
				boton2.setPosition(Gdx.graphics.getWidth()/2 - widthBoton*1.3f, Gdx.graphics.getHeight()/2.5f);
				numeroSol.setPosition(Gdx.graphics.getWidth()/2 + widthBoton/1.1f, Gdx.graphics.getHeight()/2.2f);
				boton1.setPosition(Gdx.graphics.getWidth()/2 + widthBoton/2, Gdx.graphics.getHeight()/2.5f);
			}
			else {
				numeroSol.setPosition(Gdx.graphics.getWidth()/2  - widthBoton*0.92f, Gdx.graphics.getHeight()/2.2f);
				boton1.setPosition(Gdx.graphics.getWidth()/2 - widthBoton*1.3f, Gdx.graphics.getHeight()/2.5f);
				numeroMal.setPosition(Gdx.graphics.getWidth()/2 + widthBoton/1.1f, Gdx.graphics.getHeight()/2.2f);
				boton2.setPosition(Gdx.graphics.getWidth()/2 + widthBoton/2, Gdx.graphics.getHeight()/2.5f);
			}
			
			stage.addActor(numeroMal);
			stage.addActor(numeroSol);
			
			boton2.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					respuesta = 3;
					return super.touchDown(event, x, y, pointer, button);
				}
			});
			
			boton1.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					respuesta = 2;
					return super.touchDown(event, x, y, pointer, button);
				}
			});
			
			numeroMal.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					respuesta = 3;
					return super.touchDown(event, x, y, pointer, button);
				}
			});
			
			numeroSol.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					respuesta = 2;
					return super.touchDown(event, x, y, pointer, button);
				}
			});
			
			respuesta = 0;
		}
		

	}
	
	private void redimensionar() {

		Gdx.app.log(fibooGame.LOG, "Redimensiomiento empezado.");
		escala = ((float) ((Gdx.graphics.getHeight() / 6f) / naves.get(0).getHeight()));
		for (int i = 0; i < marcianos.size(); ++i) {
			marciano = marcianos.get(i);
			marciano.setWidth(tamMarcianos);
			marciano.setHeight(tamMarcianos);
		}
		tamNaves *= escala;
		for (int i = 0; i < naves.size(); ++i) {
			nave = naves.get(i);
			nave.setWidth(tamNaves);
			nave.setHeight(tamNaves);
		}
		for (int i = 0; i < navesMarcianos.size(); ++i) {
			navesMarcianos.get(i).setWidth(tamNaves);
			navesMarcianos.get(i).setHeight(tamNaves);
		}
		widthPuntuacion = widthPuntuacion * escala;
		heightPuntuacion = heightPuntuacion * escala;
		for (int i = 0; i < puntuacionVacia.size(); ++i) {
			puntuacionVacia.get(i).setPosition(widthPuntuacion*0.2f + (i) * widthPuntuacion*1.1f, Gdx.graphics.getHeight() - heightPuntuacion*1.1f);
			puntuacionVacia.get(i).setWidth(widthPuntuacion);
			puntuacionVacia.get(i).setHeight(heightPuntuacion);
			puntuacionVacia.get(i).toFront();
		}
		for (int i = 0; i < puntuacion.size(); ++i) {
			puntuacion.get(i).setPosition(widthPuntuacion*0.2f + (i) * widthPuntuacion*1.1f, Gdx.graphics.getHeight() - heightPuntuacion*1.1f);
			puntuacion.get(i).setWidth(widthPuntuacion);
			puntuacion.get(i).setHeight(heightPuntuacion);
			puntuacion.get(i).toFront();
		}
		widthPregunta *= escala;
		heightPregunta *= escala;
		widthBien *= escala;
		heightBien *= escala;
		
		if (naves.size() != 0 && naves.size()%2 == 0) {
			for (int i = 0; i < naves.size(); ++i) {
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
			}
		}
		else
			for (int i = 0; i < naves.size(); ++i) {
				if (i == 0) 
					naves.get(i).setPosition(Gdx.graphics.getWidth() * 0.75f, Gdx.graphics.getHeight() * 0.4f);
				else 
					if (i % 2 == 0)
						naves.get(i).setPosition(Gdx.graphics.getWidth() * 0.75f, Gdx.graphics.getHeight() * 0.4f - naves.get(i).getWidth()*1.1f*(i/2));
					else
						naves.get(i).setPosition(Gdx.graphics.getWidth() * 0.75f, Gdx.graphics.getHeight() * 0.4f + naves.get(i).getWidth()*1.1f* (float) Math.ceil((float) i/2f));
			}
		
		widthNumero *= escala;
		heightNumero *= escala;
		widthBoton *= escala;
		heightBoton *= escala;
		
		if (resuelto == false) {
			numeroNaves.setWidth(widthNumero);
			numeroNaves.setHeight(heightNumero);
			numeroNaves.setPosition(Gdx.graphics.getWidth() * 0.9f, Gdx.graphics.getHeight() * 0.4f);
		}
		
		Gdx.app.log(fibooGame.LOG, "Redimensiomiento terminado.");
	}
	
	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
	}

}