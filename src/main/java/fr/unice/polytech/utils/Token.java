package fr.unice.polytech.utils;

import fr.unice.polytech.utilisateur.CompteUtilisateur;

public class Token {

    private static int idGlobal = 0;
    CompteUtilisateur compteUtilisateur;


    public Token(CompteUtilisateur compteUtilisateur) {
        this.compteUtilisateur = compteUtilisateur;
        idGlobal++;
    }

}
