package fr.unice.polytech.restaurant;

import fr.unice.polytech.commande.SousCommande;
import fr.unice.polytech.nourriture.Menu;
import fr.unice.polytech.utilisateur.Utilisateur;
import fr.unice.polytech.utils.Position;
import io.cucumber.java.fr.*;

import java.util.*;

import static org.junit.Assert.*;

public class AjouterUneCommande {

    private Utilisateur utilisateur;
    private Commande commande;

    private RestaurantManager restaurantManager;
    private Restaurant restaurant;

    private AdresseGestionnaire adresseGestionnaire;

    @Etantdonnéque("l'utilisateur {string} {string} est loggé")
    public void lUtilisateurEstLoggé(String prenom, String nom) {
        this.utilisateur = new Utilisateur(nom, prenom, 0, "password");
        this.utilisateur.logIn("password");
        assertTrue(this.utilisateur.isLogged());
    }

    @Etque("{string} {string} crée une commande {string}")
    public void créeUneCommande(String prenom, String nom, String nomCommande) {
        assertEquals(prenom, this.utilisateur.getPrenom());
        assertEquals(nom, this.utilisateur.getNom());
        this.commande = new Commande(nomCommande);
    }

    @Etque("l'utilisateur peut accéder aux restaurants suivant :")
    public void lUtilisateurPeutAccéderAuxRestaurantsSuivant(List<String> restaurants) {
        for (String restaurant : restaurants) {
            this.restaurantManager.addRestaurant(new Restaurant(restaurant, new Coordinate(0, 0)));
        }
    }

    @Etque("les adresses suivantes sont pré-enregistré :")
    public void lesAdressesSuivantesSontPréEnregistré(List<String> adresses) {
        this.adresseGestionnaire = new AdresseGestionnaire();
        for (String adresse : adresses) {
            this.adresseGestionnaire.addAdresse(adresse, new Coordinate(10, 10));
        }
    }

    @Etque("le restaurant {string} propose les menus suivant :")
    public void leRestaurantProposeLesMenusSuivant(String nomRestaurant, Map<String, Double> menus) {

        Restaurant restaurant = this.restaurantManager.getRestaurantParNom(nomRestaurant);
        for (Map.Entry<String, Double> menu : menus.entrySet()) {
            restaurant.addMenu(new Menu(menu.getKey(), menu.getValue()));
        }

        try {
            assertEquals(menus.size(), restaurant.getMenus().size());
        } catch (AucunMenuException e) {
            assertThrows(AucunMenuException.class, () -> restaurant.getMenus());
        }
    }


    @Etque("il choisit le restaurant {string}")
    public void ilChoisitLeRestaurant(String nomRestaurant) {
        this.restaurant = this.restaurantManager.getRestaurantParNom(nomRestaurant);
        assertNotNull(this.restaurant);
    }

    @Etque("il choisit le menu {string} à {int} €")
    public void ilChoisitLeMenuÀ€(String nomMenu, int price) throws AucunMenuException {
        SousCommande sousCommande = new SousCommande();
        for (Menu menu : this.restaurant.getMenus()) {
            if (menu.getNom().equals(nomMenu)) {
                sousCommande.ajout_plat(menu,1);
                break;
            }
        }
        this.commande.addSousCommande(sousCommande);
        assertEquals((int) price*100, (int) sousCommande.getPrix()*100);
    }

    @Etque("il choisit la livraison le {string} à {string} à l'adresse pré-enregistré {string}")
    public void ilChoisitLaLivraisonLeÀÀLAdressePréEnregistré(String dateInput, String heureInput, String adresseInput) {
        Date date = new Date(dateInput);
        Horaire horaire = new Horaire(heureInput);
        Coordinate coordinate = this.adresseGestionnaire.getCoordinate(adresseInput);
        this.commande.setDate(date);
        this.commande.setHoraire(horaire);
    }

    @Quand("l{string}il paye les {int} €")
    public void lUtilisateurConfirmeSaCommandeEtQuIlPayeLes€(int arg0) {

    }

    @Alors("la commande est ajouté à la liste des commandes en préparation et elle est envoyé au restaurant {string}")
    public void laCommandeEstAjoutéÀLaListeDesCommandesEnPréparationEtElleEstEnvoyéAuRestaurant(String arg0) {
    }



}
