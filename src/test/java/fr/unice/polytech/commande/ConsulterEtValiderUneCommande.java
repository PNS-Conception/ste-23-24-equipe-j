package fr.unice.polytech.commande;

import fr.unice.polytech.builder.TypeCommandeSimple;
import fr.unice.polytech.exceptions.CapaciteDepasseException;
import fr.unice.polytech.exceptions.PasswordException;
import fr.unice.polytech.exceptions.RestaurantNonValideException;
import fr.unice.polytech.exceptions.TokenException;
import fr.unice.polytech.globalsystem.GlobalSystem;
import fr.unice.polytech.livraison.CompteLivreur;
import fr.unice.polytech.livraison.EtatLivraisonCommande;
import fr.unice.polytech.livraison.InformationLivraison;
import fr.unice.polytech.livraison.SystemeLivraison;
import fr.unice.polytech.nourriture.TypeMenuPlat;
import fr.unice.polytech.restaurant.*;
import fr.unice.polytech.utils.temps.Date;
import fr.unice.polytech.utils.temps.Horaire;
import fr.unice.polytech.utils.adress.Position;
import fr.unice.polytech.nourriture.Menu;
import io.cucumber.java.fr.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class ConsulterEtValiderUneCommande {
    Restaurant restaurant;
    SystemeCommande systemeCommande = new SystemeCommande();
    SystemeLivraison systemeLivraison;
    GlobalSystem globalSystem = new GlobalSystem();


    @Etantdonnéque("le restaurateur de position {int},{int} a la liste des commandes en attente avec les menus :")
    public void getCommandes(Integer latitude, Integer longitude, List<String> listeCommande) throws RestaurantNonValideException, CapaciteDepasseException {
        systemeLivraison = new SystemeLivraison();
        restaurant = new Restaurant("Chinois", new Position(latitude, longitude));

        for (String nomMenu : listeCommande) {
            Menu menu = new Menu(nomMenu, 0);
            restaurant.addMenu(menu);

            CommandeSimple commande = (CommandeSimple) systemeCommande.creerCommandeSimpleMultipleGroupe(
                            this.globalSystem.createAccount(), TypeCommandeSimple.SIMPLE);

            LocalDateTime now = LocalDateTime.now();
            int year = now.getYear();
            int month = now.getMonthValue();
            int day = now.getDayOfMonth();
            int hour = now.getHour();
            int minute = now.getMinute();
            Date date = new Date(day, month, year);
            Horaire horaire = new Horaire(hour, minute);
            Position position = new Position("positionInput");
            commande.setInformationLivraison(date,horaire, position);
            commande.ajoutMenuPlat(menu, TypeMenuPlat.MENU);
        }

        assertEquals(listeCommande.size(), systemeCommande.getCommandesRestaurant(restaurant).size());
    }

    @Etque("le livreur {string} {string} est disponible au coordonnées {int} {int}")
    public void leLivreurEstDisponibleAuCoordonnées(String prenom, String nom, int latitude, int longitude) {
        Position position = new Position(latitude, longitude);
        this.systemeLivraison.addLivreur(new CompteLivreur(prenom, nom, position));
    }


    @Etque("la commande avec le menu d'ID {int} à le status {string}")
    public void setCommande(Integer id, String status) throws PasswordException, TokenException {
        CommandeSimple commandeVoulu = (CommandeSimple) systemeCommande.getCommandeParId(id);
        EtatCommande etatCommande = EtatCommande.getEtatSousCommande(status);

        commandeVoulu.setEtatCommande(etatCommande);
        assertEquals(id.intValue(), commandeVoulu.getIdCommande());
        assertEquals(etatCommande, commandeVoulu.getEtatCommande());
    }

    @Quand("le restaurateur valide la commande avec le menu d'ID {int} avec le status {string}")
    public void setStatus(Integer id, String status) {
        CommandeSimple commandeVoulu = (CommandeSimple) systemeCommande.getCommandeParId(id);
        InformationLivraison informationLivraison = commandeVoulu.getInformationLivraison();
        EtatLivraisonCommande etatLivraisonCommande = EtatLivraisonCommande.getEtatLivraisonCommande(status);

        informationLivraison.setEtatLivraisonCommande(etatLivraisonCommande);

        assertEquals(etatLivraisonCommande, informationLivraison.getEtatLivraisonCommande());
    }

    @Alors("le livreur peut retirer la commande avec le menu d'ID {int}, ie la commande est attribué au livreur le plus proche : {string} {string}")
    public void leLivreurPeutRetirerLaCommandeDIDIeLaCommandeEstAttribuéAuLivreurLePlusProche(Integer ID, String prenomLivreur, String nomLivreur) {
        Position position = restaurant.getPosition();
        CompteLivreur compteLivreur = systemeLivraison.getLivreurEnLivraison(systemeCommande.getCommandeParId(ID));

        assertEquals(prenomLivreur, compteLivreur.getPrenom());
        assertEquals(nomLivreur, compteLivreur.getNom());
    }
}
