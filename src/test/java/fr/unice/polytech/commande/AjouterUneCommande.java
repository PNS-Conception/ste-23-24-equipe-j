package fr.unice.polytech.commande;

import fr.unice.polytech.exceptions.*;
import fr.unice.polytech.globalSystem.GlobalSystem;
import fr.unice.polytech.nourriture.Menu;
import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.restaurant.*;
import fr.unice.polytech.builder.TypeCommandeSimple;
import fr.unice.polytech.nourriture.*;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.*;
import fr.unice.polytech.utils.adress.Position;
import fr.unice.polytech.utils.temps.Date;
import fr.unice.polytech.utils.temps.Horaire;
import io.cucumber.java.fr.*;

import java.util.*;

import static org.junit.Assert.*;

public class AjouterUneCommande {
    private final RestaurantManager restaurantManager = new RestaurantManager();
    private final SystemeCommande systemeCommande = new SystemeCommande();

    private CompteUtilisateur compteUtilisateur;
    private CommandeSimple commande;
    private Restaurant restaurant;
    GlobalSystem globalSystem = new GlobalSystem();


    @Etantdonnéque("l'utilisateur {string} {string} est loggé \\(pass)")
    public void lUtilisateurEstLoggéAjouterUneCommande(String prenom, String nom) {
        /* Line to pass cucumber duplication : AjouterCommande */
        compteUtilisateur = globalSystem.createAccount(nom, prenom);

    }

    @Etque("{string} {string} crée une commande \\(pass)")
    public void créeUneCommandeAjouterUneCommande(String prenom, String nom) {
        /* Line to pass cucumber duplication : AjouterCommande */
        commande = (CommandeSimple) systemeCommande.creerCommandeSimpleMultipleGroupe(compteUtilisateur,
                TypeCommandeSimple.SIMPLE);


        assertEquals(prenom, compteUtilisateur.getPrenom());
        assertEquals(nom, compteUtilisateur.getNom());
    }

    @Etque("L'utilisateur peut accéder aux restaurants suivant : \\(pass)")
    public void lUtilisateurPeutAccéderAuxRestaurantsSuivantAjouterUneCommande(List<String> restaurants) {
        /* Line to pass cucumber duplication : AjouterCommande */
        for (String restaurant : restaurants) {
            restaurantManager.addRestaurant(new Restaurant(restaurant));

        }
    }

    @Etque("les adresses suivantes sont pré-enregistré : \\(pass)")
    public void lesAdressesSuivantesSontPréEnregistréAjouterUneCommande(List<String> adresses) {
        /* Line to pass cucumber duplication : AjouterCommande */
        for (String adresse : adresses) {
            compteUtilisateur.ajouterAdresse(new Position(adresse));

        }
    }

    @Etque("le restaurant {string} propose les menus suivant : \\(pass)")
    public void leRestaurantProposeLesMenusSuivantAjouterUneCommande(String nomRestaurant, Map<String, Double> menus) {
        /* Line to pass cucumber duplication : AjouterCommande */
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


    @Etque("l'utilisateur choisit le restaurant {string} \\(pass)")
    public void ilChoisitLeRestaurantAjouterUneCommande(String nomRestaurant) {
        /* Line to pass cucumber duplication : AjouterCommande */
        restaurant = restaurantManager.getRestaurantParNom(nomRestaurant);

        assertNotNull(restaurant);
    }

    @Etque("l'utilisateur choisit le menu {string} à {int} € \\(pass)")
    public void ilChoisitLeMenuÀ€AjouterUneCommande(String nomMenu, int prix) throws AucunMenuException, RestaurantNonValideException {
        /* Line to pass cucumber duplication : AjouterCommande */
        commande.setInformationLivraison(new Date(true), new Horaire(true), new Position(""));
        for (MenuPlat menu : restaurant.getMenus()) {
            if (menu.getNom().equals(nomMenu)) {
                try {
                    commande.ajoutMenuPlat(menu,TypeMenuPlat.MENU);
                } catch (CapaciteDepasseException e) {
                    throw new RuntimeException(e);

                }
                break;
            }
        }

        assertEquals(prix * 100, (int) commande.getPrix() * 100);
    }

    @Etque("l'utilisateur choisit la livraison le {string} à {string} à l'adresse pré-enregistré {string} \\(pass)")
    public void ilChoisitLaLivraisonLeÀÀLAdressePréEnregistréAjouterUneCommande(String dateInput, String heureInput, String adresseInput) {
        /* Line to pass cucumber duplication : AjouterCommande */
        Date date = new Date(dateInput);

        Horaire horaire = new Horaire(heureInput);
        Position position = compteUtilisateur.getAdresseEnregistreesParNom(adresseInput);

        commande.setInformationLivraison(date, horaire, position);
    }

    @Quand("l'utilisateur paye sa commande à {int}€ \\(pass)")
    public void lUtilisateurConfirmeSaCommandeEtQuIlPayeLes€AjouterUneCommande(int prix) throws PasswordException, TokenException {
        /* Line to pass cucumber duplication : AjouterCommande */
        Token token = compteUtilisateur.createToken(CompteUtilisateur.DEFAULT_PASSWORD);

        commande.payerCommande(token);
        assertEquals(prix, (int) commande.getPrix());
    }

    @Alors("la commande est ajouté à la liste des commandes {string} et elle est envoyé au restaurant {string} \\(pass)")
    public void laCommandeEstAjoutéÀLaListeDesCommandesEnPréparationEtElleEstEnvoyéAuRestaurantAjouterUneCommande(String etatCommande, String nomRestaurant) {
        /* Line to pass cucumber duplication : AjouterCommande */
        assertEquals(EtatCommande.getEtatSousCommande(etatCommande), commande.getEtatCommande());

        assertEquals(restaurant.getNomRestaurant(), nomRestaurant);
        assertTrue(systemeCommande.getCommandeEnPreparationRestaurant(restaurant).contains(commande));
    }
}
