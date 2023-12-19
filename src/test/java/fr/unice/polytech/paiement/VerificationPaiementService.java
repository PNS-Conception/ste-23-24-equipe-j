package fr.unice.polytech.paiement;

import fr.unice.polytech.commande.CommandeSimple;
import fr.unice.polytech.commande.EtatCommande;
import fr.unice.polytech.exceptions.CapaciteDepasseException;
import fr.unice.polytech.exceptions.PasswordException;
import fr.unice.polytech.exceptions.RestaurantNonValideException;
import fr.unice.polytech.exceptions.TokenException;
import fr.unice.polytech.nourriture.Menu;
import fr.unice.polytech.nourriture.TypeMenuPlat;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.Token;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Etque;
import io.cucumber.java.fr.Quand;

import static org.junit.Assert.assertEquals;

public class VerificationPaiementService {
    CompteUtilisateur compteUtilisateur;
    CommandeSimple commande;
    Restaurant restaurant;


    @Etantdonné("l'utilisateur est {string} {string}")
    public void lUtilisateurEst(String nom, String prenom) {
        compteUtilisateur = new CompteUtilisateur(nom, prenom);
        restaurant = new Restaurant("test");
        commande = new CommandeSimple(0, compteUtilisateur);
    }


    @Etque("il paye sa commande et le paiement est accepte")
    public void lePaiementEstAccepteEtLePaiementEstAccepte() throws PasswordException, TokenException, CapaciteDepasseException, RestaurantNonValideException {
            Menu menu = new Menu("test", 5);
            menu.setRestaurant(restaurant);
            commande.ajoutMenuPlat(menu, TypeMenuPlat.PLAT);
            Token token = compteUtilisateur.createToken("0000");
            commande.payerCommande(token);
    }

    @Quand("il paye sa commande et le paiement n'est pas accepte")
    public void ilPayeSaCommandeEtLePaiementNEstPasAccepte() throws PasswordException, TokenException, CapaciteDepasseException, RestaurantNonValideException {
        Menu menu = new Menu("test", -5);
        menu.setRestaurant(restaurant);
        commande.ajoutMenuPlat(menu, TypeMenuPlat.PLAT);
        Token token = compteUtilisateur.createToken("0000");
        commande.payerCommande(token);
    }

    @Alors("le status de la commande est {string}")
    public void leStatusDeLaCommandeEst(String statusCommande) {
        EtatCommande etatCommandeAttendu = EtatCommande.valueOf(statusCommande);
        assertEquals(etatCommandeAttendu, commande.getEtatCommande());
    }
}
