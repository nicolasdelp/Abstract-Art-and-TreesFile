package com.delplanquerideau;

/**
 * Class représentant un noeud dans un 2d-arbres
 */
public class Node {
    private CuttingDirection cuttingDirection;
    private Colors color;
    private Node leftNode;
    private Node rightNode;

    /**
     * Noeud de l'arbre
     * @param cuttingDirection
     * @param color
     * @param leftNode
     * @param rightNode
     */
    public Node(CuttingDirection cuttingDirection, Colors color, Node leftNode, Node rightNode){
        this.cuttingDirection = cuttingDirection;
        this.color = color;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    /**
     * Feuille de l'arbre
     * @param cuttingDirection
     * @param color
     */
    public Node(CuttingDirection cuttingDirection, Colors color){
        this.cuttingDirection = cuttingDirection;
        this.color = color;
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
}
