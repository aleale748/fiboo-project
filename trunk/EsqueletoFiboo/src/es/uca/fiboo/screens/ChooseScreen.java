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

	private Image ninoBoton;
	private Image ninaBoton;
	private Image imgFondo;
	private float w,h;
	
	public ChooseScreen(FibooGame game) {
		super(game);
		Gdx.input.setInputProcessor(stage);
		
		imgFondo = new Image(FibooGame.MANAGER.get("portada/pantallamenuentrenamiento.png", Texture.class));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
	}

	@Override
	public void show() {
		super.show();
		
		Texture nino = FibooGame.MANAGER.get("portada/polaroidnino2.png", Texture.class);
		Texture nina = FibooGame.MANAGER.get("portada/polaroidnina2.png", Texture.class);
		nino.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		nina.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		ninoBoton = new Image(nino);
		ninaBoton = new Image(nina);
		
		float imgWidth = w * 0.3f;
		float imgHeight = imgWidth;
		ninoBoton.setSize(imgWidth, imgHeight);
		ninaBoton.setSize(imgWidth, imgHeight);		
		
		ninoBoton.setX(w / 4f - ninoBoton.getWidth() / 2f);
		ninoBoton.setY(h / 2f - ninoBoton.getHeight() / 2f);		
		ninoBoton.addListener(new MyClickListener("pelonino3"));
		
		ninaBoton.setX(w / (4f/3f) - ninaBoton.getWidth() / 2f);
		ninaBoton.setY(h / 2 - ninaBoton.getHeight() / 2f);
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
		
		private String base;

		public MyClickListener(String base) {
			this.base = base;
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y) {
			Gdx.app.log("ChooseScreen", "Clickeando en " + base);
			FibooGame.getPersonaje().getAvatar().setBase(base);
			stage.addAction(sequence(delay(1.0f), fadeOut(0.75f),
			new Action() {
				@Override
				public boolean act(float delta) {
					game.setScreen(new MenuScreen(game));
					return true;
				}
			}));
		}
	}

}
