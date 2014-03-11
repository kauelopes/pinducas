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
				velocidadeNormal,
				velocidadeDeCorrida;
	private float angle;
	
    boolean right,left,up,down;

	
	public Guarda(World world, RayHandler rayHandler, int velocidadeDeMovimentacao, int velocidadeDeCorrida){
		this.velocidadeDeCorrida = velocidadeDeCorrida;
		this.velocidadeNormal = velocidadeDeMovimentacao;
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
	    fixtureDef.restitution = 0.2f;
	    
	    body.createFixture(fixtureDef);
	    body.setFixedRotation(true);
	    
	    
	}

	private void CriaLanterna(RayHandler rayHandler){
		lanterna = new ConeLight(rayHandler, 100, Color.BLUE, 200, 0, 0, 0, 50);
        lanterna.attachToBody(body, 0, 0);
	}
	
	
	public void loop(){
         anda();
	}

	 
	 private void anda(){
		 vX = 0;
		 vY = 0;
		 int velocidade; 
		 
		 
		 if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)){
			 velocidade = velocidadeDeCorrida;
		 }else velocidade= velocidadeNormal;
         
         if(Gdx.input.isKeyPressed(Keys.RIGHT))
        	 right=true;
         else if(Gdx.input.isKeyPressed(Keys.LEFT)){
        	 left=true;
         }
        
         if(Gdx.input.isKeyPressed(Keys.UP)){
        	 up=true;
         }
         else if(Gdx.input.isKeyPressed(Keys.DOWN)){
        	 down=true;
         }
         if(!Gdx.input.isKeyPressed(Keys.RIGHT))
        	 right=false;
         if(!Gdx.input.isKeyPressed(Keys.LEFT))
        	 left=false;
         if(!Gdx.input.isKeyPressed(Keys.UP))
        	 up=false;
         if(!Gdx.input.isKeyPressed(Keys.DOWN))
        	 down=false;
         
         
         if(right){
        	 vX += velocidade;
             angle=(float)Math.toRadians(0);
             if(up){
            	 angle=(float)Math.toRadians(45);
            	 vY += velocidade;
             }
             if(down){
            	 angle=(float)Math.toRadians(315);
            	 vY -= velocidade;
             }
         }
         else if(left){
        	 vX -= velocidade;
        	 angle=(float)Math.toRadians(180);
             if(up){
                     angle=(float)Math.toRadians(135);
                     vY += velocidade;
             }
             if(down){
                     angle=(float)Math.toRadians(225);
                     vY -= velocidade;
             }
         }
         else if(up){
        	 angle=(float)Math.toRadians(90);
        	 vY += velocidade;
         }
         else if(down){
        	 angle=(float)Math.toRadians(270);
        	 vY -= velocidade;
         }
         
         body.setLinearVelocity(vX, vY);
         body.setTransform(body.getPosition(), angle);
	 }	
}
