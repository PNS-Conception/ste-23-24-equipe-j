package fr.unice.polytech.livraison;

import fr.unice.polytech.utils.Date;
import fr.unice.polytech.utils.Horaire;
import fr.unice.polytech.utils.Position;

/**
 * Classe contenant les informations de livraison d'une commande
 * @author Equipe J
 */
public class InformationLivraison {
    private Date dateLivraison;
    private Horaire heureLivraison;
    private EtatLivraisonCommande etatLivraisonCommande;
    private Position lieuxLivraison;

    /**
     * Constructeur pour ajouter l'état de livraison uniquement
     */
    public InformationLivraison() {
        this(null, null, null);
    }

    /**
     * Constructeur par défaut
     * @param dateLivraison la date de livraison de la commande
     * @param heureLivraison l'heure de livraison de la commande
     * @param lieuxLivraison le lieux de livraison de la commande
     */
    public InformationLivraison(Date dateLivraison, Horaire heureLivraison, Position lieuxLivraison) {
        this.dateLivraison = dateLivraison;
        this.heureLivraison = heureLivraison;
        this.lieuxLivraison = lieuxLivraison;
        this.etatLivraisonCommande = EtatLivraisonCommande.NON_PRETE_POUR_LIVRAISON;
    }

    /**
     * Retourne la date de livraison de la commande doit être livré
     * @return la date de la commande
     */
    public Date getDateLivraison() {
        return dateLivraison;
    }

    /**
     * Retourne l'heure de livraison de la commande
     * @return l'horaire de la commande
     */
    public Horaire getHeureLivraison() {
        return heureLivraison;
    }

    /**
     * Retourne le lieu où la commande doit être livré
     * @return le lieu de la commande
     */
    public Position getLieuxLivraison() {
        return lieuxLivraison;
    }

    /**
     * Retourne l'état de livraison de la commande
     * @return l'état de livraison de la commande
     */
    public EtatLivraisonCommande getEtatLivraisonCommande() {
        return etatLivraisonCommande;
    }

    /**
     * Change le statut d'une livraison d'une commande pour qu'elle soit prête à être livré ou la commande est livré
     * @param etatLivraisonCommande le nouveau statut de la livraison de la commande
     */
    public void setEtatLivraisonCommande(EtatLivraisonCommande etatLivraisonCommande) {
        this.etatLivraisonCommande = etatLivraisonCommande;
    }

    public void setInformationLivraison(Date dateLivraison, Horaire heureLivraison, Position lieuxLivraison) {
        if (this.dateLivraison == null)
            this.dateLivraison = dateLivraison;

        if (this.heureLivraison == null)
            this.heureLivraison = heureLivraison;

        if (this.lieuxLivraison == null)
            this.lieuxLivraison = lieuxLivraison;
    }
}
