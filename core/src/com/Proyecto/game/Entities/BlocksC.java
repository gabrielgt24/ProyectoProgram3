package com.Proyecto.game.Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.Proyecto.game.Constans;

public class BlocksC extends Actor{
    private TextureRegion textureRegion;
    public boolean touch;


    public BlocksC(TextureRegion textureRegion, Vector2 posicion, Stage escenario){
        this.textureRegion=textureRegion;
        setX(posicion.x);
        setY(posicion.y);
        setSize(Constans.PIXEL_IN_METER,Constans.PIXEL_IN_METER);
        touch=false;
        escenario.addActor(this);
    }

    public void setTextureRegion(TextureRegion textureRegion){
        this.textureRegion=textureRegion;
    }

    public void draw(Batch batch, float parentAlpha) {
        setPosition(getX(),getY());
        batch.draw(textureRegion,getX(),getY(),getWidth(),getHeight());

    }

    public void detach(){
        this.remove();
    }
}
