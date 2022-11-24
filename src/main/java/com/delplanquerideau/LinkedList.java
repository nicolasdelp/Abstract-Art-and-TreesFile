package com.delplanquerideau;

public class LinkedList {

    private LinkedListItem first;
    private LinkedListItem last;

    public LinkedList(){}

    public LinkedListItem getFirst() {
        return this.first;
    }

    public void add(Node node) {
        LinkedListItem item = new LinkedListItem(node);
        if(this.first == null){
            this.first = item;
        }else{
            this.last.setNext(item);
        }
        this.last = item;
    }

    public Node remove(int itemID){
        LinkedListItem previous = null;
        LinkedListItem item = this.first;

        do {
            if(item.getNode().getID() == itemID){
                if(item.getNext() == null && previous == null) { //Si c'est le seul élément
                    this.first = null;
                    this.last = null;
                }

                if(item.getNext() != null && previous == null){ //Si c'est le premier élément
                    this.first = item.getNext();
                }

                if(item.getNext() == null && previous != null) { //Si c'est le dernier élément
                    previous.setNext(null);
                    this.last = previous;
                }

                if(item.getNext() != null && previous != null) { //Si c'est un élément au milieu
                    previous.setNext(item.getNext());
                }

                return item.getNode();
            }

            previous = item;
            item = item.getNext();
        } while(item != null);
        return null;
    }

    @Override
    public String toString() {
        LinkedListItem item = this.first;
        String res = "";
        while (item != null){
            res = res + " " + item.getNode().getID();
            item = item.getNext();
        }
        return res;
    }
}
