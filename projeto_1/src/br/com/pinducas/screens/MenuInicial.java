package br.com.pinducas.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuInicial implements Screen {
	
	Core game;
	BitmapFont font;
	SpriteBatch spriteBatch;
	OrthographicCamera camera;
	
	public MenuInicial(Core core){		
		this.game = core;
	}
	
	@Override
	public void show() {
		camera = new OrthographicCamera(game.WIDTH, game.WIDTH);
		font = new BitmapFont();
		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(camera.combined);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT );
		Gdx.gl.glClearColor(0, 0, 0, 1);
		spriteBatch.begin();
		font.setColor(Color.CYAN);
		font.draw(spriteBatch, "tela do Menu", 0, 0);
		font.draw(spriteBatch, "aperte B", 0, -17);
		spriteBatch.end();
		if(Gdx.input.isKeyPressed(Keys.B)){
			game.setScreen(new JogoScreen(game));
		}
		

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
		// TODO Auto-generated method stub
		
	}

}
