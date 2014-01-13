package es.uca.fiboo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.robotgame.screens.RobotGameScreen;

public class InicioRobotGameScreen extends AbstractScreen {

	private Image pantallaAyuda;
	private Image imgFondo;
	public InicioRobotGameScreen(fibooGame game) {
		super(game);
		Gdx.input.setInputProcessor(stage);
		imgFondo = new Image(fibooGame.MANAGER.get("naveminigame/fondonave.png", Texture.class));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);
	}
	
	@Override
	public void show() {
		super.show();
		TextureRegion pantallaBotonRegion = new TextureRegion(fibooGame.MANAGER.get("robotgame/pantallainiciorobot.png", Texture.class));
		Drawable pantallaBotonDrawable = new TextureRegionDrawable(pantallaBotonRegion);
		
		// Creamos botones, los posicionamos y los a??adimos al stage
		pantallaAyuda = new Image(pantallaBotonDrawable);
		pantallaAyuda.setSize(Gdx.graphics.getWidth()*0.85f,Gdx.graphics.getWidth()*0.85f);
		pantallaAyuda.setPosition(Gdx.graphics.getWidth()/2 - pantallaAyuda.getWidth()/2, 
                Gdx.graphics.getHeight()/2 - pantallaAyuda.getHeight()/2);
		pantallaAyuda.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching down on " + pantallaAyuda.getClass().getSimpleName());
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(fibooGame.LOG, "Touching up on " + pantallaAyuda.getClass().getSimpleName());
					game.setScreen(new RobotGameScreen(game));
				}
		});
		stage.addActor(pantallaAyuda);
		//stage.act();
		stage.draw();
	}

}
