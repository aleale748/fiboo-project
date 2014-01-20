package es.uca.fiboo.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import es.uca.fiboo.FibooGame;
import es.uca.fiboo.marcianosminigame.screens.InicioMarcianosGameScreen;
import es.uca.fiboo.naveminigame.screens.InicioNaveScreen;
import es.uca.fiboo.piano.screens.InicioPianoGameScreen;
import es.uca.fiboo.robotgame.screens.InicioRobotGameScreen;
import es.uca.fiboo.tallerminigame.screens.InicioTallerGameScreen;

public class MenuMiniJuegosScreen extends AbstractScreen {

	private transient final float width, height;
	
	public MenuMiniJuegosScreen(final FibooGame game) {
		super(game);
		Texture fondo = FibooGame.MANAGER.get("portada/pantallamenuentrenamiento.png", Texture.class);
		fondo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		Image imgFondo = new Image(fondo);
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
	}

	@Override
	public void show() {
		super.show();
		final InputMultiplexer inputMultiplexer = new InputMultiplexer(new InputAdapter() {
			@Override
			public boolean keyUp(final int keycode) {
				if (keycode == Keys.BACK || keycode == Keys.ESCAPE){
					game.setScreen(new MenuScreen(game));
				}
				return false;
			}
		}, stage);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
		float imgWidth = width * 0.3f;
		float imgHeight = imgWidth;
	

		// Creamos botones, los posicionamos y los añadimos al stage
		Texture nave = FibooGame.MANAGER.get("portada/naveboton.png", Texture.class);
		nave.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image naveBoton = new Image(nave);
		naveBoton.setSize(imgWidth, imgHeight);
		naveBoton.setX(width*0.2f - naveBoton.getWidth()/2); 
		naveBoton.setY(height*0.35f - naveBoton.getHeight()/2);
		naveBoton.addListener(new MenuClickListener(1));
		stage.addActor(naveBoton);
		
		Texture bolsa = FibooGame.MANAGER.get("portada/bolsaboton.png", Texture.class);
		bolsa.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image bolsaBoton = new Image(bolsa);
		bolsaBoton.setSize(imgWidth, imgHeight);
		bolsaBoton.setX(width*0.5f - bolsaBoton.getWidth()/2); 
		bolsaBoton.setY(height*0.35f - bolsaBoton.getHeight()/2);
		bolsaBoton.addListener(new MenuClickListener(2));
		stage.addActor(bolsaBoton);
		
		Texture robot = FibooGame.MANAGER.get("portada/robotboton.png", Texture.class);
		robot.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image robotBoton = new Image(robot);
		robotBoton.setSize(imgWidth, imgHeight);
		robotBoton.setX(width*0.8f - robotBoton.getWidth()/2); 
		robotBoton.setY(height*0.35f - robotBoton.getHeight()/2);
		robotBoton.addListener(new MenuClickListener(3));
		stage.addActor(robotBoton);
		
		Texture piano = FibooGame.MANAGER.get("portada/pianoboton.png", Texture.class);
		piano.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image pianoBoton = new Image(piano);
		pianoBoton.setSize(imgWidth, imgHeight);
		pianoBoton.setX(width*0.35f - pianoBoton.getWidth()/2); 
		pianoBoton.setY(height*0.7f - pianoBoton.getHeight()/2);
		pianoBoton.addListener(new MenuClickListener(4));
		stage.addActor(pianoBoton);
		
		Texture marciano = FibooGame.MANAGER.get("portada/marcianoboton.png", Texture.class);
		marciano.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image marcianoBoton = new Image(marciano);
		marcianoBoton.setSize(imgWidth, imgHeight);
		marcianoBoton.setX(width*0.65f - marcianoBoton.getWidth()/2); 
		marcianoBoton.setY(height*0.7f - marcianoBoton.getHeight()/2);
		marcianoBoton.addListener(new MenuClickListener(5));
		stage.addActor(marcianoBoton);
		
		Texture atras = FibooGame.MANAGER.get("portada/atrasboton.png", Texture.class);
		atras.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image atrasBoton = new Image(atras);
		atrasBoton.setSize(imgWidth/2, imgHeight/2);
		atrasBoton.setX(width/(4f/0.3f) - atrasBoton.getWidth()/2f); 
		atrasBoton.setY(height*0.1f - atrasBoton.getHeight()/2);
		atrasBoton.addListener(new MenuClickListener(6));
		stage.addActor(atrasBoton);
	}
	
	private class MenuClickListener extends ClickListener {

		private transient final int pantalla;

		public MenuClickListener(final int pantalla) {
			super();
			this.pantalla = pantalla;
		}
			
		@Override
		public void clicked(final InputEvent event, final float x, final float y) {
			stage.addAction(sequence(delay(0.5f), fadeOut(0.75f),
                new Action() {
                    @Override
                    public boolean act(final float delta) {
                    	setGame();
                        return true;
                    }
                }));
		}
		
		private void setGame() {
			switch(pantalla) {
			case 1: 
				game.setScreen(new InicioNaveScreen(game));
				break;
			case 2:
				game.setScreen(new InicioTallerGameScreen(game));
				break;
			case 3:
				game.setScreen(new InicioRobotGameScreen(game));
				break;
			case 4:
				game.setScreen(new InicioPianoGameScreen(game));
				break;
			case 5:
				game.setScreen(new InicioMarcianosGameScreen(game));
				break;
			case 6:
				game.setScreen(new MenuScreen(game));
				break;
			default:
				break;
			}
		}
	}

}
