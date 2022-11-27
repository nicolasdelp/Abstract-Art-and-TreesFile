package com.delplanquerideau;

import java.awt.*; //Color
import java.util.Random; //Random

/**
 * Class représentant une variante du 2d-arbres (TwoDimensionalTree)
 */
public class TwoDimensionalTree {

    public LinkedList drawTree;
    private int largeurLigne;
    private int actualNbFeuilles = 0;

    private AVL leafs;

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
        Node racine = new Node(chooseDivision(hauteur, largeur, proportionCoupe), Color.WHITE, 0,largeur, 0, hauteur);
        this.leafs = new AVL(racine); //Ajout de la racine aux feuilles
        this.drawTree = new LinkedList(racine); //Arbre pour le dessin
        this.actualNbFeuilles++;

        boolean isFinish = false;
        Node leaf = null;

        while(!isFinish){
            leaf = chooseLeaf(minDimensionCoupe); //On cherche la feuille a diviser si elle existe sinon = null

            if(leaf != null) { //Si on a trouvé une feuille respecatant les conditions
                this.leafs.setRacine(this.leafs.delete(this.leafs.getRacine(), leaf)); //On retire le noeud des feuilles

                CuttingDirection cut = chooseDivision(leaf.getHeight(), leaf.getWidth(), proportionCoupe); //On calcul une coupe pour les nouvelles feuilles
                Node node1;
                Node node2;

                if(cut.getDirection() == Direction.X){
                    node1 = new Node(cut, chooseColor(leaf, memeCouleurProb), leaf.getStartX(),leaf.getEndX(), leaf.getStartY(), cut.getPosition());
                    node2 = new Node(cut, chooseColor(leaf, memeCouleurProb), leaf.getStartX(),leaf.getEndX(), cut.getPosition(), leaf.getEndY());
                }else{
                    node1 = new Node(cut, chooseColor(leaf, memeCouleurProb), leaf.getStartX(),cut.getPosition(), leaf.getStartY(), leaf.getEndY());
                    node2 = new Node(cut, chooseColor(leaf, memeCouleurProb), cut.getPosition(),leaf.getEndX(), leaf.getStartY(), leaf.getEndY());
                }
                leaf.setLeftNode(node1);
                leaf.setRightNode(node2);

                this.leafs.setRacine(this.leafs.insert(this.leafs.getRacine(), leaf.getLeftNode()));
                this.leafs.setRacine(this.leafs.insert(this.leafs.getRacine(), leaf.getRightNode()));
                this.drawTree.add(leaf.getLeftNode());
                this.drawTree.add(leaf.getRightNode());
                this.actualNbFeuilles++; //On supprime 1 feuille pour en rajouter 2 donc +1

                // System.out.println("Noeud gauche \n" + "Start x : " + drawTree.getLeftNode().getStartX() + "\nEnd x : " + drawTree.getLeftNode().getEndX()
                //         + "\nStart y : " + drawTree.getLeftNode().getStartY() + "\nEnd y : " + drawTree.getLeftNode().getEndY()
                //         + "\nCut Direction : " + drawTree.getLeftNode().getCuttingDirection().getDirection()
                //         + "\nCut Position : " + drawTree.getLeftNode().getCuttingDirection().getPosition());
                // System.out.println("Noeud droit \n" + "Start x : " + drawTree.getRightNode().getStartX() + "\nEnd x : " + drawTree.getRightNode().getEndX()
                //         + "\nStart y : " + drawTree.getRightNode().getStartY() + "\nEnd y : " + drawTree.getRightNode().getEndY()
                //         + "\nCut Direction : " + drawTree.getRightNode().getCuttingDirection().getDirection()
                //         + "\nCut Position : " + drawTree.getRightNode().getCuttingDirection().getPosition());
            }else{
                isFinish = true;
            }
            if(this.actualNbFeuilles == nbFeuilles){
                isFinish = true;
            }
        }
    }

    /**
     * Parcours de la linkedlist pour trouver la feuille a diviser
     * @return la feuille à diviser
     */
    private Node chooseLeaf(int minDimensionCoupe){
        return this.leafs.getMax(this.leafs.getRacine());
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

    public void toCMD(LinkedListItem drawTree){
        if(drawTree != null){
            System.out.println("Noeud N°" + drawTree.getNode().getID() + " Coleur : " + drawTree.getNode().getColor() + " Hauteur :" + drawTree.getNode().getHeight() + " Largeur :" + drawTree.getNode().getWidth());
            toCMD(drawTree.getNext());
        }
    }

    /**
     * Génère le tableau au format PNG à partir d'un arbre
     */
    public void toImage(){
        LinkedListItem racine = this.drawTree.getFirst();
        Image img = new Image(racine.getNode().getWidth(), racine.getNode().getHeight());

        Draw(img, racine);

        try {
            img.save("tableau.png");
        } catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Dessine le tableau
     * @param img
     * @param node
     */
    public void Draw(Image img, LinkedListItem item){
        if(item != null){ // Si le noeud est une feuille
            int demiLargeurLigne = (int)largeurLigne/2;

            img.setRectangle(item.getNode().getStartX(), item.getNode().getEndX(), item.getNode().getStartY(), item.getNode().getEndY(), item.getNode().getColor()); //Couleur

            img.setRectangle(item.getNode().getStartX(), item.getNode().getStartX()+demiLargeurLigne, item.getNode().getStartY(), item.getNode().getEndY(), Color.GRAY); //Ligne côté gauche
            img.setRectangle(item.getNode().getStartX(), item.getNode().getEndX(), item.getNode().getStartY(), item.getNode().getStartY()+demiLargeurLigne, Color.GRAY); //Ligne du haut
            img.setRectangle(item.getNode().getEndX()-demiLargeurLigne, item.getNode().getEndX(), item.getNode().getStartY(), item.getNode().getEndY(), Color.GRAY); //Ligne côté droit
            img.setRectangle(item.getNode().getStartX(), item.getNode().getEndX(), item.getNode().getEndY()-demiLargeurLigne, item.getNode().getEndY(), Color.GRAY); //Ligne du bas
            Draw(img, item.getNext());
        }
    }
}
