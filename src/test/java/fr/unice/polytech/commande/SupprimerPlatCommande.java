package fr.unice.polytech.commande;

import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.nourriture.Plat;
import fr.unice.polytech.nourriture.TypeMenuPlat;
import fr.unice.polytech.restaurant.CapaciteDepasseException;
import fr.unice.polytech.restaurant.RestaurantNonValideException;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import io.cucumber.java.fr.*;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SupprimerPlatCommande {
    CommandeSimple commande;
    Plat plat;

    @Etantdonnéque("l'utilisateur a une commande")
    public void lUtilisateurAUneCommande() {
        commande = new CommandeSimple(0, new CompteUtilisateur("nom", "prenom"));
    }

    @Et("avec {int} plat {string} et que ce plat coûte {int}€")
    public void avecPlatEtQueCePlatCoûte€(int nombrePlat, String nomPlat, int prixPlat) throws RestaurantNonValideException, CapaciteDepasseException {
        plat = new Plat(nomPlat, prixPlat, null, null);

        for (int i = 0; i < nombrePlat; i++)
            commande.ajoutMenuPlat(plat, TypeMenuPlat.PLAT);

        Map<MenuPlat, Integer> menuPlats = commande.getMenuPlats();
        assertTrue(menuPlats.containsKey(plat));
        assertEquals(nombrePlat, menuPlats.get(plat).intValue());
        assertEquals(prixPlat * nombrePlat, commande.getPrix(), 0.001);
    }

    @Quand("l'utilisateur supprime le plat {string}")
    public void lUtilisateurSupprimeLePlat(String nomPlat) {
        if (plat == null)
            plat = new Plat(nomPlat, 7, null, null);
        commande.supprimerMenuPlat(plat);
        assertEquals(nomPlat, plat.getNom());
    }

    @Alors("la commande contient {int} plat {string}")
    public void laCommandeContientPlat(int nombrePlat, String nomPlat) {
        int nombrePlatDansCommande = commande.getMenuPlats().getOrDefault(plat, 0);

        assertEquals(nombrePlat, nombrePlatDansCommande);
        assertEquals(nomPlat, plat.getNom());
    }

    @Etque("la commande coûte {int}€")
    public void laCommandeCoûte€(int prix) {
        assertEquals(prix, commande.getPrix(), 0.001);
    }
}
