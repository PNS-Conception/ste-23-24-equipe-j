package fr.unice.polytech.commandegroupe;

import fr.unice.polytech.builder.TypeCommandeSimple;
import fr.unice.polytech.commande.CommandeGroupe;
import fr.unice.polytech.commande.SystemeCommande;
import fr.unice.polytech.globalsystem.GlobalSystem;
import fr.unice.polytech.utils.adress.Position;
import fr.unice.polytech.utils.temps.Date;
import fr.unice.polytech.utils.temps.Horaire;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonnéque;
import io.cucumber.java.fr.Quand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ChangerInformationLivraisonCommande {
    SystemeCommande systemeCommande;
    CommandeGroupe commandeGroupe;
    GlobalSystem globalSystem = new GlobalSystem();


    @Etantdonnéque("la commande ne possède pas d'informations de livraison")
    public void laCommandeNePossèdePasDInformationsDeLivraison() {
        systemeCommande = new SystemeCommande();
        commandeGroupe = (CommandeGroupe) systemeCommande.creerCommandeSimpleMultipleGroupe(
                this.globalSystem.createAccount(), TypeCommandeSimple.GROUPEE);

        assertNull(commandeGroupe.getInformationLivraison().getDateLivraison());
        assertNull(commandeGroupe.getInformationLivraison().getLieuxLivraison());
    }

    @Etantdonnéque("la commande possède la date {string} et le lieu {string}")
    public void laCommandePossèdeLaDateEtLeLieu(String date, String lieu) {
        systemeCommande = new SystemeCommande();
        commandeGroupe = (CommandeGroupe) systemeCommande.creerCommandeSimpleMultipleGroupe(
                this.globalSystem.createAccount(), TypeCommandeSimple.GROUPEE);

        commandeGroupe.setInformationLivraison(new Date(date), new Horaire("23:00"), new Position(lieu));
    }

    @Quand("l'utilisateur veut ajouter la date {string} et le lieu {string}")
    public void lUtilisateurVeutAjouterLaDateEtLeLieu(String date, String lieu) {
        commandeGroupe.setInformationLivraison(new Date(date), new Horaire("23:00"), new Position(lieu));
    }

    @Alors("la commande groupe contiendra la date {string} et le lieu {string}")
    public void laCommandeGroupeContiendraLaDateEtLeLieu(String date, String lieu) {
        Date dateObj = new Date(date);
        Date dateCommande = commandeGroupe.getInformationLivraison().getDateLivraison();
        Position position = new Position(lieu);

        assertEquals(dateObj.getAnnee(), dateCommande.getAnnee());
        assertEquals(dateObj.getMois(), dateCommande.getMois());
        assertEquals(dateObj.getJour(), dateCommande.getJour());
        assertEquals(position, commandeGroupe.getInformationLivraison().getLieuxLivraison());
    }
}
