package es.uca.fiboo;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class FibooAssetManager extends AssetManager {
	
	//Botones y pantallas de menús
	public void loadMenuTextures() {
		load("portada/atrasboton.png", Texture.class);
		load("portada/atrasbotonpeque.png", Texture.class);
		load("portada/bolsaboton.png", Texture.class);
		load("portada/botonentrenamiento.png", Texture.class);
		load("portada/botonpersonalizar.png", Texture.class);
		load("portada/mapaboton.png", Texture.class);
		load("portada/naveboton.png", Texture.class);
		load("portada/pianoboton.png", Texture.class);
		load("portada/ascensorboton.png", Texture.class);
		load("portada/robotboton.png", Texture.class);
		load("portada/polaroidnino2.png", Texture.class);
		load("portada/polaroidnina2.png", Texture.class);
		load("portada/pantallamenuprincipal.png", Texture.class);
		load("portada/pantallamenuentrenamiento.png", Texture.class);
		load("portada/logoasteroid.png", Texture.class);
		load("portada/playportada.png", Texture.class);
		load("portada/fondopersonalizar.png", Texture.class);
	}
	
	public void loadMusicaFondo() {
		load("sonidos/fondo.mp3", Sound.class);
	}
	
	public void loadSonidos() {
		load("sonidos/ayuda.mp3", Sound.class);
	}
	
	//Texturas de pantalla de Personalización
	public void loadPersonalizacionTextures() {
		load("portada/fondopersonalizar.png", Texture.class);
		load("complementos/complementos.atlas", TextureAtlas.class);
	}
	
	//Texturas y Sonidos de Nave minijuego
	public void loadNaveMiniGameTextures() {
		load("naveminigame/ayuda.png", Texture.class);
		load("naveminigame/atlasNaveMiniGame.atlas", TextureAtlas.class);
	}
	
	public void loadNaveMiniGameSounds() {
		load("naveminigame/older/hit.ogg", Sound.class);
		load("naveminigame/older/explosion.ogg", Sound.class);
		load("naveminigame/older/shoot.ogg", Sound.class);
		load("sonidos/naves.mp3", Sound.class);
	}
	
	public void unloadNaveMiniGameSounds() {
		unload("naveminigame/older/hit.ogg");
		unload("naveminigame/older/explosion.ogg");
		unload("naveminigame/older/shoot.ogg");
		unload("sonidos/naves.mp3");
	}
	
	//Texturas y Sonidos de Marcianos minijuego
	public void loadMarcianosMiniGameTextures() {
		load("marcianosminigame/imagenesMarcianosMiniGame.txt", TextureAtlas.class);
	}
	
	public void loadMarcianosMiniGameSounds() {
		load("sonidos/guala.mp3", Sound.class);
		load("sonidos/bien.mp3", Sound.class);
		load("sonidos/cuantosquedan.mp3", Sound.class);
		load("sonidos/fondo.mp3", Sound.class);
		load("sonidos/yuju.mp3", Sound.class);
	}
	
	public void unloadMarcianosMiniGameSounds() {
		unload("sonidos/guala.mp3");
		unload("sonidos/bien.mp3");
		unload("sonidos/cuantosquedan.mp3");
		unload("sonidos/fondo.mp3");
		unload("sonidos/yuju.mp3");
	}
	
	//Texturas de Camara mini juego
	public void loadCameraMiniGameTextures() {
		//load("cameraminigame/cesped2.png", Texture.class);
		load("cameraminigame/0.png", Texture.class);
		load("cameraminigame/1.png", Texture.class);
		load("cameraminigame/2.png", Texture.class);
		load("cameraminigame/3.png", Texture.class);
		load("cameraminigame/4.png", Texture.class);
		load("cameraminigame/5.png", Texture.class);
		load("cameraminigame/6.png", Texture.class);
		load("cameraminigame/7.png", Texture.class);
		load("cameraminigame/8.png", Texture.class);
		load("cameraminigame/9.png", Texture.class);
		load("cameraminigame/menu0.png", Texture.class);
		load("cameraminigame/menu1.png", Texture.class);
		load("cameraminigame/menu2.png", Texture.class);
		load("cameraminigame/menu3.png", Texture.class);
		load("cameraminigame/menu4.png", Texture.class);
		load("cameraminigame/menu5.png", Texture.class);
		load("cameraminigame/menu6.png", Texture.class);
		load("cameraminigame/menu7.png", Texture.class);
		load("cameraminigame/menu8.png", Texture.class);
		load("cameraminigame/menu9.png", Texture.class);
	}

	//Texturas y Sonidos de Robot minijuego
	public void loadRobotMiniGameTextures() {
		load("robotgame/fondoestrellas.png", Texture.class);
		load("robotgame/estrella_.png", Texture.class);
		load("robotgame/luna_.png", Texture.class);
		load("robotgame/marciano_.png", Texture.class);
		load("robotgame/planeta_.png", Texture.class);
		load("robotgame/numerosrojos.png", Texture.class);
		load("robotgame/numerosverdes.png", Texture.class);
		load("robotgame/pantallainiciorobot.png", Texture.class);
		load("robotgame/robotfrente.png", Texture.class);
		load("robotgame/robotderecha.png", Texture.class);
		load("robotgame/robotizquierda.png", Texture.class);
		load("robotgame/pantallainiciorobot.png", Texture.class);
	}
	
	public void loadRobotMiniGameSounds() {
		load("sonidos/robot.mp3", Sound.class);
		load("robotgame/bien.mp3", Sound.class);
        load("robotgame/mal.mp3", Sound.class);
        load("robotgame/regu.mp3", Sound.class);
	}
	
	public void unloadRobotMiniGameSounds() {
		unload("sonidos/robot.mp3");
		unload("robotgame/bien.mp3");
        unload("robotgame/mal.mp3");
        unload("robotgame/regu.mp3");
	}

	//Texturas y Sonidos de Piano minijuego
	public void loadPianoMiniGameTextures() {
		load("pianogame/pantallainiciopiano.png", Texture.class);
		load("pianogame/logotrompeta.png", Texture.class);
		load("pianogame/logopiano.png", Texture.class);
		load("pianogame/piano.png", Texture.class);
		load("pianogame/base.png", Texture.class);
		load("pianogame/play.png", Texture.class);
		load("pianogame/stop.png", Texture.class);
		load("pianogame/pause.png", Texture.class);
		load("pianogame/mute.png", Texture.class);
		load("pianogame/sonido.png", Texture.class);
	}
	
	public void loadPianoMiniGameSounds() {
        load("pianogame/do.mp3", Sound.class);
        load("pianogame/re.mp3", Sound.class);
        load("pianogame/mi.mp3", Sound.class);
        load("pianogame/fa.mp3", Sound.class);
        load("pianogame/sol.mp3", Sound.class);
        load("pianogame/la.mp3", Sound.class);
        load("pianogame/si.mp3", Sound.class);
        load("pianogame/zdo.mp3", Sound.class);
        load("pianogame/dotrom.mp3", Sound.class);
        load("pianogame/retrom.mp3", Sound.class);
        load("pianogame/mitrom.mp3", Sound.class);
        load("pianogame/fatrom.mp3", Sound.class);
        load("pianogame/soltrom.mp3", Sound.class);
        load("pianogame/latrom.mp3", Sound.class);
        load("pianogame/sitrom.mp3", Sound.class);
        load("pianogame/zdotrom.mp3", Sound.class);
	}
	
	public void unloadPianoMiniGameSounds() {
        unload("pianogame/do.mp3");
        unload("pianogame/re.mp3");
        unload("pianogame/mi.mp3");
        unload("pianogame/fa.mp3");
        unload("pianogame/sol.mp3");
        unload("pianogame/la.mp3");
        unload("pianogame/si.mp3");
        unload("pianogame/zdo.mp3");
        unload("pianogame/dotrom.mp3");
        unload("pianogame/retrom.mp3");
        unload("pianogame/mitrom.mp3");
        unload("pianogame/fatrom.mp3");
        unload("pianogame/soltrom.mp3");
        unload("pianogame/latrom.mp3");
        unload("pianogame/sitrom.mp3");
        unload("pianogame/zdotrom.mp3");
	}
	
	//Texturas de Saco minigame
	public void loadSacoMiniGameTextures() {
		load("sacominigame/pantallainiciobolsa.png", Texture.class);
	}
	
	public void loadSacoMiniGameSounds() {
		load("sacominigame/clic.wav", Sound.class);
	}
	
	public void unloadSacoMiniGameSounds() {
		unload("sacominigame/clic.wav");
	}
	
	public void loadAscensorGameTextures() {
		load("ascensor/habitacionverde.png", Texture.class);
		load("ascensor/habitacionmorada.png", Texture.class);
		load("ascensor/habitacionceleste.png", Texture.class);
		load("ascensor/habitacionnaranja.png", Texture.class);
		load("ascensor/habitacionrosa.png", Texture.class);
	}
}
