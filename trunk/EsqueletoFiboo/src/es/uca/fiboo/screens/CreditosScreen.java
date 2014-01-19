package es.uca.fiboo.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.screens.MenuMiniJuegosScreen;

public class CreditosScreen extends AbstractScreen {

	private Image pantallaAyuda;
	private Image imgFondo;
	private Image playBoton;
	
	public CreditosScreen(fibooGame game) {
		super(game);
		//fibooGame.MANAGER.get("sonidos/fondo.mp3", Sound.class).stop();
		//fibooGame.MANAGER.get("sonidos/ayuda.mp3",Sound.class).loop();
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void show() {
		super.show();
		
		InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
						game.setScreen(new MenuMiniJuegosScreen(game));
				}
				return false;
			}
		}, stage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		Texture fondo = fibooGame.MANAGER.get("portada/pantallamenuentrenamiento.png", Texture.class);
		Texture ayuda = fibooGame.MANAGER.get("portada/creditos.png", Texture.class);
		fondo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		ayuda.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		imgFondo = new Image(fondo);
		imgFondo.setFillParent(true);
		
		pantallaAyuda = new Image(ayuda);
		pantallaAyuda.setSize(Gdx.graphics.getWidth()*0.85f, Gdx.graphics.getWidth()*0.85f);
		pantallaAyuda.setX(Gdx.graphics.getWidth()/2 - pantallaAyuda.getWidth()/2); 
        pantallaAyuda.setY(Gdx.graphics.getHeight()/2 - pantallaAyuda.getHeight()/2);
		
        stage.addActor(imgFondo);
		stage.addActor(pantallaAyuda);
		
        
        pantallaAyuda.addListener(new ClickListener() {
            @Override
			public void clicked(InputEvent event, float x, float y) {
				stage.addAction(sequence(delay(0.5f), fadeOut(0.75f),
                    new Action() {
                        @Override
                        public boolean act(float delta) {
                			fibooGame.MANAGER.get("sonidos/ayuda.ogg", Music.class).stop();
                            game.setScreen(new MenuScreen(game));
                            return true;
                        }
                    }));
			}
        });
        
	}

}