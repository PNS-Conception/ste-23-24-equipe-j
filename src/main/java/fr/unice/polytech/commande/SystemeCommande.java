package fr.unice.polytech.commande;

import fr.unice.polytech.builder.*;
import fr.unice.polytech.commande.interfacecommande.ICommande;
import fr.unice.polytech.commande.interfacecommande.ICommandeAjoutable;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import java.util.*;

/**
 * Système de commande qui possède toutes les commandes de la création à la livraison
 * @author Equipe J
 */
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

    /**
     * Créer une commande simple pour une commande groupe
     * @param utilisateur le créateur de la commande
     * @param typeCommandeAjoutable le type de la commande (simple)C
     * @param idCommandeGroupe l'id de la commande groupe
     * @return la commande ajoutée dans la commande groupe
     */
    public ICommandeAjoutable creerCommandeAjoutable(CompteUtilisateur utilisateur,
                                                     TypeCommandeAjoutable typeCommandeAjoutable,
                                                     int idCommandeGroupe) {
        CommandeAvecID commande = commandes.get(idCommandeGroupe);


        if (commande == null || commande.estCommandeSimple())
            return null;
        BuilderCommandeSimpleMultiplePourGroupe builder = new BuilderCommandeSimpleMultiplePourGroupe(idCommande++,
                utilisateur, (ACommandeGroupe) commande);
        return builder.setTypeCommandeSimple(typeCommandeAjoutable).build();
    }

    /**
     * Crée une commande Afterwork
     * @param utilisateur l'utilisateur de la commande
     * @param nombrePersonne le nombre de personne pour la commande Afterwork
     * @return la commande crée
     */
    public CommandeAfterworks creerCommandeAfterwork(CompteUtilisateur utilisateur, int nombrePersonne) {
        BuilderCommandeSimpleMultipleGroupe builder = new BuilderCommandeSimpleMultipleGroupe(idCommande++, utilisateur);

        CommandeAfterworks commande = (CommandeAfterworks) builder.buildTypeCommandeSimple(TypeCommandeSimple.AFTERWORKS)
                .buildNombrePersonneCommandeAfterworks(nombrePersonne).build();

        commandes.put((int) commande.getIdCommande(), commande);
        return commande;
    }

    /**
     * Supprime une commande du système
     * @param idCommande l'id de la commande
     */
    public void supprimerCommande(int idCommande) {
        commandes.remove(idCommande);
    }


    /**
     * Récupère la commande par un id
     * @param idCommande l'id de la commande
     * @return la commande si l'id existe sinon <code>null</code>
     */
    public CommandeAvecID getCommandeParId(int idCommande) {
        return commandes.get(idCommande);
    }

    /**
     * Récupère la liste des commandes en préparation du restaurant
     * @return la liste des commandes en préparation du restaurant
     */
    public List<ICommande> getCommandeEnPreparationRestaurant(Restaurant restaurant) {
        List<ICommande> commandesRestaurant = new ArrayList<>();

        for (CommandeAvecID commande : commandes.values()) {
            if ( commande.estCommandeSimple() && commande.getEtatCommande().equals(EtatCommande.EN_PREPARATION)) {
                Optional<Restaurant> restaurantCommande = ((CommandeSimpleAvecID) commande).getRestaurant();

                if (restaurantCommande.isPresent() && restaurantCommande.get().equals(restaurant))
                    commandesRestaurant.add(commande);
            }
            else if(!commande.estCommandeSimple())
                commandesRestaurant.addAll(((ACommandeGroupe) commande).getCommandesEnPreparationRestaurant(restaurant));
        }

        return commandesRestaurant;
    }

    /**
     * Récupère la liste des commandes du restaurant
     * @param restaurant le restaurant où on veut récupérer ses commandes
     * @return la liste des commandes du restaurant
     */
    public List<ICommande> getCommandesRestaurant(Restaurant restaurant) {
        List<ICommande> commandesRestaurant = new ArrayList<>();

        for (CommandeAvecID commande : commandes.values()) {
            Optional<Restaurant> restaurantCommande = ((CommandeSimpleAvecID) commande).getRestaurant();

            if (restaurantCommande.isPresent() && restaurantCommande.get().equals(restaurant))
                commandesRestaurant.add(commande);
        }

        return commandesRestaurant;
    }
}
