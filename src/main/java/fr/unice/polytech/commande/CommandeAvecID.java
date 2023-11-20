package fr.unice.polytech.commande;

import fr.unice.polytech.utilisateur.CompteUtilisateur;

import java.util.Objects;

/**
 * Classe abstraite d'une commande général avec un ID
 * @author Equipe J
 */
public abstract class CommandeAvecID  extends Commande {
    private final long idCommande;

    // Constructeur

    /**
     * Constructeur par défaut
     * @param idCommande l'identifiant de la commande
     * @param createurCommande le créateur de la commande
     */
    protected CommandeAvecID(long idCommande, CompteUtilisateur createurCommande) {
        super(createurCommande);
        this.idCommande = idCommande;
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
