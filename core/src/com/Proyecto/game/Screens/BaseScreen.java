package com.Proyecto.game.Screens;

import com.badlogic.gdx.Screen;
import com.Proyecto.game.proyectgamemain;

public abstract class BaseScreen implements Screen {

    protected proyectgamemain juego;

    public BaseScreen(proyectgamemain juego){
        this.juego=juego;
    }
}