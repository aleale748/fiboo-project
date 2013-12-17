package es.uca.fiboo.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.actores.BotonComplemento;
import es.uca.fiboo.actores.Complemento;

public class PruebaComplementosScreen extends AbstractScreen {

	private ArrayList<BotonComplemento> botones;
	private Skin skin;
	
	public PruebaComplementosScreen(fibooGame game) {
		super(game);
		Gdx.input.setInputProcessor(stage);
		
		// Para probar que se carga bien el skin con todos los ficheros
		FileHandle skinFile = Gdx.files.internal( "skin/uiskin.json" );
        skin = new Skin( skinFile );
		
		botones = new ArrayList<BotonComplemento>();
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
		for(Complemento c : fibooGame.getComplementos()) {
			botones.add(new BotonComplemento(c));
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

}
