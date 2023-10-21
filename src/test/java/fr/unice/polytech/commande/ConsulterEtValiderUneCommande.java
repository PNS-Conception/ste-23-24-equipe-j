package fr.unice.polytech.restaurant;

import io.cucumber.java.fr.*;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ConsulterEtValiderUneCommande {

    EtatSousCommande statusSousCommande;
    EtatLivraisonCommande statusLivraisonCommande;
    Restaurant restaurant;
    SystemeLivraison systemeLivraison = new SystemeLivraison();

    @Etantdonnéque("le restaurateur de position {int},{int} a la liste des commandes en attente :")
    public void getCommandes(Integer latitude, Integer longitude, List<String> listeCommande) {
        this.restaurant = new Restaurant("Chinois", new Coordinate(latitude, longitude));

        for (String commande : listeCommande)
            this.restaurant.addCommande(new Commande(commande));

        assertEquals(listeCommande.size(), this.restaurant.getCommandes().size());
    }

    @Etque("le livreur {string} {string} est disponible au coordonnées {int} {int}")
    public void leLivreurEstDisponibleAuCoordonnées(String prenom, String nom, int latitude, int longitude) {
        this.systemeLivraison.addLivreur(new Livreur(prenom, nom, new Coordinate(latitude, longitude)));
    }


    @Etque("la commande {string} d'ID {int} a le status {string}")
    public void setCommande(String nomCommande, Integer ID, String status) {
        HashMap<Integer,Commande> commandes = this.restaurant.getCommandes();

        Commande commandeVoulu = commandes.get(ID);
        commandeVoulu.setStatusPreparation(status);
        this.restaurant.addCommande(commandeVoulu);

        assertEquals(nomCommande, commandeVoulu.getNomCommande());
        assertEquals(status, this.restaurant.getCommandes().get(ID).getStatusPreparation());
    }

    @Quand("le restaurateur valide la commande {string} d'ID {int} avec le status {string}")
    public void setStatus(String nomCommande, Integer ID, String status) {
        HashMap<Integer,Commande> commandes = this.restaurant.getCommandes();

        Commande commandeVoulu = commandes.get(ID);
        commandeVoulu.setStatusLivraison(status);
        this.restaurant.addCommande(commandeVoulu);

        assertEquals(status, this.restaurant.getCommandes().get(ID).getStatusLivraison());
    }

    @Alors("le livreur peut retirer la commande {string} d'ID {int}, ie la commande est attribué au livreur le plus proche : {string} {string}")
    public void leLivreurPeutRetirerLaCommandeDIDIeLaCommandeEstAttribuéAuLivreurLePlusProche(String nomCommande, Integer ID, String prenomLivreur, String nomLivreur) {
        Coordinate coordonees = this.restaurant.getCoordonnees();
        Livreur livreur = this.systemeLivraison.getClosestLivreur(coordonees);
        assertEquals(livreur.getPrenom(), prenomLivreur);
        assertEquals(livreur.getNom(), nomLivreur);
    }
}
