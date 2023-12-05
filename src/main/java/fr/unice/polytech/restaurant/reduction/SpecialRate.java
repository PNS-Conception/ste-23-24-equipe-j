package fr.unice.polytech.restaurant.reduction;

import fr.unice.polytech.utilisateur.UserStatut;

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
    private Map<UserStatut,Integer> rates;


    // CONSTRUCTOR
    public SpecialRate() {
        this.rates = new EnumMap<>(UserStatut.class);
    }


    // METHODS
    public void addSpecialRate(UserStatut statut, int rate) {
        this.rates.put(statut, rate);
    }

    public void addSpecialRate(UserStatut statut, double rate) {
        int rateFormatted = (int) Math.round(rate * 100);
        this.rates.put(statut, rateFormatted);
    }

    public int getSpecialRate(UserStatut statut) {
        if (this.rates.containsKey(statut)) {
            return this.rates.get(statut);
        }
        return 0;
    }
}
