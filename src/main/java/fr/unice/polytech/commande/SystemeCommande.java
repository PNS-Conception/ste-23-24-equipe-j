package fr.unice.polytech.commande;

import fr.unice.polytech.builder.*;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import java.util.*;

public class SystemeCommande {
    private long idCommande;
    private final Map<Integer, CommandeAvecID> commandes;

    // Constructeur

    /**
     * Constructeur par défaut
     */
    public SystemeCommande() {
        idCommande = 0;
        commandes = new HashMap<>();
    }

    // Methodes

    /**
     * Créer une commande simple
     * @param createurCommande le createur de la commande
     * @param typeCommande le type de la commande
     * @return la commande créée
     */
    public CommandeAvecID creerCommandeSimpleMultipleGroupe(CompteUtilisateur createurCommande,
                                                            TypeCommandeSimple typeCommande) {
        BuilderCommandeSimpleMultipleGroupe builder = new BuilderCommandeSimpleMultipleGroupe(idCommande++, createurCommande);
        CommandeAvecID commande = builder.buildTypeCommandeSimple(typeCommande).build();
        commandes.put((int) commande.getIdCommande(), commande);
        return commande;
    }

    // Méthodes

    /**
     * Récupère la commande par un id
     * @param idCommande l'id de la commande
     * @return la commande si l'id existe sinon <code>null</code>
     */
    public Commande getCommandeParId(int idCommande) {
        return commandes.get(idCommande);
    }

    /**
     * Récupère la liste des commandes en préparation du restaurant
     * @return la liste des commandes en préparation du restaurant
     */
    public List<Commande> getCommandeEnPreparationRestaurant(Restaurant restaurant) {
        List<Commande> commandesRestaurant = new ArrayList<>();

        for (Commande commande : commandes.values()) {
            if (commande.getEtatCommande().equals(EtatCommande.EN_PREPARATION)) {
                Optional<Restaurant> restaurantCommande = ((CommandeSimpleAvecID) commande).getRestaurant();

                if (restaurantCommande.isPresent() && restaurantCommande.get().equals(restaurant))
                    commandesRestaurant.add(commande);
            }
        }

        return commandesRestaurant;
    }

    /**
     * Récupère la liste des commandes du restaurant
     * @param restaurant le restaurant où on veut récupérer ses commandes
     * @return la liste des commandes du restaurant
     */
    public List<Commande> getCommandesRestaurant(Restaurant restaurant) {
        List<Commande> commandesRestaurant = new ArrayList<>();

        for (Commande commande : commandes.values()) {
            Optional<Restaurant> restaurantCommande = ((CommandeSimpleAvecID) commande).getRestaurant();

            if (restaurantCommande.isPresent() && restaurantCommande.get().equals(restaurant))
                commandesRestaurant.add(commande);
        }

        return commandesRestaurant;
    }
}
