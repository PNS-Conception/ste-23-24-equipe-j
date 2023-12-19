package fr.unice.polytech.builder;

import fr.unice.polytech.commande.ACommandeGroupe;
import fr.unice.polytech.commande.CommandeMultipleAjoutable;
import fr.unice.polytech.commande.CommandeSimpleAjoutable;
import fr.unice.polytech.commande.interfacecommande.ICommandeAjoutable;
import fr.unice.polytech.utilisateur.CompteUtilisateur;

/**
 * Classe permettant de créer des commandes ajoutables dans une commande groupe
 * @author Equipe J
 */
public class BuilderCommandeSimpleMultiplePourGroupe extends BuilderCommande {
    private ACommandeGroupe commandeGroupe;
    private TypeCommandeAjoutable typeCommande;

    /**
     * Constructeur par défaut
     * @param id l'identifiant de la commande
     * @param createur le créateur de la commande
     */
    public BuilderCommandeSimpleMultiplePourGroupe(long id, CompteUtilisateur createur, ACommandeGroupe commandeGroupe) {
        super(id, createur);
        this.commandeGroupe = commandeGroupe;
        typeCommande = TypeCommandeAjoutable.SIMPLE;
    }

    /**
     * Permet de définir le type de la commande
     * @param typeCommande le type de la commande
     * @return le builder lui-même
     */
    public BuilderCommandeSimpleMultiplePourGroupe setTypeCommandeSimple(TypeCommandeAjoutable typeCommande) {
        this.typeCommande = typeCommande;
        return this;
    }

    /**
     * Permet de construire la commande ajoutable dans une commande groupe
     * @return la commande ajoutable dans une commande groupe
     */
    public ICommandeAjoutable build() {
        return switch (typeCommande) {
            case SIMPLE -> new CommandeSimpleAjoutable(id, createur, commandeGroupe);
            case MULTIPLE -> new CommandeMultipleAjoutable(id, createur, commandeGroupe);
        };
    }
}
