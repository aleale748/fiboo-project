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
                        Gdx.app.log(fibooGame.LOG, "Creating game");
                        
                        //Cargamos las imágenes en el manager
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
            			MANAGER.load("naveminigame/older/title.png", Texture.class);
            			MANAGER.load("naveminigame/older/jugar.png", Texture.class);
            			MANAGER.load("naveminigame/older/salir.png", Texture.class);
            			MANAGER.load("naveminigame/older/alien.gif", Texture.class);
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
            			MANAGER.load("naveminigame/older/0.gif", Texture.class);
            			MANAGER.load("naveminigame/older/1.gif", Texture.class);
            			MANAGER.load("naveminigame/older/2.gif", Texture.class);
            			MANAGER.load("naveminigame/older/3.gif", Texture.class);
            			MANAGER.load("naveminigame/older/4.gif", Texture.class);
            			MANAGER.load("naveminigame/older/5.gif", Texture.class);
            			MANAGER.load("naveminigame/older/6.gif", Texture.class);
            			MANAGER.load("naveminigame/older/7.gif", Texture.class);
            			MANAGER.load("naveminigame/older/8.gif", Texture.class);
            			MANAGER.load("naveminigame/older/9.gif", Texture.class);
            			MANAGER.load("naveminigame/older/vida.png", Texture.class);
            			MANAGER.load("naveminigame/older/bala.png", Texture.class);
            			MANAGER.load("naveminigame/older/cohete.png", Texture.class);
            			MANAGER.load("naveminigame/older/defensa.png", Texture.class);
            			MANAGER.load("naveminigame/older/fondoalien.png", Texture.class);
            			MANAGER.load("naveminigame/older/pad.png", Texture.class);
            			MANAGER.load("naveminigame/older/hit.ogg", Sound.class);
            			MANAGER.load("naveminigame/older/explosion.ogg", Sound.class);
            			MANAGER.load("naveminigame/older/shoot.ogg", Sound.class);
            			MANAGER.finishLoading();
            			Gdx.app.log(fibooGame.LOG, "Imagenes cargadas en el manager");
                        
                        //Cargamos ficheros de datos guardados
                        savedData = Gdx.files.local("savedData.json");
                        comps = Gdx.files.internal("comps.json");
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