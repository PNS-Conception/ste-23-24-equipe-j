package fr.unice.polytech.utils;

import fr.unice.polytech.nourriture.MenuPlat;

import java.util.Map;

/**
 * Classe traitant la réduction d'une commande
 * @author Equipe J
 */
public class Reduction {
    public static final double R = 0.05;
    public static final double N = 10;

    /**
     * Méthode privé
     * @throws IllegalArgumentException class static
     */
    private Reduction() {
        throw new IllegalArgumentException("Utility class");
    }

    /**
     * Méthode pour avoir les réductions d'une commande
     * @param produitsCommandes les menus et/ou plats des commandes
     * @return le prix réduit si on peut obtenir la réduction
     */
    protected static double getReduction(Map<MenuPlat, Integer> produitsCommandes) {
        double sum = 0;
        int nombreProduits = 0;

        for (Map.Entry<MenuPlat, Integer> entry : produitsCommandes.entrySet()) {
            sum += entry.getValue() * entry.getKey().getPrix();
            nombreProduits += entry.getValue();
        }

        if (nombreProduits >= N)
            return sum * (1 - R);
        else
            return sum;
    }
}
