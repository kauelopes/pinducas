package br.com.pinducas.models;

import box2dLight.ConeLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class Guarda {
	public Body body;
	public ConeLight lanterna;
	private World world;
	private int vY,
				vX,
				velocidadeDeMovimentacao;
	private float angle;
	
	
	
	public Guarda(World world, RayHandler rayHandler, int velocidadeDeMovimentacao){
		this.velocidadeDeMovimentacao = velocidadeDeMovimentacao;
		this.world = world;
		CriaCorpo();
		CriaLanterna(rayHandler);
	}
	
	
	private void CriaCorpo(){
		BodyDef bodyDef = new BodyDef();  
	    bodyDef.type = BodyType.DynamicBody;  
	    bodyDef.position.set(0,0);  
	    
	    body = world.createBody(bodyDef);
	    
	    CircleShape dynamicCircle = new CircleShape();  
	    dynamicCircle.setRadius(10f);
	    
	    FixtureDef fixtureDef = new FixtureDef();  
	    fixtureDef.shape = dynamicCircle;  
	    fixtureDef.density = 1.0f;  
	    fixtureDef.friction = 0.0f;  
	    fixtureDef.restitution = 2f;
	    
	    body.createFixture(fixtureDef);
	    body.setFixedRotation(true);
	}

	private void CriaLanterna(RayHandler rayHandler){
		lanterna = new ConeLight(rayHandler, 100, Color.YELLOW, 200, 0, 0, 0, 50);
        lanterna.attachToBody(body, 0, 0);
	}
	public void loop(){
		anda();
	}
	
	
	private void anda(){
		vX = 0;
		vY = 0;
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			vX += velocidadeDeMovimentacao;
			angle = 0;
			
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			vX -= velocidadeDeMovimentacao;
			angle = (float) Math.toRadians(180);
		}
		
		if(Gdx.input.isKeyPressed(Keys.UP)){
			vY += velocidadeDeMovimentacao;
			body.setAngularDamping(100);
			angle = (float) Math.toRadians(90);
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
			vY -= velocidadeDeMovimentacao;
			angle = (float) Math.toRadians(270);
		}
		body.setLinearVelocity(vX, vY);
		body.setTransform(body.getPosition(), angle);
		
	}
      

	
	
	
}
