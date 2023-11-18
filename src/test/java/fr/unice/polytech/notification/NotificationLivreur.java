package fr.unice.polytech.notification;

import fr.unice.polytech.commande.Commande;
import fr.unice.polytech.commande.CommandeManager;
import fr.unice.polytech.commande.EtatCommande;
import fr.unice.polytech.livraison.CompteLivreur;
import fr.unice.polytech.livraison.EtatLivraisonCommande;
import fr.unice.polytech.livraison.SystemeLivraison;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.Position;
import io.cucumber.java.fr.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class NotificationLivreur {
    CommandeManager commandeManager;
    SystemeLivraison systemeLivraison;
    Map<String, CompteLivreur> livreurs;
    Map<CompteLivreur, Integer> nombreNotifications;
    Commande commande;


    @Etantdonné("les livreurs {string} {string} et {string} {string}")
    public void lesLivreursEt(String nom1, String prenom1, String nom2, String prenom2) {
        commandeManager = new CommandeManager();
        systemeLivraison = new SystemeLivraison(commandeManager.eventManager);
        livreurs = new HashMap<>();
        nombreNotifications = new HashMap<>();
        CompteLivreur livreur1 = spy(new CompteLivreur(nom1, prenom1, new Position(0, 0)));
        CompteLivreur livreur2 = spy(new CompteLivreur(nom2, prenom2, new Position(1, 1)));

        livreurs.put(nom1 + prenom1, livreur1);
        livreurs.put(nom2 + prenom2, livreur2);
        nombreNotifications.put(livreur1, 0);
        nombreNotifications.put(livreur2, 0);
        systemeLivraison.addLivreur(livreur1);
        systemeLivraison.addLivreur(livreur2);
    }

    @Etantdonnéque("{string} {string} est disponible")
    public void estDisponible(String nom, String prenom) {
        CompteLivreur livreur = livreurs.get(nom + prenom);
        List<CompteLivreur> listeLivreurs = systemeLivraison.getLivreursDisponibles();

        assertTrue(listeLivreurs.contains(livreur));
    }

    @Etque("{string} {string} n'est pas disponible")
    public void nEstPasDisponible(String nom, String prenom) {
        CompteLivreur livreur = livreurs.get(nom + prenom);
        Commande commandeLivreur = commandeManager.creerCommande(new CompteUtilisateur("nom", "prenom"));
        commandeLivreur.setEtatCommande(EtatCommande.PRETE);
        List<CompteLivreur> listeLivreurs = systemeLivraison.getLivreursDisponibles();

        assertFalse(listeLivreurs.contains(livreur));
    }

    @Quand("la commande change de status à {string}")
    public void laCommandeChangeDeStatusÀ(String status) {
        commande = commandeManager.creerCommande(new CompteUtilisateur("nom", "prenom"));
        commande.setEtatCommande(EtatCommande.getEtatSousCommande(status));
    }


    @Alors("le livreur {string} {string} doit recevoir une notification")
    public void leLivreurDoitRecevoirUneNotification(String nom, String prenom) {
        CompteLivreur livreur = livreurs.get(nom + prenom);
        EtatLivraisonCommande etatLivraisonCommande = commande.getInformationLivraison().getEtatLivraisonCommande();
        int nombreNotification = this.nombreNotifications.get(livreur) + 1;
        nombreNotifications.replace(livreur, nombreNotification);

        verify(livreur, times(nombreNotification)).notify(etatLivraisonCommande.toString());
    }

    @Etque("la commande est de status {string}")
    public void laCommandeEstDeStatus(String status) {
        assertEquals(status, commande.getInformationLivraison().getEtatLivraisonCommande().toString());
    }
}
