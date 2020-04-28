package uvsq.algo.graphique;

import javax.swing.*;

/*
1) Une Jframe s'ouvre avec un panel
2) Deux lignes de métro s'affichent
3) On attend que le choix de l'utilisateur pour la station de départ ( un premier clic sur un des boutons )
4) On enregsitre dans une variable la station de départ
5) Une boîte de dialogue demande à l'utilisateur de choisir sa station d'arrivée
6) On attend que le choix de l'utilisateur pour la station d'arrivée ( un deuxième clic sur un des boutons )
7) On enregsitre dans une variable la station d'arrivée
8) On calcule l'itinéraire le plus court
9) On l'affiche dans une boîte de dialogue
 */

public class MapInterface {
    public static void main(String[] args) { //ce main calcule l'itinéraire mais avec une interface graphique
        JFrame f=new JFrame();//creation d'une fenêtre
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // cette ligne dit que si on appuie sur la croix rouge de la fenêtre le programme s'arrête
        MetroGraphe g = new MetroGraphe(); // obejt MetroGraphe qui fait quasiment tout ( construction du graphe , calcule itinéraire ,affichage des stations de deux lignes de métro  )
        f.add(g);                           //association de la fenêtre et du reste ( g )
        f.setSize(2000,900);//2000 width and 900 height (grande fenêtre)
        f.setVisible(true);//apparition de la fenêtre mise à vrai
        f.setTitle("RATP"); //titre de la fenêtre RATP
    }
}

