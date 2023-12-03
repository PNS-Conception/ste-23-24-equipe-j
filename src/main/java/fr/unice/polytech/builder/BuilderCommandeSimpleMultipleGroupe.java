package fr.unice.polytech.builder;

import fr.unice.polytech.commande.CommandeAvecID;
import fr.unice.polytech.commande.CommandeBuffet;
import fr.unice.polytech.commande.CommandeGroupe;
import fr.unice.polytech.commande.CommandeMultiple;
import fr.unice.polytech.commande.CommandeSimple;
import fr.unice.polytech.utilisateur.CompteUtilisateur;

/**
 * Classe Builder d'une commande
 * @author Equipe J
 */
public class BuilderCommandeSimpleMultipleGroupe extends BuilderCommande{
    private TypeCommandeSimple typeCommandeSimple;
    private CompteUtilisateur destinataire;

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
     * @param typeCommandeSimple le type de commande
     * @return le builder <code>this</code>
     */
    public BuilderCommandeSimpleMultipleGroupe buildTypeCommandeSimple(TypeCommandeSimple typeCommandeSimple) {
        this.typeCommandeSimple = typeCommandeSimple;
        return this;
    }

    /**
     * Ajoute un destinataire à la commande
     * @param destinataire le destinataire à ajouter à la commande
     * @return le builder <code>this</code>
     */
    public BuilderCommandeSimpleMultipleGroupe buildDestinataire(CompteUtilisateur destinataire) {
        this.destinataire = destinataire;
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
            case MULTIPLE -> new CommandeMultiple(id, createur);
            case BUFFET -> new CommandeBuffet(id, createur, destinataire);
        };
    }
}
