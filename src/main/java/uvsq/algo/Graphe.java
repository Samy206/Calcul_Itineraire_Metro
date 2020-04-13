package uvsq.algo;
import javax.management.relation.RelationSupport;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class Graphe {
    public List<Station> Sommets;           //Un graphe est composé de sommets et de liens
    public List<Lien> Links;

    public Graphe() {   //Pour l'initialiser on créer juste les deux listes
        Sommets = new ArrayList<Station>();
        Links = new ArrayList<Lien>();
    }

    public void ajoutSommet(Station S) {
        if (!(Sommets.contains(S)))                                    //Si le sommet n'est pas déjà présent dans le graphe on l'ajoute
        {
            Sommets.add(S);
        }
        else
            System.out.println("Le sommet : " + S.Nom + " de numéro " + S.Num +  " est déjà présent "); //Sinon on affiche ça
    }

    public int createLink(int numero1, int numero2, int temps) {
        int indiceI = -1;
        int indiceJ = -1;
        int cmp = 0;


        for (Station s : Sommets)    //on recherche l'indice des deux sommets
        {

            if (s.Num == numero1) {  //si on trouve la station correspondante au numero1 on l'enregsitre
                indiceI = cmp;
            } else if (s.Num == numero2) { //si on trouve la station correspondante au numero2 on l'enregsitre
                indiceJ = cmp;
            } else if (indiceI != (-1) && indiceJ != (-1)) // on a trouvé les deux sommets qu'on veut , ce n'est donc plus la peine de chercher
                break;
            cmp++;
        }


        if (indiceI == -1 || indiceJ == -1)   //Si l'un des deux sommets qu'on veut lier n'est pas présent ( voir même les deux ) on l'affiche
        {
            if (indiceI == -1)
                System.out.println("Le premier sommet n'a pas été trouvé."); //on n'a pas trouvé la première station

            if (indiceJ == -1)
                System.out.println("Le deuxième sommet n'a pas été trouvé."); //on n'a pas trouvé la deuxième station

            return -1;
        }

        Links.add(new Lien(Sommets.get(indiceI), Sommets.get(indiceJ), temps)); //Création du lien
        return 0;

    }

    public ResultTrajet ItineraireFromProgram(Station depart, Station arrivee ) {
        //Recherche d'itinéraire sans rien donner en
        // paramètre à l'éxecution ( Djikstra )

        int numDep = depart.Num ;                                       //Source : numéro de début du trajet
        double[] distance = new double[Sommets.size()];                 //Tableau des distances des autres stations à la source

        List <Station> trajet = new ArrayList<Station>();              //Liste qui va enregistrer le trajet emprunté
        String nomLignePrev;                                            //Nom de la ligne précédente sur le trajet

        String stat1 = depart.Nom ;               // enregistrement du nom de la station de départ qui servira plus tard

        double previousValDep;                                          //variable nous permettant de comparer les valeurs
        // (dep = depart => le sommet sur lequel on est
        //est l'origine du lien

        String direction ;                                              //Affichage de la direction à la fin

        double previousValArr;                                          //variable nous permettant de comparer les valeurs
        // (arr = arrivee => le sommet sur lequel on est
        //est la destination du lien

        Station Iti ;                                                     //Station qui va nous permettre d'afficher l'itinéraire
        double min = 3000000;                                             //Grande valeur nous permettant de vérifier le minimum
        int indice_min = -1 ;                                              //indice de la distance minimale à chaque fois
        Station[] pere = new Station[Sommets.size()];                      //tableau de pere nous permettant de tracer l'itinéraire
        List<Station> traite;                                              //liste des stations traitées
        traite = new ArrayList<Station>();

        pere[depart.Num] = null;                                        //la source n'a pas de père
        distance[depart.Num] = 0;                                         // et sa distance avec lui-même est de 0
        for (int i = 0; i < Sommets.size(); i++) {
            if (i != depart.Num)
                distance[i] = 3000000;                                  //initialisation à la valeur max

            pere[i] = null;                     // pere des autres stations pour l'instant null
        }
        while (traite.size() != Sommets.size()) { //tant que nous n'avons pas traité tout les sommets
            min = 300000;                       // on remet le minimum à 300000
            for (Lien l : Links) {              // Parmis tout les liens
                if (!traite.contains(depart)) {  // si nous n'avons pas traité le sommet sur lequel on est
                    if ((l.Depart).equals(depart) || (l.Arrivee).equals(depart) )  { //s'il y a un lien dans un sens ou l'autre
                        previousValDep = (distance[l.Depart.Num]);              //si c'est une autre station qui est lié à la station
                        // où on est
                        previousValArr = (distance[l.Arrivee.Num]);             //si c'est la station actuelle qui est lié à une autre
                        if(previousValArr < previousValDep)
                        {
                            if( (previousValArr+l.Temps) < distance[l.Depart.Num])
                            {
                                distance[l.Depart.Num] = previousValArr + l.Temps ;
                                pere[l.Depart.Num] = l.Arrivee ;
                            }
                        }                                                       //On initie la distance du sommet le plus court
                                                                                // et leur pere va être l'origine du lien
                        else
                        {
                            if( (previousValDep+l.Temps) < distance[l.Arrivee.Num])
                            {
                                distance[l.Arrivee.Num] = previousValDep + l.Temps ;
                                pere[l.Arrivee.Num] = l.Depart ;
                            }
                        }

                    }
                }
            }
            traite.add(depart);                             //on ajoute le sommet traité à la liste des sommets pour ne pas les retraiter
            for (int j = 0; j < Sommets.size(); j++) {

                if (min > distance[j] && distance[j] != 0 && j != depart.Num && (!traite.contains(Sommets.get(j)))) {
                    indice_min = j;
                    min = distance[j];
                    depart = Sommets.get(j);            //on recherche le sommet le plus proche de la source
                }
            }


        }

        Iti = arrivee;
        while (!((Iti.Nom).equals(stat1)))  // tant qu'on n'est pas à la station de départ
        {
            trajet.add(Iti);
            Iti = pere[Iti.Num];
        }

        trajet.add(Iti);
        return new ResultTrajet(distance[arrivee.Num],trajet);

    }

    public ResultTrajet getItineraireGraphe(String stat1, String stat2) {

        List <Station> depart = getNumStation(stat1);
        List <Station> arrivee = getNumStation(stat2);
        double min = Double.MAX_VALUE ;
        ResultTrajet tmp ;

        List <Station> itineraire = new ArrayList<>();
        for(int i = 0 ; i < depart.size() ; i++)
        {
            for(int j = 0 ;  j < arrivee.size() ; j++)
            {
                tmp = ItineraireFromProgram(depart.get(i),arrivee.get(j));
                if(min > tmp.duree)
                {
                   itineraire = tmp.itineraire;
                   min = tmp.duree ;
                }
            }
        }
        return new ResultTrajet (min,itineraire);
    }

    public String itineraireToString(ResultTrajet res)
    {
        String direction ;
        String trajet ="";
        Station Iti = res.itineraire.get(res.itineraire.size()-1);
        String nomLignePrev ;
        int minutes = (int) (res.duree / 60) ;  //nombre de minutes de la source à la station d'arrivée
        int secondes = (int) (res.duree % 60) ; //nombre de secondes de la source à la station d'arrivée

        List<Station> trajetStations = res.itineraire;
        Station depart = trajetStations.get(trajetStations.size()-1);
        Station next = trajetStations.get(trajetStations.size()-2);
        direction = getDirection(depart, next);   //obtention de la direction de la première ligne empruntée
        trajet += "Vous êtes à "+ Iti.Nom + " , prenez la ligne "+ Iti.Line.Nom + " en direction de "+ direction; //affichage du
        //debut du trajet
        nomLignePrev = Iti.Line.Nom ; //enregistrement de la ligne en cas de changement
        for(int i = trajetStations.size()-2 ; i >= 0 ; i--)
        {
            if(i == 0 )
                trajet += "\nVous arriverez à la station " + trajetStations.get(i).Nom;

            else if (Station.stringCompareDirection(trajetStations.get(i).Line.Nom , nomLignePrev) == 0 && i != 0 )
                trajet += "\nVous passerez par la station "+ trajetStations.get(i).Nom + " sur la ligne " +nomLignePrev;

            else if (Station.stringCompareDirection(trajetStations.get(i).Line.Nom , nomLignePrev) == 1 && i != 0)
            {
                nomLignePrev = trajetStations.get(i).Line.Nom ;
                depart = trajetStations.get(i);
                next = trajetStations.get(i-1);
                direction =  getDirection(depart, next);
                trajet += "\nA partir de  "+ trajetStations.get(i).Nom + " vous devrez prendre la ligne "+ trajetStations.get(i).Line.Nom + " en direction de " + direction;
            }

        }
        trajet += "\nCe trajet devrait durer "+ minutes + " minutes et " + secondes + " secondes";
        return trajet ;
    }


    /*public String getDirection(Station s1 , Ligne line ) //Méthode qui nous permet d'avoir la direction dans laquelle on va
    {
        Station s3 ;
        s3 = s1 ;   //station temporaire qui nous permet de nous balader sur la ligne

        int check = 98 ;

           //si nous ne sommes à aucun des deux ou trois terminus
            while ((Station.stringCompareDirection(s3.Nom, line.Terminus1) != 0 || (!(line.Terminus2.contains(s3.Nom))))) {
                for (Lien l : Links) {
                    check = 98;

                    //On "navigue" sur la même ligne jusqu'à trouver un des terminus

                    if ((l.Depart).equals(s3) && (Station.stringCompareDirection(l.Arrivee.Line.Nom,line.Nom) == 0)) { //Encore une fois
                                                    //soit on va dans la direction des liens , soit dans leur sens inverses
                                                    //( les lignes de métro permettent ça) , ( ici c'est dans leur sens)
                        s3 = l.Arrivee;
                        if ((Station.stringCompareDirection(s3.Nom, line.Terminus1) == 0)
                                || (Station.stringCompareDirection(s3.Nom, line.Terminus2.get(0)) == 0)) { //Ces vérifications
                                                    // peuvent mettre la variable check à 0 , et si c'est le cas c'est
                                                    // qu'on a trouvé un des terminus , du coup on sort des boucles et on renvoie la direction
                            check = 0;
                        }
                        if ((line.Terminus2.size() == 2)) {
                            if (Station.stringCompareDirection(s3.Nom, line.Terminus2.get(1)) == 0)
                                check = 0;
                        }

                        if (check == 0)
                            break;

                    }

                }
                if (check == 0)
                    break;
            }



        return s3.Nom ;
    }*/

    public String getDirection( Station depart, Station next )
    {

        Ligne line = depart.Line ;
        if(Station.stringCompareDirection(depart.Nom,line.Terminus1) == 0 ) // si on est déjà à un des deux ou trois terminus on retourne
        // un autre dans la direction opposée
        {
            if(line.Terminus2.size() == 1)
                return line.Terminus2.get(0) ;
        }
        else if (( line.Terminus2.contains(depart.Nom) ) )  //ici aussi
        {
            return line.Terminus1 ;
        }

        List<String> stationsTraitees = new ArrayList<>();
        stationsTraitees.add(depart.Nom);
        stationsTraitees.add(next.Nom);
        Boolean isArrivee = false;
        Boolean isDepart = false;
        Boolean isTerminus = false;
        while(! isTerminus){
            for (Lien link : Links) {
                if (next.Nom.equals(link.Arrivee.Nom)) {
                    isArrivee = true;
                }
                else if (next.Nom.equals(link.Depart.Nom)) {
                    isDepart = true;
                }
                if (isArrivee && link.Depart.Line == next.Line && ! stationsTraitees.contains(link.Depart.Nom)) {
                    next = link.Depart;
                    stationsTraitees.add(next.Nom);
                }
                else if (isDepart && link.Arrivee.Line == next.Line && ! stationsTraitees.contains(link.Arrivee.Nom)) {
                    next = link.Arrivee;
                    stationsTraitees.add(next.Nom);
                }
                isDepart = false;
                isArrivee = false;
            }
            isTerminus = (next.Nom.equals(line.Terminus1) || line.Terminus2.contains(next.Nom));
        }


        return next.Nom;

    }

    public List<Station> getNumStation(String Name)  //on prend un nom de station et on renvoie le sommet correspondant
    {
        List <Station> possibilite = new ArrayList<Station>();
        for(Station s : Sommets){
            if(Station.stringCompareStation(Name,s.Nom) == 0 )
            {
                possibilite.add(s);
            }
        }
        return possibilite;
    }

}

