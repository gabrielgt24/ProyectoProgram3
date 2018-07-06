package com.Proyecto.game.Factory;

import com.Proyecto.game.Colors.Blue;
import com.Proyecto.game.Colors.Brown;
import com.Proyecto.game.Colors.Gray;
import com.Proyecto.game.Colors.Green;
import com.Proyecto.game.Colors.IColors;
import com.Proyecto.game.Colors.Orange;
import com.Proyecto.game.Colors.Pink;
import com.Proyecto.game.Colors.Purple;
import com.Proyecto.game.Colors.Red;



public class FactoryC {

    public IColors Fac(int x){
        switch (x){
            case 1:return new Blue();
            case 2:return new Green();
            case 3:return new Purple();
            case 4:return new Orange();
            case 5:return new Red();
            case 6:return new Brown();
            case 7:return new Gray();
            case 8:return new Pink();
            default: return null;
        }
    }
}

