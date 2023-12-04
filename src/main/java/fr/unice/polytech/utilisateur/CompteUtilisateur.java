package fr.unice.polytech.utilisateur;

import fr.unice.polytech.commande.CommandeAvecID;
import fr.unice.polytech.commande.interfacecommande.ICommande;
import fr.unice.polytech.exceptions.PasswordException;
import fr.unice.polytech.livraison.CompteLivreur;
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
    private StatusUtilisateur status;
    private int solde; // en centimes pour éviter les erreurs d'arrondi
    private List<Integer> notesRetard;
    private List<Integer> notesAmabilite;


    public CompteUtilisateur(String nom,
                             String prenom,
                             String password,
                             Statistique statistique,
                             StatusUtilisateur status,
                             SavedPosition savedPosition) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.statistique = statistique;
        this.historique = new Historique();
        this.tokens = new ArrayList<>();
        this.solde = 0;
        this.status = status;
        this.adresseEnregistrees = savedPosition;
        this.notesRetard = new ArrayList<>();
        this.notesAmabilite = new ArrayList<>();
    }

    //TODO : to delete, keep for test
    public CompteUtilisateur(String nom, String prenom) {
        this(nom, prenom, DEFAULT_PASSWORD, new Statistique(),StatusUtilisateur.NORMAL , new SavedPosition());
    }

    public CompteUtilisateur(String nom, String prenom, Statistique statistique, SavedPosition savedPosition, StatusUtilisateur status) {
        this(nom, prenom, DEFAULT_PASSWORD, statistique, status, savedPosition);
    }

    public CompteUtilisateur(String nom, String prenom, Statistique statistique, SavedPosition savedPosition) {
        this(nom, prenom, DEFAULT_PASSWORD, statistique, StatusUtilisateur.NORMAL, savedPosition);
    }

    public CompteUtilisateur(String nom, String prenom, Statistique statistique, SavedPosition savedPosition, String password) {
        this(nom, prenom, password, statistique, StatusUtilisateur.NORMAL, savedPosition);
    }


    public void setStatusUtilisateur(StatusUtilisateur status) {
        this.status = status;
    }

    public StatusUtilisateur getStatusUtilisateur() {
        return status;
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

    /**
     * Return la moyenne des notes de retard de l'utilisateur
     * @return la moyenne des notes de retard de l'utilisateur
     */
    public double getNoteRetard(){
        double noteTotal = 0;
        for (Integer note: notesRetard){
            noteTotal += note;
        }
        return noteTotal/notesRetard.size();
    }

    /**
     * Retourne la liste des notes de retard de l'utilisateur
     * @return la liste des notes de retard
     */
    public List<Integer> getNotesRetard(){
        return notesRetard;
    }

    /**
     * Return la moyenne des notes d'amabilité de l'utilisateur
     * @return la moyenne des notes d'amabilité de l'utilisateur
     */
    public double getNoteAmabilite(){
        double noteTotal = 0;
        for (Integer note: notesAmabilite){
            noteTotal += note;
        }
        return noteTotal/ notesAmabilite.size();
    }

    /**
     * Retourne la liste des notes d'amabilité de l'utilisateur
     * @return la liste des notes d'amabilité
     */
    public List<Integer> getNotesAmabilite(){
        return notesAmabilite;
    }

    /**
     * Ajoute une note de retard à la liste de notes de retard de l'utilisateur
     * @param noteRetard la note à ajouter
     */
    public void addNoteRetard(Integer noteRetard){
        notesRetard.add(noteRetard);
    }

    /**
     * Ajoute une note d'amabilité à la liste de notes d'amabilité de l'utilisateur
     * @param noteAmabilite la note à ajouter
     */
    public void addNoteAmabilite(Integer noteAmabilite){
        notesAmabilite.add(noteAmabilite);
    }

    /**
     * Note le livreur
     * @param compteLivreur le livreur à noter
     * @param note la note
     */
    public void noteLivreur(CompteLivreur compteLivreur, Integer note){
        compteLivreur.addNote(note);
    }

    public void noteRestaurant(Restaurant restaurant, Integer note){
        restaurant.addNote(note);
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