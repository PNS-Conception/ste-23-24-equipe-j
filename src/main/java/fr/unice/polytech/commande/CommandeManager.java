package fr.unice.polytech.commande;

import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.restaurant.TokenException;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.Token;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Le gestionnaire de commande contenant toutes les commandes
 * @author Equipe J
 */
public class CommandeManager {
    private final Set<Commande> commandes;
    private int id;

    /**
     * Constructeur par défaut
     */
    public CommandeManager() {
        commandes = new HashSet<>();
        id = 0;
    }

    /**
     * Création d'une commande pour un utilisateur dans un restaurant
     * @param compteUtilisateur le CompteUtilisateur qui passe la commande
     */
    public Commande creerCommande(CompteUtilisateur compteUtilisateur) {
        Commande commande = new Commande(compteUtilisateur, id++);
        commandes.add(commande);
        return commande;
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
     * Payer la commande
     * @param commande la commande à payer
     * @return <code>true</code> si la commande a été payée
     */
    public boolean payerCommande(Commande commande, Token token) {
        try {
            commande.setEtatCommande(EtatCommande.EN_PREPARATION, token);
            return true;
        } catch (TokenException e) {
            return false;
        }
    }

    /**
     * Récupère la liste des commandes en préparation du restaurant
     * @return la liste des commandes en préparation du restaurant
     */
    public List<Commande> getCommandeEnPreparationRestaurant(Restaurant restaurant) {
        return commandes.stream()
                .filter(commande -> commande.getEtatCommande() == EtatCommande.EN_PREPARATION &&
                        restaurant.equals(commande.getRestaurant())).toList();
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