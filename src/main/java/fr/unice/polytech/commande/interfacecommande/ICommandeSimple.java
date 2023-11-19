package fr.unice.polytech.commande.interfacecommande;

import fr.unice.polytech.nourriture.*;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.restaurant.RestaurantNonValideException;

import java.util.Map;
import java.util.Optional;

/**
 * Interface pour les commandes simples avec les méthodes pour organiser des plats et des menus
 * @author Equipe J
 */
public interface ICommandeSimple {

    /**
     * Ajoute un menu à la commande
     * @param menuPlat le menu ou plat à ajouter à la commande
     * @param typeMenuPlat le type du menu <code>menu</code> ou du plat <code>plat</code>
     * @throws RestaurantNonValideException si le restaurant du menu ou du plat n'est pas le même que celui de la commande
     */
    void ajoutMenuPlat(Menu menuPlat, TypeMenuPlat typeMenuPlat) throws RestaurantNonValideException;

    /**
     * Supprime un menu ou un plat de la commande
     * @param menuPlat le menu ou le plat à supprimer de la commande
     */
    boolean supprimerMenuPlat(MenuPlat menuPlat);

    /**
     * Retourne les plats et les menus avec leur quantité dans la commande
     * @return les plats et les menus avec leur quantité dans la commande
     */
    Map<MenuPlat, Integer> getMenuPlats();

    /**
     * Retourne le restaurant de la commande
     * @return le restaurant de la commande
     */
    Optional<Restaurant> getRestaurant();
}
