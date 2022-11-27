package com.delplanquerideau;

public class Main {

    /**
     * Liste des différents paramètres que l'utilisateur peut modifier
     */
    private final static int strategy = 0; // La stratégie de génération d’arbre
    private final static int largeur = 1000; // La largeur de l'image
    private final static int hauteur = 1000; // La hauteur de l'image
    private final static int nbFeuilles = 50; // Le nombre de feuilles maximum de l’arbre
    private final static double proportionCoupe = 0.1; // La valeur qui permet de ne pas découper trop proche des bords de la région
    private final static int minDimensionCoupe = 50; // La taille minimum des dimensions d’une région
    private final static double memeCouleurProb = 0.5; // La probabilité de garder la couleur du parent
    private final static int largeurLigne = 15; // La largeur (en pixels) de la ligne qui sépare les régions
    private final static long seed = 0; // La graine aléatoire utilisée pour générer l’arbre (0 = pas de graine)

    public static void main(String[] args) {
        GenerateTree GT = new GenerateTree();
        if (strategy == 0){
            TwoDimensionalTree TDT = GT.generateRandomTree(largeur, hauteur, nbFeuilles, proportionCoupe, minDimensionCoupe, memeCouleurProb, largeurLigne, seed);
            TDT.toImage();
            TDT.toCMD(TDT.drawTree.getFirst());
        }else if(strategy == 1){
            TwoDimensionalTree TDT = GT.generateBetterRandomTree();
            TDT.toImage();
        }
    }
}