package uvsq.algo;


import java.util.List;

public class ResultTrajet {
    double duree ;
    List<Station>  itineraire ;

    public ResultTrajet(double temps , List <Station> liste)
    {
        duree = temps ;
        itineraire = liste ;
    }


}
