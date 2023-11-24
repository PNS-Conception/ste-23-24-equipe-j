package fr.unice.polytech.restaurant.reservation;

import fr.unice.polytech.exceptions.CapaciteDepasseException;
import fr.unice.polytech.exceptions.ImpossibleAugmenterCapaciterException;
import fr.unice.polytech.utils.temps.HoraireDate;

import java.util.HashMap;

public class Reservation {

    private int capacityMax;

    private HashMap<HoraireDate,Integer> capacityPerHour = new HashMap<>();


    public Reservation(int capacityMax) {
        this.capacityMax = capacityMax;
    }

    public void increaseReservation(HoraireDate horaire, int capacity) throws CapaciteDepasseException {
        if (horaire == null)
            throw new IllegalArgumentException("Horaire null");
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
            throw new IllegalArgumentException("Horaire null");
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

    public int getCapacityMax() {
        return capacityMax;
    }

    public int getCapacity(HoraireDate horaire) {
        if (horaire == null)
            throw new IllegalArgumentException("Horaire null");
        if (capacityPerHour.containsKey(horaire)) {
            return capacityPerHour.get(horaire);
        } else {
            return 0;
        }
    }

    public void setCapacityMax(int capacityMax) {
        this.capacityMax = capacityMax;
    }



}
