package fr.unice.polytech.utilisateur;

public enum StatusUtilisateur {
    ETUDIANT,
    PROFESSEUR,
    ADMINISTRATEUR,
    PERSONNEL,
    EXTERIEUR,
    NORMAL;

    /**
     * Retourne l'attribut de l'enum en fonction du paramètre status
     *
     * @param status status de l'utilisateur
     * @return l'attribut de l'enum en fonction du paramètre status ou <code>null</code> si le status n'existe pas
     */
    public static StatusUtilisateur getStatusUtilisateur(String status) {
        String statusUpperCase = status.toUpperCase();
        return switch (statusUpperCase) {
            case "ETUDIANT" -> ETUDIANT;
            case "PROFESSEUR" -> PROFESSEUR;
            case "PERSONNEL" -> PERSONNEL;
            case "EXTERIEUR" -> EXTERIEUR;
            case "NORMAL" -> NORMAL;
            default -> NORMAL;
        };
    }
}
