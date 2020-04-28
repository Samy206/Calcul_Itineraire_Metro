package uvsq.algo;


import java.util.List;

public class ResultTrajet { //structure qui a été utilisé dans la recherche d'un itinéraire optimal à partir d'une station où il y a plusieurs ligne :
                            // on a ici une durée et un itinéraire ( ensemble de stations par lesquelles passer )
                            //selon la ligne qu'on emprunte , on compare la durée de chaque trajet afin d'avoir la moins longue possible
    double duree ;
    List<Station>  itineraire ;

    public ResultTrajet(double temps , List <Station> liste) //le constructeur prend donc naturellement une durée et une liste de stations
    {
        duree = temps ;
        itineraire = liste ;
    }


}
