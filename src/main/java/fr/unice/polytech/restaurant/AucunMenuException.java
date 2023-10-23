package fr.unice.polytech.restaurant;

/**
 * Exception lorsqu'il n'y a aucun menu dans le restaurant
 * @author Equipe J
 */
public class AucunMenuException extends Exception{
    public AucunMenuException() {
        super("Aucun menu dans ce restaurant");
    }
}
