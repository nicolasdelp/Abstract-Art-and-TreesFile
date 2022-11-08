package com.delplanquerideau;
public class Main {

    /**
     * Liste des différents paramètres que l'utilisateur peut modifier
     */
    private static int strategy = 0; // La stratégie de génération d’arbre
    private static int largeur = 0; // La largeur de l'image
    private static int hauteur = 0; // La hauteur de l'image
    private static int nbFeuilles = 0; // Le nombre de feuilles maximum de l’arbre
    private static int minDimensionCoupe = 0; // La taille minimum des dimensions d’une région
    private static int proportionCoupe = 0; // La valeur qui permet de ne pas découper trop proche des bords de la région
    private static int memeCouleurProb = 0; // La probabilité de garder la couleur du parent
    private static int largeurLigne = 0; // La largeur (en pixels) de la ligne qui sépare les régions
    private static int seed = 0; // La graine aléatoire utilisée pour générer l’arbre

    public static void main(String[] args) {
        // Appel à la fonction de génération aléatoire de tableau

        System.out.println("Hello world!");
    }
}