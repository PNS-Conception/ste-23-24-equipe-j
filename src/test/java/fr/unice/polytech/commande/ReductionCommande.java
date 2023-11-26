package fr.unice.polytech.commande;

import fr.unice.polytech.nourriture.Menu;
import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.nourriture.TypeMenuPlat;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.restaurant.RestaurantNonValideException;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.Reduction;
import io.cucumber.java.fr.Etantdonnéque;
import io.cucumber.java.fr.Etque;
import io.cucumber.java.fr.Quand;

import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReductionCommande {
    CommandeSimple commandeSimple;

    @Etantdonnéque("l'utilisateur possède une commande")
    public void lUtilisateurPossèdeUneCommande() {
        commandeSimple = new CommandeSimple(0, new CompteUtilisateur("test", "test"));
    }

    @Etque("la commande possède {int} menus de {int}€")
    public void laCommandePossèdeMenusDe€(int nombreCommande, int prix) throws RestaurantNonValideException {
        Restaurant restaurant = new Restaurant("Hello World");
        Menu menu = new Menu("Saumon", prix);
        menu.setRestaurant(restaurant);

        for (int i = 0; i < nombreCommande; i++) {
            commandeSimple.ajoutMenuPlat(menu, TypeMenuPlat.MENU);
        }

        assertEquals(nombreCommande, commandeSimple.getMenuPlats().values().stream().mapToInt(Integer::intValue).sum());
    }

    @Etque("le prix de la commande est à {double} €")
    public void lePrixDeLaCommandeEstÀ€(double prix) {
        assertEquals(prix, commandeSimple.getPrix(), 0.0);
    }

    @Etque("la réduction est à {int}%")
    public void laRéductionEstÀ(int reduction) {
        assertEquals(reduction, Reduction.R * 100, 0.01);
    }

    @Quand("j'ajoute {int} menu")
    public void jAjouteMenu(int nombreMenu) throws RestaurantNonValideException {
        Restaurant restaurant = new Restaurant("Hello World");
        Menu menu = new Menu("Saumon", 10);
        menu.setRestaurant(restaurant);
        for (int i = 0; i < nombreMenu; i++) {
            commandeSimple.ajoutMenuPlat(menu, TypeMenuPlat.MENU);
        }
    }
}
