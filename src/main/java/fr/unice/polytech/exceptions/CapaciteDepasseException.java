package fr.unice.polytech.exceptions;

public class CapaciteDepasseException extends Exception {


    public CapaciteDepasseException() {
        super("Capacité du restaurant déjà nulle");
    }
}
