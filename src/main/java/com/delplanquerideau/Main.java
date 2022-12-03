package com.delplanquerideau;

import java.util.Random; //Random

public class Main {

    public static void main(String[] args) {
        if(args.length != 9){
            throw new IllegalArgumentException("\n\nVeuillez rentrer les 9 parametres : \n strategy, largeur, hauteur, seed, nbFeuilles, \n minDimensionCoupe, proportionCoupe(double), memeCouleurProb(double), largeurLigne\n");
		}

        /**
         * Liste des différents paramètres que l'utilisateur peut modifier
         */
        int strategy = Integer.parseInt(args[0]); // La stratégie de génération d’arbre
        int largeur = Integer.parseInt(args[1]); // La largeur de l'image
        int hauteur = Integer.parseInt(args[2]); // La hauteur de l'image
        long seed = Long.parseLong(args[3]); // La graine aléatoire utilisée pour générer l’arbre
        int nbFeuilles = Integer.parseInt(args[4]); // Le nombre de feuilles maximum de l’arbre
        double proportionCoupe = Double.parseDouble(args[5]); // La valeur qui permet de ne pas découper trop proche des bords de la région
        int minDimensionCoupe = Integer.parseInt(args[6]); // La taille minimum des dimensions d’une région
        double memeCouleurProb = Double.parseDouble(args[7]); // La probabilité de garder la couleur du parent
        int largeurLigne = Integer.parseInt(args[8]); // La largeur (en pixels) de la ligne qui sépare les régions




		// int strategy = 1;
        // int largeur = 1000;
        // int hauteur = 1000;
        // long seed = 247;
        // int nbFeuilles = 10;
        // double proportionCoupe = 0.3;
        // int minDimensionCoupe = 10;
        // double memeCouleurProb = 0.1;
        // int largeurLigne = 8;




        GenerateTree GT = new GenerateTree();
		System.out.println("strategy : " + strategy);
		System.out.println("largeur : " + largeur);
		System.out.println("hauteur : " + hauteur);
		System.out.println("seed : " + seed);
		System.out.println("nbFeuilles :" + nbFeuilles);
		System.out.println("proportionCoupe : " + proportionCoupe);
		System.out.println("minDimensionCoupe : " + minDimensionCoupe);
		System.out.println("largeurLigne : " + largeurLigne);

        if (strategy == 0){
            TwoDimensionalTree TDT = GT.generateRandomTree(largeur, hauteur, nbFeuilles, proportionCoupe, minDimensionCoupe, memeCouleurProb, largeurLigne, seed);
            TDT.toImage();
			// System.out.println(TDT);
        }else if(strategy == 1){
            Random r = new Random();
            seed = r.nextLong();
            TwoDimensionalTree TDT = GT.generateRandomTree(largeur, hauteur, nbFeuilles, proportionCoupe, minDimensionCoupe, memeCouleurProb, largeurLigne, seed);
            TDT.toImage();
			// System.out.println(TDT);
        }else if(strategy == 1){
            TwoDimensionalTree TDT = GT.generateBetterRandomTree();
            TDT.toImage();
        }
    }
}