package fr.unice.polytech.livraison;
import fr.unice.polytech.observer.EventListener;
import fr.unice.polytech.utils.Position;

/**
 * Compte du livreur contant son nom, prénom et sa position actuelle
 * @author Equipe J
 */
public class CompteLivreur implements EventListener {
    private Position position ;
    private final String nom;
    private final String prenom;

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
     * Modifie la position du livreur
     * @param position la position du livreur
     * @throws IllegalArgumentException si la position est nulle
     */
    public void setPosition(Position position) {
        if (position == null)
            throw new IllegalArgumentException("La position ne peut pas être nulle");
        this.position = position;
    }

    @Override
    public void notify(String message) {
        System.out.println(message);
    }
}
