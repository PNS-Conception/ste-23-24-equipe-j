package fr.unice.polytech.builder;

import fr.unice.polytech.commande.CommandeAvecID;
import fr.unice.polytech.commande.CommandeGroupe;
import fr.unice.polytech.commande.CommandeSimple;
import fr.unice.polytech.utilisateur.CompteUtilisateur;

/**
 * Classe Builder d'une commande simple, multiple ou groupe
 * @author Equipe J
 */
public class BuilderCommandeSimpleMultipleGroupe extends BuilderCommande{
    private TypeCommandeSimple typeCommandeSimple;

    // Constructeur
    /**
     * Constructeur par défaut
     * @param id l'identifiant de la commande
     * @param createur le créateur de la commande
     */
    public BuilderCommandeSimpleMultipleGroupe(long id, CompteUtilisateur createur) {
        super(id, createur);
        typeCommandeSimple = TypeCommandeSimple.SIMPLE;
    }

    /**
     * Méthode pour choisir le type de commande
     * @param typeCommande le type de commande
     */
    public BuilderCommandeSimpleMultipleGroupe buildTypeCommandeSimple(TypeCommandeSimple typeCommande) {
        this.typeCommandeSimple = typeCommande;
        return this;
    }

    /**
     * Méthode pour avoir une commande
     * @return la commande
     */
    public CommandeAvecID build() {
        return switch (typeCommandeSimple) {
            case SIMPLE -> new CommandeSimple(id, createur);
            case GROUPEE -> new CommandeGroupe(id, createur);
        };
    }
}
