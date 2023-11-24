package fr.unice.polytech.exceptions;

public class CapaciteDepasseException extends STEException {


    public CapaciteDepasseException() {
        super("Capacité du restaurant déjà nulle");
    }
}
