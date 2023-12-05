package fr.unice.polytech.restaurant.ListMenu;


import fr.unice.polytech.globalsystem.GlobalSystem;
import fr.unice.polytech.nourriture.Menu;
import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.exceptions.AucunMenuException;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.restaurant.RestaurantManager;
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
    RestaurantManager restaurantManager;
    Restaurant restaurant;
    GlobalSystem globalSystem = new GlobalSystem();


    @Etantdonnéque("l'utilisateur à une liste de restaurant :")
    public void getRestaurants(List<String> listeRestaurant) {
        restaurantManager = new RestaurantManager();

        for (String nomRestaurant : listeRestaurant)
            restaurantManager.addRestaurant(new Restaurant(nomRestaurant));

        assertEquals(listeRestaurant.size(), restaurantManager.getRestaurants().size());
    }

    @Et("le restaurant {string} a ces menus :")
    public void ajoutMenus(String nomRestaurant, List<String> menus) {
        Restaurant restaurantAjoutMenu = restaurantManager.getRestaurantParNom(nomRestaurant);

        for (String menu : menus)
            restaurantAjoutMenu.addMenu(new Menu(menu, 0));
    }

    @Quand("l'utilisateur choisi le restaurant de nom {string}")
    public void choisiRestaurant(String nomRestaurant) {
        restaurant = restaurantManager.getRestaurantParNom(nomRestaurant);
        assertEquals(nomRestaurant, restaurant.getNomRestaurant());
    }

    @Alors("l'utilisateur dois obtenir les menus :")
    public void getMenuRestaurant(List<String> menus) {
        List<MenuPlat> menusRT = null;

        try {
            menusRT = new ArrayList<>(restaurant.getMenus());
        } catch (AucunMenuException aME) {
            assert false: "Le restaurant dois avoir des menus";
        }

        List<Menu> menusArg = new ArrayList<>();

        for (String nomMenu : menus)
            menusArg.add(new Menu(nomMenu, 0));

        menusRT.sort(Comparator.comparing(MenuPlat::getNom));
        menusArg.sort(Comparator.comparing(MenuPlat::getNom));

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
