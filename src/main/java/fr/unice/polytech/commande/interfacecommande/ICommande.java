package fr.unice.polytech.commande.interfacecommande;

import fr.unice.polytech.commande.EtatCommande;
import fr.unice.polytech.livraison.InformationLivraison;
import fr.unice.polytech.utilisateur.CompteUtilisateur;

/**
 * Classe abstraite des commandes
 * @author Equipe J
 */
public interface ICommande {

    // Getters et setters

    /**
     * Retourne le créateur de la commande
     * @return le créateur de la commande
     */
    CompteUtilisateur getCreateur();

    InformationLivraison getInformationLivraison();

    /**
     * Retourne l'état de suivi d'une commande
     * @return l'état de la commande
     */
    EtatCommande getEtatCommande() ;

    /**
     * Modifie l'état de suivi d'une commande
     * @param etatCommande le nouvel état de la commande
     */
    void setEtatCommande(EtatCommande etatCommande);

    // Méthode

    /**
     * Retourne si la commande est une commande simple
     * @return <code>true</code> si la commande est une commande simple, <code>false</code> sinon
     */
    boolean estCommandeSimple();

    boolean estLivrable();
}
