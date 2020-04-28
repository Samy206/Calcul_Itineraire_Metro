package uvsq.algo.graphique;

import uvsq.algo.*;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MetroGraphe extends JPanel implements MouseListener {

    private int cmp = 0 ;         //compteur du nombre de clic ( chaque deux clics = station de départ choisie + station d'arrivée choisie )
    private String stationDepart ;   //chaine de caractère de départ dans laquelle on stockera le nom de la station contenu dans le bouton sur lequel a appuyé l'utilisateur
    private String stationArrivee ;  //chaine de caractère d'arrivée dans laquelle on stockera le nom de la station contenu dans le bouton sur lequel a appuyé l'utilisateur
    private String itineraire ;    //chaine dans laquelle on stockera l'itinéraire final
    Graphe graph ;     //graphe correspondant à la modelisation du fichier "metro.txt"
    JButton [] boutons = new JButton[100]; //affichage de boutons sur l'interface dans la limite de 100 boutons ( chaque bouton contient le nom d'une station )


    public MetroGraphe() { //le constructeur MetroGraph ajoute sur une fenêtre de taille 1200x800 des boutons correspondant ici aux ( en modélisant le graphe avant )
        setSize(1200, 900);
        graph = Itineraire.buildGraph(); // création du graphe
        addButtons("7","3bis");   // ici la fonction qui ajoute les stations
    }


    public void addButtons(String numLigne1 , String numLigne2) // pour chaque ligne on parcourt tout les liens entre les stations , et s'il existe des liens entre deux stations d'une
            // ligne on crée deux boutons qui correspondent
    {
        int cmpt = 0; // compteur du nombre de boutons crées
        for(Lien s : graph.Links) { //pour tout les liens comme on a dit , on créer deux boutons si les stations correspondantes sont sur cette ligne ( ici première ligne)
            if ((s.Depart.Line.Nom).equals(numLigne1) && (s.Arrivee.Line.Nom).equals(numLigne1)) {
                boutons[cmpt] = new JButton(s.Depart.Nom);
                add(boutons[cmpt]);
                boutons[cmpt].addMouseListener(this); //creation du premier bouton et ajout sur l'interface
                cmpt++;
                boutons[cmpt] = new JButton(s.Arrivee.Nom);
                add(boutons[cmpt]);
                boutons[cmpt].addMouseListener(this);
                cmpt++;                                 //creation du deuxième bouton et ajout sur l'interface
            }
        }

        boutons[cmpt] = new JButton("                               séparation                            " + "              affichage entre la ligne               " +numLigne1 +
                "                          et la ligne                                       "+ numLigne2 +"                                                                         ");

        //création d'une séparation entre les deux lignes

        add(boutons[cmpt]);
        cmpt++;

        for(Lien s : graph.Links) {
            if ((s.Depart.Line.Nom).equals(numLigne2) && (s.Arrivee.Line.Nom).equals(numLigne2)) { // pareillement ici pour la deuxième ligne
                boutons[cmpt] = new JButton(s.Depart.Nom);
                add(boutons[cmpt]);
                boutons[cmpt].addMouseListener(this);
                cmpt++;
                boutons[cmpt] = new JButton(s.Arrivee.Nom);
                add(boutons[cmpt]);
                boutons[cmpt].addMouseListener(this);
                cmpt++;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) { // fonction qui définit la réaction d'un bouton lorsqu'on clique dessus :
                                                      // 1) on récupère le nom de la station ( celle du bouton )
                                                      // 2) on compte le nbr de clics , s'il est impaire il s'agit de la station de départ , et s'il est pair c'est la
                                                        // station d'arrivée
                                                        //3) on calcule l'itinéraire avec djisktra
                                                        // 4) on l'affiche dans un message ( nouvelle fenêtre )
        JButton myButton = (JButton)mouseEvent.getSource();
        if((cmp%2 == 0))   // nombre impaire de cliques = station de départ ( stockage de cette dernière ) = attente de la deuxième
        {
            this.stationDepart =  myButton.getText();
            cmp++;
            System.out.println("First button clicked");

        }
        else if (cmp%2 == 1) {  //deuxième clique = stockage de la deuxième station = itinéraire = affichage itinéraire
            this.stationArrivee = myButton.getText();
            cmp++;
            itineraire = Itineraire.getItineraire(stationDepart, stationArrivee);
            System.out.println("Second button clicked ");
            JOptionPane.showMessageDialog(null, itineraire);
            System.out.println(itineraire);
        }

    }
    //a partir de là les fonctions sont inutilisées et ne sont donc pas redéfinies
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
