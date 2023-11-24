package fr.unice.polytech.exceptions;

public class ImpossibleAugmenterCapaciterException extends STEException{

    public ImpossibleAugmenterCapaciterException() {
        super("La capacité du restaurant est déjà au maximum");
    }

}
