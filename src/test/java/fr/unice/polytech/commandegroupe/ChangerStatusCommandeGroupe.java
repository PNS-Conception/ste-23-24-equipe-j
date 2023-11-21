package fr.unice.polytech.commandegroupe;

import fr.unice.polytech.builder.TypeCommandeAjoutable;
import fr.unice.polytech.builder.TypeCommandeSimple;
import fr.unice.polytech.commande.CommandeGroupe;
import fr.unice.polytech.commande.EtatCommande;
import fr.unice.polytech.commande.SystemeCommande;
import fr.unice.polytech.commande.interfacecommande.ICommandeAjoutable;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import io.cucumber.java.fr.*;

import static org.junit.Assert.assertEquals;

public class ChangerStatusCommandeGroupe {
    SystemeCommande systemeCommande;
    CommandeGroupe commandeGroupe;
    ICommandeAjoutable commande;
    String erreurMessage;

    @Etantdonnéque("la commande groupé possède {int} commande")
    public void lacommandegroupepossedecommandes(int nombreCommande){
        systemeCommande = new SystemeCommande();
        commandeGroupe = (CommandeGroupe) systemeCommande.creerCommandeSimpleMultipleGroupe(
                new CompteUtilisateur("test", "test"), TypeCommandeSimple.GROUPEE);

        for (int i = 0; i < nombreCommande; i++) {
            ICommandeAjoutable commandeAjoutable = systemeCommande.creerCommandeAjoutable(
                    new CompteUtilisateur("test" + i, "test"),
                    TypeCommandeAjoutable.SIMPLE, (int) commandeGroupe.getIdCommande());

            commandeGroupe.ajouterCommande(commandeAjoutable);
        }

        commande = commandeGroupe.getCommandes().get(0);
        assertEquals(nombreCommande, commandeGroupe.getCommandes().size());
    }

    @Quand("l'utilisateur paye la commande")
    public void lUtilisateurPayeLaCommande() {
        commande.payerCommande();
    }


    @Alors("la commande est {string}")
    public void laCommandeEstEN_PREPARATION(String status) {
        assertEquals(status, commande.getEtatCommande().toString());
    }

    @Etque("la commande groupe est {string}")
    public void laCommandeGroupeEst(String status) {
        assertEquals(status, commandeGroupe.getEtatCommande().toString());
    }

    @Quand("le restaurateur passe une commande {string}")
    public void leRestaurateurPasseUneCommande(String status) {
        commande.setEtatCommande(EtatCommande.valueOf(status));
    }

    @Etqu("une commande est {string}")
    public void uneCommandeEst(String status) {
       ICommandeAjoutable commandeAjoutable = commandeGroupe.getCommandes().get(1);
       commandeAjoutable.setEtatCommande(EtatCommande.valueOf(status));

       assertEquals(status, commandeAjoutable.getEtatCommande().toString());
    }

    @Quand("l'utilisateur passe la commande groupé {string}")
    public void lUtilisateurPasseLaCommandeGroupé(String status) {
        try {
            commandeGroupe.setEtatCommande(EtatCommande.valueOf(status));
        } catch (IllegalArgumentException e) {
            erreurMessage = e.getMessage();
        }
    }

    @Alors("une {string} doit être retournée")
    public void uneDoitÊtreRetournée(String messageErreur) {
        assertEquals(messageErreur, erreurMessage);
    }
}
