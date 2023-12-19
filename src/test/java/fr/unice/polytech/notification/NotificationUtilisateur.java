package fr.unice.polytech.notification;

import fr.unice.polytech.builder.TypeCommandeSimple;
import fr.unice.polytech.commande.CommandeSimple;
import fr.unice.polytech.commande.EtatCommande;
import fr.unice.polytech.commande.SystemeCommande;
import fr.unice.polytech.globalsystem.GlobalSystem;
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
    SystemeCommande systemeCommande;
    Map<String, CompteUtilisateur> utilisateurs;
    Map<CompteUtilisateur, Integer> nombreNotifications;
    CommandeSimple commande;
    EtatCommande etatCommande;
    EtatLivraisonCommande etatLivraisonCommande;
    GlobalSystem globalSystem = new GlobalSystem();



    @Etantdonné("les utilisateurs {string} {string} et {string} {string}")
    public void utilisateursEt(String nom1, String prenom1, String nom2, String prenom2) {
        systemeCommande = new SystemeCommande();
        utilisateurs = new HashMap<>();
        nombreNotifications = new HashMap<>();
        CompteUtilisateur compteUtilisateur1 = spy(this.globalSystem.createAccount(nom1, prenom1));
        CompteUtilisateur compteUtilisateur2 = spy(this.globalSystem.createAccount(nom2, prenom2));

        utilisateurs.put(nom1 + prenom1, compteUtilisateur1);
        utilisateurs.put(nom2 + prenom2, compteUtilisateur2);
        nombreNotifications.put(compteUtilisateur1, 0);
        nombreNotifications.put(compteUtilisateur2, 0);
    }

    @Etque("{string} {string} effectue une commande")
    public void effectueUneCommande(String nom, String prenom) {
        CompteUtilisateur compteUtilisateur = utilisateurs.get(nom + prenom);
        commande = (CommandeSimple) systemeCommande
                .creerCommandeSimpleMultipleGroupe(compteUtilisateur, TypeCommandeSimple.SIMPLE);
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
