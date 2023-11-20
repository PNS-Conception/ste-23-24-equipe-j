package fr.unice.polytech.utils;

/**
 * Classe qui permet de payer une commande
 * @author Equipe J
 */
public class PaiementService {
    public static boolean payerCommande(double prix) {
        return prix >= 0;
    }
}
