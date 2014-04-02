package br.com.pinducas.map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class BlocoMaker {
	World world;
	
	public BlocoMaker(World world){
		this.world = world;		
	}
	
	public Bloco fixoQ0(Vector2 position){
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(50, 50);
		return new Bloco(world, position, shape);
	}
	
	public Bloco dinamicoQ0(Vector2 position){
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(50/4, 50/4);
		return new Bloco(world, position, shape, 0.5f, 0.3f, 0.5f);		
	}

}
