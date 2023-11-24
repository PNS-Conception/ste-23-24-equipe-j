package fr.unice.polytech.restaurant.Reduction;

import fr.unice.polytech.utilisateur.UserStatut;

import java.util.HashMap;

public class SpecialRate {


    private HashMap<UserStatut,Integer> specialRate;

    public SpecialRate() {
        this.specialRate = new HashMap<>();
    }

    public void addSpecialRate(UserStatut statut, int rate) {
        this.specialRate.put(statut, rate);
    }

    public void addSpecialRate(UserStatut statut, double rate) {
        int rateFormatted = (int) Math.round(rate * 100);
        this.specialRate.put(statut, rateFormatted);
    }

    public int getSpecialRate(UserStatut statut) {
        if (this.specialRate.containsKey(statut)) {
            return this.specialRate.get(statut);
        }
        return 0;
    }
}
