package fr.unice.polytech.commande.interfacecommande;

/**
 * Interface pour les commande payables
 * @author Equipe J
 */
public interface IPayable {
    /**
     * Retourne le prix de la commande
     * @return le prix de la commande
     */
    double getPrix();

    /**
     * Permet de payer la commande
     */
    void payerCommande();
}
