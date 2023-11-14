package fr.unice.polytech.utilisateur;

public enum StatusUtilisateur {
    ETUDIANT,
    PROFESSEUR,
    ADMINISTRATEUR;

    /**
     * Retourne l'attribut de l'enum en fonction du paramètre status
     *
     * @param status status de l'utilisateur
     * @return l'attribut de l'enum en fonction du paramètre status ou <code>null</code> si le status n'existe pas
     */
    public static StatusUtilisateur getStatutUtilisateur(String status) {
        return switch (status) {
            case "ETUDIANT" -> ETUDIANT;
            case "PROFESSEUR" -> PROFESSEUR;
            case "ADMINISTRATEUR" -> ADMINISTRATEUR;
            default -> null;
        };
    }
}
