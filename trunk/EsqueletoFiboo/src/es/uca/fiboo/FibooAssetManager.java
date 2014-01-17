package es.uca.fiboo;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class FibooAssetManager extends AssetManager {
	
	//Botones y pantallas de men�s
	public void loadMenuTextures() {
		load("marcianosminigame/win.png", Texture.class);
		load("marcianosminigame/gameover.png", Texture.class);
		load("robotgame/numerosrojos.png", Texture.class);
		load("robotgame/numerosverdes.png", Texture.class);
		load("sacominigame/pantallainiciobolsa.png", Texture.class);
		load("pianogame/pantallainiciopiano.png", Texture.class);
		load("naveminigame/ayuda.png", Texture.class);
		load("robotgame/pantallainiciorobot.png", Texture.class);
		load("robotgame/fondoestrellas.png", Texture.class);
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
		load("portada/fondopersonalizar.png", Texture.class);
	}
	
	public void loadMusicaFondo() {
		load("sonidos/fondo.mp3", Sound.class);
	}
	
	public void loadSonidos() {
		load("sonidos/ayuda.mp3", Sound.class);
	}
	
	//Texturas de pantalla de Personalizaci�n
	public void loadPersonalizacionTextures() {
		//load("complementos/complementos.atlas", TextureAtlas.class);
		
		//load("portada/fondopersonalizar.png", Texture.class);		
		load("complementos/base.png", Texture.class);
		load("complementos/adpelo1.png", Texture.class);
		load("complementos/adpelo1Icon.png", Texture.class);
		load("complementos/adpelo2.png", Texture.class);
		load("complementos/adpelo2Icon.png", Texture.class);
		load("complementos/bigote1.png", Texture.class);
		load("complementos/bigote1Icon.png", Texture.class);
		load("complementos/bigote2.png", Texture.class);
		load("complementos/bigote2Icon.png", Texture.class);
		load("complementos/boca1.png", Texture.class);
		load("complementos/boca1Icon.png", Texture.class);
		load("complementos/boca2.png", Texture.class);
		load("complementos/boca2Icon.png", Texture.class);
		load("complementos/boca3.png", Texture.class);
		load("complementos/boca3Icon.png", Texture.class);
		load("complementos/disfraz1.png", Texture.class);
		load("complementos/disfraz1Icon.png", Texture.class);
		load("complementos/disfraz2.png", Texture.class);
		load("complementos/disfraz2Icon.png", Texture.class);
		load("complementos/gafas1.png", Texture.class);
		load("complementos/gafas1Icon.png", Texture.class);
		load("complementos/gafas2.png", Texture.class);
		load("complementos/gafas2Icon.png", Texture.class);
		load("complementos/ojos1.png", Texture.class);
		load("complementos/ojos1Icon.png", Texture.class);
		load("complementos/ojos2.png", Texture.class);
		load("complementos/ojos2Icon.png", Texture.class);
		load("complementos/ojos3.png", Texture.class);
		load("complementos/ojos3Icon.png", Texture.class);
		load("complementos/pelonina1.png", Texture.class);
		load("complementos/pelonina1Icon.png", Texture.class);
		load("complementos/pelonina2.png", Texture.class);
		load("complementos/pelonina2Icon.png", Texture.class);
		load("complementos/pelonina3.png", Texture.class);
		load("complementos/pelonina3Icon.png", Texture.class);
		load("complementos/pelonino1.png", Texture.class);
		load("complementos/pelonino1Icon.png", Texture.class);
		load("complementos/pelonino2.png", Texture.class);
		load("complementos/pelonino2Icon.png", Texture.class);
		load("complementos/pelonino3.png", Texture.class);
		load("complementos/pelonino3Icon.png", Texture.class);
		load("complementos/vacio.png", Texture.class);

		//Iconos
		load("complementos/iconos/accpelo.png", Texture.class);
		load("complementos/iconos/bigote.png", Texture.class);
		load("complementos/iconos/boca.png", Texture.class);
		load("complementos/iconos/camisa.png", Texture.class);
		load("complementos/iconos/disfraz.png", Texture.class);
		load("complementos/iconos/gafas.png", Texture.class);
		load("complementos/iconos/ojos.png", Texture.class);
		load("complementos/iconos/pantalon.png", Texture.class);
		load("complementos/iconos/pelo.png", Texture.class);
	}
	
	//Texturas y Sonidos de Nave minijuego
	public void loadNaveMiniGameTextures() {
		load("naveminigame/win.png", Texture.class);
        load("naveminigame/disparar2.png", Texture.class);
        load("naveminigame/palitos0.png", Texture.class);
        load("naveminigame/palitos1.png", Texture.class);
        load("naveminigame/palitos2.png", Texture.class);
        load("naveminigame/palitos3.png", Texture.class);
        load("naveminigame/palitos4.png", Texture.class);
        load("naveminigame/palitos5.png", Texture.class);
        load("naveminigame/palitos6.png", Texture.class);
        load("naveminigame/palitos7.png", Texture.class);
        load("naveminigame/palitos8.png", Texture.class);
        load("naveminigame/palitos9.png", Texture.class);
        load("naveminigame/asteroide0.png", Texture.class);
        load("naveminigame/asteroide1.png", Texture.class);
        load("naveminigame/asteroide2.png", Texture.class);
        load("naveminigame/asteroide3.png", Texture.class);
        load("naveminigame/asteroide4.png", Texture.class);
        load("naveminigame/asteroide5.png", Texture.class);
        load("naveminigame/asteroide6.png", Texture.class);
        load("naveminigame/asteroide7.png", Texture.class);
        load("naveminigame/asteroide8.png", Texture.class);
        load("naveminigame/asteroide9.png", Texture.class);
        load("naveminigame/nave.png", Texture.class);
        load("naveminigame/star.png", Texture.class);
        load("naveminigame/starVacia.png", Texture.class);
        load("naveminigame/vida.png", Texture.class);
        load("naveminigame/vidaEscudo.png", Texture.class);
        load("naveminigame/vidaVacia.png", Texture.class);
        load("naveminigame/laserPeque.png", Texture.class);
		load("naveminigame/gameover.png", Texture.class);
		load("naveminigame/explosion0.png", Texture.class);
		load("naveminigame/explosion1.png", Texture.class);
		load("naveminigame/explosion2.png", Texture.class);
		load("naveminigame/explosion3.png", Texture.class);
		load("naveminigame/explosion4.png", Texture.class);
		load("naveminigame/explosion5.png", Texture.class);
		load("naveminigame/explosion6.png", Texture.class);
		load("naveminigame/explosion7.png", Texture.class);
		load("naveminigame/explosion8.png", Texture.class);
		load("naveminigame/explosion9.png", Texture.class);
		load("naveminigame/explosion10.png", Texture.class);
		load("naveminigame/explosion11.png", Texture.class);
		load("naveminigame/explosion12.png", Texture.class);
		load("naveminigame/explosion13.png", Texture.class);
		load("naveminigame/explosion14.png", Texture.class);
		load("naveminigame/explosion15.png", Texture.class);
		load("naveminigame/explosion16.png", Texture.class);
		load("naveminigame/explosion17.png", Texture.class);
		load("naveminigame/explosion18.png", Texture.class);
		load("naveminigame/explosion19.png", Texture.class);
		load("naveminigame/explosion20.png", Texture.class);
		load("naveminigame/explosion21.png", Texture.class);
		load("naveminigame/explosion22.png", Texture.class);
		load("naveminigame/explosion23.png", Texture.class);
		load("naveminigame/explosionMal0.png", Texture.class);
		load("naveminigame/explosionMal1.png", Texture.class);
		load("naveminigame/explosionMal2.png", Texture.class);
		load("naveminigame/explosionMal3.png", Texture.class);
		load("naveminigame/explosionMal4.png", Texture.class);
		load("naveminigame/explosionMal5.png", Texture.class);
		load("naveminigame/explosionMal6.png", Texture.class);
		load("naveminigame/explosionMal7.png", Texture.class);
		load("naveminigame/explosionMal8.png", Texture.class);
		load("naveminigame/explosionMal9.png", Texture.class);
		load("naveminigame/explosionMal10.png", Texture.class);
		load("naveminigame/explosionMal11.png", Texture.class);
		load("naveminigame/explosionMal12.png", Texture.class);
		load("naveminigame/explosionMal13.png", Texture.class);
		load("naveminigame/explosionMal14.png", Texture.class);
		load("naveminigame/explosionMal15.png", Texture.class);
		load("naveminigame/explosionMal16.png", Texture.class);
		load("naveminigame/explosionMal17.png", Texture.class);
		load("naveminigame/explosionMal18.png", Texture.class);
		load("naveminigame/explosionMal19.png", Texture.class);
		load("naveminigame/explosionMal20.png", Texture.class);
		load("naveminigame/explosionMal21.png", Texture.class);
		load("naveminigame/explosionMal22.png", Texture.class);
		load("naveminigame/explosionMal23.png", Texture.class);
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
		load("marcianosminigame/star.png", Texture.class);
		load("marcianosminigame/starVacia.png", Texture.class);
		load("marcianosminigame/0.png", Texture.class);
		load("marcianosminigame/1.png", Texture.class);
		load("marcianosminigame/2.png", Texture.class);
		load("marcianosminigame/3.png", Texture.class);
		load("marcianosminigame/4.png", Texture.class);
		load("marcianosminigame/5.png", Texture.class);
		load("marcianosminigame/6.png", Texture.class);
		load("marcianosminigame/7.png", Texture.class);
		load("marcianosminigame/8.png", Texture.class);
		load("marcianosminigame/9.png", Texture.class);
		load("marcianosminigame/bien.png", Texture.class);
		load("marcianosminigame/pregunta.png", Texture.class);
		load("marcianosminigame/naveMarciano.png", Texture.class);
		load("marcianosminigame/nave.png", Texture.class);
		load("marcianosminigame/marciano.png", Texture.class);
		load("marcianosminigame/boton.png", Texture.class);
	}
	
	public void unloadMarcianosMiniGameTextures() {
		unload("marcianosminigame/star.png");
		unload("marcianosminigame/starVacia.png");
		unload("marcianosminigame/0.png");
		unload("marcianosminigame/1.png");
		unload("marcianosminigame/2.png");
		unload("marcianosminigame/3.png");
		unload("marcianosminigame/4.png");
		unload("marcianosminigame/5.png");
		unload("marcianosminigame/6.png");
		unload("marcianosminigame/7.png");
		unload("marcianosminigame/8.png");
		unload("marcianosminigame/9.png");
		unload("marcianosminigame/bien.png");
		unload("marcianosminigame/pregunta.png");
		unload("marcianosminigame/naveMarciano.png");
		unload("marcianosminigame/nave.png");
		unload("marcianosminigame/marciano.png");
		unload("marcianosminigame/boton.png");
	}
	
	public void loadMarcianosMiniGameSounds() {
		load("sonidos/guala.ogg", Sound.class);
		load("sonidos/bien.ogg", Sound.class);
		load("sonidos/cuantosquedan.ogg", Sound.class);
		load("sonidos/yuju.ogg", Sound.class);
	}
	
	public void unloadMarcianosMiniGameSounds() {
		unload("sonidos/guala.ogg");
		unload("sonidos/bien.ogg");
		unload("sonidos/cuantosquedan.ogg");
		unload("sonidos/yuju.ogg");
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
		load("robotgame/estrella_.png", Texture.class);
		load("robotgame/luna_.png", Texture.class);
		load("robotgame/marciano_.png", Texture.class);
		load("robotgame/planeta_.png", Texture.class);
		load("robotgame/robotfrente.png", Texture.class);
		load("robotgame/robotderecha.png", Texture.class);
		load("robotgame/robotizquierda.png", Texture.class);
		load("robotgame/pantallainiciorobot.png", Texture.class);
	}
	
	public void unloadRobotMiniGameTextures() {
		unload("robotgame/estrella_.png");
		unload("robotgame/luna_.png");
		unload("robotgame/marciano_.png");
		unload("robotgame/planeta_.png");
		unload("robotgame/robotfrente.png");
		unload("robotgame/robotderecha.png");
		unload("robotgame/robotizquierda.png");
		unload("robotgame/pantallainiciorobot.png");
	}
	
	public void loadRobotMiniGameSounds() {
		load("sonidos/robot.mp3", Sound.class);
		load("robotgame/bien.ogg", Sound.class);
        load("robotgame/mal.ogg", Sound.class);
        load("robotgame/regu.ogg", Sound.class);
	}
	
	public void unloadRobotMiniGameSounds() {
		unload("sonidos/robot.mp3");
		unload("robotgame/bien.ogg");
        unload("robotgame/mal.ogg");
        unload("robotgame/regu.ogg");
	}

	//Texturas y Sonidos de Piano minijuego
	public void loadPianoMiniGameTextures() {
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
