package fr.unice.polytech.historique;

import fr.unice.polytech.builder.TypeCommandeSimple;
import fr.unice.polytech.commande.*;
import fr.unice.polytech.commande.interfacecommande.ICommande;
import fr.unice.polytech.exceptions.*;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.*;

public class Historique {

    private CompteUtilisateur compteUtilisateur;
    private ArrayList<ICommande> historiqueCommandes;
    private final RestaurantManager restaurantManager = new RestaurantManager();

    @Etantdonnéque("l'utilisateur {string} {string} est connecté \\(passHistorique)")
    public void lUtilisateurEstLoggéHistorique(String prenom, String nom) {
        /* Line to pass cucumber duplication : Historique */
        compteUtilisateur = new CompteUtilisateur(nom, prenom);

        assertEquals(prenom, compteUtilisateur.getPrenom());
        assertEquals(nom, compteUtilisateur.getNom());
    }

    @Etque("{string} {string} n'a jamais effectué de commande \\(passHistorique)")
    public void nAJamaisEffectuéDeCommandeHistorique(String prenom, String nom) {
        /* Line to pass cucumber duplication : Historique */
        if (Objects.equals(compteUtilisateur.getNom(), nom) && Objects.equals(compteUtilisateur.getPrenom(), prenom)) {
            assertEquals(0, compteUtilisateur.getAllHistorique().size());
        } else {
            assert false : "L'utilisateur n'est pas le bon";
        }
    }

    @Quand("{string} {string} veut accéder à l'historique \\(passHistorique)")
    public void veutAccéderHistorique(String prenom, String nom) {
        /* Line to pass cucumber duplication : Historique */
        if (Objects.equals(compteUtilisateur.getNom(), nom) && Objects.equals(compteUtilisateur.getPrenom(), prenom)) {
            this.historiqueCommandes = compteUtilisateur.getAllHistorique();
            assert true : "L'utilisateur peut accéder à son historique : " + compteUtilisateur.getAllHistorique();
        } else {
            assert false : "L'utilisateur n'est pas le bon";
        }
    }

    @Alors("il obtient une liste de taille {int} \\(passHistorique)")
    public void ilObtientUneListeDeTailleHistorique(int tailleHistorique) {
        /* Line to pass cucumber duplication : Historique */
        assertEquals(tailleHistorique, historiqueCommandes.size());
    }

    @Etque("{string} {string} effectue une commande dans le restaurant {string} \\(passHistorique)")
    public void effectueUneCommandeDansLeRestaurantHistorique(String prenom, String nom, String nomRestaurant) throws AucunMenuException, PasswordException, TokenException, RestaurantNonValideException {
        /* Line to pass cucumber duplication : Historique */
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
    }



    @Etque("L'utilisateur peut accéder aux restaurants suivant: \\(passHistorique)")
    public void lUtilisateurPeutAccéderAuxRestaurantsSuivantHistorique(List<String> restaurants) {
        /* Line to pass cucumber duplication : Historique */
        for (String restaurant : restaurants) {
            restaurantManager.addRestaurant(new Restaurant(restaurant));
        }
    }

    @Etque("les menus proposés par le restaurant {string} sont les suivant : \\(passHistorique)")
    public void leRestaurantProposeLesMenusSuivantHistorique(String nomRestaurant, Map<String, Double> menus) {
        /* Line to pass cucumber duplication : Historique */
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
