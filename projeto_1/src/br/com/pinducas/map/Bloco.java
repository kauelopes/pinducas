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
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class Bloco {
	
	private Body body;
	private Texture texture;
	
	
	public Bloco(World world, Vector2 position, PolygonShape shape){
		criaBlocoEstatico(world, position, shape);
	}
	
	public Bloco(World world, Vector2 position, PolygonShape shape, float densidade, float friction, float restituicao ){
		criaBlocoDinamico(world, position, shape, densidade, friction, restituicao);
	}
	
	private void criaBlocoDinamico(World world, Vector2 position, PolygonShape shape, float densidade, float friction, float restituicao ) {
		BodyDef bodyDef = new BodyDef();  
	    bodyDef.type = BodyType.DynamicBody;
	    bodyDef.position.set(position);  
	    
	    this.body = world.createBody(bodyDef);
	    	    
	    FixtureDef fixDef = new FixtureDef();
	    fixDef.shape = shape;
	    fixDef.density = densidade;
	    fixDef.friction = friction;
	    fixDef.restitution = restituicao;
	    
	    body.createFixture(fixDef);	   
	}
	
	
	private void criaBlocoEstatico(World world, Vector2 position, PolygonShape shape) {
		BodyDef bodyDef = new BodyDef();  
	    bodyDef.type = BodyType.StaticBody;
	    bodyDef.position.set(position);  
	    
	    this.body = world.createBody(bodyDef);
	    	    
	    FixtureDef fixDef = new FixtureDef();
	    fixDef.shape = shape;
	    
	    body.createFixture(fixDef);	   
	}
	
	public void deletaFixtureBloco(){
		this.body.destroyFixture(body.getFixtureList().first());
	}
}
