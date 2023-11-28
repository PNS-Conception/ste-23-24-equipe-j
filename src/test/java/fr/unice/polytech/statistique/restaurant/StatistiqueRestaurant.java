package fr.unice.polytech.statistique.restaurant;

import fr.unice.polytech.builder.TypeCommandeSimple;
import fr.unice.polytech.commande.CommandeAvecID;
import fr.unice.polytech.commande.CommandeSimple;
import fr.unice.polytech.commande.SystemeCommande;
import fr.unice.polytech.exceptions.*;
import fr.unice.polytech.globalSystem.GlobalSystem;
import fr.unice.polytech.nourriture.Menu;
import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.nourriture.TypeMenuPlat;
import fr.unice.polytech.restaurant.*;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.adress.Position;
import fr.unice.polytech.utils.temps.Date;
import fr.unice.polytech.utils.temps.Horaire;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonnéque;
import io.cucumber.java.fr.Etque;
import io.cucumber.java.fr.Quand;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class StatistiqueRestaurant {

    private CompteUtilisateur compteUtilisateur;
    private ArrayList<CommandeAvecID> historiqueCommandes;
    private final RestaurantManager restaurantManager = new RestaurantManager();
    private final SystemeCommande commandeManager = new SystemeCommande();
    GlobalSystem globalSystem = new GlobalSystem();



    @Etantdonnéque("L'utilisateur peut accéder aux restaurants suivant : \\(passStatistiqueRestaurant)")
    public void lUtilisateurPeutAccéderAuxRestaurantsSuivant(List<String> restaurants) {
        for (String restaurant : restaurants) {
            restaurantManager.addRestaurant(new Restaurant(restaurant));
        }
    }

    @Etque("le restaurant {string} propose les menus suivant : \\(passStatistiqueRestaurant)")
    public void leRestaurantProposeLesMenusSuivant(String nomRestaurant, Map<String, Double> menus) {

        Restaurant restaurant = restaurantManager.getRestaurantParNom(nomRestaurant);
        for (Map.Entry<String, Double> menu : menus.entrySet()) {
            restaurant.addMenu(new Menu(menu.getKey(), menu.getValue()));
        }

        try {
            assertEquals(menus.size(), restaurant.getMenus().size());
        } catch (AucunMenuException e) {
            assert false : "Le restaurant ne doit pas avoir aucun menu";
        }
    }


    @Etantdonnéque("l'utilisateur {string} {string} est connecté \\(passStatistiqueRestaurant)")
    public void lUtilisateurEstLoggé(String prenom, String nom) {
        compteUtilisateur = this.globalSystem.createAccount(nom, prenom);
        assertEquals(prenom, compteUtilisateur.getPrenom());
        assertEquals(nom, compteUtilisateur.getNom());
    }

    @Etque("le restaurant {string} n'a jamais effectué de commande \\(passStatistiqueRestaurant)")
    public void leRestaurantNAJamaisEffectuéDeCommande(String nomRestaurant) {
        Restaurant restaurant = restaurantManager.getRestaurantParNom(nomRestaurant);
        try {
            HashMap<Restaurant, Integer> statResto = compteUtilisateur.getStatResto();
            if (statResto.containsKey(restaurant)) {
                assertEquals(0, (int) statResto.get(restaurant));
            } else {
                assert true : "Le restaurant à la bonne statistique (0)";
            }
        } catch (PasswordException e) {
            throw new RuntimeException(e);
        }
    }

    @Quand("{string} {string} accède à la page des statistiques du restaurant {string}, il obtient une valeur {int}. \\(passStatistiqueRestaurant)")
    public void accèdeÀLaPageDesStatistiquesDuRestaurantIlObtientUneValeur(String prenom, String nom, String nomRestaurant, int tailleStatistique) {
        if (Objects.equals(compteUtilisateur.getNom(), nom) && Objects.equals(compteUtilisateur.getPrenom(), prenom)) {
            Restaurant restaurant = restaurantManager.getRestaurantParNom(nomRestaurant);
            try {
                HashMap<Restaurant, Integer> statResto = compteUtilisateur.getStatResto();
                if (statResto.containsKey(restaurant)) {
                    assertEquals(tailleStatistique, (int) statResto.get(restaurant));
                } else {
                    assertEquals(tailleStatistique, 0);
                }
            } catch (PasswordException e) {
                throw new RuntimeException(e);
            }
        } else {
            assert false : "L'utilisateur n'est pas le bon";
        }
    }

    @Alors("{string} {string} effectue une commande dans le restaurant {string}. \\(passStatistiqueRestaurant)")
    public void ilEffectueUneCommandeDansCeRestaurant(String prenom, String nom, String nomRestaurant) throws AucunMenuException, PasswordException, TokenException, RestaurantNonValideException {
        if (Objects.equals(compteUtilisateur.getNom(), nom) && Objects.equals(compteUtilisateur.getPrenom(), prenom)) {
            Restaurant restaurant = restaurantManager.getRestaurantParNom(nomRestaurant);
            SystemeCommande systemeCommande = new SystemeCommande();
            CommandeSimple commande = (CommandeSimple) systemeCommande.creerCommandeSimpleMultipleGroupe(compteUtilisateur, TypeCommandeSimple.SIMPLE);
            commande.setInformationLivraison(new Date(true), new Horaire(true), new Position(""));
            List<MenuPlat> listMenus = restaurant.getMenus();
            if (listMenus.size()!=0) {
                try {
                    commande.ajoutMenuPlat(listMenus.get(0), TypeMenuPlat.MENU);
                    assertEquals(1, commande.getMenuPlats().size());
                } catch (CapaciteDepasseException e) {
                    assert false : "Impossible d'ajouter une commande";
                }
            } else {
                assert false : "Le restaurant n'a pas de menu";
            }
            commande.payerCommande(this.compteUtilisateur.createToken(CompteUtilisateur.DEFAULT_PASSWORD));
        } else {
            assert false : "L'utilisateur n'est pas le bon";
        }
    }
}
