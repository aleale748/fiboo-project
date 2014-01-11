package es.uca.fiboo;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Json;

import es.uca.fiboo.actores.Complemento;
import es.uca.fiboo.actores.Personaje;
import es.uca.fiboo.screens.MainScreen;

public class fibooGame extends Game {

                public static final String VERSION = "0.0.0.01 Pre-Alpha";
                public static final String LOG = "fibooGame";
                public static final boolean DEV_MODE = false;
                
                public static final AssetManager MANAGER = new AssetManager();

                private static Personaje personaje;
                private static ArrayList<Complemento> complementos;
                private FileHandle savedData, comps;

                // Clase de ayuda de libgdx que logea los FPS cada segundo
                private FPSLogger fpsLogger;

                public MainScreen getMainScreen() {
                        return new MainScreen(this);
                }
                
                @SuppressWarnings("unchecked")
                @Override
                public void create() {
                    //Cargamos las im���genes en el manager
                		Gdx.app.log(fibooGame.LOG, "Creating game");
                        MANAGER.load("marcianosminigame/bien.png", Texture.class);
                        MANAGER.load("marcianosminigame/nave.png", Texture.class);
                        MANAGER.load("marcianosminigame/marciano.png", Texture.class);
                        MANAGER.load("cameraminigame/cesped2.png", Texture.class);
                		MANAGER.load("cameraminigame/0.png", Texture.class);
                		MANAGER.load("cameraminigame/1.png", Texture.class);
                		MANAGER.load("cameraminigame/2.png", Texture.class);
                		MANAGER.load("cameraminigame/3.png", Texture.class);
                		MANAGER.load("cameraminigame/4.png", Texture.class);
                		MANAGER.load("cameraminigame/5.png", Texture.class);
                		MANAGER.load("cameraminigame/6.png", Texture.class);
                		MANAGER.load("cameraminigame/7.png", Texture.class);
                		MANAGER.load("cameraminigame/8.png", Texture.class);
                		MANAGER.load("cameraminigame/9.png", Texture.class);
                		MANAGER.load("cameraminigame/menu0.png", Texture.class);
                		MANAGER.load("cameraminigame/menu1.png", Texture.class);
                		MANAGER.load("cameraminigame/menu2.png", Texture.class);
                		MANAGER.load("cameraminigame/menu3.png", Texture.class);
                		MANAGER.load("cameraminigame/menu4.png", Texture.class);
                		MANAGER.load("cameraminigame/menu5.png", Texture.class);
                		MANAGER.load("cameraminigame/menu6.png", Texture.class);
                		MANAGER.load("cameraminigame/menu7.png", Texture.class);
                		MANAGER.load("cameraminigame/menu8.png", Texture.class);
                		MANAGER.load("cameraminigame/menu9.png", Texture.class);
                        MANAGER.load("naveminigame/older/win.png", Texture.class);
                        MANAGER.load("naveminigame/disparar2.png", Texture.class);
                        MANAGER.load("naveminigame/asteroideVacio.png", Texture.class);
                        MANAGER.load("naveminigame/asteroide.png", Texture.class);
                        MANAGER.load("naveminigame/asteroide0.png", Texture.class);
                        MANAGER.load("naveminigame/asteroide1.png", Texture.class);
                        MANAGER.load("naveminigame/asteroide2.png", Texture.class);
                        MANAGER.load("naveminigame/asteroide3.png", Texture.class);
                        MANAGER.load("naveminigame/asteroide4.png", Texture.class);
                        MANAGER.load("naveminigame/asteroide5.png", Texture.class);
                        MANAGER.load("naveminigame/asteroide6.png", Texture.class);
                        MANAGER.load("naveminigame/asteroide7.png", Texture.class);
                        MANAGER.load("naveminigame/asteroide8.png", Texture.class);
                        MANAGER.load("naveminigame/asteroide9.png", Texture.class);
                        MANAGER.load("naveminigame/fondonave.png", Texture.class);
                        MANAGER.load("naveminigame/vidaText.png", Texture.class);
                        MANAGER.load("naveminigame/escudoText.png", Texture.class);
                        MANAGER.load("naveminigame/nave.png", Texture.class);
                        MANAGER.load("naveminigame/star.png", Texture.class);
                        MANAGER.load("naveminigame/starVacia.png", Texture.class);
                        MANAGER.load("naveminigame/suma.png", Texture.class);
                        MANAGER.load("naveminigame/vida.png", Texture.class);
                        MANAGER.load("naveminigame/vidaEscudo.png", Texture.class);
                        MANAGER.load("naveminigame/vidaVacia.png", Texture.class);
                        MANAGER.load("naveminigame/laser.png", Texture.class);
                        MANAGER.load("naveminigame/laserPeque.png", Texture.class);
                        MANAGER.load("naveminigame/older/cargando.png", Texture.class);
            			MANAGER.load("naveminigame/older/gameover.png", Texture.class);
            			MANAGER.load("naveminigame/0.png", Texture.class);
            			MANAGER.load("naveminigame/1.png", Texture.class);
            			MANAGER.load("naveminigame/2.png", Texture.class);
            			MANAGER.load("naveminigame/3.png", Texture.class);
            			MANAGER.load("naveminigame/4.png", Texture.class);
            			MANAGER.load("naveminigame/5.png", Texture.class);
            			MANAGER.load("naveminigame/6.png", Texture.class);
            			MANAGER.load("naveminigame/7.png", Texture.class);
            			MANAGER.load("naveminigame/8.png", Texture.class);
            			MANAGER.load("naveminigame/9.png", Texture.class);
            			MANAGER.load("naveminigame/explosion0.png", Texture.class);
            			MANAGER.load("naveminigame/explosion1.png", Texture.class);
            			MANAGER.load("naveminigame/explosion2.png", Texture.class);
            			MANAGER.load("naveminigame/explosion3.png", Texture.class);
            			MANAGER.load("naveminigame/explosion4.png", Texture.class);
            			MANAGER.load("naveminigame/explosion5.png", Texture.class);
            			MANAGER.load("naveminigame/explosion6.png", Texture.class);
            			MANAGER.load("naveminigame/explosion7.png", Texture.class);
            			MANAGER.load("naveminigame/explosion8.png", Texture.class);
            			MANAGER.load("naveminigame/explosion9.png", Texture.class);
            			MANAGER.load("naveminigame/explosion10.png", Texture.class);
            			MANAGER.load("naveminigame/explosion11.png", Texture.class);
            			MANAGER.load("naveminigame/explosion12.png", Texture.class);
            			MANAGER.load("naveminigame/explosion13.png", Texture.class);
            			MANAGER.load("naveminigame/explosion14.png", Texture.class);
            			MANAGER.load("naveminigame/explosion15.png", Texture.class);
            			MANAGER.load("naveminigame/explosion16.png", Texture.class);
            			MANAGER.load("naveminigame/explosion17.png", Texture.class);
            			MANAGER.load("naveminigame/explosion18.png", Texture.class);
            			MANAGER.load("naveminigame/explosion19.png", Texture.class);
            			MANAGER.load("naveminigame/explosion20.png", Texture.class);
            			MANAGER.load("naveminigame/explosion21.png", Texture.class);
            			MANAGER.load("naveminigame/explosion22.png", Texture.class);
            			MANAGER.load("naveminigame/explosion23.png", Texture.class);
            			MANAGER.load("naveminigame/explosionMal0.png", Texture.class);
            			MANAGER.load("naveminigame/explosionMal1.png", Texture.class);
            			MANAGER.load("naveminigame/explosionMal2.png", Texture.class);
            			MANAGER.load("naveminigame/explosionMal3.png", Texture.class);
            			MANAGER.load("naveminigame/explosionMal4.png", Texture.class);
            			MANAGER.load("naveminigame/explosionMal5.png", Texture.class);
            			MANAGER.load("naveminigame/explosionMal6.png", Texture.class);
            			MANAGER.load("naveminigame/explosionMal7.png", Texture.class);
            			MANAGER.load("naveminigame/explosionMal8.png", Texture.class);
            			MANAGER.load("naveminigame/explosionMal9.png", Texture.class);
            			MANAGER.load("naveminigame/explosionMal10.png", Texture.class);
            			MANAGER.load("naveminigame/explosionMal11.png", Texture.class);
            			MANAGER.load("naveminigame/explosionMal12.png", Texture.class);
            			MANAGER.load("naveminigame/explosionMal13.png", Texture.class);
            			MANAGER.load("naveminigame/explosionMal14.png", Texture.class);
            			MANAGER.load("naveminigame/explosionMal15.png", Texture.class);
            			MANAGER.load("naveminigame/explosionMal16.png", Texture.class);
            			MANAGER.load("naveminigame/explosionMal17.png", Texture.class);
            			MANAGER.load("naveminigame/explosionMal18.png", Texture.class);
            			MANAGER.load("naveminigame/explosionMal19.png", Texture.class);
            			MANAGER.load("naveminigame/explosionMal20.png", Texture.class);
            			MANAGER.load("naveminigame/explosionMal21.png", Texture.class);
            			MANAGER.load("naveminigame/explosionMal22.png", Texture.class);
            			MANAGER.load("naveminigame/explosionMal23.png", Texture.class);
            			MANAGER.load("naveminigame/older/hit.ogg", Sound.class);
            			MANAGER.load("naveminigame/older/explosion.ogg", Sound.class);
            			MANAGER.load("naveminigame/older/shoot.ogg", Sound.class);
            			MANAGER.finishLoading();
            			Gdx.app.log(fibooGame.LOG, "Imagenes cargadas en el manager");
                        
                        //Cargamos ficheros de datos guardados
                        savedData = Gdx.files.local("savedData.json");
                        comps = Gdx.files.local("comps.json");
                        FileHandle firstLoad = Gdx.files.internal("compsdefault.json");
                        Json json = new Json(); 
                        
                        System.out.println(json.prettyPrint(complementos));
                        
                        if(savedData.exists()) {
                                personaje = json.fromJson(Personaje.class, savedData);
                                //Cargamos el TreeMap de Avatar
                                personaje.getAvatar().loadData();
                                System.out.println(personaje.toString());
                        }
                        else {
                                //Partida nueva
                                personaje = new Personaje();
                        }
                        
                        if(comps.exists()) {
                                //Todos los complementos habidos y por haber
                                complementos = json.fromJson(ArrayList.class, comps);
                        }
                        else {
                        	//La primera vez lee de internal y lo guarda en local
                        	complementos = json.fromJson(ArrayList.class, firstLoad);
                        }
                        
                        fpsLogger = new FPSLogger();
                }
                
                @Override
                public void setScreen(Screen screen) {
                        super.setScreen(screen);
                        
                        Gdx.app.log(fibooGame.LOG, "'Setting screen' to " + screen.getClass().getSimpleName());
                }

                @Override
                public void dispose() {
                        //Preparamos el array de avatar para guardarlo
                        personaje.getAvatar().formatToSave();
                        Json json = new Json();
                        
                        //Se guardan los datos
                        savedData.writeString(json.prettyPrint(personaje), false);
                        comps.writeString(json.prettyPrint(complementos), false);
                        
                        super.dispose();
                        
            			MANAGER.dispose();
                        
                        Gdx.app.log(fibooGame.LOG, "'Disposing' game");
                }

                @Override
                public void render() {
                        super.render();
                        
                        if( DEV_MODE ) fpsLogger.log();
                }

                @Override
                public void resize(int width, int height) {
                        super.resize(width, height);
                        Gdx.app.log(fibooGame.LOG, "'Resizing' to: " + width + " x " + height);

                        // show the splash screen when the game is resized for the first time
                        if (getScreen() == null) {
                                setScreen(getMainScreen());
                        }
                }

                @Override
                public void pause() {
                        super.pause();
                        
                        Gdx.app.log(fibooGame.LOG, "'Pausing' game");
                }

                @Override
                public void resume() {
                        super.resume();
                        
                        Gdx.app.log(fibooGame.LOG, "'Resuming' game");
                }
                
                public static Personaje getPersonaje() {
                        return personaje;
                }

                public static ArrayList<Complemento> getComplementos() {
                        return complementos;
                }
}