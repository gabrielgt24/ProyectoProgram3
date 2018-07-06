package com.Proyecto.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.Proyecto.game.Colors.IColors;
import com.Proyecto.game.Constans;


public class Blocks extends Actor{
    private Texture textura;
    private World mundo;
    public Body cuerpo;
    public Fixture fixture;
    private TextureRegion textureRegion;
    boolean up;
    boolean down;
    boolean left;
    boolean right;
    public Sprite s;

    public  Blocks(World mundo, Texture textura, Vector2 posicion, IColors c){
        this.up=false;
        this.down=false;
        this.left=false;
        this.right=false;
        this.textura=textura;
        this.mundo=mundo;
        textureRegion=new TextureRegion(textura,c.getX(),c.getY(),c.getWidth(),c.getHeight());
        BodyDef def=new BodyDef();
        def.position.set(posicion);
        def.type= BodyDef.BodyType.DynamicBody;
        cuerpo=mundo.createBody(def);
        PolygonShape box=new PolygonShape();
        box.setAsBox(0.5f,0.5f,new Vector2(0,0),0);
        fixture=cuerpo.createFixture(box,1);
        s = new Sprite(textureRegion);
        s.setSize(Constans.PIXEL_IN_METER,Constans.PIXEL_IN_METER);
        s.setPosition((cuerpo.getPosition().x - 0.5f) * Constans.PIXEL_IN_METER, (cuerpo.getPosition().y - 0.5f) * Constans.PIXEL_IN_METER);
        fixture.setUserData(s);
        box.dispose();

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (fixture.getUserData() != null) {
            Sprite sprite = (Sprite) fixture.getUserData();
            sprite.setRotation(cuerpo.getAngle() * MathUtils.radiansToDegrees);
            sprite.setPosition((cuerpo.getPosition().x - 0.5f) * Constans.PIXEL_IN_METER, (cuerpo.getPosition().y - 0.5f) * Constans.PIXEL_IN_METER);
            sprite.draw(batch);
        }

    }
    
    public float getBodyp() {
    	return (cuerpo.getPosition().y);
    }

    public void dispose(){
        cuerpo.destroyFixture(fixture);
        mundo.destroyBody(cuerpo);
        textura.dispose();
    }
}
