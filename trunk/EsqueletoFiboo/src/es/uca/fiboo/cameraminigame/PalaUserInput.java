package es.uca.fiboo.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class PalaUserInput extends InputListener {

	private Objeto objeto;
	private ObjetosMenu Menu;
	private OrthographicCamera camera;
	
	private float delta;
	
	public PalaUserInput(Objeto objeto, ObjetosMenu Menu) {
		this.objeto = objeto;
		this.Menu = Menu;
	}
	
	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		//delta = event.getStageY();
		//if (objeto.getTipoInt() == Menu.getTipoInt())
		//{
		Gdx.app.log(Makipong.LOG, "Screen: x=" + x + "; y=" + y);
		objeto.remove();
			//TODO: Mostrar pantalla de Bien Hecho!
		//}
		return true;
	}
		
	/*@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		objeto.translate(0, event.getStageY() - delta);
		delta = event.getStageY();
	}*/
	
}
