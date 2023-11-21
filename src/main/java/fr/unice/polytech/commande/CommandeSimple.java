package fr.unice.polytech.commande;

import fr.unice.polytech.commande.interfacecommande.ILivrable;
import fr.unice.polytech.livraison.InformationLivraison;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.*;

/**
 * Classe d'une commande simple de commande payable et livrable
 * @author Equipe J
 */
public class CommandeSimple extends CommandeSimplePayable implements ILivrable {
    InformationLivraison informationLivraison;

    /**
     * Constructeur par défaut
     * @param idCommande l'identifiant de la commande
     * @param createurCommande le créateur de la commande
     */
    public CommandeSimple(long idCommande, CompteUtilisateur createurCommande) {
        super(idCommande, createurCommande);
        informationLivraison = new InformationLivraison(this);
    }


    @Override
    public boolean estLivrable() {
        return true;
    }


    @Override
    public InformationLivraison getInformationLivraison() {
        return informationLivraison;
    }


    @Override
    public void setInformationLivraison(Date dateLivraison, Horaire heureLivraison, Position lieuxLivraison) {
        informationLivraison.setInformationLivraison(dateLivraison, heureLivraison, lieuxLivraison);
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
