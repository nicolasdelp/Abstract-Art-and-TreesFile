package com.delplanquerideau;

/**
 * Class représentant la direction et les cordonnée du milieu de la coupe
 */
public class CuttingDirection{
    private Direction direction; // Axe de découpe
    private int position; // TODO

    /**
     * Constructeur d'une coupe
     * @param direction
     * @param position
     */
    public CuttingDirection(Direction direction, int position){
        this.direction = direction;
        this.position = position;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public int getPosition() {
        return this.position;
    }
    
    @Override
    public String toString() {
        String res = "" + this.position;
        return res;
    }
}
