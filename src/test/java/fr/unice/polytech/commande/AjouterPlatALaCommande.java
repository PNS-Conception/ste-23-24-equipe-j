package fr.unice.polytech.commande;

import fr.unice.polytech.nourriture.Plat;
import io.cucumber.java.fr.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AjouterPlatALaCommande {
    SousCommande sousCommande;
    Plat plat;

    @Etantdonnéque("une commande en cours de création avec un montant qui s'élève à {double}€")
    public void createCommande(double prix){
        sousCommande = new SousCommande();
        assertEquals(prix, sousCommande.getPrix(), 0);
    }

    @Quand("l'utilisateur ajoute {int} quantité de plat de {string} à {double}€")
    public void ajoutPlatDansCommande(int quantite, String nomPlat, double prix){
        List<String> aliments = new ArrayList<>(Arrays.asList("Tagliatelles", "Saumon", "Crème Fraiche"));
        List<String> alergene =  new ArrayList<>();
        plat = new Plat(nomPlat, prix, aliments, alergene);

        sousCommande.ajout_plat(plat, quantite);

        int nombrePlatAjouter = sousCommande.getMenuPlats().get(plat);
        assertEquals(quantite, nombrePlatAjouter);
    }

    @Alors("{int} quantité de ce plat sera dans ma commande")
    public void getQuantitePlatDansLaCommande(int quantite){
        int nombrePlatAjouter = sousCommande.getMenuPlats().get(plat);
        assertEquals(quantite, nombrePlatAjouter);
    }

    @Et("le prix de la commande est à {double}€")
    public void getPrixDeLaCommande(double prix){
        assertEquals(prix, sousCommande.getPrix(), 0.001);
    }

}
