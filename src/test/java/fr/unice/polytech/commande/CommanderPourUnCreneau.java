package fr.unice.polytech.commande;

import fr.unice.polytech.nourriture.Menu;
import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.offre.*;
import fr.unice.polytech.commande.Commande;
import fr.unice.polytech.restaurant.AucunMenuException;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.restaurant.RestaurantManager;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.Date;
import fr.unice.polytech.utils.Horaire;
import fr.unice.polytech.utils.OffreUtils;
import io.cucumber.java.fr.*;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static fr.unice.polytech.utils.OffreUtils.createCreneaus;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommanderPourUnCreneau {
    RestaurantManager restaurantManager = new RestaurantManager();
    Restaurant restaurant;
    CreneauDirector creneauDirector = new CreneauDirector();
    Scheduler scheduler = new Scheduler();
    CompteUtilisateur compteUtilisateur;
    Date date;

    @Etantdonné("un restaurant  {string}")
    public void unRestaurant(String arg0) {
        restaurant = new Restaurant(arg0);
        restaurantManager.addRestaurant(restaurant);

    }

    @Etqu("il a possède uniquement des créneaux  {string}:")
    public void ilAPossèdeUniquementDesCréneaux(String arg0, List<List<String>>iCreneauList) {
            createCreneaus(arg0,iCreneauList,creneauDirector).forEach(c->restaurant.ajouterCreneau(c));

        scheduler.ajouterRestaurant(restaurant);


    }

    @Quand("je demande la liste des creneaux pour le restaurant {string}")
    public void jeDemandeLaListeDesCreneauxPourLeRestaurant(String arg0) {
        restaurant = new Restaurant(arg0);

    }

    @Alors("j'obtient les creneaux {string}:")
    public void jObtient(String arg0, List<List<String>> horaires) {

        assertEquals(createCreneaus(arg0, horaires, creneauDirector), scheduler.getCreneauxDisponibles(restaurant));

    }


    @Quand("je commande à {string} {string} prochain  entre {string} et {string} un menu:")
    public void jeCommandeÀAujourdHuiEntreEtUnMenu(String arg0, String arg1, String arg2, String arg3, List<String> menus) {
        //restaurant=new Restaurant(arg0);
        Date date = OffreUtils.convertStringToDate(arg1);
        Horaire debut = new Horaire(arg2);
        Horaire fin = new Horaire(arg3);
        assertTrue(scheduler.restaurantContientCreneau(restaurant, arg1, debut, fin));
        ICreneau creneau = scheduler.getCreneau(restaurant, arg1, debut, fin);
        Commande commande = new Commande(compteUtilisateur);

        try {
            List<MenuPlat> menuPlats = restaurant.getMenus();
            Optional<MenuPlat> menu = menuPlats.stream().filter(m -> m.getNom().equals(menus.get(0))).findFirst();
            assertTrue(menu.isPresent());
            commande.ajoutMenuPlat(menu.get(), 1);
            scheduler.diminuerLaCapacitePourCreneau(restaurant, date, creneau, 1);

        } catch (AucunMenuException e) {
            throw new RuntimeException(e);
        }

    }


    @Etqu("il propose les menus suivant :")
    public void ilProposeLesMenusSuivant(Map<String, Double> menus) {
        for (Map.Entry<String, Double> menu : menus.entrySet()) {
            restaurant.addMenu(new Menu(menu.getKey(), menu.getValue()));
        }

        try {
            Assert.assertEquals(menus.size(), restaurant.getMenus().size());
        } catch (AucunMenuException e) {
            assert false : "Le restaurant ne doit pas avoir aucun menu";
        }
    }

    @Et("un utilisateur {string} {string}")
    public void unUtilisateur(String arg0, String arg1) {
        compteUtilisateur = new CompteUtilisateur(arg0, arg1);
    }

    @Quand("je demande la liste des creneaux pour le restaurant {string} pour le {string}")
    public void jeDemandeLaListeDesCreneauxPourLeRestaurantPourLe(String arg0, String arg1) {
        restaurant=new Restaurant(arg0);
        date= new Date(arg1);

    }

    @Alors("j'obtient les creneaux :")
    public void jObtientLesCreneaux(List<String> creneaux) {
        CreneauQuotidien creneau = new CreneauQuotidien(new Horaire(creneaux.get(0)), new Horaire(creneaux.get(1)), Integer.parseInt(creneaux.get(2)), creneaux.get(3));
        ICreneau tes = scheduler.getCreneauxDisponblesFor(date, restaurant).get(0);

        assertTrue(creneau.estIdentique(tes));
    }


    @Alors("{string} contient pour {string} prochain:")
    public void contientPourProchain(String arg0, String arg1,List<String >creneaux) {
        Date date=OffreUtils.convertStringToDate(arg1);
        CommandeCreneau commandeCreneau=scheduler.getCommandesPlannifiees().get(restaurant).getCommandeCreneau(date,new Horaire(creneaux.get(0)),new Horaire(creneaux.get(1)));
        assertTrue(commandeCreneau!=null&&commandeCreneau.getNbCommandesPasses()==Integer.parseInt(creneaux.get(2))&&commandeCreneau.getNbCommandesPossibles()==Integer.parseInt(creneaux.get(3)));

    }
}
