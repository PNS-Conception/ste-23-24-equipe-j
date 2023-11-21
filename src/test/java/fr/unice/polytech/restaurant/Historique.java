package fr.unice.polytech.restaurant;

import fr.unice.polytech.commande.Commande;
import fr.unice.polytech.commande.CommandeManager;
import fr.unice.polytech.nourriture.Menu;
import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.Position;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonnéque;
import io.cucumber.java.fr.Etque;
import io.cucumber.java.fr.Quand;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.*;

public class Historique {

    private CompteUtilisateur compteUtilisateur;
    private ArrayList<Commande> historiqueCommandes;
    private final RestaurantManager restaurantManager = new RestaurantManager();
    private final CommandeManager commandeManager = new CommandeManager();


    @Etantdonnéque("l'utilisateur {string} {string} est connecté")
    public void lUtilisateurEstLoggé(String prenom, String nom) {
        compteUtilisateur = new CompteUtilisateur(nom, prenom);
        assertEquals(prenom, compteUtilisateur.getPrenom());
        assertEquals(nom, compteUtilisateur.getNom());
    }

    @Etque("{string} {string} n'a jamais effectué de commande")
    public void nAJamaisEffectuéDeCommande(String prenom, String nom) {
        if (Objects.equals(compteUtilisateur.getNom(), nom) && Objects.equals(compteUtilisateur.getPrenom(), prenom)) {
            assertEquals(0, compteUtilisateur.getHistoriqueCommandes().size());
        } else {
            assert false : "L'utilisateur n'est pas le bon";
        }
    }

    @Quand("{string} {string} veut accéder à l'historique")
    public void veutAccéder(String prenom, String nom) {
        if (Objects.equals(compteUtilisateur.getNom(), nom) && Objects.equals(compteUtilisateur.getPrenom(), prenom)) {
            this.historiqueCommandes = compteUtilisateur.getHistoriqueCommandes();
            assert true : "L'utilisateur peut accéder à son historique : " + compteUtilisateur.getHistoriqueCommandes();
        } else {
            assert false : "L'utilisateur n'est pas le bon";
        }
    }

    @Alors("il obtient une liste de taille {int}")
    public void ilObtientUneListeDeTaille(int tailleHistorique) {
        assertEquals(tailleHistorique, historiqueCommandes.size());
    }

    @Etque("{string} {string} effectue une commande dans le restaurant {string}")
    public void effectueUneCommandeDansLeRestaurant(String prenom, String nom, String nomRestaurant) throws AucunMenuException, PasswordException, TokenException {
        Restaurant restaurant = restaurantManager.getRestaurantParNom(nomRestaurant);
        Commande commande = commandeManager.creerCommande(compteUtilisateur);

        List<MenuPlat> listMenus = restaurant.getMenus();
        if (listMenus.size()!=0) {
            try {
                commande.ajoutMenuPlat(listMenus.get(0),1);
                assertEquals(1, commande.getMenuPlats().size());
            } catch (CapaciteDepasseException e) {
                assert false : "Impossible d'ajouter une commande";
            }
        } else {
            assert false : "Le restaurant n'a pas de menu";
        }
        commandeManager.payerCommande(commande, this.compteUtilisateur.createToken(CompteUtilisateur.DEFAULT_PASSWORD));
    }



    @Etque("L'utilisateur peut accéder aux restaurants suivant :")
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
}
