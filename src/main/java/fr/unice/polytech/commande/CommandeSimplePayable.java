package fr.unice.polytech.commande;

import fr.unice.polytech.commande.interfacecommande.IPayable;
import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.nourriture.TypeMenuPlat;
import fr.unice.polytech.restaurant.RestaurantNonValideException;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.PaiementCommande;

/**
 * Classe abstraite d'une commande seule payable avec un ID
 * @author Equipe J
 */
public abstract class CommandeSimplePayable extends CommandeSimpleAvecID implements IPayable {
    protected PaiementCommande paiementCommande;

    /**
     * Constructeur par défaut
     * @param idCommande l'identifiant de la commande
     * @param createurCommande le créateur de la commande
     */
    protected CommandeSimplePayable(long idCommande, CompteUtilisateur createurCommande) {
        super(idCommande, createurCommande);
        paiementCommande = new PaiementCommande();
    }

    @Override
    public double getPrix() {
        return paiementCommande.getPrix(menuPlats);
    }

    @Override
    public void ajoutMenuPlat(MenuPlat menuPlat, TypeMenuPlat typeMenuPlat) throws RestaurantNonValideException {
        super.ajoutMenuPlat(menuPlat, typeMenuPlat);
        paiementCommande.ajoutPrix(menuPlat.getPrix());
    }

    @Override
    public boolean supprimerMenuPlat(MenuPlat menuPlat) {
        boolean estSupprimer = super.supprimerMenuPlat(menuPlat);

        if (estSupprimer)
            paiementCommande.retraitPrix(menuPlat.getPrix());

        return estSupprimer;
    }

    @Override
    public void payerCommande() {
        if (paiementCommande.payerCommande())
            setEtatCommande(EtatCommande.EN_PREPARATION);
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
