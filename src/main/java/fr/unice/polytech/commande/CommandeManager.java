package fr.unice.polytech.commande;

import fr.unice.polytech.restaurant.Restaurant;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Le gestionnaire de commande contenant toutes les commandes
 * @author Equipe J
 */
public class CommandeManager {
    private Set<Commande> commandes;
    private int id;

    /**
     * Constructeur par défaut
     */
    public CommandeManager() {
        commandes = new HashSet<>();
        id = 0;
    }

    /**
     * Ajout d'une commande dans le set de Commande, en lui ajoutant un id
     * @param commande la commande à ajouter
     */
    public void ajoutCommande(Commande commande) {
        commande.setId(id++);
        commandes.add(commande);
    }

    /**
     * Récupère la commande par un id
     * @param id l'id de la commande
     * @return la commande si l'id existe sinon <code>null</code>
     */
    public Commande getCommandeParID(int id) {
        return commandes.stream().filter(commande -> commande.getId() == id).findFirst().orElse(null);
    }

    /**
     * Récupère la liste des commandes du restaurant
     * @param restaurant le restaurant où on veut récupérer ses commandes
     * @return la liste des commandes du restaurant
     */
    public List<Commande> getCommandeRestaurant(Restaurant restaurant) {
        return commandes.stream().filter(commande -> restaurant.equals(commande.getRestaurant())).toList();
    }
}
