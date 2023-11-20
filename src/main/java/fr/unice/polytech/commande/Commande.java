package fr.unice.polytech.commande;

import fr.unice.polytech.utilisateur.CompteUtilisateur;

/**
 * Classe abstraite des commandes
 * @author Equipe J
 */
public abstract class Commande {
    protected final CompteUtilisateur createur;
    protected EtatCommande etatCommande;

    // Constructeur
    /**
     * Constructeur par défaut
     * @param createur le createur de la commande
     */
    protected Commande(CompteUtilisateur createur) {
        this.createur = createur;
        etatCommande = EtatCommande.EN_ATTENTE;
    }

    // Getters et setters

    /**
     * Retourne le créateur de la commande
     * @return le créateur de la commande
     */
    public CompteUtilisateur getCreateur() {
        return createur;
    }

    /**
     * Retourne l'état de suivi d'une commande
     * @return l'état de la commande
     */
    public EtatCommande getEtatCommande() {
        return etatCommande;
    }

    /**
     * Modifie l'état de suivi d'une commande
     * @param etatCommande le nouvel état de la commande
     */
    public abstract void setEtatCommande(EtatCommande etatCommande);
}
