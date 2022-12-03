package com.delplanquerideau;

import java.awt.*;

/**
 * Class représentant un noeud
 */
public class Node {

    private static int ID = 1;

    private int nodeID;
    private CuttingDirection cuttingDirection;
    private Color color;

    private int startX;
    private int endX;
    private int startY;
    private int endY;

    private Node leftNode;
    private Node rightNode;

    private int AVLheight;
	public Node AVLLeft;
	public Node AVLRight;

	private int height;
	private int width;
    private double weight;

    public Node(Color color, int startX, int endX, int startY, int endY){
        this.nodeID = ID++;
        this.color = color;
		if(startX == Double.NaN || endX == Double.NaN || startY == Double.NaN || endY == Double.NaN ) throw new Error("NaN");
		if(startX >= endX) throw new Error("Negative width");	//include Nan case
        this.startX = startX;
        this.endX = endX;
		if(startY >= endY) throw new Error("Negative height");	//include Nan case
        this.startY = startY;
        this.endY = endY;

		this.AVLLeft = null;
		this.AVLRight = null;
		this.weight = calculateWeight();
		if (Double.isNaN(weight)) throw new Error("error : void surface");
    }

    public int getID() {
        return this.nodeID;
    }

    public void setID(int id) {
        this.nodeID = id;
    }

    public CuttingDirection getCuttingDirection(){
        return this.cuttingDirection;
    }

    public void setCut(CuttingDirection cuttingDirection) {
        this.cuttingDirection = cuttingDirection;
    }

    public Color getColor(){
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getStartX() {
        return this.startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getEndX() {
        return this.endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getStartY() {
        return this.startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndY() {
        return this.endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public Node getRightNode(){
        return this.rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public Node getLeftNode(){
        return this.leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public int getAVLHeight(){
        return this.AVLheight;
    }

    public void setAVLHeight(int AVLheight){
        this.AVLheight = AVLheight;
    }

	public Node getAVLLeft() {
		return AVLLeft;
	}

	public void setAVLLeft(Node AVLLeft) {
		this.AVLLeft = AVLLeft;
	}

	public Node getAVLRight() {
		return AVLRight;
	}

	public void setAVLRight(Node AVLRight) {
		this.AVLRight = AVLRight;
	}

    /**
     * Donne la hauteur du rectangle
     * @return la hauteur du rectangle
     */
    public int height(){
		height = this.endY-this.startY;
        return height;
    }

    /**
     * Donne la largeur du rectangle
     * @return la largeur du rectangle
     */
    public int width(){
    	width = this.endX-this.startX;
		return width;
    }
    
    /**
     * Calcul le poids du noeud
     * @param w la largeur
     * @param h la hauteur
     * @return le poids du noeud
     */
    public double getWeight(){
        return this.weight;
    }

	public void setWeight(double weight){	//used to differentiate equal weights
        this.weight = weight;
    }

	private double calculateWeight(){
        int w = this.endX - this.startX;
        int h = this.endY - this.startY;
        
        this.weight = (w*h)/(Math.pow((w+h), 1.5));
        return this.weight;
    }

	public boolean isLeaf(){
		return (leftNode ==null) && (rightNode==null);
	}

    /**
     * Représentation un noeud sous forme de texte
     */
    // @Override
	public String toString(){
		return "(ID : " + this.nodeID + ", H : " + height() + ", L : " + width() + ", Poids : " + Math.floor((weight*100)) + ") ";
	}

    /**
     * Représentation un noeud sous forme de texte numero 2
     */
	public String toStringAVL(){
		return "@" + this.nodeID + "/" + Math.round((weight*100)) + "/" + AVLheight + "/" + this.startX + "/" + this.endX + "/" + this.startY + "/" + this.endY;
	}
}
