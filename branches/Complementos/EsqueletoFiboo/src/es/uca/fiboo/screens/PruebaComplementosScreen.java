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

		FileHandle skinFile = Gdx.files.internal("skin/uiskin.json");
		skin = new Skin(skinFile);

		botones = new ArrayList<BotonComplemento>();
		cargaComplementos();

		Table table = new Table(skin);
		table.setPosition(600f, 420f);
		stage.addActor(table);
		table.debug();

		int newRow = 0;

		for (BotonComplemento b : botones) {
			table.add(b).width(128).height(128);
			newRow++;

			if (newRow > 1) {
				newRow = 0;
				table.row();
			}
			// stage.addActor(c);
		}
	}

	private void cargaComplementos() {
		BotonComplemento c;
		c = new BotonComplemento("data/complementos/gafas1.png", new Complemento(
				"data/complementos/gafas1.png", Complemento.Tipo.GAFAS));
		botones.add(c);

		c = new BotonComplemento("data/complementos/gafas2.png", new Complemento(
				"data/complementos/gafas2.png", Complemento.Tipo.GAFAS));
		botones.add(c);

		c = new BotonComplemento("data/complementos/disfraz1Mini.png", new Complemento(
				"data/complementos/disfraz1.png", Complemento.Tipo.DISFRAZ));
		botones.add(c);

		c = new BotonComplemento("data/complementos/disfraz2Mini.png", new Complemento(
				"data/complementos/disfraz2.png", Complemento.Tipo.DISFRAZ));
		botones.add(c);

		c = new BotonComplemento("data/complementos/pelonina1Mini.png", new Complemento(
				"data/complementos/pelonina1.png", Complemento.Tipo.PELO));
		botones.add(c);

		c = new BotonComplemento("data/complementos/pelonina2Mini.png", new Complemento(
				"data/complementos/pelonina2.png", Complemento.Tipo.PELO));
		botones.add(c);

		c = new BotonComplemento("data/complementos/pelonino1Mini.png", new Complemento(
				"data/complementos/pelonino1.png", Complemento.Tipo.PELO));
		botones.add(c);

		c = new BotonComplemento("data/complementos/pelonino2Mini.png", new Complemento(
				"data/complementos/pelonino2.png", Complemento.Tipo.PELO));
		botones.add(c);

		c = new BotonComplemento("data/complementos/ojos1.png", new Complemento(
				"data/complementos/ojos1.png", Complemento.Tipo.OJOS));
		botones.add(c);

		c = new BotonComplemento("data/complementos/ojos2.png", new Complemento(
				"data/complementos/ojos2.png", Complemento.Tipo.OJOS));
		botones.add(c);

		c = new BotonComplemento("data/complementos/ojos3.png", new Complemento(
				"data/complementos/ojos3.png", Complemento.Tipo.OJOS));
		botones.add(c);
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Pintar avatar
		batch.begin();
			game.personaje.drawAvatar(batch);
		batch.end();

		stage.act(delta);
		stage.draw();
		Table.drawDebug(stage);
	}

}
