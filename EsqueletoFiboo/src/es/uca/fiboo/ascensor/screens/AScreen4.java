package es.uca.fiboo.ascensor.screens;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.TimeUtils;

import es.uca.fiboo.FibooGame;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.ascensor.screens.Herramienta.Tipo;

public class AScreen4 extends AbstractScreen{
	
	public final FibooGame game;
	private static final Tipo MINUS = null;
	private static final Tipo GO = null;
	private static final Tipo PLUS = null;
	private static final Tipo DOOR = null;
	private static final Tipo NUM = null;
	private static final Tipo SMASH = null;
	private static final Tipo HAMMER = null;
	private static final Tipo BROOM = null;
	private static final Tipo BELL = null;

	Sound dropSound;
	//Sound bellSound;
	Sound elevatorMove, elevatorStop;
	Music fondoWow;
	
	OrthographicCamera camera;

	 Texture ascensor;
	 FiguraAscen marco;
	 FiguraAscen leftDoor;
	 FiguraAscen rightDoor;
	 //FiguraAscen numeross;
	 FiguraAscen marker;
	 FiguraAscen hammer;
	 FiguraAscen paint;
	// FiguraAscen temp;
	 FiguraAscen bell;
	 FiguraAscen bellL;
	 FiguraAscen bellR;
	 FiguraAscen count;
	// FiguraAscen black;
	 FiguraAscen pink;
	 
	 ArrayList<FiguraAscen> alienParasites;
	 ArrayList<FiguraAscen> contador;
	 ArrayList<FiguraAscen> contadorPlus;
	
	 int i3=0;
	 int imgTam = 64;
	 int c=0;
	 int con=0;
	 Random random; 
     int espacio = 3;
     boolean no=true;
     boolean st = true;
     boolean sw5=false;
     
     Tipo minus = MINUS;
     Tipo go = GO;
     Tipo plus = PLUS;
     Tipo door = DOOR;
     Tipo num = NUM;
     Tipo cleaner = BROOM;
     Tipo hamm = HAMMER;
     Tipo bel = BELL;
     
     long lastMoveTime = TimeUtils.nanoTime();
     long lastCreateTime = TimeUtils.nanoTime();
     long lastBellTime = TimeUtils.nanoTime();
     long endTime = TimeUtils.nanoTime();
     int piso; 
     long tiempo = 10000;
     int pointsGathered;
     Button butBroom;
     float tmpDoor, tmpFin, tmpCreate =0;
    
     
     float widthScreen;
     float heightScreen;
     float widthScreenPorcent;
     float heightScreenPorcent;
    
     boolean controlPunto = true;
    
      int q=20;
	  int h=0;
	  AccionHerramienta accion;
	  Herramienta herrHammer;
	

	public AScreen4(final FibooGame game) {
		
        super(game);
        this.game = game;
         random = new Random(9);
         camera = new OrthographicCamera();
         camera.setToOrtho(false, 800, 480);
        
     // load the sound effect and the rain background "music"
         dropSound = Gdx.audio.newSound(Gdx.files.internal("ascensor/drop.wav"));
         fondoWow = Gdx.audio.newMusic(Gdx.files.internal("ascensor/kids.wav"));
         elevatorMove = Gdx.audio.newSound(Gdx.files.internal("ascensor/elevatorMove.wav"));
         elevatorStop = Gdx.audio.newSound(Gdx.files.internal("ascensor/elevatorStop.wav"));
         
         ascensor = new Texture(Gdx.files.internal("ascensor/roomLila.png"));
         marco =   new FiguraAscen("ascensor/ascensor.png", door, 1024, 512, 0, 0);    
         leftDoor = new FiguraAscen("ascensor/puertaIz.png", door, 512, 512, 400-500, 0);
         rightDoor = new FiguraAscen("ascensor/puertaD.png", door, 512, 512, 400, 0);  
        // numeross = new FiguraAscen("numero5.png", num, 64, 512, 750-64, 20);
         hammer = new FiguraAscen("ascensor/hammer.png", hamm, 64, 64, 164, -70);
         
         //pink = new FiguraAscen("pinkman.png", bel, 64, 64, 0, 0);
         paint = new FiguraAscen("ascensor/magicStick.png", cleaner, 64, 64, 100, -70);
        // FiguraAscen water = new FiguraAscen("ascensor/water.png", cleaner, 64, 64, 0, 0);
         FiguraAscen black = new FiguraAscen("ascensor/blackman.png", cleaner, 64, 64, 100, 100);
        
         count = new FiguraAscen("ascensor/count.png", cleaner, 64, 64, 0, 800);
         butBroom = new Button();
         int t=64;
         contador = new ArrayList<FiguraAscen>();
         contadorPlus = new ArrayList<FiguraAscen>();
               
         herrHammer = new Herramienta("ascensor/hammer.png", HAMMER);
         
  // create the alien array and spawn the first alien
         alienParasites = new ArrayList<FiguraAscen>();
         contador = new ArrayList<FiguraAscen>();
         crearAlien(black);
              
	}

	private void crearAlien(FiguraAscen alienParasite) {
		
		alienParasite.rectangleTarea.x = MathUtils.random(150, 650 - 64);
		alienParasite.rectangleTarea.y = MathUtils.random(20, 400 - 64);
        alienParasites.add(alienParasite);
        tmpCreate=0;
        lastCreateTime = TimeUtils.nanoTime();
		
	}
	
	private void inline(FiguraAscen figura){
		 if (figura.rectangleTarea.x < 20)
			 figura.rectangleTarea.x = 20;
         if (figura.rectangleTarea.x > 700- 64)
        	 figura.rectangleTarea.x = 700- 64;
         if (figura.rectangleTarea.y < 20)
        	 figura.rectangleTarea.y = 20;
         if (figura.rectangleTarea.y > 400 - 64)
        	 figura.rectangleTarea.y = 400 - 64;

	}
	
	
	 
	 public void abrirDoors(){
		 
	               if(leftDoor.rectangleTarea.x > -500){
                          leftDoor.rectangleTarea.x -= i3;
                     }
				
                    if(rightDoor.rectangleTarea.x < 800){                      
                         rightDoor.rectangleTarea.x += i3;                       
                    }	
			
                    //para espera
		
                    i3+=1;
                    if(rightDoor.rectangleTarea.x < 700){                   	
                    	elevatorMove.play(); 
                    }
	
                    if(rightDoor.rectangleTarea.x >= 700){
                    	sw5=true;
                   	 dropSound.play(); 
                    }


	 }
		 
	
	
	
	//mover la heramienta
	private void mover(FiguraAscen figura){
		 
		 Vector3 touchPos3 = new Vector3();
		 touchPos3.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		 if(figura.tipo == hamm){
        	 camera.unproject(touchPos3);
        	
        	 figura.rectangleTarea.x = touchPos3.x - 64 ;
        	 figura.rectangleTarea.y = touchPos3.y - 64 ;
        }else{
        	if(figura.tipo == cleaner){
           	 camera.unproject(touchPos3);
           	
           	 figura.rectangleTarea.x = touchPos3.x - 64 ;
           	 figura.rectangleTarea.y = touchPos3.y - 64 ;
        }
        
		
		}   	
     }
	

	
	//devolver la heramienta
	private void unmove(FiguraAscen figura){
		 
		if(figura.tipo == hamm){
			figura.rectangleTarea.x = 20 ;
			figura.rectangleTarea.y = 20 ;
		}
	
		if(figura.tipo == cleaner){
			figura.rectangleTarea.x = 100 ;
			figura.rectangleTarea.y = 20 ;
		}
    }
		
	 
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        // tell the camera to update its matrices.
        camera.update();
      
       batch.setProjectionMatrix(camera.combined);
       
       batch.begin();
       batch.draw(ascensor, 0, 0);  
       batch.draw(paint.textureTarea, paint.rectangleTarea.x, paint.rectangleTarea.y);
       batch.draw(hammer.textureTarea, hammer.rectangleTarea.x, hammer.rectangleTarea.y);
       batch.draw(leftDoor.textureTarea, leftDoor.rectangleTarea.x, leftDoor.rectangleTarea.y);
       batch.draw(rightDoor.textureTarea, rightDoor.rectangleTarea.x, rightDoor.rectangleTarea.y);
       batch.draw(marco.textureTarea, marco.rectangleTarea.x, marco.rectangleTarea.y);
       // game.batch.draw(black.textureTarea, black.rectangleTarea.x, black.rectangleTarea.y);
        //game.batch.draw(pink.textureTarea, pink.rectangleTarea.x, pink.rectangleTarea.y);
    
        batch.end();
      
      //Abrrir la puerta del ascensor
        tmpDoor += delta;
        tmpFin += delta;
        
        if(tmpDoor > 0.05){
        	abrirDoors();
        	tmpDoor=0;
        }
        
        
      if(sw5){
    	  marco.rectangleTarea.x=900;
    	   int k=20;
			for(int g=0; g<5; g++)
	        {
	        	contador.add(new FiguraAscen("ascensor/starVacia.png", cleaner, 64, 64, k, 400));
	        	batch.begin();
	        	batch.draw(contador.get(g).textureTarea, contador.get(g).rectangleTarea.x, contador.get(g).rectangleTarea.y);
	        	batch.end();
	        	k+=64;
	        }
        
       batch.begin();
       // game.font.draw(game.batch, "ALIEN SMASHED: " + pointsGathered, 0, 480);

        for (FiguraAscen alienP : alienParasites) {
                batch.draw(alienP.textureTarea, alienP.rectangleTarea.x, alienP.rectangleTarea.y);
        }
        batch.end();
                
 
       
  // check if we need to create a new alien
    //    if (TimeUtils.nanoTime() - lastCreateTime > 50000000 ){
        tmpCreate += delta;
        if(tmpCreate > 2){
        	 FiguraAscen black = new FiguraAscen("ascensor/blackman.png", cleaner, 64, 64, 100, 100);	 
         	 crearAlien(black);
         }
       
     
 // process user input con el herramienta hammer
        
                if (Gdx.input.isTouched()) {
                	 Vector3 touchPos4 = new Vector3();
            		 touchPos4.set(Gdx.input.getX(), Gdx.input.getY(), 0);
           			
            		 if (paint.rectangleTarea.contains(touchPos4.x, touchPos4.y)){
            			   mover(paint); 
                	}
            		 
    				else{
    					mover(paint);
    				}
               }
                else{
                	unmove(paint);
               }
          
            if (Gdx.input.isKeyPressed(Keys.LEFT))
            	paint.rectangleTarea.x -= 300 * Gdx.graphics.getDeltaTime();
            if (Gdx.input.isKeyPressed(Keys.RIGHT))
            	paint.rectangleTarea.x += 300 * Gdx.graphics.getDeltaTime();

            // make sure the broom stays within the screen bounds  
            	inline(paint);
               // inline(hammer);
            	
//            	 Iterator<FiguraAscen> iter2 = alienParasites.iterator();
//                 while (iter2.hasNext() && controlPunto) {
//                 	FiguraAscen alienParasite = iter2.next();
//                 	 for(int y=1 ; y<100 ; y++){
//                    	   alienParasite.rectangleTarea.x += y + Gdx.graphics.getDeltaTime();
//                    	   alienParasite.rectangleTarea.y -= y + Gdx.graphics.getDeltaTime();
//                       }
//                 }
           
           Iterator<FiguraAscen> iter = alienParasites.iterator();
    //calcular puntuacion
         while (iter.hasNext() && controlPunto) {
        	FiguraAscen alienParasite = iter.next();
           if (alienParasite.rectangleTarea.overlaps(paint.rectangleTarea)) {  
        	   alienParasite.setPath("ascensor/pinkman.png");
        	   
        	   h++;
            if(h == 11){
            	pointsGathered++;           	
            	h=0;
            	iter.remove(); 
            }
             dropSound.play();
             
            // iter.remove();         
           }	                      
      }
 
        
         
         
         
 //el contador para terminar el Juego
         if (TimeUtils.nanoTime() - endTime > 90000000 * Gdx.graphics.getRawDeltaTime()){       		
         		c++;
         		if(c >= 99){
         			con++;
         			c=0;
         		}
         		  			
         		endTime = TimeUtils.nanoTime();
         }
 		
         if(pointsGathered < 5 || pointsGathered == 5){
        	 for(int g=0; g<pointsGathered; g++){
        	 
        		contadorPlus.add(new FiguraAscen("ascensor/star.png", cleaner, 64, 64, q, 400));
	        	batch.begin();
	        	batch.draw(contadorPlus.get(g).textureTarea, contadorPlus.get(g).rectangleTarea.x, contadorPlus.get(g).rectangleTarea.y);
	        	batch.end();
	        	q+=64;
//	        	game.batch.begin();
//				game.font.draw(game.batch, "PLUS Gathered"+con, 250, 250);
//				game.batch.end();
//				
//				game.batch.begin();
//				game.font.draw(game.batch, "pointsGathered"+pointsGathered, 250, 100);
//				game.batch.end();
				
        	 }	
         } 	
        
          if(pointsGathered > 5 || con >= 10){
        	  controlPunto=false;
		  if (Gdx.input.isTouched()) {
			  	if(pointsGathered >= 5){
			  		game.setScreen(new AScreenWin(game));
			  		dispose();
			  	}else{
			  		game.setScreen(new AScreenLost(game));
			  		dispose();
			  	}
			  	
		  }
      }   
    }        
 }

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
		fondoWow.play();
		int t=0;
			for(int g=0; g<5; g++)
	        {
	        	contador.add(new FiguraAscen("ascensor/starVacia.png", cleaner, 64, 64, 20+t, 400));
	        	batch.begin();
	        	batch.draw(contador.get(g).textureTarea, contador.get(g).rectangleTarea.x, contador.get(g).rectangleTarea.y);
	        	batch.end();
	        	t+=64;
	        }

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
		elevatorMove.dispose();
		elevatorStop.dispose();
		fondoWow.dispose();
		
		
	}
}




