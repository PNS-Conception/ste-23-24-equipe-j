package fr.unice.polytech.utils;

/**
 * Classe qui permet de payer une commande
 * @author Equipe J
 */
public class PaiementService {
    private PaiementService() {
        throw new IllegalArgumentException("Utility class");
    }

    public static boolean payerCommande(double prix) {
        return prix >= 0;
    }
}
