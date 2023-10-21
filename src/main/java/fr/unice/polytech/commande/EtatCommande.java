package fr.unice.polytech.commande;

/**
 * Enum de l'état d'une sous commande dans le restaurant
 */
public enum EtatCommande {

    EN_ATTENTE,
    EN_PREPARATION,
    PRETE;

    /**
     * Retourne l'attribut de l'enum en fonction du paramètre état
     * @param etat état de la livraison
     * @return l'attribut de l'enum en fonction du paramètre état ou <code>null</code> si l'état n'existe pas
     */
    public static EtatCommande getEtatSousCommande(String etat) {
        return switch (etat) {
            case "EN_ATTENTE" -> EN_ATTENTE;
            case "EN_PREPARATION" -> EN_PREPARATION;
            case "PRETE" -> PRETE;
            default -> null;
        };
    }
}
