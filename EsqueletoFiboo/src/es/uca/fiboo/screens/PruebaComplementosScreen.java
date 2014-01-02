package es.uca.fiboo.screens;

import java.util.ArrayList;
import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.actores.BotonCategoria;
import es.uca.fiboo.actores.BotonComplemento;
import es.uca.fiboo.actores.Complemento;
import es.uca.fiboo.actores.Complemento.Tipo;

public class PruebaComplementosScreen extends AbstractScreen {

	private ArrayList<BotonComplemento> botones;
	private ArrayList<BotonCategoria> botonesCat;
	private Skin skin;
	
	public PruebaComplementosScreen(fibooGame game) {
		super(game);
		Gdx.input.setInputProcessor(stage);
		
		// Para probar que se carga bien el skin con todos los ficheros
		FileHandle skinFile = Gdx.files.internal( "skin/uiskin.json" );
        skin = new Skin( skinFile );
		
		botones = new ArrayList<BotonComplemento>();
		botonesCat = new ArrayList<BotonCategoria>();
		cargaComplementos();
		
		// Tabla para colocar los iconos
		Table table = new Table(skin);
		table.setPosition(700f, 390f);
        stage.addActor(table);
        table.debug();
        
        int newRow = 0;
		for(BotonComplemento b : botones) {
			table.add(b).width(128).height(128);
			newRow++;
			// 4 complementos por cada fila
			if(newRow > 3) {
				newRow = 0;
				table.row();
			}
		}
	}
	
	//Carga todos los complementos habidos y por haber
	private void cargaComplementos() {
		
		TreeMap<Tipo, ArrayList<Complemento>> complementosPorTipo;
		complementosPorTipo = new TreeMap<Tipo, ArrayList<Complemento>>();
		complementosPorTipo.put(Tipo.PELO, new ArrayList<Complemento>());
		complementosPorTipo.put(Tipo.ACCPELO, new ArrayList<Complemento>());
		complementosPorTipo.put(Tipo.OJOS, new ArrayList<Complemento>());
		complementosPorTipo.put(Tipo.GAFAS, new ArrayList<Complemento>());
		complementosPorTipo.put(Tipo.BIGOTE, new ArrayList<Complemento>());
		complementosPorTipo.put(Tipo.BOCA, new ArrayList<Complemento>());
		complementosPorTipo.put(Tipo.MASCARA, new ArrayList<Complemento>());
		complementosPorTipo.put(Tipo.CAMISA, new ArrayList<Complemento>());
		complementosPorTipo.put(Tipo.PANTALON, new ArrayList<Complemento>());
		complementosPorTipo.put(Tipo.DISFRAZ, new ArrayList<Complemento>());
		
		for(Complemento c : fibooGame.getComplementos()) {
			botones.add(new BotonComplemento(c));
			
			complementosPorTipo.get(c.getTipo()).add(c);
		}
		botones.get(0).setStage(stage);
	}
	
	@Override
	public void render(float delta) {		
		
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Acciones de los actores
		stage.act(delta);

		//Pintar avatar
		batch.begin();
			fibooGame.getPersonaje().drawAvatar(batch);
		batch.end();

		//Pintar el resto de actores
		stage.draw();
		Table.drawDebug(stage);
	}

	public Skin getSkin() {
		return skin;
	}

	public Stage getStage() {
		return stage;
	}

}
