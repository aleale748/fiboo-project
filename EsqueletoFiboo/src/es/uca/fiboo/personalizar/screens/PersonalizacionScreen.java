package es.uca.fiboo.personalizar.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import es.uca.fiboo.FibooGame;
import es.uca.fiboo.personalizar.actores.BotonCategoria;
import es.uca.fiboo.personalizar.actores.BotonComplemento;
import es.uca.fiboo.personalizar.actores.Complemento;
import es.uca.fiboo.personalizar.actores.Complemento.Tipo;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.MenuScreen;

public class PersonalizacionScreen extends AbstractScreen {

	private transient List<BotonCategoria> botonesCat;
	private Skin skin;
	private transient Texture fondo;
	private transient float escalaAvatar;
	
	public PersonalizacionScreen(final FibooGame game) {
		super(game);
		FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).stop();
		
		final InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(final int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
					FibooGame.MANAGER.get("sonidos/personalizacion.ogg", Music.class).stop();
					FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).setLooping(true);
					FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).play();
					dispose();
					game.setScreen(new MenuScreen(game));
				}
				return false;
			}
		}, stage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		FibooGame.MANAGER.get("sonidos/personalizacion.ogg", Music.class).setLooping(true);
		FibooGame.MANAGER.get("sonidos/personalizacion.ogg", Music.class).play();
		BotonComplemento.setBotonComplementoStage(stage);
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
		complementosPorTipo.put(Tipo.CAMISETA, new ArrayList<BotonComplemento>());
		complementosPorTipo.put(Tipo.PANTALON, new ArrayList<BotonComplemento>());
		complementosPorTipo.put(Tipo.DISFRAZ, new ArrayList<BotonComplemento>());
		
		for(final Complemento c : FibooGame.getComplementos()) {
			if(c.isDisponible()) {
				complementosPorTipo.get(c.getTipo()).add(new BotonComplemento(c));
			}
		}
		
		for(final Entry<Tipo, ArrayList<BotonComplemento>> tb : complementosPorTipo.entrySet()) {
			botonesCat.add(new BotonCategoria(this, tb.getValue(), tb.getKey()));
		}
	}
	
	@Override
	public void show() {

		// Para probar que se carga bien el skin con todos los ficheros
		final FileHandle skinFile = Gdx.files.internal( "skin/uiskin.json" );
        skin = new Skin( skinFile );
		
        botonesCat = new ArrayList<BotonCategoria>();
		cargaComplementos();
		
		final float width = Gdx.graphics.getWidth();
		final float height = Gdx.graphics.getHeight();
		
		setFondoAleatorio();

		escalaAvatar = height * 0.7f;
        
        // Tabla para colocar los iconos
 		final Table table = new Table(skin);
 		
 		final float cellHeight = height * 0.2f;
 		final float cellWidth = cellHeight;
 		final float posTableX = width / 2f + cellWidth * 1.5f;
 		final float posTableY = height / 2f;
 		
 		table.setPosition(posTableX, posTableY);
        stage.addActor(table);
         
        int newRow = 0;
        final int maxRows = 2; // 3 complementos por cada fila
        for(final BotonCategoria b : botonesCat) {
 			table.add(b.getIcono()).width(cellWidth).height(cellHeight);
 			newRow++;
 			if(newRow > maxRows) {
 				newRow = 0;
 				table.row();
 			}
 		}
	}
	
	private void setFondoAleatorio() {
		final int fondoRand = (int)(Math.random()*10) % 5;
		switch(fondoRand) {
			case 0: fondo = FibooGame.MANAGER.get("complementos/habitacionverde.png", Texture.class); break;
			case 1: fondo = FibooGame.MANAGER.get("complementos/habitacionrosa.png", Texture.class); break;
			case 2: fondo = FibooGame.MANAGER.get("complementos/habitacionceleste.png", Texture.class); break;
			case 3: fondo = FibooGame.MANAGER.get("complementos/habitacionmorada.png", Texture.class); break;
			case 4: fondo = FibooGame.MANAGER.get("complementos/habitacionnaranja.png", Texture.class); break;
			default: break;
		}
		fondo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}

	@Override
	public void render(final float delta) {		
		
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);

		stage.act(delta);

		//Pintar avatar y fondo
		batch.begin();
			batch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			FibooGame.getPersonaje().drawAvatar(batch, escalaAvatar);
		batch.end();

		stage.draw();
	}
	
	public Skin getSkin() {
		return skin;
	}

	public Stage getStage() {
		return stage;
	}

}
