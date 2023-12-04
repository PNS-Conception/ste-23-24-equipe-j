package fr.unice.polytech.commande;

import fr.unice.polytech.exceptions.RestaurantNonValideException;
import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.restaurant.Restaurant;

import java.util.Map;

/**
 * Classe static qui permet d'ajouter des commandes dans une liste de commandes
 * @author Equipe J
 */
public class GestionCommande {

    /**
     * Constructeur par défaut
     * @throws IllegalStateException erreur renvoyé si le constructeur est appelé
     */
    private GestionCommande() {
        throw new IllegalStateException("utility class");
    }

    /**
     * Ajout d'un plat ou menu dans la liste de menus ou plats
     * @param menuPlats la liste des menus ou plats
     * @param menuPlat le menu ou plat à ajouter
     * @param restaurant le restaurant de la commande pour vérifier si c'est le même restaurant que celui de la commande
     * @throws RestaurantNonValideException si le restaurant n'est pas le même que celui de la commande
     */
    public static void ajoutMenuPlat(Map<MenuPlat, Integer> menuPlats, MenuPlat menuPlat, Restaurant restaurant)
            throws RestaurantNonValideException {
        if (!restaurant.equals(menuPlat.getRestaurant()))
            throw new RestaurantNonValideException();
        int nombre = menuPlats.getOrDefault(menuPlat, 0);
        menuPlats.put(menuPlat, nombre + 1);
    }

    /**
     * Supprime de la liste de menus et plats le menu ou le plat
     * @param menuPlats la liste de menus et plats
     * @param menuPlat le menu ou plat à supprimer
     * @return <code>true</code> si le menu ou plat à était supprimer
     */
    public static boolean supprimerMenuPlat(Map<MenuPlat, Integer> menuPlats, MenuPlat menuPlat) {
        Integer nombre = menuPlats.get(menuPlat);

        if (nombre != null) {
            if (nombre > 1)
                menuPlats.put(menuPlat, nombre - 1);
            else
                menuPlats.remove(menuPlat);

            return true;
        }

        return false;
    }
}
