package fr.unice.polytech.commande;

import fr.unice.polytech.commande.interfacecommande.ICommande;
import fr.unice.polytech.utilisateur.CompteUtilisateur;

import java.util.Objects;

/**
 * Classe abstraite d'une commande général avec un ID
 * @author Equipe J
 */
public abstract class CommandeAvecID  implements ICommande {
    private final long idCommande;
    protected final CompteUtilisateur createur;
    protected EtatCommande etatCommande;

    // Constructeur

    /**
     * Constructeur par défaut
     * @param idCommande l'identifiant de la commande
     * @param createurCommande le créateur de la commande
     */
    protected CommandeAvecID(long idCommande, CompteUtilisateur createurCommande) {
        this.idCommande = idCommande;
        createur = createurCommande;
    }

    // Getters et setters

    /**
     * Retourne l'identifiant de la commande
     * @return l'identifiant de la commande
     */
    public long getIdCommande() {
        return idCommande;
    }

    @Override
    public CompteUtilisateur getCreateur() {
        return createur;
    }

    @Override
    public void setEtatCommande(EtatCommande etatCommande) {
        this.etatCommande = etatCommande;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        else if (!(o instanceof CommandeAvecID))
            return false;
        CommandeAvecID commande = (CommandeAvecID) o;
        return idCommande == commande.idCommande;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idCommande);
    }
}
