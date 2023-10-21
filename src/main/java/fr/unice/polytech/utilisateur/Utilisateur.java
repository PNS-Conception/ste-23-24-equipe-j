package fr.unice.polytech.utilisateur;

import fr.unice.polytech.restaurant.Commande;

public class Utilisateur {

    private String nom;
    private String prenom;
    private int solde; // en centimes in order to avoid floating errors
    private String motDePasse;
    private boolean isLogged;

    public Utilisateur(String nom, String prenom, int solde, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.solde = solde;
        this.motDePasse = motDePasse;
        this.isLogged = false;
    }

    public void logIn(String motDePasse) {
        if (this.motDePasse.equals(motDePasse)) {
            this.isLogged = true;
        }
    }

    public void logOut() {
        this.isLogged = false;
    }

    public boolean isLogged() {
        return this.isLogged;
    }

    public void addCommande(Commande commande) {
        if (this.isLogged) {
            // TODO
        }
    }

    public Commande getCommande(int id) {
        if (this.isLogged) {
            // TODO
        }
        return null;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }


}
