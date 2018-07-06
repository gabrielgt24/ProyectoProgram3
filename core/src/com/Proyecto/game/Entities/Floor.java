package com.Proyecto.game.Entities;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.Proyecto.game.Colors.IColors;
import com.Proyecto.game.Factory.FactoryC;
import com.Proyecto.game.Constans;

public class Floor extends Actor{
    private Texture textura;
    private World mundo;
    private Body cuerpo;
    private Fixture fixture;
    private TextureRegion textureRegion;


    public  Floor(World mundo, Texture textura, Vector2 posicion){
        this.textura=textura;
        this.mundo=mundo;
        BodyDef def=new BodyDef();
        def.position.set(posicion);
        def.type= BodyDef.BodyType.StaticBody;
        cuerpo=mundo.createBody(def);
        PolygonShape box=new PolygonShape();
        box.setAsBox(4f,1f);
        fixture=cuerpo.createFixture(box,1);
        setSize(8*Constans.PIXEL_IN_METER,2*Constans.PIXEL_IN_METER);
        box.dispose();
        FactoryC fc=new FactoryC();
        IColors c= fc.Fac(8);
        textureRegion=new TextureRegion(textura,c.getX(),c.getY(),c.getWidth(),c.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((cuerpo.getPosition().x-4)*Constans.PIXEL_IN_METER,(cuerpo.getPosition().y-1)*Constans.PIXEL_IN_METER);
        batch.draw(textureRegion,getX(),getY(),getWidth(),getHeight());
    }

    public void detach(){
        cuerpo.destroyFixture(fixture);
        mundo.destroyBody(cuerpo);

    }
    
    public float sueloBodyY() {
    	return cuerpo.getPosition().y;
    }
}
