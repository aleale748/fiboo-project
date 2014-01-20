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

import es.uca.fiboo.FibooGame;

public class CreditosScreen extends AbstractScreen {

	public CreditosScreen(final FibooGame game) {
		super(game);

		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void show() {
		super.show();
		
		final InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(final int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
        			FibooGame.MANAGER.get("sonidos/creditos.ogg", Music.class).stop();
        			FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).setLooping(true);
        			FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).play();
					game.setScreen(new MenuScreen(game));
				}
				return false;
			}
		}, stage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		FibooGame.MANAGER.get("sonidos/creditos.ogg", Music.class).setLooping(true);
		FibooGame.MANAGER.get("sonidos/creditos.ogg", Music.class).play();
		
		Texture fondo = FibooGame.MANAGER.get("portada/pantallamenuentrenamiento.png", Texture.class);
		Texture ayuda = FibooGame.MANAGER.get("portada/creditos.png", Texture.class);
		fondo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		ayuda.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		Image imgFondo = new Image(fondo);
		imgFondo.setFillParent(true);
		
		Image pantallaAyuda = new Image(ayuda);
		pantallaAyuda.setSize(Gdx.graphics.getWidth()*0.85f, Gdx.graphics.getWidth()*0.85f);
		pantallaAyuda.setX(Gdx.graphics.getWidth()/2 - pantallaAyuda.getWidth()/2); 
        pantallaAyuda.setY(Gdx.graphics.getHeight()/2 - pantallaAyuda.getHeight()/2);
		
        stage.addActor(imgFondo);
		stage.addActor(pantallaAyuda);
		   
        pantallaAyuda.addListener(new ClickListener() {
            @Override
			public void clicked(final InputEvent event, final float x, final float y) {
				stage.addAction(sequence(delay(0.5f), fadeOut(0.75f),
                    new Action() {
                        @Override
                        public boolean act(final float delta) {
                			FibooGame.MANAGER.get("sonidos/creditos.ogg", Music.class).stop();
                			FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).setLooping(true);
                			FibooGame.MANAGER.get("sonidos/fondo.ogg", Music.class).play();
                            game.setScreen(new MenuScreen(game));
                            return true;
                        }
                    }));
			}
        });  
	}

}