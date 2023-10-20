package fr.unice.polytech.restaurant;

import fr.unice.polytech.commande.SousCommande;

import java.util.ArrayList;

public class Commande {

    private static int dernierIdentifiant = 0;
    private int id;
    private String nomCommande;
    private String statusPreparation;
    private String statusLivraison;
    private boolean isPayed;
    private ArrayList<SousCommande> sousCommandes;

    private Date dateCommande;
    private Horaire horaireCommande;

    public Commande(String nomCommande) {
        this.id = dernierIdentifiant;
        dernierIdentifiant++;
        this.nomCommande = nomCommande;
        this.statusPreparation = EtatSousCommande.EN_ATTENTE.toString();
        this.statusLivraison = EtatLivraisonCommande.NON_PRETE_POUR_LIVRAISON.toString();
        this.isPayed = false;
        this.sousCommandes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNomCommande() {
        return nomCommande;
    }

    public String getStatusPreparation() {
        return statusPreparation;
    }

    public String getStatusLivraison() {
        return statusLivraison;
    }

    public void setStatusPreparation(String statusPreparation) {
        this.statusPreparation = statusPreparation;
    }

    public void setStatusLivraison(String statusLivraison) {
        this.statusLivraison = statusLivraison;
    }

    public void setIsPayed() {
        this.isPayed = true;
    }

    public boolean getIsPayed() {
        return this.isPayed;
    }

    public void addSousCommande(SousCommande sousCommande) {
        this.sousCommandes.add(sousCommande);
    }

    public void setDate (Date date) {
        this.dateCommande = date;
    }

    public void setHoraire (Horaire horaire) {
        this.horaireCommande = horaire;
    }

    public Date getDate () {
        return this.dateCommande;
    }

    public Horaire getHoraire () {
        return this.horaireCommande;
    }

}
