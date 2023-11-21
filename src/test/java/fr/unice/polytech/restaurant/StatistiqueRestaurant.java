package fr.unice.polytech.restaurant;

import fr.unice.polytech.utilisateur.CompteUtilisateur;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonnéque;
import io.cucumber.java.fr.Etque;
import io.cucumber.java.fr.Quand;

public class StatistiqueRestaurant {

    @Etantdonnéque("l'utilisateur {string} {string} est connecté")
    public void lUtilisateurEstLoggé(String prenom, String nom) {
    }

    @Etque("le restaurant {string} n'a jamais effectué de commande")
    public void leRestaurantNAJamaisEffectuéDeCommande(String nomRestaurant) {
    }

    @Quand("{string} {string} accède à la page des statistiques du restaurant {string}, il obtient une valeur {int}.")
    public void accèdeÀLaPageDesStatistiquesDuRestaurantIlObtientUneValeur(String prenom, String nom, String nomRestaurant, int tailleStatistique) {
    }

    @Alors("il effectue une commande dans ce restaurant {string}.")
    public void ilEffectueUneCommandeDansCeRestaurant(String nomRestaurant) {
    }
}
