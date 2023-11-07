package fr.unice.polytech.commande;

import fr.unice.polytech.nourriture.Plat;
import fr.unice.polytech.restaurant.CapaciteDepasseException;
import io.cucumber.java.fr.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AjouterPlatALaCommande {
    Commande commande;
    Plat plat;

    @Etantdonnéque("une commande en cours de création avec un montant qui s'élève à {double}€")
    public void createCommande(double prix){
        commande = new Commande(0);
        assertEquals(prix, commande.getPrix(), 0);
    }

    @Quand("l'utilisateur ajoute {int} quantité de plat de {string} à {double}€")
    public void ajoutPlatDansCommande(int quantite, String nomPlat, double prix) throws CapaciteDepasseException {
        List<String> aliments = new ArrayList<>(Arrays.asList("Tagliatelles", "Saumon", "Crème Fraiche"));
        List<String> alergene =  new ArrayList<>();
        plat = new Plat(nomPlat, prix, aliments, alergene);

        commande.ajoutMenuPlat(plat, quantite);

        int nombrePlatAjouter = commande.getMenuPlats().get(plat);
        assertEquals(quantite, nombrePlatAjouter);
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
