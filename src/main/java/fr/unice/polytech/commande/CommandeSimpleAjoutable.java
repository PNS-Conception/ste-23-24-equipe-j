package fr.unice.polytech.commande;

import fr.unice.polytech.commande.interfacecommande.ICommandeAjoutablePayable;
import fr.unice.polytech.livraison.InformationLivraison;
import fr.unice.polytech.utilisateur.CompteUtilisateur;

/**
 * Classe d'une commande simple ajoutable dans une commande groupe
 * @author Equipe J
 */
public class CommandeSimpleAjoutable extends CommandeSimplePayable implements ICommandeAjoutablePayable {
    private final ACommandeGroupe commandeGroupe;

    /**
     * Constructeur par défaut
     * @param idCommande l'identifiant de la commande
     * @param createurCommande le créateur de la commande
     */
    public CommandeSimpleAjoutable(long idCommande, CompteUtilisateur createurCommande, ACommandeGroupe commandeGroupe) {
        super(idCommande, createurCommande);
        commandeGroupe.ajouterCommande(this);
        this.commandeGroupe = commandeGroupe;
    }

    @Override
    public InformationLivraison getInformationLivraison() {
        return this.commandeGroupe.getInformationLivraison();
    }

    @Override
    public void setEtatCommande(EtatCommande etatCommande) {
        super.setEtatCommande(etatCommande);
        commandeGroupe.updateStatusCommande();
    }

    @Override
    public boolean estPayable() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
