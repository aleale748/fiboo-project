package es.uca.fiboo.ascensor.screens;

import java.nio.channels.GatheringByteChannel;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import es.uca.fiboo.FibooGame;
import es.uca.fiboo.screens.AbstractScreen;
import es.uca.fiboo.ascensor.screens.Herramienta.Tipo;

public class Ascensor6 extends AbstractScreen{

	final FibooGame game;
	private static final Tipo DOOR = null;
	private static final Tipo NUM = null;
	private static final Tipo BROOM = null;
	Tipo door = DOOR;
    Tipo num = NUM;
    Tipo cleaner = BROOM;
	OrthographicCamera camara;
	Stage stage;
	Sound ascendoorsound;
	Sound bien;
	
	Texture ascensor;
	ArrayList<FiguraAscen> bichoses;
	ArrayList<FiguraAscen> blockes;
	ArrayList<FiguraAscen>balls;
	ArrayList<FiguraAscen>contador;
	ArrayList<FiguraAscen>contadorPlus;
	
	ImageButton btnBicho;
	ImageButton btnBlocks;
	ImageButton btnBall;
	ImageButton btnTotal;
	
	FiguraAscen marco;
	 FiguraAscen leftDoor;
	 FiguraAscen rightDoor;
	 float tmpDoor, finTime = 0;
	 boolean doorOp = false;
	 int i3=0;
	
	int nbichos=20;
	static int pos, pos3, pos2=0;
	int pointsGathered = 0;
	int posXstar=20;
	int numeroT = 0;
	
	Texture num0;
	Texture num1;
	Texture num2;
	Texture num3;
	Texture num4;
	Texture num5;
	
	public Ascensor6(final FibooGame game){
		
		super(game);
		this.game = game;
		camara = new OrthographicCamera();
		camara.setToOrtho(false, 800, 480);
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		ascendoorsound =  Gdx.audio.newSound(Gdx.files.internal("ascensor/elevatorMove.wav"));
		bien = Gdx.audio.newSound(Gdx.files.internal("sonidos/bien.ogg"));
		ascensor = new Texture(Gdx.files.internal("ascensor/roomPink.png"));
		bichoses = new ArrayList<FiguraAscen> ();
		blockes = new ArrayList<FiguraAscen> ();
		balls = new ArrayList<FiguraAscen> ();
		
		for(int i=0; i<nbichos; i++){
			bichoses.add( new FiguraAscen(getColor(), cleaner, 64, 64, 800, 0));
			blockes.add( new FiguraAscen("ascensor/block1.png", cleaner, 64, 64, 800, 0));
			balls.add( new FiguraAscen("ascensor/ball.png", cleaner, 64, 64, 800, 0));
		}
		
		 marco =   new FiguraAscen("ascensor/ascensor.png", door, 1024, 512, 0, 0);        
         leftDoor = new FiguraAscen("ascensor/puertaIz.png", door, 512, 512, 400-500, 0);
         rightDoor = new FiguraAscen("ascensor/puertaD.png", door, 512, 512, 400, 0);  
         contador = new ArrayList<FiguraAscen>();
         contadorPlus = new ArrayList<FiguraAscen>();
         
         num0 = new Texture(Gdx.files.internal("ascensor/Number0.png"));
         num1 = new Texture(Gdx.files.internal("ascensor/Number1.png"));
         num2 = new Texture(Gdx.files.internal("ascensor/Number2.png"));
         num3 = new Texture(Gdx.files.internal("ascensor/Number3.png"));
         num4 = new Texture(Gdx.files.internal("ascensor/Number4.png"));
         num5 = new Texture(Gdx.files.internal("ascensor/Number5.png"));
         
         numeroT = MathUtils.random(0, 5);
         
         
         
	}
	
//Metodos de funcionalidad
	
	public String getColor(){		
		int tipoColor = MathUtils.random(1, 4);
		switch(tipoColor){
		case 1:  return "ascensor/colorR.png"; 
		case 2:  return "ascensor/colorB.png";
		case 3:  return  "ascensor/colorV.png";
		case 4:  return  "ascensor/colorO.png";
		default: return  "ascensor/colorR.png";
		
		}
	}		

	
//return el numero
	public Texture getNumero(int num){		
		switch(num){
		case 0:  return num0; 
		case 1:  return num1;
		case 2:  return num2;
		case 3:  return num3;
		case 4:  return num4;
		case 5:  return num5;
		default: return num0;
		
		}
	}		
	
	
	//abre el ascensor
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
        	 ascendoorsound.play(); 
         }

         if(rightDoor.rectangleTarea.x >= 700){
         	doorOp=true;
         	ascendoorsound.stop(); 
         }


}

	
	
	
	
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camara.update();
	batch.setProjectionMatrix(camara.combined);
		
		
		
		batch.begin();
			batch.draw(ascensor, 0,0);
			for(int i=0; i<nbichos; i++){
				batch.draw(bichoses.get(i).textureTarea, bichoses.get(i).rectangleTarea.x, bichoses.get(i).rectangleTarea.y);
				batch.draw(blockes.get(i).textureTarea, blockes.get(i).rectangleTarea.x, blockes.get(i).rectangleTarea.y);
				batch.draw(balls.get(i).textureTarea, balls.get(i).rectangleTarea.x, balls.get(i).rectangleTarea.y);
				
			}
		batch.end();
		
		stage.act();
		stage.draw();
		
		batch.begin();	
			batch.draw(getNumero(numeroT), 400-32, 300);
			batch.draw(leftDoor.textureTarea, leftDoor.rectangleTarea.x, leftDoor.rectangleTarea.y);
	        batch.draw(rightDoor.textureTarea, rightDoor.rectangleTarea.x, rightDoor.rectangleTarea.y);
	        batch.draw(marco.textureTarea, marco.rectangleTarea.x, marco.rectangleTarea.y);
	    batch.end();
			
			
			tmpDoor+= delta;
			
			 if(tmpDoor > 0.05){
		        	abrirDoors();
		        	tmpDoor=0;
		        }
		        
	//despues de abrr las puertas     
	    if(doorOp){
		    	  	
		    	  //Crear los estrellas
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
		     
		    	  		
		    	  		if(pointsGathered < 5 || pointsGathered == 5){
		    	        	 for(int g=0; g<pointsGathered; g++){
		    	        	 
		    	        		contadorPlus.add(new FiguraAscen("ascensor/star.png", cleaner, 64, 64, posXstar, 400));
		    		        	batch.begin();
		    		        	batch.draw(contadorPlus.get(g).textureTarea, contadorPlus.get(g).rectangleTarea.x, contadorPlus.get(g).rectangleTarea.y);
		    		        	batch.end();
		    		        	posXstar+=64;
		    		        	
		    	        	 }
		    	  		}
		    	  		
		    	  		finTime += delta; //control de tiempo para terminar el juego
		    	  		
		    	  		if(pointsGathered > 5 || finTime >= 80){
		    	        	 
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
		TextureRegion tRegion = new TextureRegion(new Texture(Gdx.files.internal("ascensor/colorR.png")));
		Drawable drawable = new TextureRegionDrawable(tRegion);
		
		btnBicho = new ImageButton(drawable);
		btnBicho.setPosition(100, 20);
		btnBicho.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				
				return true;
			}
			
			@Override
			 public void touchUp(InputEvent event, float x, float y, int pointer, int button){
				btnBicho.addAction(new  Action(){
				@Override
				public boolean act(float delta){
					
					bichoses.get(pos).rectangleTarea.x = Gdx.input.getX() - 32; 
					bichoses.get(pos).rectangleTarea.y = 480 -64  - Gdx.input.getY();
										
					if(pos<nbichos){
						pos ++;
					}
					if (pos >= nbichos){
						pos=0;
					}
					
					return true;
					}
						
				});
				}
			});
			
		stage.addActor(btnBicho);
		
		
		
		TextureRegion tRegion2 = new TextureRegion(new Texture(Gdx.files.internal("ascensor/block1.png")));
		Drawable drawable2 = new TextureRegionDrawable(tRegion2);
		
		btnBlocks = new ImageButton(drawable2);
		btnBlocks.setPosition(200, 20);
		btnBlocks.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				
				return true;
			}
			
			@Override
			 public void touchUp(InputEvent event, float x, float y, int pointer, int button){
				btnBlocks.addAction(new  Action(){
				@Override
				public boolean act(float delta){
					
					blockes.get(pos).rectangleTarea.x = Gdx.input.getX() - 32; 
					blockes.get(pos).rectangleTarea.y = 480 - 64 - Gdx.input.getY();
					
					
					if(pos2 < nbichos){
						pos2 ++;
					}
					if (pos2 >= nbichos){
						pos2 = 0;
					}
					
					return true;
					}
						
				});
				}
			});
			
		stage.addActor(btnBlocks);
		
		
		TextureRegion tRegion3 = new TextureRegion(new Texture(Gdx.files.internal("ascensor/ball.png")));
		Drawable drawable3 = new TextureRegionDrawable(tRegion3);
		
		btnBall = new ImageButton(drawable3);
		btnBall.setPosition(300, 20);
		btnBall.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				
				return true;
			}
			
			@Override
			 public void touchUp(InputEvent event, float x, float y, int pointer, int button){
				btnBall.addAction(new  Action(){
				@Override
				public boolean act(float delta){
					
					balls.get(pos).rectangleTarea.x = Gdx.input.getX() - 32; 
					balls.get(pos).rectangleTarea.y = 480 - 64 - Gdx.input.getY();
					
					
					if(pos3 < nbichos){
						pos3 ++;
					}
					if (pos3 >= nbichos){
						pos3 = 0;
					}
					
					return true;
					}
						
				});
				}
			});
			
		stage.addActor(btnBall);
		
		
		TextureRegion tRegion4 = new TextureRegion(new Texture(Gdx.files.internal("ascensor/go3.png")));
		Drawable drawable4 = new TextureRegionDrawable(tRegion4);
		
		btnTotal = new ImageButton(drawable4);
		btnTotal.setPosition(400, 20);
		btnTotal.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				
				return true;
			}
			
			@Override
			 public void touchUp(InputEvent event, float x, float y, int pointer, int button){
				btnTotal.addAction(new  Action(){
				@Override
				public boolean act(float delta){
					
					if(pos + pos2 + pos3 == numeroT){
						bien.play();
						pointsGathered ++;
					}
					
					for(int j=0; j< nbichos; j++){
						bichoses.get(j).rectangleTarea.x = 800; 
						bichoses.get(j).rectangleTarea.y = 0;
						blockes.get(j).rectangleTarea.x = 800; 
						blockes.get(j).rectangleTarea.y = 0;
						balls.get(j).rectangleTarea.x = 800; 
						balls.get(j).rectangleTarea.y = 0;
						
					}
					
					pos =0; pos2 =0; pos3 =0;
					numeroT= MathUtils.random(0, 5);;
					
					return true;
					}
						
				});
				}
			});
			
		stage.addActor(btnTotal);
		
		
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
		stage.dispose();
		ascendoorsound.dispose();		
		ascensor.dispose();
		 num0.dispose();
		 num1.dispose();
		 num2.dispose();
		 num3.dispose();
		 num4.dispose();
		 num5.dispose();
	}

}
