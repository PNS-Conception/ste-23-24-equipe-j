package fr.unice.polytech.restaurant;

import io.cucumber.java.fr.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AjouterPlatALaCommande {
    SousCommande sousCommande;
    Plat plat;

    @Etantdonnéque("une commande en cours de création avec un montant qui s'élève à {double}€")
    public void createCommande(double prix){
        sousCommande = new SousCommande();
    }

    @Etque("je veux ajouter {int} quantité de plat de \"tagliatelles au saumon\" à {double}€")
    public void createPlat(int quantite, double prix){
        List<String> aliments = new ArrayList<>(Arrays.asList("Tagliatelles", "Saumon", "Crème Fraiche"));
        List<String> alergene =  new ArrayList<>();
        plat = new Plat("Tagliatelles au saumon", 10, aliments, alergene);
    }

    @Quand("j'ajoute {int} quantité sur le plat")
    public void ajoutPlatDansCommande(int quantite){
        sousCommande.ajout_plat(plat);
        assertTrue(sousCommande.getPlats().containsKey(plat.getName()));
    }

    @Alors("{int} quantité de ce plat sera dans ma commande")
    public void getQuantitePlatDansLaCommande(int quantite){
        int quantiteCommande = sousCommande.getPlats().get(plat.getName());
        assertEquals(quantite, quantiteCommande);
    }

    @Et("le prix de la commande est à {double}€")
    public void getPrixDeLaCommande(double prix){
        assertEquals(prix, sousCommande.getPrix(), 0.001);
    }

}
