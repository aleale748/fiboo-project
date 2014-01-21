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



public class AScreen1 implements AbstractScreen{
	
	public final FibooGame game;
	
	Stage stage;
	
	private static final Tipo MINUS = null;
	private static final Tipo GO = null;
	private static final Tipo PLUS = null;
	private static final Tipo DOOR = null;
	private static final Tipo SMASH = null;
	private static final Tipo HAMMER = null;
	private static final Tipo BROOM = null;
	private static final Tipo BELL = null;
	private static final Tipo NUM = null;

	Tipo minus = MINUS;
    Tipo go = GO;
    Tipo plus = PLUS;
    Tipo door = DOOR;
    Tipo num = NUM;
    Tipo cleaner = BROOM;
    Tipo hamm = HAMMER;
    Tipo bel = BELL;
	int spotTime=0;
	Sound bellSound;
	Sound dropSound;
	Sound elevatorMove, elevatorStop;
	Music fondoWow;
	
	OrthographicCamera camera;
	
	 Texture ascensor;
	 FiguraAscen circulo;
	 
	 ImageButton btnPlus;
	 ImageButton btnMenus;
	 ImageButton btnGo;

	 FiguraAscen menosButton;	
	 FiguraAscen plusButton;
	 FiguraAscen goButton;		 
	 FiguraAscen spot;
	 FiguraAscen circuloImg;
	 FiguraAscen bell;
	 FiguraAscen bellL;
	 FiguraAscen bellR;
	 
	 FiguraAscen number0;
	 FiguraAscen number1;
	 FiguraAscen number2;
	 FiguraAscen number3;
	 FiguraAscen number4;
	 FiguraAscen number5;
	 
	 FiguraAscen u0;
	 FiguraAscen u1;
	 FiguraAscen u2;
	 FiguraAscen u3;
	 FiguraAscen u4;
	 FiguraAscen u5;
	 //FiguraAscen marcoAscensor;
	 
	 float tim = 9999 + Gdx.graphics.getRawDeltaTime();
	 boolean dw=true;
	 long endTime = TimeUtils.nanoTime();
	 long lastMoveTimeDoor = TimeUtils.nanoTime();
	  long lastBellTime = TimeUtils.nanoTime();
	 long tiempo = 10000;
	 
	 int velos = 64;
	 int imgSize = 64;
	 Random random;
	 int espacio = 64;
	 static int posAscen = 0;
     public static int posstart=0;
     public static int piso; 
     int posright=800; 
     int posdoor=-512;
     static int pointsGathered;
     float p800= 800, p510 = -512;
     float widthScreen;
     float heightScreen;
     float widthScreenPorcent;
     float heightScreenPorcent;
     boolean sw1=true;
     int g=0;
     int posleft=0;
	 int i2 =0;
	 int i3 =0;
	 int t=0;
	 int pisoTemp=0;
	 float tmpSpot=0;
	 boolean sw2=false;
	 boolean sw3=true;
	 float tmpSpot2 = Gdx.input.getCurrentEventTime();

     public AScreen1(final FibooGame game) {
		
         suoer(game);
         random = new Random(9);
         camera = new OrthographicCamera();
         camera.setToOrtho(false, 800, 480);
         stage = new Stage();
         
         Gdx.input.setInputProcessor(stage);
         
         heightScreen = Gdx.graphics.getHeight();
         widthScreen = Gdx.graphics.getWidth();
         widthScreenPorcent = (float) (widthScreen * 0.10);
         heightScreenPorcent = (float) (heightScreen * 0.10);
         
         // load the drop sound effect and the rain background "music"
         dropSound = Gdx.audio.newSound(Gdx.files.internal("ascensor/drop.wav"));
         fondoWow = Gdx.audio.newMusic(Gdx.files.internal("ascensor/kids.wav"));
         elevatorMove = Gdx.audio.newSound(Gdx.files.internal("ascensor/elevatorMove.wav"));
         elevatorStop = Gdx.audio.newSound(Gdx.files.internal("ascensor/elevatorStop.wav"));
         bellSound = Gdx.audio.newSound(Gdx.files.internal("ascensor/bell3.wav"));
        
         ascensor = new Texture(Gdx.files.internal("ascensor/closeDoor.png"));
         circuloImg = new FiguraAscen("ascensor/j.png", num, 64, 64,  750-64, 20);
         spot = new FiguraAscen("ascensor/spot.png", num, 64, 64, 750-64, 20);
         
         bellL = new FiguraAscen("ascensor/rightBellImg.png", bel, 64, 64, 700, 400);
         bell = new FiguraAscen("ascensor/bellC.png", bel,64, 64, 700, 400);
         bellR = new FiguraAscen("ascensor/leftBellImg.png", bel, 64, 64, 700, 400);
  //BtnSpot
//         TextureRegion playBotonRegion5 = new TextureRegion(new Texture(Gdx.files.internal("spot.png")));
//         Drawable playBotonDrawable5 = new TextureRegionDrawable(playBotonRegion5);
//         btnSpot = new ImageButton(playBotonDrawable5);
//         btnSpot.setPosition(750-128, 20); 
//         stage.addActor(btnSpot);
         
         
          number0 = new FiguraAscen("ascensor/Number0.png", num,64, 64, 800/2 -32, 420);
          number1 = new FiguraAscen("ascensor/Number1.png", num, 64, 64, 800/2 -32, 420);
          number2 = new FiguraAscen("ascensor/Number2.png", num, 64, 64, 800/2 -32, 420);
          number3 = new FiguraAscen("ascensor/Number3.png", num, 64, 64, 800/2 -32, 420);
          number4 = new FiguraAscen("ascensor/Number4.png", num, 64, 64, 800/2 -32, 420);
          number5 = new FiguraAscen("ascensor/Number5.png", num, 64, 64, 800/2 -32, 420);
          
          u5 = new FiguraAscen("ascensor/u5.png", num, 64, 64, 750 -64, 20+64*5);
          u4 = new FiguraAscen("ascensor/u4.png", num, 64, 64, 750 -64, 20+64*4);
          u3 = new FiguraAscen("ascensor/u3.png", num, 64, 64, 750 -64, 20+64*3);
          u2 = new FiguraAscen("ascensor/u2.png", num, 64, 64, 750 -64, 20+64*2);
          u1 = new FiguraAscen("ascensor/u1.png", num, 64, 64, 750 -64, 20+64);
          u0 = new FiguraAscen("ascensor/u0.png", num, 64, 64, 750 -64, 20);
     
          
         
          
//       winSC = new AScreenWin(game);
      
      // create the alien array and spawn the first alien
         piso = MathUtils.random(1, 5);
         
	}
     
     private void ringBell(FiguraAscen bel) {
 		
 		while(g<3){
 		if(TimeUtils.nanoTime() - lastBellTime > 9000000 + Gdx.graphics.getRawDeltaTime()){
 			game.batch.begin();
 				game.batch.draw(bellL.textureTarea, bellL.rectangleTarea.x - 2, bellL.rectangleTarea.y);
 				bellSound.play();
 				game.batch.end();
 				lastBellTime = TimeUtils.nanoTime();
 				g++;
 				
 		}else{
 			if(TimeUtils.nanoTime() - lastBellTime > 9000000 + Gdx.graphics.getRawDeltaTime()){
 				game.batch.begin();
 				game.batch.draw(bell.textureTarea, bell.rectangleTarea.x, bell.rectangleTarea.y);
 				bellSound.play();
 				game.batch.end();
 				lastBellTime = TimeUtils.nanoTime();
 				
 				g++;
 			}else{
 				if(TimeUtils.nanoTime() - lastBellTime > 9000000 + Gdx.graphics.getRawDeltaTime()){
 					game.batch.begin();
 					game.batch.draw(bellR.textureTarea, bellR.rectangleTarea.x + 2, bellR.rectangleTarea.y);
 					
 					bellSound.play();
 					game.batch.end();
 					lastBellTime = TimeUtils.nanoTime(); 
 					g++;
 				}
 			}
 		}
 		}
 		
 	
 	}
     
     public void getPiso(int pisoo){
    	 game.batch.begin();
    	 switch(pisoo){
    	 case 0 :  game.batch.draw(number0.textureTarea, number0.rectangleTarea.x, number0.rectangleTarea.y);break;
    	 case 1 :  game.batch.draw(number1.textureTarea, number1.rectangleTarea.x, number1.rectangleTarea.y);break;
    	 case 2 :  game.batch.draw(number2.textureTarea, number2.rectangleTarea.x, number2.rectangleTarea.y);break;
    	 case 3 :  game.batch.draw(number3.textureTarea, number3.rectangleTarea.x, number3.rectangleTarea.y);break;
    	 case 4 :  game.batch.draw(number4.textureTarea, number4.rectangleTarea.x, number4.rectangleTarea.y);break;
    	 case 5 :  game.batch.draw(number5.textureTarea, number5.rectangleTarea.x, number5.rectangleTarea.y);break;
    	 default: game.batch.draw(number0.textureTarea, number0.rectangleTarea.x, number0.rectangleTarea.y);break;
    	 }
    	 game.batch.end();
     }
    
     
     public void setAscenMarker(){
 		if(dw){
 		int po = 20 + posstart * 64;
 		spot.rectangleTarea.y = po ;
 		circuloImg.rectangleTarea.y = po;
 		dw=false;
 		}

 	}
     
     public void goToPiso(){
    	 switch(piso){
    	 case 0 : {game.setScreen(new AScreen4(game)); dispose();}break;
    	 case 1 : {game.setScreen(new AScreen2(game)); dispose();}break;
    	 case 2 : {game.setScreen(new Ascensor6(game)); dispose();}break;
    	 case 3 : {game.setScreen(new Ascensor7(game)); dispose();}break;
    	 case 4 : {game.setScreen(new Ascensor6(game)); dispose();}break;
    	 case 5 : {game.setScreen(new AScreen2(game)); dispose();}break;
    	 default: {game.setScreen(new Ascensor7(game)); dispose();}break;
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
       
        ringBell(bell);
        game.batch.begin();
        game.batch.draw(ascensor, 0, 0); 
        game.batch.draw(u0.textureTarea, u0.rectangleTarea.x, u0.rectangleTarea.y);
        game.batch.draw(u1.textureTarea, u1.rectangleTarea.x, u1.rectangleTarea.y);
        game.batch.draw(u2.textureTarea, u2.rectangleTarea.x, u2.rectangleTarea.y);
        game.batch.draw(u3.textureTarea, u3.rectangleTarea.x, u3.rectangleTarea.y);
        game.batch.draw(u4.textureTarea, u4.rectangleTarea.x, u4.rectangleTarea.y);
        game.batch.draw(u5.textureTarea, u5.rectangleTarea.x, u5.rectangleTarea.y);
        
        game.batch.draw(spot.textureTarea, spot.rectangleTarea.x, spot.rectangleTarea.y);
        game.batch.draw(circuloImg.textureTarea, circuloImg.rectangleTarea.x, circuloImg.rectangleTarea.y);
        
         game.batch.end();
       
        setAscenMarker();
        fondoWow.play();
        spotTime += delta;
        getPiso(piso);
       
        stage.act(delta);
        stage.draw(); 		
      
     
		// check if we need to ring the bell 
//        if (TimeUtils.nanoTime() - endTime  > 9000000 + Gdx.graphics.getRawDeltaTime()){
//        	
//         	ringBell(bell);
//         	lastBellTime = TimeUtils.nanoTime();
//         }
     
	}  
	
  
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		elevatorMove.play();
		//El Image button plus
		   TextureRegion playBotonRegion = new TextureRegion(new Texture(Gdx.files.internal("ascensor/plus.png")));
	          Drawable playBotonDrawable = new TextureRegionDrawable(playBotonRegion);
	          btnPlus = new ImageButton(playBotonDrawable);
	          btnPlus.setPosition(20, 20+imgSize*3);                   
	          btnPlus.addListener(new InputListener() {
	                  @Override
	                  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	                         // Gdx.app.log(fibooGame.LOG, "Touching down on playBoton");
	                	  
	          			if(circuloImg.rectangleTarea.y <= 20+64*4){
	          				posAscen +=1;
	          				dropSound.play();
	          			}
	                          return true;
	                  }//fin touchDown bntPlus
	                  
	                  @Override
	                  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
	                         // Gdx.app.log(fibooGame.LOG, "Touching up on playBoton");
	                          btnPlus.addAction( sequence(delay(0.2f),
	                          new Action() {
	                                  @Override
	                                  public boolean act(float delta) {
	                                       
	                                	 if(circuloImg.rectangleTarea.y <=  20+64*4){
	                                	  circuloImg.rectangleTarea.y += espacio; 
	                                	 }
	                                          return true;
	                                  }
	                          }));
	                  } //fin touchUp bntPlus
	      
	         }); //fin Listener bntPlus
	          
	          stage.addActor(btnPlus);
    
	          
	          
	 //El Image Button Menus
	          TextureRegion playBotonRegion2 = new TextureRegion(new Texture(Gdx.files.internal("ascensor/minus.png")));
	          Drawable playBotonDrawable2 = new TextureRegionDrawable(playBotonRegion2);
	          btnMenus = new ImageButton(playBotonDrawable2);
	          btnMenus.setPosition(20, 20+imgSize*2);                   
	          btnMenus.addListener(new InputListener() {
	                  @Override
	                  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	                         // Gdx.app.log(fibooGame.LOG, "Touching down on playBoton");
	                	  
	          			if(circuloImg.rectangleTarea.y >= 20+64){
	          				
	          				posAscen -= 1; 
	          				dropSound.play();
	          			}
	                          return true;
	                  } //fin touchDown bntMenus
	                  
	                  @Override
	                  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
	                         // Gdx.app.log(fibooGame.LOG, "Touching up on playBoton");
	                	  btnMenus.addAction( sequence(delay(0.2f),
	                          new Action() {
	                                  @Override
	                                  public boolean act(float delta) {
	                                          // La ??ltima acci??n mover?? hacia pantalla de men??
	                                         // game.setScreen(new ChooseScreen(game));
	                                          //game.setScreen(new MenuScreen(game));
	                                	  if(circuloImg.rectangleTarea.y >= 20+64){
	                                	       circuloImg.rectangleTarea.y -= espacio; 
	                                	  }
	                                          return true;
	                                  }
	                          }));
	                  } //fin touchUp btnMenus
	      
	         });  //fin Listener btnMenus
	          
	          stage.addActor(btnMenus);
    
	          
	          
	      //El Image Button Go
	          TextureRegion playBotonRegion3 = new TextureRegion(new Texture(Gdx.files.internal("ascensor/go.png")));
	          Drawable playBotonDrawable3 = new TextureRegionDrawable(playBotonRegion3);
	          btnGo = new ImageButton(playBotonDrawable3);
	          btnGo.setPosition(20, 20+imgSize); 
	          
	          btnGo.addListener(new InputListener() {
	                  @Override
	                  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	                         // Gdx.app.log(fibooGame.LOG, "Touching down on playBoton");
	                	 
	              
	          		if(posAscen == piso){
	          			 
	                		  dropSound.play();
	                		  int posleft=0;
	                		  pisoTemp = posAscen;
	                	//close door 
	                		 
//	                    		Actor actor = new Actor();
	                    		lastMoveTimeDoor = TimeUtils.nanoTime();
   
	          		}
	          	     return true;
	             } //fin touchDown bntGo
	                  
	                  @Override
	                  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
	                         // Gdx.app.log(fibooGame.LOG, "Touching up on playBoton");
	                	  btnGo.addAction( sequence(delay(0.1f),
	                          new Action() {
	                                  @Override
	                                  public boolean act(float delta) {
	                                	  if( pisoTemp == piso){	
	                                	  int r=posstart;
	                                	tim += 99;	                                	
	                                	 elevatorMove.play();
	                                	 lastMoveTimeDoor = TimeUtils.nanoTime();
	                                	if(posstart < posAscen){
	                                		
	                                			while(sw1){
	                                				if( r <= posAscen && TimeUtils.nanoTime() > lastMoveTimeDoor + tim){	
	                                					  spot.rectangleTarea.y += espacio;
	                                					  game.batch.begin();
		                                  				  game.batch.draw(spot.textureTarea, spot.rectangleTarea.x, spot.rectangleTarea.y);
		                                  				  game.batch.end();
	                                					 //spotTime=0;
	                                					 
	                                					// posSpot += espacio;
	                                					 //btnSpot.setPosition(750-128, posSpot); 
	                                					r++;
	                                					dropSound.play();
	                                					lastMoveTimeDoor = TimeUtils.nanoTime();
//	                                					for(int u=0; u<9999999; u++){int h=7^99999;}
	                                				}
	                                				if(r>=posAscen){
	                                					sw1=false;
	                                					
	                                				}
	                                			}
	                                		}
	                                		
	                                		if(posstart > posAscen){
	                                			while(sw1){
	                                				if( r >= posAscen && TimeUtils.nanoTime() > lastMoveTimeDoor + tim){	
	                                					dropSound.play();
	                                					 spot.rectangleTarea.y -= espacio;
	                                					  game.batch.begin();
		                                  				  game.batch.draw(spot.textureTarea, spot.rectangleTarea.x, spot.rectangleTarea.y);
		                                  				  game.batch.end();
		                                  				  r--;	                                					
	                                					  lastMoveTimeDoor = TimeUtils.nanoTime();
	                                				}
	                                				if(r<=posAscen){
	                                					sw1=false;
	                                					
	                                				}	
	                                			}
	                                		}
	                                		 
	                                		
	                                		posstart = posAscen;
	                                		elevatorMove.stop();
	                                		elevatorStop.play();
	                                  		//if(TimeUtils.nanoTime() > lastMoveTimeDoor + tim){
	                                		
	                                  			sw2=false;
	                                  			elevatorStop.play();
	                                  			goToPiso();
                                  				//game.setScreen(new AScreen2(game));
                                  				//dispose();
	                                  		//}
	                                			
                           	  }
	                          return true;
	                             }
	                	  
	                      }));
	                      
	                  }//fin touchUp btnGo 
	                  
	      
	         }); //fin Listener btnGo
	          
	          stage.addActor(btnGo);
             
	} //fin el metodo Show          
	          

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
		//plusButton.dispose();
//		menosButton.dispose();
		
		stage.dispose();
		//manager.dispose();
		dropSound.dispose();
		fondoWow.dispose();
		elevatorMove.dispose();
		elevatorStop.dispose();
		
	}
}
