package es.uca.fiboo;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.utils.Json;

import es.uca.fiboo.actors.Personaje;
import es.uca.fiboo.personalizar.actores.Complemento;
import es.uca.fiboo.screens.MainScreen;

public class FibooGame extends Game {

        public static final String VERSION = "0.0.0.01 Pre-Alpha";
        public static final String LOG = "fibooGame";
        public static final boolean DEV_MODE = false;
        
        public static final FibooAssetManager MANAGER = new FibooAssetManager();
        // public static TextureAtlas atlasComplementos, atlasNaveMiniGame, atlasMarcianosMiniGame;

        private static Personaje personaje;
        private static List<Complemento> complementos;
        private transient FileHandle savedData, comps;

        // Clase de ayuda de libgdx que logea los FPS cada segundo
        private transient FPSLogger fpsLogger;

        @SuppressWarnings("unchecked")
        @Override
        public void create() {

    		Gdx.input.setCatchBackKey(true);
    		            
            //Cargamos ficheros de datos guardados
            savedData = Gdx.files.local("savedData.json");
            comps = Gdx.files.local("comps.json");
            FileHandle firstLoad = Gdx.files.internal("compsdefault.json");
            Json json = new Json(); 
            
            if(savedData.exists()) {
                    personaje = json.fromJson(Personaje.class, savedData);
                    //Cargamos el TreeMap de Avatar
                    personaje.getAvatar().loadData();
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
            
            Gdx.app.log(FibooGame.LOG, "'Setting screen' to " + screen.getClass().getSimpleName());
        }

        @Override
        public void dispose() {
            //Preparamos el array de avatar para guardarlo
            personaje.getAvatar().formatToSave();
            Json json = new Json();
            
            //Se guardan los datos
            savedData.writeString(json.prettyPrint(personaje), false);
            comps.writeString(json.prettyPrint(complementos), false);
            
			MANAGER.dispose();
	
			super.dispose();
			System.exit(0);
            Gdx.app.log(FibooGame.LOG, "'Disposing' game");
        }

        @Override
        public void render() {
            super.render();
            
            if( DEV_MODE ) { fpsLogger.log(); }
        }

        @Override
        public void resize(final int width, final int height) {
            super.resize(width, height);
            Gdx.app.log(FibooGame.LOG, "'Resizing' to: " + width + " x " + height);

            if (getScreen() == null) {
                    setScreen(new MainScreen(this));
            }
        }

        @Override
        public void pause() {
            super.pause();
                
            Gdx.app.log(FibooGame.LOG, "'Pausing' game");
        }

        @Override
        public void resume() {
            super.resume();
              
            Gdx.app.log(FibooGame.LOG, "'Resuming' game");
        }
        
        public static Personaje getPersonaje() {
            return personaje;
        }

        public static List<Complemento> getComplementos() {
        	return complementos;
        }
}