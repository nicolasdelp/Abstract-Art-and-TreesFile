package com.delplanquerideau;

/**
 * Class représentant la direction et la position de la coupe
 */
public class CuttingDirection{
    private Direction direction; //Axe de coupe
    private int position; //Position de coupe

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
}
