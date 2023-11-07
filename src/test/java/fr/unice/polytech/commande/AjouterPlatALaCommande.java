package fr.unice.polytech.commande;

import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.nourriture.Plat;
import fr.unice.polytech.restaurant.AucunMenuException;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.restaurant.RestaurantManager;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.fr.*;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AjouterPlatALaCommande {
    private final RestaurantManager restaurantManager = new RestaurantManager();
    private Restaurant restaurant;
    Commande commande;
    Plat plat;

    @Etantdonnéque("une commande en cours de création avec un montant qui s'élève à {double}€")
    public void createCommande(double prix){
        List<String> aliments = new ArrayList<>(Arrays.asList("Tagliatelles", "Saumon", "Crème Fraiche"));
        List<String> alergenes =  new ArrayList<>();
        Plat newPlat = new Plat("tagliatelles au saumon", 10, aliments, alergenes);

        restaurant = new Restaurant("Italien");
        restaurant.addMenu(newPlat);

        commande = new Commande();
        assertEquals(prix, commande.getPrix(), 0);
    }

    @Quand("l'utilisateur séléctionne le plat {string}")
    public void selectionPlat(String nomPlat) {
        try {
            for (MenuPlat menuPlat : restaurant.getMenus()){
                if (menuPlat.getNom().equals(nomPlat) && menuPlat.getClass().equals(Plat.class)){
                    plat = (Plat) menuPlat;
                    break;
                }
            }
            assertNotNull(plat);
        }
        catch (AucunMenuException e){
            System.err.println(e);
        }

    }

    @Alors("l'utilisateur récupère la liste des aliments du plat")
    public void recupereAliments(){
        List<String> aliments = plat.getAliments();
        assertNotNull(aliments);
    }

    @Et("l'utilisateur récupère la liste des alergenes du plat")
    public void recupereAlergenes(){
        List<String> alergenes = plat.getAlergenes();
        assertNotNull(alergenes);
    }

    @Quand("l'utilisateur ajoute {int} quantité de plat de {string} à {double}€")
    public void ajoutPlatDansCommande(int quantite, String nomPlat, double prix) {
        try {
            for (MenuPlat menuPlat : restaurant.getMenus()){
                if (menuPlat.getNom().equals(nomPlat)){
                    plat = (Plat) menuPlat;
                    break;
                }
            }

            commande.ajoutMenuPlat(plat, quantite);
            int nombrePlatAjouter = commande.getMenuPlats().get(plat);
            assertEquals(quantite, nombrePlatAjouter);
        }
        catch (AucunMenuException e){
            System.err.println(e);
        }
    }

    @Alors("{int} quantité de ce plat sera dans ma commande")
    public void getQuantitePlatDansLaCommande(int quantite){
        int nombrePlatAjouter = commande.getMenuPlats().get(plat);
        assertEquals(quantite, nombrePlatAjouter);
    }

    @Et("le prix de la commande est à {double}€")
    public void getPrixDeLaCommande(double prix){
        assertEquals(prix, commande.getPrix(), 0.001);
    }

}
