package es.uca.fiboo.ascensor.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import sun.net.idn.Punycode;

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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.TimeUtils;


public class AScreen2 extends AbstractScreen{
	
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
	Stage stage;

	 Texture ascensor;
	 FiguraAscen marco;
	 FiguraAscen leftDoor;
	 FiguraAscen rightDoor;
	 FiguraAscen numeross;
	 FiguraAscen marker;
	 FiguraAscen hammer;
	 FiguraAscen broom;
	 FiguraAscen bell;
	 FiguraAscen bellL;
	 FiguraAscen bellR;
	 FiguraAscen count;
	 FiguraAscen sofa;
	 
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
     float tmpDoor, tmpFin =0;
    int pos=4;
     
     float widthScreen;
     float heightScreen;
     float widthScreenPorcent;
     float heightScreenPorcent;
    
     boolean controlPunto = true;
    
      int q=20;
	  int h=0;
	  AccionHerramienta accion;
	  Herramienta herrHammer;
	  ImageButton btnSilla;
	  FiguraAscen silla;
	  
	

	public AScreen2(final FibooGame game) {
		
        super(game);
        this.game = game;
         random = new Random(9);
         camera = new OrthographicCamera();
         camera.setToOrtho(false, 800, 480);
         stage = new Stage();
         Gdx.input.setInputProcessor(stage);
         
     // load the sound effect and the rain background "music"
         dropSound = Gdx.audio.newSound(Gdx.files.internal("ascensor/smerk.mp3"));
         fondoWow = Gdx.audio.newMusic(Gdx.files.internal("sonidos/fondo.wav"));
         elevatorMove = Gdx.audio.newSound(Gdx.files.internal("ascensor/elevatorMove.wav"));
         elevatorStop = Gdx.audio.newSound(Gdx.files.internal("ascensor/elevatorStop.wav"));
         
         ascensor = new Texture(Gdx.files.internal("ascensor/roomNaraja.png"));
         marco =   new FiguraAscen("ascensor/ascensor.png", door, 1024, 512, 0, 0);
         
         leftDoor = new FiguraAscen("ascensor/puertaIz.png", door, 512, 512, 400-500, 0);
         rightDoor = new FiguraAscen("ascensor/puertaD.png", door, 512, 512, 400, 0);  
         numeross = new FiguraAscen("ascensor/numero5.png", num, 64, 512, 750-64, 20);
         hammer = new FiguraAscen("ascensor/hammer.png", hamm, 64, 64, 164, -70);
         broom = new FiguraAscen("ascensor/broom.png", cleaner, 64, 64, 100, -70);
         FiguraAscen water = new FiguraAscen("water.png", cleaner, 64, 64, 0, 0);
         count = new FiguraAscen("ascensor/count.png", cleaner, 64, 64, 0, 800);
         butBroom = new Button();
         int t=64;
         contador = new ArrayList<FiguraAscen>();
         contadorPlus = new ArrayList<FiguraAscen>();
         silla = new FiguraAscen("ascensor/silla4.png", cleaner, 128, 128, 500-64, 50);
         sofa = new FiguraAscen("ascensor/sofa.png", cleaner, 64, 64, 100, 20);
         herrHammer = new Herramienta("ascensor/hammer.png", HAMMER);
         
  // create the alien array and spawn the first alien
         alienParasites = new ArrayList<FiguraAscen>();
         contador = new ArrayList<FiguraAscen>();
         crearAlien(water);
              
	}

	public void crearAlien(FiguraAscen alienParasite) {
		if(controlPunto){
		alienParasite.rectangleTarea.x = MathUtils.random(150, 650 - 64);
		alienParasite.rectangleTarea.y = MathUtils.random(20, 200 - 64);
        alienParasites.add(alienParasite);
        
        lastCreateTime = TimeUtils.nanoTime();
		}
	}
	
	public void inline(FiguraAscen figura){
		 if (figura.rectangleTarea.x < 20)
			 figura.rectangleTarea.x = 20;
         if (figura.rectangleTarea.x > 700- 64)
        	 figura.rectangleTarea.x = 700- 64;
         if (figura.rectangleTarea.y < 20)
        	 figura.rectangleTarea.y = 20;
         if (figura.rectangleTarea.y > 400 - 64)
        	 figura.rectangleTarea.y = 400 - 64;

	}
	
	
	public String getColor(int num){		
		
		switch(num){
		case 1:  return "ascensor/silla1.png"; 
		case 2:  return "ascensor/silla2.png";
		case 3:  return  "ascensor/silla3.png";
		case 4:  return  "ascensor/silla4.png";
		default: return  "ascensor/silla4.png";
		
		}
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
	public void mover(FiguraAscen figura){
		 
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
	public void unmove(FiguraAscen figura){
		 
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
        game.batch.draw(silla.textureTarea, silla.rectangleTarea.x, silla.rectangleTarea.y);
        game.batch.draw(sofa.textureTarea, sofa.rectangleTarea.x, sofa.rectangleTarea.y);
        game.batch.draw(broom.textureTarea, broom.rectangleTarea.x, broom.rectangleTarea.y);
        game.batch.draw(hammer.textureTarea, hammer.rectangleTarea.x, hammer.rectangleTarea.y);
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
        
   //despues de abrr las puertas     
      if(sw5){
    	  	marco.rectangleTarea.x=900;
    	  	stage.act();
    	  	stage.draw();
    	  	
    	   int k=20;
			for(int g=0; g<5; g++)
	        {
	        	contador.add(new FiguraAscen("ascensor/starVacia.png", cleaner, 64, 64, k, 400));
	        	game.batch.begin();
	        	game.batch.draw(contador.get(g).textureTarea, contador.get(g).rectangleTarea.x, contador.get(g).rectangleTarea.y);
	        	game.batch.end();
	        	k+=64;
	        }
        
        game.batch.begin();
        	for (FiguraAscen alienP : alienParasites) {
                game.batch.draw(alienP.textureTarea, alienP.rectangleTarea.x, alienP.rectangleTarea.y);
        	}
        game.batch.end();
            
       
       
 
       
  // check if we need to create a new alien
        if (TimeUtils.nanoTime() - lastCreateTime > 500000000){
        	 FiguraAscen water = new FiguraAscen("ascensor/water.png", cleaner, 64, 64, 0, 0);
        	 
         	crearAlien(water);
         }
       
     
 // process user input con el herramienta hammer
        
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
                
                
          
            if (Gdx.input.isKeyPressed(Keys.LEFT))
            	broom.rectangleTarea.x -= 300 * Gdx.graphics.getDeltaTime();
            if (Gdx.input.isKeyPressed(Keys.RIGHT))
            	broom.rectangleTarea.x += 300 * Gdx.graphics.getDeltaTime();

            // make sure the broom stays within the screen bounds  
            	inline(broom);
               // inline(hammer);
           
           Iterator<FiguraAscen> iter = alienParasites.iterator();
    //calcular puntuacion
         while (iter.hasNext() && controlPunto) {
        	FiguraAscen alienParasite = iter.next();
           if (alienParasite.rectangleTarea.overlaps(broom.rectangleTarea)) {      	   
        	   h++;
            if(h == 11){
            	pointsGathered++;           	
            	h=0;
            }
             dropSound.play();
             iter.remove();         
           }	                      
      }
 
 //el contador para terminar el Juego
         if (TimeUtils.nanoTime() - endTime > 900000000 * Gdx.graphics.getRawDeltaTime()){       		
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
	        	game.batch.begin();
	        	game.batch.draw(contadorPlus.get(g).textureTarea, contadorPlus.get(g).rectangleTarea.x, contadorPlus.get(g).rectangleTarea.y);
	        	game.batch.end();
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
	        	game.batch.begin();
	        	game.batch.draw(contador.get(g).textureTarea, contador.get(g).rectangleTarea.x, contador.get(g).rectangleTarea.y);
	        	game.batch.end();
	        	t+=64;
	        }
			
			TextureRegion tRegion = new TextureRegion(new Texture(Gdx.files.internal(getColor(4))));
			Drawable drawable = new TextureRegionDrawable(tRegion);
			
			btnSilla = new ImageButton(drawable);
			btnSilla.setPosition(500-64, 40);
			btnSilla.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
					
					return true;
				}
				
				@Override
				 public void touchUp(InputEvent event, float x, float y, int pointer, int button){
					btnSilla.addAction( sequence(delay(0.01f), fadeOut(0.01f), new  Action(){
					@Override
					public boolean act(float delta){
						if(pointsGathered >= 1){
							pointsGathered --;
						}
						silla.setPath(getColor(pos));
						pos--;
															
						return true;
						}
							
					}));
					}
				});
				
			stage.addActor(btnSilla);
			
			

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
		
	}
}




