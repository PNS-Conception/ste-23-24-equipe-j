package fr.unice.polytech.commande;

import fr.unice.polytech.livraison.CompteLivreur;
import fr.unice.polytech.livraison.EtatLivraisonCommande;
import fr.unice.polytech.livraison.SystemeLivraison;
import fr.unice.polytech.restaurant.*;
import fr.unice.polytech.utils.Position;
import fr.unice.polytech.nourriture.Menu;
import io.cucumber.java.fr.*;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class ConsulterEtValiderUneCommande {
    Restaurant restaurant;
    CommandeManager commandeManager = new CommandeManager();
    SystemeLivraison systemeLivraison = new SystemeLivraison();

    @Etantdonnéque("le restaurateur de position {int},{int} a la liste des commandes en attente avec les menus :")
    public void getCommandes(Integer latitude, Integer longitude, List<String> listeCommande) {
        restaurant = new Restaurant("Chinois", new Position(latitude, longitude));

        for (String nomMenu : listeCommande) {
            Menu menu = new Menu(nomMenu, 0);
            restaurant.addMenu(menu);

            Commande commande = new Commande();
            commande.ajoutMenuPlat(menu, 1);
            commandeManager.ajoutCommande(commande);
        }

        assertEquals(listeCommande.size(), commandeManager.getCommandeRestaurant(restaurant).size());
    }

    @Etque("le livreur {string} {string} est disponible au coordonnées {int} {int}")
    public void leLivreurEstDisponibleAuCoordonnées(String prenom, String nom, int latitude, int longitude) {
        Position position = new Position(latitude, longitude);
        this.systemeLivraison.addLivreur(new CompteLivreur(prenom, nom, position));
    }


    @Etque("la commande avec le menu d'ID {int} à le status {string}")
    public void setCommande(Integer id, String status) {
        Commande commandeVoulu = commandeManager.getCommandeParID(id);
        EtatCommande etatCommande = EtatCommande.getEtatSousCommande(status);

        commandeVoulu.setEtatCommande(etatCommande);
        assertEquals(id.intValue(), commandeVoulu.getId());
        assertEquals(etatCommande, commandeManager.getCommandeParID(id).getEtatCommande());
    }

    @Quand("le restaurateur valide la commande avec le menu d'ID {int} avec le status {string}")
    public void setStatus(Integer id, String status) {
        Commande commandeVoulu = commandeManager.getCommandeParID(id);
        EtatLivraisonCommande etatLivraisonCommande = EtatLivraisonCommande.getEtatLivraisonCommande(status);

        commandeVoulu.setEtatLivraisonCommande(etatLivraisonCommande);

        assertEquals(etatLivraisonCommande, commandeManager.getCommandeParID(id).getEtatLivraisonCommande());
    }

    @Alors("le livreur peut retirer la commande avec le menu d'ID {int}, ie la commande est attribué au livreur le plus proche : {string} {string}")
    public void leLivreurPeutRetirerLaCommandeDIDIeLaCommandeEstAttribuéAuLivreurLePlusProche(Integer ID, String prenomLivreur, String nomLivreur) {
        Position position = restaurant.getPosition();
        CompteLivreur compteLivreur = this.systemeLivraison.getPlusProcheLivreur(position);

        assertEquals(compteLivreur.getPrenom(), prenomLivreur);
        assertEquals(compteLivreur.getNom(), nomLivreur);
    }
}