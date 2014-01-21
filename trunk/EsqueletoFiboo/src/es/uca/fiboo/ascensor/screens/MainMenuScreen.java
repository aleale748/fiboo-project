package es.uca.fiboo.ascensor.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenuScreen implements AbstractLoadingScreen{
	final FibooGame game;
	 OrthographicCamera camera;
	 
	 public MainMenuScreen(final FibooGame game) {
         super(game);
         this.game = game;
         camera = new OrthographicCamera();
         camera.setToOrtho(false, 800, 480);

 }

	@Override
	public void render(float delta) {

            Gdx.gl.glClearColor(0, 0, 0.2f, 1);
            Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

            camera.update();
            game.batch.setProjectionMatrix(camera.combined);

            game.batch.begin();
            game.font.draw(game.batch, "Welcome to Drop!!! ", 300, 250);
            game.font.draw(game.batch, "Tap anywhere to begin!", 300, 200);
           
            game.batch.end();

            if (Gdx.input.isTouched()) {
                    game.setScreen(new AScreen1(game));
                    dispose();
            }
    }
	

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
