package es.uca.fiboo.cameraminigame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.FibooGame;
import es.uca.fiboo.cameraminigame.screens.CameraScreen;

public class ObjetoActor extends Actor {
	
	private Texture texture;

	//private Rectangle rectangle;
	
	private int tipoInt;
	
	public ObjetoActor(int tipoInt) {
		String nombreFichero = Integer.toString(tipoInt) + ".png";
		texture = FibooGame.MANAGER.get("cameraminigame/" + nombreFichero, Texture.class);
		//rectangle = new Rectangle(x, y, width, height)
		this.tipoInt = tipoInt;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY(), CameraScreen.tamanoMundo.x*0.15f, CameraScreen.tamanoMundo.x*0.15f);
	}
	
	public float getAncho() {
		return CameraScreen.tamanoMundo.x*0.15f;
	}
	
	public float getAlto() {
		return CameraScreen.tamanoMundo.y*0.15f;
	}
	
	public int getTipoInt() {
		return tipoInt;
	}
}
