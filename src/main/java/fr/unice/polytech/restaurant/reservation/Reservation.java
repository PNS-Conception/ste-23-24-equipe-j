package fr.unice.polytech.restaurant.reservation;

import fr.unice.polytech.exceptions.CapaciteDepasseException;
import fr.unice.polytech.exceptions.ImpossibleAugmenterCapaciterException;
import fr.unice.polytech.utils.temps.HoraireDate;

import java.util.HashMap;

public class Reservation {
    private static final String HORAIRE_NON_EXISTANT = "Horaire null";

    /**
     * Class used to represent a reservation : the capacity of the restaurant at a given time
     */

    //ATTRIBUTES
    private int capacityMax;

    /**
     * HashMap containing the capacity of the restaurant at a given time
     */
    private HashMap<HoraireDate,Integer> capacityPerHour = new HashMap<>();


    //CONSTRUCTOR
    public Reservation(int capacityMax) {
        this.capacityMax = capacityMax;
    }



    //GETTERS AND SETTERS
    public int getCapacityMax() {
        return capacityMax;
    }

    public int getCapacity(HoraireDate horaire) {
        if (horaire == null)
            throw new IllegalArgumentException(HORAIRE_NON_EXISTANT);
        return capacityPerHour.getOrDefault(horaire, 0);
    }

    public void setCapacityMax(int capacityMax) {
        this.capacityMax = capacityMax;
    }


    //METHODS
    public void increaseReservation(HoraireDate horaire, int capacity) throws CapaciteDepasseException {
        if (horaire == null)
            throw new IllegalArgumentException(HORAIRE_NON_EXISTANT);
        if (capacity > capacityMax || capacity < 0)
            throw new CapaciteDepasseException();
        if (capacityPerHour.containsKey(horaire)) {
            int capaciteRestante = capacityPerHour.get(horaire) + capacity;
            if (capaciteRestante > capacityMax) {
                throw new CapaciteDepasseException();
            } else {
                capacityPerHour.put(horaire, capaciteRestante);
            }
        } else {
            capacityPerHour.put(horaire, capacity);
        }
    }

    public void reduceReservation(HoraireDate horaire, int capacity) throws CapaciteDepasseException, ImpossibleAugmenterCapaciterException {
        if (horaire == null)
            throw new IllegalArgumentException(HORAIRE_NON_EXISTANT);
        if (capacity <= 0 || capacity >= capacityMax)
            throw new CapaciteDepasseException();
        if (capacityPerHour.containsKey(horaire)) {
            int capaciteRestante = capacityPerHour.get(horaire) - capacity;
            if (capaciteRestante <= 0) {
                capacityPerHour.remove(horaire);
            } else {
                capacityPerHour.put(horaire, capaciteRestante);
            }
        } else {
            throw new ImpossibleAugmenterCapaciterException();
        }
    }
}
