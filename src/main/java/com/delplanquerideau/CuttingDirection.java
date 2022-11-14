package com.delplanquerideau;

public class CuttingDirection{
    private Direction direction;
    private int x;
    private int y;

    public CuttingDirection(Direction direction, int x, int y){
        this.direction = direction;
        this.x = x;
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
