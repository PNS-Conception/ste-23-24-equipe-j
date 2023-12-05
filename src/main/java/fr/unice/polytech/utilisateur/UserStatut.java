package fr.unice.polytech.utilisateur;

public enum UserStatut {

    ETUDIANT,
    PROFESSEUR,
    PERSONNEL,
    EXTERIEUR,
    NORMAL;


    public static UserStatut getEtatUtilisateur(String etat) {
        String etatModificated = etat.toUpperCase();
        return switch (etatModificated) {
            case "ETUDIANT" -> ETUDIANT;
            case "PROFESSEUR" -> PROFESSEUR;
            case "PERSONNEL" -> PERSONNEL;
            case "EXTERIEUR" -> EXTERIEUR;
            case "NORMAL" -> NORMAL;
            default -> NORMAL;
        };
    }



}
