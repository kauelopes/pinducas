package com.me.mygdxgame;

import br.com.pinducas.screens.Core;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "projeto_1";
		cfg.useGL20 = true;
		cfg.width = 480;
		cfg.height = 500;
		
		new LwjglApplication(new Core(), cfg);
	}
}
