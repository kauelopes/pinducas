package br.com.pinducas.models;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
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
	public float getX(){return body.getPosition().x;}
	public float getY(){return body.getPosition().y;}
}
