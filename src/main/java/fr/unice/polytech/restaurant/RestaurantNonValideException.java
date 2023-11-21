package fr.unice.polytech.restaurant;

/**
 * Exception si un menu d'un autre restaurant est ajouté à une commande
 * @author Equipe J
 */
public class RestaurantNonValideException extends Exception{
    /**
     * Constructeur par défaut
     */
    public RestaurantNonValideException() {
        super("Le menu ou plat ajouté n'est pas le même que celui du restaurant de la commande");
    }
}
