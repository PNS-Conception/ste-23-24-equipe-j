package fr.unice.polytech.restaurant;

public class CapaciteDepasseException extends Exception {


    public CapaciteDepasseException() {
        super("Capacité du restaurant déjà nulle");
    }
}
