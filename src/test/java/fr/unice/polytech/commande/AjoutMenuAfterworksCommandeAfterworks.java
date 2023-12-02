package fr.unice.polytech.commande;

import fr.unice.polytech.nourriture.*;
import fr.unice.polytech.restaurant.RestaurantNonValideException;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import io.cucumber.java.fr.*;

import static org.junit.Assert.*;

public class AjoutMenuAfterworksCommandeAfterworks {
    CompteUtilisateur utilisateur;
    CommandeAfterworks commandeAfterworks;
    MenuPlat menuPlat;
    String messageErreur;

    @Etantdonnéqu("un utilisateur se nomme {string} {string}")
    public void UnUtilisateurSeNomme(String prenom, String nom) {
        utilisateur = new CompteUtilisateur(nom, prenom);
    }

    @Etqu("il possède une commande afterwork avec {int} personnes")
    public void ilPossèdeUneCommandeAfterworkAvecPersonnes(int nombrePersonne) {
        commandeAfterworks = new CommandeAfterworks(0, utilisateur, nombrePersonne);
    }

    @Quand("il ajoute le menu afterworks {string} à {int}€")
    public void ilAjouteLeMenuAfterworksÀ€(String nomMenu, int prix) throws RestaurantNonValideException {
        menuPlat = new Menu(nomMenu, prix, TypeMenu.AFTERWORKS);
        commandeAfterworks.ajoutMenuPlat(menuPlat, TypeMenuPlat.MENU);
    }

    @Alors("le menu est ajouté à la commande")
    public void leMenuEstAjoutéÀLaCommande() {
        assertTrue(commandeAfterworks.getMenuPlats().containsKey(menuPlat));
    }

    @Quand("il ajoute le menu classique {string} à {int}€")
    public void ilAjouteLeMenuClassiqueÀ€(String nomMenu, int prix) throws RestaurantNonValideException {
        menuPlat = new Menu(nomMenu, prix);

        try {
            commandeAfterworks.ajoutMenuPlat(menuPlat, TypeMenuPlat.MENU);
        }
        catch (IllegalArgumentException iAE) {
            messageErreur = iAE.getMessage();
        }
    }

    @Alors("une erreur est renvoyé {string}")
    public void uneErreurEstRenvoyé(String messageErreur) {
        assertEquals(messageErreur, this.messageErreur);
    }

    @Quand("il ajoute le plat {string} à {int}€")
    public void ilAjouteLePlatÀ€(String nomPlat, int prix) throws RestaurantNonValideException {
        menuPlat = new Plat(nomPlat, prix, null, null);

        try {
            commandeAfterworks.ajoutMenuPlat(menuPlat, TypeMenuPlat.PLAT);
        }
        catch (IllegalArgumentException iAE) {
            messageErreur = iAE.getMessage();
        }
    }

    @Et("le menu n'est pas ajouté")
    public void leMenuNEstPasAjouté() {
        assertFalse(commandeAfterworks.getMenuPlats().containsKey(menuPlat));
    }
}
