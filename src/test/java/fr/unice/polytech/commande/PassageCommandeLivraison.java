package fr.unice.polytech.commande;

import fr.unice.polytech.builder.TypeCommandeSimple;
import fr.unice.polytech.commande.interfacecommande.ICommande;
import fr.unice.polytech.exceptions.CapaciteDepasseException;
import fr.unice.polytech.exceptions.PasswordException;
import fr.unice.polytech.exceptions.RestaurantNonValideException;
import fr.unice.polytech.exceptions.TokenException;
import fr.unice.polytech.livraison.CompteLivreur;
import fr.unice.polytech.livraison.EtatLivraisonCommande;
import fr.unice.polytech.livraison.SystemeLivraison;
import fr.unice.polytech.nourriture.Plat;
import fr.unice.polytech.nourriture.TypeMenuPlat;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.Token;
import fr.unice.polytech.utils.adress.Position;
import fr.unice.polytech.utils.temps.Date;
import fr.unice.polytech.utils.temps.Horaire;
import io.cucumber.java.fr.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PassageCommandeLivraison {
    CommandeSimple commandeSimple;
    SystemeCommande systemeCommande;
    SystemeLivraison systemeLivraison;
    CompteUtilisateur compteUtilisateur;
    Restaurant restaurant;


    @Etantdonnéque("un Utilisateur se nommant {string} {string}")
    public void unUtilisateurSeNommant(String nom, String prenom) {
        compteUtilisateur = new CompteUtilisateur(nom, prenom);
        systemeCommande = new SystemeCommande();
        systemeLivraison = new SystemeLivraison();
        CompteLivreur compteLivreur = new CompteLivreur("livreur", "livreur");
        systemeLivraison.addLivreur(compteLivreur);
    }

    @Etantdonnéque("l'utilisateur possède une commande d'ID {int}")
    public void lUtilisateurPossèdeUneCommandeDID(int identifiantCommande) {
        commandeSimple = (CommandeSimple)
                systemeCommande.creerCommandeSimpleMultipleGroupe(compteUtilisateur, TypeCommandeSimple.SIMPLE);

        assertEquals(identifiantCommande, commandeSimple.getIdCommande());
    }

    @Etqu("il ajoute la date de livraison {string} à {string} et le lieu de livraison {string}")
    public void ilAjouteLaDateDeLivraisonÀEtLeLieuDeLivraison(String dateLivraison, String heureLivraison, String lieuxDeLivraison) {
        Date date = new Date(dateLivraison);
        Horaire heure = new Horaire(heureLivraison);
        Position position = new Position(lieuxDeLivraison);

        commandeSimple.setInformationLivraison(date, heure, position);
    }

    @Etque("l'utilisateur a ajouter un plat {string} à {int}€ au restaurant {string}")
    public void lUtilisateurAAjouterUnPlatÀ€AuRestaurant(String nomPlat, int prix, String nomRestaurant) throws CapaciteDepasseException, RestaurantNonValideException {
        restaurant = new Restaurant(nomRestaurant);
        Plat plat = new Plat(nomPlat, prix, null, null);
        plat.setRestaurant(restaurant);

        commandeSimple.ajoutMenuPlat(plat, TypeMenuPlat.PLAT);
    }

    @Alors("la commande est passé au status {string}")
    public void laCommandeEstPasséAuStatus(String status) {
        if (status.equals("EN_PREPARATION"))
            assertEquals(status, commandeSimple.getEtatCommande().toString());
        else
            assertEquals(status, commandeSimple.getInformationLivraison().getEtatLivraisonCommande().toString());
    }

    @Et("le restaurateur peut récupérer la commande d'ID {int}")
    public void leRestaurateurPeutRécupérerLaCommandeDID(int identifiantCommande) {
        List<ICommande> commandes = systemeCommande.getCommandeEnPreparationRestaurant(restaurant);
        CommandeSimple commande = (CommandeSimple) commandes.get(0);

        assertEquals(identifiantCommande, commande.getIdCommande());
    }

    @Quand("le restaurateur termine la commande")
    public void leRestaurateurTermineLaCommande() {
        commandeSimple.setEtatCommande(EtatCommande.PRETE);
    }

    @Etque("le livreur peut récupérer la commande d'ID {int}")
    public void leLivreurPeutRécupérerLaCommandeDID(int identifiantCommande) {
        CompteLivreur compteLivreur = systemeLivraison.getLivreurEnLivraison(commandeSimple);
        assertNotNull(compteLivreur);
        assertEquals(identifiantCommande, commandeSimple.getIdCommande());
    }

    @Quand("le livreur livre la commande")
    public void leLivreurLivreLaCommande() {
        commandeSimple.getInformationLivraison().setEtatLivraisonCommande(EtatLivraisonCommande.LIVREE);
    }

    @Quand("l'utilisateur paye la commande d'ID {int}")
    public void lUtilisateurPayeLaCommandeDID(int identifiantCommande) throws PasswordException, TokenException {
        commandeSimple = (CommandeSimple) systemeCommande.getCommandeParId(identifiantCommande);
        Token token = compteUtilisateur.createToken("0000");
        commandeSimple.payerCommande(token);
    }
}
