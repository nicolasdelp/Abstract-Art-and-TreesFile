package com.delplanquerideau;

/**
 * Class représentant la direction et les cordonnée du milieu de la coupe
 */
public class CuttingDirection{
    private Direction direction;
    private int x;
    private int y;

    /**
     * Constructeur d'une coupe
     * @param direction
     * @param x
     * @param y
     */
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
