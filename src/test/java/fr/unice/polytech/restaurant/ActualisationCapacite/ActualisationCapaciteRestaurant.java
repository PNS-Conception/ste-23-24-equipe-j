package fr.unice.polytech.restaurant.ActualisationCapacite;


import fr.unice.polytech.builder.TypeCommandeSimple;
import fr.unice.polytech.commande.CommandeAvecID;
import fr.unice.polytech.commande.CommandeSimple;
import fr.unice.polytech.commande.SystemeCommande;
import fr.unice.polytech.livraison.InformationLivraison;
import fr.unice.polytech.nourriture.Menu;
import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.nourriture.TypeMenuPlat;
import fr.unice.polytech.restaurant.*;
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
    private final SystemeCommande commandeManager = new SystemeCommande();

    private CompteUtilisateur compteUtilisateur;
    private CommandeSimple commande;
    private Restaurant restaurant;
    private Exception exception;

    @Etantdonnéque("l'utilisateur {string} {string} est connecté \\(passActualisationCapaciteRestaurant)")
    public void lUtilisateurEstLoggé(String prenom, String nom) {
        compteUtilisateur = new CompteUtilisateur(nom, prenom);
    }



    @Etque("L'utilisateur peut accéder aux restaurants suivant : \\(passActualisationCapaciteRestaurant)")
    public void lUtilisateurPeutAccéderAuxRestaurantsSuivant(List<String> restaurants) {
        for (String restaurant : restaurants) {
            restaurantManager.addRestaurant(new Restaurant(restaurant));
        }
    }

    @Etque("le restaurant {string} propose les menus suivant : \\(passActualisationCapaciteRestaurant)")
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


    @Etque("le restaurant {string} a une capacité de {int} le {string} à {string} \\(passActualisationCapaciteRestaurant)")
    public void leRestaurantAUneCapacitéDe(String nomRestaurant, int capaciteRestaurant, String dateInput, String horaireInput) throws CapaciteDepasseException {
        Restaurant restaurant = restaurantManager.getRestaurantParNom(nomRestaurant);
        HoraireDate horaireDate = new HoraireDate(dateInput, horaireInput);
        restaurant.increaseReservation(horaireDate, capaciteRestaurant);
        assertEquals(capaciteRestaurant, restaurant.getCapacity(horaireDate));
    }

    @Quand("l'utilisateur choisit le restaurant {string} \\(passActualisationCapaciteRestaurant)")
    public void ilChoisitLeRestaurant(String nomRestaurant) {
        restaurant = restaurantManager.getRestaurantParNom(nomRestaurant);
        assertNotNull(restaurant);
    }

    @Quand("l'utilisateur choisit le menu {string} à {double} € \\(passActualisationCapaciteRestaurant)")
    public void ilChoisitLeMenuÀ€(String nomMenu, double prix) throws AucunMenuException, RestaurantNonValideException, PasswordException, TokenException, CapaciteDepasseException {
        for (MenuPlat menu : restaurant.getMenus()) {
            if (menu.getNom().equals(nomMenu)) {
                try {
                    commande.ajoutMenuPlat(menu, TypeMenuPlat.MENU);
                } catch (CapaciteDepasseException e) {
                    this.exception = e;
                }
                break;
            }
        }
        commande.payerCommande(this.compteUtilisateur.createToken(CompteUtilisateur.DEFAULT_PASSWORD));
        assertEquals((int) prix * 100, (int) commande.getPrix() * 100);
    }


    @Alors("la capacité du restaurant {string} est de {int} le {string} à {string} \\(passActualisationCapaciteRestaurant)")
    public void laCapacitéDuRestaurantEstDe(String nomRestaurant, int capacity, String dateInput, String horaireInput) {
        Restaurant restaurant = restaurantManager.getRestaurantParNom(nomRestaurant);
        HoraireDate horaireDate = new HoraireDate(dateInput, horaireInput);
        assertEquals(capacity, restaurant.getCapacity(horaireDate));
    }

    @Alors("une {string} doit être déclenché \\(passActualisationCapaciteRestaurant)")
    public void uneDoitÊtreDéclenché(String exception) {
        assertEquals(exception, this.exception.getClass().getSimpleName());
    }


    @Quand("l'utilisateur crée une commande pour le {string} à {string} avec comme point de livraison {string} \\(passActualisationCapaciteRestaurant)")
    public void lUtilisateurCréeUneCommandePourLeÀAvecCommePointDeLivraison(String dateInput, String horaireInput, String positionInput) {
        commande = (CommandeSimple) commandeManager.creerCommandeSimpleMultipleGroupe(compteUtilisateur, TypeCommandeSimple.SIMPLE);
        Date date = new Date(dateInput);
        Horaire horaire = new Horaire(horaireInput);
        Position position = new Position(positionInput);
        commande.setInformationLivraison(date,horaire, position);

        assertEquals(date, commande.getInformationLivraison().getDateLivraison());
        assertEquals(horaire, commande.getInformationLivraison().getHeureLivraison());
        assertEquals(position, commande.getInformationLivraison().getLieuxLivraison());
    }
}
