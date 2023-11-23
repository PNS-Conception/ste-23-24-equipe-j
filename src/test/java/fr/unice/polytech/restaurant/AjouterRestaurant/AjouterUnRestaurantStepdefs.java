package fr.unice.polytech.restaurant.AjouterRestaurant;

import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.restaurant.RestaurantManager;
import fr.unice.polytech.utils.Position;
import io.cucumber.java.fr.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class AjouterUnRestaurantStepdefs {
    Restaurant restaurant;
    RestaurantManager restaurantManager;
    Map<String, String> champsUtilisateur = new HashMap<>();

    @Etantdonnéque("l' Administrateur du campus est authentifié")
    public void administrateurEstAuthentifie() {
        restaurantManager = new RestaurantManager();
    }

    @Et("les champs nom, position:")
    public void ajouterUnRestaurantNormal(Map<String, String> champs) {
        assertTrue(champs.containsKey("nom") && champs.containsKey("position"));
        champsUtilisateur = champs;
    }

    @Et("le champ nom:")
    public void ajouterUnRestaurantUniquementAvecLeNom(Map<String, String> champs) {
        // 2 car il y a juste le nom du restaurant et les attributs
        assertTrue(champs.containsKey("nom") && champs.size() == 2);
        champsUtilisateur = champs;
    }

    @Etque("{string} de position {string} est dans le système")
    public void restaurantexisteDeja(String nom, String position) {
        Position positionRestaurant = new Position(position);
        Restaurant restautantDansLeSysteme = new Restaurant(nom, positionRestaurant);
        restaurantManager.addRestaurant(restautantDansLeSysteme);

        assertTrue(restaurantManager.getRestaurants().contains(restautantDansLeSysteme));
    }

    @Quand("il valide")
    public void creeRestaurant() {
        String champUtilisateurPosition = champsUtilisateur.get("nom");

        if (!champUtilisateurPosition.isEmpty()) {
            Position positionRestaurant = new Position(champUtilisateurPosition);
            restaurant = new Restaurant(champsUtilisateur.get("nom"), positionRestaurant);
            restaurantManager.addRestaurant(restaurant);
        }
    }

    @Alors("{string} de position {string} est ajouté")
    public void verifierAjoutRuNice(String nomRestaurant, String position) {
        Position positionRestaurant = new Position(position);
        Restaurant restaurantCree = new Restaurant(nomRestaurant, positionRestaurant);
        restaurantManager.addRestaurant(restaurantCree);

        assertTrue(restaurantManager.getRestaurants().contains(restaurantCree));
    }

    @Alors("{string} de position {string} n'est pas ajouté une seconde fois")
    public void verifierNonDuplicite(String nom, String position) {
        Position positionRestaurant = new Position(position);
        Restaurant restaurant = new Restaurant(nom, positionRestaurant);
        List<Restaurant> restaurantList = restaurantManager.getRestaurants();
        assertEquals(1, restaurantList.stream().filter(r -> r.equals(restaurant)).count());
    }

    @Alors("{string} n'est pas ajouté")
    public void neContientPasRestaurant(String nomRestaurant) {
        Restaurant restaurant = new Restaurant(nomRestaurant);
        assertFalse(restaurantManager.getRestaurants().contains(restaurant));
    }


}
