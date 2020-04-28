package uvsq.algo;

public class Lien
{
    public Station Depart ;         //Un lien relie deux stations : une station départ , une station arrivée , et cela en X secondes
                                    // (temps)
    public Station Arrivee;
    public int Temps ;

    public Lien(Station debut , Station fin , int temps) //le constructeur est là pour initialiser la station de départ et d'arrivée ainsi que le temps nécessaire entre ces dernières
    {
        Depart = debut ;
        Arrivee = fin ;
        Temps = temps ;
    }

    @Override
    public String toString() //fonction toString qui est redéfinie ici et affiche ses caractéristiques
    {
        return ("Lien entre " + Depart.Nom + " et " + Arrivee.Nom + " qui se fait en " + Temps + " secondes. ");
    }
}