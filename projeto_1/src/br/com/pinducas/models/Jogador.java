package br.com.pinducas.models;

import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Jogador {
	public Body body;
	public Lanterna lanterna;
	private World world;
	private int vY,
				vX,
				velocidadeNormal,
				velocidadeDeCorrida,
				velocidadeAtual;
	private float angle;
	int btnCorrida, btnR, btnL, btnU, btnD;
    boolean right,left,up,down;
    boolean lanternaSwitch;
    
    
	public Jogador(World world, RayHandler rayHandler, int velocidadeDeMovimentacao, int velocidadeDeCorrida){
		this.velocidadeDeCorrida = velocidadeDeCorrida;
		this.velocidadeNormal = velocidadeDeMovimentacao;
		this.velocidadeAtual= velocidadeDeMovimentacao;
		
		this.world = world;
		CriaCorpo();
		CriaLanterna(rayHandler);
		btnCorrida = Keys.SHIFT_LEFT;
		btnR = Keys.RIGHT;
		btnL = Keys.LEFT;
		btnU = Keys.UP;
		btnD = Keys.DOWN;
	}
	
	public Jogador(World world, RayHandler rayHandler, int velocidadeDeMovimentacao, int velocidadeDeCorrida, int bCorrida, int bR, int bL, int bU, int bD){
		this.velocidadeDeCorrida = velocidadeDeCorrida;
		this.velocidadeNormal = velocidadeDeMovimentacao;
		this.world = world;
		CriaCorpo();
		btnCorrida = bCorrida;
		btnR = bR;
		btnL = bL;
		btnU = bU;
		btnD = bD;
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
		lanterna = new Lanterna(rayHandler, 100, Color.BLUE, 300, 0, 0, 0, 40);
        lanterna.attachToBody(body, 0, 0);
	}
	public void Initialize(){
		lanternaSwitch = true;
		
	}
	
	public void loop(){
         anda(btnCorrida, btnR, btnL, btnU, btnD);
         Update();
         estadoLanterna();
         
	}

	 
	 private void estadoLanterna() {
		 if(Gdx.input.isKeyPressed(Keys.F)&&lanternaSwitch){
			 lanternaSwitch = false;
			 if(lanterna.isActive())
				 lanterna.setActive(false);
			 else 
				 lanterna.setActive(true);
		 }
		 if(!Gdx.input.isKeyPressed(Keys.F)&&!lanternaSwitch)
			 lanternaSwitch=true;
	}
	private void Update(){
		 if(right){
        	 vX = velocidadeAtual;
             angle=(float)Math.toRadians(0);
             if(up){
            	 angle=(float)Math.toRadians(45);
            	 vY = velocidadeAtual;
             }
             if(down){
            	 angle=(float)Math.toRadians(315);
            	 vY = -velocidadeAtual;
             }
         }
         else if(left){
        	 vX = -velocidadeAtual;
        	 angle=(float)Math.toRadians(180);
             if(up){
                     angle=(float)Math.toRadians(135);
                     vY = velocidadeAtual;
             }
             if(down){
                     angle=(float)Math.toRadians(225);
                     vY = -velocidadeAtual;
             }
         }
         else if(up){
        	 angle=(float)Math.toRadians(90);
        	 vY = velocidadeAtual;
         }
         else if(down){
        	 angle=(float)Math.toRadians(270);
        	 vY = -velocidadeAtual;
         }
         
         body.setLinearVelocity(vX, vY);
         body.setTransform(body.getPosition(), angle);
	}
	private void anda(int btnCorrida, int btnR, int btnL, int btnU, int btnD){ 
		 //Botao de corrida
		 vX=0;
		 vY=0;
		 if(Gdx.input.isKeyPressed(btnCorrida)){
			 velocidadeAtual = velocidadeDeCorrida;
		 }else velocidadeAtual= velocidadeNormal;
		 
		 
         //Botoes de movimentacao horizontal
         if(Gdx.input.isKeyPressed(btnR)){
        	 right=true;
         }else if(Gdx.input.isKeyPressed(btnL)){
        	 left=true;
         }
        
         //botoes de movimentao vertica;
         if(Gdx.input.isKeyPressed(btnU)){
        	 up=true;
         }else if(Gdx.input.isKeyPressed(btnD)){
        	 down=true;
         }
         
         
         //Botoes soltos.
         if(!Gdx.input.isKeyPressed(btnR))
        	 right=false;
         if(!Gdx.input.isKeyPressed(btnL))
        	 left=false;
         if(!Gdx.input.isKeyPressed(btnU))
        	 up=false;
         if(!Gdx.input.isKeyPressed(btnD))
        	 down=false;
         
         
        
	 }	
}
