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
    private boolean isALeaf = false;

    private double weight;

    /**
     * Constructeur d'un noeud dans un arbre
     * @param cuttingDirection direction de coupe
     * @param color couleur du noeud
     * @param startX pixel de départ en X
     * @param endX pixel de fin en X
     * @param startY pixel de départ en Y
     * @param endY pixel de fin en Y
     * @param leftNode noeud enfant à gauche
     * @param rightNode noeud enfant à droite
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
        if(this.leftNode == null && this.rightNode == null){
            this.isALeaf = true;
        }
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

    public int getHeight(){
        return endY-startY;
    }

    public int getWidth(){
        return endX-startX;
    }
    
    /**
     * Calcul le poids d'une feuille
     * @param w la largeur
     * @param h la hauteur
     * @return le poids (double)
     */
    public double weight(){
        int w = this.endX - this.startX;
        int h = this.endY - this.startY;
        
        this.weight = (w*h)/((w+h)*1.5*1.5);
        return this.weight;
    }
}
