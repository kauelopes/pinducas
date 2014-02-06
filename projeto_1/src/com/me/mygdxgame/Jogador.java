package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

public class Jogador {

	public final static int LADRAO = 0;
	public final static int GUARDA = 1;
	
	//CARACTERISTICAS DO PERSONAGEM
	private int largura,altura;
	private float speed;
	private Body body;
	private World world;
	
	public Jogador(World world){
		this.world=world;
		
	}
	
	
	public void init(){
		
		
		
	}
	
	
	
}
