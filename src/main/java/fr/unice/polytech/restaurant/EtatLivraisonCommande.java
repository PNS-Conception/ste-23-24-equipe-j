package fr.unice.polytech.restaurant;

public enum EtatLivraisonCommande {

    NON_PRETE_POUR_LIVRAISON,
    PRETE_POUR_LIVRAISON,
    EN_LIVRAISON,
    LIVREE;

    public static EtatLivraisonCommande getEtatLivraisonCommande(String etat) {
        switch (etat) {
            case "NON_PRETE_POUR_LIVRAISON":
                return NON_PRETE_POUR_LIVRAISON;
            case "PRETE_POUR_LIVRAISON":
                return PRETE_POUR_LIVRAISON;
            case "EN_LIVRAISON":
                return EN_LIVRAISON;
            case "LIVREE":
                return LIVREE;
            default:
                return null;
        }
    }
}
