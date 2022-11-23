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
        LinkedListItem next = item.getNext();

        do {
            if(item.getNode().getID() == itemID){
                System.out.println(item.getNode().getID());
                if(previous == null){ //Si c'est le premier élément
                    this.first = next;
                }

                if(next == null) {
                    if(previous == null){ //Si c'est le seul élément
                        this.first = null;
                        this.last = null;
                    }else{ //Si c'est le dernier élément
                        previous.setNext(null);
                        this.last = previous;
                    }
                }
                return item.getNode();
            }
            if(item.getNext() == null){
                break;
            }else{
                item = next;
                previous = item;
                next = next.getNext();
            }
        } while(item.getNode().getID() != itemID);
        return null;
    }

    // public Node getNodeByID(int ID){
    //     LinkedListItem item = this.first;

    //     do {
    //         if(item.getNode().getID() == ID){
    //             return item.getNode();
    //         }else{
    //             item = item.getNext();
    //         }
    //     } while(item.getNext() != null);

    //     return null;
    // }
}
