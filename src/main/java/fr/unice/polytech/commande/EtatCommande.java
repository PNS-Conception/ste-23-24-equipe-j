package fr.unice.polytech.commande;

/**
 * Enum des états de suivi d'une commande
 * @author Equipe J
 */
public enum EtatCommande {
    ANNULE,
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
            case "ANNULE" -> ANNULE;
            case "EN_ATTENTE" -> EN_ATTENTE;
            case "EN_PREPARATION" -> EN_PREPARATION;
            case "PRETE" -> PRETE;
            default -> null;
        };
    }
}
