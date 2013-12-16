package es.uca.fiboo.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.actores.BotonComplemento;
import es.uca.fiboo.actores.Complemento;

public class PruebaComplementosScreen extends AbstractScreen {

	private Image nino;
	private Rectangle cabeza, cuerpo;
	private ArrayList<BotonComplemento> botones;
	private ShapeRenderer shapeRenderer;
	private Skin skin;
	
	public PruebaComplementosScreen(fibooGame game) {
		super(game);
		Gdx.input.setInputProcessor(stage);
		
		// Para probar que se carga bien el skin con todos los ficheros
		FileHandle skinFile = Gdx.files.internal( "skin/uiskin.json" );
        skin = new Skin( skinFile );
        
        // Para pintar los rectangulos a modo de debug
		shapeRenderer = new ShapeRenderer();
		
		// Cuerpo base (no tengo el calvo sin ropa) y rect�ngulos para colisiones
		nino = new Image(new Texture(fibooGame.personaje.getCompl(Complemento.Tipo.BASE)));
		nino.setPosition(100f, 100f);
		stage.addActor(nino);
		cabeza = new Rectangle(102f, 318f, 256f, 256f);
		cuerpo = new Rectangle(102f, 97f, nino.getWidth(), nino.getHeight());
		
		botones = new ArrayList<BotonComplemento>();
		cargaComplementos();
		
		// Tabla para colocar los iconos
		Table table = new Table(skin);
		table.setPosition(600f, 390f);
        stage.addActor(table);
        table.debug();
        
        int newRow = 0;
		for(BotonComplemento b : botones) {
			table.add(b).width(128).height(128);
			newRow++;
			// 2 complementos por cada fila
			if(newRow > 1) {
				newRow = 0;
				table.row();
			}
		}
	}
	
	//Forma un poco bestia de cargar todos los complementos, pero no se me ocurr�a otra :D
	private void cargaComplementos() {
		BotonComplemento c;
		c = new BotonComplemento(new Texture("data/complementos/gafas1.png"),
								 new Complemento(new Texture("data/complementos/gafas1.png"), Complemento.Tipo.GAFAS));
		c.setOverlap(cabeza);
		c.setStage(stage);
		botones.add(c);
		
		c = new BotonComplemento(new Texture("data/complementos/gafas2.png"),
								 new Complemento(new Texture("data/complementos/gafas2.png"), Complemento.Tipo.GAFAS));
		c.setOverlap(cabeza);
		c.setStage(stage);
		botones.add(c);
		
		c = new BotonComplemento(new Texture("data/complementos/disfraz1Mini.png"),
								 new Complemento(new Texture("data/complementos/disfraz1.png"), Complemento.Tipo.DISFRAZ));
		c.setOverlap(cuerpo);
		c.setStage(stage);
		botones.add(c);
		
		c = new BotonComplemento(new Texture("data/complementos/disfraz2Mini.png"),
								 new Complemento(new Texture("data/complementos/disfraz2.png"), Complemento.Tipo.DISFRAZ));
		c.setOverlap(cuerpo);
		c.setStage(stage);
		botones.add(c);
		
		c = new BotonComplemento(new Texture("data/complementos/pelonina1Mini.png"),
								 new Complemento(new Texture("data/complementos/pelonina1.png"), Complemento.Tipo.PELO));
		c.setOverlap(cabeza);
		c.setStage(stage);
		botones.add(c);
		
		c = new BotonComplemento(new Texture("data/complementos/pelonina2Mini.png"),
								 new Complemento(new Texture("data/complementos/pelonina2.png"), Complemento.Tipo.PELO));
		c.setOverlap(cabeza);
		c.setStage(stage);
		botones.add(c);
		
		c = new BotonComplemento(new Texture("data/complementos/pelonino1Mini.png"),
								 new Complemento(new Texture("data/complementos/pelonino1.png"), Complemento.Tipo.PELO));
		c.setOverlap(cabeza);
		c.setStage(stage);
		botones.add(c);
		
		c = new BotonComplemento(new Texture("data/complementos/pelonino2Mini.png"),
								 new Complemento(new Texture("data/complementos/pelonino2.png"), Complemento.Tipo.PELO));
		c.setOverlap(cabeza);
		c.setStage(stage);
		botones.add(c);
		
		c = new BotonComplemento(new Texture("data/complementos/ojos1.png"),
								 new Complemento(new Texture("data/complementos/ojos1.png"), Complemento.Tipo.OJOS));
		c.setOverlap(cabeza);
		c.setStage(stage);
		botones.add(c);
		
		c = new BotonComplemento(new Texture("data/complementos/ojos2.png"),
								 new Complemento(new Texture("data/complementos/ojos2.png"), Complemento.Tipo.OJOS));
		c.setOverlap(cabeza);
		c.setStage(stage);
		botones.add(c);
		
		c = new BotonComplemento(new Texture("data/complementos/ojos3.png"),
								 new Complemento(new Texture("data/complementos/ojos3.png"), Complemento.Tipo.OJOS));
		c.setOverlap(cabeza);
		c.setStage(stage);
		botones.add(c);
	}
	
	@Override
	public void render(float delta) {		
		
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Pinta los rect�ngulos de cabeza y cuerpo del avatar base
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(0, 0, 0, 0);
		shapeRenderer.rect(cabeza.x, cabeza.y, cabeza.width, cabeza.height);
		shapeRenderer.rect(cuerpo.x, cuerpo.y, cuerpo.width, cuerpo.height);
		shapeRenderer.end();

		stage.act(delta);
		stage.draw();
		// Para pintar bordes de la tabla
		Table.drawDebug(stage);
	}

}
