package es.uca.fiboo;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class FibooAssetManager extends AssetManager {
	
	//Botones y pantallas de menús
	public void loadMenuTextures() {
		load("robotgame/numerosrojos.png", Texture.class);
		load("robotgame/numerosverdes.png", Texture.class);
		load("marcianosminigame/pantallaayudamarcianos.png", Texture.class);
		load("sacominigame/pantallainiciobolsa.png", Texture.class);
		load("pianogame/pantallainiciopiano.png", Texture.class);
		load("naveminigame/ayuda.png", Texture.class);
		load("sonidos/creditos.ogg", Music.class);
		load("robotgame/pantallainiciorobot.png", Texture.class);
		load("robotgame/fondoestrellas.png", Texture.class);
		load("portada/atrasboton.png", Texture.class);
		load("portada/atrasbotonpeque.png", Texture.class);
		load("sonidos/personalizacion.ogg", Music.class);
		load("portada/bolsaboton.png", Texture.class);
		load("portada/botonentrenamiento.png", Texture.class);
		load("portada/botonpersonalizar.png", Texture.class);
		load("portada/naveboton.png", Texture.class);
		load("portada/pianoboton.png", Texture.class);
		load("portada/marcianoboton.png", Texture.class);
		load("portada/robotboton.png", Texture.class);
		load("portada/polaroidnino2.png", Texture.class);
		load("portada/polaroidnina2.png", Texture.class);
		load("portada/pantallamenuprincipal.png", Texture.class);
		load("portada/pantallamenuentrenamiento.png", Texture.class);
		load("portada/fondopersonalizar.png", Texture.class);
		load("sonidos/fondo.ogg", Music.class);
		load("sonidos/ayuda.ogg", Music.class);
		load("portada/win.png", Texture.class);
		load("portada/gameover.png", Texture.class);
		load("portada/info.png", Texture.class);
		load("portada/creditos.png", Texture.class);
		load("portada/base.png", Texture.class);
	}
	
	public void loadSonidos() {
		//load("sonidos/ayuda.mp3", Sound.class);
	}
	
	//Texturas de pantalla de Personalizaci���n
	public void loadPersonalizacionTextures() {
		//Fondos
		load("complementos/habitacionverde.png", Texture.class);
		load("complementos/habitacionmorada.png", Texture.class);
		load("complementos/habitacionceleste.png", Texture.class);
		load("complementos/habitacionnaranja.png", Texture.class);
		load("complementos/habitacionrosa.png", Texture.class);
		
		load("complementos/base.png", Texture.class);
		load("complementos/adpelo1.png", Texture.class);
		load("complementos/adpelo1Icon.png", Texture.class);
		load("complementos/adpelo2.png", Texture.class);
		load("complementos/adpelo2Icon.png", Texture.class);
		load("complementos/adpelo3.png", Texture.class);
		load("complementos/adpelo3Icon.png", Texture.class);
		
		load("complementos/bigote1.png", Texture.class);
		load("complementos/bigote1Icon.png", Texture.class);
		load("complementos/bigote2.png", Texture.class);
		load("complementos/bigote2Icon.png", Texture.class);
		load("complementos/bigote3.png", Texture.class);
		load("complementos/bigote3Icon.png", Texture.class);
		
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
		load("complementos/gafas3.png", Texture.class);
		load("complementos/gafas3Icon.png", Texture.class);
		
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
		
		load("complementos/pantalon1.png", Texture.class);
		load("complementos/pantalon1Icon.png", Texture.class);
		load("complementos/pantalon2.png", Texture.class);
		load("complementos/pantalon2Icon.png", Texture.class);
		load("complementos/pantalon3.png", Texture.class);
		load("complementos/pantalon3Icon.png", Texture.class);
		load("complementos/pantalon4.png", Texture.class);
		load("complementos/pantalon4Icon.png", Texture.class);
		
		load("complementos/camiseta1.png", Texture.class);
		load("complementos/camiseta1Icon.png", Texture.class);
		load("complementos/camiseta2.png", Texture.class);
		load("complementos/camiseta2Icon.png", Texture.class);
		load("complementos/camiseta3.png", Texture.class);
		load("complementos/camiseta3Icon.png", Texture.class);
		load("complementos/camiseta4.png", Texture.class);
		load("complementos/camiseta4Icon.png", Texture.class);
		load("complementos/camiseta5.png", Texture.class);
		load("complementos/camiseta5Icon.png", Texture.class);
		
		load("complementos/vacio.png", Texture.class);

		//Iconos
		load("complementos/iconos/accpelo.png", Texture.class);
		load("complementos/iconos/bigote.png", Texture.class);
		load("complementos/iconos/boca.png", Texture.class);
		load("complementos/iconos/camiseta.png", Texture.class);
		load("complementos/iconos/disfraz.png", Texture.class);
		load("complementos/iconos/gafas.png", Texture.class);
		load("complementos/iconos/ojos.png", Texture.class);
		load("complementos/iconos/pantalon.png", Texture.class);
		load("complementos/iconos/pelo.png", Texture.class);
	}
	
	//Texturas y Sonidos de Nave minijuego
	public void loadNaveMiniGameTextures() {
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
		load("sonidos/nave.ogg", Music.class);
		load("naveminigame/older/hit.ogg", Sound.class);
		load("naveminigame/older/explosion.ogg", Sound.class);
		load("naveminigame/older/shoot.ogg", Sound.class);
	}
	
	public void unloadNaveMiniGameSounds() {
		unload("sonidos/nave.ogg");
		unload("naveminigame/older/hit.ogg");
		unload("naveminigame/older/explosion.ogg");
		unload("naveminigame/older/shoot.ogg");
	}
	
	//Texturas y Sonidos de Marcianos minijuego
	public void loadMarcianosMiniGameTextures() {
		load("sonidos/marcianos.ogg", Music.class);
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
		unload("sonidos/marcianos.ogg");
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

	//Texturas y Sonidos de Robot minijuego
	public void loadRobotMiniGameTextures() {
		load("sonidos/robot.ogg", Music.class);
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
		unload("sonidos/robot.ogg");
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
		load("robotgame/bien.ogg", Sound.class);
        load("robotgame/mal.ogg", Sound.class);
        load("robotgame/regu.ogg", Sound.class);
	}
	
	public void unloadRobotMiniGameSounds() {
		unload("robotgame/bien.ogg");
        unload("robotgame/mal.ogg");
        unload("robotgame/regu.ogg");
	}

	//Texturas y Sonidos de Piano minijuego
	public void loadPianoMiniGameTextures() {
		load("pianogame/partitura.png", Texture.class);
		load("pianogame/logotrompeta.png", Texture.class);
		load("pianogame/logopiano.png", Texture.class);
		load("pianogame/piano.png", Texture.class);
		load("pianogame/play.png", Texture.class);
		load("pianogame/stop.png", Texture.class);
		load("pianogame/pause.png", Texture.class);
		load("pianogame/mute.png", Texture.class);
		load("pianogame/sonido.png", Texture.class);
		load("pianogame/teclas.png", Texture.class);
	}
	
	public void loadPianoMiniGameSounds() {
        load("pianogame/do.ogg", Sound.class);
        load("pianogame/re.ogg", Sound.class);
        load("pianogame/mi.ogg", Sound.class);
        load("pianogame/fa.ogg", Sound.class);
        load("pianogame/sol.ogg", Sound.class);
        load("pianogame/la.ogg", Sound.class);
        load("pianogame/si.ogg", Sound.class);
        load("pianogame/zdo.ogg", Sound.class);
        load("pianogame/dotrom.ogg", Sound.class);
        load("pianogame/retrom.ogg", Sound.class);
        load("pianogame/mitrom.ogg", Sound.class);
        load("pianogame/fatrom.ogg", Sound.class);
        load("pianogame/soltrom.ogg", Sound.class);
        load("pianogame/latrom.ogg", Sound.class);
        load("pianogame/sitrom.ogg", Sound.class);
        load("pianogame/zdotrom.ogg", Sound.class);
	}
	
	public void unloadPianoMiniGameSounds() {
        unload("pianogame/do.ogg");
        unload("pianogame/re.ogg");
        unload("pianogame/mi.ogg");
        unload("pianogame/fa.ogg");
        unload("pianogame/sol.ogg");
        unload("pianogame/la.ogg");
        unload("pianogame/si.ogg");
        unload("pianogame/zdo.ogg");
        unload("pianogame/dotrom.ogg");
        unload("pianogame/retrom.ogg");
        unload("pianogame/mitrom.ogg");
        unload("pianogame/fatrom.ogg");
        unload("pianogame/soltrom.ogg");
        unload("pianogame/latrom.ogg");
        unload("pianogame/sitrom.ogg");
        unload("pianogame/zdotrom.ogg");
	}
	
	//Texturas de Saco minigame
	public void loadSacoMiniGameTextures() {
		load("sacominigame/robotalegre.png", Texture.class);
		load("sacominigame/robottriste.png", Texture.class);
		load("sacominigame/robottallerneutro.png", Texture.class);
		load("sacominigame/tornillo2.png", Texture.class);
		load("sacominigame/tornillo2azul.png", Texture.class);
		load("sacominigame/tornillo2rojo.png", Texture.class);
		load("sacominigame/tornillo6.png", Texture.class);
		load("sacominigame/tornillo6azul.png", Texture.class);
		load("sacominigame/tornillo6rojo.png", Texture.class);
		load("sacominigame/tallerboton.png", Texture.class);
		load("sacominigame/circuitoazul.png", Texture.class);
		load("sacominigame/circuitorojo.png", Texture.class);
		load("sacominigame/circuitogris.png", Texture.class);
		load("sacominigame/0.png", Texture.class);
		load("sacominigame/1.png", Texture.class);
		load("sacominigame/2.png", Texture.class);
		load("sacominigame/3.png", Texture.class);
		load("sacominigame/4.png", Texture.class);
		load("sacominigame/5.png", Texture.class);
		load("sacominigame/6.png", Texture.class);
		load("sacominigame/7.png", Texture.class);
		load("sacominigame/8.png", Texture.class);
		load("sacominigame/9.png", Texture.class);
	}
	
	public void loadSacoMiniGameSounds() {
		load("sonidos/taller.ogg", Music.class);
		load("sonidos/bien.ogg", Sound.class);
		load("sonidos/nono.ogg", Sound.class);
		load("sacominigame/robot.ogg", Sound.class);
		
	}
	
	public void unloadSacoMiniGameSounds() {
		unload("sonidos/taller.ogg");
		unload("sonidos/bien.ogg");
		unload("sonidos/nono.ogg");
		unload("sacominigame/robot.ogg");
	}

}
