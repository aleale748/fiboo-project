package es.uca.fiboo.ascensor.screens;

import java.util.ArrayList;

import com.badlogic.drop.Herramienta.Tipo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class FiguraAscen {
	
//	public static enum Tipo {
//		PELO, ACCPELO, OJOS, BIGOTE, BOCA, GAFAS, DISFRAZ, CAMISA, PANTALON, MASCARA;
//	}
	
	int maxHer = 3;
	ArrayList<Herramienta> heramientas;
	String path;
	Tipo tipo;
	Texture textureTarea;
	Rectangle rectangleTarea;
	Image imageTarea;
	Herramienta nada;
	
	Button button;
	
	public FiguraAscen(){}
		
	public FiguraAscen(String path, Tipo tipo, int imgX, int imgY, int X, int Y){
		heramientas = new ArrayList<Herramienta>();
		this.tipo = tipo;
		textureTarea = new Texture(Gdx.files.internal(path));
		rectangleTarea = new Rectangle();
		rectangleTarea.x = X;
		rectangleTarea.y = Y;
		rectangleTarea.width = imgX;
		rectangleTarea.height = imgY;
		
		button = new Button();
		
		
	}


	
	public void addAHeramienta(Herramienta heramienta) {
		if(heramientas.size() <= maxHer)
			this.heramientas.add(heramienta);
	}
	
	public Herramienta getAHeramienta(int i) {
		if(i< maxHer && i > -1)
			return this.heramientas.get(i);
		else
			return nada;
		
	}
	
	

	public ArrayList<Herramienta> getHeramientas() {
		return heramientas;
	}

	public void setHeramientas(ArrayList<Herramienta> heramientas) {
		this.heramientas = heramientas;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
	this.textureTarea = new Texture(Gdx.files.internal(path));
		
		//this.path = path;
	}

//	public Tipo getTipo() {
//		return tipo;
//	}
//
//	public void setTipo(Tipo tipo) {
//		this.tipo = tipo;
//	}

	public Texture getTextureTarea() {
		return textureTarea;
	}

	public void setTextureTarea(Texture textureTarea) {
		this.textureTarea = textureTarea;
	}

	public Rectangle getRectangleTarea() {
		return rectangleTarea;
	}

	public void setRectangleTarea(Rectangle rectangleTarea) {
		this.rectangleTarea = rectangleTarea;
	}
	
	

}
