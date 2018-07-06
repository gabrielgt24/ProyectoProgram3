package com.Proyecto.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.Proyecto.game.Screens.MenuScreen;

public class proyectgamemain extends Game{

	private AssetManager manager;
	public MenuScreen menuScreen;
	

	public AssetManager getManager() {
		return manager;
	}

	@Override
	public void create () {
		manager=new AssetManager();
		manager.load("match3_sheet.png",Texture.class);			
		manager.load("stars.png",Texture.class);
		manager.load("GameOver_n.png",Texture.class);
		manager.load("menuLetters.png",Texture.class);
		manager.load("fondo.png",Texture.class);					
		manager.load("menuBackground.jpg",Texture.class);		
		manager.finishLoading();
		
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen);
	}

	@Override
	public void dispose() {
		menuScreen.dispose();
		manager.clear();
		manager.dispose();
	}
}
