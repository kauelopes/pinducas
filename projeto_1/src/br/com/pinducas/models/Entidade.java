package br.com.pinducas.models;

import java.util.ArrayList;

import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;



public class Entidade {
	protected World world;
	protected Body body;
	protected SpriteBatch sb;
	
    //Variaves de movimentacao
	protected int vY;
	protected int vX;
	protected int velocidadeNormal;
	protected int velocidadeDeCorrida;
	protected int velocidadeAtual;
	protected float angle;
	protected boolean right,left,up,down,upRight,upLeft,downRight,downLeft;
	
	public void Initialize(){
		
	}
	public void Render(){
		Update();
		Draw();
	}
	protected void Update(){
		
	}
	protected void Draw(){
		
	}
	
}
