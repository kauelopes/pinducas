package br.com.pinducas.models;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera extends OrthographicCamera{
	
	public Camera(int heigth, int width){
		this.viewportHeight = heigth;
		this.viewportWidth = width;
	}
	
	public void enquadraPonto(float x, float y, float margemVertical, float margemHorizontal){
		if(x > ((this.position.x + (this.viewportWidth/2)) - margemHorizontal)){
			this.position.x = (x -(this.viewportWidth/2 - margemHorizontal))+1;
		}
		if(x < ((this.position.x - (this.viewportWidth/2)) + margemHorizontal)){
			this.position.x = (x +(this.viewportWidth/2 - margemHorizontal))-1;
		}
		if(y > ((this.position.y + (this.viewportHeight/2)) - margemVertical)){
			this.position.y= (y -(this.viewportHeight/2 - margemVertical))+1;
		}
		if(y < ((this.position.y - (this.viewportHeight/2)) + margemVertical)){
			this.position.y = (y +(this.viewportHeight/2 - margemVertical))-1;
		}
	}
	
	

}
