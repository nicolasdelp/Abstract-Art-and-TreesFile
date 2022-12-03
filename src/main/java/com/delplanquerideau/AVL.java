package com.delplanquerideau;

/**
 * Class représentant un AVL
 */
public class AVL {

    private Node root;
	private int size = 0;

    public AVL(Node root){
        this.root = root;
        this.size++;
    }

	public int getSize(){
		return this.size;
	}

    public Node getRoot() {
        return this.root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * Indique si l'AVL est vide ou non
     * @return true ou false
     */
	public boolean isEmpty(){
		return this.root == null;
	}

    /**
     * Donne la hauteur d'un noeud
     * @param node Un noeud
     * @return la hauteur du noeud
     */
    public int getNodeHeight(Node node){
        return node == null ? -1 : node.getAVLHeight();
    }

    /**
     * Met à jour la hauteur d'un noeud
     * @param node Un noeud
     */
    public void updateNodeHeight(Node node){
        node.setAVLHeight(1 + Math.max(getNodeHeight(node.getAVLLeft()), getNodeHeight(node.getAVLRight())));
    }

    /**
     * Donne la balance d'un noeud
     * @param node Un noeud
     * @return la balance du noeud
     */
    public int getNodeBalance(Node node){
        return (node == null) ? 0 : getNodeHeight(node.getAVLRight()) - getNodeHeight(node.getAVLLeft());
    }

    /**
     * Effectue une rotation à droite d'un AVL
     * @param node Le noeud racine
     * @return la racine après la rotation
     */
    public Node rightRotation(Node node){
        Node a = node.getAVLLeft();
        Node b = a.getAVLRight();

        a.setAVLRight(node);
        node.setAVLLeft(b);
        updateNodeHeight(node);
        updateNodeHeight(a);

        return a;
    }

    /**
     * Effectue une rotation à gauche d'un AVL
     * @param node Le noeud racine
     * @return la racine après la rotation
     */
    public Node leftRotation(Node node){
        Node a = node.getAVLRight();
        Node b = a.getAVLLeft();

        a.setAVLLeft(node);
        node.setAVLRight(b);
        updateNodeHeight(node);
        updateNodeHeight(a);

        return a;
    }

    /**
     * Rééquilibre un AVL
     * @param node Le noeud racine
     * @return un AVL équilibré
     */
    public Node balance(Node node){

        updateNodeHeight(node);

        if(getNodeBalance(node) > 1){
            if(getNodeHeight(node.getAVLRight().getAVLRight()) > getNodeHeight(node.getAVLRight().getAVLLeft())){
                node = leftRotation(node);
            }else {
                node.setAVLRight(rightRotation(node.getAVLRight()));
                node = leftRotation(node);
            }
        }else if(getNodeBalance(node) < -1){
            if(getNodeHeight(node.getAVLLeft().getAVLLeft()) > getNodeHeight(node.getAVLLeft().getAVLRight())){
                node = rightRotation(node);
            }else {
                node.setAVLLeft(leftRotation(node.getAVLLeft()));
                node = rightRotation(node);
            }
        }
        return node;
    }

    /**
     * Insertion d'un noeud dans l'AVL
     * @param n Le noeud a inserer
     * @return La nouvelle root de l'AVL
     */
	public void insert(Node node){
		this.size++;
		setRoot(insertRec(root, node));	//old root may be updated with rotations
	}

    /**
     * Insertion recursive
     * @param parent Le noeud courant
     * @param node Le noeud a inserer
     * @return la nouvelle root de l'AVL
     */
	private Node insertRec(Node parent, Node node){
        if(parent == null){
            return node;
        }else if(parent.getWeight() >= node.getWeight()){
            parent.setAVLLeft(insertRec(parent.getAVLLeft(), node));
        }else if(parent.getWeight() < node.getWeight()){
            parent.setAVLRight(insertRec(parent.getAVLRight(), node));
        }

        return balance(parent);
    }




	/**
     * Supprime un noeud de l'AVL
     * @param node Le noeud a supprimer
     */
	public void remove(Node node){
		size--;
		setRoot(removeRec(root, node));
	}

    
	public Node removeRec(Node parent, Node node){
        if(parent == null){
            return parent;
        }else if(parent.getWeight() > node.getWeight()){
                parent.setAVLLeft(removeRec(parent.getAVLLeft(), node));
        }else if(parent.getWeight() < node.getWeight()){
                parent.setAVLRight(removeRec(parent.getAVLRight(), node));
        }else{ // Si c'est le noeud a supprimer
            if(parent.getAVLLeft() == null || parent.getAVLRight() == null){
                parent = (parent.getAVLLeft() == null) ? parent.getAVLRight() : parent.getAVLLeft();
            }else{
                Node minimum = getMinRec(parent.getAVLRight()); //On prend le minimum à droite

                // On copie le noeud à la racine
                parent.setWeight(minimum.getWeight());
                parent.setCut(minimum.getCuttingDirection());
                parent.setColor(minimum.getColor());
                parent.setStartX(minimum.getStartX());
                parent.setEndX(minimum.getEndX());
                parent.setStartY(minimum.getStartY());
                parent.setEndY(minimum.getEndY());
                parent.setID(minimum.getID());

                // On supprime le noeud qu'on vient de copier
                parent.setAVLRight(removeRec(parent.getAVLRight(), minimum));
            }
        }

        if(parent != null){
            parent = balance(parent);
        }

        return parent;
    }

	
    /**
     * Donne le maximum de l'AVL
     * @return le noeud maximum de l'AVL
     */
	public Node getMax(){
		return getMaxRec(root);
	}

	/**
     * Donne le maximum à partir d'un noeud racine
     * @param node Un noeud racine
     * @return le noeud maximum
     */
    private Node getMaxRec(Node node){
        while(node.getAVLRight() != null){
            node = node.getAVLRight();
        }

        return node;
    }
    
    /**
     * Donne le minimum de l'AVL
     * @return le noeud minimum de l'AVL
     */
	public Node getMin(){
		return getMinRec(root);
	}

	/**
     * Donne le minimum à partir d'un noeud racine
     * @param node Un noeud racine
     * @return le noeud minimum
     */
    private Node getMinRec(Node node){
        while(node.getAVLLeft() != null){
            node = node.getAVLLeft();
        }

        return node;
    }

	/**
     * Parcours preordre à partir d'un noeud racine
     * @param node Un noeud racine
     * @return un String
     */
    public String preorder(Node node){
		if(node==null) return "";

		String s = node.getID() + "";

        if(node.getAVLLeft() != null){
		    s += " ( L-" + preorder(node.getAVLLeft()) + " ) ";
        }else{
            s += " ( L-null ) ";
        }
        if(node.getAVLRight() != null){
		    s += " ( R-" + preorder(node.getAVLRight()) + " ) ";
        }else{
            s += " ( R-null ) ";
        }
    	
        return s;
    }

    /**
     * Représentation d'un AVL sous forme de texte
     */
    @Override
	public String toString(){
		return preorder(root);
	}
}