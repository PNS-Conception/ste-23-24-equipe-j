package fr.unice.polytech.restaurant;

public class Commande {

    private static int dernierIdentifiant = 0;
    private int id;
    private String nomCommande;
    private String statusPreparation;
    private String statusLivraison;

    public Commande(String nomCommande) {
        this.id = dernierIdentifiant;
        dernierIdentifiant++;
        this.nomCommande = nomCommande;
        this.statusPreparation = EtatSousCommande.EN_ATTENTE.toString();
        this.statusLivraison = EtatLivraisonCommande.NON_PRETE_POUR_LIVRAISON.toString();
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

}
