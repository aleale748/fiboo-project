package es.uca.fiboo;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.utils.Json;

import es.uca.fiboo.actores.Complemento;
import es.uca.fiboo.actores.Personaje;
import es.uca.fiboo.screens.MainScreen;

public class fibooGame extends Game {

                public static final String VERSION = "0.0.0.01 Pre-Alpha";
                public static final String LOG = "fibooGame";
                public static final boolean DEV_MODE = false;

                private static Personaje personaje;
                private static ArrayList<Complemento> complementos;
                
                private AssetManager manager;
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

                public AssetManager getManager() {
                        return manager;
                }
}