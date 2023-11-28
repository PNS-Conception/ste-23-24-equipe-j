package fr.unice.polytech.nourriture;

import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.utilisateur.StatusUtilisateur;

/**
 * Interface qui regroupe les menus et les plats pour les ajouter tous les deux dans une commande
 * @author Equipe J
 */
public interface MenuPlat {
    /**
     * Récupérer le nom du plat ou du menu
     * @return Le nom du plat ou du menu
     */
    String getNom();

    /**
     * Récupère le prix du plat ou du menu
     * @return le prix du plat ou du menu
     */
    double getPrix(StatusUtilisateur statusUtilisateur);
    void setPrix(double newPrix);
    void setPrixStatus(StatusUtilisateur statusUtilisateur, double newPrixStatus);

    /**
     * Ajoute le restaurant au plat ou au menu pour pouvoir savoir de quel restaurant vient le plat ou le menu
     * @param restaurant
     */
    void setRestaurant(Restaurant restaurant);

    /**
     * Récupère le restaurant du plat ou du menu
     * @return le restaurant du plat ou du menu
     */
    Restaurant getRestaurant();
}
