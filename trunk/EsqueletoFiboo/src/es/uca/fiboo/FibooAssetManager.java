package es.uca.fiboo;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class FibooAssetManager extends AssetManager {
	
	public void loadPersonalizacionScreen() {
		load("data/complementos/base.png", Texture.class);
		load("data/complementos/adpelo1.png", Texture.class);
		load("data/complementos/adpelo1Icon.png", Texture.class);
		load("data/complementos/adpelo2.png", Texture.class);
		load("data/complementos/adpelo2Icon.png", Texture.class);
		load("data/complementos/bigote1.png", Texture.class);
		load("data/complementos/bigote1Icon.png", Texture.class);
		load("data/complementos/bigote2.png", Texture.class);
		load("data/complementos/bigote2Icon.png", Texture.class);
		load("data/complementos/boca1.png", Texture.class);
		load("data/complementos/boca1Icon.png", Texture.class);
		load("data/complementos/boca2.png", Texture.class);
		load("data/complementos/boca2Icon.png", Texture.class);
		load("data/complementos/boca3.png", Texture.class);
		load("data/complementos/boca3Icon.png", Texture.class);
		load("data/complementos/disfraz1.png", Texture.class);
		load("data/complementos/disfraz1Icon.png", Texture.class);
		load("data/complementos/disfraz2.png", Texture.class);
		load("data/complementos/disfraz2Icon.png", Texture.class);
		load("data/complementos/gafas1.png", Texture.class);
		load("data/complementos/gafas1Icon.png", Texture.class);
		load("data/complementos/gafas2.png", Texture.class);
		load("data/complementos/gafas2Icon.png", Texture.class);
		load("data/complementos/ojos1.png", Texture.class);
		load("data/complementos/ojos1Icon.png", Texture.class);
		load("data/complementos/ojos2.png", Texture.class);
		load("data/complementos/ojos2Icon.png", Texture.class);
		load("data/complementos/ojos3.png", Texture.class);
		load("data/complementos/ojos3Icon.png", Texture.class);
		load("data/complementos/pelonina1.png", Texture.class);
		load("data/complementos/pelonina1Icon.png", Texture.class);		
		load("data/complementos/pelonina2.png", Texture.class);
		load("data/complementos/pelonina2Icon.png", Texture.class);	
		load("data/complementos/pelonina3.png", Texture.class);
		load("data/complementos/pelonina3Icon.png", Texture.class);
		load("data/complementos/pelonino1.png", Texture.class);
		load("data/complementos/pelonino1Icon.png", Texture.class);		
		load("data/complementos/pelonino2.png", Texture.class);
		load("data/complementos/pelonino2Icon.png", Texture.class);	
		load("data/complementos/pelonino3.png", Texture.class);
		load("data/complementos/pelonino3Icon.png", Texture.class);
		load("data/complementos/vacio.png", Texture.class);
		
		load("iconos/accpelo.png", Texture.class);
		load("iconos/bigote.png", Texture.class);
		load("iconos/boca.png", Texture.class);
		load("iconos/camisa.png", Texture.class);
		load("iconos/disfraz.png", Texture.class);
		load("iconos/gafas.png", Texture.class);
		load("iconos/mascara.png", Texture.class);
		load("iconos/ojos.png", Texture.class);
		load("iconos/pantalon.png", Texture.class);
		load("iconos/pelo.png", Texture.class);
		load("iconos/zapatos.png", Texture.class);
		finishLoading();
	}
	
	public void loadNaveminigameScreen() {
        load("naveminigame/ayuda.png", Texture.class);
		load("naveminigame/older/win.png", Texture.class);
        load("naveminigame/disparar2.png", Texture.class);
        load("naveminigame/palitos1.png", Texture.class);
        load("naveminigame/palitos2.png", Texture.class);
        load("naveminigame/palitos3.png", Texture.class);
        load("naveminigame/palitos4.png", Texture.class);
        load("naveminigame/palitos5.png", Texture.class);
        load("naveminigame/palitos6.png", Texture.class);
        load("naveminigame/palitos7.png", Texture.class);
        load("naveminigame/palitos8.png", Texture.class);
        load("naveminigame/palitos9.png", Texture.class);
        //load("naveminigame/asteroideVacio.png", Texture.class);
        //load("naveminigame/asteroide.png", Texture.class);
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
        load("naveminigame/fondonave.png", Texture.class);
        load("naveminigame/vidaText.png", Texture.class);
        load("naveminigame/escudoText.png", Texture.class);
        load("naveminigame/nave.png", Texture.class);
        load("naveminigame/star.png", Texture.class);
        load("naveminigame/starVacia.png", Texture.class);
        load("naveminigame/suma.png", Texture.class);
        load("naveminigame/vida.png", Texture.class);
        load("naveminigame/vidaEscudo.png", Texture.class);
        load("naveminigame/vidaVacia.png", Texture.class);
        load("naveminigame/laser.png", Texture.class);
        load("naveminigame/laserPeque.png", Texture.class);
        load("naveminigame/older/cargando.png", Texture.class);
		load("naveminigame/older/gameover.png", Texture.class);
		load("naveminigame/0.png", Texture.class);
		load("naveminigame/1.png", Texture.class);
		load("naveminigame/2.png", Texture.class);
		load("naveminigame/3.png", Texture.class);
		load("naveminigame/4.png", Texture.class);
		load("naveminigame/5.png", Texture.class);
		load("naveminigame/6.png", Texture.class);
		load("naveminigame/7.png", Texture.class);
		load("naveminigame/8.png", Texture.class);
		load("naveminigame/9.png", Texture.class);
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
		load("naveminigame/older/hit.ogg", Sound.class);
		load("naveminigame/older/explosion.ogg", Sound.class);
		load("naveminigame/older/shoot.ogg", Sound.class);
		finishLoading();
	}
	
	public void loadMarcianosminigameScreen() {
		load("naveminigame/older/win.png", Texture.class);
		load("naveminigame/older/gameover.png", Texture.class);
		load("naveminigame/0.png", Texture.class);
		load("naveminigame/1.png", Texture.class);
		load("naveminigame/2.png", Texture.class);
		load("naveminigame/3.png", Texture.class);
		load("naveminigame/4.png", Texture.class);
		load("naveminigame/5.png", Texture.class);
		load("naveminigame/6.png", Texture.class);
		load("naveminigame/7.png", Texture.class);
		load("naveminigame/8.png", Texture.class);
		load("naveminigame/9.png", Texture.class);
        load("naveminigame/star.png", Texture.class);
        load("naveminigame/starVacia.png", Texture.class);
        load("naveminigame/fondonave.png", Texture.class);
		load("marcianosminigame/boton.png", Texture.class);
		load("marcianosminigame/pregunta.png", Texture.class);
		load("marcianosminigame/bien.png", Texture.class);
        load("marcianosminigame/nave.png", Texture.class);
        load("marcianosminigame/marciano.png", Texture.class);
        load("robotgame/marciano_.png", Texture.class);
        finishLoading();
	}
	
	public void loadCameraminigameScreen() {
		load("cameraminigame/cesped2.png", Texture.class);
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
		finishLoading();
	}
}