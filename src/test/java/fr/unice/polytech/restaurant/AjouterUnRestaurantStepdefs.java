package fr.unice.polytech.restaurant;

import io.cucumber.java.fr.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class AjouterUnRestaurantStepdefs {
    Restaurant restaurant;
    RestaurantSystem restaurantSystem;
    Map<String, String> champsUtilisateur = new HashMap<>();

    @Etantdonnéque("l' Administrateur du campus est authentifié")
    public void administrateurEstAuthentifie() {
        restaurantSystem = new RestaurantSystem();
    }

    @Et("les champs nom,position:")
    public void ajouterUnRestaurantNormal(Map<String, String> champs) {
        assertTrue(champs.containsKey("nom") && champs.containsKey("position"));
        champsUtilisateur = champs;
    }

    @Et("le champ nom:")
    public void ajouterUnRestaurantUniquementAvecLeNom(Map<String, String> champs) {
        assertTrue(champs.containsKey("nom") && champs.size() == 2);
        champsUtilisateur = champs;
    }

    @Etque("{string} de position {string} existe déjà dans le système")
    public void restaurantexisteDeja(String nom, String position) {
        restaurantSystem.addRestaurant(new Restaurant(nom, position));
    }

    @Quand("il valide")
    public void creeRestaurant() {
        restaurant = new Restaurant(champsUtilisateur.get("nom"), champsUtilisateur.get("position"));
        restaurantSystem.addRestaurant(restaurant);
    }

    @Alors("{string} de  position {string} est ajouté")
    public void verifierAjoutRuNice(String nomRestaurant, String position) {
        Restaurant restaurantCree = new Restaurant(nomRestaurant, position);
        assertTrue(restaurantSystem.contientRestaurant(restaurantCree));
    }

    @Alors("{string} de position {string} n'est pas ajouté une seconde fois")
    public void verifierNonDuplicite(String nom, String position) {
        Restaurant restaurant = new Restaurant(nom, position);
        List<Restaurant> restaurantList = restaurantSystem.getRestaurants();
        assertEquals(1, restaurantList.stream().filter(r -> r.equals(restaurant)).toList().size());
    }

    @Alors("{string} n'est pas ajouté")
    public void neContientPasRestaurant(String nomRestaurant) {
        System.out.println(restaurantSystem.getRestaurant(nomRestaurant));
        Restaurant restaurant = new Restaurant(nomRestaurant);
        assertTrue(restaurantSystem.getRestaurants().contains(restaurant));
    }


}
