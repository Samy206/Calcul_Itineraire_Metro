package uvsq.algo;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;


public class Itineraire {
    public static void main(String[] args) {//ce main là n'a pas d'interface graphique , en effet pour exécuter le programme on donne juste à l'exécution ce que nous demandes les Scanner
                                            // ici , et on enregistre les stations de départ et d'arrivée . Puis dans un second temps , on lit fichier "metro.txt" pour construire le
                                            // graphe et les lignes , pour finir on calcule l'itinéraire et on l'affche

        String itineraire ;
        String stationDepart;
        String stationArrivee;
        System.out.print("A quelle station êtes-vous : ");
        System.out.println("");
        Scanner scanf = new Scanner(System.in);
        stationDepart = scanf.nextLine();         //Lecture de station de départ
        System.out.print("A quelle station voulez-vous arriver : ");
        scanf = new Scanner(System.in);
        System.out.println("");  //Les deux premiers objets Scanner nous permettent de récupérer les stations de départ et d'arrivée
        stationArrivee = scanf.nextLine();     //Lecture de station d'arrivee

        System.out.println(getItineraire(stationDepart,stationArrivee)); //construction du graphe , calcule de l'itinéraire ,et affichage du trajet
    }

    public static String getItineraire(String stationdepart , String stationarrivee) // dans cette fonction on construit le graphe , on cherhce l'itinéraire et on renvoie
    {
        Graphe G = buildGraph(); //construction du graphe
        ResultTrajet res = G.getItineraireGraphe(stationdepart,stationarrivee); //on calcule l'itinéraire
        return G.itineraireToString(res); //renvoie de la chaine de caractère contenant les infos du trajet
    }

    public static Graphe buildGraph() //lecture du fichier et construction du graphe , comportement selon la ligne lue détaillé plus bas
    {
        InputStream file = Itineraire.class.getClassLoader().getResourceAsStream("metro.txt"); //le fichier est stocké dans
        // "resources"

        Scanner sc = new Scanner(file);
        Ligne L; //variable qui nous permet d'enregistrer les différentes lignes
        String[] stationsExec; //Tableau qui va récupérer les stations données à l'éxecution
        String curseur; //chaine de caractère qui va enregistrer la ligne où on est
        String Nom_station; //nom de chaque station lue
        String Nom_ligne;  //Nom de chaque ligne lue
        Station S;   //variable qui va nous servir à créer le nombre de station qu'il faut
        Graphe G = new Graphe();    // Graphe du métro de Paris
        List<Ligne> lignes = new ArrayList<Ligne>(); //liste de ligne
        String[] array; //division de la variable curseur en plusieurs String
        int numSommet1;
        int numSommet2, numTemps; //Ensemble des numéros qui vont nous permettre de lire les numéros de chaque liens ( num de station et temps)

        while (sc.hasNextLine())  //Lecture du fichier et création du graphe à partir de ce dernier (tant qu'il y a des choses à lire)
        {
            Nom_station = "";   //initialisation du nom de chaque station et chaque ligne
            Nom_ligne = "";
            curseur = sc.nextLine();

            if (curseur.charAt(0) == '#') //si on lit un # ça ne correspont à rien
                continue;

            else if (curseur.charAt(0) == 'L')//si on lit un 'L' (ligne)
            {
                array = curseur.split("  ", 5); //le array[0] est le L , la séparation est un double espace
                L = new Ligne(array[1]); //Le array1 est le nom de la ligne

                if (Station.stringCompareDirection(array[4], "null") == 0) //on a complété les stations à deux terminus avec un null
                    L.setTerminus(array[2], array[3], null);   //si on lit le null on n'ajoute uniquement les deux premiers terminus

                else
                    L.setTerminus(array[2], array[3], array[4]); //sinon on en ajoute trois

                lignes.add(L); //on ajoute cette nouvelle ligne à la liste de lignes
            } else if (curseur.charAt(0) == 'V') // V = Sommets
            {
                array = curseur.split("  ", 4); //La séparation est un double espace
                numSommet1 = Integer.parseInt(array[1]); //Numéro de sommets
                Nom_ligne = array[3]; //Nom de ligne
                Nom_station = array[2]; // nom de la station
                for (Ligne l : lignes) {
                    if (Station.stringCompareDirection(l.Nom, Nom_ligne) == 0) // comparaison de nom de ligne
                    {                                                   //si on trouve un nom correspondant à une des lignes on
                                                                    // ajoute la station à celle ci et à la liste des sommets du graphe
                        S = new Station(Nom_station, l, numSommet1);
                        l.addStation(S);
                        G.ajoutSommet(S);
                    }
                }
            } else if (curseur.charAt(0) == 'E') //E = liaison
            {
                array = curseur.split("  ", 4);      //Séparation = double espace
                numSommet1 = Integer.parseInt(array[1]);        //Num sommet 1
                numSommet2 = Integer.parseInt(array[2]);       //Num sommet 2
                numTemps = Integer.parseInt(array[3]);       //temps en secondes
                G.createLink(numSommet1, numSommet2, numTemps);  //création du lien
            }

        }  //fin de lecture

        return G ; //renvoie du graphe construit
    }
}