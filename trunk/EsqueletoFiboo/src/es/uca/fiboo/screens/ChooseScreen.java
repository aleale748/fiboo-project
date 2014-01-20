package es.uca.fiboo.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import es.uca.fiboo.FibooGame;

public class ChooseScreen extends AbstractScreen {

	private transient final float width, height;
	
	public ChooseScreen(final FibooGame game) {
		super(game);
		Gdx.input.setInputProcessor(stage);
		
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
	}

	@Override
	public void show() {
		super.show();
		
		Texture nino = FibooGame.MANAGER.get("portada/polaroidnino2.png", Texture.class);
		Texture nina = FibooGame.MANAGER.get("portada/polaroidnina2.png", Texture.class);
		nino.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		nina.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image ninoBoton = new Image(nino);
		Image ninaBoton = new Image(nina);
		
		Image imgFondo = new Image(FibooGame.MANAGER.get("portada/pantallamenuentrenamiento.png", Texture.class));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);
		
		float imgWidth = width * 0.3f;
		float imgHeight = imgWidth;
		ninoBoton.setSize(imgWidth, imgHeight);
		ninaBoton.setSize(imgWidth, imgHeight);		
		
		ninoBoton.setX(width / 4f - ninoBoton.getWidth() / 2f);
		ninoBoton.setY(height / 2f - ninoBoton.getHeight() / 2f);		
		ninoBoton.addListener(new MyClickListener("pelonino3"));
		
		ninaBoton.setX(width / (4f/3f) - ninaBoton.getWidth() / 2f);
		ninaBoton.setY(height / 2 - ninaBoton.getHeight() / 2f);
		ninaBoton.addListener(new MyClickListener("pelonina3"));
		
		stage.addActor(ninoBoton);
		stage.addActor(ninaBoton);
	}
	
	@Override
	public void hide() {
		super.hide();
		FibooGame.MANAGER.unload("portada/polaroidnino2.png");
		FibooGame.MANAGER.unload("portada/polaroidnina2.png");
		super.dispose();
	}

	private class MyClickListener extends ClickListener {
		
		private transient final String base;

		public MyClickListener(final String base) {
			super();
			this.base = base;
		}
		
		@Override
		public void clicked(final InputEvent event, final float x, final float y) {
			//Gdx.app.log("ChooseScreen", "Clickeando en " + base);
			FibooGame.getPersonaje().getAvatar().setBase(base);
			stage.addAction(sequence(delay(1.0f), fadeOut(0.75f),
			new Action() {
				@Override
				public boolean act(final float delta) {
					game.setScreen(new MenuScreen(game));
					return true;
				}
			}));
		}
	}

}
