package uvsq.algo;
import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.GraphvizCmdLineEngine;
import guru.nidi.graphviz.model.Graph;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.*;

public class Main
{
    public static void main(String[] args)
    {
        File file = new File(Main.class.getClassLoader().getResource("metro.txt").getFile()); //le fichier est stocké dans
                                                                                                    // "resources"
        
        try { //si la lecture du fchier précédent se fait bien


            String stationDepart;
            String stationArrivee ;
            System.out.print("A quelle station êtes-vous : ");
            Scanner scanf = new Scanner(System.in);
            stationDepart = scanf.nextLine();
            System.out.print("A quelle station voulez-vous arriver : ");
            scanf = new Scanner(System.in);
            System.out.println("");  //Les deux premiers objets Scanner nous permettent de récupérer les stations de départ et d'arrivée
            stationArrivee = scanf.nextLine();
            Scanner sc = new Scanner(file);

            Ligne L ; //variable qui nous permet d'enregistrer les différentes lignes
            String[] stationsExec; //Tableau qui va récupérer les stations données à l'éxecution
            String curseur; //chaine de caractère qui va enregistrer la ligne où on est
            String Nom_station ; //nom de chaque station lue
            String Nom_ligne ;  //Nom de chaque ligne lue
            Station S ;   //variable qui va nous servir à créer le nombre de station qu'il faut
            Graphe G = new Graphe();    // Graphe du métro de Paris
            List <Ligne> lignes = new ArrayList<Ligne>(); //liste de ligne
            String[] array ; //division de la variable curseur en plusieurs String
            int num ;
            int num1 , num2 ; //Ensemble des numéros qui vont nous permettre de lire les numéros de chaque liens ( num de station et temps)

            while(sc.hasNextLine())  //Lecture du fichier et création du graphe à partir de ce dernier (tant qu'il y a des choses à lire)
            {
                Nom_station = "";   //initialisation du nom de chaque station et chaque ligne
                Nom_ligne = "";
                curseur = sc.nextLine();

               if(curseur.charAt(0) == '#') //si on lit un # ça ne correspont à rien
                   continue;

               else if (curseur.charAt(0) == 'L')//si on lit un 'L' (ligne)
               {
                   array = curseur.split("  ",5); //le array[0] est le L , la séparation est un double espace
                   L = new Ligne(array[1]); //Le array1 est le nom de la ligne

                   if( Station.stringCompareDirection(array[4],"null") == 0) //on a complété les stations à deux terminus avec un null
                   L.setTerminus(array[2],array[3],null);   //si on lit le null on n'ajoute uniquement les deux premiers terminus

                   else
                       L.setTerminus(array[2],array[3],array[4]); //sinon on en ajoute trois

                   lignes.add(L); //on ajoute cette nouvelle ligne à la liste de lignes
               }


               else if(curseur.charAt(0) == 'V') // V = Sommets
               {
                   array = curseur.split("  ", 4); //La séparation est un double espace
                   num = Integer.parseInt(array[1]); //Numéro de sommets
                   Nom_ligne = array[3]; //Nom de ligne
                   Nom_station = array[2]; // nom de la station
                   for(Ligne l : lignes)
                   {
                       if(Station.stringCompareDirection(l.Nom,Nom_ligne) == 0) // comparaison de nom de ligne
                       {                                        //si on trouve un nom correspondant à une des lignes on
                                                                // ajoute la station à celle ci et à la liste des ommets du graphe
                           S = new Station(Nom_station,l,num);
                           l.addStation(S);
                           G.ajoutSommet(S);
                       }
                   }
               }
               else if(curseur.charAt(0) == 'E' ) //E = liaison
               {
                   array = curseur.split("  ", 4);      //Séparation = double espace
                   num = Integer.parseInt(array[1]);        //Num sommet 1
                   num1 = Integer.parseInt(array[2]);       //Num sommet 2
                   num2 = Integer.parseInt(array[3]);       //temps en secondes
                   G.createLink(num,num1,num2);  //création du lien
               }

            }  //fin de lecture


            G.ItineraireFromExec(stationDepart,stationArrivee); //calcul de l'itinéraire





        }
        catch(FileNotFoundException ex) //Si on ne peut pas lire le fichier on se contente d'afficher ça
        {
            System.out.println("File not found");
        }

    }
}