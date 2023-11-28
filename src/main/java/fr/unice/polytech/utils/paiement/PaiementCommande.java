package fr.unice.polytech.utils.paiement;

/**
 * Classe contenant le prix d'une commande
 * @author Equipe J
 */
public class PaiementCommande {
    private double prix;
    private int rate;

    /**
     * Constructeur par défaut
     */
    public PaiementCommande() {
        this(0);
    }

    public PaiementCommande(int discount) {
        this.prix = 0;
        this.rate = discount;
    }

    public void setDiscount(int discount) {
        this.rate = discount;
    }

    /**
     * Retourne le prix de la commande
     * @return le prix de la commande
     */
    public double getPrix() {
        return Math.max(prix*(100-rate)/100, 0);
    }

    /**
     * Ajoute le prix d'un menu ou plat d'une commande
     * @param prix le prix à ajouter
     */
    public void ajoutPrix(double prix) {
        this.prix += prix;
    }

    /**
     * Retire le prix d'un menu ou plat d'une commande
     * @param prix le prix à retirer
     */
    public void retraitPrix(double prix) {
        this.prix -= prix;
    }

    /**
     * Permet de payer la commande
     * @return <code>true</code> si la commande est payée, <code>false</code> sinon
     */
    public boolean payerCommande() {
        return PaiementService.payerCommande(prix);
    }
}
