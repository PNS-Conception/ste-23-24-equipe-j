package fr.unice.polytech.restaurant;

import java.util.List;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Classe contenant tous les Restaurants
 * @author Equipe J
 */
public class RestaurantManager {
    private final Set<Restaurant> restaurants;

    // Constructeur
    /**
     * Constructeur par défaut
     */
    public RestaurantManager() {
        restaurants = new HashSet<>();
    }

    // Accesseur
    /**
     * Récupère les restaurants
     * @return les restaurants sous forme de liste
     */
    public List<Restaurant> getRestaurants() {
        return restaurants.stream().toList();
    }

    /**
     * Récupère le restaurant
     * @param nomRestaurant nom du restaurant à récupérer
     * @return Le restaurant de nom "nomRestaurant"
     */
    public Restaurant getRestaurantParNom(String nomRestaurant){
        Optional<Restaurant> oPTr = restaurants.stream()
                .filter(r -> r.getNomRestaurant().equals(nomRestaurant)).findFirst();

        if (oPTr.isEmpty())
            throw new IllegalArgumentException("Il n'existe pas de restaurant de ce nom là");

        return oPTr.get();
    }

    /**
     * Ajoute un restaurant
     * @param r le restaurant à ajouter
     * @return <code>true</code> si le restaurant est ajouté sinon <code>false</code>
     */
    public boolean addRestaurant(Restaurant r) {
        return restaurants.add(r);
    }
}
