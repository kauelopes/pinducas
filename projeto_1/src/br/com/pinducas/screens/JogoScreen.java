package br.com.pinducas.screens;

import javax.swing.Spring;

import box2dLight.RayHandler;
import br.com.pinducas.models.Guarda;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;

public class JogoScreen implements Screen {

	
	
	Core game;

	OrthographicCamera camera;
	
	World world;
	
	RayHandler rayHandler;
	SpriteBatch spriteBatch;
	Box2DDebugRenderer box2dDebugRender;

	Guarda guarda;
	
	BitmapFont font;
	
	static final float BOX_STEP=1/60f;  
	static final int BOX_VELOCITY_ITERATIONS=6;  
	static final int BOX_POSITION_ITERATIONS=2;  
	static final float WORLD_TO_BOX=0.01f;  
	static final float BOX_WORLD_TO=100f;
	
	
	
    /*---------------------------------------------------\
    | 	Area abaixo reservada para testes com variaveis.  |
    | 			Tudo fora desse espaco e final			  |
    \----------------------------------------------------*/
	
	Sprite sprite;
	
	
	
	
	/*---------------------------------------------------\
	|		 		Fim do Espaco de teste           	  |
	\---------------------------------------------------*/
    

	public JogoScreen(Core core){
		this.game = core;
	}
    
	@Override
	public void show() {
		
		
		camera = new OrthographicCamera(game.WIDTH, game.HEIGHT);
		camera.update();
		
		world = new World(new Vector2(0, -1), true);
		
		rayHandler = new RayHandler(world);
		rayHandler.setCombinedMatrix(camera.combined);
		
		guarda = new Guarda(world, rayHandler, 40, 80);
		
		box2dDebugRender = new Box2DDebugRenderer();
         
        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(camera.combined);

        font = new BitmapFont();
    
		
	    /*---------------------------------------------------\
	    | 	Area abaixo reservada para testes com variaveis.  |
	    | 			Tudo fora desse espaco e final			  |
	    \----------------------------------------------------*/
		Texture texture = new Texture(Gdx.files.internal("assets/libgdx.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Linear);
		
		TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);
		sprite = new Sprite(region);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
		
        
        BodyDef bodyDef = new BodyDef();  
	    bodyDef.type = BodyType.DynamicBody;  
	    bodyDef.position.set(0,0);  
	    
	    Body body = world.createBody(bodyDef);
	    
	    CircleShape dynamicCircle = new CircleShape();  
	    dynamicCircle.setRadius(10f);
	    
	    FixtureDef fixtureDef = new FixtureDef();  
	    fixtureDef.shape = dynamicCircle;  
	    fixtureDef.density = 1.0f;  
	    fixtureDef.friction = 0.8f;  
	    fixtureDef.restitution = 2f;
	    
	    body.createFixture(fixtureDef);
	    body.setFixedRotation(true);


	    
		/*---------------------------------------------------\
		|		 		Fim do Espaco de teste           	  |
		\---------------------------------------------------*/

	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT );
		Gdx.gl.glClearColor(0, 0, 0, 1);

		world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
		box2dDebugRender.render(world, camera.combined);
		guarda.loop();
		spriteBatch.begin();
		sprite.draw(spriteBatch);
		font.draw(spriteBatch, " Distance:"+guarda.lanterna.getDistance()+"   Mouse_X:"+(Gdx.input.getX()-game.WIDTH/2) + " Mouse_Y:"+(-(Gdx.input.getY()-game.HEIGHT/2)) , -(game.WIDTH/2), -(game.HEIGHT/2)+100);
		spriteBatch.end();
		if(guarda.lanterna.contains((Gdx.input.getX()-game.WIDTH/2), (-(Gdx.input.getY()-game.HEIGHT/2)))){
			System.out.println("Contem o mouse");
        }else System.out.println("Nao Contem");
		rayHandler.updateAndRender();
		

	    /*---------------------------------------------------\
	    | 	Area abaixo reservada para testes com variaveis.  |
	    | 			Tudo fora desse espaco e final			  |
	    \----------------------------------------------------*/
		
		
		
		
		
		
		/*---------------------------------------------------\
		|		 		Fim do Espaco de teste           	  |
		\---------------------------------------------------*/
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
	    /*---------------------------------------------------\
	    | 	Area abaixo reservada para testes com variaveis.  |
	    | 			Tudo fora desse espaco e final			  |
	    \----------------------------------------------------*/
		
		
		
		
		
		
		/*---------------------------------------------------\
		|		 		Fim do Espaco de teste           	  |
		\---------------------------------------------------*/		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		rayHandler.dispose();
		box2dDebugRender.dispose();
	}

}
