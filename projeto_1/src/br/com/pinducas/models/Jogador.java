package br.com.pinducas.models;


import java.util.ArrayList;

import box2dLight.RayHandler;
import br.com.pinducas.screens.JogoScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;



public class Jogador extends Entidade {
	
	
	public Lanterna lanterna;
	boolean lanternaSwitch;
	
	//Valores dos botões
    int btnCorrida, btnR, btnL, btnU, btnD;
    private float keyTimer;
    
    //Variaveis da spriteSheet
    Texture spriteSheet;
    ArrayList<TextureRegion[]> sprites;
    TextureRegion currentFrame;
    int currentFrameNum;
    int numFrames;
    int currentAction;
    boolean rodando;
    float timer;
    float animationTime;
    //Setting TextureRegions indexes
    private static final int IDLE=0;
    private static final int WALK=1;
    
    
    
    
    
    
    
	public Jogador(World world,SpriteBatch spriteBatch,Vector2 position, RayHandler rayHandler, int velocidadeDeMovimentacao, int velocidadeDeCorrida){
		Initialize();
		this.velocidadeDeCorrida = velocidadeDeCorrida;
		this.velocidadeNormal = velocidadeDeMovimentacao;
		this.velocidadeAtual= velocidadeDeMovimentacao;
		this.world = world;
		this.sb=spriteBatch;
		CriaCorpo(position);
		CriaLanterna(rayHandler);
		btnCorrida = Keys.SHIFT_LEFT;
		btnR = Keys.RIGHT;
		btnL = Keys.LEFT;
		btnU = Keys.UP;
		btnD = Keys.DOWN;
	}
	
	public Jogador(World world,SpriteBatch spriteBatch,Vector2 position, RayHandler rayHandler, int velocidadeDeMovimentacao, int velocidadeDeCorrida, int bCorrida, int bR, int bL, int bU, int bD){
		Initialize();
		this.velocidadeDeCorrida = velocidadeDeCorrida;
		this.velocidadeNormal = velocidadeDeMovimentacao;
		this.velocidadeAtual=velocidadeDeMovimentacao;
		this.world = world;
		this.sb=spriteBatch;
		CriaCorpo(position);
		CriaLanterna(rayHandler);
		btnCorrida = bCorrida;
		btnR = bR;
		btnL = bL;
		btnU = bU;
		btnD = bD;
	}
	
	private void CriaCorpo(Vector2 position){
		BodyDef bodyDef = new BodyDef();  
	    bodyDef.type = BodyType.DynamicBody;  
	    bodyDef.position.set(position);  
	    
	    body = world.createBody(bodyDef);
	    
	    CircleShape dynamicCircle = new CircleShape();  
	    dynamicCircle.setRadius(10f);
	    
	    FixtureDef fixtureDef = new FixtureDef();  
	    fixtureDef.shape = dynamicCircle;  
	    fixtureDef.density = 0.1f;  
	    fixtureDef.friction = 0.0f;  
	    fixtureDef.restitution = 0.3f;
	    
	    body.createFixture(fixtureDef);
	    body.setFixedRotation(true);	    
	    
	}

	private void CriaLanterna(RayHandler rayHandler){
		lanterna = new Lanterna(rayHandler, 100, Color.YELLOW, 400, 0, 0, 0, 40);
		lanterna.attachToBody(body, 0, 0);
        
	}
	public void Initialize(){
		vX=0;
		vY=0;
		lanternaSwitch = true;
		angle=0;
		currentFrameNum=0;
		currentAction = 0;
		rodando=false;
		
		//Loading Sprites
		sprites=new ArrayList<TextureRegion[]>();
		
		spriteSheet =  new Texture(Gdx.files.internal("assets/jogador/gtaSheet.png"));  
		
		TextureRegion[][]temp =  TextureRegion.split(spriteSheet, spriteSheet.getWidth() / 
				12, spriteSheet.getHeight() / 2);  
		
		TextureRegion [] temporario = new TextureRegion[5];
		for(int coluna=0;coluna<5;coluna++){
			temporario[coluna]=temp[0][coluna];
		}
		sprites.add(temporario);
		
		temporario = new TextureRegion[12];
		for(int coluna=0;coluna<12;coluna++){
			temporario[coluna]=temp[1][coluna];
		}
		sprites.add(temporario);
		
	}
	
	
	
	protected void Update(){
		anda();
		
		
		//SETTING CURRENT ACTION
		if(!right&&!left&&!up&&!down&&!upRight&&!upLeft&&!downRight&&!downLeft){
			if(currentAction!=IDLE){
				currentAction=IDLE;
				numFrames=5;
				currentFrameNum=0;
				rodando=false;
			}
			
		}
		else
			rodando=true;
		
		
		
		if(right){
	       	 vX = velocidadeAtual;
	         angle=(float)Math.toRadians(0);
	         currentAction=WALK;
	         numFrames=12;
	         animationTime=0.1f;
	      }
		if(left){
			vX = -velocidadeAtual;
			angle=(float)Math.toRadians(180);
			currentAction=WALK;
	        numFrames=12;
	        animationTime=0.1f;
		}
		if(up){
			angle=(float)Math.toRadians(90);
			vY = velocidadeAtual;
			currentAction=WALK;
	        numFrames=12;
	        animationTime=0.1f;
		}
		if(down){
			angle=(float)Math.toRadians(270);
			vY = -velocidadeAtual;
			currentAction=WALK;
	        numFrames=12;
	        animationTime=0.1f;
		}
		
		if(up&&right){
			angle=(float)Math.toRadians(45);
			vY = velocidadeAtual;
			currentAction=WALK;
	        numFrames=12;
	        animationTime=0.1f;
		}
		if(down&&right){
			angle=(float)Math.toRadians(315);
			vY = -velocidadeAtual;
			currentAction=WALK;
	        numFrames=12;
	        animationTime=0.1f;
		}
		if(up&&left){
			angle=(float)Math.toRadians(135);
			vY = velocidadeAtual;
			currentAction=WALK;
	        numFrames=12;
	        animationTime=0.1f;
		}
		if(down&left){
			angle=(float)Math.toRadians(225);
			vY = -velocidadeAtual;
			currentAction=WALK;
	        numFrames=12;
	        animationTime=0.1f;
		}
        body.setLinearVelocity(vX, vY);
        body.setTransform(body.getPosition(),angle);
        if(right)
        System.out.println("RIGHT: "+right);
        if(left)
        System.out.println("LEFT: "+left);
        if(up)
        System.out.println("UP: "+up);
        if(down)
        System.out.println("DOWN: "+down);
        updateLanterna();
	}
	
	protected void Draw(){
		if(rodando){
			timer+= Gdx.graphics.getDeltaTime();  
			if(timer>animationTime){
			currentFrameNum++;
				if(currentFrameNum>numFrames-1)
					currentFrameNum=0;
			timer=0;
			}
			
		}
		currentFrame = sprites.get(currentAction)[currentFrameNum];
		
        sb.draw(currentFrame,body.getPosition().x-currentFrame.getRegionWidth()/2,body.getPosition().y-currentFrame.getRegionHeight()/2,
        		currentFrame.getRegionWidth()/2,currentFrame.getRegionHeight()/2,currentFrame.getRegionWidth(),currentFrame.getRegionHeight(),
        		2f,2f,(float)Math.toDegrees(angle));                  
	}
	
	private void updateLanterna() {
				
		
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
	private void anda(){
		 //Botao de corrida
		 vX=0;
		 vY=0;
		 if(Gdx.input.isKeyPressed(btnCorrida)){
			 velocidadeAtual = velocidadeDeCorrida;
		 }else velocidadeAtual= velocidadeNormal;

		
		 keyTimer+=Gdx.graphics.getDeltaTime();
		 
		 if(keyTimer>0.080f){
         //Botoes de movimentacao Horizontal e Vertical
         if(Gdx.input.isKeyPressed(btnR)){
        	 right=true;
        	 left=false;
        	 
         }
         else if(Gdx.input.isKeyPressed(btnL)){
        	 left=true;
        	 right=false;
         }
         if(Gdx.input.isKeyPressed(btnU)){
        	 up=true;
        	 down=false;
         }
         else if(Gdx.input.isKeyPressed(btnD)){
        	 down=true;
        	 up=false;
         }
      
		 
        
         
         if(!Gdx.input.isKeyPressed(btnR)){
        	 right=false;
        	 upRight=false;
        	 downRight=false;
         }
         if(!Gdx.input.isKeyPressed(btnL)){
        	 left=false;
        	 downLeft=false;
        	 upLeft=false;
         }
         if(!Gdx.input.isKeyPressed(btnU)){
        	 up=false;
        	 upLeft=false;
        	 upRight=false;
         }
         if(!Gdx.input.isKeyPressed(btnD)){
        	 down=false;
        	 downLeft=false;
        	 downRight=false;
        	
         }
         keyTimer=0;
		 }
	 }	
		
	
	
}
