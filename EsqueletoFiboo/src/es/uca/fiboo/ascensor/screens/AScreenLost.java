package es.uca.fiboo.ascensor.screens;

import com.badlogic.drop.Herramienta.Tipo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.TimeUtils;

public class AScreenLost implements AbstractScreen{
	
private static final Tipo GO = null;

public final FibooGame game;
	
	Music dropSound;
	OrthographicCamera camera;
	 long endTime = TimeUtils.nanoTime();
	 Tipo go = GO;
	 Texture ascensor;
	 FiguraAscen marco;
	 	FiguraAscen mono;
		FiguraAscen leftDoor;
		FiguraAscen rightDoor;
		private static final Tipo DOOR = null;
		Tipo door = DOOR;
		long lastMoveTime = TimeUtils.nanoTime();
		long lastMoveTimeMono = TimeUtils.nanoTime();
		int c=0;
		int con;
		AScreen1 ascreen1;
	   float tmpDoor=0;
	   float tmpFin=0;
		float lastMoveTimeDoor = TimeUtils.nanoTime();
 		boolean sw3=true;
 	    int i3=0;
 		int posright = 800;
 		float tim = 1000; //Gdx.graphics.getRawDeltaTime();
 		boolean jump = true;
 		
	 public AScreenLost(final FibooGame game){
		 super(game);
		 this.game = game;
		 ascreen1 = new AScreen1(game);
         camera = new OrthographicCamera();
         camera.setToOrtho(false, 800, 480);
         // load the drop sound effect and the rain background "music"
         dropSound = Gdx.audio.newMusic(Gdx.files.internal("ascensor/sad.mp3"));
         marco =   new FiguraAscen("ascensor/ascensor.png", door, 1024, 512, 0, 0);
         ascensor = new Texture(Gdx.files.internal("ascensor/roomAcua.png"));  
         mono = new FiguraAscen("ascensor/monoStop.png", go, 128, 128, 800/2 - 64, 100);
         leftDoor = new FiguraAscen("ascensor/puertaIz.png", door, 512, 512, 0-350, 0);
         rightDoor = new FiguraAscen("ascensor/puertaD.png", door, 512, 512, 600, 0);
	 }
	 
	 
	 public void closeDoors(){
		 
	               if(leftDoor.rectangleTarea.x < 400-512){
                           leftDoor.rectangleTarea.x += i3;
                      }
 				
                     if(rightDoor.rectangleTarea.x > 400){
                          rightDoor.rectangleTarea.x -= i3;
                     }	
 			
                     //para espera
 		
                     i3+=1;
                   
	
                     if(rightDoor.rectangleTarea.x <= 401){
                    	 dropSound.play(); 
                     }


	 }
		 
	 
	 
	 
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
				Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		        // tell the camera to update its matrices.
		        camera.update();
		      
		        game.batch.setProjectionMatrix(camera.combined);
		       
		        game.batch.begin();
		        game.batch.draw(ascensor, 0, 0);
		        game.batch.draw(mono.textureTarea, mono.rectangleTarea.x, mono.rectangleTarea.y);
		        game.batch.draw(leftDoor.textureTarea, leftDoor.rectangleTarea.x, leftDoor.rectangleTarea.y);
		        game.batch.draw(rightDoor.textureTarea, rightDoor.rectangleTarea.x, rightDoor.rectangleTarea.y);
		        game.batch.draw(marco.textureTarea, marco.rectangleTarea.x, marco.rectangleTarea.y);
		        game.batch.end();
		        lastMoveTime = (long) (TimeUtils.nanoTime() + Gdx.graphics.getRawDeltaTime());
		      
		        tmpDoor += delta;
		        tmpFin += delta;
		        
		        //if(TimeUtils.nanoTime() > 999999 + lastMoveTime){
		        if(tmpDoor > 0.1){
		        	if(!jump){
		        		mono.rectangleTarea.y -=40;
		        		jump = true;
		        	}else{
		        		mono.rectangleTarea.y +=40;
		        		jump = false;
		        	}
		        	
		        	 closeDoors();
		        	 tmpDoor=0;
		        }
		        
		      if(tmpFin >= 7){
		        tmpFin=0;
		        		ascreen1.piso =  MathUtils.random(1, 5);
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
		dropSound.play();
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
		ascensor.dispose();
		dropSound.dispose();
		
	}

}
