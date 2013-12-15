package com.me.mygdxgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.me.mygdxgame.MyGdxGame;

public class PruebaDragScreen extends AbstractScreen {

	private Image imagen;
	
	public PruebaDragScreen(MyGdxGame game) {
		super(game);
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void show() {
		super.show();

		imagen = new Image(new Texture("data/PersoBoton.png"));
		Image imagen2 = new Image(new Texture("data/PersoBoton.png"));
		
		imagen.setPosition(225f, 225f);
		final Rectangle rImagen = new Rectangle(imagen.getX(), imagen.getY(), imagen.getWidth(), imagen.getHeight());
		
		imagen2.setPosition(50f, 50f);
		final Rectangle toHere = new Rectangle(imagen2.getX(), imagen2.getY(), imagen2.getWidth(), imagen2.getHeight() );

		
		imagen.addListener(new DragListener() {
			 public void touchDragged(InputEvent event, float x, float y, int pointer) {
				 //Gdx.app.log(MyGdxGame.LOG, "Dragging to x:" + x + " y:" + y);
				 float dx = x - imagen.getWidth()*0.5f;
				 float dy = y - imagen.getHeight()*0.5f;
				 imagen.setPosition(imagen.getX() + dx, imagen.getY() + dy);
				 rImagen.setPosition(imagen.getX(), imagen.getY());
			 }
			 
			 public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				 if(rImagen.overlaps(toHere)) {
					 Gdx.app.log(MyGdxGame.LOG, "intersecting...");
					 imagen.addAction(Actions.moveTo(toHere.x, toHere.y, 1f));
				 }
				 else {
					 Gdx.app.log(MyGdxGame.LOG, "didn't intersect");
					 super.touchUp(event, x, y, pointer, button);
				 }
			 }

		});
		
		stage.addActor(imagen);
		stage.addActor(imagen2);
	}

}
