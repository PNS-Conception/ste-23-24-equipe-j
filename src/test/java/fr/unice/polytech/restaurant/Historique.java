package fr.unice.polytech.restaurant;

import fr.unice.polytech.utilisateur.CompteUtilisateur;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonnéque;
import io.cucumber.java.fr.Etque;
import io.cucumber.java.fr.Quand;

public class Historique {


    @Etantdonnéque("l'utilisateur {string} {string} est connecté")
    public void lUtilisateurEstLoggé(String prenom, String nom) {
    }

    @Etque("{string} {string} n'a jamais effectué de commande")
    public void nAJamaisEffectuéDeCommande(String prenom, String nom) {
    }

    @Quand("{string} {string} veut accéder à l'historique")
    public void veutAccéder(String prenom, String nom) {
    }

    @Alors("il obtient une liste de taille {int}")
    public void ilObtientUneListeDeTaille(int tailleHistorique) {
    }
}
