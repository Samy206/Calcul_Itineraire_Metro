package uvsq.algo.graphique;

import javax.swing.*;

/*
Workflow :
1) Une Jframe s'ouvre avec un panel
2) La ligne de métro s'affiche
3) Une boîte de dialogue demande à l'utilisateur de choisir sa station de départ
4) On attend que le choix de l'utilisateur
5) On enregsitre dans une variable la station de départ
6) Une boîte de dialogue demande à l'utilisateur de choisir sa station d'arrivée
7) On attend que le choix de l'utilisateur
8) On enregsitre dans une variable la station d'arrivée
9) On calcule l'itinéraire le plus court
10) On l'affiche dans une boîte de dialogue
 */

public class MapInterface {
    public static void main(String[] args) {
        JFrame f=new JFrame();//creating instance of JFrame
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MetroGraphe g = new MetroGraphe();
        f.add(g);
        f.setSize(800,800);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
    }
}

