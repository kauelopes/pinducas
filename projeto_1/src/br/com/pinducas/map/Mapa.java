package br.com.pinducas.map;

import java.awt.Point;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Mapa {
	public Bloco[][] blocosDoMapa;
	private BlocoMaker maker;
	private Point tamanhoMapa;
	
	
	public Mapa(World world, Point t){
		this.tamanhoMapa = t;
		maker = new BlocoMaker(world);
		blocosDoMapa = new Bloco[tamanhoMapa.x][tamanhoMapa.y];
		criaMapa();		
	}
	
	
	void criaMapa(){
		for(int x = 0; x < tamanhoMapa.x; x++){
			for(int y = 0; y < tamanhoMapa.y; y++){
				blocosDoMapa[x][y] = maker.fixoQ0(new Vector2(x*50, y*50));
			}	
		}		
	}
	
	public Bloco getbloco(int x, int y){
		return blocosDoMapa[x][y];
	}
}
