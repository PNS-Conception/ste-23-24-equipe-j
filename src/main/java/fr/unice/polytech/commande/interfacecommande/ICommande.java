package fr.unice.polytech.commande;

import fr.unice.polytech.utilisateur.CompteUtilisateur;

/**
 * Classe abstraite des commandes
 * @author Equipe J
 */
public interface Commande {

    // Getters et setters

    /**
     * Retourne le créateur de la commande
     * @return le créateur de la commande
     */
    public CompteUtilisateur getCreateur();

    /**
     * Retourne l'état de suivi d'une commande
     * @return l'état de la commande
     */
    public EtatCommande getEtatCommande() ;

    /**
     * Modifie l'état de suivi d'une commande
     * @param etatCommande le nouvel état de la commande
     */
    public abstract void setEtatCommande(EtatCommande etatCommande);
}
