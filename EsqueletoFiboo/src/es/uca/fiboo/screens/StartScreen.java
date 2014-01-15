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

import es.uca.fiboo.fibooGame;

public class StartScreen extends AbstractScreen {

    private Image playBoton;
    private Image imgFondo;
    
    public StartScreen(fibooGame game) {
        super(game);
        Gdx.input.setInputProcessor(stage);
        
        imgFondo = new Image(fibooGame.MANAGER.get("portada/portadafiboo.png", Texture.class));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);
    }

    @Override
    public void show() {
        super.show();
        
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        
        Texture boton = fibooGame.MANAGER.get("portada/playportada.png", Texture.class); 
        boton.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        
        playBoton = new Image(boton);
        float botonWidth = w * 0.3f;
		float botonHeight = botonWidth;
		
		playBoton.setSize(botonWidth, botonHeight);
        playBoton.setX(w/2 - playBoton.getWidth()/2);
        playBoton.setY(h/4 - playBoton.getHeight()/2);
        
        playBoton.addListener(new ClickListener() {
            @Override
			public void clicked(InputEvent event, float x, float y) {
				stage.addAction(sequence(delay(0.5f), fadeOut(0.75f),
                    new Action() {
                        @Override
                        public boolean act(float delta) {
                            game.setScreen(new ChooseScreen(game));
                            return true;
                        }
                    }));
			}
        });
        
        stage.addActor(playBoton);
    }
    
    @Override
    public void hide() {
    	super.hide();
    	fibooGame.MANAGER.unload("portada/playportada.png");
    	fibooGame.MANAGER.unload("portada/portadafiboo.png");
    	dispose();
    }

}