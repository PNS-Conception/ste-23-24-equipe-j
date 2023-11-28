package fr.unice.polytech.commande;

import fr.unice.polytech.nourriture.Menu;
import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.restaurant.AucunMenuException;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.restaurant.RestaurantManager;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utilisateur.StatusUtilisateur;
import fr.unice.polytech.utilisateur.UtilisateurNonAuthentifieException;
import fr.unice.polytech.utils.*;
import fr.unice.polytech.utils.Date;
import io.cucumber.java.fr.*;

import java.util.*;

import static org.junit.Assert.*;

public class AjouterUneCommande {
    private final RestaurantManager restaurantManager = new RestaurantManager();
    private final CommandeManager commandeManager = new CommandeManager();

    private CompteUtilisateur compteUtilisateur;
    private Commande commande;
    private Restaurant restaurant;

    @Etantdonnéque("l'utilisateur {string} {string} est connecté")
    public void lUtilisateurEstConnecté(String prenom, String nom) {
        compteUtilisateur = new CompteUtilisateur(nom, prenom, "ETUDIANT");
    }

    @Etantdonnéque("l'utilisateur {string} {string} est connecté et est {string}")
    public void lUtilisateurEstConnectéEtEstEtudiant(String prenom, String nom, String status) {
        compteUtilisateur = new CompteUtilisateur(nom, prenom, "ETUDIANT");
    }

    @Etque("{string} {string} crée une commande")
    public void créeUneCommande(String prenom, String nom) {
        commande = new Commande(compteUtilisateur);

        assertEquals(prenom, compteUtilisateur.getPrenom());
        assertEquals(nom, compteUtilisateur.getNom());
    }

    @Etque("l'utilisateur {string} {string} crée une commande")
    public void créeUneCommandeEtudiant(String prenom, String nom) {
        commande = new Commande(compteUtilisateur);

        assertEquals(prenom, compteUtilisateur.getPrenom());
        assertEquals(nom, compteUtilisateur.getNom());
        assertEquals(StatusUtilisateur.ETUDIANT, compteUtilisateur.getStatusUtilisateur());
    }

    @Etque("L'utilisateur peut accéder aux restaurants suivant :")
    public void lUtilisateurPeutAccéderAuxRestaurantsSuivant(List<String> restaurants) {
        for (String restaurant : restaurants) {
            restaurantManager.addRestaurant(new Restaurant(restaurant));
        }
    }

    @Etque("les adresses suivantes sont pré-enregistré :")
    public void lesAdressesSuivantesSontPréEnregistré(List<String> adresses) {
        for (String adresse : adresses) {
            compteUtilisateur.ajouterAdresse(new Position(adresse));
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

    @Etque("l'utilisateur choisit le restaurant {string}")
    public void ilChoisitLeRestaurant(String nomRestaurant) {
        restaurant = restaurantManager.getRestaurantParNom(nomRestaurant);
        assertNotNull(restaurant);
    }

    @Etque("le restaurant applique un tarif spécial pour les {string} :")
    public void leRestaurantAppliqueUnTarifSpecialPourLesEtudiant(String statusUtilisateur, Map<String, Double> menus) throws AucunMenuException{
        for (Map.Entry<String, Double> menu : menus.entrySet()) {
            MenuPlat menuRestaurant = restaurant.getMenu(menu.getKey());
            menuRestaurant.setPrixStatus(StatusUtilisateur.valueOf(statusUtilisateur), menus.get(menuRestaurant.getNom()));
        }

        for (MenuPlat menu : restaurant.getMenus()){
            Optional<Double> prixMenuEtudiant = Optional.of(menu.getPrix(StatusUtilisateur.ETUDIANT));
            assertEquals(menus.get(menu.getNom()),prixMenuEtudiant.get());
        }
    }

    @Etque("l'utilisateur choisit le menu {string} à {int} €")
    public void ilChoisitLeMenuÀ€(String nomMenu, int prix) throws AucunMenuException, UtilisateurNonAuthentifieException {

        for (MenuPlat menu : restaurant.getMenus()) {
            if (menu.getNom().equals(nomMenu)) {
                commande.ajoutMenuPlat(menu,1);
                break;
            }
        }

        commandeManager.ajoutCommande(commande);
        assertEquals(prix * 100, (int) commande.getPrix() * 100);
    }

    @Etque("l'utilisateur choisit la livraison le {string} à {string} à l'adresse pré-enregistré {string}")
    public void ilChoisitLaLivraisonLeÀÀLAdressePréEnregistré(String dateInput, String heureInput, String adresseInput) {
        Date date = new Date(dateInput);
        Horaire horaire = new Horaire(heureInput);
        Position position = compteUtilisateur.getAdresseEnregistreesParNom(adresseInput);
        commande.setDateCommande(date);
        commande.setHoraireCommande(horaire);
        commande.setPosition(position);
    }

    @Quand("l'utilisateur paye sa commande à {int}€")
    public void lUtilisateurConfirmeSaCommandeEtQuIlPayeLes€(int prix) {
        commandeManager.payerCommande(commande);
        assertEquals(prix, (int) commande.getPrix());
    }

    @Alors("la commande est ajouté à la liste des commandes {string} et elle est envoyé au restaurant {string}")
    public void laCommandeEstAjoutéÀLaListeDesCommandesEnPréparationEtElleEstEnvoyéAuRestaurant(String etatCommande, String nomRestaurant) {
        assertEquals(EtatCommande.getEtatSousCommande(etatCommande), commande.getEtatCommande());
        assertEquals(restaurant.getNomRestaurant(), nomRestaurant);
        assertTrue(commandeManager.getCommandeEnPreparationRestaurant(restaurant).contains(commande));
    }

    @Alors("le prix de la commande augmente de {int} €")
    public void lePrixDeLaCommandeAugmenteDe(int prix){
        assertEquals(prix, commande.getPrix(), 0.001);
    }
}
