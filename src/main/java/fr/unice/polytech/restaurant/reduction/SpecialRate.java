package fr.unice.polytech.restaurant.reduction;

import fr.unice.polytech.utilisateur.StatusUtilisateur;

import java.util.EnumMap;
import java.util.Map;

public class SpecialRate {

    /**
     * Class used to apply a special rate to a specific user status
     */


    // ATTRIBUTES
    /**
     * HashMap containing the special rates for each user status
     */
    private final Map<StatusUtilisateur,Integer> rates;


    // CONSTRUCTOR
    public SpecialRate() {
        this.rates = new EnumMap<>(StatusUtilisateur.class);
    }


    // METHODS
    public void addSpecialRate(StatusUtilisateur status, int rate) {
        this.rates.put(status, rate);
    }

    public void addSpecialRate(StatusUtilisateur status, double rate) {
        int rateFormatted = (int) Math.round(rate * 100);
        this.rates.put(status, rateFormatted);
    }

    public int getSpecialRate(StatusUtilisateur status) {
        if (this.rates.containsKey(status)) {
            return this.rates.get(status);
        }
        return  0;
    }
}
