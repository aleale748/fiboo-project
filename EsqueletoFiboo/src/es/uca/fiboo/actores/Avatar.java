package es.uca.fiboo.actores;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import es.uca.fiboo.fibooGame;
import es.uca.fiboo.personalizar.actores.Complemento;
import es.uca.fiboo.personalizar.actores.Complemento.Tipo;

/**
 * Con el avatar se consigue que siempre se inserten los complementos
 * en el orden que han de ser pintados correctamente
 * 
 * @author Sergio
 * @version 0.2
 *
 */
public class Avatar {

	//Con transient se consigue que no se guarde ni lea en Json
	private transient TreeMap<Tipo, Complemento> complementos;
	private transient TextureRegion base;
	private transient float posX, posY, escala;
	
	private ArrayList<Complemento> data;
	
	public Avatar() {
		complementos = new TreeMap<Tipo, Complemento>();
		escala = Gdx.graphics.getHeight() * 0.7f;
		posX = 0;
		posY = (Gdx.graphics.getHeight() - escala) / 2f;
		
		initializeComplementos();
	}
	
	//Quizá hay que corregir el orden
	private void initializeComplementos() {
		complementos.put(Tipo.PELO, null);
		complementos.put(Tipo.ACCPELO, null);
		complementos.put(Tipo.OJOS, null);
		complementos.put(Tipo.GAFAS, null);
		complementos.put(Tipo.BOCA, null);
		complementos.put(Tipo.BIGOTE, null);
		complementos.put(Tipo.MASCARA, null);
		complementos.put(Tipo.CAMISA, null);
		complementos.put(Tipo.PANTALON, null);
		complementos.put(Tipo.DISFRAZ, null);
	}
	
	//Faltan algunas comprobaciones
	public void addComplemento(Complemento c) {
		complementos.put(c.getTipo(), c);
	}
	
	public void removeComplemento(Tipo tipo) {
		complementos.put(tipo, null);
	}
	
	public void setBase(String imagePath) {
		int index = fibooGame.getComplementos().indexOf(new Complemento(imagePath, Tipo.PELO));
		addComplemento(fibooGame.getComplementos().get(index));
	}
	
	public void draw(SpriteBatch batch) {
		if(base == null) {
			base = fibooGame.atlasComplementos.findRegion("base");
		}
		batch.draw(base, posX, posY, escala, escala);
		
		for(Entry<Tipo, Complemento> c : complementos.entrySet()) {
			if(c.getValue() != null) {
				batch.draw(c.getValue().getImagen(), posX, posY, escala, escala);
			}
		}
	}
	
	public void dispose() {
		base = null;
		for(Entry<Tipo, Complemento> c : complementos.entrySet()) {
			if(c.getValue() != null) {
				c.getValue().dispose();
			}
		}
	}
	
	
	//Guardamos el TreeMap en el array para pasarlo al Json
	public void formatToSave() {
		data = new ArrayList<Complemento>(complementos.size());
		for(Entry<Tipo, Complemento> c : complementos.entrySet()) {
			if(c.getValue() != null)
				data.add(c.getValue());
		}
	}
	
	//Cargamos el TreeMap del array obtenido del Json
	public void loadData() {
		for(Complemento c : data) {
			complementos.put(c.getTipo(), c);
		}
		data.clear();
	}

}
