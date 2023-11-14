package fr.unice.polytech.utilisateur;

import fr.unice.polytech.utils.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un compte utilisateur
 * @author Equipe J
 */
public class CompteUtilisateur {

    private final String nom;
    private final String prenom;
    private int solde; // en centimes pour éviter les erreurs d'arrondi
    private List<Position> adresseEnregistrees;
    private boolean authentifie;
    private StatusUtilisateur statusUtilisateur;


    // Constructeur
    /**
     * Constructeur par défaut
     * @param nom nom de l'utilisateur
     * @param prenom prénom de l'utilisateur
     */
    public CompteUtilisateur(String nom, String prenom, String status) {
        this.nom = nom;
        this.prenom = prenom;
        solde = 0;
        adresseEnregistrees = new ArrayList<>();
        authentifie = true;
        statusUtilisateur = StatusUtilisateur.getStatutUtilisateur(status);
    }

    // Accesseurs

    /**
     * Récupère le nom du compte de l'utilisateur
     * @return Le nom du compte de l'utilisateur
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Récupère le prénom du compte de l'utilisateur
     * @return Le prénom du compte de l'utilisateur
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * Récupère le solde du compte de l'utilisateur
     * @return Le solde du compte de l'utilisateur
     */
    public int getSolde() {
        return this.solde;
    }

    /**
     * Récupère si l'utilisateur est authentifié ou non
     * @return Le boolean du compte de l'utilisateur
     */
    public boolean isAuthentifie() {
        return authentifie;
    }

    public StatusUtilisateur getStatusUtilisateur() {
        return statusUtilisateur;
    }

    public List<Position> getAdresseEnregistrees() {
        return adresseEnregistrees;
    }

    public Position getAdresseEnregistreesParNom(String nom) {
        for (Position adresse : adresseEnregistrees) {
            if (adresse.getNomPosition().equals(nom)) {
                return adresse;
            }
        }
        return null;
    }

    // Méthodes
    public void ajouterAdresse(Position adresse) {
        adresseEnregistrees.add(adresse);
    }
}
