package fr.unice.polytech.restaurant;


import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Etantdonnéque;
import io.cucumber.java.fr.Quand;

public class ObtenirMenuStepdefs {
    @Etantdonnéque("l'utilisateur à une liste de restaurant")
    public void getRestaurants() {

    }

    @Et("l'utilisateur choisi le restaurant de nom {string}")
    public void choisiRestaurant(String nameRestaurant) {

    }

    @Alors("l'utilisateur dois obtenir les menus du restaurant {string}")
    public void getMenuRestaurant(String nameRestaurant) {

    }

    @Quand("le {string} n'a aucun menu")
    public void verifieAucunMenu(String string) {

    }

    @Alors("une \"AucunMenuException\" doit être déclenché")
    public void erreurDeclenche() {

    }
}
