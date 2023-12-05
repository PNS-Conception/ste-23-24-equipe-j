package fr.unice.polytech.commandegroupe;

import fr.unice.polytech.builder.TypeCommandeAjoutable;
import fr.unice.polytech.builder.TypeCommandeSimple;
import fr.unice.polytech.commande.CommandeGroupe;
import fr.unice.polytech.commande.CommandeSimpleAjoutable;
import fr.unice.polytech.commande.SystemeCommande;
import fr.unice.polytech.commande.interfacecommande.ICommandeAjoutable;
import fr.unice.polytech.globalsystem.GlobalSystem;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonnéque;
import io.cucumber.java.fr.Etque;
import io.cucumber.java.fr.Quand;

import static org.junit.Assert.*;

public class RejoindreCommandeGroupe {
    SystemeCommande systemeCommande;
    CommandeGroupe commandeGroupe;
    ICommandeAjoutable iCommandeAjoutable;
    GlobalSystem globalSystem = new GlobalSystem();


    @Etantdonnéque("il existe une commande groupe d ID {int}")
    public void ilExisteUneCommandeGroupeDID(int id){
        systemeCommande = new SystemeCommande();
        commandeGroupe = (CommandeGroupe) systemeCommande.creerCommandeSimpleMultipleGroupe(
                this.globalSystem.createAccount("test", "test"), TypeCommandeSimple.GROUPEE);

        assertEquals(id, commandeGroupe.getIdCommande());
    }


    @Etantdonnéque("le nombre de commande est {int}")
    public void leNombreDeCommandeEst(int nombreCommande) {
        assertEquals(nombreCommande, commandeGroupe.getCommandes().size());
    }

    @Alors("la commande Groupe possède la commande")
    public void laCommandeGroupePossèdeLaCommande() {
        assertTrue(commandeGroupe.getCommandes().contains(iCommandeAjoutable));
    }

    @Quand("l utilisateur ajoute {int} commande à cette commande groupe")
    public void lUtilisateurAjouteCommandeÀCetteCommandeGroupe(int nombreCommande) {
            iCommandeAjoutable = systemeCommande.creerCommandeAjoutable(this.globalSystem.createAccount("test3", "test3"),
                    TypeCommandeAjoutable.SIMPLE, (int) commandeGroupe.getIdCommande());

            assertEquals(nombreCommande, commandeGroupe.getCommandes().size());
    }

    @Quand("l utilisateur supprime cette commande")
    public void lUtilisateurSupprimeCetteCommande() {
        commandeGroupe.supprimerCommande(iCommandeAjoutable);
    }

    @Quand("l utilisateur supprime cette commande et la commande groupe n a pas cette commande")
    public void lUtilisateurSupprimeCetteCommandeEtLaCommandeGroupeNAPasCetteCommande() {
        CommandeGroupe commandeGroupeFausse = new CommandeGroupe(15,
                this.globalSystem.createAccount("test3", "test3"));

        ICommandeAjoutable commandeAjoutableFausse = new CommandeSimpleAjoutable(15,
                this.globalSystem.createAccount("test4", "test4"), commandeGroupeFausse);

        commandeGroupe.supprimerCommande(commandeAjoutableFausse);
        assertFalse(commandeGroupe.getCommandes().contains(commandeAjoutableFausse));
    }

    @Etque("la commande groupe n a pas cette commande")
    public void laCommandeGroupeNAPasCetteCommande() {
        assertFalse(commandeGroupe.getCommandes().contains(iCommandeAjoutable));
    }
}
