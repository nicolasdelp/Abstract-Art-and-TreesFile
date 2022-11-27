package com.delplanquerideau;

public class AVL {

    private Node racine;

    public AVL(Node n){
        this.racine = n;
    }

    public Node getRacine() {
        return this.racine;
    }

    public void setRacine(Node racine) {
        this.racine = racine;
    }

    public int getNodeHeight(Node n){
        if(n == null){
            return -1;
        }
        return n.getAVLHeight();
    }

    public void updateNodeHeight(Node n){
        n.setAVLHeight(Math.max(getNodeHeight(n.getLeftNode()), getNodeHeight(n.getRightNode())) + 1); //On prend le max des deux sous-arbres et on ajoute 1
    }

    public int getNodeBalance(Node n){
        if(n == null){
            return 0;
        }
        return getNodeHeight(n.getRightNode()) - getNodeHeight(n.getLeftNode()); // Différence entre la hauteur des deux sous-arbres
    }

    public Node rightRotation(Node n){
        Node a = n.getLeftNode();
        Node b = a.getRightNode();
        a.setRightNode(n);
        n.setLeftNode(b);
        updateNodeHeight(n);
        updateNodeHeight(a);
        return a;
    }

    public Node leftRotation(Node n){
        Node a = n.getRightNode();
        Node b = a.getLeftNode();
        a.setLeftNode(n);
        n.setRightNode(b);
        updateNodeHeight(n);
        updateNodeHeight(a);
        return a;
    }

    public Node balance(Node n){
        updateNodeHeight(n);

        if(getNodeBalance(n) > 1){
            if(getNodeHeight(n.getRightNode().getRightNode()) > getNodeHeight(n.getRightNode().getLeftNode())){
                n = leftRotation(n);
            }else {
                n.setRightNode(rightRotation(n.getRightNode()));
                n = leftRotation(n);
            }
        }else if(getNodeBalance(n) < -1){
            if(getNodeHeight(n.getLeftNode().getLeftNode()) > getNodeHeight(n.getLeftNode().getRightNode())){
                n = rightRotation(n);
            }else {
                n.setLeftNode(leftRotation(n.getLeftNode()));
                n = rightRotation(n);
            }
        }
        return n;
    }

    public Node insert(Node racine, Node n){
        if(racine == null){
            return racine;
        }

        if(racine.weight() > n.weight()){
            if(racine.getLeftNode() != null){
                racine.setLeftNode(insert(racine.getLeftNode(), n));
            }else{
                racine.setLeftNode(n);
            }
        }else if(racine.weight() < n.weight()){
            if(racine.getRightNode() != null){
                racine.setRightNode(insert(racine.getRightNode(), n));
            }else{
                racine.setRightNode(n);
            }
        }
        return balance(racine);
    }

    public Node delete(Node racine, Node n){
        if(racine == null){
            return racine;
        }

        while(racine.getID() != n.getID()){
            if(racine.weight() > n.weight()){
                racine = racine.getLeftNode();
            }else if(racine.weight() < n.weight()){
                racine = racine.getRightNode();
            }else{ // Si c'est le noeud a supprimer
                if(racine.getLeftNode() == null){
                    racine = racine.getRightNode();
                }else if(racine.getRightNode() == null){
                    racine = racine.getLeftNode();
                }else{
                    // On copie le noeud à la racine
                    Node minimum = getMin(racine);
                    racine.setCuttingDirection(minimum.getCuttingDirection());
                    racine.setColor(minimum.getColor());
                    racine.setStartX(minimum.getStartX());
                    racine.setEndX(minimum.getEndX());
                    racine.setStartY(minimum.getStartY());
                    racine.setEndY(minimum.getEndX());

                    // On supprime le noeud qu'on vient de copier
                    racine.setRightNode(delete(racine, minimum));
                }
            }
            if(racine != null){
                racine = balance(racine);
            }
        }

        return racine;
    }

    /**
     * Donne le max à partir d'un noeud racine
     * @param racine
     * @return
     */
    public Node getMax(Node racine){
        while(racine.getRightNode() != null){
            racine = racine.getRightNode();
        }

        return racine;
    }

    /**
     * Donne le min à partir d'un noeud racine
     * @param racine
     * @return
     */
    public Node getMin(Node racine){
        while(racine.getLeftNode() != null){
            racine = racine.getLeftNode();
        }

        return racine;
    }

    

    // public String preorder(Node n, String s){
    //     s = s + n.toString();
    //     if(!n.getIsALeaf()){
    //         s = s + "(" + preorder(n.getLeftNode(), s) + ")";
    //         s = s + "(" + preorder(n.getRightNode(), s) + ")";
    //     }
    //     return s;
    // }

    // public Node insert(Node n) {


    //     public Node insert_rec (Node curr, Node toAdd){
    //         if (curr == null) {
    //             return toAdd;    //we get the existing node (from the Kd Tree)
    //         } else if (toAdd.getWeight() <= curr.getWeight()) {    //if equal, insert node as child, choice for next division is left to fate
    //             curr.setLeftNode(insert_rec(curr.getLeftNode(), toAdd));
    //         } else {
    //             curr.setRightNode(insert_rec(curr.getRightNode(), toAdd));
    //         }

    //         updateHeight(n);
    //         rotate(n);
    //         return Node;


    //         // //override
    //         // public Node delete(Node toDel){
    //         // 	delete_rec(curr, root);
    //         // }

    //         // public Node delete_rec(Node curr, Node toDel){	//find method is useless, delete wants to have the parents of the node to delete
    //         // 	if(n==null) return null;
    //         // 	else if(toAdd.getWeight() < curr.getWeight()){	//if equal, delete ? how to know which to delete? is deletion necessary here?
    //         // 		curr.setLeftNode(delete_rec(curr.getLeftNode(), toAdd));
    //         // 	}
    //         // 	else{
    //         // 		curr.setRightNode(delete_rec(curr.getRightNode(), toAdd)) ;
    //         // 	}

    //         // }


    //         public Node getMax () {
    //             Node n = root;
    //             while (!n.isLeaf) {
    //                 n = n.getRightNode;
    //             }
    //             return n;
    //         }

    //         public Node getMin () {
    //             Node n = root;
    //             while (!n.isLeaf) {
    //                 n = n.getLeftNode;
    //             }
    //             return n;
    //         }
    //     }

    //     insert_rec(root, n);
    // }
}
