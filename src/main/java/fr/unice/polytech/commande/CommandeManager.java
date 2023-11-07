package fr.unice.polytech.commande;

import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.utilisateur.CompteUtilisateur;

import java.util.*;

/**
 * Le gestionnaire de commande contenant toutes les commandes
 * @author Equipe J
 */
public class CommandeManager {
    private final Set<Commande> commandes;
    private final Set<Integer> idCommandesGroupees;
    private int id;

    /**
     * Constructeur par défaut
     */
    public CommandeManager() {
        commandes = new HashSet<>();
        idCommandesGroupees = new HashSet<>();
        id = 0;
    }

    /**
     * Création d'une commande pour un utilisateur dans un restaurant
     * @param compteUtilisateur le CompteUtilisateur qui passe la commande
     */
    public CommandeIndividuelle creerCommande(CompteUtilisateur compteUtilisateur) {
        CommandeIndividuelle commande = new CommandeIndividuelle(compteUtilisateur, id++);
        commandes.add(commande);
        return commande;
    }

    /**
     * Création d'une commande groupée
     * @param id l'id de la commande groupée
     * @return la commande groupée
     */
    public CommandeGroupee creerCommandeGroupee(int id) {
        if (idCommandesGroupees.contains(id))
            throw new IllegalArgumentException("L'id de la commande groupe existe déjà");

        idCommandesGroupees.add(id);
        CommandeGroupee commandeGroupee = new CommandeGroupee(id);
        commandes.add(commandeGroupee);

        return commandeGroupee;
    }

    /**
     * Supprime une commande individuelle dans une commande groupée
     * @param idGroupe l'identifiant de la commande groupée
     * @param compteUtilisateurCommande l'utilisateur de la commande
     */
    public void supprimerCommandeIndividuelleGroupee(int idGroupe, CompteUtilisateur compteUtilisateurCommande) {
        CommandeGroupee commandeGroupee = getCommandeGroupeeParID(idGroupe);
        Optional<CommandeIndividuelle> optCommande = commandeGroupee.getCommandeUtilisateur(compteUtilisateurCommande);

        if (optCommande.isPresent()) {
            CommandeIndividuelle commande = optCommande.get();
            commandeGroupee.supprimerCommandeIndividuelle(commande);
            commandes.remove(commande);
        }
    }

    /**
     * Récupère toutes les commandes individuelles
     * @return la liste des commandes individuelles
     */
    private List<CommandeIndividuelle> getCommandesIndividuelles() {
        List<CommandeIndividuelle> commandesIndividuelles = new ArrayList<>();
        for (Commande commande : commandes)
            commandesIndividuelles.addAll(commande.getCommandes());

        return commandesIndividuelles;
    }

    /**
     * Récupère la commande par un id
     * @param id l'id de la commande
     * @return la commande si l'id existe sinon <code>null</code>
     */
    public CommandeIndividuelle getCommandeParID(int id) {
        List<CommandeIndividuelle> commandesIndividuelles = getCommandesIndividuelles();
        return commandesIndividuelles.stream().filter(commande -> commande.getId() == id).findFirst().orElse(null);
    }

    /**
     * Récupère la commande groupée par un id
     * @param id l'id de la commande groupée
     * @return la commande groupée si l'id existe sinon <code>null</code>
     */
    public CommandeGroupee getCommandeGroupeeParID(int id) {
        CommandeGroupee commandeGroupee = (CommandeGroupee) commandes.stream()
                .filter(commande -> commande.getId() == id && commande.estGroupee())
                .findFirst().orElse(null);

        if (commandeGroupee == null)
            throw new IllegalArgumentException("Pas d'id pour cette commande groupé");

        return commandeGroupee;
    }

    /**
     * Payer la commande
     * @param commandeIndividuelle la commande à payer
     * @return <code>true</code> si la commande a été payée
     */
    public boolean payerCommande(CommandeIndividuelle commandeIndividuelle) {
        commandeIndividuelle.setEtatCommande(EtatCommande.EN_PREPARATION);
        return true;
    }

    /**
     * Récupère la liste des commandes en préparation du restaurant
     * @return la liste des commandes en préparation du restaurant
     */
    public List<CommandeIndividuelle> getCommandeEnPreparationRestaurant(Restaurant restaurant) {
        List<CommandeIndividuelle> commandeIndividuelles = getCommandesIndividuelles();
        return commandeIndividuelles.stream()
                .filter(commande -> commande.getEtatCommande() == EtatCommande.EN_PREPARATION &&
                        restaurant.equals(commande.getRestaurant())).toList();
    }

    /**
     * Récupère la liste des commandes du restaurant
     * @param restaurant le restaurant où on veut récupérer ses commandes
     * @return la liste des commandes du restaurant
     */
    public List<CommandeIndividuelle> getCommandeRestaurant(Restaurant restaurant) {
        List<CommandeIndividuelle> commandeIndividuelles = getCommandesIndividuelles();
        return commandeIndividuelles.stream().filter(commande -> restaurant.equals(commande.getRestaurant())).toList();
    }
}
