package fr.unice.polytech.commande.commandemultiple;

import fr.unice.polytech.builder.TypeCommandeAjoutable;
import fr.unice.polytech.builder.TypeCommandeSimple;
import fr.unice.polytech.commande.*;
import fr.unice.polytech.commande.interfacecommande.ICommandeAjoutable;
import fr.unice.polytech.exceptions.CapaciteDepasseException;
import fr.unice.polytech.exceptions.RestaurantNonValideException;
import fr.unice.polytech.exceptions.TokenException;
import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.nourriture.Plat;
import fr.unice.polytech.nourriture.TypeMenuPlat;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonnéqu;
import io.cucumber.java.fr.Etantdonnéque;
import io.cucumber.java.fr.Quand;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AjoutCommandeMultipleDansCommande {
    SystemeCommande systemeCommande;
    CommandeGroupe commandeGroupe;
    CompteUtilisateur compteUtilisateur;

    @Etantdonnéqu("il y a une commande groupe d'ID {int}")
    public void ilYAUneCommandeGroupeDID(int identifiantCommande) {
        systemeCommande = new SystemeCommande();
        compteUtilisateur = new CompteUtilisateur("test", "test");
        commandeGroupe = (CommandeGroupe) systemeCommande.creerCommandeSimpleMultipleGroupe(compteUtilisateur,
                TypeCommandeSimple.GROUPEE);

        assertEquals(identifiantCommande, commandeGroupe.getIdCommande());
    }

    @Quand("il y a une commande multiple d ID {int} avec {int} plat de {string} et " +
            "{int} plat de {string} du restaurant {string} ajouté dans commande groupe d'ID {int}")
    public void ilYAUneCommandeMultipleDIDAvecPlatDeEtPlatDeDuRestaurantAjoutéDansCommandeGroupeDID(int identifiantCommandeMultiple, int nombrePlat,
                                                                                                    String nomPlat, int nombrePlat2,
                                                                                                    String nomPlat2, String nomRestaurant,
                                                                                                    int identifiantCommandeGroupe)
            throws CapaciteDepasseException, RestaurantNonValideException {

        CommandeMultipleAjoutable commandeMultiple = (CommandeMultipleAjoutable) systemeCommande
                .creerCommandeAjoutable(compteUtilisateur, TypeCommandeAjoutable.MULTIPLE, identifiantCommandeGroupe);

        CommandeSimpleSansID commandeSimpleSansID = new CommandeSimpleSansID(compteUtilisateur);
        Restaurant restaurant = new Restaurant(nomRestaurant);
        Plat plat1 = new Plat(nomPlat, 10, null, null);
        Plat plat2 = new Plat(nomPlat2, 10, null, null);

        plat1.setRestaurant(restaurant);
        plat2.setRestaurant(restaurant);

        for (int i = 0; i < nombrePlat; i++)
            commandeSimpleSansID.ajoutMenuPlat(plat1, TypeMenuPlat.PLAT);

        for (int i = 0; i < nombrePlat2; i++)
            commandeSimpleSansID.ajoutMenuPlat(plat2, TypeMenuPlat.PLAT);

        commandeMultiple.ajouterCommande(commandeSimpleSansID);
        commandeGroupe.ajouterCommande(commandeMultiple);
        assertEquals(identifiantCommandeMultiple, commandeMultiple.getIdCommande());
    }

    @Alors("la commande groupe possède {int} commande contenant {int} plat de {string} et {int} plat de {string} du restaurant {string}")
    public void laCommandeGroupePossèdeCommandeContenantPlatDeEtPlatDe(int nombreCommande, int nombrePlat, String nomPlat,
                                                                       int nombrePlat2, String nomPlat2, String nomRestaurant) {

        List<ICommandeAjoutable> commandes = commandeGroupe.getCommandes();
        assertEquals(nombreCommande, commandes.size());
        Restaurant restaurant = new Restaurant(nomRestaurant);
        Plat plat1 = new Plat(nomPlat, 10, null, null);
        Plat plat2 = new Plat(nomPlat2, 10, null, null);
        plat1.setRestaurant(restaurant);
        plat2.setRestaurant(restaurant);
        int nombrePlat1Commande = 0;
        int nombrePlat2Commande = 0;

        for (ICommandeAjoutable commandeAjoutable : commandes) {
            CommandeSimpleSansID commandeSimpleSansID = (CommandeSimpleSansID) commandeAjoutable;
            Map<MenuPlat, Integer> menuPlatIntegerMap = commandeSimpleSansID.getMenuPlats();

            if (menuPlatIntegerMap.containsKey(plat1))
                nombrePlat1Commande += menuPlatIntegerMap.get(plat1);
            if (menuPlatIntegerMap.containsKey(plat2))
                nombrePlat2Commande += menuPlatIntegerMap.get(plat2);
        }

        assertEquals(nombrePlat, nombrePlat1Commande);
        assertEquals(nombrePlat2, nombrePlat2Commande);
    }

    @Etantdonnéque("il y a une commande multiple d ID {int} avec {int} plat de {string} du restaurant {string} et {int} plat de {string} du restaurant {string} ajouté dans commande groupe d'ID {int}")
    public void ilYAUneCommandeMultipleDIDAvecPlatDeDuRestaurantEtPlatDeDuRestaurantAjoutéDansCommandeGroupeDID(int identifiantCommandeMultiple, int nombrePlat, String nomPlat, String nomRestaurant, int nombrePlat2, String nomPlat2, String nomRestaurant2, int identifiantCommandeGroupe) throws CapaciteDepasseException, RestaurantNonValideException {

        CommandeMultipleAjoutable commandeMultiple = (CommandeMultipleAjoutable) systemeCommande
                .creerCommandeAjoutable(compteUtilisateur, TypeCommandeAjoutable.MULTIPLE, identifiantCommandeGroupe);

        CommandeSimpleSansID commandeSimpleSansID = new CommandeSimpleSansID(compteUtilisateur);
        CommandeSimpleSansID commandeSimpleSansID2 = new CommandeSimpleSansID(compteUtilisateur);
        Restaurant restaurant = new Restaurant(nomRestaurant);
        Restaurant restaurant2 = new Restaurant(nomRestaurant2);
        Plat plat1 = new Plat(nomPlat, 10, null, null);
        Plat plat2 = new Plat(nomPlat2, 10, null, null);

        plat1.setRestaurant(restaurant);
        plat2.setRestaurant(restaurant2);

        for (int i = 0; i < nombrePlat; i++)
            commandeSimpleSansID.ajoutMenuPlat(plat1, TypeMenuPlat.PLAT);

        for (int i = 0; i < nombrePlat2; i++)
            commandeSimpleSansID2.ajoutMenuPlat(plat2, TypeMenuPlat.PLAT);

        commandeMultiple.ajouterCommande(commandeSimpleSansID);
        commandeMultiple.ajouterCommande(commandeSimpleSansID2);
        commandeGroupe.ajouterCommande(commandeMultiple);
        assertEquals(identifiantCommandeMultiple, commandeMultiple.getIdCommande());
    }

    @Quand("la commande d'ID {int} est payé")
    public void laCommandeDIDEstPayé(int identifiantCommande) throws TokenException {
        CommandeMultipleAjoutable commande = (CommandeMultipleAjoutable) systemeCommande.getCommandeParId(identifiantCommande);
        commande.payerCommande(null);

        assertEquals(EtatCommande.EN_PREPARATION, commande.getEtatCommande());
    }

    @Alors("le restaurateur du restaurant {string} voit le plat {string}")
    public void leRestaurateurDuRestaurantVoitLePlat(String nomRestaurant, String nomPlat) {
        Plat plat = new Plat(nomPlat, 10, null, null);
        Restaurant restaurant = new Restaurant(nomRestaurant);

        plat.setRestaurant(restaurant);
        boolean platPresent = false;
        List<ICommandeAjoutable> commandes = commandeGroupe.getCommandes();

        for (ICommandeAjoutable commandeAjoutable : commandes) {
            CommandeSimpleSansID commandeSimpleSansID = (CommandeSimpleSansID) commandeAjoutable;
            Map<MenuPlat, Integer> menuPlatIntegerMap = commandeSimpleSansID.getMenuPlats();

            if (menuPlatIntegerMap.containsKey(plat))
                platPresent = true;
        }

        assertTrue(platPresent);
    }
}
