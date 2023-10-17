package fr.unice.polytech.restaurant;

import io.cucumber.java.fr.*;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AjouterPlatALaCommande {
    SousCommande sousCommande;
    Plat plat;

    @Etantdonnéque("une commande en cours de création avec un montant qui s'élève à 0€")
    public void createCommande(){
        sousCommande = new SousCommande();
    }

    @Etque("je veux ajouter \"1\" quantité de plat de \"tagliatelles au saumon\" à \"10€\"")
    public void createPlat(){
        List<String> aliments = new ArrayList<>(Arrays.asList("Tagliatelles", "Saumon", "Crème Fraiche"));
        List<String> alergene =  new ArrayList<>();
        plat = new Plat("Tagliatelles au saumon", 10, aliments, alergene);
    }

    @Etque("le stock restant sur ce produit est de \"3\"")
    public void checkStockPlat(){
        
    }

    @Quand("j'ajoute \"1\" quantité sur le plat")
    public void ajoutPlatDansCommande(){
        sousCommande.ajout_plat(plat);
        assertTrue(sousCommande.getPlats().containsKey(plat.getName()));
    }

    @Alors("\"1\" quantité de ce plat sera dans ma commande")
    public void getQuantitePlatDansLaCommande(){
        int quantite = sousCommande.getPlats().get(plat.getName());
        assertEquals(1, quantite);
    }

    @Et("le prix de la commande est à \"10€\"")
    public void getPrixDeLaCommande(){
        assertEquals(10, sousCommande.getPrix());
    }

}
