package fr.unice.polytech.restaurant.Reduction;

import fr.unice.polytech.utilisateur.StatusUtilisateur;

import java.util.HashMap;

public class SpecialRate {

    /**
     * Class used to apply a special rate to a specific user status
     */


    // ATTRIBUTES
    /**
     * HashMap containing the special rates for each user status
     */
    private HashMap<StatusUtilisateur,Integer> specialRate;


    // CONSTRUCTOR
    public SpecialRate() {
        this.specialRate = new HashMap<>();
    }


    // METHODS
    public void addSpecialRate(StatusUtilisateur status, int rate) {
        this.specialRate.put(status, rate);
    }

    public void addSpecialRate(StatusUtilisateur status, double rate) {
        int rateFormatted = (int) Math.round(rate * 100);
        this.specialRate.put(status, rateFormatted);
    }

    public int getSpecialRate(StatusUtilisateur status) {
        if (this.specialRate.containsKey(status)) {
            return this.specialRate.get(status);
        }
        return 0;
    }
}
