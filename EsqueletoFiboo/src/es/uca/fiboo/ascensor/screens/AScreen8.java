package es.uca.fiboo.ascensor.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.drop.Herramienta.Tipo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.TimeUtils;


public class AScreen8 implements AbstractScreen{
	
	public final FibooGame game;
	Stage stage;
	private static final Tipo MINUS = null;
	private static final Tipo GO = null;
	private static final Tipo PLUS = null;
	private static final Tipo DOOR = null;
	private static final Tipo NUM = null;
	private static final Tipo SMASH = null;
	private static final Tipo HAMMER = null;
	private static final Tipo BROOM = null;
	private static final Tipo BELL = null;
	//private static final ImageButton TextureRegion = null;
	

	Sound smash;
	Sound dropSound;
	//Sound bellSound;
	Sound elevatorMove, elevatorStop;
	Music fondoWow;
	
	
	OrthographicCamera camera;

	 Texture ascensor;
	 FiguraAscen marco;
	 FiguraAscen leftDoor;
	 FiguraAscen rightDoor;
	 
	 ImageButton btnAlien; 
     ImageButton btnBichos; 
     ImageButton btnIr;
     ImageButton btnExit2;
     ImageButton btnTester;
	
	 FiguraAscen broom;

	 FiguraAscen count;
 
	static ArrayList<FiguraAscen> aliens;
	static  ArrayList<FiguraAscen> bichoses;
	 ArrayList<FiguraAscen> contador;
	 ArrayList<FiguraAscen> contadorPlus;
	 ArrayList<ImageButton> btnColors;
	 
	 int tmpPos=1;
	 int i3=0;
	 int imgTam = 64;
	 int c=0;
	 int con=0;
	 //Random random; 
     int espacio = 3;
     boolean no=true;
     boolean st = true;
     boolean sw5=false;
     int numCount=0;
     Tipo minus = MINUS;
     Tipo go = GO;
     Tipo door = DOOR;
     Tipo num = NUM;
     Tipo cleaner = BROOM;
     Tipo hamm = HAMMER;
     Tipo bel = BELL;
     
     long lastMoveTime = TimeUtils.nanoTime();
     long lastCreateTime = TimeUtils.nanoTime();
     long lastBellTime = TimeUtils.nanoTime();
     long endTime = TimeUtils.nanoTime();
    // int piso; 
     long tiempo = 1000;
     int pointsGathered=0;
     int puntua2=0;
     float tmpDoor, tmpFin =0;
    
    
     
     float widthScreen;
     float heightScreen;
     float widthScreenPorcent;
     float heightScreenPorcent;
     int pos = 0, pos3 = 0;
     int tamColor = 50;
     int z=0;
     boolean controlPunto = true;
     int puntua=0;
      int q=20;
	  int h=0;
	  AccionHerramienta accion;
	  Herramienta herrHammer;
	int numero1, numero2, resultado=0;
	AScreen1 ascreen1;
	int nBicho = 10;
	 Drawable playBotonDrawable7;
	 TextureRegion playBotonRegion7;
	 
	public AScreen8(final FibooGame game) {
		
         super(game);
        // random = new Random(9);
         camera = new OrthographicCamera();
         camera.setToOrtho(false, 800, 480);
         stage = new Stage();
        
         Gdx.input.setInputProcessor(stage);
        
     // load the sound effect and the rain background "music"
         dropSound = Gdx.audio.newSound(Gdx.files.internal("ascensor/drop.wav"));
         fondoWow = Gdx.audio.newMusic(Gdx.files.internal("ascensor/kids.wav"));
         elevatorMove = Gdx.audio.newSound(Gdx.files.internal("ascensor/kids.wav"));
         elevatorStop = Gdx.audio.newSound(Gdx.files.internal("ascensor/drop.wav"));
         smash = Gdx.audio.newSound(Gdx.files.internal("ascensor/sad.mp3"));
         
         ascensor = new Texture(Gdx.files.internal("ascensor/roomSpace.png"));
         marco =   new FiguraAscen("ascensor/ascensor.png", door, 1024, 512, 0, 0);    
         leftDoor = new FiguraAscen("ascensor/puertaIz.png", door, 512, 512, 400-500, 0);
         rightDoor = new FiguraAscen("ascensor/puertaD.png", door, 512, 512, 400, 0);  
            
         broom = new FiguraAscen("ascensor/hammer.png", cleaner, 32, 32, 100, 20);
       
         count = new FiguraAscen("ascensor/count.png", cleaner, 64, 64, 0, 800);
        
         int t=64;
         contador = new ArrayList<FiguraAscen>();
         contadorPlus = new ArrayList<FiguraAscen>();
         aliens = new ArrayList<FiguraAscen>(); 
         bichoses = new ArrayList<FiguraAscen>();
        
         herrHammer = new Herramienta("ascensor/hammer.png", HAMMER);
         ascreen1 = new AScreen1(game);    

         boolean s=true;
         for(int f=0; f<nBicho; f++){
        	 
        	 aliens.add(new FiguraAscen(getColor(f), cleaner, 128, 128, 800, 0));
     	
        	bichoses.add(new FiguraAscen("ascensor/AlienParasite.png", cleaner, 128, 128, 800, 0));	
        	 
         }
               
	}

//	private void crearAlien(FiguraAscen alienParasite) {
//		if(controlPunto){
//		alienParasite.rectangleTarea.x = MathUtils.random(150, 650 - 64);
//		alienParasite.rectangleTarea.y = MathUtils.random(20, 200 - 64);
//        alienParasites.add(alienParasite);
//        
//        lastCreateTime = TimeUtils.nanoTime();
//		}
//	}
	
	private void inline(FiguraAscen figura){
		 if (figura.rectangleTarea.x < 50)
			 figura.rectangleTarea.x = 600;
         if (figura.rectangleTarea.x > 650)
        	 figura.rectangleTarea.x = 51;
         if (figura.rectangleTarea.y < 50)
        	 figura.rectangleTarea.y = 390;
         if (figura.rectangleTarea.y > 400)
        	 figura.rectangleTarea.y = 51;

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
                    	elevatorMove.stop(); 
                    }
	 }

	 
	public String getColor(int jj){
		
		switch(jj){
		case 0:  return "ascensor/alien1.png";
		case 1:  return "ascensor/alien2.png"; 
		case 2:  return "ascensor/alien3.png";
		case 3:  return  "ascensor/alien4.png";
		case 4:  return  "ascensor/alien5.png";
		case 5:  return  "ascensor/alien6.png";
		case 6:  return  "ascensor/alien7.png";
		case 7:  return  "ascensor/alien6.png";
		case 8:  return  "ascensor/alien7.png";
		case 9:  return  "ascensor/alien8.png";
		default: return  "ascensor/alien2.png";
		
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
		// coloriar(figura);
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
      
        game.batch.setProjectionMatrix(camera.combined);
       
        game.batch.begin();
        	game.batch.draw(ascensor, 0, 0);  
        	game.batch.draw(broom.textureTarea, broom.rectangleTarea.x, broom.rectangleTarea.y);
       
        	for(int j=0; j<nBicho; j++){
        	
        		game.batch.draw(bichoses.get(j).textureTarea, bichoses.get(j).rectangleTarea.x, bichoses.get(j).rectangleTarea.y);
        		game.batch.draw(aliens.get(j).textureTarea, aliens.get(j).rectangleTarea.x, aliens.get(j).rectangleTarea.y);
        	}
         
        game.batch.end();
        
        stage.act(delta);
        stage.draw(); 		
      
        game.batch.begin();
        	game.batch.draw(leftDoor.textureTarea, leftDoor.rectangleTarea.x, leftDoor.rectangleTarea.y);
        	game.batch.draw(rightDoor.textureTarea, rightDoor.rectangleTarea.x, rightDoor.rectangleTarea.y);
        	game.batch.draw(marco.textureTarea, marco.rectangleTarea.x, marco.rectangleTarea.y);
        game.batch.end();
    
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
	        	game.batch.begin();
	        	game.batch.draw(contador.get(g).textureTarea, contador.get(g).rectangleTarea.x, contador.get(g).rectangleTarea.y);
	        	game.batch.end();
	        	k+=64;
	        }
 
			pos3 = MathUtils.random(2, nBicho-2);
		 
        	 bichoses.get(pos3).rectangleTarea.y -= 200 * Gdx.graphics.getDeltaTime();
        	 bichoses.get(pos3).rectangleTarea.x += delta;
			
        	 bichoses.get(pos3 -1).rectangleTarea.y += 200 * Gdx.graphics.getDeltaTime();
        	 bichoses.get(pos3 -1).rectangleTarea.x -= delta *2;
 
        	 bichoses.get(pos3 -2).rectangleTarea.y += delta *2;
        	 bichoses.get(pos3 -2).rectangleTarea.x -= 100 * Gdx.graphics.getDeltaTime();
        	 
     //mover el martillo   para matar bichos	 
        	 if (Gdx.input.isTouched()) {
            	 Vector3 touchPos4 = new Vector3();
        		 touchPos4.set(Gdx.input.getX(), Gdx.input.getY(), 0);
       			
        		 if (broom.rectangleTarea.contains(touchPos4.x, touchPos4.y)){
        			   mover(broom); 
            	}
        		 
				else{
					mover(broom);
				}
           }
            else{
            	unmove(broom);
           }
        	       	 
        	 
        	 Iterator<FiguraAscen> iter = bichoses.iterator();
        	    //calcular puntuacion
        	         while (iter.hasNext()) {
        	        	FiguraAscen alienParasite = iter.next();
        	           if (alienParasite.rectangleTarea.overlaps(broom.rectangleTarea)) {      	   
        	        	   dropSound.play();
        	        	   puntua++;
        	             alienParasite.rectangleTarea.x = 0;
        	             alienParasite.rectangleTarea.y = 0;
        	                    
        	           }	                      
        	      }
 
        	         
        	 // make sure the broom stays within the screen bounds  
          	inline(bichoses.get(pos3));
          	inline(bichoses.get(pos3-1));
          	inline(bichoses.get(pos3-2));
              
 	
            	numCount += delta;
       
           if (puntua/2 >= 1) {      	   
        	  
            	pointsGathered++;           	
            	puntua=0;
            	dropSound.play();
           }
                                 
    
 		
         if(pointsGathered < 5 || pointsGathered == 5){
        	 for(int g=0; g<pointsGathered; g++){
        	 
        		contadorPlus.add(new FiguraAscen("ascensor/star.png", cleaner, 64, 64, q, 400));
	        	game.batch.begin();
	        	game.batch.draw(contadorPlus.get(g).textureTarea, contadorPlus.get(g).rectangleTarea.x, contadorPlus.get(g).rectangleTarea.y);
	        	game.batch.end();
	        	q+=64;

        	 }	
         } 	
        
          if(pointsGathered > 5 ){
//        	  controlPunto=false;
        	  if (Gdx.input.isTouched()) {		  	
			  		game.setScreen(new AScreenWin(game));
			  		dispose();			  
			  	}			  	
		  }
          
          if(numCount > 20){
        	  game.setScreen(new AScreenLost(game));
		  		dispose();	
          }
          
          if (Gdx.input.isKeyPressed(Keys.LEFT))
           	broom.rectangleTarea.x -= 300 * Gdx.graphics.getDeltaTime();
           if (Gdx.input.isKeyPressed(Keys.RIGHT))
           	broom.rectangleTarea.x += 300 * Gdx.graphics.getDeltaTime();
      }   
    }        
 

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
//		btnColors = new ArrayList<ImageButton>();
		
		
		int t=0;
			for(int g=0; g<5; g++)
	        {
	        	contador.add(new FiguraAscen("ascensor/starVacia.png", cleaner, 64, 64, 20+t, 400));
	        	game.batch.begin();
	        	game.batch.draw(contador.get(g).textureTarea, contador.get(g).rectangleTarea.x, contador.get(g).rectangleTarea.y);
	        	game.batch.end();
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
		stage.dispose();
		
		smash.dispose();
		
		
	}
}







