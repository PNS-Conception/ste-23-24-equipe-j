package fr.unice.polytech.utilisateur;

import fr.unice.polytech.commande.Commande;
import fr.unice.polytech.restaurant.PasswordException;
import fr.unice.polytech.restaurant.TokenException;
import fr.unice.polytech.utils.Position;
import fr.unice.polytech.utils.Token;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe représentant un compte utilisateur
 * @author Equipe J
 */
public class CompteUtilisateur {
    public static final String DEFAULT_PASSWORD = "0000";
    private final String nom;
    private final String prenom;
    private String password = DEFAULT_PASSWORD;
    private int solde; // en centimes pour éviter les erreurs d'arrondi
    private List<Position> adresseEnregistrees;

    private ArrayList<Commande> historiqueCommandes;

    private ArrayList<Token> tokens;

    // Constructeur
    /**
     * Constructeur par défaut
     * @param nom nom de l'utilisateur
     * @param prenom prénom de l'utilisateur
     */
    public CompteUtilisateur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        solde = 0;
        adresseEnregistrees = new ArrayList<>();
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

    public ArrayList<Commande> getHistoriqueCommandes() {
        return this.historiqueCommandes;
    }

    public void ajouterCommande(Commande commande, Token token) throws TokenException {
        if (tokens.contains(token)) {
            tokens.remove(token);
            this.historiqueCommandes.add(commande);
        } else {
            throw new TokenException();
        }
    }

    public Token createToken(String mdp) throws PasswordException {
        if (Objects.equals(mdp, this.password)) {
            Token token = new Token(this);
            tokens.add(token);
            return token;
        } else {
            throw new PasswordException();
        }
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
