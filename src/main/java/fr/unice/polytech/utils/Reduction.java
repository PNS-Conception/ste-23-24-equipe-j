package fr.unice.polytech.utils;

import fr.unice.polytech.nourriture.MenuPlat;

import java.util.Map;

public class Reduction {
    public static final double R = 0.05;
    public static final double N = 10;

    private Reduction() {
        throw new IllegalArgumentException("Utility class");
    }

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
