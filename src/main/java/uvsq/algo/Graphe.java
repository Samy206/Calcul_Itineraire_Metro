package uvsq.algo;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class Graphe {
    public List<Station> Sommets;           //Un graphe est composé de sommets ( stations ) et de liens ( transitions )
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

    public int createLink(int numero1, int numero2, int temps) { //on cherche les deux numéros de sommets entrés , et une fois trouvés on créer un lien entre les deux
                                                                 //dont le poids est la vairable "temps"
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

    public ResultTrajet ItineraireFromProgram(Station depart, Station arrivee ) { //Recherche d'itinéraire ( algorithme de djikstra ) : on part d'une station et tant qu'on trouve des liens
        // qui ont cette station soit en arrivée soit en départ on inscrit dans l'indice correspondant du tableau distance le poids du lien + la distance actuelle de la station
        // en question , et ainsi on avance de façon à avoir les chemins dont les durées sont les moins longues pour chaque autre station ( et on change le pere si il faut )
        // et dès qu'on a fini de traiter un sommet on l'ajoute à une liste où il ne sera pas retraité une deuxième fois , et ainsi on utilise le principe de Djikstra dans cet algortihme
        //A la fin , avec le tableau pere on retrace l'itinéraire trouvé et on renvoie une classe ResultTrajet qui correspond à une liste de stations et une durée en secondes

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
        while (!((Iti.Nom).equals(stat1)))  // tant qu'on n'est pas à la station de départ , on ajoute les stations à la liste Trajet qui correspond à l'itinéraire qu'on va renvoyer
        {
            trajet.add(Iti);
            Iti = pere[Iti.Num];
        }

        trajet.add(Iti);
        return new ResultTrajet(distance[arrivee.Num],trajet);

    }

    public ResultTrajet getItineraireGraphe(String stat1, String stat2) { // si une station de départ a plusieurs correspondances on vérifie chaque départ de ligne possible afin
        // d'obtenir le trajet le plus court
        //la fonction getNumStation renvoie une liste de station qui portent le même nom mais qui se trouvent sur des lignes différentes et ainsi on les teste toutes
        //on fait de même avec la station d'arrivée si elle a plusieurs correspondances aussi

        List <Station> depart = getNumStation(stat1);
        List <Station> arrivee = getNumStation(stat2); //récupération des deux numéros de sommets à partir  des noms de ces stations
        double min = Double.MAX_VALUE ;
        ResultTrajet tmp ;

        List <Station> itineraire = new ArrayList<>();
        for(int i = 0 ; i < depart.size() ; i++)  // double boucle sur la station de départ et d'arrivée nous permettant d'avoir la durée optimale
        {
            for(int j = 0 ;  j < arrivee.size() ; j++)
            {
                tmp = ItineraireFromProgram(depart.get(i),arrivee.get(j));
                if(min > tmp.duree) //si on trouve une durée inférieure on enregistre le trajet et la durée
                {
                   itineraire = tmp.itineraire;
                   min = tmp.duree ;
                }
            }
        }
        return new ResultTrajet (min,itineraire); //on renvoie la même structure que pour l'algorithme de djikstra pour que l'itinéraire et la durée soient affichés
    }

    public String itineraireToString(ResultTrajet res) //affichage de l'itinéraire qui prend en paramètre la classe qu'on vient d'évoquer
    {
        String direction ;
        String trajet ="";
        Station Iti = res.itineraire.get(res.itineraire.size()-1); // première station empruntée
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
        for(int i = trajetStations.size()-2 ; i >= 0 ; i--) //on navigue dans le trajet pour concaténer le nom des stations à la chaine de caractère nommée trajet
        {
            if(i == 0 )
                trajet += "\nVous arriverez à la station " + trajetStations.get(i).Nom;

            else if (Station.stringCompareDirection(trajetStations.get(i).Line.Nom , nomLignePrev) == 0 && i != 0 ) // si on est sur la même ligne on se contente de concaténer la station
                                                                                                                    // actuelle
                trajet += "\nVous passerez par la station "+ trajetStations.get(i).Nom + " sur la ligne " +nomLignePrev;

            else if (Station.stringCompareDirection(trajetStations.get(i).Line.Nom , nomLignePrev) == 1 && i != 0) //sinon on recherche la direction de la nouvelle ligne empruntée
                // puis on concaténe
            {
                nomLignePrev = trajetStations.get(i).Line.Nom ;
                depart = trajetStations.get(i);
                next = trajetStations.get(i-1);
                direction =  getDirection(depart, next);
                trajet += "\nA partir de  "+ trajetStations.get(i).Nom + " vous devrez prendre la ligne "+ trajetStations.get(i).Line.Nom + " en direction de " + direction;
            }

        }
        trajet += "\nCe trajet devrait durer "+ minutes + " minutes et " + secondes + " secondes";
        return trajet ; //on renvoie la chaîne de caractère contenant maintenant l'ensemble des informations du trajet
    }

    public String getDirection( Station depart, Station next )//cette fonction nous permet d'avoir grâce à deux stations la direction qu'on prend tant qu'on est sur une ligne
    {
        //si on est à un des terminus on renvoie un terminus dans le sens opposé
        //sinon tant qu'on a pas trouvé un des terminus on avance dans la ligne grâce à un parcours des liens existants sur cette ligne là de façon à arriver à un moment à un des terminus
        // la seule subtilité ici est de vérifier les liens dans les deux sens ( arrivée et depart ) , et le fait d'avoir deux stations en paramètre nous permet de ne pas nous tromper
        // de sens dès le début
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

        List<String> stationsTraitees = new ArrayList<>(); //sinon on initialise aussi une liste de stations qui dès qu'elle seront traitées ne pourront plus servir de point de départ
                                                            // sur la ligne
        stationsTraitees.add(depart.Nom);
        stationsTraitees.add(next.Nom);
        Boolean isArrivee = false;
        Boolean isDepart = false;
        Boolean isTerminus = false;
        while(! isTerminus){ //tant qu'on a pas trouvé de terminus
            for (Lien link : Links) {
                if (next.Nom.equals(link.Arrivee.Nom)) {
                    isArrivee = true;
                }
                else if (next.Nom.equals(link.Depart.Nom)) {
                    isDepart = true;
                }
                if (isArrivee && link.Depart.Line == next.Line && ! stationsTraitees.contains(link.Depart.Nom)) { //si on doit aller dans le sens "depart"
                                                        //next devient le départ de la prochaine itération
                    next = link.Depart;
                    stationsTraitees.add(next.Nom);
                }
                else if (isDepart && link.Arrivee.Line == next.Line && ! stationsTraitees.contains(link.Arrivee.Nom)) { //sinon c'est l'arrivée
                    next = link.Arrivee;
                    stationsTraitees.add(next.Nom);
                } // et qu'il y a encore des stations qui nous sépare du terminus qu'on cherche on continue
                isDepart = false;
                isArrivee = false;
            }
            isTerminus = (next.Nom.equals(line.Terminus1) || line.Terminus2.contains(next.Nom)); // le bouleen qui vérifie bien qu'on est pas à un des terminus est changé ici
        }

        return next.Nom; //on renvoie le nom du terminus en question

    }

    public List<Station> getNumStation(String Name)  //on prend un nom de station et on renvoie les sommets correspondants sur plusieurs lignes
    {
        List <Station> possibilite = new ArrayList<Station>();

        for(Station s : Sommets){
            if(Station.stringCompareStation(Name,s.Nom) == 0 )
            {
                possibilite.add(s); // si un des sommets correspond au nom entré en paramètre on ajoute ce sommet à la liste de stations
            }
        }
        if(possibilite.size() != 0 ) // s'il y en a au moins un on renvoie la liste
            return possibilite;

        else
            System.out.println("Nom invalide de station");
            System.exit(-1);
            return null ; //sinon on renvoie null
    }

}

