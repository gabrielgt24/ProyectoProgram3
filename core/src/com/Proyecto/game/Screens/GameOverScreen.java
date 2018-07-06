package com.Proyecto.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.Proyecto.game.proyectgamemain;

public class GameOverScreen extends BaseScreen{
	
	private Stage stage;
	private Skin skin;
	private Image gameover;
	private Image fondo;
	private TextButton reiniciar;
	private TextButton mainMenu;

	public GameOverScreen(final proyectgamemain juego) {
		super(juego);
		
		stage = new Stage(new FitViewport(640,360));
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		gameover = new Image(juego.getManager().get("GameOver_n.png", Texture.class));
		fondo = new Image(juego.getManager().get("fondo.png", Texture.class));		
		reiniciar = new TextButton("Reiniciar Juego", skin);
		mainMenu = new TextButton("Regresar al Menu Principal", skin);
		
		reiniciar.addCaptureListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				juego.setScreen(new GameScreen(juego));												
			}		
			
			
		});
		
		mainMenu.addCaptureListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				juego.setScreen(juego.menuScreen);
				
			}
			
		});
		
		gameover.setPosition(320 - gameover.getWidth() / 2, 320- gameover.getHeight());				
		reiniciar.setSize(200, 50);
		reiniciar.setPosition(70, 50);	
		mainMenu.setSize(250, 50);
		mainMenu.setPosition(350, 50);
		stage.addActor(fondo);
		stage.addActor(gameover);		
		stage.addActor(reiniciar);
		stage.addActor(mainMenu);
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		
		
	}

	@Override
	public void pause() {
		
		
	}

	@Override
	public void resume() {
		
		
	}

	@Override
	public void hide() {		
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void dispose() {
		fondo.remove();
		gameover.remove();
		reiniciar.remove();
		mainMenu.remove();
		stage.dispose();
	}

}
