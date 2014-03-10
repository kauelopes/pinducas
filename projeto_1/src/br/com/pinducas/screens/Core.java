package br.com.pinducas.screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;



public class Core extends Game implements ApplicationListener {
	
	OpenScreen openScreen;
	MenuInicial menuInicial;
	JogoScreen jogoScreen;
	
	public int WIDTH;
	public int HEIGHT;

	@Override
	public void create() {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		openScreen = new OpenScreen(this);
		menuInicial = new MenuInicial(this);
		jogoScreen = new JogoScreen(this);
		setScreen(openScreen);
		
	}
	
	
}
