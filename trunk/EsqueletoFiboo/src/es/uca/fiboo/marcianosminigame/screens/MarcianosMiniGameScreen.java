package es.uca.fiboo.marcianosminigame.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
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

import es.uca.fiboo.FibooGame;
import es.uca.fiboo.marcianosminigame.actors.BienActor;
import es.uca.fiboo.marcianosminigame.actors.BotonActor;
import es.uca.fiboo.marcianosminigame.actors.MarcianoActor;
import es.uca.fiboo.marcianosminigame.actors.NaveActor;
import es.uca.fiboo.marcianosminigame.actors.NaveMarcianoActor;
import es.uca.fiboo.marcianosminigame.actors.NumeroActor;
import es.uca.fiboo.marcianosminigame.actors.PreguntaActor;
import es.uca.fiboo.naveminigame.actors.EmptyStarActor;
import es.uca.fiboo.naveminigame.actors.StarActor;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.GameOverScreen;
import es.uca.fiboo.screens.MenuMiniJuegosScreen;
import es.uca.fiboo.screens.WinScreen;

public class MarcianosMiniGameScreen extends AbstractScreen {
	private transient float pantallaWeight, pantallaHeight;
	private transient float widthPuntuacion = 42, heightPuntuacion = 40, tamNaves = 128, tamMarcianos = 128, 
			widthPregunta = 1024, heightPregunta = 128, widthBien = 512, heightBien = 128, widthNumero = 60, heightNumero = 100,
			widthBoton = 202, heightBoton = 205;
	private transient List<MarcianoActor> marcianos;
	private transient List<NaveActor> naves;
	private transient List<NaveMarcianoActor> navesMarcianos;
	private transient boolean resuelto, mostrarBien, movimiento, mostrarPregunta;
	private transient NumeroActor numeroNaves, numeroMarcianos;
	private transient int numMarcInt;
	private transient BienActor bien;
	private transient int contadorBien;
	private transient List<StarActor> puntuacion;
	private transient List<EmptyStarActor> puntuacionVacia;
	private transient int numeroMalInt, numeroSolInt;
	private transient NumeroActor numeroMal, numeroSol;
	private transient int numNaves, numMarcianos, cont, respuesta;
	private transient PreguntaActor pregunta;
	private transient BotonActor boton1, boton2;
	private transient Image baseestrellas;
	private transient final int PRIMERO = 0, SEGUNDO = 1, PAR = 0, INCORRECTA = 3, CORRECTA = 2, RESPONDIDA = 1;;
	private transient Music musicaFondo;
	
	public MarcianosMiniGameScreen(final FibooGame game) {
		super(game);
		//Mientras no hay pantalla de ayuda
		FibooGame.MANAGER.loadMarcianosMiniGameTextures();
		FibooGame.MANAGER.finishLoading();
		FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).stop();	
		//----------------------------------
	}

	@Override
	public void show() {
		musicaFondo = FibooGame.MANAGER.get("sonidos/marcianos.ogg", Music.class);
		musicaFondo.setLooping(true);
		musicaFondo.play();
		pantallaWeight= Gdx.graphics.getWidth();
		pantallaHeight= Gdx.graphics.getHeight();
		final Image imgFondo = new Image(FibooGame.MANAGER.get("robotgame/fondoestrellas.png", Texture.class));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);
		baseestrellas = new Image(FibooGame.MANAGER.get("portada/base.png", Texture.class));
		stage.addActor(baseestrellas);
		cont = 0;
		mostrarBien = false;
		bien = new BienActor();
		contadorBien = 0;
		respuesta = 1;
		
		////Gdx.app.log(fibooGame.LOG, "Comienzo de show.");
		
		final InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(final int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
						//fibooGame.MANAGER.get("sonidos/fondo.mp3", Sound.class).loop();
						musicaFondo.stop();
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
		for (int i = 0; i < 5; ++i) {
			puntuacionVacia.add(new EmptyStarActor());
			stage.addActor(puntuacionVacia.get(i));
		}
		
		puntuacion = new ArrayList<StarActor>();
		
		movimiento = false;
		mostrarPregunta = false;
		
		////Gdx.app.log(fibooGame.LOG, "Show terminado.");
	}
	
	@Override
	public void render(final float delta) {
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		stage.act();
		
		if (movimiento) {
			if (navesMarcianos.get(navesMarcianos.size()-1).getX() > pantallaWeight) {
				for (int i = 0; i < numNaves; ++i) {
					navesMarcianos.get(i).remove();
				}
				navesMarcianos.clear();
				movimiento = false;
			}
		}
		else {
			if (mostrarPregunta) {
			pregunta = new PreguntaActor();
			pregunta.setPosition(pantallaWeight/2f - widthPregunta/2f, pantallaHeight * 2.5f / 4f);
			pregunta.setWidth(widthPregunta);
			pregunta.setHeight(heightPregunta);
			stage.addActor(pregunta);
			FibooGame.MANAGER.get("sonidos/cuantosquedan.ogg", Sound.class).play();
			
			boton1 = new BotonActor();
			boton2 = new BotonActor();
			stage.addActor(boton1);
			stage.addActor(boton2);
			
			numeroMalInt = numMarcInt;
			if (Math.random() > 0.5f && numMarcInt != 0) {
				numeroMalInt = MathUtils.random(0,numMarcInt-1);
			}
			else {
				if (numMarcInt != 9) {
					numeroMalInt = MathUtils.random(numMarcInt+1, 9);
				}
			}
			numeroSolInt = numMarcInt;
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
				numeroMal.setPosition(pantallaWeight/2  - widthBoton*1.03f, pantallaHeight/2.40f);
				boton2.setPosition(pantallaWeight/2 - widthBoton*1.3f, pantallaHeight/3f);
				numeroSol.setPosition(pantallaWeight/2 + widthBoton/1.28f, pantallaHeight/2.40f);
				boton1.setPosition(pantallaWeight/2 + widthBoton/2, pantallaHeight/3f);
			}
			else {
				numeroSol.setPosition(pantallaWeight/2  - widthBoton*1.03f, pantallaHeight/2.40f);
				boton1.setPosition(pantallaWeight/2 - widthBoton*1.3f, pantallaHeight/3f);
				numeroMal.setPosition(pantallaWeight/2 + widthBoton/1.28f, pantallaHeight/2.40f);
				boton2.setPosition(pantallaWeight/2 + widthBoton/2, pantallaHeight/3f);
			}
			
			stage.addActor(numeroMal);
			stage.addActor(numeroSol);
			
			boton2.addListener(new InputListener() {
				@Override
				public boolean touchDown(final InputEvent event, final float x, final float y,
						final int pointer, final int button) {
					respuesta = 3;
					return super.touchDown(event, x, y, pointer, button);
				}
			});
			
			boton1.addListener(new InputListener() {
				@Override
				public boolean touchDown(final InputEvent event, final float x, final float y,
						final int pointer, final int button) {
					respuesta = 2;
					return super.touchDown(event, x, y, pointer, button);
				}
			});
			
			numeroMal.addListener(new InputListener() {
				@Override
				public boolean touchDown(final InputEvent event, final float x, final float y,
						final int pointer, final int button) {
					respuesta = 3;
					return super.touchDown(event, x, y, pointer, button);
				}
			});
			
			numeroSol.addListener(new InputListener() {
				@Override
				public boolean touchDown(final InputEvent event, final float x, final float y,
						final int pointer, final int button) {
					respuesta = 2;
					return super.touchDown(event, x, y, pointer, button);
				}
			});
			mostrarPregunta = false;
			}
		if (respuesta == INCORRECTA) {
			musicaFondo.stop();
			dispose();
			game.setScreen(new GameOverScreen(game));
		}
		else
			if (respuesta == CORRECTA) {
				respuesta = 1;
				puntuacion.add(new StarActor());
				puntuacion.get(puntuacion.size() - 1).setPosition(widthPuntuacion*0.2f + (puntuacion.size() - 1) * widthPuntuacion*1.1f, pantallaHeight - heightPuntuacion*1.1f);
				puntuacion.get(puntuacion.size() - 1).setWidth(widthPuntuacion);
				puntuacion.get(puntuacion.size() - 1).setHeight(heightPuntuacion);
				stage.addActor(puntuacion.get(puntuacion.size() - 1));
				
				if (puntuacion.size() == 5) {
					musicaFondo.stop();
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
				if (respuesta == RESPONDIDA) {
					
						if (mostrarBien) {
							bien.setPosition(pantallaWeight/2.5f, pantallaHeight/2.2f);
							if (contadorBien < 30) {
								if (contadorBien == PRIMERO) {
									FibooGame.MANAGER.get("sonidos/bien.ogg", Sound.class).play();
									stage.addActor(bien);
									////Gdx.app.log(fibooGame.LOG, "Bien a�adido.");
								}
								if (contadorBien < 8) {
									bien.setWidth(widthBien*1.06f);
									bien.setHeight(heightBien*1.06f);
								}
				
								
								contadorBien++;
							}
							else {
								mostrarBien = false;
								////Gdx.app.log(fibooGame.LOG, "Buclesito acabado." + mostrarBien);
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
								
								////Gdx.app.log(fibooGame.LOG, "Generando n�mero de naves y de marcianos.");
								numNaves = MathUtils.random(1, 5);
								numMarcianos = MathUtils.random(numNaves, 9);
								numMarcInt = numMarcianos;
								numeroNaves = new NumeroActor(numNaves);
								numeroMarcianos = new NumeroActor(numMarcInt);
								numeroNaves.setPosition(pantallaWeight * 0.9f, pantallaHeight * 0.4f);
								numeroMarcianos.setPosition(pantallaWeight * 0.225f, pantallaHeight * 0.8f);
								numeroNaves.setWidth(widthNumero);
								numeroNaves.setHeight(heightNumero);
								
								stage.addActor(numeroNaves);
								//stage.addActor(numeroMarcianos);
								
								/*//Gdx.app.log(fibooGame.LOG, "N�mero de naves y de marcianos generado: " + 
								numNaves + " naves y " + numMarcianos + " marcianos.");*/
								
								////Gdx.app.log(fibooGame.LOG, "Generando naves.");
								if (numNaves%2 == PAR) {
									for (int i = 0; i < numNaves; ++i) {
										naves.add(new NaveActor());
										if (i == PRIMERO) {
											naves.get(i).setPosition(pantallaWeight * 0.75f, pantallaHeight * 0.4f + tamNaves*1.1f*0.6f);
										}
										else {
											if (i == SEGUNDO) {
												naves.get(i).setPosition(pantallaWeight * 0.75f, pantallaHeight * 0.4f - tamNaves*1.1f*0.6f);
											}
											else {
												if (i%2 == PAR) {
													naves.get(i).setPosition(pantallaWeight * 0.75f, pantallaHeight * 0.4f + tamNaves*1.1f*0.6f + tamNaves*1.1f*(i/2));
												}
												else {
													naves.get(i).setPosition(pantallaWeight * 0.75f, pantallaHeight * 0.4f - tamNaves*1.1f*0.6f - tamNaves*1.1f*(i/2));
												}
											}
										}
										naves.get(i).setWidth(tamNaves);
										naves.get(i).setHeight(tamNaves);
										stage.addActor(naves.get(i));
									}
								}
								else {
									for (int i = 0; i < numNaves; ++i) {
										naves.add(new NaveActor());
										if (i == PRIMERO) {
											naves.get(i).setPosition(pantallaWeight * 0.75f, pantallaHeight * 0.4f);
										}
										else {
											if (i % 2 == PAR) {
												naves.get(i).setPosition(pantallaWeight * 0.75f, pantallaHeight * 0.4f - tamNaves*1.1f*(i/2));
											}
											else {
												naves.get(i).setPosition(pantallaWeight * 0.75f, pantallaHeight * 0.4f + tamNaves*1.1f* (float) Math.ceil((float) i/2f));
											}
										}
										naves.get(i).setWidth(tamNaves);
										naves.get(i).setHeight(tamNaves);
										stage.addActor(naves.get(i));
									}
								}
								////Gdx.app.log(fibooGame.LOG, "Naves generadas.");
								
								////Gdx.app.log(fibooGame.LOG, "Generando marcianos.");
								for (int i = 0; i < numMarcianos; ++i) {
									marcianos.add(new MarcianoActor());
									if (i == PRIMERO) {
										marcianos.get(i).setPosition(pantallaWeight * 0.01f,pantallaHeight * (float) Math.random() * 0.6f);
									}
									else
										if (i%2 == PAR) {
											marcianos.get(i).setPosition(((float) Math.random() * 0.1f + 
													0.7f) * (pantallaWeight * 0.01f + 
															(float) tamMarcianos * i), 
															pantallaHeight * (float) Math.random() * 0.6f);
										}
										else {
											if (marcianos.get(i-1).getY() < pantallaHeight * 0.3f) {
												marcianos.get(i).setPosition(((float) Math.random() * 0.2f + 
														0.7f) * (pantallaWeight * 0.01f + 
																(float) tamMarcianos * (i-1)), ((float) Math.random() * 0.2f + 
																		0.7f) * (
														marcianos.get(i-1).getY() + tamMarcianos));
											}
											else {
												marcianos.get(i).setPosition(((float) Math.random() * 0.2f + 
														0.7f) * (pantallaWeight * 0.01f + 
																(float) tamMarcianos * (i-1)), ((float) Math.random() * 0.2f + 
																		0.7f) * (
														marcianos.get(i-1).getY() - tamMarcianos));
											}
										}
									marcianos.get(i).setWidth(tamMarcianos);
									marcianos.get(i).setHeight(tamMarcianos);
									stage.addActor(marcianos.get(i));
								}
								////Gdx.app.log(fibooGame.LOG, "Marcianos generados.");
								
								resuelto = false;
								cont = 0;
							}
						
							if (cont > 0) {
								comprobarOverlaps();
							}
							else {
								cont++;
							}
						}
					}
				}
				stage.draw();
	}
	
	private void comprobarOverlaps() {
		NaveActor nave;
		MarcianoActor marciano;
		////Gdx.app.log(fibooGame.LOG, "Comprobando choque nave-marciano");
		for (int i = 0; i < naves.size(); ++i) {
			nave = naves.get(i);
			if (nave.colocado() && !nave.verificado()) {
				FibooGame.MANAGER.get("sonidos/yuju.ogg", Sound.class).play();
				nave.verificar();
				numMarcInt--;
				numeroMarcianos.remove();
				numeroMarcianos = new NumeroActor(numMarcInt);
				numeroMarcianos.setPosition(pantallaWeight * 0.225f, pantallaHeight * 0.8f);
				//stage.addActor(numeroMarcianos);
			}
			for (int j = 0; j < marcianos.size(); ++j) {
				marciano = marcianos.get(j);
					if (nave.rectangleNave.overlaps(marciano.rectangleMarciano)) {
							marciano.donde(nave);
					}
			}
		}

		////Gdx.app.log(fibooGame.LOG, "Comprobacion choque nave-marciano terminada.");
		

		////Gdx.app.log(fibooGame.LOG, "Comprobando si estan todas las naves cubiertas");
		resuelto = true;
		for (int i = 0; i < naves.size(); ++i) {
			if (!naves.get(i).colocado()) {
				resuelto = false;
			}
		}

		////Gdx.app.log(fibooGame.LOG, "Comprobacion terminada");
		if (resuelto) {

			////Gdx.app.log(fibooGame.LOG, "Todas las naves cubiertas");
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
			////Gdx.app.log(fibooGame.LOG, "Naves-marcianos de la solucion eliminados");
			if (numNaves%2 == PAR) {
				for (int i = 0; i < numNaves; ++i) {
					navesMarcianos.add(new NaveMarcianoActor());
					if (i == PRIMERO) {
						navesMarcianos.get(i).setPosition(pantallaWeight * 0.75f, pantallaHeight * 0.4f + tamNaves*1.1f*0.6f);
					}
					else {
						if (i == SEGUNDO) {
							navesMarcianos.get(i).setPosition(pantallaWeight * 0.75f, pantallaHeight * 0.4f - tamNaves*1.1f*0.6f);
						}
						else {
							if (i%2 == PAR) {
								navesMarcianos.get(i).setPosition(pantallaWeight * 0.75f, pantallaHeight * 0.4f + tamNaves*1.1f*0.6f + tamNaves*1.1f*(i/2));
							}
							else {
								navesMarcianos.get(i).setPosition(pantallaWeight * 0.75f, pantallaHeight * 0.4f - tamNaves*1.1f*0.6f - tamNaves*1.1f*(i/2));
							}
						}
					}
					navesMarcianos.get(i).setWidth(tamMarcianos);
					navesMarcianos.get(i).setHeight(tamMarcianos);
					stage.addActor(navesMarcianos.get(i));
				}
			}
			else {
				for (int i = 0; i < numNaves; ++i) {
					navesMarcianos.add(new NaveMarcianoActor());
					if (i == PRIMERO) {
						navesMarcianos.get(i).setPosition(pantallaWeight * 0.75f, pantallaHeight * 0.4f);
					}
					else {
						if (i % 2 == PAR) {
							navesMarcianos.get(i).setPosition(pantallaWeight * 0.75f, pantallaHeight * 0.4f - tamNaves*1.1f*(i/2));
						}
						else {
							navesMarcianos.get(i).setPosition(pantallaWeight * 0.75f, pantallaHeight * 0.4f + tamNaves*1.1f* (float) Math.ceil((float) i/2f));
						}
					}
					navesMarcianos.get(i).setWidth(tamMarcianos);
					navesMarcianos.get(i).setHeight(tamMarcianos);
					stage.addActor(navesMarcianos.get(i));
				}
			}
			////Gdx.app.log(fibooGame.LOG, "Naves-marcianos solucion generados");
			numeroNaves.remove();
			mostrarBien = true;
			movimiento = true;
			mostrarPregunta = true;
	
			respuesta = 0;
		}
	}
	
	@Override
	public void dispose() {
		FibooGame.MANAGER.unloadMarcianosMiniGameTextures();
		FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).setLooping(true);
		FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).play();
		super.dispose();
	}

	@Override
	public void resize(final int width, final int height) {
		float widthBasePunt= 120, heightBasePunt= 90;
		final float escala2 = (float) (pantallaWeight / 4f) / 256;
		final float escala = (float) (pantallaHeight / 6f) / tamNaves;
		widthPuntuacion *= escala2;
		heightPuntuacion *= escala2;
		widthBasePunt = pantallaWeight*0.27f;
		heightBasePunt = pantallaHeight*0.32f;
		tamNaves *= escala;
		tamMarcianos *= escala;
		widthPregunta *= escala;
		heightPregunta *= escala;
		widthBien *= escala; 
		heightBien *= escala; 
		widthNumero *= escala;
		heightNumero *= escala;
		widthBoton *= escala;
		heightBoton *= escala;
		for (int i = 0; i < 5; ++i) {
			puntuacionVacia.get(i).setWidth(widthPuntuacion);
			puntuacionVacia.get(i).setHeight(heightPuntuacion);
			puntuacionVacia.get(i).setPosition(widthPuntuacion*0.2f + i * widthPuntuacion*1.1f, pantallaHeight - heightPuntuacion*1.1f);
		}
		bien.setWidth(widthBien);
		bien.setHeight(heightBien);
		baseestrellas.setPosition(pantallaWeight*0.12f - widthBasePunt/2f, pantallaHeight-heightBasePunt/2f);
		baseestrellas.setWidth(widthBasePunt);
		baseestrellas.setHeight(heightBasePunt);
		stage.setViewport(width, height, true);
	}

}
