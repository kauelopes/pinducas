package br.com.pinducas.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Bloco {
	
	private Body body;
	private Texture texture;
	
	
	public Bloco(World world, Vector2 position){
		criaBloco(world, position);
	}
	
	private void criaBloco(World world, Vector2 position ) {
		BodyDef bodyDef = new BodyDef();  
	    bodyDef.type = BodyType.StaticBody;  
	    bodyDef.position.set(position);  
	    
	    this.body = world.createBody(bodyDef);
	    
	    PolygonShape shape = new PolygonShape();
	    shape.setAsBox(50, 50);
	    
	    FixtureDef fixDef = new FixtureDef();
	    fixDef.shape = shape;
	    
	    body.createFixture(fixDef);	   
	}
}
