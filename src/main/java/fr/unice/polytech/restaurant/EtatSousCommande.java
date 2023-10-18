package fr.unice.polytech.restaurant;

public enum EtatSousCommande {

    EN_ATTENTE,
    EN_PREPARATION,
    PRETE;

    public static EtatSousCommande getEtatSousCommande(String etat) {
        switch (etat) {
            case "EN_ATTENTE":
                return EN_ATTENTE;
            case "EN_PREPARATION":
                return EN_PREPARATION;
            case "PRETE":
                return PRETE;
            default:
                return null;
        }
    }
}
