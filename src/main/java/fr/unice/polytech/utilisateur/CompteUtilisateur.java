package fr.unice.polytech.utilisateur;

import fr.unice.polytech.commande.CommandeAvecID;
import fr.unice.polytech.restaurant.PasswordException;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.restaurant.Statistique;
import fr.unice.polytech.restaurant.TokenException;
import fr.unice.polytech.utils.Position;
import fr.unice.polytech.utils.Token;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import fr.unice.polytech.observer.EventListener;
import java.util.List;
import java.util.Objects;

/**
 * Classe représentant un compte utilisateur
 * @author Equipe J
 */
public class CompteUtilisateur implements EventListener {
    public static final String DEFAULT_PASSWORD = "0000";
    private final String statisticUserPassword = "0000";
    private final String nom;
    private final String prenom;
    private String password = DEFAULT_PASSWORD;
    private int solde; // en centimes pour éviter les erreurs d'arrondi
    private List<Position> adresseEnregistrees;
    private final Statistique statistique = new Statistique();

    private ArrayList<CommandeAvecID> historiqueCommandes;

    private ArrayList<Token> tokens;

    // Constructeur
    /**
     * Constructeur par défaut
     * @param nom nom de l'utilisateur
     * @param prenom prénom de l'utilisateur
     */
    public CompteUtilisateur(String nom, String prenom) {
        this.tokens = new ArrayList<>();
        this.historiqueCommandes = new ArrayList<>();
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

    public ArrayList<CommandeAvecID> getHistoriqueCommandes() {
        return this.historiqueCommandes;
    }

    public void ajouterCommande(CommandeAvecID commande, Token token) throws TokenException {
        if (tokens.contains(token)) {
            tokens.remove(token);
            this.historiqueCommandes.add(commande);
            this.statistique.updateUserStat(commande, this.statisticUserPassword);
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

    public HashMap<CompteUtilisateur,Integer> getStatUser() throws PasswordException {
        return this.statistique.getUserStat(this.statisticUserPassword);
    }

    public HashMap<Restaurant,Integer> getStatResto() throws PasswordException {
        return this.statistique.getRestaurantStat(this.statisticUserPassword);
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

    @Override
    public void notify(String message) {
        System.out.println(message);
    }
}
