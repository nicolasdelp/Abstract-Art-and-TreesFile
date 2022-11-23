package com.delplanquerideau;

import java.awt.*; //Color
import java.util.Random; //Random

/**
 * Class représentant une variante du 2d-arbres (TwoDimensionalTree)
 */
public class TwoDimensionalTree {

    private Node racine;
    private int largeurLigne;
    private int actualNbFeuilles = 0;

    private LinkedList leafs;

    /**
     * Constructeur d'un 2d-arbre
     * @param largeur
     * @param hauteur
     * @param nbFeuilles
     * @param proportionCoupe
     * @param minDimensionCoupe
     * @param memeCouleurProb
     * @param largeurLigne
     */
    public TwoDimensionalTree(int largeur, int hauteur, int nbFeuilles, double proportionCoupe, int minDimensionCoupe, double memeCouleurProb, int largeurLigne, long seed){
        this.largeurLigne = largeurLigne;
        this.racine = new Node(chooseDivision(hauteur, largeur, proportionCoupe), Color.WHITE, 0,largeur, 0, hauteur);
        this.leafs = new LinkedList();
        this.leafs.add(this.racine); //Ajout de la racine aux feuilles
        this.actualNbFeuilles++;

        while(this.actualNbFeuilles < nbFeuilles){
            Node leaf = chooseLeaf(minDimensionCoupe); //On cherche la feuille a diviser si elle existe sinon = null
            //System.out.println(leaf.getID());

            if(leaf != null) { //Si on a trouvé une feuille respecatant les conditions
                System.out.println(this.leafs);
                this.leafs.remove(leaf.getID()); //On retire le noeud des feuilles

                CuttingDirection cut = chooseDivision(leaf.getHeight(), leaf.getWidth(), proportionCoupe); //On calcul une coupe pour les nouvelles feuilles
                if(cut.getDirection().equals(Direction.X)){
                    leaf.setLeftNode(new Node(cut, chooseColor(leaf, memeCouleurProb), leaf.getStartX(),leaf.getEndX(), leaf.getStartY(), cut.getPosition()));
                    leaf.setRightNode(new Node(cut, chooseColor(leaf, memeCouleurProb), leaf.getStartX(),leaf.getEndX(), cut.getPosition(), leaf.getEndY()));
                }else{
                    leaf.setLeftNode(new Node(cut, chooseColor(leaf, memeCouleurProb), leaf.getStartX(),cut.getPosition(), leaf.getStartY(), leaf.getEndY()));
                    leaf.setRightNode(new Node(cut, chooseColor(leaf, memeCouleurProb), cut.getPosition(),leaf.getEndX(), leaf.getStartY(), leaf.getEndY()));
                }

                this.leafs.add(leaf.getLeftNode());
                this.leafs.add(leaf.getRightNode());
                this.actualNbFeuilles++; //On supprime 1 feuille pour en rajouter 2 donc +1
            }else{
                break;
            }
        }
    }

    /**
     * Parcours de la linkedlist pour trouver la feuille a diviser
     * @return la feuille à diviser
     */
    private Node chooseLeaf(int minDimensionCoupe){
        LinkedListItem item = this.leafs.getFirst();
        double bestWeight = -1;
        Node resItem = null;

        do {
            if(item.getNode().weight() > bestWeight){
                if((item.getNode().getHeight() > minDimensionCoupe) && (item.getNode().getWidth() > minDimensionCoupe)){
                    bestWeight = item.getNode().weight();
                    resItem = item.getNode();
                }
            }
        }while((item = item.getNext()) != null);


        return resItem;
    }

    /**
     * Choix du sens de la coupe
     * @return le sens de coupe
     */
    private CuttingDirection chooseDivision(int h, int w, double proportionCoupe){
        double probaX = (double)w/(w+h);
        Random r = new Random();
        double randomDouble = r.nextDouble();
        int startCut;
        int endCut;

        if(randomDouble < probaX){ //Axe X
            startCut = (int)(w*proportionCoupe);
            endCut = (int)(w*(1-proportionCoupe));
            
            int cut = r.nextInt((endCut - startCut) + 1) + startCut; //Donne une valeur entre startcut et endcut pour la coupe
            
            return new CuttingDirection(Direction.X, cut);
        }else { //Axe Y
            startCut = (int)(h*proportionCoupe);
            endCut = (int)(h*(1-proportionCoupe));

            int cut = r.nextInt((endCut - startCut) + 1) + startCut; //Donne une valeur entre startcut et endcut pour la coupe

            return new CuttingDirection(Direction.Y, cut);
        }
    }

    /**
     * Choix de la couleur de la nouvelle feuille créée
     * @return la couleur
     */
    private Color chooseColor(Node parent, double memeCouleurProb){
        Random r = new Random();
        int colorProb = 1 + (int)(Math.random() * ((5 - 1) + 1));
        double randomDouble = r.nextDouble();
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
        Image img = new Image(this.racine.getWidth(), this.racine.getHeight());
        img.setRectangle(this.racine.getStartX(), this.racine.getEndX(), this.racine.getStartY(), this.racine.getEndY(), this.racine.getColor()); // On dessine la racine (un tableau blanc)


        DrawLeaf(img, this.racine);
        DrawNode(img, this.racine);

        try {
            img.save("tableau.png");
        } catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Dessine les lignes grises
     * @param img
     * @param node
     */
    public void DrawNode(Image img, Node node){
        if(!node.getIsALeaf()) { // Si le noeud n'est pas une feuille
            int position = node.getCuttingDirection().getPosition();

            if (node.getCuttingDirection().getDirection().equals(Direction.X)) {
                img.setRectangle(node.getStartX(), node.getEndX(), position-(largeurLigne/2), position+(largeurLigne/2), Color.GRAY);
            } else {
                img.setRectangle(position-(largeurLigne/2), position+(largeurLigne/2), node.getStartY(), node.getEndY(), Color.GRAY);
            }

            DrawNode(img, node.getRightNode());
            DrawNode(img, node.getLeftNode());
        }
    }

    /**
     * Dessine les rectangles de couleurs
     * @param img
     * @param node
     */
    public void DrawLeaf(Image img, Node node){
        if(node.getIsALeaf()){ // Si le noeud est une feuille
            img.setRectangle(node.getStartX(), node.getEndX(), node.getStartY(), node.getEndY(), node.getColor());
        }else{
            DrawLeaf(img, node.getLeftNode());
            DrawLeaf(img, node.getRightNode());
        }
    }
}
