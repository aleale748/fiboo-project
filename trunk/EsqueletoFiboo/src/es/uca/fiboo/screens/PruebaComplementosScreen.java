package es.uca.fiboo.screens;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.actores.BotonCategoria;
import es.uca.fiboo.actores.BotonComplemento;
import es.uca.fiboo.actores.Complemento;
import es.uca.fiboo.actores.Complemento.Tipo;

public class PruebaComplementosScreen extends AbstractScreen {

	private ArrayList<BotonComplemento> botones;
	private ArrayList<BotonCategoria> botonesCat;
	private Skin skin;
	private ImageButton atrasBoton;
	
	public PruebaComplementosScreen(final fibooGame game) {
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
        
        // Ventana con los botones de categorías
       // Window win = new Window("Personaliza", skin);
        
        int newRow = 0;
        for(BotonCategoria b : botonesCat) {
			table.add(b.getIcono()).width(128).height(128);
			newRow++;
			// 3 complementos por cada fila
			if(newRow > 2) {
				newRow = 0;
				table.row();
			}
		}
		
        /*
		for(BotonCategoria b : botonesCat) {
			win.add(b.getIcono()).width(128).height(128);
			newRow++;
			// 3 complementos por cada fila
			if(newRow > 2) {
				newRow = 0;
				win.row();
			}
		}
		win.setPosition(Gdx.graphics.getWidth() - win.getWidth() - 100f, (Gdx.graphics.getHeight() - 512f) / 2f);
        stage.addActor(win);
        win.debug();
        */
        TextureRegion atrasBotonRegion = new TextureRegion(new Texture(Gdx.files.internal("portada/atrasboton.png")));
		Drawable atrasBotonDrawable = new TextureRegionDrawable(atrasBotonRegion);
        atrasBoton = new ImageButton(atrasBotonDrawable);
		atrasBoton.setPosition(Gdx.graphics.getWidth()/(4f/0.3f) - atrasBoton.getWidth()/2, 
				Gdx.graphics.getHeight()/(4f/0.5f) - atrasBoton.getHeight()/2);
		atrasBoton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on " + atrasBoton.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + atrasBoton.getClass().getSimpleName());
						game.setScreen(new MenuScreen(game));
				}
		});
		stage.addActor(atrasBoton);
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
			//botones.add(new BotonComplemento(c));
			
			if(c.isDisponible()) {
				complementosPorTipo.get(c.getTipo()).add(new BotonComplemento(c));
			}
		}
		
		for(Entry<Tipo, ArrayList<BotonComplemento>> c : complementosPorTipo.entrySet()) {
			botonesCat.add(new BotonCategoria(this, c.getValue(), c.getKey()));
		}
		
		//botones.get(0).setStage(stage);
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
		//Table.drawDebug(stage);
		//Window.drawDebug(stage);
	}

	public Skin getSkin() {
		return skin;
	}

	public Stage getStage() {
		return stage;
	}

}
