package com.me.mygdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;



public class Core implements ApplicationListener {
	float w,h;
	OrthographicCamera camera;
	World mundo;
	Box2DDebugRenderer render;
	
	Body jog;
	BodyDef jogDef;
	
	
	
	
	@Override
	public void create() {
		// respectivos tamanhos em pixel...
		h = Gdx.graphics.getHeight();
		w = Gdx.graphics.getWidth();
		
		//mundo com vetor de gravidade
		mundo = new World(new Vector2(0, -9.7f), true);
		//rendenizador do Box2d
		render = new Box2DDebugRenderer();
		//Camera em que o rendenizador será "acoplado"
		camera = new OrthographicCamera(w,h);
        camera.viewportHeight = h;  
        camera.viewportWidth = w;  
        camera.position.set(camera.viewportWidth * .5f, camera.viewportHeight * .5f, 0f);  
        camera.update();  
		 BodyDef groundBodyDef =new BodyDef();  
         groundBodyDef.position.set(new Vector2(0, 10));  
         Body groundBody = mundo.createBody(groundBodyDef);  
         PolygonShape groundBox = new PolygonShape();  
         groundBox.setAsBox((camera.viewportWidth) * 2, 10.0f);  
         groundBody.createFixture(groundBox, 0.0f);  
         //Dynamic Body  
         BodyDef bodyDef = new BodyDef();  
         bodyDef.type = BodyType.DynamicBody;  
         bodyDef.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2);  
         Body body = mundo.createBody(bodyDef);  
         CircleShape dynamicCircle = new CircleShape();  
         dynamicCircle.setRadius(5f);  
         FixtureDef fixtureDef = new FixtureDef();  
         fixtureDef.shape = dynamicCircle;  
         fixtureDef.density = 1.0f;  
         fixtureDef.friction = 0.0f;  
         fixtureDef.restitution = 1;  
         body.createFixture(fixtureDef);  
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		render.render(mundo, camera.combined);
		mundo.step(1/20f, 6, 2);
		
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
