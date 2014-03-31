package br.com.pinducas.map;

import java.awt.Point;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Mapa {
	Bloco[][] blocosDoMapa;
	Point tamanhoMapa;
	
	public Mapa(World mundo, Point t){
		this.tamanhoMapa = t;
		blocosDoMapa = new Bloco[tamanhoMapa.x][tamanhoMapa.y];
		criaMapa(mundo);		
	}
	
	
	void criaMapa(World mundo){
		for(int x = 0; x < tamanhoMapa.x; x++){
			for(int y = 0; y < tamanhoMapa.y; y++){
				blocosDoMapa[x][y] = new Bloco(mundo, new Vector2(x*50, y*50));
			}	
		}		
	}
}
