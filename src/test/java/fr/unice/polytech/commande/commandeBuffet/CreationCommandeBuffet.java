package fr.unice.polytech.commande.commandeBuffet;

import fr.unice.polytech.commande.SystemeCommande;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utilisateur.StatusUtilisateur;
import fr.unice.polytech.utilisateur.StatusUtilisateur;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Etantdonnéque;
import io.cucumber.java.fr.Quand;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CreationCommandeBuffet {
    Map<String, CompteUtilisateur> utilisateurs = new HashMap<>();
    SystemeCommande systemeCommande;
    String messageErreur;

    @Etantdonnéque("un {string} se nommant {string} {string}")
    public void unSeNommant(String statusUtilisateur, String prenom, String nom) {
        StatusUtilisateur statutUtilisateur = StatusUtilisateur.getStatusUtilisateur(statusUtilisateur);
        CompteUtilisateur compteUtilisateur = new CompteUtilisateur(nom, prenom, null, null, statutUtilisateur);

        utilisateurs.put(nom + prenom, compteUtilisateur);
    }

    @Quand("{string} {string} crée la commande en ajoutant {string} {string} comme destinataire")
    public void créeLaCommandeEnAjoutantCommeDestinataire(String prenom1, String nom1, String prenom2, String nom2) {
        systemeCommande = new SystemeCommande();
        CompteUtilisateur compteUtilisateur1 = utilisateurs.get(nom1 + prenom1);
        CompteUtilisateur compteUtilisateur2 = utilisateurs.get(nom2 + prenom2);

        try {
            systemeCommande.creerCommandeBuffet(compteUtilisateur1, compteUtilisateur2);
        }
        catch (IllegalArgumentException iAE) {
            messageErreur = iAE.getMessage();
        }
    }


    @Alors("une erreur est retourner {string}")
    public void uneErreurEstRetourner(String messageErreur) {
        assertEquals(messageErreur, this.messageErreur);
    }

    @Alors("la commande Buffet d'ID {int} est créé")
    public void laCommandeBuffetDIDEstCréé(int idCommande) {
        assertNotNull(systemeCommande.getCommandeParId(idCommande));
    }

    @Et("la commande Buffet d'ID {int} n'est pas créé")
    public void laCommandeBuffetDIDNEstPasCréé(int idCommande) {
        assertNull(systemeCommande.getCommandeParId(idCommande));
    }
}
