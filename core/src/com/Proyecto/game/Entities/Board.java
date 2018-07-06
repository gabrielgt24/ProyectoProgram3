package com.Proyecto.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.Proyecto.game.Constans;
import com.Proyecto.game.Factory.FactoryC;
import com.Proyecto.game.Colors.IColors;

import java.util.ArrayList;

public class Board {
    private Texture textura;
    private TextureRegion textureRegion;
    public ArrayList<BlocksC> tabla=new ArrayList<BlocksC>();
    int deltax=0;
    int deltay=0;
    private World mundo;
    private Stage escenario;
    private FactoryC fc;
    private IColors c;
    private StringBlocks acumulador;
    

    public  Board(Texture textura, Vector2 posicion, Stage escenario, World mundo, StringBlocks acumulador){
        this.mundo=mundo;
        this.textura=textura;
        this.escenario=escenario;        
        fc=new FactoryC();
        c= fc.Fac(7);
        textureRegion=new TextureRegion(textura,c.getX(),c.getY(),c.getWidth(),c.getHeight());
        for(int i=0;i<3;i++){
            for (int j=0;j<5;j++){
                tabla.add(new BlocksC(textureRegion,new Vector2(posicion.x+deltax,posicion.y+deltay),escenario));
                deltax+=Constans.PIXEL_IN_METER+1;
            }
            deltax=0;
            deltay+=Constans.PIXEL_IN_METER+1;
        }
        this.acumulador = acumulador;
    }

    public void setColor(IColors c){
        this.c=c;
    }

    public void revisiontouchdown(){
        for (BlocksC a:tabla){
            if((Gdx.input.getX()>=a.getX())&&(Gdx.input.getX()<=a.getX()+a.getWidth())&&(Gdx.graphics.getHeight()-Gdx.input.getY()>=a.getY())&&(Gdx.graphics.getHeight()-Gdx.input.getY()<=a.getY()+a.getHeight())){
                if(a.touch==false){
                    a.touch=true;
                    a.setTextureRegion(new TextureRegion(textura,c.getX(),c.getY(),c.getWidth(),c.getHeight()));                    
                }
            }
        }
    }

    public void revisiontouchup(){
        crearpieza(textura,escenario,mundo);
        for (BlocksC a:tabla){
            if(a.touch){
                c=fc.Fac(7);
                a.setTextureRegion(new TextureRegion(textura,c.getX(),c.getY(),c.getWidth(),c.getHeight()));
                a.touch=false;
            }
        }
    }

    public void  revisiodragged(){
        for (BlocksC a:tabla){
            if((Gdx.input.getX()>=a.getX())&&(Gdx.input.getX()<=a.getX()+a.getWidth())&&(Gdx.graphics.getHeight()-Gdx.input.getY()>=a.getY())&&(Gdx.graphics.getHeight()-Gdx.input.getY()<=a.getY()+a.getHeight())){
                if(a.touch==false){
                    a.touch=true;
                    a.setTextureRegion(new TextureRegion(textura,c.getX(),c.getY(),c.getWidth(),c.getHeight()));
                }
            }
        }
    }


    private void crearpieza(Texture textura, Stage escenario, World mundo) {
        DistanceJointDef dJd=new DistanceJointDef();
        DistanceJoint dj;
        Blocks b;
        ArrayList<Blocks> p=new ArrayList<Blocks>();
        for (BlocksC a:tabla){
            if(a.touch){            	
                p.add(new Blocks(mundo,textura,new Vector2((a.getX()+0.5f*Constans.PIXEL_IN_METER)/Constans.PIXEL_IN_METER,(a.getY()+0.5f*Constans.PIXEL_IN_METER)/Constans.PIXEL_IN_METER),c));
            }
        }
        for (Blocks a:p){

            b=mate(p,new Vector2(a.s.getX(),a.s.getY()+30),a.cuerpo);
            if(b!=null && !b.down){
                dJd.bodyA=a.cuerpo;
                dJd.bodyB=b.cuerpo;
                dJd.length=1f;
                dJd.frequencyHz=0;
                dJd.collideConnected=true;
                dJd.dampingRatio=0f;
                dj=(DistanceJoint) mundo.createJoint(dJd);
                a.up=true;
                b.down=true;
            }
            b=mate(p,new Vector2(a.s.getX(),a.s.getY()-30),a.cuerpo);
            if(b!=null && !b.up){
                dJd.bodyA=a.cuerpo;
                dJd.bodyB=b.cuerpo;
                dJd.length=1f;
                dJd.frequencyHz=0;
                dJd.collideConnected=true;
                dJd.dampingRatio=0f;
                dj=(DistanceJoint) mundo.createJoint(dJd);
                a.down=true;
                b.up=true;
            }
            b=mate(p,new Vector2(a.s.getX()-30,a.s.getY()),a.cuerpo);
            if(b!=null && !b.right){
                dJd.bodyA=a.cuerpo;
                dJd.bodyB=b.cuerpo;
                dJd.length=1f;
                dJd.frequencyHz=0;
                dJd.collideConnected=true;
                dJd.dampingRatio=0f;
                dj=(DistanceJoint) mundo.createJoint(dJd);
                a.left=true;
                b.right=true;
            }
            b=mate(p,new Vector2(a.s.getX()+30,a.s.getY()),a.cuerpo);
            if(b!=null && !b.left){
                dJd.bodyA=a.cuerpo;
                dJd.bodyB=b.cuerpo;
                dJd.length=1f;
                dJd.frequencyHz=0;
                dJd.collideConnected=true;
                dJd.dampingRatio=0f;
                dj=(DistanceJoint) mundo.createJoint(dJd);
                a.right=true;
                b.left=true;
            }
        }
        for (Blocks a:p){
            escenario.addActor(a);
            acumulador.addBlock(a);
        }

    }

    public Blocks mate(ArrayList<Blocks> p,Vector2 posicion,Body a){
        for (Blocks c:p){
            if((c.s.getX()<=posicion.x)&&(c.s.getX()+c.s.getWidth()>=posicion.x)&&(c.s.getY()<=posicion.y)&&(c.s.getY()+c.s.getHeight()>=posicion.y)&&(c.cuerpo!=a)){
                return c;
            }
        }
        return null;
    }

    public void detach(){
        textura.dispose();
        for(BlocksC b:tabla){
            b.detach();
        }
    }

}
