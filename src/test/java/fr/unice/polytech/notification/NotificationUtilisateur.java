package fr.unice.polytech.notification;

import fr.unice.polytech.commande.Commande;
import fr.unice.polytech.commande.CommandeManager;
import fr.unice.polytech.commande.EtatCommande;
import fr.unice.polytech.livraison.EtatLivraisonCommande;
import fr.unice.polytech.livraison.InformationLivraison;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Etque;
import io.cucumber.java.fr.Quand;

import java.util.HashMap;
import java.util.Map;

import static fr.unice.polytech.livraison.EtatLivraisonCommande.getEtatLivraisonCommande;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class NotificationUtilisateur {
    CommandeManager commandeManager;
    Map<String, CompteUtilisateur> utilisateurs;
    Map<CompteUtilisateur, Integer> nombreNotifications;
    Commande commande;
    EtatCommande etatCommande;
    EtatLivraisonCommande etatLivraisonCommande;


    @Etantdonné("les utilisateurs {string} {string} et {string} {string}")
    public void utilisateursEt(String nom1, String prenom1, String nom2, String prenom2) {
        commandeManager = new CommandeManager();
        utilisateurs = new HashMap<>();
        nombreNotifications = new HashMap<>();
        CompteUtilisateur compteUtilisateur1 = spy(new CompteUtilisateur(nom1, prenom1));
        CompteUtilisateur compteUtilisateur2 = spy(new CompteUtilisateur(nom2, prenom2));

        utilisateurs.put(nom1 + prenom1, compteUtilisateur1);
        utilisateurs.put(nom2 + prenom2, compteUtilisateur2);
        nombreNotifications.put(compteUtilisateur1, 0);
        nombreNotifications.put(compteUtilisateur2, 0);
    }

    @Etque("{string} {string} effectue une commande")
    public void effectueUneCommande(String nom, String prenom) {
        CompteUtilisateur compteUtilisateur = utilisateurs.get(nom + prenom);
        commande = commandeManager.creerCommande(compteUtilisateur);
    }

    @Quand("la commande change de status {string}")
    public void laCommandeChangeDeStatus(String status) {
        etatLivraisonCommande = getEtatLivraisonCommande(status);

        if (etatLivraisonCommande != null) {
            InformationLivraison informationLivraison = commande.getInformationLivraison();
            informationLivraison.setEtatLivraisonCommande(etatLivraisonCommande);

            assertEquals(status, informationLivraison.getEtatLivraisonCommande().toString());
        }
        else {
            etatCommande = EtatCommande.getEtatSousCommande(status);
            commande.setEtatCommande(etatCommande);

            assertEquals(status, commande.getEtatCommande().toString());
        }

    }

    @Alors("{string} {string} doit recevoir une notification")
    public void dupontDoitRecevoirUneNotification(String nom, String prenom) {
        CompteUtilisateur compteUtilisateur = utilisateurs.get(nom + prenom);
        int nombreNotification = this.nombreNotifications.get(compteUtilisateur) + 1;
        nombreNotifications.replace(compteUtilisateur, nombreNotification);

        if (etatLivraisonCommande == null)
            verify(compteUtilisateur, times(nombreNotification)).notify(etatCommande.toString());
        else
            verify(compteUtilisateur, times(nombreNotification)).notify(etatLivraisonCommande.toString());
    }

    @Etque("{string} {string} ne dois pas recevoir une notification")
    public void neDoisPasRecevoirUneNotification(String nom, String prenom) {
        CompteUtilisateur compteUtilisateur = utilisateurs.get(nom + prenom);
        int nombreNotification = this.nombreNotifications.get(compteUtilisateur);

        verify(compteUtilisateur, times(nombreNotification)).notify(etatLivraisonCommande.toString());
    }
}
