package fr.unice.polytech.commande;

import fr.unice.polytech.utilisateur.CompteUtilisateur;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Etantdonnéqu;
import io.cucumber.java.fr.Quand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CreationCommandeAfterworks {
    CompteUtilisateur utilisateur;
    String messageErreur;
    SystemeCommande systemeCommande;

    @Etantdonnéqu("un utilisateur se nommant {string} {string}")
    public void UnUtilisateurSeNommant(String prenom, String nom) {
        utilisateur = new CompteUtilisateur(nom, prenom);
    }

    @Quand("il ajoute {int} personnes lors de la création de la commande afterworks")
    public void ilAjoutePersonnesLorsDeLaCréationDeLaCommandeAfterworks(int nombrePersonne) {
        systemeCommande = new SystemeCommande();

        try {
            systemeCommande.creerCommandeAfterwork(utilisateur, nombrePersonne);
        }
        catch (IllegalArgumentException iAE) {
            messageErreur = iAE.getMessage();
        }
    }

    @Alors("la commande afterworks d'ID {int} est crée avec {int} personnes assignées")
    public void laCommandeAfterworksEstCréeAvecPersonnesAssignées(int idCommande, int nombrePersonne) {
        CommandeAfterworks commande = (CommandeAfterworks) systemeCommande.getCommandeParId(idCommande);

        assertEquals(idCommande, commande.getIdCommande());
        assertEquals(nombrePersonne, commande.getNombrePersonne());
    }

    @Alors("une erreur est renvoyé marqué {string}")
    public void uneErreurEstRenvoyéMarqué(String messageErreur) {
        assertEquals(messageErreur, this.messageErreur);
    }

    @Et("la commande d'ID {int} n'est pas créer")
    public void laCommandeNEstPasCréer(int idCommande) {
        assertNull(systemeCommande.getCommandeParId(idCommande));
    }
}
