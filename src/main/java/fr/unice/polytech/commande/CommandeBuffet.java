package fr.unice.polytech.commande;

import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utilisateur.UserStatut;

/**
 * Classe d'une commande buffet qui hérite de tous les attributs d'une commande classique
 * @author Equipe J
 */
public class CommandeBuffet extends CommandeSimple{
    CompteUtilisateur destinataire;

    /**
     * Constructeur par défaut
     * @param idCommande l'identifiant de la commande
     * @param createurCommande le créateur de la commande
     * @param destinataireCommande le destinataire de la commande
     */
    public CommandeBuffet(long idCommande, CompteUtilisateur createurCommande, CompteUtilisateur destinataireCommande) {
        super(idCommande, createurCommande);

        if (destinataireCommande == null)
            throw new IllegalArgumentException("l'usager destinataire n'existe pas");
        else if (!createurCommande.getStatut().equals(UserStatut.PROFESSEUR) &&
                !createurCommande.getStatut().equals(UserStatut.PERSONNEL))
            throw new IllegalArgumentException("le créateur de cette commande n'est pas un staff de l'université");

        destinataire = destinataireCommande;
    }

    /**
     * Retourne le destinataire de la commande
     * @return le destinataire de la commande
     */
    public CompteUtilisateur getDestinataire() {
        return destinataire;
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
