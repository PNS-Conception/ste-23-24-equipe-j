package fr.unice.polytech.restaurant;

public class AucunMenuException extends Exception{
    public AucunMenuException() {
        super("Aucun menu dans ce restaurant");
    }
}
