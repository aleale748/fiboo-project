package es.uca.fiboo.personalizar.screens;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.personalizar.actores.BotonCategoria;
import es.uca.fiboo.personalizar.actores.BotonComplemento;
import es.uca.fiboo.personalizar.actores.Complemento;
import es.uca.fiboo.personalizar.actores.Complemento.Tipo;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.MenuScreen;

public class PersonalizacionScreen extends AbstractScreen {

	private ArrayList<BotonCategoria> botonesCat;
	private Skin skin;
	private Texture fondo;
	private float escalaAvatar;
	
	public PersonalizacionScreen(final fibooGame game) {
		super(game);
		
		InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
					dispose();
					game.setScreen(new MenuScreen(game));
				}
				return false;
			}
		}, stage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
		// Para probar que se carga bien el skin con todos los ficheros
		FileHandle skinFile = Gdx.files.internal( "skin/uiskin.json" );
        skin = new Skin( skinFile );
		
        botonesCat = new ArrayList<BotonCategoria>();
		cargaComplementos();
	}

	//Carga todos los complementos habidos y por haber
	private void cargaComplementos() {
		
		TreeMap<Tipo, ArrayList<BotonComplemento>> complementosPorTipo;
		complementosPorTipo = new TreeMap<Tipo, ArrayList<BotonComplemento>>();
		
		complementosPorTipo.put(Tipo.PELO, new ArrayList<BotonComplemento>());
		complementosPorTipo.put(Tipo.ACCPELO, new ArrayList<BotonComplemento>());
		complementosPorTipo.put(Tipo.OJOS, new ArrayList<BotonComplemento>());
		complementosPorTipo.put(Tipo.GAFAS, new ArrayList<BotonComplemento>());
		complementosPorTipo.put(Tipo.BOCA, new ArrayList<BotonComplemento>());
		complementosPorTipo.put(Tipo.BIGOTE, new ArrayList<BotonComplemento>());
		//complementosPorTipo.put(Tipo.MASCARA, new ArrayList<BotonComplemento>());
		complementosPorTipo.put(Tipo.CAMISA, new ArrayList<BotonComplemento>());
		complementosPorTipo.put(Tipo.PANTALON, new ArrayList<BotonComplemento>());
		complementosPorTipo.put(Tipo.DISFRAZ, new ArrayList<BotonComplemento>());
		
		for(Complemento c : fibooGame.getComplementos()) {
			if(c.isDisponible()) {
				complementosPorTipo.get(c.getTipo()).add(new BotonComplemento(c));
			}
		}
		
		for(Entry<Tipo, ArrayList<BotonComplemento>> c : complementosPorTipo.entrySet()) {
			botonesCat.add(new BotonCategoria(this, c.getValue(), c.getKey()));
		}
	}
	
	@Override
	public void show() {
		super.show();
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		fondo = fibooGame.MANAGER.get("data/fondopersonalizar.png", Texture.class);
		fondo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		escalaAvatar = h * 0.7f;
        
        // Tabla para colocar los iconos
 		Table table = new Table(skin);
 		
 		float cellHeight = h * 0.2f;
 		float cellWidth = cellHeight;
 		float posTableX = w / 2f + cellWidth * 1.5f;
 		float posTableY = h / 2f;
 		
 		table.setPosition(posTableX, posTableY);
         stage.addActor(table);
         
         int newRow = 0;
         for(BotonCategoria b : botonesCat) {
 			table.add(b.getIcono()).width(cellWidth).height(cellHeight);
 			newRow++;
 			// 3 complementos por cada fila
 			if(newRow > 2) {
 				newRow = 0;
 				table.row();
 			}
 		}
	}
	
	@Override
	public void render(float delta) {		
		
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Acciones de los actores
		stage.act(delta);

		//Pintar avatar y fondo
		batch.begin();
			batch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			fibooGame.getPersonaje().drawAvatar(batch, escalaAvatar);
		batch.end();

		//Pintar el resto de actores
		stage.draw();
	}

	
	@Override
	public void dispose() {
		fondo.dispose();
		skin.dispose();
		for(Complemento c : fibooGame.getComplementos()) c.dispose();
		fibooGame.getPersonaje().getAvatar().dispose();
		fibooGame.MANAGER.unload("data/fondopersonalizar.png");
		fibooGame.MANAGER.unload("complementos/complementos.atlas");
		Gdx.app.log(fibooGame.LOG, "'Disposed' Fondo, skin, fondo y Atlas de Complementos");
		super.dispose();
	}
	
	public Skin getSkin() {
		return skin;
	}

	public Stage getStage() {
		return stage;
	}

}
