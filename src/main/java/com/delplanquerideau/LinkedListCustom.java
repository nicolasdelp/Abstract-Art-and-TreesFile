package com.delplanquerideau;

/**
 * Class représentant une liste chainée
 */
public class LinkedListCustom {

    private LinkedListItem first;
    private LinkedListItem last;
	private int size = 0;

    public LinkedListCustom(){}

	public int getSize(){
		return size;
	}

    public LinkedListItem getFirst() {
        return this.first;
    }
    
    /**
     * Indique si la liste chainée est vide ou non
     * @return true ou false
     */
	public boolean isEmpty(){
		return first==null;
	}

    /**
     * Ajoute un noeud à la site chainée
     * @param node Un noeud
     */
    public void add(Node node) {
        LinkedListItem item = new LinkedListItem(node);
        if(this.first == null){ // S'il n'y a rien dans la liste
            this.first = item;
            this.last = item;
        }else{
            this.last.setNext(item);
            this.last = item;
        }
		size++;
    }

    /**
     * Supprime un noeud de la site chainée
     * @param node Un noeud
     */
    public Node remove(Node n){
		int itemID = n.getID();
        LinkedListItem previous = null;
        LinkedListItem item = this.first;

        while(item != null) {
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

		        size--;
                return item.getNode();
            }

            previous = item;
            item = item.getNext();
        }

        return null;
    }

    /**
     * Représentation d'une liste chainée sous forme de texte
     */
    @Override
    public String toString() {
        LinkedListItem item = this.first;
        String res = "";
        while (item != null){
            res += " " + item.getNode().getID();
            item = item.getNext();
        }

        return res;
    }
}
