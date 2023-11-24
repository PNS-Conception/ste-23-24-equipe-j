package fr.unice.polytech.utilisateur;

import fr.unice.polytech.commande.EtatCommande;

public enum UserStatut {

    ETUDIANT,
    PROFESSEUR,
    PERSONNEL,
    EXTERIEUR,
    NORMAL;


    public static UserStatut getEtatUtilisateur(String etat) {
        return switch (etat) {
            case "ETUDIANT" -> ETUDIANT;
            case "PROFESSEUR" -> PROFESSEUR;
            case "PERSONNEL" -> PERSONNEL;
            case "EXTERIEUR" -> EXTERIEUR;
            case "NORMAL" -> NORMAL;
            default -> NORMAL;
        };
    }



}
