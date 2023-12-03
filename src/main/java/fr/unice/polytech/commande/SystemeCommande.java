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
     * @param typeCommandeSimple le type de la commande
     * @return la commande créée
     */
    public CommandeAvecID creerCommandeSimpleMultipleGroupe(CompteUtilisateur createurCommande,
                                                            TypeCommandeSimple typeCommandeSimple) {
        BuilderCommandeSimpleMultipleGroupe builder = new BuilderCommandeSimpleMultipleGroupe(idCommande++, createurCommande);
        CommandeAvecID commande = builder.buildTypeCommandeSimple(typeCommandeSimple).build();
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

        ICommandeAjoutable commande2 = builder.setTypeCommandeSimple(typeCommandeAjoutable).build();
        CommandeAvecID commande3 = (CommandeAvecID) commande2;
        commandes.put((int) commande3.getIdCommande(), commande3);

        return commande2;
    }

    /**
     * Créer une commande buffet
     * @param createur le créateur de la commande
     * @param destinataire le destinataire de la commande
     * @return la commande buffet
     */
    public CommandeBuffet creerCommandeBuffet(CompteUtilisateur createur, CompteUtilisateur destinataire) {
        BuilderCommandeSimpleMultipleGroupe builder = new BuilderCommandeSimpleMultipleGroupe(idCommande++, createur);
        CommandeBuffet commandeBuffet = (CommandeBuffet) builder.buildTypeCommandeSimple(TypeCommandeSimple.BUFFET)
                .buildDestinataire(destinataire).build();

        commandes.put((int) commandeBuffet.getIdCommande(), commandeBuffet);
        return commandeBuffet;
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
