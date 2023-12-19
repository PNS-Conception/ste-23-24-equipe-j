package fr.unice.polytech.utilisateur;

public class UtilisateurNonAuthentifieException extends Exception {
    public UtilisateurNonAuthentifieException() {
        super("Utilisateur non authentifié");
    }
}
