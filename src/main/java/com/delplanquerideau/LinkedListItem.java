package com.delplanquerideau;

/**
 * Class représentant une élément d'une liste chainée
 */
public class LinkedListItem{

    private Node node;
    private LinkedListItem next;
    
    public LinkedListItem(Node node){
        this.node = node;
        this.next = null;
    }

    public void setNext(LinkedListItem next) {
        this.next = next;
    }

    public LinkedListItem getNext() {
        return this.next;
    }

    public Node getNode() {
        return this.node;
    }
}
