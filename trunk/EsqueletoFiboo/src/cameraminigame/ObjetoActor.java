package cameraminigame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.uca.fiboo.fibooGame;

public class ObjetoActor extends Actor {
	
	private Texture texture;

	//private Rectangle rectangle;
	
	private int tipoInt;
	
	public ObjetoActor(int tipoInt) {
		String nombreFichero = Integer.toString(tipoInt) + ".png";
		texture = fibooGame.MANAGER.get("cameraminigame/" + nombreFichero, Texture.class);
		setSize(texture.getWidth(), texture.getHeight());
		//rectangle = new Rectangle(x, y, width, height)
		this.tipoInt = tipoInt;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY());
	}
	
	public int getTipoInt() {
		return tipoInt;
	}
}
