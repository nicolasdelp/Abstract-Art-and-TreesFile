package com.delplanquerideau;

import java.awt.*; //Color
import java.util.Random; //Random
import java.util.Scanner;

/**
 * Class représentant une variante du 2d-arbres (TwoDimensionalTree)
 */
public class TwoDimensionalTree {

	private int nbFeuilles;
	private double proportionCoupe;
	private int minDimensionCoupe;
	private double memeCouleurProb;
    private int largeurLigne;

	private Random randomCut;
	private Random randomColor;
	
	private Node root;
    public LinkedListCustom leafs;
    public LinkedListCustom lines;
    private AVL avl;
    private int n = 0;
    
    public TwoDimensionalTree(int largeur, int hauteur, int nbFeuilles, double proportionCoupe, int minDimensionCoupe, double memeCouleurProb, int largeurLigne, long seed){
        this.randomCut = new Random(seed);
		this.randomColor = new Random(seed + 1);

		this.nbFeuilles = nbFeuilles;
		this.proportionCoupe = proportionCoupe;
		this.minDimensionCoupe = minDimensionCoupe;
		this.memeCouleurProb = memeCouleurProb;
        this.largeurLigne = largeurLigne;
		
		this.root = new Node(Color.WHITE, 0, largeur, 0, hauteur);
        this.avl = new AVL(root);
        this.leafs = new LinkedListCustom();
		divide();
		addLeafs();
	}


    /**
     * Divise récussivement la meilleur feuille disponible
     */
	public void divide(){
		
		

		Node parent = null;
		while (!avl.isEmpty() && n < nbFeuilles){ // Si l'AVL est vide ou si on a créé assez de feuille
			

			
			
			//to draw step by step
			// toImage();
			// Scanner stream = new Scanner(System.in);
			// String wait = stream.nextLine();

			parent = chooseLeaf(); // On choisit le noeud a diviser en 2
			chooseDivision(parent, proportionCoupe); // On calcul une coupe pour les nouvelles feuilles

			Node child1;
			Node child2;
            
            // On crée 2 noeuds selon la surface et l'axe de coupe du noeud parent
			// 
			if(parent.getCuttingDirection().isX()){
				// 
				child1 = new Node(chooseColor(parent, memeCouleurProb), parent.getStartX(), parent.getEndX(), parent.getStartY(), parent.getCuttingDirection().getPosition());
				child2 = new Node(chooseColor(parent, memeCouleurProb), parent.getStartX(), parent.getEndX(), parent.getCuttingDirection().getPosition(), parent.getEndY());
			}else{
				// 
				child1 = new Node(chooseColor(parent, memeCouleurProb), parent.getStartX(), parent.getCuttingDirection().getPosition(), parent.getStartY(), parent.getEndY());
				child2 = new Node(chooseColor(parent, memeCouleurProb), parent.getCuttingDirection().getPosition(), parent.getEndX(), parent.getStartY(), parent.getEndY());
			}

			// 

			parent.setLeftNode(child1); // On insere les noeuds dans le 2dtree
			parent.setRightNode(child2); 

            // On insère les noeuds de taille suffisante dans l'AVL
			if(isValid(child1)){
				avl.insert(child1);
			}
			if(isValid(child2)){
                avl.insert(child2);
			}
			avl.remove(parent); // On retire le noeud parent de l'AVL (car il n'est plus diponible pour une coupe)
            n++;

		}
	}

	public void addLeafs(){
		addLeafs_rec(root);
	}

	public void addLeafs_rec(Node node){
		if(node == null) return;
		else if(node.isLeaf()) leafs.add(node);
		else {
			addLeafs_rec(node.getLeftNode());
			addLeafs_rec(node.getRightNode());
		}
	}

    /**
     * Choix du sens de la coupe
     * @param h La hauteur du noeud
     * @param w La largeur du noeud
     * @param proportionCoupe La proportion de coupe
     * @return le sens de coupe
     */
    private Node chooseDivision(Node parent, double proportionCoupe){
        int w = parent.width();
        int h = parent.height();
        double probaX = (double)h/(w+h);	//TODO : note : CHANGED because x/y inversed
        double randomAxis = randomCut.nextDouble();
        int startCut;
        int endCut;
        if(randomAxis <= probaX){ //Axe X
            startCut = (int)(h*proportionCoupe);
            endCut = (int)(h*(1-proportionCoupe));
            int cutPos = parent.getStartY() + startCut + (int)randomCut.nextInt((endCut - startCut)); //Donne une valeur entre startcut et endcut pour la coupe
            
            parent.setCut(new CuttingDirection(Direction.X, cutPos));
            return parent;
        }else { //Axe Y
            startCut = (int)(w*proportionCoupe);
            endCut = (int)(w*(1-proportionCoupe));
			int cutPos = parent.getStartX() + startCut + randomCut.nextInt((endCut - startCut)); //Donne une valeur entre startcut et endcut pour la coupe
            
            parent.setCut(new CuttingDirection(Direction.Y, cutPos));
            return parent;
        }
    }

    /**
     * Choisie la feuille qui va être coupée en deux
     * @return la feuille a couper
     */
	private Node chooseLeaf(){
		return avl.getMax();
	}

    /**
     * Vérifie si un noeud respecte bien les conditions pour être coupé
     * @param n Un noeud
     * @return true ou false
     */
	private boolean isValid(Node n){
		if ((n.getEndX()-n.getStartX() > minDimensionCoupe) && (n.getEndY()-n.getStartY()> minDimensionCoupe)){
			return true;
		}else{
			return false;
		}
	}

    /**
     * Choix de la couleur de la nouvelle feuille créée
     * @param parent Le noeud parent
     * @param memeCouleurProb La probabilité d'avoir la même couleur que le parent
     * @return la couleur
     */
    private Color chooseColor(Node parent, double memeCouleurProb){
        
        int colorProb = 1 + randomColor.nextInt(5);
        double randomDouble = randomColor.nextDouble();
        if(randomDouble > memeCouleurProb){
            switch (colorProb){
                case 1:
                    return Color.RED;
                case 2:
                    return Color.BLUE;
                case 3:
                    return Color.YELLOW;
                case 4:
                    return Color.BLACK;
                case 5:
                    return Color.WHITE;
            }
        }
        
        return parent.getColor();
    }

    /**
     * Génère le tableau au format PNG à partir d'un arbre
     */
    public void toImage(){
		
        Node racine = this.root;
        Image img = new Image(racine.width(), racine.height());
        Draw(img);
        try {
            img.save("tableau.png");
        } catch (Exception e){
            
        }
    }

    /**
     * Dessine le tableau
     * @param img Une image PNG de destination
     */
    public void Draw(Image img){
		LinkedListItem leaf = leafs.getFirst();

        drawSurfaces(img, leaf);
        drawLines(img);
        
    }

	/**
     * Dessines les feuilles de l'arbre
     * @param img Une image PNG de destination
     * @param item La première feuille d'une liste chainée comportant toutes les feuilles
     */
	public void drawSurfaces(Image img, LinkedListItem item){
		while(item != null){
			img.setRectangle(item.getNode().getStartX(), item.getNode().getEndX(), item.getNode().getStartY(), item.getNode().getEndY(), item.getNode().getColor()); //Couleur
            item = item.getNext();
		}
	}

	/**
     * Dessines les lignes de division entre les feuilles de l'arbre
     * @param img Une image PNG de destination
     */
	public void drawLines(Image img){
        Node racine = this.root;
        drawLinesRec(img, racine);
	}

    /**
     * Dessine les lignes récursivement
     * @param img Une image PNG de destination
     * @param n Un noeud parent représentant une coupe
     */
    private void drawLinesRec(Image img, Node n){
        if(n.isLeaf() || n == null){
            return;
        }else{
            CuttingDirection cut = n.getCuttingDirection();
            int demiLargeurLigne = (int)largeurLigne/2;
            if(cut != null){
                if(cut.isX()){
                    img.setRectangle(n.getStartX(), n.getEndX(), cut.getPosition()-demiLargeurLigne, cut.getPosition()+demiLargeurLigne, Color.GRAY);
                }else{
                    img.setRectangle(cut.getPosition()-demiLargeurLigne, cut.getPosition()+demiLargeurLigne, n.getStartY(), n.getEndY(), Color.GRAY);
                }
            }
            
        }
        drawLinesRec(img, n.getLeftNode());
        drawLinesRec(img, n.getRightNode());
    }



	public String preorder(Node node){
		if(node==null) return "";

		String s = node.toString() + "";

        if(node.getLeftNode() != null){
		    s += " ( L-" + preorder(node.getLeftNode()) + " ) ";
        }else{
            s += " ( L-null ) ";
        }
        if(node.getRightNode() != null){
		    s += " ( R-" + preorder(node.getRightNode()) + " ) ";
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
