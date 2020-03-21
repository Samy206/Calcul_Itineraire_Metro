package uvsq.algo;
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

    public void ItineraireFromProgram(Station depart, Station arrivee) {
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

        int minutes = (int) (distance[arrivee.Num] / 60) ;  //nombre de minutes de la source à la station d'arrivée
        int secondes = (int) (distance[arrivee.Num] % 60) ; //nombre de secondes de la source à la station d'arrivée

        System.out.println("Arrivée à " + arrivee.Nom + " à partir de " + Sommets.get(numDep).Nom + //Affichage de la durée
                " en " + minutes + " minutes et " + secondes + " secondes");
        Iti = arrivee;
        direction = getDirection(Iti,Iti.Line);  // direction qui évoluera au fil du trajet
        while (!((Iti.Nom).equals(stat1)))  // tant qu'on n'est pas à la station de départ on met les stations dans la liste
        {
            trajet.add(Iti);
            Iti = pere[Iti.Num];            // et on retourne en arrière
        }

        System.out.println("Démarrez de " + Iti.Nom + " en prenant la ligne " + Iti.Line.Nom + " en direction de "+direction);

        nomLignePrev = Iti.Line.Nom ;     //enregistrement de la ligne actuelle

        for(int i = trajet.size()-1 ; i >= 0 ; i--)
        {
            if(i == 0 )
                System.out.println("Vous êtes donc arrivé à la station " + trajet.get(i).Nom);  //on affiche la station d'arrivée

            if(Station.stringCompareDirection(trajet.get(i).Line.Nom , nomLignePrev) == 0 && i != 0)  // Si la station d'arrivée n'est pas encore arrivée
                                                                                                      //et qu'on reste sur la même ligne
            {
                System.out.println("Puis vous arriverez à "+ trajet.get(i).Nom + " sur la même ligne" );
            }
            else if (Station.stringCompareDirection(trajet.get(i).Line.Nom , nomLignePrev) == 1 && i != 0) //Si on change de ligne d'une station à une autre on affiche
                                                                                                        // la nouvelle ligne et sa direction
            {
                nomLignePrev = trajet.get(i).Line.Nom ;
                direction =  getDirection(trajet.get(i),trajet.get(i).Line);
                System.out.println("A partir de  "+ trajet.get(i).Nom + " et vous devrez prendre la ligne "+ trajet.get(i).Line.Nom + " en direction de " + direction );
            }

        }

    }

    public void ItineraireFromExec(String stat1, String stat2) {
        //Même commentaire que l'algo précent à l'exception que celui ci
        // prend deux noms de stations que l'on donne à l'execution

        int cmp = 0;
        String nomLignePrev ;
        List <Station> trajet = new ArrayList<Station>();
        Station depart, arrivee;
        depart = getNumStation(stat1);       //et ici on récupère leur numéro de sommets
        arrivee = getNumStation(stat2);       // ici aussi
        int numDep = depart.Num;
        double[] distance = new double[Sommets.size()];
        double previousValDep;
        String direction;
        double previousValArr;
        Station Iti;
        double min = 3000000;
        int indice_min = -1;
        Station[] pere = new Station[Sommets.size()];
        List<Station> traite;
        traite = new ArrayList<Station>();

        pere[depart.Num] = null;
        distance[depart.Num] = 0;
        for (int i = 0; i < Sommets.size(); i++) {
            if (i != depart.Num)
                distance[i] = 3000000;

            pere[i] = null;
        }
        while (traite.size() != Sommets.size()) {
            min = 300000;
            for (Lien l : Links) {
                if (!traite.contains(depart)) {
                    if ((l.Depart).equals(depart) || (l.Arrivee).equals(depart)) {
                        previousValDep = (distance[l.Depart.Num]);
                        previousValArr = (distance[l.Arrivee.Num]);
                        if (previousValArr < previousValDep) {
                            if ((previousValArr + l.Temps) < distance[l.Depart.Num]) {
                                distance[l.Depart.Num] = previousValArr + l.Temps;
                                pere[l.Depart.Num] = l.Arrivee;
                            }
                        } else {
                            if ((previousValDep + l.Temps) < distance[l.Arrivee.Num]) {
                                distance[l.Arrivee.Num] = previousValDep + l.Temps;
                                pere[l.Arrivee.Num] = l.Depart;
                            }
                        }

                    }
                }
            }
            traite.add(depart);
            for (int j = 0; j < Sommets.size(); j++) {

                if (min > distance[j] && distance[j] != 0 && j != depart.Num && (!traite.contains(Sommets.get(j)))) {
                    indice_min = j;
                    min = distance[j];
                    depart = Sommets.get(j);
                }
            }


        }


        int minutes = (int) (distance[arrivee.Num] / 60) ;  //nombre de minutes de la source à la station d'arrivée
        int secondes = (int) (distance[arrivee.Num] % 60) ; //nombre de secondes de la source à la station d'arrivée

        System.out.println("Arrivée à " + arrivee.Nom + " à partir de " + Sommets.get(numDep).Nom + //Affichage de la durée
                " en " + minutes + " minutes et " + secondes + " secondes");
        Iti = arrivee;
        direction = getDirection(Iti,Iti.Line);  // direction qui évoluera au fil du trajet
        while (!((Iti.Nom).equals(stat1)))  // tant qu'on n'est pas à la station de départ
        {
            trajet.add(Iti);
            Iti = pere[Iti.Num];
        }

        direction = getDirection(Iti,Iti.Line);
        System.out.println("Démarrez de " + Iti.Nom + " en prenant la ligne " + Iti.Line.Nom + " en direction de "+direction);
        nomLignePrev = Iti.Line.Nom ;
        for(int i = trajet.size()-1 ; i >= 0 ; i--)
        {
            if(i == 0 )
                System.out.println("Vous êtes donc arrivé à la station " + trajet.get(i).Nom);

            if(Station.stringCompareDirection(trajet.get(i).Line.Nom , nomLignePrev) == 0 && i != 0)
            {
                System.out.println("Puis vous arriverez à "+ trajet.get(i).Nom + " sur la même ligne" );
            }
            else if (Station.stringCompareDirection(trajet.get(i).Line.Nom , nomLignePrev) == 1 && i != 0)
            {
                nomLignePrev = trajet.get(i).Line.Nom ;
                direction =  getDirection(trajet.get(i),trajet.get(i).Line);
                System.out.println("A partir de  "+ trajet.get(i).Nom + " et vous devrez prendre la ligne "+ trajet.get(i).Line.Nom + " en direction de " + direction );
            }

        }

    }


    public String getDirection(Station s1 , Ligne line ) //Méthode qui nous permet d'avoir la direction dans laquelle on va
    {
        Station s3 ;
        s3 = s1 ;   //station temporaire qui nous permet de nous balader sur la ligne

        int check = 98 ;

        if(Station.stringCompareDirection(s1.Nom,line.Terminus1) == 0 ) // si on est déjà à un des deux ou trois terminus on retourne
                                                                        // un autre dans la direction opposée
        {
           if(line.Terminus2.size() == 1)
            return line.Terminus2.get(0) ;
        }
        else if (( line.Terminus2.contains(s3.Nom) ) )  //ici aussi
        {
            return line.Terminus1 ;
        }
        else {  //si nous ne sommes à aucun des deux ou trois terminus
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
                    else if ((l.Arrivee).equals(s3) && (Station.stringCompareDirection(l.Depart.Line.Nom,line.Nom) == 0)) { //ici
                                                    // on va dans le sens inverse des liens
                                                    //on ne voit cette possibilité uniquement s'il n'y a pas de liens qui nous
                                                    //arrange ( sur la même ligne  de métro)
                                                    //check prend 0 si on trouve un terminus

                        s3 = l.Depart;
                        if ((Station.stringCompareDirection(s3.Nom, line.Terminus1) == 0)
                                || (Station.stringCompareDirection(s3.Nom, line.Terminus2.get(0)) == 0)) {
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

        }

        return s3.Nom ;
    }

    public Station getNumStation(String Name)  //on prend un nom de station et on renvoie le sommet correspondant
    {
        Station find = null ;
        for(Station s : Sommets){
            if(Station.stringCompareStation(Name,s.Nom) == 0 )
            {
                find = s ;          //là c'est trouvé
                break ;
            }
        }

        return find;
    }

}

