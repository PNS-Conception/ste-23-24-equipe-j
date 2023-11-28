package fr.unice.polytech.commande.interfacecommande;

import fr.unice.polytech.exceptions.TokenException;
import fr.unice.polytech.utils.Token;

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
    void payerCommande(Token token) throws TokenException;

    void checkDiscount();
}
