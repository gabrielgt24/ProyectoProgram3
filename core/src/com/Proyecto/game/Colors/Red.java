package com.Proyecto.game.Colors;

public class Red implements IColors {
    private int x=205;
    private int y=69;
    private int Width=36;
    private int Height=34;

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return Width;
    }

    @Override
    public int getHeight() {
        return Height;
    }
}
