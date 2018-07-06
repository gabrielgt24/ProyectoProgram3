package com.Proyecto.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.Proyecto.game.Constans;
import com.Proyecto.game.proyectgamemain;
import com.Proyecto.game.Entities.StringBlocks;
import com.Proyecto.game.Entities.Board;
//import com.proyectgame1.Entities.*;
//import com.proyectgame1.Processor.Gprocessor;
import com.Proyecto.game.Entities.Floor;
import com.Proyecto.game.Processor.Gprocessor;

public class GameScreen extends BaseScreen {
    private OrthographicCamera camera;
    private FitViewport viewport;
    private Stage escenario;
    private World mundo;
    private Floor piso;
    private Texture texture;
    private Board tablero;
    private Gprocessor cpu; 
    private Image background; 
    private StringBlocks acumulador;
    private GameOverScreen Over;

    public GameScreen(proyectgamemain juego) {
        super(juego);
        cpu=new Gprocessor();        
        camera=new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        viewport=new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),camera);
        escenario=new Stage(viewport);
        mundo=new World(new Vector2(0,15),true);
        texture=juego.getManager().get("match3_sheet.png");                   
        background = new Image(juego.getManager().get("stars.png", Texture.class));                     
        escenario.addActor(background);
        acumulador = new StringBlocks();

    }

    @Override
    public void show() {
    	Gdx.input.setInputProcessor(cpu);
        piso=new Floor(mundo,texture,new Vector2((190+4f*Constans.PIXEL_IN_METER)/Constans.PIXEL_IN_METER,(425+1f*Constans.PIXEL_IN_METER)/Constans.PIXEL_IN_METER));        
        escenario.addActor(piso);
        tablero=new Board(texture,new Vector2(250,0),escenario,mundo, acumulador);
        cpu.setTablero(tablero);               
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);       
        escenario.act();
        if (gameEnds()) {
            Over=new GameOverScreen(juego);
        	juego.setScreen(Over);
        }    
        mundo.step(delta,6,2);
        escenario.draw();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        viewport.setCamera(camera);
        escenario.setViewport(viewport);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        piso.detach();
        piso.remove();
        tablero.detach();
        acumulador.dispose();
        escenario.clear();
        escenario.dispose();
        mundo.dispose();
        texture.dispose();
        Over.dispose();
    }
    
    private boolean gameEnds() {
    	return acumulador.gameEnds(piso.sueloBodyY());
    }
}
