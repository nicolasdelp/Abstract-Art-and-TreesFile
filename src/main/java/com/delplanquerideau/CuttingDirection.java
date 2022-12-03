package com.delplanquerideau;

/**
 * Class représentant la direction et la position d'une coupe
 */
public class CuttingDirection{
    private Direction direction;
    private int position;

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

	public String toString(){
		return direction + " " + position;
	}

	public boolean isX(){
		return (direction == Direction.X);
	}
	public boolean isY(){
		return (direction == Direction.Y);
	}
}
