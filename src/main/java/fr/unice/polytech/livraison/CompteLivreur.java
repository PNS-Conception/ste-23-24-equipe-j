package fr.unice.polytech.livraison;
import fr.unice.polytech.observer.EventListener;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.adress.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Compte du livreur contant son nom, prénom et sa position actuelle
 * @author Equipe J
 */
public class CompteLivreur implements EventListener {
    private Position position ;
    private final String nom;
    private final String prenom;
    private List<Integer> notes;

    // Constructeurs
    /**
     * Constructeur du compte livreur contenant le prenom et nom d'un livreur
     * @param prenom le prenom du livreur
     * @param nom le nom du livreur
     */
    public CompteLivreur(String prenom, String nom) {
        this(prenom, nom, new Position(0, 0));
    }

    /**
     * Constructeur par défaut
     * @param prenom le prenom du livreur
     * @param nom le nom du livreur
     * @param position la position du livreur, peut être (0,0) par défaut
     */
    public CompteLivreur(String prenom, String nom, Position position) {
        this.position = position;
        this.nom = nom;
        this.prenom = prenom;
        this.notes = new ArrayList<>();
    }

    // Accesseurs et setters

    /**
     * Retourne le nom du livreur
     * @return le nom du livreur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne le prenom du livreur
     * @return le prenom du livreur
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Retourne la position du livreur
     * @return la position du livreur
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Retourne la moyenne des notes du Livreur
     * @return la moyenne des notes du Livreur
     */
    public double getNote(){
        double noteTotal = 0;
        for (Integer note: notes){
            noteTotal += note;
        }
        return noteTotal/notes.size();
    }

    /**
     * Retourne la liste des notes du livreur
     * @return la liste des notes
     */
    public List<Integer> getNotes(){
        return notes;
    }

    /**
     * Ajoute une note à la liste des notes du Livreur
     * @param note la note à ajouter
     */
    public void addNote(Integer note){
        notes.add(note);
    }

    /**
     * Modifie la position du livreur
     * @param position la position du livreur
     * @throws IllegalArgumentException si la position est nulle
     */
    public void setPosition(Position position) {
        if (position == null)
            throw new IllegalArgumentException("La position ne peut pas être nulle");
        this.position = position;
    }

    /**
     * Note l'utilisateur par rapport au retard et à l'amabilité
     * @param compteUtilisateur l'utilisateur à noter
     * @param noteRetard la note pour le retard
     * @param noteAimabilite la note pour l'amabilte
     */
    public void noteUtilisateur(CompteUtilisateur compteUtilisateur, Integer noteRetard, Integer noteAimabilite) {
        compteUtilisateur.addNoteRetard(noteRetard);
        compteUtilisateur.addNoteAmabilite(noteAimabilite);
    }

    @Override
    public void notify(String message) {
        System.out.println(message);
    }
}
