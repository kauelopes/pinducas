package br.com.pinducas.screens;

import box2dLight.ConeLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import br.com.pinducas.models.Guarda;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class JogoScreen implements Screen {

	
	Core game;
	
	OrthographicCamera camera;
	Box2DDebugRenderer box2dDebugRender;
	SpriteBatch spriteBatch;
	Guarda guarda;
	
	BitmapFont font;
	
	RayHandler rayHandler;
	
	World world;
    static final float BOX_STEP=1/60f;  
    static final int BOX_VELOCITY_ITERATIONS=6;  
    static final int BOX_POSITION_ITERATIONS=2;  
    static final float WORLD_TO_BOX=0.01f;  
    static final float BOX_WORLD_TO=100f;
    

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
		
		guarda = new Guarda(world, rayHandler, 40);
		
		box2dDebugRender = new Box2DDebugRenderer();
         
        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(camera.combined);

        font = new BitmapFont();
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT );
		Gdx.gl.glClearColor(0, 0, 0, 1);
		rayHandler.updateAndRender();
		world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
		box2dDebugRender.render(world, camera.combined);
		guarda.loop();
		spriteBatch.begin();
		font.draw(spriteBatch, " Distance:"+guarda.lanterna.getDistance()+"   Mouse_X:"+(Gdx.input.getX()-game.WIDTH/2) + " Mouse_Y:"+(-(Gdx.input.getY()-game.HEIGHT/2)) , -(game.WIDTH/2), -(game.HEIGHT/2)+100);
		spriteBatch.end();
		if(guarda.lanterna.contains((Gdx.input.getX()-game.WIDTH/2), (-(Gdx.input.getY()-game.HEIGHT/2)))){
			System.out.println("Contem o mouse");
        }else System.out.println("Nao Contem");

		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
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
