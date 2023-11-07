package fr.unice.polytech.commande;

import fr.unice.polytech.nourriture.Menu;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import io.cucumber.java.fr.*;

import static org.junit.Assert.*;

public class AjoutCommandeGroupeSimple {
    CommandeManager commandeManager;
    CommandeGroupee commandeGroupe;
    CompteUtilisateur compteUtilisateur;
    String messageErreur;

    @Etantdonnéqu("une commande groupé contient {int} commandes et s'élevant à {int}€")
    public void uneCommandeGroupéContientCommandesEtSÉlevantÀ€(int nombreCommande, int prixEuro) {
        commandeManager = new CommandeManager();
        CommandeGroupee commandeGroupee = commandeManager.creerCommandeGroupee(0);
        CompteUtilisateur compteUtilisateurDefault = new CompteUtilisateur("DEFAULT", "DEFAULT");

        for (int i = 0; i < nombreCommande; i++) {
            CommandeIndividuelle commandeIndividuelle = commandeManager.creerCommande(compteUtilisateurDefault);
            commandeIndividuelle.ajoutMenuPlat(new Menu("Menu", (double) prixEuro / nombreCommande), 1);
            commandeGroupee.ajoutCommandeIndividuelle(commandeIndividuelle);
        }

        assertEquals(nombreCommande, commandeGroupee.getCommandes().size());
        assertEquals(prixEuro, commandeGroupee.getPrix(), 0.001);
    }

    @Quand("{string} {string} donne l'identifiant {int} de la commande groupé")
    public void donneLIdentifiantDeLaCommandeGroupé(String nomPersonne, String prenomPersonne, int identifiantCommandeGroupe) {
        compteUtilisateur = new CompteUtilisateur(nomPersonne, prenomPersonne);
        try {
            commandeGroupe = commandeManager.getCommandeGroupeeParID(identifiantCommandeGroupe);
        } catch (IllegalArgumentException e) {
            commandeGroupe = null;
            messageErreur = e.getMessage();
        }
    }

    @Etque("la commande groupé existe")
    public void laCommandeGroupéExiste() {
        assertNotNull(commandeGroupe);
    }


    @Etque("la commande groupé n'existe pas")
    public void laCommandeGroupéNExistePas() {
        assertNull(commandeGroupe);
    }

    @Etque("{string} {string} ajoute sa commande à la commande groupé")
    public void ajouteSaCommandeÀLaCommandeGroupé(String nomPersonne, String prenomPersonne) {
        CommandeIndividuelle commandeIndividuelle = commandeManager.creerCommande(compteUtilisateur);
        commandeIndividuelle.ajoutMenuPlat(new Menu("Pate", 10), 1);
        commandeGroupe.ajoutCommandeIndividuelle(commandeIndividuelle);

        CompteUtilisateur cU = commandeIndividuelle.getCompteUtilisateur();
        assertEquals(cU.getNom(), nomPersonne);
        assertEquals(cU.getPrenom(), prenomPersonne);
    }

    @Etqu("il supprime sa commande de la commande groupé")
    public void supprimeSaCommandeDeLaCommandeGroupé() {
        commandeManager.supprimerCommandeIndividuelleGroupee(commandeGroupe.getId(), compteUtilisateur);
    }

    @Alors("la commande groupé contient {int} commandes et s'élève à {int}€")
    public void laCommandeGroupéContientCommandesEtSÉlèveÀ€(int nombreCommande, int prixTotal) {
        assertEquals(nombreCommande, commandeGroupe.getCommandes().size());
        assertEquals(prixTotal, commandeGroupe.getPrix(), 0.001);
    }

    @Et("la commande groupé contient cette commande")
    public void laCommandeGroupéContientCetteCommande() {
        assertTrue(commandeGroupe.getCommandeUtilisateur(compteUtilisateur).isPresent());
    }

    @Et("la commande groupé ne contient pas cette commande")
    public void laCommandeGroupéNeContientPasCetteCommande() {
        assertTrue(commandeGroupe.getCommandeUtilisateur(compteUtilisateur).isEmpty());
    }

    @Alors("une erreur {string} est récupérer")
    public void uneErreurEstRécupérer(String nomErreur){
        assertEquals(nomErreur, messageErreur);
    }
}
