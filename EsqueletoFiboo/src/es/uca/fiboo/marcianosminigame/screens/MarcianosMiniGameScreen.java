package es.uca.fiboo.marcianosminigame.screens;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.Marshaller.Listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.marcianosminigame.actors.*;
import es.uca.fiboo.screens.AbstractScreen;

public class MarcianosMiniGameScreen extends AbstractScreen {

	public MarcianosMiniGameScreen(fibooGame game) {
		super(game);
	}
	
	private List<MarcianoActor> marcianos;
	private List<NaveActor> naves;
	private boolean resuelto;
	private NumeroActor numeroNaves, numeroMarcianos;
	private int numeroMarcianosInt;
	
	@Override
	public void show() {
		
		cont = 0;
		
		Gdx.app.log(fibooGame.LOG, "Comienzo de show.");
		
		Gdx.input.setInputProcessor(stage);
		resuelto = true;
		marcianos = new ArrayList<MarcianoActor>();
		naves = new ArrayList<NaveActor>();
		Gdx.app.log(fibooGame.LOG, "Show terminado.");
		
	}
	
	private int numNaves, numMarcianos, cont;
	
	@Override
	public void render(float delta) {

		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		stage.act();
		
		if (resuelto) {
			
			if (numeroNaves != null) {
				numeroNaves.remove();
				numeroMarcianos.remove();
			}
			
			Gdx.app.log(fibooGame.LOG, "Generando número de naves y de marcianos.");
			numNaves = (int) (Math.random() * 5) % 5 + 1;
			numMarcianos = numNaves + (int) (Math.random() * (9 - numNaves)) % (9 - numNaves) + 1;
			numeroMarcianosInt = numMarcianos;
			numeroNaves = new NumeroActor(numNaves);
			numeroMarcianos = new NumeroActor(numeroMarcianosInt);
			numeroNaves.setPosition(Gdx.graphics.getWidth() * 0.9f, Gdx.graphics.getHeight() * 0.4f);
			numeroMarcianos.setPosition(Gdx.graphics.getWidth() * 0.225f, Gdx.graphics.getHeight() * 0.8f);
			
			stage.addActor(numeroNaves);
			stage.addActor(numeroMarcianos);
			
			Gdx.app.log(fibooGame.LOG, "Número de naves y de marcianos generado: " + numNaves + " naves y " + numMarcianos + " marcianos.");
			
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
		
		stage.draw();
		
	}
	
	private MarcianoActor marciano;
	private NaveActor nave;
	private float velocidadX, velocidadY;
	
	private void comprobarOverlaps() {
		velocidadX = Gdx.graphics.getWidth()/500f;
		velocidadY = Gdx.graphics.getWidth()/500f;
		for (int j = 0; j < marcianos.size(); ++j) {
			marciano = marcianos.get(j);
			for (int i = 0; i < naves.size(); ++i) {
				nave = naves.get(i);
				if (!nave.colocado() && marciano.bb.overlaps(nave.bb)) {
					if (nave.getX() < marciano.getX())
						velocidadX = - velocidadX;
					if (nave.getY() < marciano.getY())
						velocidadY = - velocidadY;
					marciano.translate(velocidadX, velocidadY);
					if ((Math.abs(nave.getX()/Gdx.graphics.getWidth() - marciano.getX()/Gdx.graphics.getWidth()) < 0.01) && 
							(Math.abs(nave.getY()/Gdx.graphics.getHeight() - marciano.getY()/Gdx.graphics.getHeight()) < 0.01)) {
						marciano.translate(0, 0);
						marciano.setPosition(nave.getX(), nave.getY());
						marciano.colocar();
						nave.colocar();
						numeroMarcianosInt--;
						numeroMarcianos.remove();
						numeroMarcianos = new NumeroActor(numeroMarcianosInt);
						numeroMarcianos.setPosition(Gdx.graphics.getWidth() * 0.225f, Gdx.graphics.getHeight() * 0.8f);
						stage.addActor(numeroMarcianos);
					}
					else {
						if (Math.abs(nave.getX()/Gdx.graphics.getWidth() - marciano.getX()/Gdx.graphics.getWidth()) < 0.01)
							marciano.translate(0, velocidadY);
						if (Math.abs(nave.getY()/Gdx.graphics.getHeight() - marciano.getY()/Gdx.graphics.getHeight()) < 0.01)
							marciano.translate(velocidadX, 0);
					}
					//marciano.setPosition(nave.getX(), nave.getY());
				}
			}
		}
		resuelto = true;
		for (int i = 0; i < naves.size(); ++i) {
			if (!naves.get(i).colocado())
				resuelto = false;
		}
		
		if (resuelto) {
			for (int i = 0; i < marcianos.size(); ++i) {
				marcianos.get(i).remove();
			}
			marcianos.clear();
			for (int i = 0; i < naves.size(); ++i) {
				naves.get(i).remove();
			}
			naves.clear();
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
