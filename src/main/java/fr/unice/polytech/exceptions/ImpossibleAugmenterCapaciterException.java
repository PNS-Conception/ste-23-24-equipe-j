package fr.unice.polytech.exceptions;

public class ImpossibleAugmenterCapaciterException extends Exception{

    public ImpossibleAugmenterCapaciterException() {
        super("La capacité du restaurant est déjà au maximum");
    }

}
