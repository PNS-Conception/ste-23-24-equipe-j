package fr.unice.polytech.commande;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Classe d'une commande de plusieurs personnes
 * @author Equipe J
 */
public class CommandeGroupee extends Commande{
    private final Set<CommandeIndividuelle> commandes;

    /**
     * Constructeur par défaut de CommandeGroupee
     * @param id l'identifiant de la commandeGroupee
     */
    public CommandeGroupee(int id) {
        super(id);
        commandes = new HashSet<>();
    }

    /**
     * Ajoute une commande individuelle à la commande groupée
     * @param commandeIndividuelle la commande individuelle à ajouter
     */
    public void ajoutCommandeIndividuelle(CommandeIndividuelle commandeIndividuelle) {
        commandes.add(commandeIndividuelle);
    }

    /**
     * Supprime une commande individuelle de la commande groupée
     * @param commandeIndividuelle la commande individuelle à supprimer
     */
    public void supprimerCommandeIndividuelle(CommandeIndividuelle commandeIndividuelle) {
        commandes.remove(commandeIndividuelle);
    }

    @Override
    public List<CommandeIndividuelle> getCommandes() {
        List<CommandeIndividuelle> commandeIndividuelles = new ArrayList<>();
        for (CommandeIndividuelle commandeIndividuelle : commandes)
            commandeIndividuelles.addAll(commandeIndividuelle.getCommandes());

        return commandeIndividuelles;
    }

    @Override
    public double getPrix() {
        double prix = 0;
        for (CommandeIndividuelle commandeIndividuelle : commandes)
            prix += commandeIndividuelle.getPrix();
        return prix;
    }

    @Override
    public EtatCommande getEtatCommande() {
        EtatCommande etatCommande = EtatCommande.PRETE;

        for(CommandeIndividuelle commandeIndividuelle : commandes) {
            if (commandeIndividuelle.getEtatCommande() == EtatCommande.EN_ATTENTE) {
                etatCommande = EtatCommande.EN_ATTENTE;
                break;
            }
            else if (commandeIndividuelle.getEtatCommande() == EtatCommande.EN_PREPARATION)
                etatCommande = EtatCommande.EN_PREPARATION;
        }

        return etatCommande;
    }

    @Override
    public boolean estGroupee() {
        return true;
    }

    // Equals et hashCode

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o))
            return false;
        CommandeGroupee commandeGroupee = (CommandeGroupee) o;
        return commandeGroupee.estGroupee();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
