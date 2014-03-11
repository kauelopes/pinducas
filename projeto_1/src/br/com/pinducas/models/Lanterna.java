package br.com.pinducas.models;

import box2dLight.ConeLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Lanterna extends ConeLight {
	public Vector2 posicao = new Vector2();

	public Lanterna(RayHandler rayHandler, int rays, Color color,
			float distance, float x, float y, float directionDegree,
			float coneDegree) {
		super(rayHandler, rays, color, distance, x, y, directionDegree, coneDegree);
		
	}
	
	public boolean contem(float x, float y, float raio){
		float distancia = (float) Math.sqrt(    Math.pow((super.getX() - x), 2)    +   Math.pow((super.getY() - y), 2) );
		if(distancia<=raio && this.contains(x, y)){
			return true;
		}else return false;
	}

}
