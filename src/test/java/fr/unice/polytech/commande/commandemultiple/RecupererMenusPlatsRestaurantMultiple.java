package fr.unice.polytech.commande.commandemultiple;

import fr.unice.polytech.builder.TypeCommandeSimple;
import fr.unice.polytech.commande.*;
import fr.unice.polytech.commande.interfacecommande.ICommandeAjoutable;
import fr.unice.polytech.exceptions.*;
import fr.unice.polytech.nourriture.Plat;
import fr.unice.polytech.nourriture.TypeMenuPlat;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.Token;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonnéqu;
import io.cucumber.java.fr.Etque;
import io.cucumber.java.fr.Quand;

import java.util.List;

import static org.junit.Assert.*;

public class RecupererMenusPlatsRestaurantMultiple {
    SystemeCommande systemeCommande = new SystemeCommande();
    CommandeMultiple commandeMultiple;
    CompteUtilisateur compteUtilisateur;

    @Etantdonnéqu("il y a une commande Multiple avec un Plat {string} du Restaurant {string}")
    public void ilYAUneCommandeMultipleAvecUnPlatDuRestaurant(String nomPlat, String nomRestaurant) throws CapaciteDepasseException, RestaurantNonValideException {
        compteUtilisateur = new CompteUtilisateur("test", "test");
        commandeMultiple = (CommandeMultiple) systemeCommande.creerCommandeSimpleMultipleGroupe(
                compteUtilisateur, TypeCommandeSimple.MULTIPLE);

        Restaurant restaurant = new Restaurant(nomRestaurant);
        Plat plat = new Plat(nomPlat, 5, null, null);
        plat.setRestaurant(restaurant);

        CommandeSimpleSansID commandeSimpleSansID = new CommandeSimpleSansID(compteUtilisateur);
        commandeSimpleSansID.ajoutMenuPlat(plat, TypeMenuPlat.PLAT);
        commandeMultiple.ajouterCommande(commandeSimpleSansID);
    }

    @Quand("la commande Multiple est paye")
    public void laCommandeMultipleEstPaye() throws PasswordException, TokenException {
        Token temp = compteUtilisateur.createToken("0000");
        commandeMultiple.payerCommande(temp);

        assertEquals(EtatCommande.EN_PREPARATION, commandeMultiple.getEtatCommande());
    }

    @Alors("le plat {string} est en préparation pour le Restaurant {string}")
    public void lePlatEstEnPréparationPourLeRestaurant(String nomPlat, String Restaurant) {
        Plat plat = new Plat(nomPlat, 5, null, null);
        plat.setRestaurant(new Restaurant(Restaurant));

        CommandeSimpleSansID commandeSimpleSansID = (CommandeSimpleSansID) commandeMultiple
                .getCommandesEnPreparationRestaurant(new Restaurant(Restaurant)).get(0);
        assertTrue(commandeSimpleSansID.getMenuPlats().containsKey(plat));
    }

    @Etque("la commande multiple possède {int} autres Plats de {string} du Restaurant {string}")
    public void laCommandeMultiplePossèdeAutresPlatsDeDuRestaurant(int nombrePlat, String nomPlats, String nomRestaurant)
            throws CapaciteDepasseException, RestaurantNonValideException {

        Restaurant restaurant = new Restaurant(nomRestaurant);
        Plat plat = new Plat(nomPlats, 5, null, null);
        plat.setRestaurant(restaurant);

        CommandeSimpleSansID commandeSimpleSansID = new CommandeSimpleSansID(compteUtilisateur);
        for (int i = 0; i < nombrePlat; i++) {
            commandeSimpleSansID.ajoutMenuPlat(plat, TypeMenuPlat.PLAT);
            commandeMultiple.ajouterCommande(commandeSimpleSansID);
        }
    }

    @Etque("{int} Plat de {string} est en préparation pour le Restaurant {string}")
    public void platDeEstEnPréparationPourLeRestaurant(int nombrePlat, String nomPlat, String nomRestaurant) {
        Restaurant restaurant = new Restaurant(nomRestaurant);
        Plat plat = new Plat(nomPlat, 5, null, null);
        plat.setRestaurant(restaurant);

        List<ICommandeAjoutable> commandeSimpleSansID = commandeMultiple.getCommandesEnPreparationRestaurant(restaurant);
        int nombreMemePlat = 0;
        for(ICommandeAjoutable commande : commandeSimpleSansID) {
            CommandeSimpleSansID commandeSimple = (CommandeSimpleSansID) commande;

            if(commandeSimple.getMenuPlats().containsKey(plat))
                nombreMemePlat += commandeSimple.getMenuPlats().get(plat);
        }
        assertEquals(nombrePlat, nombreMemePlat);
    }

    @Alors("aucun menu pour le Restaurant {string}")
    public void aucunMenuPourLeRestaurant(String nomRestaurant) {
        assertTrue(commandeMultiple.getCommandesEnPreparationRestaurant(new Restaurant(nomRestaurant)).isEmpty());
    }
}
