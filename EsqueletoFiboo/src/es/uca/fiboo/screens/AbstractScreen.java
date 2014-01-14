package es.uca.fiboo.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import es.uca.fiboo.fibooGame;

/*
 * Clase base para todas las Screens
 */
public abstract class AbstractScreen implements Screen {

	protected final Stage stage;
	protected final SpriteBatch batch;
	protected final fibooGame game;
	
	public AbstractScreen(fibooGame game) {
		this.game = game;
		this.batch = new SpriteBatch();
		this.stage = new Stage(0, 0, true);
	}
	
	protected String getName() {
		return getClass().getSimpleName();
	}
	
	@Override
	public void render(float delta) {
		// Limpia la pantalla rellenándola con el color RGB dado (blanco)
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Actualiza y dibuja los actores del stage
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log(fibooGame.LOG, "'Resizing' screen: " + getName() + " to: " + width + " x " + height);
		
		// Redimensiona el stage
		stage.setViewport(width, height, true);
	}

	@Override
	public void show() {
		Gdx.app.log(fibooGame.LOG, "'Showing' screen: " + getName());
	}

	@Override
	public void hide() {
		Gdx.app.log(fibooGame.LOG, "'Hidding' screen: " + getName());
	}

	@Override
	public void pause() {
		Gdx.app.log(fibooGame.LOG, "'Pausing' screen: " + getName());
	}

	@Override
	public void resume() {
		Gdx.app.log(fibooGame.LOG, "'Resuming' screen: " + getName());
	}

	@Override
	public void dispose() {
		Gdx.app.log(fibooGame.LOG, "'Disposing' screen: " + getName());

		// Liberando memoria
		stage.dispose();
		batch.dispose();
	}

}
