package fr.unice.polytech.globalSystem;

import fr.unice.polytech.traçabilite.Statistique;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utilisateur.UserStatut;
import fr.unice.polytech.utils.adress.SavedPosition;

public class GlobalSystem {


    private Statistique statistique;
    private SavedPosition savedPosition;


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

    public CompteUtilisateur createAccount(String nom, String prenom, UserStatut userStatut) {
        return new CompteUtilisateur(nom,prenom,statistique,savedPosition,userStatut);
    }

    public CompteUtilisateur createAccount(String nom, String prenom, UserStatut userStatut, String password) {
        return new CompteUtilisateur(nom,prenom,password, statistique,userStatut, savedPosition);
    }
}
