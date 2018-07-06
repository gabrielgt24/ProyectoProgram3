package com.Proyecto.game.Processor;

import com.badlogic.gdx.InputAdapter;
import com.Proyecto.game.Colors.IColors;
import com.Proyecto.game.Entities.Board;
import com.Proyecto.game.Factory.FactoryC;


import java.util.Random;

public class Gprocessor extends InputAdapter{
    private Board tablero;
    private int x=0;


    public void setTablero(Board tablero){
        this.tablero=tablero;
    }


    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Random r=new Random();
        FactoryC fc=new FactoryC();
        int y=(int) (Math.random() * 6) + 1;
        while(x==y) y=(int) (Math.random() * 6) + 1;
        x=y;
        IColors c=fc.Fac(y);
        tablero.setColor(c);
        tablero.revisiontouchdown();
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        tablero.revisiontouchup();
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        tablero.revisiodragged();
        return true;
    }
}
