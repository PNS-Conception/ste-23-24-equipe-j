package fr.unice.polytech.commande;

import fr.unice.polytech.commande.interfacecommande.ICommandeAjoutable;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.adress.Position;
import fr.unice.polytech.utils.temps.Date;
import fr.unice.polytech.utils.temps.Horaire;

/**
 * Classe d'une commande groupe composé de commandes payables, avec des id
 * @author Equipe J
 */
public class CommandeGroupe extends ACommandeGroupe {

    // Constructeur

    /**
     * Constructeur par défaut
     * @param idCommande identifiant de la commande
     * @param createurCommande le créateur de la commande
     */
    public CommandeGroupe(long idCommande, CompteUtilisateur createurCommande) {
        super(idCommande, createurCommande);
    }

    @Override
    public void ajouterCommande(ICommandeAjoutable commande) {
        commandes.add(commande);
    }

    @Override
    public void supprimerCommande(ICommandeAjoutable commande) {
        commandes.remove(commande);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
