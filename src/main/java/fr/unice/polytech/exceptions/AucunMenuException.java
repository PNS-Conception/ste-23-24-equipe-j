package fr.unice.polytech.exceptions;

/**
 * Exception lorsqu'il n'y a aucun menu dans le restaurant
 * @author Equipe J
 */
public class AucunMenuException extends STEException {
    public AucunMenuException() {
        super("Aucun menu dans ce restaurant");
    }
}
