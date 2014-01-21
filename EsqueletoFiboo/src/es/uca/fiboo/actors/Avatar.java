package es.uca.fiboo.actors;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import es.uca.fiboo.FibooGame;
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
	private transient final Map<Tipo, Complemento> complementos;
	private transient Texture base;
	
	private List<Complemento> data;
	
	public Avatar() {
		complementos = new EnumMap<Tipo, Complemento>(Tipo.class);
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
		complementos.put(Tipo.PANTALON, null);
		complementos.put(Tipo.CAMISETA, null);
		complementos.put(Tipo.DISFRAZ, null);
	}
	
	//Faltan algunas comprobaciones
	public void addComplemento(final Complemento complemento) {
		complementos.put(complemento.getTipo(), complemento);
		if(complemento.getTipo() == Tipo.CAMISETA || complemento.getTipo() == Tipo.PANTALON) {
			complementos.put(Tipo.DISFRAZ, null);
		}
		else if(complemento.getTipo() == Tipo.DISFRAZ) {
			complementos.put(Tipo.CAMISETA, null);
			complementos.put(Tipo.PANTALON, null);
		}
	}
	
	public void removeComplemento(final Tipo tipo) {
		complementos.put(tipo, null);
	}
	
	public void setBase(final String imagePath) {
		final int index = FibooGame.getComplementos().indexOf(new Complemento(imagePath, Tipo.PELO));
		addComplemento(FibooGame.getComplementos().get(index));
	}
	
	public void draw(final SpriteBatch batch, final float escala) {
		if(base == null) {
			base = FibooGame.MANAGER.get("complementos/base.png");
			base.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		float posY = (Gdx.graphics.getHeight() - escala) / 2f;
		batch.draw(base, 0, posY, escala, escala);
		
		for(final Entry<Tipo, Complemento> entry : complementos.entrySet()) {
			if(entry.getValue() != null) {
				batch.draw(entry.getValue().getImagen(), 0, posY, escala, escala);
			}
		}
	}
	
	//Guardamos el TreeMap en el array para pasarlo al Json
	public void formatToSave() {
		data = new ArrayList<Complemento>(complementos.size());
		for(final Entry<Tipo, Complemento> entry : complementos.entrySet()) {
			if(entry.getValue() != null) {
				data.add(entry.getValue());
			}
		}
	}
	
	//Cargamos el TreeMap del array obtenido del Json
	public void loadData() {
		for(final Complemento complemento : data) {
			complementos.put(complemento.getTipo(), complemento);
		}
		data.clear();
	}

}
