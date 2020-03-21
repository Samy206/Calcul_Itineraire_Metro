package uvsq.algo;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class Ligne
{
    public String Nom;                    //une station a un nom ( ça aurait pu être un nombre mais il y a les stations 3 et 7 bis)
    public List <Station> Stations ;    //Contient un ensemble de stations
    public String Terminus1 ;           //Un terminus de base ( choisis au hasard)
    public List<String> Terminus2 ;     //Un ou deux autres terminus ( terminus restants)

    public Ligne(String nom )           //De base on l'initialise avec un nom , on ajoutera les stations au fur et à mesure
    {
        Nom = nom ;
        Stations = new ArrayList <Station> () ;
        Terminus2 = new ArrayList <String> ();
    }

    public void setTerminus(String terminus1 , String terminus2 , String terminus3)     //là on initialise les terminus
    {
        Terminus1 = terminus1 ;
        Terminus2.add(terminus2);    // Il y a forcément deux terminus à une ligne

        if(terminus3 != null)       //certaines lignes peuvent avoir trois terminus , du coup on met null au troisième si ce n'est pas le cas
        {
            Terminus2.add(terminus3);
        }
    }
    public void addStation(Station s)   //nous permet d'ajouter des stations à la ligne
    {
        if(!(Stations.contains(s)))     // si la station n'est pas déjà dans la liste on l'ajoute
            Stations.add(s);
    }

    @Override
    public String toString()
    {
        String ListeStations , ListeTerminus ;
        ListeStations = "";
        ListeTerminus = "";

        for(Station s : Stations)                                   //Concaténation de tout les noms de stations
        {
            ListeStations = ListeStations + s.Nom ;
            ListeStations = ListeStations + " , " ;
        }

        ListeTerminus = ListeTerminus + Terminus1 ;

        for(String t : Terminus2 )                                     //Concaténation des terminus
        {
            ListeStations = ListeStations + t ;
            ListeStations = ListeStations + " , " ;
        }

        return ("La ligne de nom " + Nom + " a pour terminus " + ListeTerminus + " et dessert les stations " + ListeStations + "");
        //On l'affiche
    }

}
