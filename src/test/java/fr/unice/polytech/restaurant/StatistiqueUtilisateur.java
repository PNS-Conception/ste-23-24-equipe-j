package fr.unice.polytech.restaurant;

import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonnéque;
import io.cucumber.java.fr.Etque;
import io.cucumber.java.fr.Quand;

public class StatistiqueUtilisateur {

    @Etantdonnéque("l'utilisateur {string} {string} est connecté")
    public void lUtilisateurEstLoggé(String prenom, String nom) {
    }

    @Etque("{string} {string} n'a jamais effectué de commande")
    public void nAJamaisEffectuéDeCommande(String prenom, String name) {
    }

    @Quand("{string} {string} accède à sa page de statistique, il obtient une valeur {int}.")
    public void accèdeÀSaPageDeStatistiqueIlObtientUneValeur(String prenom, String name, int tailleStatistique) {
    }

    @Alors("il effectue une commande dans ce restaurant {string}.")
    public void ilEffectueUneCommandeDansCeRestaurant(String nomRestaurant) {
    }
}
