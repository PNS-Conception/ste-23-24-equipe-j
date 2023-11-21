package fr.unice.polytech.statistique.utilisateur;

import fr.unice.polytech.builder.TypeCommandeSimple;
import fr.unice.polytech.commande.CommandeSimple;
import fr.unice.polytech.commande.SystemeCommande;
import fr.unice.polytech.nourriture.Menu;
import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.nourriture.TypeMenuPlat;
import fr.unice.polytech.restaurant.*;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonnéque;
import io.cucumber.java.fr.Etque;
import io.cucumber.java.fr.Quand;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class StatistiqueUtilisateur {

    private CompteUtilisateur compteUtilisateur;
    private final RestaurantManager restaurantManager = new RestaurantManager();
    private final SystemeCommande commandeManager = new SystemeCommande();

    @Etantdonnéque("L'utilisateur peut accéder aux restaurants suivant :")
    public void lUtilisateurPeutAccéderAuxRestaurantsSuivant(List<String> restaurants) {
        for (String restaurant : restaurants) {
            restaurantManager.addRestaurant(new Restaurant(restaurant));
        }
    }

    @Etque("le restaurant {string} propose les menus suivant :")
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

    @Etantdonnéque("l'utilisateur {string} {string} est connecté")
    public void lUtilisateurEstLoggé(String prenom, String nom) {
        compteUtilisateur = new CompteUtilisateur(nom, prenom);
        assertEquals(prenom, compteUtilisateur.getPrenom());
        assertEquals(nom, compteUtilisateur.getNom());
    }

    @Etque("{string} {string} n'a jamais effectué de commande")
    public void nAJamaisEffectuéDeCommande(String prenom, String name) {
        if (Objects.equals(compteUtilisateur.getNom(), name) && Objects.equals(compteUtilisateur.getPrenom(), prenom)) {
            try {
                assertEquals(0, compteUtilisateur.getHistoriqueCommandes().size());
                HashMap<CompteUtilisateur, Integer> statUser = null;
                statUser = compteUtilisateur.getStatUser();
                if (statUser.containsKey(compteUtilisateur)) {
                    assertEquals(0, (int) statUser.get(compteUtilisateur));
                } else {
                    assert true : "L'utilisateur à la bonne statistique (0)";
                }
            } catch (PasswordException e) {
                throw new RuntimeException(e);
            }
        } else {
            assert false : "L'utilisateur n'est pas le bon";
        }
    }

    @Quand("{string} {string} accède à sa page de statistique, il obtient une valeur {int}.")
    public void accèdeÀSaPageDeStatistiqueIlObtientUneValeur(String prenom, String name, int tailleStatistique) {
        if (Objects.equals(compteUtilisateur.getNom(), name) && Objects.equals(compteUtilisateur.getPrenom(), prenom)) {
            try {
                HashMap<CompteUtilisateur, Integer> statUser = compteUtilisateur.getStatUser();
                if (statUser.containsKey(compteUtilisateur)) {
                    assertEquals(tailleStatistique, (int) statUser.get(compteUtilisateur));
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

    @Alors("{string} {string} effectue une commande dans le restaurant {string}.")
    public void ilEffectueUneCommandeDansCeRestaurant(String prenom, String nom, String nomRestaurant) throws AucunMenuException, PasswordException, TokenException, RestaurantNonValideException {
        if (Objects.equals(compteUtilisateur.getNom(), nom) && Objects.equals(compteUtilisateur.getPrenom(), prenom)) {
            Restaurant restaurant = restaurantManager.getRestaurantParNom(nomRestaurant);
            SystemeCommande systemeCommande = new SystemeCommande();
            CommandeSimple commande = (CommandeSimple) systemeCommande.creerCommandeSimpleMultipleGroupe(compteUtilisateur, TypeCommandeSimple.SIMPLE);

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
