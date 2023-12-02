package fr.unice.polytech.restaurant;

import fr.unice.polytech.offre.Creneau;
import fr.unice.polytech.offre.CreneauDirector;
import fr.unice.polytech.offre.ICreneau;
import fr.unice.polytech.utils.Horaire;
import fr.unice.polytech.utils.OffreUtils;
import io.cucumber.java.fr.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AjouterUnHoraire {
    Restaurant restaurant;
    int ancienneTaille =0;
    int ancienneCapacite;
    boolean estAjoute=false;
    ICreneau ancienCreneau;
    CreneauDirector creneauDirector=new CreneauDirector();




    @Etantdonnéque("j'ai une liste d'horaires:")
    public void jAiUneListeDHoraires(List<List<String>>horaires) {
        for (List<String>val: horaires.subList(1,horaires.size())
             ) {
            restaurant.ajouterCreneau(new Creneau(new Horaire(val.get(0)),new Horaire(val.get(1)),Integer.parseInt(val.get(2))));

        }
        ancienneCapacite=restaurant.getCapacite();
        ancienneTaille =restaurant.getCreneaus().size();
    }



    @Alors("la liste d'horaire contient:")
    public void laListeDHoraireContient(List<String> list) {


        assertEquals(restaurant.getCreneaus().get(restaurant.getCreneaus().size() - 1), ancienCreneau);

    }

    @Et("la taille de la liste a augmenté de {int}")
    public void laTailleDeLaListeAAugmenteDe(int increment) {

        assertEquals(ancienneTaille +increment,restaurant.getCreneaus().size());
    }


    @Et("la capacité du restaurant a augmenté de {int}")
    public void laCapaciteDuRestaurantAAugmenteDe(int arg0) {
       assertEquals( restaurant.getCapacite()-ancienneCapacite,arg0);

    }

    @Etantdonné("un restaurant {string}")
    public void unRestaurant(String arg0) {
        restaurant=new Restaurant(arg0);
        restaurant.setDuree_slot(10);

    }

    @Alors("l'horaire n'est pas ajouté")
    public void lHoraireNEstPasAjouté() {
        assertFalse(estAjoute);
    }

    @Alors("la capacite n'a pas augmenté")
    public void laCapaciteNAPasAugmenté() {
        assertEquals(ancienneCapacite,restaurant.getCapacite());
    }


    @Quand("j'ajoute l'horaire {string}:")
    public void jAjouteLHoraire(String arg0,List<String>horaire) {
        try{

            List<ICreneau>creneaus1= OffreUtils.createCreneaus(arg0,List.of(horaire),creneauDirector);
           estAjoute= restaurant.ajouterCreneau(creneaus1.get(0));
           ancienCreneau=creneaus1.get(0);


        }
        catch (NumberFormatException e){
            estAjoute=false;
        }


    }
}
