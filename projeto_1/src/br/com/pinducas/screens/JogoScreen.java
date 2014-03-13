package br.com.pinducas.screens;
 
import box2dLight.RayHandler;
import br.com.pinducas.models.Camera;
import br.com.pinducas.models.Jogador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
 
public class JogoScreen implements Screen {   
	Core game;
	
	public static Camera camera;
        
    OrthographicCamera cam;
 
    World world;
       
    //Rendenizadores Gerais
    //Luz:
    RayHandler rayHandler;
    //Sprite:
    SpriteBatch spriteBatch;
    //Fisica
    Box2DDebugRenderer box2dDebugRender;
 
    Jogador guarda;
       
    //Constantes do Box2dphysics
    static final float BOX_STEP=1/30f;  
    static final int BOX_VELOCITY_ITERATIONS=6;  
    static final int BOX_POSITION_ITERATIONS=2;  
    static final float WORLD_TO_BOX=0.01f;  
    static final float BOX_WORLD_TO=100f;
       
       
       
    /*---------------------------------------------------\
    |   Area abaixo reservada para testes com variaveis.  |
    |                   Tudo fora desse espaco e final    |
    \----------------------------------------------------*/
       
       
    //Fonte para escrever em baixo   
    BitmapFont font;
               
    //Sprite da imagem Default do Libgdx
    Sprite sprite;
      
    //Render do TiledMap
    TiledMapRenderer tileMapRenderer;
       
    //Map
    TiledMap map;      
    ParticleEffect p;
 
       
        /*---------------------------------------------------\
        |          Fim do Espaco de teste                    |
        \---------------------------------------------------*/
   
 
        
    public JogoScreen(Core core){
    	this.game = core;    
    }
        
        
    @Override
    public void show() {
    	//Camera para Box2d, Sprite, tiledMap e Luz
    	camera = new Camera(game.HEIGHT, game.WIDTH);

    	camera.update();

        world = new World(new Vector2(0, 0), true);
        
        rayHandler = new RayHandler(world);
        rayHandler.setCombinedMatrix(camera.combined);
           
        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(camera.combined);

        box2dDebugRender = new Box2DDebugRenderer();
           
        map = new TmxMapLoader().load("assets/mapa.tmx");
           
        tileMapRenderer = new OrthogonalTiledMapRenderer(map, 2 / 3f);
        
        guarda = new Jogador(world,spriteBatch,new Vector2(0,0), rayHandler, 20, 150,Keys.SHIFT_LEFT,Keys.D,Keys.A,Keys.W,Keys.S);

        font = new BitmapFont();
            
           
        /*---------------------------------------------------\
        |   Area abaixo reservada para testes com variaveis.  |
        |                   Tudo fora desse espaco e final    |
        \----------------------------------------------------*/
           
       p = new ParticleEffect();
       p.load(Gdx.files.internal("assets/spark.p"), Gdx.files.internal("assets"));
            /*---------------------------------------------------\
            |       	Fim do Espaco de teste                    |
            \---------------------------------------------------*/
       
    }
    @Override
    public void render(float delta) {
    	//Limpa a Tela
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT );
    	Gdx.gl.glClearColor(0, 0, 0, 1);
    	
    	camera.enquadraPonto(guarda.getX(), guarda.getY(), 200, 250);
                                                           
    	world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
           
    	tileMapRenderer.setView(camera);
    	tileMapRenderer.render();
           
           


    	p.setPosition(0,0);
    	p.start();
    	     
    	rayHandler.updateAndRender();
    	rayHandler.setCombinedMatrix(camera.combined);
       
            
    	spriteBatch.setProjectionMatrix(camera.combined);
    	spriteBatch.begin();
    	p.draw(spriteBatch);
            
    	font.draw(spriteBatch, " Distance:"+guarda.lanterna.posicao.x+"   Mouse_X:"+(Gdx.input.getX()-game.WIDTH/2) + " Mouse_Y:"+(-(Gdx.input.getY()-game.HEIGHT/2)) , -(game.WIDTH/2), -(game.HEIGHT/2)+100);
            
    	guarda.Render();
            
            
    	spriteBatch.end();
         
    	
            
        /*---------------------------------------------------\
        |   Area abaixo reservada para testes com variaveis.  |
        |                   Tudo fora desse espaco e final   |
        \----------------------------------------------------*/
         
    	/*---------------------------------------------------\
       	|          Fim do Espaco de teste                    |
        \---------------------------------------------------*/
           
           
    	camera.update();
    	
    }
    @Override
    public void resize(int width, int height) {
            // TODO Auto-generated method stub
           
    }
    @Override
    public void hide() {
        /*---------------------------------------------------\
        |   Area abaixo reservada para testes com variaveis.  |
        |   Tudo fora desse espaco e final                    |
        \----------------------------------------------------*/
        
    	
    	/*---------------------------------------------------\
        |   Fim do Espaco de teste                    |
        \---------------------------------------------------*/      
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
