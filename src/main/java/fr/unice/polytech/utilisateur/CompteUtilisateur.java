package fr.unice.polytech.utilisateur;

import fr.unice.polytech.commande.CommandeAvecID;
import fr.unice.polytech.commande.interfacecommande.ICommande;
import fr.unice.polytech.exceptions.PasswordException;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.traçabilite.Historique;
import fr.unice.polytech.traçabilite.Statistique;
import fr.unice.polytech.exceptions.TokenException;
import fr.unice.polytech.utils.adress.Position;
import fr.unice.polytech.utils.Token;

import java.util.ArrayList;
import java.util.HashMap;
import fr.unice.polytech.observer.EventListener;
import fr.unice.polytech.utils.adress.SavedPosition;

import java.util.List;
import java.util.Objects;

/**
 * Classe représentant un compte utilisateur
 * @author Equipe J
 */
public class CompteUtilisateur implements EventListener {
    public static final String DEFAULT_PASSWORD = "0000";
    private final String statisticUserPassword = "0000";

    private final Historique historique;
    private final Statistique statistique;

    private SavedPosition adresseEnregistrees;
    private ArrayList<Token> tokens;


    private final String nom;
    private final String prenom;
    private String password = DEFAULT_PASSWORD;
    private UserStatut statut;
    private int solde; // en centimes pour éviter les erreurs d'arrondi

    public CompteUtilisateur(String nom,
                             String prenom,
                             String password,
                             Statistique statistique,
                             UserStatut statut,
                             SavedPosition savedPosition) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.statistique = statistique;
        this.historique = new Historique();
        this.tokens = new ArrayList<>();
        this.solde = 0;
        this.statut = statut;
        this.adresseEnregistrees = savedPosition;
    }

    //TODO : to delete, keep for test
    public CompteUtilisateur(String nom, String prenom) {
        this(nom, prenom, DEFAULT_PASSWORD, new Statistique(), UserStatut.NORMAL, new SavedPosition());
    }

    public CompteUtilisateur(String nom, String prenom, Statistique statistique, SavedPosition savedPosition, UserStatut statut) {
        this(nom, prenom, DEFAULT_PASSWORD, statistique, statut, savedPosition);
    }

    public CompteUtilisateur(String nom, String prenom, Statistique statistique, SavedPosition savedPosition) {
        this(nom, prenom, DEFAULT_PASSWORD, statistique, UserStatut.NORMAL, savedPosition);
    }

    public CompteUtilisateur(String nom, String prenom, Statistique statistique, SavedPosition savedPosition, String password) {
        this(nom, prenom, password, statistique, UserStatut.NORMAL, savedPosition);
    }


    public void setStatut(UserStatut statut) {
        this.statut = statut;
    }

    public UserStatut getStatut() {
        return this.statut;
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

    public ArrayList<ICommande> getAllHistorique() {
        return this.historique.getArrayListCommande();
    }

    public void ajouterCommande(CommandeAvecID commande, Token token) throws TokenException {
        tokens.remove(token);
        this.historique.addCommande(commande);
        this.statistique.updateUserStat(commande, this.statisticUserPassword);
    }

    public boolean checkToken(Token token) {
        return tokens.contains(token);
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
        return adresseEnregistrees.getSavedPosition();
    }

    public Position getAdresseEnregistreesParNom(String nom) {
        for (Position adresse : adresseEnregistrees.getSavedPosition()) {
            if (adresse.getNomPosition().equals(nom)) {
                return adresse;
            }
        }
        return null;
    }

    // Méthodes
    public void ajouterAdresse(Position adresse) {
        adresseEnregistrees.addPosition(adresse);
    }

    @Override
    public void notify(String message) {
        System.out.println(message);
    }
}
