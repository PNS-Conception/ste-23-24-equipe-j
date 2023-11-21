package fr.unice.polytech.builder;

import fr.unice.polytech.utilisateur.CompteUtilisateur;

public abstract class BuilderCommande {
    protected final long id;
    protected final CompteUtilisateur createur;

    // Constructeur
    /**
     * Constructeur par défaut
     * @param id l'identifiant de la commande
     * @param createur le créateur de la commande
     */
    protected BuilderCommande(long id, CompteUtilisateur createur) {
        this.id = id;
        this.createur = createur;
    }
}
