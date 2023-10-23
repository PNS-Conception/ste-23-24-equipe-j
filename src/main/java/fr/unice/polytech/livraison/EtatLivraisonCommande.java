package fr.unice.polytech.livraison;

/**
 * Etat de la livraison d'une commande
 * @author Equipe J
 */
public enum EtatLivraisonCommande {

    NON_PRETE_POUR_LIVRAISON,
    PRETE_POUR_LIVRAISON,
    EN_LIVRAISON,
    LIVREE;

    /**
     * Retourne l'attribut de l'enum en fonction du paramètre état
     * @param etat état de la livraison
     * @return l'attribut de l'enum en fonction du paramètre état ou <code>null</code> si l'état n'existe pas
     */
    public static EtatLivraisonCommande getEtatLivraisonCommande(String etat) {
        return switch (etat) {
            case "NON_PRETE_POUR_LIVRAISON" -> NON_PRETE_POUR_LIVRAISON;
            case "PRETE_POUR_LIVRAISON" -> PRETE_POUR_LIVRAISON;
            case "EN_LIVRAISON" -> EN_LIVRAISON;
            case "LIVREE" -> LIVREE;
            default -> null;
        };
    }
}
