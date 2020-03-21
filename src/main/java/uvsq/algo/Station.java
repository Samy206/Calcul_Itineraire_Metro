package uvsq.algo;
import java.lang.String;

public class Station {
    public String Nom;              //Une station a un nom , un numéro dans le fichier et appartient à une ligne
    public Ligne Line;
    public int Num;

    public Station(String nom, Ligne line, int num_sommet) {
        Nom = nom;
        Line = line;                        //On initialise ça au début
        Num = num_sommet;
    }

    @Override
    public String toString() {
        return ("Il s'agit de la sation " + Nom + " de numéro " + Num + " présente sur la ligne " + Line + "");
    }

    public static int stringCompareDirection(String str1, String str2) { //Compare deux noms de stations , utilisées pour chercher
                                                                         // les directions à chaque changement de ligne

        int l1 = str1.length();
        int l2 = str2.length();
                                                                    //Si leur taille sont diff on peut d'ores et déjà dire qu'elles sont diff
        if (l1 != l2)
            return 1;

        else if (l1 == l2) {

            for (int i = 0; i < l1; i++) {                      //Puis on compare caractère par caractère , et si l'un est différent
                                                                // on retourne faux
                if (str1.charAt(i) != str2.charAt(i))
                    return 1;

            }
        }
        return 0;
    }

    public static int stringCompareStation(String str1, String str2) { //Même but que la fonction précédente mais ici on ne prend pas
                                                                        //en compte les espaces
                                                                        //Utilisée pour la recherche des num de stations passées à
                                                                        // l'éxecution

        str1.replaceAll("\\s+", "");
        str2.replaceAll("\\s+", "");

        if ((str1.replaceAll("\\s+", "")).equalsIgnoreCase(str2.replaceAll("\\s+", ""))) //Usage de replace all qui supprime tout les
                                                                                            // espaces
                                                                                            //equals qui vérifie l'égalité
                                                                                            //(Trouvée sur internet)
            return 0;
        else
            return 1;

    }
}
