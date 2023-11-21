package fr.unice.polytech.restaurant;


import fr.unice.polytech.commande.Commande;
import fr.unice.polytech.commande.CommandeManager;
import fr.unice.polytech.livraison.InformationLivraison;
import fr.unice.polytech.nourriture.Menu;
import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.Date;
import fr.unice.polytech.utils.Horaire;
import fr.unice.polytech.utils.HoraireDate;
import fr.unice.polytech.utils.Position;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonnéque;
import io.cucumber.java.fr.Etque;
import io.cucumber.java.fr.Quand;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ActualisationCapaciteRestaurant {

    private final RestaurantManager restaurantManager = new RestaurantManager();
    private final CommandeManager commandeManager = new CommandeManager();

    private CompteUtilisateur compteUtilisateur;
    private Commande commande;
    private Restaurant restaurant;
    private Exception exception;

    @Etantdonnéque("l'utilisateur {string} {string} est connecté")
    public void lUtilisateurEstLoggé(String prenom, String nom) {
        compteUtilisateur = new CompteUtilisateur(nom, prenom);
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


    @Etque("le restaurant {string} a une capacité de {int} le {string} à {string}")
    public void leRestaurantAUneCapacitéDe(String nomRestaurant, int capaciteRestaurant, String dateInput, String horaireInput) throws CapaciteDepasseException {
        Restaurant restaurant = restaurantManager.getRestaurantParNom(nomRestaurant);
        HoraireDate horaireDate = new HoraireDate(dateInput, horaireInput);
        restaurant.increaseReservation(horaireDate, capaciteRestaurant);
        assertEquals(capaciteRestaurant, restaurant.getCapacity(horaireDate));
    }

    @Quand("l'utilisateur choisit le restaurant {string}")
    public void ilChoisitLeRestaurant(String nomRestaurant) {
        restaurant = restaurantManager.getRestaurantParNom(nomRestaurant);
        assertNotNull(restaurant);
    }

    @Quand("l'utilisateur choisit le menu {string} à {double} €")
    public void ilChoisitLeMenuÀ€(String nomMenu, double prix) throws AucunMenuException {
        for (MenuPlat menu : restaurant.getMenus()) {
            if (menu.getNom().equals(nomMenu)) {
                try {
                    commande.ajoutMenuPlat(menu,1);
                } catch (CapaciteDepasseException e) {
                    this.exception = e;
                }
                break;
            }
        }
        assertEquals((int) prix * 100, (int) commande.getPrix() * 100);
    }


    @Alors("la capacité du restaurant {string} est de {int} le {string} à {string}")
    public void laCapacitéDuRestaurantEstDe(String nomRestaurant, int capacity, String dateInput, String horaireInput) {
        Restaurant restaurant = restaurantManager.getRestaurantParNom(nomRestaurant);
        HoraireDate horaireDate = new HoraireDate(dateInput, horaireInput);
        assertEquals(capacity, restaurant.getCapacity(horaireDate));
    }

    @Alors("une {string} doit être déclenché")
    public void uneDoitÊtreDéclenché(String exception) {
        assertEquals(exception, this.exception.getClass().getSimpleName());
    }


    @Quand("l'utilisateur crée une commande pour le {string} à {string} avec comme point de livraison {string}")
    public void lUtilisateurCréeUneCommandePourLeÀAvecCommePointDeLivraison(String dateInput, String horaireInput, String positionInput) {
        commande = commandeManager.creerCommande(compteUtilisateur);
        Date date = new Date(dateInput);
        Horaire horaire = new Horaire(horaireInput);
        Position position = new Position(positionInput);
        InformationLivraison informationLivraison = new InformationLivraison(date, horaire, position);
        commande.setInformationLivraison(informationLivraison);

        assertEquals(date, commande.getInformationLivraison().getDateLivraison());
        assertEquals(horaire, commande.getInformationLivraison().getHeureLivraison());
        assertEquals(position, commande.getInformationLivraison().getLieuxLivraison());
    }
}