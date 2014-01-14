package es.uca.fiboo;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class FibooAssetManager extends AssetManager {
	
	public void loadNaveminigameScreen() {
		load("naveminigame/ayuda.png", Texture.class);
		load("naveminigame/older/hit.ogg", Sound.class);
		load("naveminigame/older/explosion.ogg", Sound.class);
		load("naveminigame/older/shoot.ogg", Sound.class);
		load("naveminigame/atlasNaveMiniGame.png", Texture.class);
		load("naveminigame/atlasNaveMiniGame2.png", Texture.class);
		load("naveminiGame/atlasNaveMiniGame3.png", Texture.class);
	}
	
	public void loadCameraminigameScreen() {
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

	public void robotgameScreen() {
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
        load("robotgame/bien.mp3", Sound.class);
        load("robotgame/mal.mp3", Sound.class);
        load("robotgame/regu.mp3", Sound.class);
	}
	public void pianogameScreen() {
		load("pianogame/logotrompeta.png", Texture.class);
		load("pianogame/logopiano.png", Texture.class);
		load("pianogame/piano.png", Texture.class);
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
}
