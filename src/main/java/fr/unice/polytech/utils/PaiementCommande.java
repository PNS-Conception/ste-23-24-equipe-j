package fr.unice.polytech.utils;

import fr.unice.polytech.nourriture.MenuPlat;

import java.util.Map;

/**
 * Classe contenant le prix d'une commande
 * @author Equipe J
 */
public class PaiementCommande {
    private double prix;

    /**
     * Constructeur par défaut
     */
    public PaiementCommande() {
        prix = 0;
    }

    /**
     * Retourne le prix de la commande
     * @return le prix de la commande
     */
    public double getPrix(Map<MenuPlat, Integer> produitsCommandes) {
        return Reduction.getReduction(produitsCommandes);
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
