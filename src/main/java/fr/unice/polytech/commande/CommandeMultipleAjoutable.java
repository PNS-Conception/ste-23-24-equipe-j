package fr.unice.polytech.commande;


import fr.unice.polytech.commande.interfacecommande.ICommandeAjoutablePayable;
import fr.unice.polytech.livraison.InformationLivraison;
import fr.unice.polytech.observer.EventManager;
import fr.unice.polytech.utilisateur.CompteUtilisateur;

/**
 * Commande multiple ajoutable dans une commande groupe
 * @author Equipe J
 */
public class CommandeMultipleAjoutable extends AbstractCommandeMultiple implements ICommandeAjoutablePayable {

    private final ACommandeGroupe commandeGroupe;

    /**
     * Constructeur par défaut
     * @param idCommande l'identifiant de la commande
     * @param createurCommande le créateur de la commande
     * @param commandeGroupe la commande groupe qui lui est associé
     */
    public CommandeMultipleAjoutable(long idCommande, CompteUtilisateur createurCommande, ACommandeGroupe commandeGroupe) {
        super(idCommande, createurCommande);
        this.commandeGroupe = commandeGroupe;
    }

    @Override
    public void setEtatCommande(EtatCommande etatCommande) {
        this.etatCommande = etatCommande;
        commandeGroupe.updateStatusCommande();
        EventManager.notify(this, etatCommande.name());
    }

    @Override
    public boolean estPayable() {
        return true;
    }

    @Override
    public InformationLivraison getInformationLivraison() {
        return null;
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
