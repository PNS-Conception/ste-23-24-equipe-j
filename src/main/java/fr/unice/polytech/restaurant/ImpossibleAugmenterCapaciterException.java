package fr.unice.polytech.restaurant;

public class ImpossibleAugmenterCapaciterException extends Exception{

    public ImpossibleAugmenterCapaciterException() {
        super("La capacité du restaurant est déjà au maximum");
    }

}
