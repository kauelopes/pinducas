package br.com.pinducas.models;

import java.awt.Point;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class FpsGraph {
	
	
	//grafico
	FPSLogger logger;
	ArrayList<Point> points;
	ShapeRenderer render;	
	OrthographicCamera camera;
	int heigth, weigth;
	
	int contador;
		
	public FpsGraph(ShapeRenderer render,OrthographicCamera camera, int h, int w){
		this.render = render;		
		logger = new FPSLogger();
		points =  new ArrayList<Point>();
		contador = 0;
		this.camera = new OrthographicCamera(h,w);
		this.heigth = h;
		this.weigth = w;
		setup();
	}
	
	private void setup(){
		
		camera.viewportWidth = weigth*4;
		camera.viewportHeight = heigth*16;
		camera.translate(weigth*2, (heigth*8)+3600);
		camera.update();
		render.setProjectionMatrix(camera.combined);

		
	}
		
		
	public void render(){
		if(Gdx.graphics.getFramesPerSecond()!=0){
			points.add(contador, new Point(contador, Gdx.graphics.getFramesPerSecond()*80));
			int dif = 0;
			if(contador>weigth*4){
				dif = -contador + weigth*4;
			}
			
			render.begin(ShapeType.Line);
			render.setColor(Color.CYAN);
			for(int i = 0; i < points.size(); i++){
	    		if(i == 0){
	    			render.line(points.get(0).x+dif, points.get(0).y, points.get(0).x+dif, points.get(0).y);
	    		}else render.line(points.get(i-1).x+dif, points.get(i-1).y, points.get(i).x+dif, points.get(i).y);    		
	    	}    	
			render.end();
			contador++;			
		}
	}
		

}
