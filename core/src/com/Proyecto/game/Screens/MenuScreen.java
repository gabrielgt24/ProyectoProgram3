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

public class MenuScreen extends BaseScreen {

	private Stage stage;
	private Skin skin;
	private Image Mainmenu;
	private Image fondo;		
	private TextButton jugar;
	private TextButton exit;
	private GameScreen game;
	
	
	public MenuScreen(final proyectgamemain juego) {
		super(juego);	
		
		stage = new Stage(new FitViewport(640,360));
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		Mainmenu = new Image(juego.getManager().get("menuLetters.png", Texture.class));				
		fondo = new Image(juego.getManager().get("menuBackground.jpg", Texture.class));	
		jugar = new TextButton("Iniciar juego", skin);
		exit = new TextButton("Salir del juego", skin);
		jugar.addCaptureListener(new ChangeListener() {  

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game=new GameScreen(juego);
				juego.setScreen(game);
			}			
			
		});
		
		exit.addCaptureListener(new ChangeListener() {  

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.exit(0);				
			}
			
		});
		
		Mainmenu.setPosition(320 - Mainmenu.getWidth() / 2, 320 - Mainmenu.getHeight());				
		jugar.setSize(200, 50);
		jugar.setPosition(220, 150);				
		exit.setSize(200, 50);
		exit.setPosition(220, 30);
		
		stage.addActor(fondo);
		stage.addActor(Mainmenu);		
		stage.addActor(exit);
		stage.addActor(jugar);
				
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
		stage.dispose();
		skin.dispose();
		game.dispose();
	}

}
