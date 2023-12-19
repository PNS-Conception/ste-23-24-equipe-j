package fr.unice.polytech.commande.commandemultiple;

import fr.unice.polytech.builder.TypeCommandeSimple;
import fr.unice.polytech.commande.*;
import fr.unice.polytech.commande.interfacecommande.ICommandeAjoutable;
import fr.unice.polytech.exceptions.CapaciteDepasseException;
import fr.unice.polytech.exceptions.RestaurantNonValideException;
import fr.unice.polytech.nourriture.Menu;
import fr.unice.polytech.nourriture.Plat;
import fr.unice.polytech.nourriture.TypeMenuPlat;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import io.cucumber.java.fr.*;

import static org.junit.Assert.assertEquals;

public class CreationAjoutMenuPlatRestaurant {
    SystemeCommande systemeCommande;
    CompteUtilisateur createur;
    CommandeMultiple commandeMultiple;
    Restaurant restaurant;

    @Etantdonné("l'utilisateur {string} {string} qui possède une commande Multiple")
    public void lUtilisateurQuiPossèdeUneCommandeMultiple(String prenom, String nom) {
        createur = new CompteUtilisateur(nom, prenom);
        systemeCommande = new SystemeCommande();
        commandeMultiple = (CommandeMultiple) systemeCommande.creerCommandeSimpleMultipleGroupe(createur,
                TypeCommandeSimple.MULTIPLE);
    }

    @Etque("cette commande possède un menu {string} du restaurant {string}")
    public void cetteCommandePossèdeUnMenuDuRestaurant(String nomMenu, String nomRestaurant)
            throws CapaciteDepasseException, RestaurantNonValideException {

        CommandeSimpleSansID commandeRestaurant = new CommandeSimpleSansID(createur);
        restaurant = new Restaurant(nomRestaurant);
        Menu menu = new Menu(nomMenu, 5);

        menu.setRestaurant(restaurant);
        commandeRestaurant.ajoutMenuPlat(menu, TypeMenuPlat.MENU);
        commandeMultiple.ajouterCommande(commandeRestaurant);
    }

    @Quand("il ajoute le plat {string} du restaurant {string}")
    public void ilAjouteLePlatDuRestaurant(String nomPlat, String nomRestaurant) throws CapaciteDepasseException,
            RestaurantNonValideException {
        Restaurant resto;
        CommandeSimpleSansID commande;
        Plat plat = new Plat(nomPlat, 5, null, null);

        if (restaurant.getNomRestaurant().equals(nomRestaurant)) {
            resto = restaurant;
            plat.setRestaurant(resto);
            commande = (CommandeSimpleSansID) commandeMultiple.getCommandes().get(0);
            commande.ajoutMenuPlat(plat, TypeMenuPlat.PLAT);
        }
        else {
            resto = new Restaurant(nomRestaurant);
            commande = new CommandeSimpleSansID(createur);
            plat.setRestaurant(resto);
            commande.ajoutMenuPlat(plat, TypeMenuPlat.PLAT);
            commandeMultiple.ajouterCommande(commande);
        }
    }

    @Alors("sa commande Multiple possède {int} commande simple")
    public void saCommandeMultiplePossèdeCommandeSimple(int nombreCommande) {
        assertEquals(nombreCommande, commandeMultiple.getCommandes().size());
    }

    @Etque("sa commande simple possède {int} menu")
    public void saCommandeSimplePossèdeMenu(int nombreMenu) {
        CommandeSimpleSansID commande = (CommandeSimpleSansID) commandeMultiple.getCommandes().get(0);

        assertEquals(nombreMenu, commande.getMenuPlats().values().stream().mapToInt(i -> i).sum());
    }

    @Et("chaque commande simple possède {int} menu")
    public void chaqueCommandeSimplePossèdeMenu(int nombreMenu) {
        for (ICommandeAjoutable commande : commandeMultiple.getCommandes()) {
            CommandeSimpleSansID commandeSimple = (CommandeSimpleSansID) commande;
            assertEquals(nombreMenu, commandeSimple.getMenuPlats().values().stream().mapToInt(i -> i).sum());
        }
    }
}
