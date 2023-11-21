package fr.unice.polytech.commandegroupe;

import fr.unice.polytech.builder.TypeCommandeSimple;
import fr.unice.polytech.commande.CommandeGroupe;
import fr.unice.polytech.commande.SystemeCommande;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import io.cucumber.java.fr.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CreerCommandeGroupe {
    SystemeCommande systemeCommande;
    CompteUtilisateur compteUtilisateur;
    CommandeGroupe commandeGroupe;

    @Etantdonné("un utilisateur {string} {string}")
    public void unUtilisateur(String nom, String Prenom) {
        systemeCommande = new SystemeCommande();
        compteUtilisateur = new CompteUtilisateur(nom, Prenom);
    }

    @Quand("l utilisateur crée une commande groupé")
    public void lUtilisateurCréeUneCommandeGroupé() {
        commandeGroupe = (CommandeGroupe) systemeCommande
                .creerCommandeSimpleMultipleGroupe(compteUtilisateur, TypeCommandeSimple.GROUPEE);
    }

    @Alors("le commande d'ID {int} est créé")
    public void leCommandeDIDEstCréé(int id) {
        assertEquals(commandeGroupe, systemeCommande.getCommandeParId(0));
    }

    @Et("le créateur de cette commande est {string} {string}")
    public void leCréateurDeCetteCommandeEst(String nom, String prenom) {
        CommandeGroupe commandeGroupeObtenu = (CommandeGroupe) systemeCommande.getCommandeParId(0);
        CompteUtilisateur createur = commandeGroupeObtenu.getCreateur();
        assertEquals(nom, createur.getNom());
        assertEquals(prenom, createur.getPrenom());
    }

    @Etantdonnéque("l utilisateur possède une commande groupé d ID {int}")
    public void lUtilisateurPossèdeUneCommandeGroupéDID(int id) {
        systemeCommande = new SystemeCommande();
        commandeGroupe = (CommandeGroupe) systemeCommande
                .creerCommandeSimpleMultipleGroupe(compteUtilisateur, TypeCommandeSimple.GROUPEE);

        assertEquals(id, commandeGroupe.getIdCommande());
    }

    @Alors("quand l utilisateur supprime la commande")
    public void quandLUtilisateurSupprimeLaCommande() {
        systemeCommande.supprimerCommande((int) commandeGroupe.getIdCommande());
    }

    @Et("la commande d ID {int} n existe plus")
    public void laCommandeDIDNExistePlus(int id) {
        assertNull(systemeCommande.getCommandeParId(id));
    }
}
