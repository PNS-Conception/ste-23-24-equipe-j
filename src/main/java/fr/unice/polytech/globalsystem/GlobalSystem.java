package fr.unice.polytech.globalsystem;

import fr.unice.polytech.tracabilite.Statistique;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utilisateur.StatusUtilisateur;
import fr.unice.polytech.utils.adress.SavedPosition;

public class GlobalSystem {
    /**
     * Classe qui permet de créer un compte utilisateur tout en syncronisant entre les différents utilisateurs les mêmes objets :
     * - Statistique
     * - SavedPosition
     */

    private Statistique statistique;
    private SavedPosition savedPosition;

    /**
     * Constructeur par défaut
     */
    public GlobalSystem() {
        this.statistique = new Statistique();
        this.savedPosition = new SavedPosition();
    }

    public CompteUtilisateur createAccount() {
        return new CompteUtilisateur("nom", "prenom", statistique, savedPosition);
    }

    public CompteUtilisateur createAccount(String nom, String prenom) {
        return new CompteUtilisateur(nom, prenom, statistique, savedPosition);
    }

    public CompteUtilisateur createAccount(String nom, String prenom, String password) {
        return new CompteUtilisateur(nom, prenom, statistique, savedPosition, password);
    }

    public CompteUtilisateur createAccount(String nom, String prenom, StatusUtilisateur statusUtilisateur) {
        return new CompteUtilisateur(nom,prenom,statistique,savedPosition,statusUtilisateur);
    }

    public CompteUtilisateur createAccount(String nom, String prenom, StatusUtilisateur statusUtilisateur, String password) {
        return new CompteUtilisateur(nom,prenom,password, statistique,statusUtilisateur, savedPosition);
    }
}
