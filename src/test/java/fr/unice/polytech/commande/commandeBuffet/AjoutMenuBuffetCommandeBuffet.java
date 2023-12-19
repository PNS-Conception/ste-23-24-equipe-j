package fr.unice.polytech.commande.commandeBuffet;

import fr.unice.polytech.commande.CommandeBuffet;
import fr.unice.polytech.exceptions.CapaciteDepasseException;
import fr.unice.polytech.exceptions.RestaurantNonValideException;
import fr.unice.polytech.nourriture.*;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utilisateur.StatusUtilisateur;
import io.cucumber.java.fr.*;

import static org.junit.Assert.*;

public class AjoutMenuBuffetCommandeBuffet {
    CommandeBuffet commandeBuffet;
    String messageErreur;
    MenuPlat menuPlat;

    @Etantdonnéque("le {string} {string} {string} possède une commande Buffet avec {string} {string} comme destinataire")
    public void lePossèdeUneCommandeBuffetAvecCommeDestinataire(String typeUtilisateur, String prenom1, String nom1,
                                                                String prenom2, String nom2) {

        StatusUtilisateur statutUtilisateur = StatusUtilisateur.getStatusUtilisateur(typeUtilisateur);
        CompteUtilisateur createur = new CompteUtilisateur(nom1, prenom1, null, null, statutUtilisateur);
        CompteUtilisateur destinataire = new CompteUtilisateur(nom2, prenom2, null, null, StatusUtilisateur.ETUDIANT);

        commandeBuffet = new CommandeBuffet(0, createur, destinataire);
    }

    @Quand("il ajoute le menu Buffet {string}")
    public void ilAjouteLeMenuBuffetCrêpe(String nomMenu) throws CapaciteDepasseException, RestaurantNonValideException {
        menuPlat = new Menu(nomMenu, 5, TypeMenu.BUFFET);
        commandeBuffet.ajoutMenuPlat(menuPlat, TypeMenuPlat.MENU);
    }

    @Quand("il ajoute le menu classique {string}")
    public void ilAjouteLeMenuClassiquePate(String nomMenu) throws CapaciteDepasseException, RestaurantNonValideException {
        menuPlat = new Menu(nomMenu, 5, TypeMenu.NORMAL);

        try {
            commandeBuffet.ajoutMenuPlat(menuPlat, TypeMenuPlat.MENU);
        } catch (IllegalArgumentException iAE) {
            messageErreur = iAE.getMessage();
        }
    }

    @Quand("il ajoute le plat {string}")
    public void ilAjouteLePlatPate(String nomPlat) throws CapaciteDepasseException, RestaurantNonValideException {
        menuPlat = new Plat(nomPlat, 5, null, null);

        try {
            commandeBuffet.ajoutMenuPlat(menuPlat, TypeMenuPlat.PLAT);
        } catch (IllegalArgumentException iAE) {
            messageErreur = iAE.getMessage();
        }
    }

    @Alors("le menu est ajouté à la commande")
    public void leMenuEstAjoutéÀLaCommande() {
        assertTrue(commandeBuffet.getMenuPlats().containsKey(menuPlat));
    }

    @Alors("une erreur est renvoyé {string}")
    public void uneErreurEstRenvoyé(String messageErreur) {
        assertEquals(messageErreur, this.messageErreur);
    }

    @Et("le menu n'est pas ajouté à la commande")
    public void leMenuNEstPasAjoutéÀLaCommande() {
        assertFalse(commandeBuffet.getMenuPlats().containsKey(menuPlat));
    }
}