package com.delplanquerideau;

/**
 * Class représentant un noeud dans un 2d-arbres
 */
public class Node {
    private CuttingDirection cuttingDirection;
    private Colors color;
    private int startX;
    private int endX;
    private int startY;
    private int endY;
    private Node leftNode;
    private Node rightNode;

    /**
     * Noeud de l'arbre
     * @param cuttingDirection
     * @param color
     * @param leftNode
     * @param rightNode
     */
    public Node(CuttingDirection cuttingDirection, Colors color, int startX, int endX, int startY, int endY, Node leftNode, Node rightNode){
        this.cuttingDirection = cuttingDirection;
        this.color = color;
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    /**
     * Feuille de l'arbre
     * @param cuttingDirection
     * @param color
     */
    public Node(CuttingDirection cuttingDirection, Colors color, int startX, int endX, int startY, int endY){
        this.cuttingDirection = cuttingDirection;
        this.color = color;
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
        this.leftNode = null;
        this.rightNode = null;
    }

    public CuttingDirection getCuttingDirection(){
        return this.cuttingDirection;
    }

    public Colors getColor(){
        return this.color;
    }

    public Node getRightNode(){
        return rightNode;
    }

    public Node getLeftNode(){
        return leftNode;
    }
    
    /**
     * Calcul le poids d'une feuille
     * @param w la largeur
     * @param h la hauteur
     * @return
     */
    public double weight(){
        int w = this.endX - this.startX;
        int h = this.endY - this.startY;
        
        return (w*h)/(w+h)*1.5*1.5;
    }
}
