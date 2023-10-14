package fr.unice.polytech.restaurant;


import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Etantdonnéque;
import io.cucumber.java.fr.Quand;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ObtenirMenuStepdefs {
    RestaurantSystem restaurantSystem;
    Restaurant restaurant;

    @Etantdonnéque("l'utilisateur à une liste de restaurant :")
    public void getRestaurants(List<String> listeRestaurant) {
        restaurantSystem = new RestaurantSystem();

        for (String nomRestaurant : listeRestaurant)
            restaurantSystem.addRestaurant(new Restaurant(nomRestaurant));

        assertEquals(listeRestaurant.size(), restaurantSystem.getRestaurants().size());
    }

    @Et("le restaurant {string} a ces menus :")
    public void ajoutMenus(String nomRestaurant, List<String> menus) {
        Restaurant restaurantAjoutMenu = restaurantSystem.getRestaurant(nomRestaurant);

        for (String menu : menus)
            restaurantAjoutMenu.addMenu(new Menu(menu));
    }

    @Quand("l'utilisateur choisi le restaurant de nom {string}")
    public void choisiRestaurant(String nomRestaurant) {
        restaurant = restaurantSystem.getRestaurant(nomRestaurant);
        assertEquals(nomRestaurant, restaurant.getNomRestaurant());
    }

    @Alors("l'utilisateur dois obtenir les menus :")
    public void getMenuRestaurant(List<String> menus) {
        List<Menu> menusRT = null;

        try {
            menusRT = new ArrayList<>(restaurant.getMenus());
        } catch (AucunMenuException aME) {
            assert false: "Le restaurant dois avoir des menus";
        }

        List<Menu> menusArg = new ArrayList<>();

        for (String nomMenu : menus)
            menusArg.add(new Menu(nomMenu));

        menusRT.sort(Comparator.comparing(Menu::nomMenu));
        menusArg.sort(Comparator.comparing(Menu::nomMenu));

        assertEquals(menusRT, menusArg);
    }

    @Et("le restaurant n'a aucun menu")
    public void verifieAucunMenu() {
        assertEquals(0, restaurant.nombreMenu());
    }

    @Alors("une \"AucunMenuException\" doit être déclenché")
    public void erreurDeclenche() {
        assertThrows(AucunMenuException.class, () -> restaurant.getMenus());
    }
}
